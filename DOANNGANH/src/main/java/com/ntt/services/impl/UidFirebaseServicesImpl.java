/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.pojo.UidFirebase;
import com.ntt.repositories.UidFirebaseRepository;
import com.ntt.services.UidFirebaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class UidFirebaseServicesImpl implements UidFirebaseServices{
    @Autowired 
    private UidFirebaseRepository uid;

    @Override
    public void create(UidFirebase uidFirebase) {
        uid.create(uidFirebase);
    }

    @Override
    public boolean existsUidFirebase(int i) {
        return this.uid.existsUidFirebase(i);
    }
    
}
