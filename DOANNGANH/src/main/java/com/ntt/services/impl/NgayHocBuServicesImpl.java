/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Ngayhocbu;
import com.ntt.repositories.NgayHocBuRepository;
import com.ntt.services.NgayHocBuServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class NgayHocBuServicesImpl implements NgayHocBuServices {

    @Autowired
    private NgayHocBuRepository ngayhocbu;

    @Override
    public void TaoNgayHocBu(Ngayhocbu ngayhocbu) {
        this.ngayhocbu.TaoNgayHocBu(ngayhocbu);
    }

    @Override
    public boolean checkNgayHocBuHopLe(Ngayhocbu ngayhocbu, int idGiangVienPhuTrach)  {
        return this.ngayhocbu.checkNgayHocBuHopLe(ngayhocbu, idGiangVienPhuTrach);
    }

    @Override
    public List<Ngayhocbu> findByKhoaHoc(int i) {
        return this.ngayhocbu.findByKhoaHoc(i);
    }

}
