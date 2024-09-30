package com.ntt.pojo;

import com.ntt.pojo.Chucvu;
import com.ntt.pojo.UserLoaigiaovien;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-30T12:09:03")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> ho;
    public static volatile SingularAttribute<User, String> gioiTinh;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, Date> ngayTao;
    public static volatile SingularAttribute<User, String> queQuan;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Chucvu> chucVuId;
    public static volatile SetAttribute<User, UserLoaigiaovien> userLoaigiaovienSet;
    public static volatile SingularAttribute<User, Date> ngaySinh;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> ten;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}