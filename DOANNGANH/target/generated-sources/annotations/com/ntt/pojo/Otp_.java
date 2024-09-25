package com.ntt.pojo;

import com.ntt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-09-25T08:57:26")
@StaticMetamodel(Otp.class)
public class Otp_ { 

    public static volatile SingularAttribute<Otp, Boolean> isUse;
    public static volatile SingularAttribute<Otp, Date> thoiGianHetHan;
    public static volatile SingularAttribute<Otp, String> maOTP;
    public static volatile SingularAttribute<Otp, Integer> id;
    public static volatile SingularAttribute<Otp, User> userId;

}