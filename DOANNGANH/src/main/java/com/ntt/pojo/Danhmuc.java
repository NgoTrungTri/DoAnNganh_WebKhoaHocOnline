/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "danhmuc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Danhmuc.findAll", query = "SELECT d FROM Danhmuc d"),
    @NamedQuery(name = "Danhmuc.findById", query = "SELECT d FROM Danhmuc d WHERE d.id = :id"),
    @NamedQuery(name = "Danhmuc.findByTenDanhMuc", query = "SELECT d FROM Danhmuc d WHERE d.tenDanhMuc = :tenDanhMuc")})
public class Danhmuc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tenDanhMuc")
    private String tenDanhMuc;
    @OneToMany(mappedBy = "danhMucId")
    @JsonIgnore
    private Set<Baidangvanban> baidangvanbanSet;
    @OneToMany(mappedBy = "danhMucId")
    @JsonIgnore
    private Set<Khoahoc> khoahocSet;

    public Danhmuc() {
    }

    public Danhmuc(Integer id) {
        this.id = id;
    }

    public Danhmuc(Integer id, String tenDanhMuc) {
        this.id = id;
        this.tenDanhMuc = tenDanhMuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @XmlTransient
    public Set<Baidangvanban> getBaidangvanbanSet() {
        return baidangvanbanSet;
    }

    @XmlTransient
    public void setBaidangvanbanSet(Set<Baidangvanban> baidangvanbanSet) {
        this.baidangvanbanSet = baidangvanbanSet;
    }

    @XmlTransient
    public Set<Khoahoc> getKhoahocSet() {
        return khoahocSet;
    }

    @XmlTransient
    public void setKhoahocSet(Set<Khoahoc> khoahocSet) {
        this.khoahocSet = khoahocSet;
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
        if (!(object instanceof Danhmuc)) {
            return false;
        }
        Danhmuc other = (Danhmuc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Danhmuc[ id=" + id + " ]";
    }
    
}
