/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DELL
 */
public interface UserRepository {
    User getUserById(int id);
    User getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    boolean authUser(String username, String password);
    List<User> getUser();
    void addOrUpdateUser(User u);
    List<User> getUsersByUserRole(String userRole);
}
