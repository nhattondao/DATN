/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyChuDe;

/**
 *
 * @author Admin
 */
public class QLCD_Model {
    private int chuDeID;
    private String tenChuDe;
    private String moTaChuDe;

    public QLCD_Model() {
    }

    public QLCD_Model(int chuDeID, String tenChuDe, String moTaChuDe) {
        this.chuDeID = chuDeID;
        this.tenChuDe = tenChuDe;
        this.moTaChuDe = moTaChuDe;
    }

    public int getChuDeID() {
        return chuDeID;
    }

    public void setChuDeID(int chuDeID) {
        this.chuDeID = chuDeID;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getMoTaChuDe() {
        return moTaChuDe;
    }

    public void setMoTaChuDe(String moTaChuDe) {
        this.moTaChuDe = moTaChuDe;
    }
    
    
}
