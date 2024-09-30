/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Donhang;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DonHangRepository {
     
    List<Donhang> findDonhangsByCurrentDay(int page, int size);

    List<Donhang> findDonhangsByCurrentMonth(int page, int size);

    List<Donhang> findDonhangsByKhoaHocId(int khoaHocId, int page, int size);
    
    List<Donhang> findDonhangsByKhoaHocName(String tenKhoaHoc, int page, int size);
    
    public int countDonhangsByCurrentDay();
    
    public int countDonhangsByCurrentMonth();
    
    public int countDonhangsByKhoaHocName(String tenKhoaHoc);
    
    ////Phương thức mua hàng, tạo đơn hàng 
    public void muaKhoaHoc(Donhang donHang);
    
    public boolean isMuaKhoaHoc(int khoaHocId, int userId);
}
