/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.ThoiGianTrongTuanRepository;
import java.sql.Time;
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
public class ThoiGianTrongTuanRepositoryImpl implements ThoiGianTrongTuanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Thoigiantrongtuan> findByKhoaHocId(int khoaHocId) {
        Session session = sessionFactory.getCurrentSession();

        // HQL query để lấy danh sách ThoiGianTrongTuan dựa trên khoaHocId
        String hql = "FROM Thoigiantrongtuan t WHERE t.khoaHocId.id = :khoaHocId";
        Query query = session.createQuery(hql);
        query.setParameter("khoaHocId", khoaHocId);

        return query.getResultList();
    }

    @Override
    public void taoBuoiHoc(Thoigiantrongtuan t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
    }

    @Override
    public Thoigiantrongtuan findById(int buoiHocId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Thoigiantrongtuan.class, buoiHocId);
    }

    public List<Thoigiantrongtuan> listLichGiangDayGiaoVien(int giaoVienId) {
        Session session = sessionFactory.getCurrentSession();

        // Câu lệnh HQL để lấy danh sách lịch giảng dạy của giáo viên dựa trên idGVPhuTrach
        String hql = "SELECT t FROM Thoigiantrongtuan t "
                + "JOIN t.khoaHocId k "
                + "WHERE k.idGVPhuTrach.id = :giaoVienId";  // Lấy giảng viên phụ trách từ bảng Khoahoc

        // Thực thi truy vấn và trả về danh sách
        Query<Thoigiantrongtuan> query = session.createQuery(hql, Thoigiantrongtuan.class);
        query.setParameter("giaoVienId", giaoVienId);

        return query.getResultList();
    }

    @Override
    public boolean checkTrungLich(Date ngayHoc, Date thoiGianBatDau, Date thoiGianKetThuc) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT COUNT(t) FROM Thoigiantrongtuan t WHERE t.ngayHoc = :ngayHoc AND t.thoiGianBatDau = :thoiGianBatDau AND t.thoiGianKetThuc = :thoiGianKetThuc";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("ngayHoc", ngayHoc);
        query.setParameter("thoiGianBatDau", thoiGianBatDau);
        query.setParameter("thoiGianKetThuc", thoiGianKetThuc);

        long count = query.uniqueResult();

        return count > 0;
    }

}
