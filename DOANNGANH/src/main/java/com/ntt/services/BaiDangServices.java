/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Videobaidang;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BaiDangServices {
    List<Baidangvanban> findByTrangThai(String trangThai);

    List<Baidangvanban> findByNgayDuyetIsNotNull();

    List<Baidangvanban> findByDanhMuc_Id(int danhMucId);
    
    public void dangBai(Baidangvanban baiDang, Videobaidang video);
    
    public Baidangvanban findById(int id);
    
    public void save(Baidangvanban baiDang);
    
    public List<Baidangvanban> findByTrangThaiAndUserId(String trangThai,int userId);
    
    public void deleteBaiDang(int id);
    
    List<Baidangvanban> getAllBaiDang();
   
   List<Baidangvanban> get5BaiDangGanNhat();
   
   List<Baidangvanban> getAllBaiDangDanhMuc(String tenDanhMuc);
}
