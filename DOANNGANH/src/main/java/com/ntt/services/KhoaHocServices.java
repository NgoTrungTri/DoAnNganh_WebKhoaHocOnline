/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Thoigiantrongtuan;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface KhoaHocServices {
    void createCourse(Khoahoc khoahoc);
    Khoahoc getCourseById(Integer id);
    List<Khoahoc> getAllCourses();
    List<Khoahoc> getKhoaHocByDanhMuc(String Danhmuc);
    public Khoahoc getKhoaHocByBuoiHoc(Thoigiantrongtuan t);
}
