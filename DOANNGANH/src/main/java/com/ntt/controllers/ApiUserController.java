/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class ApiUserController {
    
    @GetMapping("/bammatkhau")
    @CrossOrigin
    public String bamMatKhau() {
        BCryptPasswordEncoder bcr = new BCryptPasswordEncoder();
        String rawPass = "admin";
        String bamPass = bcr.encode(rawPass);
        return bamPass;
    }
}
