/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "khoahoc_decuong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KhoahocDecuong.findAll", query = "SELECT k FROM KhoahocDecuong k"),
    @NamedQuery(name = "KhoahocDecuong.findById", query = "SELECT k FROM KhoahocDecuong k WHERE k.id = :id"),
    @NamedQuery(name = "KhoahocDecuong.findByTenDeCuong", query = "SELECT k FROM KhoahocDecuong k WHERE k.tenDeCuong = :tenDeCuong"),
    @NamedQuery(name = "KhoahocDecuong.findByUrlFile", query = "SELECT k FROM KhoahocDecuong k WHERE k.urlFile = :urlFile")})
public class KhoahocDecuong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tenDeCuong")
    private String tenDeCuong;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "url_file")
    private String urlFile;
    @JoinColumn(name = "khoaHocId", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Khoahoc khoaHocId;

    @Transient
    private MultipartFile file;

    public KhoahocDecuong() {
    }

    public KhoahocDecuong(Integer id) {
        this.id = id;
    }

    public KhoahocDecuong(Integer id, String tenDeCuong, String urlFile) {
        this.id = id;
        this.tenDeCuong = tenDeCuong;
        this.urlFile = urlFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDeCuong() {
        return tenDeCuong;
    }

    public void setTenDeCuong(String tenDeCuong) {
        this.tenDeCuong = tenDeCuong;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    @XmlTransient
    public Khoahoc getKhoaHocId() {
        return khoaHocId;
    }

    @XmlTransient
    public void setKhoaHocId(Khoahoc khoaHocId) {
        this.khoaHocId = khoaHocId;
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
        if (!(object instanceof KhoahocDecuong)) {
            return false;
        }
        KhoahocDecuong other = (KhoahocDecuong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.KhoahocDecuong[ id=" + id + " ]";
    }

    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
