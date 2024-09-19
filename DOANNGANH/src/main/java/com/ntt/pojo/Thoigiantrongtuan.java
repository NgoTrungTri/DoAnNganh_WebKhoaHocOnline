/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "thoigiantrongtuan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thoigiantrongtuan.findAll", query = "SELECT t FROM Thoigiantrongtuan t"),
    @NamedQuery(name = "Thoigiantrongtuan.findById", query = "SELECT t FROM Thoigiantrongtuan t WHERE t.id = :id"),
    @NamedQuery(name = "Thoigiantrongtuan.findByTenThuTrongTuan", query = "SELECT t FROM Thoigiantrongtuan t WHERE t.tenThuTrongTuan = :tenThuTrongTuan"),
    @NamedQuery(name = "Thoigiantrongtuan.findByThoiGianBatDau", query = "SELECT t FROM Thoigiantrongtuan t WHERE t.thoiGianBatDau = :thoiGianBatDau"),
    @NamedQuery(name = "Thoigiantrongtuan.findByThoiGianKetThuc", query = "SELECT t FROM Thoigiantrongtuan t WHERE t.thoiGianKetThuc = :thoiGianKetThuc"),
    @NamedQuery(name = "Thoigiantrongtuan.findByThoiLuong", query = "SELECT t FROM Thoigiantrongtuan t WHERE t.thoiLuong = :thoiLuong")})
public class Thoigiantrongtuan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ngayHoc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayHoc;
    @Basic(optional = false)

    @Size(min = 1, max = 50)
    @Column(name = "tenThuTrongTuan")
    private String tenThuTrongTuan;
    @Basic(optional = false)

    @Column(name = "thoiGianBatDau")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date thoiGianBatDau;
    @Basic(optional = false)

    @Column(name = "thoiGianKetThuc")
    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Date thoiGianKetThuc;
    @Basic(optional = false)

    @Column(name = "thoiLuong")
    private double thoiLuong;
    @JsonIgnore
    @JoinColumn(name = "khoaHocId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khoahoc khoaHocId;

    public Thoigiantrongtuan() {
    }

    public Thoigiantrongtuan(Integer id) {
        this.id = id;
    }

    public Thoigiantrongtuan(Integer id, String tenThuTrongTuan, Date thoiGianBatDau, Date thoiGianKetThuc, double thoiLuong) {
        this.id = id;
        this.tenThuTrongTuan = tenThuTrongTuan;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.thoiLuong = thoiLuong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenThuTrongTuan() {
        return tenThuTrongTuan;
    }

    public void setTenThuTrongTuan(String tenThuTrongTuan) {
        this.tenThuTrongTuan = tenThuTrongTuan;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public double getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(double thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setNgayHoc(Date ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public Date getNgayHoc() {
        return ngayHoc;
    }

    @XmlTransient
    public Khoahoc getKhoaHocId() {
        return khoaHocId;
    }

    @XmlTransient
    public void setKhoaHocId(Khoahoc khoaHocId) {
        this.khoaHocId = khoaHocId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thoigiantrongtuan)) {
            return false;
        }
        Thoigiantrongtuan other = (Thoigiantrongtuan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Thoigiantrongtuan[ id=" + id + " ]";
    }
    

}
