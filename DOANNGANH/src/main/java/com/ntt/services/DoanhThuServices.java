/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

/**
 *
 * @author DELL
 */
public interface DoanhThuServices {
    public double doanhThuKhoaHocThang(int idKhoaHoc, int thang, int nam);
    public double doanhThuKhoaHocQuy(int idKhoaHoc, int quy, int nam);
    public double doanhThuCuaThang(int thang, int nam);
}
