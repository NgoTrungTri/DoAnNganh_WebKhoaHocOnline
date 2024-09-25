/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Videobaidang;
import com.ntt.repositories.BaiDangRepository;
import com.ntt.services.BaiDangServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class BaiDangServicesImpl implements BaiDangServices{
    
    @Autowired
    private BaiDangRepository baiDang;

    @Override
    public List<Baidangvanban> findByTrangThai(String trangThai) {
        return baiDang.findByTrangThai(trangThai);
    }

    @Override
    public List<Baidangvanban> findByNgayDuyetIsNotNull() {
        return baiDang.findByNgayDuyetIsNotNull();
    }

    @Override
    public List<Baidangvanban> findByDanhMuc_Id(int danhMucId) {
        return baiDang.findByDanhMuc_Id(danhMucId);
    }

    @Override
    public void dangBai(Baidangvanban baiDang, Videobaidang video) {
        this.baiDang.dangBai(baiDang, video);
    }
    
}
