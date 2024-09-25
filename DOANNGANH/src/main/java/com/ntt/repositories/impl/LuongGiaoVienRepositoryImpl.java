/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Luonggiaovien;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.LuongGiaoVienRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
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
public class LuongGiaoVienRepositoryImpl implements LuongGiaoVienRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Luonggiaovien> findByGVCoHuu() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT lg FROM Luonggiaovien lg "
                + "JOIN lg.userId u "
                + "JOIN u.userLoaigiaovienSet ulv "
                + "JOIN ulv.loaiGiaoVienId lgvt "
                + "WHERE lgvt.tenLoai = :loaiGiaoVien";
        Query query = session.createQuery(hql);
        query.setParameter("loaiGiaoVien", "Cơ Hữu"); // Giáo viên cơ hữu
        return query.getResultList();
    }

    @Override
    public List<Luonggiaovien> findByGVThinhGiang() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT lg FROM Luonggiaovien lg "
                + "JOIN lg.userId u "
                + "JOIN u.userLoaigiaovienSet ulv "
                + "JOIN ulv.loaiGiaoVienId lgvt "
                + "WHERE lgvt.tenLoai = :loaiGiaoVien";
        Query query = session.createQuery(hql);
        query.setParameter("loaiGiaoVien", "Thỉnh Giảng"); // Giáo viên thỉnh giảng
        return query.getResultList();
    }

    @Override
    public Luonggiaovien findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Luonggiaovien luonggiaovien = session.get(Luonggiaovien.class, id);

        if (luonggiaovien != null) {
            return luonggiaovien;
        } else {
            throw new EntityNotFoundException("Không tìm thấy giáo viên với id: " + id);
        }
    }

    @Override
    public Luonggiaovien findByGiaoVienId(int idGiaoVien) {
        Session session = sessionFactory.getCurrentSession();
        try {
            String hql = "FROM Luonggiaovien l WHERE l.userId.id = :userId";
            Query<Luonggiaovien> query = session.createQuery(hql, Luonggiaovien.class);
            query.setParameter("userId", idGiaoVien);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isGiaoVienCoHuu(int idGiaoVien) {
        Session session = sessionFactory.getCurrentSession();

        // Lấy ID loại giáo viên "Cơ Hữu"
        String hql = "SELECT l.id FROM Loaigiaovien l WHERE l.tenLoai = :tenLoai";
        Integer coHuuId = session.createQuery(hql, Integer.class)
                .setParameter("tenLoai", "Cơ Hữu")
                .uniqueResult();

        if (coHuuId == null) {
            return false;
        }

        // Kiểm tra xem giáo viên có tồn tại trong bảng UserLoaigiaovien không
        String hqlCheck = "SELECT COUNT(ugl) FROM UserLoaigiaovien ugl WHERE ugl.userId.id = :idGiaoVien AND ugl.loaiGiaoVienId.id = :loaiGiaoVienId";
        Long count = session.createQuery(hqlCheck, Long.class)
                .setParameter("idGiaoVien", idGiaoVien)
                .setParameter("loaiGiaoVienId", coHuuId)
                .uniqueResult();

        return count != null && count > 0;
    }

    @Override
    public void update(Luonggiaovien lngvn) {
        Session session = sessionFactory.getCurrentSession();
        session.update(lngvn);
    }

    @Override
    public int countGioDayTrongThang(int giaoVienId) {
        Session session = sessionFactory.getCurrentSession();
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);

        String hql = "SELECT t FROM Thoigiantrongtuan t "
                + "JOIN t.khoaHocId k "
                + "WHERE k.idGVPhuTrach.id = :giaoVienId "
                + "AND t.ngayHoc BETWEEN :startDate AND :endDate";

        List<Thoigiantrongtuan> list = session.createQuery(hql, Thoigiantrongtuan.class)
                .setParameter("giaoVienId", giaoVienId)
                .setParameter("startDate", java.sql.Date.valueOf(firstDayOfMonth))
                .setParameter("endDate", java.sql.Date.valueOf(today))
                .getResultList();

        // Tính tổng giờ 
        double totalHour = 0;
        for (Thoigiantrongtuan t : list) {
            totalHour += t.getThoiLuong();
        }

        return (int) totalHour;
    }

}
