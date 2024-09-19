package com.ntt.pojo;

import com.ntt.pojo.Binhluan;
import com.ntt.pojo.Danhmuc;
import com.ntt.pojo.Thich;
import com.ntt.pojo.User;
import com.ntt.pojo.Videobaidang;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-20T03:21:55")
@StaticMetamodel(Baidangvanban.class)
public class Baidangvanban_ { 

    public static volatile SetAttribute<Baidangvanban, Thich> thichSet;
    public static volatile SingularAttribute<Baidangvanban, Date> ngayDuyet;
    public static volatile SingularAttribute<Baidangvanban, String> noiDung;
    public static volatile SingularAttribute<Baidangvanban, User> idGVDang;
    public static volatile SingularAttribute<Baidangvanban, String> tieuDe;
    public static volatile SetAttribute<Baidangvanban, Binhluan> binhluanSet;
    public static volatile SingularAttribute<Baidangvanban, Date> ngayDang;
    public static volatile SingularAttribute<Baidangvanban, String> trangThai;
    public static volatile SingularAttribute<Baidangvanban, String> noiDungPhanHoi;
    public static volatile SingularAttribute<Baidangvanban, User> idNVDuyet;
    public static volatile SingularAttribute<Baidangvanban, Danhmuc> danhMucId;
    public static volatile SingularAttribute<Baidangvanban, Integer> id;
    public static volatile SetAttribute<Baidangvanban, Videobaidang> videobaidangSet;

}