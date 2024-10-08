/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Videobaidang;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BaiDangRepository {
    // Tìm tất cả bài đăng theo trạng thái
    List<Baidangvanban> findByTrangThai(String trangThai);

    // Tìm tất cả bài đăng đã được duyệt
    List<Baidangvanban> findByNgayDuyetIsNotNull();

    // Tìm tất cả bài đăng theo danh mục
    List<Baidangvanban> findByDanhMuc_Id(int danhMucId);
    
    ////Đăng bài
    public void dangBai(Baidangvanban baiDang, Videobaidang video);
    
    ///Tìm bài theo id
    public Baidangvanban findById(int id);
    
    public void save(Baidangvanban baiDang);
    
   List<Baidangvanban> findByTrangThaiAndUserId(String trangThai,int userId);
   
   void deleteBaiDang(int id);
   
   List<Baidangvanban> getAllBaiDang();
   
   List<Baidangvanban> get5BaiDangGanNhat();
   
   List<Baidangvanban> getAllBaiDangDanhMuc(String tenDanhMuc);
        
}
