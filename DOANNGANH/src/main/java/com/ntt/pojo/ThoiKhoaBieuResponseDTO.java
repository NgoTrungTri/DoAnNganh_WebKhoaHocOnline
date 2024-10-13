/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

/**
 *
 * @author DELL
 */
public class ThoiKhoaBieuResponseDTO {
    private String tenKhoaHoc;
    private Thoigiantrongtuan thoiGianTrongTuan;

    public ThoiKhoaBieuResponseDTO(String tenKhoaHoc, Thoigiantrongtuan thoiGianTrongTuan) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.thoiGianTrongTuan = thoiGianTrongTuan;
    }

    // Getters and setters
    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public Thoigiantrongtuan getThoiGianTrongTuan() {
        return thoiGianTrongTuan;
    }

    public void setThoiGianTrongTuan(Thoigiantrongtuan thoiGianTrongTuan) {
        this.thoiGianTrongTuan = thoiGianTrongTuan;
    }
}

