/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Baidangvanban;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface BaiDangServices {
    List<Baidangvanban> findByTrangThai(String trangThai);

    List<Baidangvanban> findByNgayDuyetIsNotNull();

    List<Baidangvanban> findByDanhMuc_Id(int danhMucId);
}
