/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.BaiDangRequest;
import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Danhmuc;
import com.ntt.pojo.User;
import com.ntt.pojo.Videobaidang;
import com.ntt.services.BaiDangServices;
import com.ntt.services.DanhMucServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")

public class ApiBaiDangController {

    @Autowired
    private BaiDangServices baiDangService;

    @Autowired
    private UserServices userService;

    @Autowired
    private DanhMucServices danhMucServices;

    @CrossOrigin
    @PostMapping("/gv/dangBai")
    @ResponseStatus(HttpStatus.CREATED)
    public void dangBai(@RequestBody BaiDangRequest request, Principal principal) {
        String username = principal.getName();
        User giaoVien = userService.getUserByUsername(username);

        Baidangvanban baiDang = new Baidangvanban();
        baiDang.setTieuDe(request.getTieuDe());
        baiDang.setNoiDung(request.getNoiDung());
        baiDang.setNgayDang(new Date());
        baiDang.setTrangThai("CHUA_DUYET");
        baiDang.setIdGVDang(giaoVien);

        // Set category
        Danhmuc danhMuc = danhMucServices.getDanhMucById(request.getDanhMucId());
        if (danhMuc != null) {
            baiDang.setDanhMucId(danhMuc);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }

        Videobaidang video = null;
        if (request.getVideoUrl() != null && !request.getVideoUrl().isEmpty()) {
            video = new Videobaidang();
            video.setVideoUrl(request.getVideoUrl());
            video.setMoTa(request.getMoTa());
        }

        try {
            baiDangService.dangBai(baiDang, video);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save article", e);
        }
    }

    @CrossOrigin
    @GetMapping("/bai-dang-chinh-sua")
    public List<Baidangvanban> xemBaiDangChinhSua(Principal principal) {
        String username = principal.getName();
        User giaoVien = userService.getUserByUsername(username);

        List<Baidangvanban> listBaiDang = baiDangService.findByTrangThaiAndUserId("KHONG_DAT", giaoVien.getId());

        return listBaiDang;
    }

    ////Xem demo bài đăng ở react
    @CrossOrigin
    @GetMapping("/demo/{idBaiViet}")
    public Baidangvanban xemDemoBaiViet(@PathVariable int idBaiViet) {
        Baidangvanban baiDang = baiDangService.findById(idBaiViet);
        return baiDang;
    }

    ////Xem bài đăng giáo viên
    @CrossOrigin
    @GetMapping("/bai-dang-public-giao-vien")
    public List<Baidangvanban> xemBaiDangPublicGiaoVien(Principal principal) {
        String username = principal.getName();
        User giaoVien = userService.getUserByUsername(username);

        List<Baidangvanban> listBaiDang = baiDangService.findByTrangThaiAndUserId("DA_DUYET", giaoVien.getId());

        return listBaiDang;
    }

    ///Chỉnh sửa bài viết
    @CrossOrigin
    @PutMapping("/blogs/{id}")
    public void capNhatBaiViet(@PathVariable int id, @RequestBody BaiDangRequest request) {
        Baidangvanban baiDang = baiDangService.findById(id);
        if (baiDang == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bài viết không tồn tại");
        }

        baiDang.setTieuDe(request.getTieuDe());
        baiDang.setNoiDung(request.getNoiDung());
        baiDang.setTrangThai("CHUA_DUYET");
        baiDang.setDanhMucId(danhMucServices.getDanhMucById(request.getDanhMucId()));
        baiDangService.save(baiDang);
    }
    
    ///Xóa bài đăng
    @DeleteMapping("/xoabaidang/{baiDangId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void deleteHoatDong(Model model, @PathVariable(value = "baiDangId") int id) {
        this.baiDangService.deleteBaiDang(id);
    }
    
    ///Lấy ra các bài đăng
    @CrossOrigin
    @GetMapping("/bai-dang-moi-nhat") 
    public List<Baidangvanban> baiDangMoiNhat() {
        List<Baidangvanban> baiDangList = this.baiDangService.get5BaiDangGanNhat();
        return baiDangList;
    }
    
    @CrossOrigin 
    @GetMapping("/bai-dang-tin-hoc")
    public List<Baidangvanban> baiDangTinHoc() {
        List<Baidangvanban> baiDangList = this.baiDangService.getAllBaiDangDanhMuc("Tin Học");
        return baiDangList;
    }
    
    @CrossOrigin 
    @GetMapping("/bai-dang-ngoai-ngu")
    public List<Baidangvanban> baiDangNgoaiNgu() {
        List<Baidangvanban> baiDangList = this.baiDangService.getAllBaiDangDanhMuc("Ngoại Ngữ");
        return baiDangList;
    }
}
