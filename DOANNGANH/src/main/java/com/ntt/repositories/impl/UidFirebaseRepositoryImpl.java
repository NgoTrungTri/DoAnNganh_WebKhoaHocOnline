/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.UidFirebase;
import com.ntt.repositories.UidFirebaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class UidFirebaseRepositoryImpl implements UidFirebaseRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(UidFirebase uidFirebase) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(uidFirebase);
    }

    @Override
    public boolean existsUidFirebase(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(u) FROM UidFirebase u WHERE u.userId = :userId";
        Long count = (Long) session.createQuery(hql)
                .setParameter("userId", userId)
                .uniqueResult();

        return count != null && count > 0;
    }
}
