/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyBaiTap;

import Admin.QlyChuDe.QLCD_Model;

/**
 *
 * @author Admin
 */
public class QLBT_Model {
    private int baiTapID;
    private String tenBaiTap;
    private String hinhAnh;
    private String video;
    private QLCD_Model chuDe;  // Đối tượng ChuDe
    private int soSet;
    private int soRep;
    private String mucDo;
    private String gioiTinh;
    private int calo;

    public QLBT_Model() {
    }

    public QLBT_Model(int baiTapID, String tenBaiTap, String hinhAnh, String video, QLCD_Model chuDe, int soSet, int soRep, String mucDo, String gioiTinh, int calo) {
        this.baiTapID = baiTapID;
        this.tenBaiTap = tenBaiTap;
        this.hinhAnh = hinhAnh;
        this.video = video;
        this.chuDe = chuDe;
        this.soSet = soSet;
        this.soRep = soRep;
        this.mucDo = mucDo;
        this.gioiTinh = gioiTinh;
        this.calo = calo;
    }

    public int getBaiTapID() {
        return baiTapID;
    }

    public void setBaiTapID(int baiTapID) {
        this.baiTapID = baiTapID;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
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

    public QLCD_Model getChuDe() {
        return chuDe;
    }

    public void setChuDe(QLCD_Model chuDe) {
        this.chuDe = chuDe;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }
    
    
}
