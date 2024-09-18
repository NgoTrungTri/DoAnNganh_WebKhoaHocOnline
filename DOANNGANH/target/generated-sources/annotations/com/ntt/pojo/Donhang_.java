package com.ntt.pojo;

import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-16T05:15:24")
@StaticMetamodel(Donhang.class)
public class Donhang_ { 

    public static volatile SingularAttribute<Donhang, Khoahoc> khoaHocId;
    public static volatile SingularAttribute<Donhang, String> thongTinThanhToan;
    public static volatile SingularAttribute<Donhang, Double> tongTien;
    public static volatile SingularAttribute<Donhang, Integer> id;
    public static volatile SingularAttribute<Donhang, User> userId;
    public static volatile SingularAttribute<Donhang, Date> ngayTao;

}