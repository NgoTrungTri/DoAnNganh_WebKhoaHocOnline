/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

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
@Table(name = "loaigiaovien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loaigiaovien.findAll", query = "SELECT l FROM Loaigiaovien l"),
    @NamedQuery(name = "Loaigiaovien.findById", query = "SELECT l FROM Loaigiaovien l WHERE l.id = :id"),
    @NamedQuery(name = "Loaigiaovien.findByTenLoai", query = "SELECT l FROM Loaigiaovien l WHERE l.tenLoai = :tenLoai")})
public class Loaigiaovien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tenLoai")
    private String tenLoai;
    @OneToMany(mappedBy = "loaiGiaoVienId")
    private Set<UserLoaigiaovien> userLoaigiaovienSet;
    
    

    public Loaigiaovien() {
    }

    public Loaigiaovien(Integer id) {
        this.id = id;
    }

    public Loaigiaovien(Integer id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @XmlTransient
    public Set<UserLoaigiaovien> getUserLoaigiaovienSet() {
        return userLoaigiaovienSet;
    }

    public void setUserLoaigiaovienSet(Set<UserLoaigiaovien> userLoaigiaovienSet) {
        this.userLoaigiaovienSet = userLoaigiaovienSet;
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
        if (!(object instanceof Loaigiaovien)) {
            return false;
        }
        Loaigiaovien other = (Loaigiaovien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Loaigiaovien[ id=" + id + " ]";
    }
    
}
