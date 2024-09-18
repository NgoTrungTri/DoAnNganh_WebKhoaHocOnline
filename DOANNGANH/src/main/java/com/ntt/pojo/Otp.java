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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "otp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otp.findAll", query = "SELECT o FROM Otp o"),
    @NamedQuery(name = "Otp.findById", query = "SELECT o FROM Otp o WHERE o.id = :id"),
    @NamedQuery(name = "Otp.findByMaOTP", query = "SELECT o FROM Otp o WHERE o.maOTP = :maOTP"),
    @NamedQuery(name = "Otp.findByThoiGianHetHan", query = "SELECT o FROM Otp o WHERE o.thoiGianHetHan = :thoiGianHetHan"),
    @NamedQuery(name = "Otp.findByIsUse", query = "SELECT o FROM Otp o WHERE o.isUse = :isUse")})
public class Otp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "maOTP")
    private String maOTP;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianHetHan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianHetHan;
    @Column(name = "isUse")
    private Boolean isUse;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Otp() {
    }

    public Otp(Integer id) {
        this.id = id;
    }

    public Otp(Integer id, String maOTP, Date thoiGianHetHan) {
        this.id = id;
        this.maOTP = maOTP;
        this.thoiGianHetHan = thoiGianHetHan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaOTP() {
        return maOTP;
    }

    public void setMaOTP(String maOTP) {
        this.maOTP = maOTP;
    }

    public Date getThoiGianHetHan() {
        return thoiGianHetHan;
    }

    public void setThoiGianHetHan(Date thoiGianHetHan) {
        this.thoiGianHetHan = thoiGianHetHan;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
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
        if (!(object instanceof Otp)) {
            return false;
        }
        Otp other = (Otp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Otp[ id=" + id + " ]";
    }
    
}
