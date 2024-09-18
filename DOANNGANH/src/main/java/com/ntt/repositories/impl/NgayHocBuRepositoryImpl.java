/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Ngayhocbu;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.NgayHocBuRepository;
import com.ntt.services.ThoiGianTrongTuanServices;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class NgayHocBuRepositoryImpl implements NgayHocBuRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ThoiGianTrongTuanServices thoiGianTrongTuanService;

    @Override
    public void TaoNgayHocBu(Ngayhocbu ngayhocbu) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(ngayhocbu);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean checkNgayHocBuHopLe(Ngayhocbu ngayhocbu, int idGiangVienPhuTrach) {
        // Lấy danh sách các buổi học của giảng viên phụ trách
        List<Thoigiantrongtuan> lichGiangDayList = thoiGianTrongTuanService.listLichGiangDayGiaoVien(idGiangVienPhuTrach);

        // Duyệt qua danh sách và kiểm tra thời gian trùng lặp
        for (Thoigiantrongtuan buoiGiangDay : lichGiangDayList) {
            // So sánh ngày học bù có trùng ngày không (chỉ so sánh phần ngày, bỏ qua giờ)
            if (isSameDay(buoiGiangDay.getNgayHoc(), ngayhocbu.getNgayHocBu())) {
                // Kiểm tra thời gian trùng lặp
                if (buoiGiangDay.getThoiGianBatDau().before(ngayhocbu.getThoiGianKetThuc())
                        && buoiGiangDay.getThoiGianKetThuc().after(ngayhocbu.getThoiGianBatDau())) {
                    return true; // Trùng lịch giảng dạy của giảng viên
                }
            }
        }
        return false;
    }

// Hàm phụ để so sánh hai ngày (bỏ qua giờ)
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public List<Ngayhocbu> findByKhoaHoc(int khoaHocId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "From Ngayhocbu n WHERE n.khoaHocId.id =: khoaHocId";
        Query<Ngayhocbu> query = session.createQuery(hql, Ngayhocbu.class);
        query.setParameter("khoaHocId", khoaHocId);
        return query.getResultList();       
    }
}
