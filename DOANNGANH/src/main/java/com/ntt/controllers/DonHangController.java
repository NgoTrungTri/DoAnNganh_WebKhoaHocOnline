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

        // Lấy đơn hàng theo ngày - xử lý null
        List<Donhang> donhangsByDay = donHangService.findDonhangsByCurrentDay(page, size);
        if (donhangsByDay == null) donhangsByDay = new ArrayList<>();
        int totalDonhangsByDay = donHangService.countDonhangsByCurrentDay();
        int totalPagesByDay = donhangsByDay.isEmpty() ? 0 : (int) Math.ceil((double) totalDonhangsByDay / size);

        // Lấy đơn hàng theo tháng - xử lý null
        List<Donhang> donhangsByMonth = donHangService.findDonhangsByCurrentMonth(page, size);
        if (donhangsByMonth == null) donhangsByMonth = new ArrayList<>();
        int totalDonhangsByMonth = donHangService.countDonhangsByCurrentMonth();
        int totalPagesByMonth = donhangsByMonth.isEmpty() ? 0 : (int) Math.ceil((double) totalDonhangsByMonth / size);

        // Lấy đơn hàng theo khóa học - xử lý null
        List<Donhang> donhangsByKhoaHoc = new ArrayList<>();
        int totalPagesByKhoaHoc = 0;
        if (tenKhoaHoc != null && !tenKhoaHoc.isEmpty()) {
            donhangsByKhoaHoc = donHangService.findDonhangsByKhoaHocName(tenKhoaHoc, page, size);
            if (donhangsByKhoaHoc == null) donhangsByKhoaHoc = new ArrayList<>();
            int totalDonhangsByKhoaHoc = donHangService.countDonhangsByKhoaHocName(tenKhoaHoc);
            totalPagesByKhoaHoc = donhangsByKhoaHoc.isEmpty() ? 0 : (int) Math.ceil((double) totalDonhangsByKhoaHoc / size);
        }

        // Đảm bảo page không âm
        if (page < 0) page = 0;

        model.addAttribute("donhangsByDay", donhangsByDay);
        model.addAttribute("totalPagesByDay", totalPagesByDay);
        model.addAttribute("donhangsByMonth", donhangsByMonth);
        model.addAttribute("totalPagesByMonth", totalPagesByMonth);
        model.addAttribute("donhangsByKhoaHoc", donhangsByKhoaHoc);
        model.addAttribute("totalPagesByKhoaHoc", totalPagesByKhoaHoc);
        model.addAttribute("tenKhoaHoc", tenKhoaHoc);
        model.addAttribute("page", page);
        model.addAttribute("activeTab", activeTab != null ? activeTab : "ngay");

        return "list-order";
    }
}
