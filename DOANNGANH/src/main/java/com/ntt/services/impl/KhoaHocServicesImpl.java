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

    @Override
    public List<Khoahoc> getKhoaHocByGiaoVien(int giaoVienId) {
        return khoaHocRepository.getKhoaHocByGiaoVien(giaoVienId);
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInMonth(int month, int year) {
        return khoaHocRepository.getKhoaHocActiveInMonth(month, year);
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInQuarter(int i, int i1) {
        return khoaHocRepository.getKhoaHocActiveInQuarter(i, i1);
    }

    @Override
    public List<Khoahoc> loadKhoaHocPhanTrang(String string, int i, int i1) {
        return khoaHocRepository.loadKhoaHocPhanTrang(string, i, i1);
    }

    @Override
    public List<Khoahoc> load4KhoaHocMoiNhat() {
        return khoaHocRepository.load4KhoaHocMoiNhat();
    }

    @Override
    public List<Khoahoc> getKhoaHocHocVien(int i) {
        return khoaHocRepository.getKhoaHocHocVien(i);
    }

    @Override
    public List<Khoahoc> getKhoaHocDangHoc(int i) {
        return khoaHocRepository.getKhoaHocDangHoc(i);
    }

    @Override
    public List<Khoahoc> getKhoaHocDaMua(int i) {
        return khoaHocRepository.getKhoaHocDaMua(i);
    }

    @Override
    public List<Khoahoc> getKhoaHocSapToi(int i) {
        return khoaHocRepository.getKhoaHocSapToi(i);
    }
       
}
