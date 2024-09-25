/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.pojo;

/**
 *
 * @author DELL
 */
public class BaiDangRequest {

    private String tieuDe;
    private String noiDung;
    private String videoUrl;  ///Link video
    private String moTa;      // mô tả video
    private Integer danhMucId;

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

    public Integer getDanhMucId() {
        return danhMucId;
    }

    public void setDanhMucId(Integer danhMucId) {
        this.danhMucId = danhMucId;
    }
}
