/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Danhmuc;
import com.ntt.repositories.DanhMucRepository;
import com.ntt.services.DanhMucServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DanhMucServicesImpl implements DanhMucServices{
    @Autowired
    private DanhMucRepository category;

    @Override
    public List<Danhmuc> getAllDanhMuc() {
        return category.getAllDanhMuc();
    }

    @Override
    public Danhmuc getDanhMucById(int id) {
        return category.getDanhMucById(id);
    }
    
}
