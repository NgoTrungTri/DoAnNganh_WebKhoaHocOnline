/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.components.JwtService;
import com.ntt.pojo.Chucvu;
import com.ntt.pojo.User;
import com.ntt.services.EmailServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServices userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailServices emailService;

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Map<String, User> temporaryUserStorage = new HashMap<>();

    // API tạo người dùng và tạo OTP (form-data)
    @PostMapping(path = "/users/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(
            @RequestParam Map<String, String> params,
            @RequestPart MultipartFile[] file
    ) {
        String rawPassword = params.get("password");
        String encodedPassword = passwordEncoder.encode(rawPassword);
        String email = params.get("email");
        String username = params.get("username");

        // Kiểm tra email và username đã tồn tại chưa
        Optional<User> existingUser = userService.getUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists.");
        }

        User existingUserByUsername = userService.getUserByUsername(username);
        if (existingUserByUsername != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username already exists.");
        }

        // Tạo đối tượng User
        User user = new User();
        user.setHo(params.get("ho"));
        user.setTen(params.get("ten"));
        String ngaySinhStr = params.get("ngaySinh");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ngaySinh = sdf.parse(ngaySinhStr);
            user.setNgaySinh(ngaySinh);
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format for ngaySinh. Please use yyyy-MM-dd.");
        }
        user.setGioiTinh(params.get("gioiTinh"));
        user.setNgayTao(new Date());
        user.setChucVuId(new Chucvu(4)); // Giả sử ID chức vụ là 4
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setUserRole("ROLE_HV");

        // Lưu file nếu có
        if (file.length > 0) {
            user.setFile(file[0]);
        }

        // Tạo mã OTP ngẫu nhiên
        String otp = generateOtp();
        otpStorage.put(email, otp);

        // Lưu trữ thông tin người dùng tạm thời
        temporaryUserStorage.put(email, user);

        // Gửi email OTP
        emailService.sendEmail(email, "Xác nhận đăng ký", "Mã OTP của bạn là: " + otp);
    }

    // Hàm tạo OTP ngẫu nhiên
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Mã OTP gồm 6 chữ số
        return String.valueOf(otp);
    }

    @PostMapping(path = "/users/verifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void verifyOtp(@RequestBody Map<String, String> params) {
        String email = params.get("email");
        String otp = params.get("otp");

        // Kiểm tra mã OTP
        String storedOtp = otpStorage.get(email);
        if (storedOtp == null || !storedOtp.equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid OTP.");
        }

        // OTP hợp lệ, lấy thông tin người dùng từ bộ nhớ tạm
        User user = temporaryUserStorage.get(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User data not found.");
        }

        // Lưu người dùng vào cơ sở dữ liệu
        userService.addOrUpdateUser(user);

        // Xóa OTP và thông tin người dùng khỏi bộ nhớ tạm sau khi hoàn tất đăng ký
        otpStorage.remove(email);
        temporaryUserStorage.remove(email);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {

        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/firebase-login")
//    @CrossOrigin
//    public ResponseEntity<?> loginWithFirebase(@RequestBody String idToken) {
//        try {
//            // Xác thực token Firebase
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//            String email = decodedToken.getEmail();
//            String uid = decodedToken.getUid();
//
//            // Kiểm tra xem user đã tồn tại hay chưa
//            Optional<User> existingUser = userService.getUserByEmail(email);
//            if (existingUser.isPresent()) {
//                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email đã tồn tại trong hệ thống");
//            }
//
//            // Nếu user chưa tồn tại, tạo user mới
//            User newUser = new User();
//            newUser.setEmail(email);
//            newUser.setFirebaseUid(uid);
//            newUser.setRoles(Collections.singletonList("ROLE_USER")); // Gán role cho user
//
//            userService.addOrUpdateUser(newUser);
//
//            // Tạo JWT token dựa trên user mới tạo
//            String token = this.jwtService.generateTokenLogin(user.getUsername());
//
//            return ResponseEntity.ok(token);
//
//        } catch (FirebaseAuthException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Firebase không hợp lệ");
//        } catch (ResponseStatusException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getReason());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống");
//        }
//    }

    @GetMapping(path = "/current-user/")
    @CrossOrigin
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> userData = new HashMap<>();
        userData.put("ho", user.getHo());
        userData.put("ten", user.getTen());
        userData.put("username", user.getUsername());
        userData.put("ngaySinh", user.getNgaySinh());
        userData.put("gioiTinh", user.getGioiTinh());
        userData.put("email", user.getEmail());
        userData.put("avatar", user.getAvatar());
        userData.put("userRole", user.getUserRole());

        return ResponseEntity.ok(userData);
    }

    @GetMapping(path = "/userinfo/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin

    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/userRole")
    @CrossOrigin
    public String checkUserRole(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return user.getUserRole();
    }
}
