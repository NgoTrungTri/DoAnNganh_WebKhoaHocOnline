/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.User;
import com.ntt.services.BaiDangServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author DELL
 */
@Controller
public class BaiDangController {

    @Autowired
    private BaiDangServices baiDangService;
    
    @Autowired 
    private UserServices userService;

    // Hiển thị danh sách các bài đăng chưa được duyệt
    @GetMapping("/bai-dang-chua-duyet")
    public String getBaiDangChuaDuyet(Model model) {
        List<Baidangvanban> baiDangChuaDuyet = baiDangService.findByTrangThai("CHUA_DUYET");
        model.addAttribute("baiDangList", baiDangChuaDuyet);
        return "list-baidangchuaduyet";
    }

    // Phương thức để duyệt bài đăng
    @PostMapping("/duyet-bai/{id}")
    public String duyetBaiDang(@PathVariable("id") int id, Model model) {
        // Lấy bài đăng theo id và cập nhật trạng thái
        Baidangvanban baiDang = baiDangService.findById(id);
        if (baiDang != null) {
            baiDang.setTrangThai("DA_DUYET");
            baiDangService.save(baiDang);
        }

        List<Baidangvanban> baiDangChuaDuyet = baiDangService.findByTrangThai("CHUA_DUYET");
        model.addAttribute("baiDangList", baiDangChuaDuyet);
        return "list-baidangchuaduyet";
    }

    // Phương thức xem Demo trước
    @GetMapping("/demo/{id}")
    @CrossOrigin
    public String viewBaiDangDemo(@PathVariable("id") int id, Model model) {
        // Lấy bài đăng từ service theo id
        Baidangvanban baiDang = baiDangService.findById(id);

        if (baiDang != null) {
            model.addAttribute("baiDang", baiDang);
            return "demo-baidang";
        } else {
            return "error";
        }
    }

    ///Phương thức xem các bài đã duyệt
    @GetMapping("/bai-dang-da-duyet")
    public String getBaiDangDaDuyet(Model model) {
        List<Baidangvanban> baiDangDaDuyet = baiDangService.findByTrangThai("DA_DUYET");
        model.addAttribute("baiDangList", baiDangDaDuyet);
        return "list-baidangdaduyet";
    }

    ////Góp Ý Bài Đăng
    @PostMapping("/gop-y-bai/{id}")
    @ResponseBody
    public ResponseEntity<String> capNhatGopY(@PathVariable("id") int id,
            @RequestBody Map<String, Object> requestData, Model model,
            Principal principal) {
        
        ///Lấy User đăng nhập hiện tại
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        
        ////Truyền vào Model
        List<Baidangvanban> baiDangChuaDuyet = baiDangService.findByTrangThai("CHUA_DUYET");
        model.addAttribute("baiDangList", baiDangChuaDuyet);
        String noiDungPhanHoi = (String) requestData.get("noiDungPhanHoi");
        String trangThai = (String) requestData.get("trangThai");

        Baidangvanban baiDang = baiDangService.findById(id);
        if (baiDang != null) {
            baiDang.setTrangThai(trangThai);
            baiDang.setNoiDungPhanHoi(noiDungPhanHoi);
            baiDang.setIdNVDuyet(user);
            baiDang.setNgayDuyet(new Date());
            baiDangService.save(baiDang);
            return ResponseEntity.ok("Đã Góp Ý Cho Giáo Viên Đăng Bài");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy bài đăng");
    }
}
