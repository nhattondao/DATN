package ChuDe_BaiTap.model;

public class Chude {
    private int chuDeID;
    private String tenChuDe;
    private String moTaChuDe;
    private String hinhAnhChuDe;

    // Constructor
    public Chude(int chuDeID, String tenChuDe, String moTaChuDe, String hinhAnhChuDe) {
        this.chuDeID = chuDeID;
        this.tenChuDe = tenChuDe;
        this.moTaChuDe = moTaChuDe;
        this.hinhAnhChuDe = hinhAnhChuDe;
    }

    public Chude() {
    }

    // Getters and Setters
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

    public String getHinhAnhChuDe() {
        return hinhAnhChuDe;
    }

    public void setHinhAnhChuDe(String hinhAnhChuDe) {
        this.hinhAnhChuDe = hinhAnhChuDe;
    }
}
