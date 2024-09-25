/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.repositories.DoanhThuRepository;
import com.ntt.services.DoanhThuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DoanhThuServicesImpl implements DoanhThuServices{
    @Autowired
    private DoanhThuRepository doanhThu;

    @Override
    public double doanhThuKhoaHocThang(int idKhoaHoc, int thang, int nam) {
        return doanhThu.doanhThuKhoaHocThang(idKhoaHoc, thang, nam);
    }

    @Override
    public double doanhThuKhoaHocQuy(int i, int i1, int i2) {
        return doanhThu.doanhThuKhoaHocQuy(i, i1, i2);
    }

    @Override
    public double doanhThuCuaThang(int thang, int nam) {
        return doanhThu.doanhThuCuaThang(thang, nam);
    }
    
}
