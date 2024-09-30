/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Donhang;
import com.ntt.services.DonHangServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@Controller
public class DonHangController {

    @Autowired
    private DonHangServices donHangService;

    @GetMapping("/donhang/viewpager")
    public String getDonhangsForViewPager(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String tenKhoaHoc,
            @RequestParam(required = false) String activeTab,
            Model model) {
        int size = 5;       

        // Lấy tổng số đơn hàng theo ngày và tính số trang
        int totalDonhangsByDay = donHangService.countDonhangsByCurrentDay();
        int totalPagesByDay = (int) Math.ceil((double) totalDonhangsByDay / size);
        List<Donhang> donhangsByDay = donHangService.findDonhangsByCurrentDay(page, size);

        // Lấy tổng số đơn hàng theo tháng và tính số trang
        int totalDonhangsByMonth = donHangService.countDonhangsByCurrentMonth();
        int totalPagesByMonth = (int) Math.ceil((double) totalDonhangsByMonth / size);
        List<Donhang> donhangsByMonth = donHangService.findDonhangsByCurrentMonth(page, size);

        // Lấy tổng số đơn hàng theo tên khóa học và tính số trang
        List<Donhang> donhangsByKhoaHoc = new ArrayList<>();
        int totalPagesByKhoaHoc = 0;
        if (tenKhoaHoc != null && !tenKhoaHoc.isEmpty()) {
            int totalDonhangsByKhoaHoc = donHangService.countDonhangsByKhoaHocName(tenKhoaHoc);
            totalPagesByKhoaHoc = (int) Math.ceil((double) totalDonhangsByKhoaHoc / size);
            donhangsByKhoaHoc = donHangService.findDonhangsByKhoaHocName(tenKhoaHoc, page, size);
        }

        if (page < 0) {
            model.addAttribute("eror", "error");
        }
        // Truyền dữ liệu vào model để hiển thị trên JSP
        model.addAttribute("donhangsByDay", donhangsByDay);
        model.addAttribute("totalPagesByDay", totalPagesByDay);
        model.addAttribute("donhangsByMonth", donhangsByMonth);
        model.addAttribute("totalPagesByMonth", totalPagesByMonth);
        model.addAttribute("donhangsByKhoaHoc", donhangsByKhoaHoc);
        model.addAttribute("totalPagesByKhoaHoc", totalPagesByKhoaHoc);
        model.addAttribute("tenKhoaHoc", tenKhoaHoc);
        model.addAttribute("activeTab", activeTab != null ? activeTab : "ngay");

        return "list-order";
    }
}
