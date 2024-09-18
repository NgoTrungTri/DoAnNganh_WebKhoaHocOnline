/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "khoahoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khoahoc.findAll", query = "SELECT k FROM Khoahoc k"),
    @NamedQuery(name = "Khoahoc.findById", query = "SELECT k FROM Khoahoc k WHERE k.id = :id"),
    @NamedQuery(name = "Khoahoc.findByTenKhoaHoc", query = "SELECT k FROM Khoahoc k WHERE k.tenKhoaHoc = :tenKhoaHoc"),
    @NamedQuery(name = "Khoahoc.findByNgayTao", query = "SELECT k FROM Khoahoc k WHERE k.ngayTao = :ngayTao"),
    @NamedQuery(name = "Khoahoc.findByNgayBatDau", query = "SELECT k FROM Khoahoc k WHERE k.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "Khoahoc.findByNgayKetThuc", query = "SELECT k FROM Khoahoc k WHERE k.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "Khoahoc.findByGiaTien", query = "SELECT k FROM Khoahoc k WHERE k.giaTien = :giaTien"),
    @NamedQuery(name = "Khoahoc.findByTrangThai", query = "SELECT k FROM Khoahoc k WHERE k.trangThai = :trangThai"),
    @NamedQuery(name = "Khoahoc.findByTienLuongPhuTroi", query = "SELECT k FROM Khoahoc k WHERE k.tienLuongPhuTroi = :tienLuongPhuTroi")})
public class Khoahoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tenKhoaHoc")
    private String tenKhoaHoc;
    @Basic(optional = false)
    @Column(name = "ngayTao")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayTao;
    @Basic(optional = false)
    @Column(name = "ngayBatDau")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayBatDau;
    @Basic(optional = false)
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayKetThuc;
    @Basic(optional = false)
    @Column(name = "giaTien")
    private double giaTien;
    @Basic(optional = false)
    @Column(name = "trangThai")
    private boolean trangThai;
    @Basic(optional = false)
    @Column(name = "tienLuongPhuTroi")
    private double tienLuongPhuTroi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaHocId")
    @JsonIgnore
    private Set<Donhang> donhangSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaHocId")
    @JsonIgnore
    private Set<Thoigiantrongtuan> thoigiantrongtuanSet;
    @JoinColumn(name = "danhMucId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Danhmuc danhMucId;
    @JoinColumn(name = "idNVTao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User idNVTao;
    @JoinColumn(name = "idGVPhuTrach", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User idGVPhuTrach;
    @OneToMany(mappedBy = "khoaHocId")
    @JsonIgnore
    private Set<KhoahocKhuyenmai> khoahocKhuyenmaiSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khoaHocId")
    private Set<Ngayhocbu> ngayhocbuSet;

    public Khoahoc() {
    }

    public Khoahoc(Integer id) {
        this.id = id;
    }

    public Khoahoc(Integer id, String tenKhoaHoc, Date ngayTao, Date ngayBatDau, Date ngayKetThuc, double giaTien, boolean trangThai, double tienLuongPhuTroi) {
        this.id = id;
        this.tenKhoaHoc = tenKhoaHoc;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
        this.tienLuongPhuTroi = tienLuongPhuTroi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public double getTienLuongPhuTroi() {
        return tienLuongPhuTroi;
    }

    public void setTienLuongPhuTroi(double tienLuongPhuTroi) {
        this.tienLuongPhuTroi = tienLuongPhuTroi;
    }

    @XmlTransient
    public Set<Donhang> getDonhangSet() {
        return donhangSet;
    }

    @XmlTransient
    public void setDonhangSet(Set<Donhang> donhangSet) {
        this.donhangSet = donhangSet;
    }

    @XmlTransient
    public Set<Thoigiantrongtuan> getThoigiantrongtuanSet() {
        return thoigiantrongtuanSet;
    }

    @XmlTransient
    public void setThoigiantrongtuanSet(Set<Thoigiantrongtuan> thoigiantrongtuanSet) {
        this.thoigiantrongtuanSet = thoigiantrongtuanSet;
    }

    @XmlTransient
    public Danhmuc getDanhMucId() {
        return danhMucId;
    }

    @XmlTransient
    public void setDanhMucId(Danhmuc danhMucId) {
        this.danhMucId = danhMucId;
    }

    @XmlTransient
    public User getIdNVTao() {
        return idNVTao;
    }

    @XmlTransient
    public void setIdNVTao(User idNVTao) {
        this.idNVTao = idNVTao;
    }

    @XmlTransient
    public User getIdGVPhuTrach() {
        return idGVPhuTrach;
    }

    @XmlTransient
    public void setIdGVPhuTrach(User idGVPhuTrach) {
        this.idGVPhuTrach = idGVPhuTrach;
    }

    @XmlTransient
    public Set<KhoahocKhuyenmai> getKhoahocKhuyenmaiSet() {
        return khoahocKhuyenmaiSet;
    }

    @XmlTransient
    public void setKhoahocKhuyenmaiSet(Set<KhoahocKhuyenmai> khoahocKhuyenmaiSet) {
        this.khoahocKhuyenmaiSet = khoahocKhuyenmaiSet;
    }

    @XmlTransient
    public Set<Ngayhocbu> getNgayhocbuSet() {
        return ngayhocbuSet;
    }

    @XmlTransient
    public void setNgayhocbuSet(Set<Ngayhocbu> ngayhocbuSet) {
        this.ngayhocbuSet = ngayhocbuSet;
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
        if (!(object instanceof Khoahoc)) {
            return false;
        }
        Khoahoc other = (Khoahoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Khoahoc[ id=" + id + " ]";
    }

}
