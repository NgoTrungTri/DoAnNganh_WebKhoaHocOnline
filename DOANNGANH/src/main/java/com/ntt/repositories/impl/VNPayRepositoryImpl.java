/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.repositories.VNPayRepository;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class VNPayRepositoryImpl implements VNPayRepository {

    @Override
    public String createPaymentUrl(String orderId, int amount, String ipAddr) {
        String vnp_TmnCode = "CQLBZ0FI";
        String vnp_HashSecret = "9BNVDSRH2LTL30NHN1QKK0XMPZIH5TH0";
        String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderType = "other"; // Thay đổi theo loại đơn hàng
        String vnp_Locale = "vn";
        String vnp_CurrCode = "VND";
        String vnp_ReturnUrl = "http://localhost:3000/paymentsuccess";

        // Lấy thời gian hiện tại
        TimeZone tz = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(tz);
        String vnp_CreateDate = sdf.format(new Date());

        // Số tiền cần thanh toán
        String vnp_Amount = String.valueOf(amount * 100); // Số tiền phải nhân với 100

        // Tạo một Map để lưu các tham số
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Amount", vnp_Amount);
        params.put("vnp_Command", vnp_Command);
        params.put("vnp_CreateDate", vnp_CreateDate);
        params.put("vnp_CurrCode", vnp_CurrCode);
        //        params.put("vnp_ExpireDate", vnp_ExpireDate);
        params.put("vnp_IpAddr", ipAddr);
        params.put("vnp_Locale", vnp_Locale);
        params.put("vnp_OrderInfo", "ThanhToan");
        params.put("vnp_OrderType", vnp_OrderType);
        try {
            params.put("vnp_ReturnUrl", URLEncoder.encode(vnp_ReturnUrl, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VNPayRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        params.put("vnp_TmnCode", vnp_TmnCode);
        params.put("vnp_TxnRef", orderId);
        params.put("vnp_Version", vnp_Version);

        // Tạo checksum (SHA256 hoặc HMACSHA512)
        StringBuilder hashData = new StringBuilder();
        List<Map.Entry<String, String>> entries = new ArrayList<>(params.entrySet());

        // Sắp xếp theo key
        Collections.sort(entries, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (Map.Entry<String, String> entry : entries) {
            if (hashData.length() > 0) {
                hashData.append('&');
            }
            hashData.append(entry.getKey());
            hashData.append('=');
            hashData.append(entry.getValue());
        }

        String queryString = hashData.toString();
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, queryString);

        // Ghi log URL chưa có secure hash
        System.out.println("VNPAY_PAYMENT_URL_BEFORE_HASH" + vnp_Url + "?" + queryString);

        // Tạo URL hoàn chỉnh
        String paymentUrl = vnp_Url + "?" + queryString + "&vnp_SecureHash=" + vnp_SecureHash;

        // Ghi log URL sau khi có secure hash
        System.out.println("VNPAY_PAYMENT_URL" + paymentUrl);

        return paymentUrl;

    }

    private String hmacSHA512(String key, String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : rawHmac) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC SHA-512", e);
        }
    }

}
