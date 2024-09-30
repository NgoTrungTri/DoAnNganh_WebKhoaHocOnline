/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.User;
import com.ntt.pojo.Videobaidang;
import com.ntt.repositories.BaiDangRepository;
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
public class BaiDangRepositoryImpl implements BaiDangRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Baidangvanban> findByTrangThai(String trangThai) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban b WHERE b.trangThai = :trangThai";
        Query query = session.createQuery(hql);
        query.setParameter("trangThai", trangThai);
        return query.list();
    }

    @Override
    public List<Baidangvanban> findByNgayDuyetIsNotNull() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban b WHERE b.ngayDuyet IS NOT NULL";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List<Baidangvanban> findByDanhMuc_Id(int danhMucId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban b WHERE b.danhMucId.id = :danhMucId";
        Query query = session.createQuery(hql);
        query.setParameter("danhMucId", danhMucId);
        return query.list();
    }

    @Override
    public void dangBai(Baidangvanban baiDang, Videobaidang video) {
        // Get the current Hibernate session
        Session session = sessionFactory.getObject().getCurrentSession();

        try {
            session.save(baiDang);

            if (video != null) {
                video.setBaiDangVanBanId(baiDang);
                session.save(video);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Baidangvanban findById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(Baidangvanban.class, id);
    }

    @Override
    public void save(Baidangvanban baiDang) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(baiDang);
    }

    @Override
    public List<Baidangvanban> findByTrangThaiAndUserId(String trangThai, int userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban b WHERE b.trangThai = :trangThai AND b.idGVDang.id = :userId";
        Query<Baidangvanban> query = session.createQuery(hql, Baidangvanban.class);
        query.setParameter("trangThai", trangThai);
        query.setParameter("userId", userId);
        return query.getResultList(); // Sử dụng getResultList() thay vì list()
    }

    @Override
    public void deleteBaiDang(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Baidangvanban baiDang = this.findById(id);
        if (baiDang != null) {
            session.delete(baiDang);
        }
    }

    @Override
    public List<Baidangvanban> getAllBaiDang() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban WHERE trangThai = :status DESC";
        Query query = session.createQuery(hql);
        query.setParameter("status", "DA_DUYET");
        return query.list();
    }

    @Override
    public List<Baidangvanban> get5BaiDangGanNhat() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban WHERE trangThai = :status ORDER BY ngayDang DESC";
        Query query = session.createQuery(hql);
        query.setParameter("status", "DA_DUYET");  
        query.setMaxResults(5); 
        return query.list();
    }

    @Override
    public List<Baidangvanban> getAllBaiDangDanhMuc(String tenDanhMuc) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String hql = "FROM Baidangvanban b WHERE b.danhMucId.tenDanhMuc = :tenDanhMuc";
        Query query = session.createQuery(hql);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        return query.list();
    }
}
