/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Thoigiantrongtuan;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ThoiGianTrongTuanServices {
    List<Thoigiantrongtuan> findByKhoaHocId(int khoaHocId);
    void taoBuoiHoc(Thoigiantrongtuan t);
    public Thoigiantrongtuan findById(int buoiHocId);
    public List<Thoigiantrongtuan> listLichGiangDayGiaoVien(int giaoVienId);
}
