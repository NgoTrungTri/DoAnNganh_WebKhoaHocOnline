/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Luonggiaovien;
import com.ntt.repositories.LuongGiaoVienRepository;
import com.ntt.services.LuongGiaoVienServices;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class LuongGiaoVienServicesImpl implements LuongGiaoVienServices{
    @Autowired
    private LuongGiaoVienRepository luongGVRepo;

    @Override
    public List<Luonggiaovien> findByGVCoHuu() {
        return this.luongGVRepo.findByGVCoHuu();
    }

    @Override
    public List<Luonggiaovien> findByGVThinhGiang() {
        return this.luongGVRepo.findByGVThinhGiang();
    }

    @Override
    public Luonggiaovien findById(int id) {
        return this.luongGVRepo.findById(id);
    }

    @Override
    public void update(Luonggiaovien luongGiaoVien) {
        this.luongGVRepo.update(luongGiaoVien);
    }

    @Override
    public int countGioDayTrongThang(int giaoVienId) {
        return this.luongGVRepo.countGioDayTrongThang(giaoVienId);
    }

    @Override
    public boolean isGiaoVienCoHuu(int i) {
        return this.luongGVRepo.isGiaoVienCoHuu(i);
    }

    @Override
    public Luonggiaovien findByGiaoVienId(int i) {
        return this.luongGVRepo.findByGiaoVienId(i);
    }
    
}
