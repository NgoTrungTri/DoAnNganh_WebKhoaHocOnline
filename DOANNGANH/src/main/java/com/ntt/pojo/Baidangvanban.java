/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "baidangvanban")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baidangvanban.findAll", query = "SELECT b FROM Baidangvanban b"),
    @NamedQuery(name = "Baidangvanban.findById", query = "SELECT b FROM Baidangvanban b WHERE b.id = :id"),
    @NamedQuery(name = "Baidangvanban.findByTieuDe", query = "SELECT b FROM Baidangvanban b WHERE b.tieuDe = :tieuDe"),
    @NamedQuery(name = "Baidangvanban.findByNgayDang", query = "SELECT b FROM Baidangvanban b WHERE b.ngayDang = :ngayDang"),
    @NamedQuery(name = "Baidangvanban.findByTrangThai", query = "SELECT b FROM Baidangvanban b WHERE b.trangThai = :trangThai"),
    @NamedQuery(name = "Baidangvanban.findByNgayDuyet", query = "SELECT b FROM Baidangvanban b WHERE b.ngayDuyet = :ngayDuyet")})
public class Baidangvanban implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tieuDe")
    private String tieuDe;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "noiDung")
    private String noiDung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayDang")
    @Temporal(TemporalType.DATE)
    private Date ngayDang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "trangThai")
    private String trangThai;
    @Column(name = "ngayDuyet")
    @Temporal(TemporalType.DATE)
    private Date ngayDuyet;
    @Lob
    @Size(max = 65535)
    @Column(name = "noiDungPhanHoi")
    private String noiDungPhanHoi;
    @JsonIgnore
    @JoinColumn(name = "danhMucId", referencedColumnName = "id")
    @ManyToOne
    private Danhmuc danhMucId;
    @JoinColumn(name = "idNVDuyet", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private User idNVDuyet;
    @JoinColumn(name = "idGVDang", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private User idGVDang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiDangVanBanId")
    @JsonIgnore
    private Set<Videobaidang> videobaidangSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiDangVanBanId")
    @JsonIgnore
    private Set<Thich> thichSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiDangVanBanId")
    @JsonIgnore
    private Set<Binhluan> binhluanSet;

    public Baidangvanban() {
    }

    public Baidangvanban(Integer id) {
        this.id = id;
    }

    public Baidangvanban(Integer id, String tieuDe, String noiDung, Date ngayDang, String trangThai) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public String getNoiDungPhanHoi() {
        return noiDungPhanHoi;
    }

    public void setNoiDungPhanHoi(String noiDungPhanHoi) {
        this.noiDungPhanHoi = noiDungPhanHoi;
    }

    @XmlTransient
    public Danhmuc getDanhMucId() {
        return danhMucId;
    }

    @XmlTransient
    public void setDanhMucId(Danhmuc danhMucId) {
        this.danhMucId = danhMucId;
    }

    @XmlTransient
    public User getIdNVDuyet() {
        return idNVDuyet;
    }

    @XmlTransient
    public void setIdNVDuyet(User idNVDuyet) {
        this.idNVDuyet = idNVDuyet;
    }

    @XmlTransient
    public User getIdGVDang() {
        return idGVDang;
    }

    @XmlTransient
    public void setIdGVDang(User idGVDang) {
        this.idGVDang = idGVDang;
    }

    @XmlTransient
    public Set<Videobaidang> getVideobaidangSet() {
        return videobaidangSet;
    }

    @XmlTransient
    public void setVideobaidangSet(Set<Videobaidang> videobaidangSet) {
        this.videobaidangSet = videobaidangSet;
    }

    @XmlTransient
    public Set<Thich> getThichSet() {
        return thichSet;
    }

    @XmlTransient
    public void setThichSet(Set<Thich> thichSet) {
        this.thichSet = thichSet;
    }

    @XmlTransient
    public Set<Binhluan> getBinhluanSet() {
        return binhluanSet;
    }

    @XmlTransient
    public void setBinhluanSet(Set<Binhluan> binhluanSet) {
        this.binhluanSet = binhluanSet;
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
        if (!(object instanceof Baidangvanban)) {
            return false;
        }
        Baidangvanban other = (Baidangvanban) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Baidangvanban[ id=" + id + " ]";
    }

}
