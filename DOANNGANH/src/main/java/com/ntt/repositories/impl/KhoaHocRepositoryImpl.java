package com.ntt.repositories.impl;

import com.ntt.pojo.Donhang;
import com.ntt.pojo.Khoahoc;
import com.ntt.pojo.Thoigiantrongtuan;
import com.ntt.repositories.KhoaHocRepository;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KhoaHocRepositoryImpl implements KhoaHocRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createCourse(Khoahoc khoahoc) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(khoahoc);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error while saving or updating course: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Khoahoc getCourseById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Khoahoc.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Khoahoc> getAllCourses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Khoahoc").list();
    }

    @Override
    public List<Khoahoc> getKhoaHocByDanhMuc(String danhMucName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Khoahoc> query = session.createQuery("FROM Khoahoc kh WHERE kh.danhMucId.tenDanhMuc = :danhMucName", Khoahoc.class);
        query.setParameter("danhMucName", danhMucName);
        return query.getResultList();
    }

    @Override
    public Khoahoc getKhoaHocByBuoiHoc(Thoigiantrongtuan t) {
        Session session = sessionFactory.getCurrentSession();

        // HQL để truy vấn khóa học dựa trên Thoigiantrongtuan
        String hql = "SELECT t.khoaHocId FROM Thoigiantrongtuan t WHERE t.id = :thoigiantrongtuanId";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("thoigiantrongtuanId", t.getId());

        // Lấy kết quả
        Khoahoc khoaHoc = query.uniqueResult();

        return khoaHoc;
    }

    @Override
    public List<Khoahoc> getKhoaHocByGiaoVien(int giaoVienId) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Khoahoc k WHERE k.idGVPhuTrach.id = :giaoVienId";
        Query query = session.createQuery(hql);
        query.setParameter("giaoVienId", giaoVienId);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInMonth(int thang, int year) {
        Session session = sessionFactory.getCurrentSession();

        // Tính toán ngày đầu tháng và ngày cuối tháng
        LocalDate startDate = LocalDate.of(year, thang, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // Chuyển đổi LocalDate thành java.sql.Date
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String hql = "FROM Khoahoc k WHERE k.trangThai = true "
                + "AND k.ngayKetThuc >= :startDate "
                + "AND k.ngayBatDau <= :endDate";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> getKhoaHocActiveInQuarter(int quy, int year) {
        Session session = sessionFactory.getCurrentSession();

        // Xác định tháng bắt đầu và tháng kết thúc của quý
        int startMonth = (quy - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        ZonedDateTime startDateTime = ZonedDateTime.now()
                .withYear(year)
                .withMonth(startMonth)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = LocalDate.of(year, endMonth, YearMonth.of(year, endMonth).lengthOfMonth());

        // Chuyển đổi LocalDate thành java.sql.Date
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String hql = "FROM Khoahoc k WHERE k.trangThai = true "
                + "AND k.ngayKetThuc >= :startDate "
                + "AND k.ngayBatDau <= :endDate";

        Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> loadKhoaHocPhanTrang(String tenDanhMuc, int page, int size) {
        Session session = sessionFactory.getCurrentSession();

        if (page < 0) {
            page = 0;
        }

        Query<Khoahoc> query = session.createQuery(
                "SELECT k FROM Khoahoc k WHERE k.danhMucId.tenDanhMuc = :tenDanhMuc AND k.trangThai = true AND ngayBatDau > :currentDate ORDER BY k.id DESC", Khoahoc.class);
        query.setParameter("tenDanhMuc", tenDanhMuc);
        query.setParameter("currentDate", new Date());
        // Tính toán offset
        int offset = page * size;

        query.setFirstResult(offset);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public List<Khoahoc> load4KhoaHocMoiNhat() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Khoahoc WHERE trangThai = true AND ngayBatDau > :currentDate ORDER BY ngayTao DESC";

        Query query = session.createQuery(hql);
        query.setParameter("currentDate", new Date());
        query.setMaxResults(4);

        return query.list();
    }

    @Override
    public List<Khoahoc> getKhoaHocHocVien(int hocVienId) {
        Session session = sessionFactory.getCurrentSession();
        List<Khoahoc> danhSachKhoaHoc = new ArrayList<>();

        try {
            // Truy vấn để lấy danh sách đơn hàng cho hocVienId
            String hqlDonHang = "SELECT d FROM Donhang d WHERE d.userId.id = :hocVienId";
            Query<Donhang> queryDonHang = session.createQuery(hqlDonHang, Donhang.class);
            queryDonHang.setParameter("hocVienId", hocVienId);

            // Lấy danh sách đơn hàng
            List<Donhang> danhSachDonHang = queryDonHang.getResultList();

            // Lấy khóa học từ danh sách đơn hàng
            for (Donhang donHang : danhSachDonHang) {
                Khoahoc khoaHoc = donHang.getKhoaHocId();
                if (khoaHoc != null) {
                    danhSachKhoaHoc.add(khoaHoc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return danhSachKhoaHoc;
    }

    @Override
    public List<Khoahoc> getKhoaHocDangHoc(int hocVienId) {
        Session session = sessionFactory.getCurrentSession();
        List<Khoahoc> danhSachKhoaHocDangHoc = new ArrayList<>();

        try {
            // Lấy ngày hiện tại
            Date currentDate = new Date();

            // Truy vấn để lấy danh sách khóa học đang học
            String hql = "SELECT kh FROM Khoahoc kh JOIN Donhang d ON kh.id = d.khoaHocId.id "
                    + "WHERE d.userId.id = :hocVienId AND kh.ngayBatDau <= :currentDate AND kh.ngayKetThuc >= :currentDate";
            Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
            query.setParameter("hocVienId", hocVienId);
            query.setParameter("currentDate", currentDate);

            // Lấy danh sách khóa học
            danhSachKhoaHocDangHoc = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return danhSachKhoaHocDangHoc;
    }

    @Override
    public List<Khoahoc> getKhoaHocDaMua(int hocVienId) {
        Session session = sessionFactory.getCurrentSession();
        List<Khoahoc> danhSachKhoaHocDaMua = new ArrayList<>();

        try {
            // Lấy ngày hiện tại
            Date currentDate = new Date();

            // Truy vấn để lấy danh sách khóa học đã mua
            String hql = "SELECT kh FROM Khoahoc kh JOIN Donhang d ON kh.id = d.khoaHocId.id "
                    + "WHERE d.userId.id = :hocVienId AND kh.ngayKetThuc < :currentDate";
            Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
            query.setParameter("hocVienId", hocVienId);
            query.setParameter("currentDate", currentDate);

            // Lấy danh sách khóa học
            danhSachKhoaHocDaMua = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return danhSachKhoaHocDaMua;
    }

    @Override
    public List<Khoahoc> getKhoaHocSapToi(int hocVienId) {
        Session session = sessionFactory.getCurrentSession();
        List<Khoahoc> danhSachKhoaHocSapToi = new ArrayList<>();

        try {
            // Lấy ngày hiện tại
            Date currentDate = new Date();

            // Truy vấn để lấy danh sách khóa học sắp tới
            String hql = "SELECT kh FROM Khoahoc kh JOIN Donhang d ON kh.id = d.khoaHocId.id "
                    + "WHERE d.userId.id = :hocVienId AND kh.ngayBatDau > :currentDate";
            Query<Khoahoc> query = session.createQuery(hql, Khoahoc.class);
            query.setParameter("hocVienId", hocVienId);
            query.setParameter("currentDate", currentDate);

            // Lấy danh sách khóa học
            danhSachKhoaHocSapToi = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }

        return danhSachKhoaHocSapToi;
    }

}
