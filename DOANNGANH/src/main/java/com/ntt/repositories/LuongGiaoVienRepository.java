/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Luonggiaovien;
import java.util.List;
import java.util.Map;

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
    
    ////Bảng lương
    public int countGioDayTrongThang(int giaoVienId);
}
