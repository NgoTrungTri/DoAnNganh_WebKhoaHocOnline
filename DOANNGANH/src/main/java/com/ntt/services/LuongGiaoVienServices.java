/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Luonggiaovien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface LuongGiaoVienServices {
    List<Luonggiaovien> findByGVCoHuu();
    List<Luonggiaovien> findByGVThinhGiang();
    Luonggiaovien findById(int id);
    public Luonggiaovien findByGiaoVienId(int idGiaoVien);
    void update(Luonggiaovien luongGiaoVien);
    public boolean isGiaoVienCoHuu(int idGiaoVien);
    
    ////Bảng Lương
    public int countGioDayTrongThang(int giaoVienId);
}
