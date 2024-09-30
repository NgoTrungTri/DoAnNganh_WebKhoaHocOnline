/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

/**
 *
 * @author DELL
 */
public interface VNPayRepository {
    public String createPaymentUrl(String orderId, int amount, String ipAddr);
}
