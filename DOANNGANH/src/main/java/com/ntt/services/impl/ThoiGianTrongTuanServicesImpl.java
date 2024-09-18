/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.ThoiGianTrongTuanRepository;
import com.ntt.services.ThoiGianTrongTuanServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ThoiGianTrongTuanServicesImpl implements ThoiGianTrongTuanServices{
    @Autowired
    private ThoiGianTrongTuanRepository tkb;

    @Override
    public List<Thoigiantrongtuan> findByKhoaHocId(int khoaHocId) {
        return tkb.findByKhoaHocId(khoaHocId);
    }

    @Override
    public void taoBuoiHoc(Thoigiantrongtuan t) {
        tkb.taoBuoiHoc(t);
    }

    @Override
    public Thoigiantrongtuan findById(int i) {
        return tkb.findById(i);
    }

    @Override
    public List<Thoigiantrongtuan> listLichGiangDayGiaoVien(int i) {
        return tkb.listLichGiangDayGiaoVien(i);
    }
}
