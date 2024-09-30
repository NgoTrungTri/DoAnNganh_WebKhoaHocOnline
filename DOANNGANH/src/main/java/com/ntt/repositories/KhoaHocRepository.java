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

    List<Khoahoc> getKhoaHocByGiaoVien(int giaoVienId);

    public List<Khoahoc> getKhoaHocActiveInMonth(int thang, int year);

    List<Khoahoc> getKhoaHocActiveInQuarter(int quy, int nam);

    public List<Khoahoc> loadKhoaHocPhanTrang(String tenDanhMuc, int page, int size);

    public List<Khoahoc> load4KhoaHocMoiNhat();

    public List<Khoahoc> getKhoaHocHocVien(int hocVienId);

    public List<Khoahoc> getKhoaHocDangHoc(int hocVienId);

    public List<Khoahoc> getKhoaHocDaMua(int hocVienId);
    
    public List<Khoahoc> getKhoaHocSapToi(int hocVienId);

}
