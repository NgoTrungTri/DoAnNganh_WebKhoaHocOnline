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
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

    @CrossOrigin
    @PostMapping("/dangBai")
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
        Danhmuc danhMuc = danhMucService.getDanhMucById(request.getDanhMucId());
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
}
