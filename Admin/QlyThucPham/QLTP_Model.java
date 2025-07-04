/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyThucPham;

/**
 *
 * @author Admin
 */
public class QLTP_Model {
    private int thucPhamID;
    private String thucPhamName;
    private String moTa;
    private float calo;
    private float carb;
    private float protein;
    private float fat;
    private float fiber;
    private float vitamin;
    private String img;

    public QLTP_Model() {
    }

    public QLTP_Model(int thucPhamID, String thucPhamName, String moTa, float calo, float carb, float protein, float fat, float fiber, float vitamin, String img) {
        this.thucPhamID = thucPhamID;
        this.thucPhamName = thucPhamName;
        this.moTa = moTa;
        this.calo = calo;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
        this.vitamin = vitamin;
        this.img = img;
    }

    public int getThucPhamID() {
        return thucPhamID;
    }

    public void setThucPhamID(int thucPhamID) {
        this.thucPhamID = thucPhamID;
    }

    public String getThucPhamName() {
        return thucPhamName;
    }

    public void setThucPhamName(String thucPhamName) {
        this.thucPhamName = thucPhamName;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getCalo() {
        return calo;
    }

    public void setCalo(float calo) {
        this.calo = calo;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    public float getVitamin() {
        return vitamin;
    }

    public void setVitamin(float vitamin) {
        this.vitamin = vitamin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}


