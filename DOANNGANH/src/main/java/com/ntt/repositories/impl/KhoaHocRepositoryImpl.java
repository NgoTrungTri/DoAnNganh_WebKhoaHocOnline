package com.ntt.repositories.impl;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.KhoaHocRepository;
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
}
