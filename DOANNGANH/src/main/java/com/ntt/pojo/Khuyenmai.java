/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "khuyenmai")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khuyenmai.findAll", query = "SELECT k FROM Khuyenmai k"),
    @NamedQuery(name = "Khuyenmai.findById", query = "SELECT k FROM Khuyenmai k WHERE k.id = :id"),
    @NamedQuery(name = "Khuyenmai.findByTenKhuyenMai", query = "SELECT k FROM Khuyenmai k WHERE k.tenKhuyenMai = :tenKhuyenMai"),
    @NamedQuery(name = "Khuyenmai.findByNgayBatDau", query = "SELECT k FROM Khuyenmai k WHERE k.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "Khuyenmai.findByNgayKetThuc", query = "SELECT k FROM Khuyenmai k WHERE k.ngayKetThuc = :ngayKetThuc"),
    @NamedQuery(name = "Khuyenmai.findByGiaTri", query = "SELECT k FROM Khuyenmai k WHERE k.giaTri = :giaTri")})
public class Khuyenmai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tenKhuyenMai")
    private String tenKhuyenMai;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayBatDau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giaTri")
    private double giaTri;
    @OneToMany(mappedBy = "khuyenMaiId")
    private Set<KhuyenmaiUser> khuyenmaiUserSet;
    @OneToMany(mappedBy = "khuyenMaiId")
    private Set<KhoahocKhuyenmai> khoahocKhuyenmaiSet;

    public Khuyenmai() {
    }

    public Khuyenmai(Integer id) {
        this.id = id;
    }

    public Khuyenmai(Integer id, String tenKhuyenMai, Date ngayBatDau, Date ngayKetThuc, double giaTri) {
        this.id = id;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
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

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    @XmlTransient
    public Set<KhuyenmaiUser> getKhuyenmaiUserSet() {
        return khuyenmaiUserSet;
    }

    public void setKhuyenmaiUserSet(Set<KhuyenmaiUser> khuyenmaiUserSet) {
        this.khuyenmaiUserSet = khuyenmaiUserSet;
    }

    @XmlTransient
    public Set<KhoahocKhuyenmai> getKhoahocKhuyenmaiSet() {
        return khoahocKhuyenmaiSet;
    }

    public void setKhoahocKhuyenmaiSet(Set<KhoahocKhuyenmai> khoahocKhuyenmaiSet) {
        this.khoahocKhuyenmaiSet = khoahocKhuyenmaiSet;
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
        if (!(object instanceof Khuyenmai)) {
            return false;
        }
        Khuyenmai other = (Khuyenmai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Khuyenmai[ id=" + id + " ]";
    }
    
}
