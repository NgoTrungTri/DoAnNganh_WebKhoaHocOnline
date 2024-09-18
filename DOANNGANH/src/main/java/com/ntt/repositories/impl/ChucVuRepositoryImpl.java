/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Chucvu;
import com.ntt.repositories.ChucVuRepository;
import java.util.List;
import org.hibernate.Session;
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
public class ChucVuRepositoryImpl implements ChucVuRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Chucvu> getAllChucVu() {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("FROM Chucvu", Chucvu.class).getResultList();
    }

}
