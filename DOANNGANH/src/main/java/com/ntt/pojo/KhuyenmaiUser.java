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
@Table(name = "khuyenmai_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KhuyenmaiUser.findAll", query = "SELECT k FROM KhuyenmaiUser k"),
    @NamedQuery(name = "KhuyenmaiUser.findById", query = "SELECT k FROM KhuyenmaiUser k WHERE k.id = :id")})
public class KhuyenmaiUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "khuyenMaiId", referencedColumnName = "id")
    @ManyToOne
    private Khuyenmai khuyenMaiId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public KhuyenmaiUser() {
    }

    public KhuyenmaiUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Khuyenmai getKhuyenMaiId() {
        return khuyenMaiId;
    }

    public void setKhuyenMaiId(Khuyenmai khuyenMaiId) {
        this.khuyenMaiId = khuyenMaiId;
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
        if (!(object instanceof KhuyenmaiUser)) {
            return false;
        }
        KhuyenmaiUser other = (KhuyenmaiUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.KhuyenmaiUser[ id=" + id + " ]";
    }
    
}
