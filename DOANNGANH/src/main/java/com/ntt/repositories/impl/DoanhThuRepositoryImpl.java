/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repositories.impl;

import com.ntt.pojo.Khoahoc;
import com.ntt.repositories.DoanhThuRepository;
import com.ntt.services.KhoaHocServices;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class DoanhThuRepositoryImpl implements DoanhThuRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    KhoaHocServices khoaHocServices;

    @Override
    public double doanhThuKhoaHocThang(int idKhoaHoc, int thang, int nam) {
        Session session = sessionFactory.getObject().getCurrentSession();

        // Thêm điều kiện để kiểm tra tháng và năm của cột 'ngayTao'
        String hql = "SELECT SUM(d.tongTien) FROM Donhang d WHERE d.khoaHocId.id = :idKhoaHoc "
                + "AND MONTH(d.ngayTao) = :thang AND YEAR(d.ngayTao) = :nam";

        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("idKhoaHoc", idKhoaHoc);
        query.setParameter("thang", thang);
        query.setParameter("nam", nam);

        // Lấy kết quả tổng doanh thu
        Double doanhThu = query.uniqueResult();

        return doanhThu != null ? doanhThu : 0;
    }

    @Override
    public double doanhThuKhoaHocQuy(int idKhoaHoc, int quy, int nam) {
        Session session = sessionFactory.getObject().getCurrentSession();
        int startMonth = (quy - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        // Câu lệnh HQL để tính tổng doanh thu trong quý
        String hql = "SELECT SUM(d.tongTien) FROM Donhang d WHERE d.khoaHocId.id = :idKhoaHoc "
                + "AND MONTH(d.ngayTao) BETWEEN :startMonth AND :endMonth "
                + "AND YEAR(d.ngayTao) = :nam";

        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("idKhoaHoc", idKhoaHoc);
        query.setParameter("startMonth", startMonth);
        query.setParameter("endMonth", endMonth);
        query.setParameter("nam", nam);

        // Lấy kết quả tổng doanh thu
        Double doanhThu = query.uniqueResult();

        return doanhThu != null ? doanhThu : 0;
    }

    @Override
    public double doanhThuCuaThang(int thang, int nam) {
        Session session = sessionFactory.getObject().getCurrentSession();

        // Xác định ngày đầu tiên và ngày cuối cùng của tháng
        LocalDate startDate = LocalDate.of(nam, thang, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // Chuyển đổi LocalDate thành java.sql.Date
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        // Truy vấn HQL để tính tổng doanh thu trong tháng
        String hql = "SELECT SUM(d.tongTien) FROM Donhang d WHERE d.ngayTao BETWEEN :startDate AND :endDate";

        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

        // Lấy kết quả, nếu không có đơn hàng thì trả về 0
        Double doanhThu = query.uniqueResult();
        return doanhThu != null ? doanhThu : 0.0;
    }

}
