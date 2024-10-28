/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Luonggiaovien;
import com.ntt.pojo.UserLoaigiaovien;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface LuongGiaoVienRepository {
    List<Luonggiaovien> findByGVCoHuu();
    List<Luonggiaovien> findByGVThinhGiang();
    Luonggiaovien findById(int id);
    Luonggiaovien findByGiaoVienId(int id);
    void update(Luonggiaovien luongGiaoVien);
    
    boolean isGiaoVienCoHuu(int idGiaoVien);
    
    void createLoaiGiaoVien(UserLoaigiaovien loaiGV);
    
    ////Bảng lương
    public int countGioDayTrongThang(int giaoVienId);
}
