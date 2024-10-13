/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.UidFirebase;

/**
 *
 * @author DELL
 */
public interface UidFirebaseRepository {
    public void create(UidFirebase uidFirebase);
    public boolean existsUidFirebase(int userId);
}
