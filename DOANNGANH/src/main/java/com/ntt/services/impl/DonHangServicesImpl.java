/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Donhang;
import com.ntt.pojo.Khoahoc;
import com.ntt.repositories.DonHangRepository;
import com.ntt.services.DonHangServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DonHangServicesImpl implements DonHangServices {

    @Autowired
    private DonHangRepository DonHangRepo;

    @Override
    public List<Donhang> findDonhangsByCurrentDay(int page, int size) {
        return this.DonHangRepo.findDonhangsByCurrentDay(page, size);
    }

    @Override
    public List<Donhang> findDonhangsByCurrentMonth(int page, int size) {
        return this.DonHangRepo.findDonhangsByCurrentMonth(page, size);
    }

    @Override
    public List<Donhang> findDonhangsByKhoaHocId(int khoaHocId,int page, int size) {
        return this.DonHangRepo.findDonhangsByKhoaHocId(khoaHocId, page, size);
    }

    @Override
    public List<Donhang> findDonhangsByKhoaHocName(String string, int i, int i1) {
        return this.DonHangRepo.findDonhangsByKhoaHocName(string, i, i1);
    }

    @Override
    public int countDonhangsByCurrentDay() {
        return DonHangRepo.countDonhangsByCurrentDay();
    }

    @Override
    public int countDonhangsByCurrentMonth() {
        return DonHangRepo.countDonhangsByCurrentMonth();
    }

    @Override
    public int countDonhangsByKhoaHocName(String string) {
        return DonHangRepo.countDonhangsByKhoaHocName(string);
    }

    @Override
    public void muaKhoaHoc(Donhang donHang) {
        DonHangRepo.muaKhoaHoc(donHang);
    }

    @Override
    public boolean isMuaKhoaHoc(int i, int i1) {
        return DonHangRepo.isMuaKhoaHoc(i, i1);
    }


}
