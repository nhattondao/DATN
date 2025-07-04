/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CacLoaiTheTrang.model;

/**
 *
 * @author PC
 */
public class Thetrangcanhan {
    private int theTrangID;
    private int userID;
    private float chieuCao;
    private float canNang;
    private String loaiCoThe;
    private String coTheMongMuon;

    public Thetrangcanhan(int theTrangID, int userID, float chieuCao, float canNang, String loaiCoThe, String coTheMongMuon) {
        this.theTrangID = theTrangID;
        this.userID = userID;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.loaiCoThe = loaiCoThe;
        this.coTheMongMuon = coTheMongMuon;
    }

    public Thetrangcanhan() {
    }

    public int getTheTrangID() {
        return theTrangID;
    }

    public void setTheTrangID(int theTrangID) {
        this.theTrangID = theTrangID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(float chieuCao) {
        this.chieuCao = chieuCao;
    }

    public float getCanNang() {
        return canNang;
    }

    public void setCanNang(float canNang) {
        this.canNang = canNang;
    }

    public String getLoaiCoThe() {
        return loaiCoThe;
    }

    public void setLoaiCoThe(String loaiCoThe) {
        this.loaiCoThe = loaiCoThe;
    }

    public String getCoTheMongMuon() {
        return coTheMongMuon;
    }

    public void setCoTheMongMuon(String coTheMongMuon) {
        this.coTheMongMuon = coTheMongMuon;
    }

}

