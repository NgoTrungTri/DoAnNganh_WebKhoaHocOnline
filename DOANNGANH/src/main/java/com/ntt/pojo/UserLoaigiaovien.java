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
@Table(name = "user_loaigiaovien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserLoaigiaovien.findAll", query = "SELECT u FROM UserLoaigiaovien u"),
    @NamedQuery(name = "UserLoaigiaovien.findById", query = "SELECT u FROM UserLoaigiaovien u WHERE u.id = :id")})
public class UserLoaigiaovien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "loaiGiaoVienId", referencedColumnName = "id")
    @ManyToOne
    private Loaigiaovien loaiGiaoVienId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public UserLoaigiaovien() {
    }

    public UserLoaigiaovien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Loaigiaovien getLoaiGiaoVienId() {
        return loaiGiaoVienId;
    }

    public void setLoaiGiaoVienId(Loaigiaovien loaiGiaoVienId) {
        this.loaiGiaoVienId = loaiGiaoVienId;
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
        if (!(object instanceof UserLoaigiaovien)) {
            return false;
        }
        UserLoaigiaovien other = (UserLoaigiaovien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.UserLoaigiaovien[ id=" + id + " ]";
    }
    
}
