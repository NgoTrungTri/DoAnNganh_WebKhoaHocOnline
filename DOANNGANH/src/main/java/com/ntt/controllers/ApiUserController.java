/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.components.JwtService;
import com.ntt.pojo.User;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping(path = "/users/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        String email = params.get("email");

        Optional<User> existingUser = userService.getUserByEmail(email);
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists.");
        }

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
        user.setEmail(email);
        user.setUsername(params.get("username"));
        user.setPassword(this.passwordEncoder.encode(params.get("password")));
        user.setUserRole("ROLE_HV");

        if (file.length > 0) {
            user.setFile(file[0]);
        }
        userService.addOrUpdateUser(user);
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

        return ResponseEntity.ok(userData);
    }

    @GetMapping("/bammatkhau")
    @CrossOrigin
    public String XuatPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";  // Mật khẩu gốc
        String encodedPassword = encoder.encode(rawPassword);  // Mật khẩu đã được băm
        return encodedPassword;
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
}
