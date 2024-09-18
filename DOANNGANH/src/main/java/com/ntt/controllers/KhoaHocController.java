package com.ntt.controllers;

import com.ntt.pojo.Danhmuc;
import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.User;
import com.ntt.services.DanhMucServices;
import com.ntt.services.KhoaHocServices;
import com.ntt.services.UserServices;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KhoaHocController {

    @Autowired
    private KhoaHocServices khoahocService;

    @Autowired
    private DanhMucServices categoryServices;

    @Autowired
    private UserServices userServices;

    @ModelAttribute
    public void commonAttr(Model model) {
        List<Danhmuc> danhMucList = categoryServices.getAllDanhMuc();
        model.addAttribute("danhMuc", danhMucList);
        model.addAttribute("giaoVien", this.userServices.getUsersByUserRole("ROLE_GV"));
    }
    
        
    @GetMapping("/view-list-course")
    public String showListCourse(Model model, @RequestParam(name = "category", required = false) String category) {
       List<Khoahoc> courses;
       if (category != null || "".equals(category)){
           courses = khoahocService.getKhoaHocByDanhMuc(category);
       }
       else {
           courses = khoahocService.getAllCourses();
       }      
       model.addAttribute("courses", courses);
       return "list-courses";
    }
    
    @GetMapping("/view-list-course/{courseId}")
    public String updateCourse (Model model, @PathVariable(value = "courseId") int id) {
        Khoahoc course = this.khoahocService.getCourseById(id);

            model.addAttribute("khoahoc", course);
            return "create-courses";

    }
    
    @GetMapping("/create-course")
    public String showCreateForm(Model model) {
        model.addAttribute("khoahoc", new Khoahoc());
        return "create-courses";
    }

    @PostMapping("/create-course")
    public String createCourse(@ModelAttribute("khoahoc") Khoahoc course, Principal principal) {
        String username = principal.getName();
        User currentUser = this.userServices.getUserByUsername(username);
        course.setIdNVTao(currentUser);

        khoahocService.createCourse(course);
        return "redirect:/list-courses";
    }

}
