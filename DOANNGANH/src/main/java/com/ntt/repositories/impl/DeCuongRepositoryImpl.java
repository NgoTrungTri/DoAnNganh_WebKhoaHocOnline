/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.KhoahocDecuong;
import com.ntt.repositories.DeCuongRepository;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class DeCuongRepositoryImpl implements DeCuongRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void createDeCuong(KhoahocDecuong dc) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.saveOrUpdate(dc);
    }

    @Override
    public List<KhoahocDecuong> getDeCuongByKhoaHocId(int idKhoaHoc) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        String hql = "FROM KhoahocDecuong kd WHERE kd.khoaHocId.id = :idKhoaHoc";
        Query<KhoahocDecuong> query = s.createQuery(hql, KhoahocDecuong.class);
        query.setParameter("idKhoaHoc", idKhoaHoc);
        return query.getResultList();
    }

}
