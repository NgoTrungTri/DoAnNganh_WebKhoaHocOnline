/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.KhoahocDecuong;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DeCuongServices {
    void createDeCuong(KhoahocDecuong dc);
    public List<KhoahocDecuong> getDeCuongByKhoaHocId(int idKhoaHoc);
}
