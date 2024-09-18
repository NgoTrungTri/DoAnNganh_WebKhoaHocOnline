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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "videobaidang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Videobaidang.findAll", query = "SELECT v FROM Videobaidang v"),
    @NamedQuery(name = "Videobaidang.findById", query = "SELECT v FROM Videobaidang v WHERE v.id = :id"),
    @NamedQuery(name = "Videobaidang.findByVideoUrl", query = "SELECT v FROM Videobaidang v WHERE v.videoUrl = :videoUrl")})
public class Videobaidang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "videoUrl")
    private String videoUrl;
    @Lob
    @Size(max = 65535)
    @Column(name = "moTa")
    private String moTa;
    @JoinColumn(name = "baiDangVanBanId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Baidangvanban baiDangVanBanId;

    public Videobaidang() {
    }

    public Videobaidang(Integer id) {
        this.id = id;
    }

    public Videobaidang(Integer id, String videoUrl) {
        this.id = id;
        this.videoUrl = videoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Baidangvanban getBaiDangVanBanId() {
        return baiDangVanBanId;
    }

    public void setBaiDangVanBanId(Baidangvanban baiDangVanBanId) {
        this.baiDangVanBanId = baiDangVanBanId;
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
        if (!(object instanceof Videobaidang)) {
            return false;
        }
        Videobaidang other = (Videobaidang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Videobaidang[ id=" + id + " ]";
    }
    
}
