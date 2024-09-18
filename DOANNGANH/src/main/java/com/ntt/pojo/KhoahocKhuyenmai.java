/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "khoahoc_khuyenmai")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KhoahocKhuyenmai.findAll", query = "SELECT k FROM KhoahocKhuyenmai k"),
    @NamedQuery(name = "KhoahocKhuyenmai.findById", query = "SELECT k FROM KhoahocKhuyenmai k WHERE k.id = :id")})
public class KhoahocKhuyenmai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "khoaHocId", referencedColumnName = "id")
    @ManyToOne
    private Khoahoc khoaHocId;
    @JoinColumn(name = "khuyenMaiId", referencedColumnName = "id")
    @ManyToOne
    private Khuyenmai khuyenMaiId;

    public KhoahocKhuyenmai() {
    }

    public KhoahocKhuyenmai(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Khoahoc getKhoaHocId() {
        return khoaHocId;
    }

    public void setKhoaHocId(Khoahoc khoaHocId) {
        this.khoaHocId = khoaHocId;
    }

    public Khuyenmai getKhuyenMaiId() {
        return khuyenMaiId;
    }

    public void setKhuyenMaiId(Khuyenmai khuyenMaiId) {
        this.khuyenMaiId = khuyenMaiId;
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
        if (!(object instanceof KhoahocKhuyenmai)) {
            return false;
        }
        KhoahocKhuyenmai other = (KhoahocKhuyenmai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.KhoahocKhuyenmai[ id=" + id + " ]";
    }
    
}
