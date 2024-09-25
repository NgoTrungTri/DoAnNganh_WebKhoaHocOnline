/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.DoanhThuQuyForm;
import com.ntt.pojo.Khoahoc;
import com.ntt.services.DoanhThuServices;
import com.ntt.services.KhoaHocServices;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@Controller
public class DoanhThuController {

    @Autowired
    private KhoaHocServices khoaHocServices;

    @Autowired
    private DoanhThuServices doanhThu;

    @GetMapping("/doanh-thu")
    public String doanhThuForm(Model model) {
        // Lấy tháng và năm hiện tại
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // Thêm tháng và năm hiện tại vào model để truyền sang JSP
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("currentYear", currentYear);

        return "doanhThu";
    }

    @PostMapping("/doanh-thu")
    public String doanhThuSubmit(@RequestParam("thang") int thang, @RequestParam("nam") int nam, Model model) {
        List<Khoahoc> listKhoaHoc = khoaHocServices.getKhoaHocActiveInMonth(thang, nam);
        model.addAttribute("danhSachKhoaHoc", listKhoaHoc);

        List<Double> doanhThuList = new ArrayList<>();

        for (Khoahoc khoaHoc : listKhoaHoc) {
            double DoanhThu = 0;
            DoanhThu = DoanhThu + doanhThu.doanhThuKhoaHocThang(khoaHoc.getId(), thang, nam);
            doanhThuList.add(DoanhThu);
        }

        model.addAttribute("doanhThuList", doanhThuList);

        return "doanhThu";
    }

    @GetMapping("/doanh-thu-quy")
    public String doanhThuTheoQuyHienTai(Model model) {
        // Lấy thời gian hiện tại
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        model.addAttribute("nam", currentYear);
        return "doanhThuQuy";
    }

    @PostMapping("/doanh-thu-quy")
    public String doanhThuQuaterSubmit(@ModelAttribute DoanhThuQuyForm form, Model model) {

        int quy = form.getQuy();
        int nam = form.getNam();
        System.out.println("Quý nhận từ request: " + quy);

        // Kiểm tra giá trị quý
        if (quy < 1 || quy > 4) {
            model.addAttribute("error", "Quý không hợp lệ, vui lòng chọn từ 1 đến 4.");
            return "doanhThuQuy";
        }

        // Lấy danh sách khóa học active trong quý
        List<Khoahoc> danhSachKhoaHoc = khoaHocServices.getKhoaHocActiveInQuarter(quy, nam);
        model.addAttribute("danhSachKhoaHoc", danhSachKhoaHoc);

        List<Double> doanhThuList = new ArrayList<>();

        for (Khoahoc khoaHoc : danhSachKhoaHoc) {
            double DoanhThu = doanhThu.doanhThuKhoaHocQuy(khoaHoc.getId(), quy, nam);
            doanhThuList.add(DoanhThu);
        }

        model.addAttribute("doanhThuList", doanhThuList);

        int startMonth = (quy - 1) * 3 + 1; // Tháng bắt đầu của quý
        List<Double> doanhThuThangList = new ArrayList<>();

        for (int thang = startMonth; thang <= startMonth + 2; thang++) {
            double doanhThuThang = doanhThu.doanhThuCuaThang(thang, nam);
            doanhThuThangList.add(doanhThuThang);
        }

        model.addAttribute("doanhThuThangList", doanhThuThangList);

        // Tạo danh sách tháng để truyền vào biểu đồ
        List<String> thangLabels = new ArrayList<>();
        for (int thang = startMonth; thang <= startMonth + 2; thang++) {
            thangLabels.add(getTenThang(thang));
        }
        model.addAttribute("thangLabels", thangLabels);

        return "doanhThuQuy";
    }

    private String getTenThang(int thang) {
        switch (thang) {
            case 1:
                return "Tháng 1";
            case 2:
                return "Tháng 2";
            case 3:
                return "Tháng 3";
            case 4:
                return "Tháng 4";
            case 5:
                return "Tháng 5";
            case 6:
                return "Tháng 6";
            case 7:
                return "Tháng 7";
            case 8:
                return "Tháng 8";
            case 9:
                return "Tháng 9";
            case 10:
                return "Tháng 10";
            case 11:
                return "Tháng 11";
            case 12:
                return "Tháng 12";
            default:
                return "Không xác định";
        }
    }

}
