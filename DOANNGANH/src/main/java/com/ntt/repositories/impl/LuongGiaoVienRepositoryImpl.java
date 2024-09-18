/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Luonggiaovien;
import com.ntt.repositories.LuongGiaoVienRepository;
import java.util.List;
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
    public void update(Luonggiaovien lngvn) {
        Session session = sessionFactory.getCurrentSession();
        session.update(lngvn);
    }

}
