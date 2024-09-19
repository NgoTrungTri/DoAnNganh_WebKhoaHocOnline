package com.ntt.pojo;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Binhluan;
import com.ntt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-20T03:21:55")
@StaticMetamodel(Binhluan.class)
public class Binhluan_ { 

    public static volatile SetAttribute<Binhluan, Binhluan> binhluanSet;
    public static volatile SingularAttribute<Binhluan, Binhluan> parentBinhLuanId;
    public static volatile SingularAttribute<Binhluan, Integer> id;
    public static volatile SingularAttribute<Binhluan, Date> ngayBinhLuan;
    public static volatile SingularAttribute<Binhluan, Baidangvanban> baiDangVanBanId;
    public static volatile SingularAttribute<Binhluan, String> noiDung;
    public static volatile SingularAttribute<Binhluan, User> userId;

}