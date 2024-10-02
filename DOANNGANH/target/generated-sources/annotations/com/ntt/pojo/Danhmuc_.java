package com.ntt.pojo;

import com.ntt.pojo.Baidangvanban;
import com.ntt.pojo.Khoahoc;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-10-02T09:57:04")
@StaticMetamodel(Danhmuc.class)
public class Danhmuc_ { 

    public static volatile SingularAttribute<Danhmuc, String> tenDanhMuc;
    public static volatile SetAttribute<Danhmuc, Khoahoc> khoahocSet;
    public static volatile SetAttribute<Danhmuc, Baidangvanban> baidangvanbanSet;
    public static volatile SingularAttribute<Danhmuc, Integer> id;

}