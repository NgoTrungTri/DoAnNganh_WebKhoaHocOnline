/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services.impl;

import com.ntt.repositories.VNPayRepository;
import com.ntt.services.VNPayServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class VNPayServicesImpl implements VNPayServices{
    @Autowired
    private VNPayRepository VNPayRepo;

    @Override
    public String createPaymentUrl(String orderId, int amount, String ipAddr) {
        return this.VNPayRepo.createPaymentUrl(orderId, amount, ipAddr);
    }
    
    
}
