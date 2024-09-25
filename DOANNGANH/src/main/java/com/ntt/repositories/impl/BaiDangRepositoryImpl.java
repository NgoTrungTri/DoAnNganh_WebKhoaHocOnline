/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Baidangvanban;
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
}
