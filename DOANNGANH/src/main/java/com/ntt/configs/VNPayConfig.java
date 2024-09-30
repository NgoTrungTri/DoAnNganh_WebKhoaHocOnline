/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.configs;

//import java.security.MessageDigest;

/**
 *
 * @author DELL
 */
public class VNPayConfig {
    public static String vnp_TmnCode = "CQLBZ0FI"; // Mã website của bạn
    public static String vnp_HashSecret = "9BNVDSRH2LTL30NHN1QKK0XMPZIH5TH0"; 
    public static String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; 

//    public static String sha256(String base) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(base.getBytes("UTF-8"));
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
}
