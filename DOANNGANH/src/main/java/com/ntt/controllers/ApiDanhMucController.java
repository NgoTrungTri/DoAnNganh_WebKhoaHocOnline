/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Danhmuc;
import com.ntt.services.DanhMucServices;
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
public class ApiDanhMucController {
    @Autowired
    private DanhMucServices danhMucService;
    
    @GetMapping("/danhMuc")
    @CrossOrigin
    public List<Danhmuc> getAllDanhMuc() {
        return danhMucService.getAllDanhMuc();
    }
}
