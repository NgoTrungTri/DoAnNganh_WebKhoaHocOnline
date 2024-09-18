/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "donhang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Donhang.findAll", query = "SELECT d FROM Donhang d"),
    @NamedQuery(name = "Donhang.findById", query = "SELECT d FROM Donhang d WHERE d.id = :id"),
    @NamedQuery(name = "Donhang.findByNgayTao", query = "SELECT d FROM Donhang d WHERE d.ngayTao = :ngayTao"),
    @NamedQuery(name = "Donhang.findByTongTien", query = "SELECT d FROM Donhang d WHERE d.tongTien = :tongTien"),
    @NamedQuery(name = "Donhang.findByThongTinThanhToan", query = "SELECT d FROM Donhang d WHERE d.thongTinThanhToan = :thongTinThanhToan")})
public class Donhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayTao")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayTao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tongTien")
    private double tongTien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "thongTinThanhToan")
    private String thongTinThanhToan;
    @JoinColumn(name = "khoaHocId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Khoahoc khoaHocId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User userId;

    public Donhang() {
    }

    public Donhang(Integer id) {
        this.id = id;
    }

    public Donhang(Integer id, Date ngayTao, double tongTien, String thongTinThanhToan) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.thongTinThanhToan = thongTinThanhToan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getThongTinThanhToan() {
        return thongTinThanhToan;
    }

    public void setThongTinThanhToan(String thongTinThanhToan) {
        this.thongTinThanhToan = thongTinThanhToan;
    }

    @XmlTransient
    public Khoahoc getKhoaHocId() {
        return khoaHocId;
    }

    @XmlTransient
    public void setKhoaHocId(Khoahoc khoaHocId) {
        this.khoaHocId = khoaHocId;
    }

    @XmlTransient
    public User getUserId() {
        return userId;
    }

    @XmlTransient
    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Donhang)) {
            return false;
        }
        Donhang other = (Donhang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Donhang[ id=" + id + " ]";
    }
    
}
