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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "ngayhocbu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ngayhocbu.findAll", query = "SELECT n FROM Ngayhocbu n"),
    @NamedQuery(name = "Ngayhocbu.findById", query = "SELECT n FROM Ngayhocbu n WHERE n.id = :id"),
    @NamedQuery(name = "Ngayhocbu.findByNgayHocBu", query = "SELECT n FROM Ngayhocbu n WHERE n.ngayHocBu = :ngayHocBu"),
    @NamedQuery(name = "Ngayhocbu.findByNgayBu", query = "SELECT n FROM Ngayhocbu n WHERE n.ngayBu = :ngayBu")})
public class Ngayhocbu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ngayHocBu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ngayHocBu;
    @Basic(optional = false)
    @Column(name = "ngayBu")
    @Temporal(TemporalType.DATE)
    private Date ngayBu;

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
    @JoinColumn(name = "khoaHocId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Khoahoc khoaHocId;

    public Ngayhocbu() {
    }

    public Ngayhocbu(Integer id) {
        this.id = id;
    }

    public Ngayhocbu(Integer id, Date ngayHocBu, Date ngayBu) {
        this.id = id;
        this.ngayHocBu = ngayHocBu;
        this.ngayBu = ngayBu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayHocBu() {
        return ngayHocBu;
    }

    public void setNgayHocBu(Date ngayHocBu) {
        this.ngayHocBu = ngayHocBu;
    }

    public Date getNgayBu() {
        return ngayBu;
    }

    public void setNgayBu(Date ngayBu) {
        this.ngayBu = ngayBu;
    }

    @XmlTransient
    public Khoahoc getKhoaHocId() {
        return khoaHocId;
    }

    @XmlTransient
    public void setKhoaHocId(Khoahoc khoaHocId) {
        this.khoaHocId = khoaHocId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ngayhocbu)) {
            return false;
        }
        Ngayhocbu other = (Ngayhocbu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Ngayhocbu[ id=" + id + " ]";
    }

}
