/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

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

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "bangluong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bangluong.findAll", query = "SELECT b FROM Bangluong b"),
    @NamedQuery(name = "Bangluong.findById", query = "SELECT b FROM Bangluong b WHERE b.id = :id"),
    @NamedQuery(name = "Bangluong.findByThoiGian", query = "SELECT b FROM Bangluong b WHERE b.thoiGian = :thoiGian"),
    @NamedQuery(name = "Bangluong.findByTongLuong", query = "SELECT b FROM Bangluong b WHERE b.tongLuong = :tongLuong")})
public class Bangluong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGian")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGian;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tongLuong")
    private double tongLuong;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Bangluong() {
    }

    public Bangluong(Integer id) {
        this.id = id;
    }

    public Bangluong(Integer id, Date thoiGian, double tongLuong) {
        this.id = id;
        this.thoiGian = thoiGian;
        this.tongLuong = tongLuong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(double tongLuong) {
        this.tongLuong = tongLuong;
    }

    public User getUserId() {
        return userId;
    }

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
        if (!(object instanceof Bangluong)) {
            return false;
        }
        Bangluong other = (Bangluong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Bangluong[ id=" + id + " ]";
    }
    
}
