/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.services;

import com.ntt.pojo.Ngayhocbu;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface NgayHocBuServices {
    public void TaoNgayHocBu(Ngayhocbu ngayhocbu);
    public boolean checkNgayHocBuHopLe(Ngayhocbu ngayhocbu, int idGiangVienPhuTrach);
    public List<Ngayhocbu> findByKhoaHoc(int khoaHocId);
}
