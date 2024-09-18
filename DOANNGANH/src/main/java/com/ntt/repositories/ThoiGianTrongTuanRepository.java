/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Thoigiantrongtuan;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ThoiGianTrongTuanRepository {

    List<Thoigiantrongtuan> findByKhoaHocId(int khoaHocId);

    void taoBuoiHoc(Thoigiantrongtuan buoihoc);

    Thoigiantrongtuan findById(int buoiHocId);

    List<Thoigiantrongtuan> listLichGiangDayGiaoVien(int giaoVienId);
}
