/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Luonggiaovien;
import com.ntt.services.LuongGiaoVienServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@Controller
public class LuongGiaoVienController {

    @Autowired
    private LuongGiaoVienServices luong;

    @GetMapping("/hesoluong")
    public String listHeSo(Model model, @RequestParam(name = "LoaiGV", required = false) String loaiGV) {
        List<Luonggiaovien> heSo = null;

        // Nếu không có lựa chọn cụ thể, mặc định là "Cơ Hữu"
        if (loaiGV == null || loaiGV.isEmpty()) {
            loaiGV = "Cơ Hữu";
        }

        switch (loaiGV) {
            case "Cơ Hữu":
                heSo = this.luong.findByGVCoHuu();
                break;
            case "Thỉnh Giảng":
                heSo = this.luong.findByGVThinhGiang();
                break;
            default:
                heSo = this.luong.findByGVCoHuu();
                break;
        }

        model.addAttribute("heSo", heSo);
        model.addAttribute("LoaiGV", loaiGV);  // Truyền giá trị loại giáo viên để sử dụng trong JSP
        return "list-hesoluonggiaovien";
    }

    @PostMapping("/updateLuongGiaoVien")
    public String updateLuongGiaoVien(@RequestParam("id") int id,
            @RequestParam("heSo") float heSo,
            @RequestParam("tienLuongThemTheoGio") double tienLuongThemTheoGio) {
        // Tìm giáo viên theo ID
        Luonggiaovien luongGiaoVien = luong.findById(id);

        // Cập nhật giá trị
        luongGiaoVien.setHeSo(heSo);
        luongGiaoVien.setTienLuongThemTheoGio(tienLuongThemTheoGio);

        // Lưu thay đổi vào cơ sở dữ liệu
        luong.update(luongGiaoVien);

        // Redirect về trang danh sách hệ số lương
        return "redirect:/hesoluong";
    }
}
