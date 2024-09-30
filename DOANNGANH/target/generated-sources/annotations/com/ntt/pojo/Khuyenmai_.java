package com.ntt.pojo;

import com.ntt.pojo.KhoahocKhuyenmai;
import com.ntt.pojo.KhuyenmaiUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-30T12:09:03")
@StaticMetamodel(Khuyenmai.class)
public class Khuyenmai_ { 

    public static volatile SingularAttribute<Khuyenmai, Date> ngayBatDau;
    public static volatile SingularAttribute<Khuyenmai, Double> giaTri;
    public static volatile SingularAttribute<Khuyenmai, String> tenKhuyenMai;
    public static volatile SingularAttribute<Khuyenmai, Integer> id;
    public static volatile SetAttribute<Khuyenmai, KhoahocKhuyenmai> khoahocKhuyenmaiSet;
    public static volatile SingularAttribute<Khuyenmai, Date> ngayKetThuc;
    public static volatile SetAttribute<Khuyenmai, KhuyenmaiUser> khuyenmaiUserSet;

}