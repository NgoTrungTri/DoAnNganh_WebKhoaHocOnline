/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.ThoiKhoaBieuResponseDTO;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.pojo.User;
import com.ntt.services.KhoaHocServices;
import com.ntt.services.ThoiGianTrongTuanServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api")
public class ApiThoiGianTrongTuan {

    @Autowired
    private ThoiGianTrongTuanServices tkb;

    @Autowired
    private KhoaHocServices khoaHocServices;

    @Autowired
    private UserServices userService;

    @GetMapping("/tkb-hocvien-khongTenKhoaHoc")
    @CrossOrigin
    public List<Thoigiantrongtuan> thoiKhoaBieuHocVien(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        // Lấy danh sách khóa học hiện tại và khóa học sắp tới của học viên
        List<Khoahoc> khoaHocDangHoc = khoaHocServices.getKhoaHocDangHoc(user.getId());
        List<Khoahoc> khoaHocSapToi = khoaHocServices.getKhoaHocSapToi(user.getId());

        // Tạo danh sách cuối cùng chứa thời gian biểu
        List<Thoigiantrongtuan> tkbFinalList = new ArrayList<>();

        // Duyệt qua từng khóa học đang học và thêm thời gian biểu của từng khóa học vào danh sách
        for (Khoahoc khoaHoc : khoaHocDangHoc) {
            List<Thoigiantrongtuan> tkbList = tkb.findByKhoaHocId(khoaHoc.getId());
            tkbFinalList.addAll(tkbList);
        }

        // Tương tự duyệt qua các khóa học sắp tới và thêm thời gian biểu vào danh sách
        for (Khoahoc khoaHoc : khoaHocSapToi) {
            List<Thoigiantrongtuan> tkbList = tkb.findByKhoaHocId(khoaHoc.getId());
            tkbFinalList.addAll(tkbList);
        }

        return tkbFinalList;
    }

    @GetMapping("/tkb-hocvien")
    @CrossOrigin
    public List<ThoiKhoaBieuResponseDTO> thoiKhoaBieuHocVien1(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        // Lấy danh sách khóa học hiện tại và khóa học sắp tới của học viên
        List<Khoahoc> khoaHocDangHoc = khoaHocServices.getKhoaHocDangHoc(user.getId());
        List<Khoahoc> khoaHocSapToi = khoaHocServices.getKhoaHocSapToi(user.getId());

        // Tạo danh sách cuối cùng chứa thời gian biểu
        List<ThoiKhoaBieuResponseDTO> tkbFinalList = new ArrayList<>();

        // Duyệt qua từng khóa học đang học và thêm thời gian biểu của từng khóa học vào DTO
        for (Khoahoc khoaHoc : khoaHocDangHoc) {
            List<Thoigiantrongtuan> tkbList = tkb.findByKhoaHocId(khoaHoc.getId());
            for (Thoigiantrongtuan tgtt : tkbList) {
                ThoiKhoaBieuResponseDTO dto = new ThoiKhoaBieuResponseDTO(khoaHoc.getTenKhoaHoc(), tgtt);
                tkbFinalList.add(dto);
            }
        }

        // Tương tự duyệt qua các khóa học sắp tới và thêm thời gian biểu vào DTO
        for (Khoahoc khoaHoc : khoaHocSapToi) {
            List<Thoigiantrongtuan> tkbList = tkb.findByKhoaHocId(khoaHoc.getId());
            for (Thoigiantrongtuan tgtt : tkbList) {
                ThoiKhoaBieuResponseDTO dto = new ThoiKhoaBieuResponseDTO(khoaHoc.getTenKhoaHoc(), tgtt);
                tkbFinalList.add(dto);
            }
        }

        return tkbFinalList;
    }
    
    @GetMapping("/tkb-giaovien")
    @CrossOrigin
    public List<ThoiKhoaBieuResponseDTO> thoiKhoaBieuGiaoVien(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        // Lấy danh sách khóa học hiện tại và khóa học sắp tới của học viên
        List<Khoahoc> khoaHocGiangDay = khoaHocServices.getKhoaHocByGiaoVien(user.getId());

        // Tạo danh sách cuối cùng chứa thời gian biểu
        List<ThoiKhoaBieuResponseDTO> tkbFinalList = new ArrayList<>();

        // Duyệt qua từng khóa học đang học và thêm thời gian biểu của từng khóa học vào DTO
        for (Khoahoc khoaHoc : khoaHocGiangDay) {
            List<Thoigiantrongtuan> tkbList = tkb.findByKhoaHocId(khoaHoc.getId());
            for (Thoigiantrongtuan tgtt : tkbList) {
                ThoiKhoaBieuResponseDTO dto = new ThoiKhoaBieuResponseDTO(khoaHoc.getTenKhoaHoc(), tgtt);
                tkbFinalList.add(dto);
            }
        }
        
        return tkbFinalList;
    }
}
