/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CacLoaiTheTrang.model;

/**
 *
 * @author PC
 */
public class Baitap {
    private int baitapID;
    private String tenBaitap;
    private String hinhAnh;
    private String video;
    private int chuDeID;
    private int huongDanChiTietID;
    private int soSet;
    private int soRep;
    private String mucDo;
    private String gioiTinhBaiTap;
    private double calo;

    public Baitap(int baitapID, String tenBaitap, String hinhAnh, String video, int chuDeID, int huongDanChiTietID, int soSet, int soRep, String mucDo, String gioiTinhBaiTap, double calo) {
        this.baitapID = baitapID;
        this.tenBaitap = tenBaitap;
        this.hinhAnh = hinhAnh;
        this.video = video;
        this.chuDeID = chuDeID;
        this.huongDanChiTietID = huongDanChiTietID;
        this.soSet = soSet;
        this.soRep = soRep;
        this.mucDo = mucDo;
        this.gioiTinhBaiTap = gioiTinhBaiTap;
        this.calo = calo;
    }

    public Baitap() {
    }

    public int getBaitapID() {
        return baitapID;
    }

    public void setBaitapID(int baitapID) {
        this.baitapID = baitapID;
    }

    public String getTenBaitap() {
        return tenBaitap;
    }

    public void setTenBaitap(String tenBaitap) {
        this.tenBaitap = tenBaitap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getChuDeID() {
        return chuDeID;
    }

    public void setChuDeID(int chuDeID) {
        this.chuDeID = chuDeID;
    }

    public int getHuongDanChiTietID() {
        return huongDanChiTietID;
    }

    public void setHuongDanChiTietID(int huongDanChiTietID) {
        this.huongDanChiTietID = huongDanChiTietID;
    }

    public int getSoSet() {
        return soSet;
    }

    public void setSoSet(int soSet) {
        this.soSet = soSet;
    }

    public int getSoRep() {
        return soRep;
    }

    public void setSoRep(int soRep) {
        this.soRep = soRep;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getGioiTinhBaiTap() {
        return gioiTinhBaiTap;
    }

    public void setGioiTinhBaiTap(String gioiTinhBaiTap) {
        this.gioiTinhBaiTap = gioiTinhBaiTap;
    }

    public double getCalo() {
        return calo;
    }

    public void setCalo(double calo) {
        this.calo = calo;
    }
    
    
}
