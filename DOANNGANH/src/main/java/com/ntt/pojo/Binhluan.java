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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "binhluan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Binhluan.findAll", query = "SELECT b FROM Binhluan b"),
    @NamedQuery(name = "Binhluan.findById", query = "SELECT b FROM Binhluan b WHERE b.id = :id"),
    @NamedQuery(name = "Binhluan.findByNgayBinhLuan", query = "SELECT b FROM Binhluan b WHERE b.ngayBinhLuan = :ngayBinhLuan")})
public class Binhluan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "noiDung")
    private String noiDung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayBinhLuan")
    @Temporal(TemporalType.DATE)
    private Date ngayBinhLuan;
    @JoinColumn(name = "baiDangVanBanId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Baidangvanban baiDangVanBanId;
    @OneToMany(mappedBy = "parentBinhLuanId")
    private Set<Binhluan> binhluanSet;
    @JoinColumn(name = "parentBinhLuanId", referencedColumnName = "id")
    @ManyToOne
    private Binhluan parentBinhLuanId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Binhluan() {
    }

    public Binhluan(Integer id) {
        this.id = id;
    }

    public Binhluan(Integer id, String noiDung, Date ngayBinhLuan) {
        this.id = id;
        this.noiDung = noiDung;
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(Date ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public Baidangvanban getBaiDangVanBanId() {
        return baiDangVanBanId;
    }

    public void setBaiDangVanBanId(Baidangvanban baiDangVanBanId) {
        this.baiDangVanBanId = baiDangVanBanId;
    }

    @XmlTransient
    public Set<Binhluan> getBinhluanSet() {
        return binhluanSet;
    }

    public void setBinhluanSet(Set<Binhluan> binhluanSet) {
        this.binhluanSet = binhluanSet;
    }

    public Binhluan getParentBinhLuanId() {
        return parentBinhLuanId;
    }

    public void setParentBinhLuanId(Binhluan parentBinhLuanId) {
        this.parentBinhLuanId = parentBinhLuanId;
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
        if (!(object instanceof Binhluan)) {
            return false;
        }
        Binhluan other = (Binhluan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Binhluan[ id=" + id + " ]";
    }
    
}
