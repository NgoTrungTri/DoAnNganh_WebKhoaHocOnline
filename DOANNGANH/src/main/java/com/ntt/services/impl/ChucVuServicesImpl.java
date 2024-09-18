/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Chucvu;
import com.ntt.repositories.ChucVuRepository;
import com.ntt.services.ChucVuServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ChucVuServicesImpl implements ChucVuServices{

    @Autowired
    private ChucVuRepository chucVuRepo;
    
    @Override
    public List<Chucvu> getAllChucVu() {
        return chucVuRepo.getAllChucVu();
    }
    
}
