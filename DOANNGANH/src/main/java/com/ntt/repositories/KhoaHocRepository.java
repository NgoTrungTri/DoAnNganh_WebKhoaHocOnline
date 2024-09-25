/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Khoahoc;
import java.util.List;
import com.ntt.pojo.Thoigiantrongtuan;

/**
 *
 * @author DELL
 */
public interface KhoaHocRepository {

    void createCourse(Khoahoc khoahoc);

    Khoahoc getCourseById(Integer id);

    List<Khoahoc> getAllCourses();

    List<Khoahoc> getKhoaHocByDanhMuc(String Danhmuc);

    Khoahoc getKhoaHocByBuoiHoc(Thoigiantrongtuan buoiHoc);
    
    List<Khoahoc> getKhoaHocByGiaoVien (int giaoVienId); 
    
    public List<Khoahoc> getKhoaHocActiveInMonth(int thang, int year);
    
    List<Khoahoc> getKhoaHocActiveInQuarter(int quy, int nam);
}
