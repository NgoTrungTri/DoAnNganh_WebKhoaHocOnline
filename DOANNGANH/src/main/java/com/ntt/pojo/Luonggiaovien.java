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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "luonggiaovien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Luonggiaovien.findAll", query = "SELECT l FROM Luonggiaovien l"),
    @NamedQuery(name = "Luonggiaovien.findById", query = "SELECT l FROM Luonggiaovien l WHERE l.id = :id"),
    @NamedQuery(name = "Luonggiaovien.findByHeSo", query = "SELECT l FROM Luonggiaovien l WHERE l.heSo = :heSo"),
    @NamedQuery(name = "Luonggiaovien.findByTienLuongThemTheoGio", query = "SELECT l FROM Luonggiaovien l WHERE l.tienLuongThemTheoGio = :tienLuongThemTheoGio")})
public class Luonggiaovien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "heSo")
    private double heSo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tienLuongThemTheoGio")
    private double tienLuongThemTheoGio;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Luonggiaovien() {
    }

    public Luonggiaovien(Integer id) {
        this.id = id;
    }

    public Luonggiaovien(Integer id, double heSo, double tienLuongThemTheoGio) {
        this.id = id;
        this.heSo = heSo;
        this.tienLuongThemTheoGio = tienLuongThemTheoGio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getHeSo() {
        return heSo;
    }

    public void setHeSo(double heSo) {
        this.heSo = heSo;
    }

    public double getTienLuongThemTheoGio() {
        return tienLuongThemTheoGio;
    }

    public void setTienLuongThemTheoGio(double tienLuongThemTheoGio) {
        this.tienLuongThemTheoGio = tienLuongThemTheoGio;
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
        if (!(object instanceof Luonggiaovien)) {
            return false;
        }
        Luonggiaovien other = (Luonggiaovien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ntt.pojo.Luonggiaovien[ id=" + id + " ]";
    }
    
}
