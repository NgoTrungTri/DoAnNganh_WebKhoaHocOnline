    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories;

import com.ntt.pojo.KhoahocDecuong;
import java.util.List;
/**
 *
 * @author DELL
 */
public interface DeCuongRepository {
    void createDeCuong(KhoahocDecuong dc);
    List<KhoahocDecuong> getDeCuongByKhoaHocId(int idKhoaHoc);
}
