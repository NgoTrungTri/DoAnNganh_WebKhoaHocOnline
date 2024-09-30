/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.KhoahocDecuong;
import com.ntt.pojo.User;
import com.ntt.services.DeCuongServices;
import com.ntt.services.KhoaHocServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiKhoaHocController {

    @Autowired
    private KhoaHocServices khoahocService;

    @Autowired
    private DeCuongServices deCuongService;

    @Autowired
    private UserServices userServices;

    ////Hiện các khóa học giáo viên đó phụ trách mà còn active
    @GetMapping("/khoahoc/giaovien/")
    @CrossOrigin
    public ResponseEntity<List<Khoahoc>> getKhoaHocByGiaoVien(Principal principal) {
        User user = userServices.getUserByUsername(principal.getName());

        List<Khoahoc> khoaHocList = khoahocService.getKhoaHocByGiaoVien(user.getId());

        // Lọc các khóa học còn active
        List<Khoahoc> activeKhoaHocList = new ArrayList<>();

        for (Khoahoc khoahoc : khoaHocList) {
            if (khoahoc.getTrangThai() == true) {
                activeKhoaHocList.add(khoahoc);
            }
        }

        if (activeKhoaHocList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(activeKhoaHocList);
    }

    @GetMapping("/course-detail/{courseId}")
    @CrossOrigin
    public List<KhoahocDecuong> chiTietKhoaHoc(@PathVariable("courseId") int id) {
        Khoahoc khoaHoc = this.khoahocService.getCourseById(id);

        ///Tìm kiếm những KhoaHocDeCuong thuộc khóa học đó 
        List<KhoahocDecuong> deCuongList = this.deCuongService.getDeCuongByKhoaHocId(khoaHoc.getId());

        return deCuongList;
    }

    ////API hiện các khóa học gần nhất 
    @CrossOrigin
    @GetMapping("/khoahoc/tinhoc")
    public List<Khoahoc> loadKhoaHocTinHoc(@RequestParam(defaultValue = "0") int page) {
        // Kiểm tra xem page có nhỏ hơn 0 không
        if (page < 0) {
            page = 0; 
        }

        List<Khoahoc> khoaHocList = this.khoahocService.loadKhoaHocPhanTrang("Tin Học", page, 4);
        return khoaHocList;
    }

    @CrossOrigin
    @GetMapping("/khoahoc/ngoaingu")
    public List<Khoahoc> loadKhoaHocNgoaiNgu(@RequestParam(defaultValue = "0") int page) {
        // Kiểm tra xem page có nhỏ hơn 0 không
        if (page < 0) {
            page = 0;
        }
        // Gọi phương thức để lấy danh sách khóa học phân trang
        List<Khoahoc> khoaHocList = this.khoahocService.loadKhoaHocPhanTrang("Ngoại Ngữ", page, 4);
        return khoaHocList;
    }
    
    ////APi hiện 4 khóa học mới nhất
    @CrossOrigin
    @GetMapping("/khoahoc/moi-nhat")
    public List<Khoahoc> get4KhoaHocMoiNhat() {
        List<Khoahoc> khoaHocList = this.khoahocService.load4KhoaHocMoiNhat();
        return khoaHocList;
    }
    
    /// API hiện ra các khóa học đang học của học viên
    @GetMapping("/khoahocList/dang-hoc")
    @CrossOrigin
    public ResponseEntity<List<Khoahoc>> getKhoaHocDangHoc(Principal principal) {
        User user = userServices.getUserByUsername(principal.getName());
        List<Khoahoc> khoaHocDangHocList = khoahocService.getKhoaHocDangHoc(user.getId()); // Gọi phương thức đã viết

        if (khoaHocDangHocList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có khóa học
        }

        return ResponseEntity.ok(khoaHocDangHocList); // Trả về danh sách khóa học đang học
    }

    /// API hiện ra các khóa học đã mua của học viên
    @GetMapping("/khoahocList/da-mua")
    @CrossOrigin
    public ResponseEntity<List<Khoahoc>> getKhoaHocDaMua(Principal principal) {
        User user = userServices.getUserByUsername(principal.getName());
        List<Khoahoc> khoaHocDaMuaList = khoahocService.getKhoaHocDaMua(user.getId()); // Gọi phương thức đã viết

        if (khoaHocDaMuaList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có khóa học
        }

        return ResponseEntity.ok(khoaHocDaMuaList); // Trả về danh sách khóa học đã mua
    }
    
    @GetMapping("/khoahocList/sap-toi")
    @CrossOrigin
    public ResponseEntity<List<Khoahoc>> getKhoaHocSapToi(Principal principal) {
        User user = userServices.getUserByUsername(principal.getName());
        List<Khoahoc> khoaHocSapToiList = khoahocService.getKhoaHocSapToi(user.getId()); // Gọi phương thức đã viết

        if (khoaHocSapToiList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Trả về 204 nếu không có khóa học
        }

        return ResponseEntity.ok(khoaHocSapToiList); // Trả về danh sách khóa học đã mua
    }
    
}
