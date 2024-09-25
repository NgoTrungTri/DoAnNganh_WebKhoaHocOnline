package com.ntt.repositories.impl;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.KhoaHocRepository;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KhoaHocRepositoryImpl implements KhoaHocRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createCourse(Khoahoc khoahoc) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(khoahoc);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error while saving or updating course: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Khoahoc getCourseById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Khoahoc.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Khoahoc> getAllCourses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Khoahoc").list();
    }

    @Override
    public List<Khoahoc> getKhoaHocByDanhMuc(String danhMucName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Khoahoc> query = session.createQuery("FROM Khoahoc kh WHERE kh.danhMucId.tenDanhMuc = :danhMucName", Khoahoc.class);
        query.setParameter("danhMucName", danhMucName);
        return query.getResultList();
    }

    @Override
    public Khoahoc getKhoaHocByBuoiHoc(Thoigiantrongtuan t) {
        Session session = sessionFactory.getCurrentSession();

        // HQL để truy vấn khóa học dựa trên Thoigiantrongtuan
        String hql = "SELECT t.khoaHocId FROM Thoigiantrongtuan t WHERE t.id = :thoigiantrongtuanId";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("thoigiantrongtuanId", t.getId());

        // Lấy kết quả
        Khoahoc khoaHoc = query.uniqueResult();

        return khoaHoc;
    }

    @Override
    public List<Khoahoc> getKhoaHocByGiaoVien(int giaoVienId) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Khoahoc k WHERE k.idGVPhuTrach.id = :giaoVienId";
        Query query = session.createQuery(hql);
        query.setParameter("giaoVienId", giaoVienId);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInMonth(int thang, int year) {
        Session session = sessionFactory.getCurrentSession();

        // Tính toán ngày đầu tháng và ngày cuối tháng
        LocalDate startDate = LocalDate.of(year, thang, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // Chuyển đổi LocalDate thành java.sql.Date
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String hql = "FROM Khoahoc k WHERE k.trangThai = true "
                + "AND k.ngayKetThuc >= :startDate "
                + "AND k.ngayBatDau <= :endDate";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInQuarter(int quy, int year) {
        Session session = sessionFactory.getCurrentSession();

        // Xác định tháng bắt đầu và tháng kết thúc của quý
        int startMonth = (quy - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        ZonedDateTime startDateTime = ZonedDateTime.now()
                .withYear(year)
                .withMonth(startMonth)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = LocalDate.of(year, endMonth, YearMonth.of(year, endMonth).lengthOfMonth());

        // Chuyển đổi LocalDate thành java.sql.Date
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String hql = "FROM Khoahoc k WHERE k.trangThai = true "
                + "AND k.ngayKetThuc >= :startDate "
                + "AND k.ngayBatDau <= :endDate";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

        return query.getResultList();
    }
}
