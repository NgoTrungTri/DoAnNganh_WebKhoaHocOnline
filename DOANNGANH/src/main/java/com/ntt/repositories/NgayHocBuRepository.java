/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Ngayhocbu;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface NgayHocBuRepository {
    public void TaoNgayHocBu(Ngayhocbu ngayhocbu);
    public boolean checkNgayHocBuHopLe(Ngayhocbu ngayhocbu, int idGiangVienPhuTrach);
    List<Ngayhocbu> findByKhoaHoc(int khoaHocId);
}
