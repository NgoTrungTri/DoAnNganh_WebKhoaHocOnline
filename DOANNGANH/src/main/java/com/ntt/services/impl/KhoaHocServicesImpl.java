package com.ntt.services.impl;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.KhoaHocRepository;
import com.ntt.services.KhoaHocServices;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhoaHocServicesImpl implements KhoaHocServices {

    @Autowired
    private KhoaHocRepository khoaHocRepository;

    @Override
    public void createCourse(Khoahoc khoahoc) {
        khoahoc.setNgayTao(new Date());
        khoahoc.setTrangThai(true);
        khoaHocRepository.createCourse(khoahoc);
    }

    @Override
    public Khoahoc getCourseById(Integer id) {
        return khoaHocRepository.getCourseById(id);
    }

    @Override
    public List<Khoahoc> getAllCourses() {
        return khoaHocRepository.getAllCourses();
    }

    @Override
    public List<Khoahoc> getKhoaHocByDanhMuc(String string) {
        return khoaHocRepository.getKhoaHocByDanhMuc(string);
    }

    @Override
    public Khoahoc getKhoaHocByBuoiHoc(Thoigiantrongtuan t) {
        return khoaHocRepository.getKhoaHocByBuoiHoc(t);
    }
}
