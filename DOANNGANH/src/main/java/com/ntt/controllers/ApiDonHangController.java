/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Donhang;
import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.User;
import com.ntt.services.DonHangServices;
import com.ntt.services.KhoaHocServices;
import com.ntt.services.UserServices;
import com.ntt.services.VNPayServices;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiDonHangController {

    @Autowired
    private DonHangServices donHangService;

    @Autowired
    private UserServices userService;

    @Autowired
    private KhoaHocServices khoaHocService;

    @Autowired
    private VNPayServices VNPay;
    
    ////Tạo Url Thanh Toán VNpay
    @CrossOrigin
    @PostMapping("/muaKhoaHoc/{khoaHocId}")
    public ResponseEntity<?> muaKhoaHoc(Principal principal, @PathVariable("khoaHocId") int id) {
        try {
            // Lấy thông tin người dùng hiện tại
            User user = this.userService.getUserByUsername(principal.getName());

            // Kiểm tra nếu người dùng đã đăng nhập
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vui lòng đăng nhập để mua khóa học.");
            }

            // Lấy thông tin khóa học
            Khoahoc khoaHoc = this.khoaHocService.getCourseById(id);

            // Kiểm tra nếu khóa học tồn tại
            if (khoaHoc == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khóa học không tồn tại.");
            }

            // Kiểm tra nếu khóa học đã bắt đầu
            Date currentDate = new Date();
            if (khoaHoc.getNgayBatDau().before(currentDate)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Khóa học đã bắt đầu, không thể mua.");
            }

            // Kiểm tra nếu người dùng đã mua khóa học này
            if (donHangService.isMuaKhoaHoc(id, user.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn đã mua khóa học này rồi.");
            }

            // Tạo URL thanh toán VNPay
            String ipAddr = "171.227.36.182";
            String randomOrderId = UUID.randomUUID().toString(); // Tạo mã ngẫu nhiên với UUID
            String paymentUrl = VNPay.createPaymentUrl(randomOrderId, (int) khoaHoc.getGiaTien(), ipAddr);

            // Trả về URL thanh toán cho frontend
            Map<String, String> response = new HashMap<>();
            response.put("paymentUrl", paymentUrl);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Xử lý ngoại lệ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi mua khóa học.");
        }
    }

    ///Tạo Đơn Hàng
    @CrossOrigin
    @PostMapping("/taoDonHang/{khoaHocId}")
    public ResponseEntity<?> taoDonHang(Principal principal, @PathVariable("khoaHocId") int id) {
        // Lấy thông tin người dùng hiện tại
        User user = this.userService.getUserByUsername(principal.getName());

        // Lấy thông tin khóa học từ id
        Khoahoc khoaHoc = this.khoaHocService.getCourseById(id);

        // Kiểm tra nếu khóa học tồn tại
        if (khoaHoc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khóa học không tồn tại.");
        }

        // Tạo Đơn hàng
        Donhang dh = new Donhang();
        dh.setKhoaHocId(khoaHoc);
        dh.setTongTien(khoaHoc.getGiaTien());
        dh.setNgayTao(new Date());
        dh.setUserId(user);
        dh.setThongTinThanhToan("Thanh Toán Khóa Học " + khoaHoc.getTenKhoaHoc() + " vào ngày " + new Date());

        // Lưu đơn hàng vào cơ sở dữ liệu
        this.donHangService.muaKhoaHoc(dh);

        // Trả về thông báo đơn giản
        return ResponseEntity.ok("Đơn hàng đã được tạo thành công");
    }

}
