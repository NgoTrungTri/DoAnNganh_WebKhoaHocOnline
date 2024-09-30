package com.ntt.pojo;

import com.ntt.pojo.Danhmuc;
import com.ntt.pojo.Donhang;
import com.ntt.pojo.KhoahocKhuyenmai;
import com.ntt.pojo.Ngayhocbu;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-30T12:09:03")
@StaticMetamodel(Khoahoc.class)
public class Khoahoc_ { 

    public static volatile SetAttribute<Khoahoc, Donhang> donhangSet;
    public static volatile SingularAttribute<Khoahoc, User> idGVPhuTrach;
    public static volatile SingularAttribute<Khoahoc, String> tenKhoaHoc;
    public static volatile SetAttribute<Khoahoc, KhoahocKhuyenmai> khoahocKhuyenmaiSet;
    public static volatile SingularAttribute<Khoahoc, Date> ngayTao;
    public static volatile SingularAttribute<Khoahoc, Date> ngayKetThuc;
    public static volatile SingularAttribute<Khoahoc, Double> giaTien;
    public static volatile SingularAttribute<Khoahoc, Date> ngayBatDau;
    public static volatile SingularAttribute<Khoahoc, Boolean> trangThai;
    public static volatile SetAttribute<Khoahoc, Thoigiantrongtuan> thoigiantrongtuanSet;
    public static volatile SingularAttribute<Khoahoc, Double> tienLuongPhuTroi;
    public static volatile SingularAttribute<Khoahoc, Danhmuc> danhMucId;
    public static volatile SingularAttribute<Khoahoc, User> idNVTao;
    public static volatile SetAttribute<Khoahoc, Ngayhocbu> ngayhocbuSet;
    public static volatile SingularAttribute<Khoahoc, Integer> id;

}