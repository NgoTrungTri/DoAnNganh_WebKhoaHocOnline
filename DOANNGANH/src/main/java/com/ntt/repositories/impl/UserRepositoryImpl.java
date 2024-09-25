/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.User;
import com.ntt.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getUsersByUserRole(String userRole) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.userRole = :userRole", User.class);
        query.setParameter("userRole", userRole);
        return query.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        List<User> users = q.getResultList();
        if (users.isEmpty()) {
            return null;    
        }

        return users.get(0); 
    }

    @Override
    public List<User> getUser() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("User.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdateUser(User u) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        s.saveOrUpdate(u);
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query<User> q = s.createQuery("FROM User WHERE email = :email", User.class);
        q.setParameter("email", email);

        try {
            User user = q.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Log the exception or handle it according to your needs
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
