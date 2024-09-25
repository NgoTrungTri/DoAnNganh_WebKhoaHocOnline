/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Donhang;
import com.ntt.repositories.DonHangRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class DonHangRepositoryImpl implements DonHangRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getObject().getCurrentSession();
    }

    // Lấy đơn hàng theo ngày hiện tại
    @Override
    public List<Donhang> findDonhangsByCurrentDay(int page, int size) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endOfDay = calendar.getTime();

        Session session = getCurrentSession();
        Query<Donhang> query = session.createQuery("FROM Donhang d WHERE d.ngayTao BETWEEN :startOfDay AND :endOfDay", Donhang.class);
        query.setParameter("startOfDay", startOfDay);
        query.setParameter("endOfDay", endOfDay);

        int offset = page * size;
        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Donhang> findDonhangsByCurrentMonth(int page, int size) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Đặt ngày đầu tháng
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfMonth = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date endOfMonth = calendar.getTime();

        Session session = getCurrentSession();
        Query<Donhang> query = session.createQuery("FROM Donhang d WHERE d.ngayTao BETWEEN :startOfMonth AND :endOfMonth", Donhang.class);
        query.setParameter("startOfMonth", startOfMonth);
        query.setParameter("endOfMonth", endOfMonth);

        int offset = page * size;
        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Donhang> findDonhangsByKhoaHocId(int khoaHocId, int page, int size) {
        Session session = getCurrentSession();
        Query<Donhang> query = session.createQuery("FROM Donhang d WHERE d.khoaHocId.id = :khoaHocId", Donhang.class);
        query.setParameter("khoaHocId", khoaHocId);

        int offset = page * size;
        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Donhang> findDonhangsByKhoaHocName(String tenKhoaHoc, int page, int size) {
        Session session = getCurrentSession();

        // Sử dụng toán tử LIKE để tìm các khóa học có tên chứa từ khóa
        String hql = "FROM Donhang d WHERE d.khoaHocId.tenKhoaHoc LIKE :tenKhoaHoc";
        Query<Donhang> query = session.createQuery(hql, Donhang.class);
        query.setParameter("tenKhoaHoc", "%" + tenKhoaHoc + "%"); // Thêm % để tìm kiếm theo từ khóa

        // Thực hiện phân trang
        int offset = page * size;
        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public int countDonhangsByCurrentDay() {
        String hql = "SELECT COUNT(d) FROM Donhang d WHERE DATE(d.ngayTao) = CURRENT_DATE";
        Query<Long> query = getCurrentSession().createQuery(hql, Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public int countDonhangsByCurrentMonth() {
        String hql = "SELECT COUNT(d) FROM Donhang d WHERE MONTH(d.ngayTao) = MONTH(CURRENT_DATE) AND YEAR(d.ngayTao) = YEAR(CURRENT_DATE)";
        Query<Long> query = getCurrentSession().createQuery(hql, Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public int countDonhangsByKhoaHocName(String tenKhoaHoc) {
        String hql = "SELECT COUNT(d) FROM Donhang d WHERE d.khoaHocId.tenKhoaHoc LIKE :tenKhoaHoc";
        Query<Long> query = getCurrentSession().createQuery(hql, Long.class);
        query.setParameter("tenKhoaHoc", tenKhoaHoc);
        return query.uniqueResult().intValue();
    }
}
