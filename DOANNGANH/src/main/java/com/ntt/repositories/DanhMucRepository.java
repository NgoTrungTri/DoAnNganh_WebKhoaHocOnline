/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.Danhmuc;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DanhMucRepository {
    public List<Danhmuc> getAllDanhMuc();
    public Danhmuc getDanhMucById(int id);
}
