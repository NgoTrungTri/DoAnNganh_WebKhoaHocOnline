/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.User;
import com.ntt.services.ChucVuServices;
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

/**
 *
 * @author PC
 */
@Controller
public class UserController {

    @Autowired
    private UserServices userService;
    @Autowired
    private ChucVuServices chucVuService;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("chucVu", this.chucVuService.getAllChucVu());
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/userpage")
    public String index(Model model, @RequestParam(name = "userRole", required = false) String userRole) {
        List<User> users;
        if (userRole != null) {
            users = this.userService.getUsersByUserRole(userRole);
        } else {
            users = this.userService.getUser();
        }
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add-user")
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute(value = "user") User u, Model model, Principal p) {
        try {
            String username = p.getName();
            User user = userService.getUserByUsername(username);

            if (u.getUserRole().equals("ROLE_ADMIN")) {
                if (user.getUserRole().equals("ROLE_ADMIN")) {
                    this.userService.addOrUpdateUser(u);
                    return "redirect:/userpage";
                } else {
                    model.addAttribute("errorMessage", "Don't have permittion");
                    return "add-user";
                }
            } else {
                this.userService.addOrUpdateUser(u);
                return "redirect:/userpage";
            }

        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Error occurred while adding/updating user: " + ex.getMessage());
            return "add-user";
        }
    }

    @GetMapping("/{userId}")
    public String updateUserView(Model model, @PathVariable(value = "userId") int id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "add-user";
        }
        return "redirect:/userpage";
    }
}
