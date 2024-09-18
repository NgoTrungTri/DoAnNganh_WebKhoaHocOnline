/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.KhoahocDecuong;
import com.ntt.services.DeCuongServices;
import com.ntt.services.KhoaHocServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeCuongController {

    @Autowired
    private DeCuongServices deCuongService;

    @Autowired
    private KhoaHocServices khoahocService;

    @GetMapping("/outline/{courseId}")
    public String viewOutLine(Model model, @PathVariable(value = "courseId") int id) {
        List<KhoahocDecuong> decuong = deCuongService.getDeCuongByKhoaHocId(id);
        model.addAttribute("decuong", decuong);
        model.addAttribute("outline", new KhoahocDecuong());
        model.addAttribute("courseId", id);
        return "outline";
    }

    @PostMapping("/outline/{courseId}")
    public String uploadOutLine(@PathVariable("courseId") int courseId, @ModelAttribute(value = "outline") KhoahocDecuong decuong) {
        Khoahoc course = khoahocService.getCourseById(courseId);
        decuong.setKhoaHocId(course);

        this.deCuongService.createDeCuong(decuong);

        // Chuyển hướng đến trang outline với thông tin cập nhật
        return "redirect:/outline/" + courseId;
    }
}
