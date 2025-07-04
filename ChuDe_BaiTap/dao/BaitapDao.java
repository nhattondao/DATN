/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChuDe_BaiTap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ChuDe_BaiTap.model.Baitap;

/**
 *
 * @author PC
 */
public class BaitapDao {
    static String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    static String getAll = "SELECT * FROM Baitap";
    static String getBaiTapByMucDo = "SELECT * FROM Baitap WHERE Muc_do = ?";
    static String getBaiTapByWeek = "SELECT bt.* FROM Lichtap_baitap lbt "
               + "JOIN Lich_tap lt ON lbt.LichtapID = lt.LichtapID "
               + "JOIN Baitap bt ON lbt.Baitap_ID = bt.Baitap_ID "
               + "WHERE lt.User_ID = ? AND lt.ThuocTuanNao = ?";
    
  
    
    public static ArrayList<Baitap> getAllBaiTap() {
        ArrayList<Baitap> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getAll);
            ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {                
                Baitap bt = new Baitap();
                bt.setBaitapID(rs.getInt("Baitap_ID"));
                bt.setTenBaitap(rs.getString("Ten_baitap"));
                bt.setHinhAnh(rs.getString("Hinh_Anh"));
                bt.setVideo(rs.getString("Video"));
                bt.setChuDeID(rs.getInt("Chu_De_ID"));
                bt.setSoSet(rs.getInt("So_Set"));
                bt.setSoRep(rs.getInt("So_Rep"));
                bt.setMucDo(rs.getString("Muc_do"));
                bt.setCalo(rs.getDouble("Calo"));

                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Baitap> BaiTapByMucDo(String MucDo) {
        ArrayList<Baitap> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getBaiTapByMucDo)) {
            pstm.setString(1, MucDo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
                Baitap bt = new Baitap();
//                bt.setBaitapID(rs.getInt("Baitap_ID"));
                bt.setTenBaitap(rs.getString("Ten_baitap"));
                bt.setHinhAnh(rs.getString("Hinh_Anh"));
//                bt.setVideo(rs.getString("Video"));
//                bt.setChuDeID(rs.getInt("Chu_De_ID"));
//                bt.setSoSet(rs.getInt("So_Set"));
//                bt.setSoRep(rs.getInt("So_Rep"));
                bt.setMucDo(rs.getString("Muc_do"));
               
//                bt.setCalo(rs.getDouble("Calo"));

                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Baitap> getBaiTapByWeek(int userID, int weekNum) {
        ArrayList<Baitap> danhSach = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement ps = conn.prepareStatement(getBaiTapByWeek)) {
            ps.setInt(1, userID);
            ps.setInt(2, weekNum);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Baitap bt = new Baitap();
                bt.setBaitapID(rs.getInt("Baitap_ID"));
                bt.setTenBaitap(rs.getString("Ten_baitap"));
                bt.setHinhAnh(rs.getString("Hinh_Anh"));
                bt.setVideo(rs.getString("Video"));
                bt.setChuDeID(rs.getInt("Chu_De_ID"));
                bt.setSoSet(rs.getInt("So_Set"));
                bt.setSoRep(rs.getInt("So_Rep"));
                bt.setMucDo(rs.getString("Muc_do"));
                bt.setGioiTinhBaiTap(rs.getString("gioi_tinh_bai_tap"));
                bt.setCalo(rs.getDouble("Calo"));
                danhSach.add(bt);
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
        return danhSach;
    }
    
    public static Baitap getBaitapById(int baitapID) {
    String sql = "SELECT * FROM Baitap WHERE Baitap_ID = ?";
    try (Connection conn = DriverManager.getConnection(connectionString);
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, baitapID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Baitap bt = new Baitap();
                bt.setBaitapID(rs.getInt("Baitap_ID"));
                bt.setTenBaitap(rs.getString("Ten_baitap"));
                bt.setMucDo(rs.getString("Muc_do"));
                bt.setSoSet(rs.getInt("So_Set"));
                bt.setSoRep(rs.getInt("So_Rep"));
                bt.setCalo(rs.getInt("Calo"));
                bt.setHinhAnh(rs.getString("Hinh_Anh"));
                return bt;
            } else {
                System.out.println("Không tìm thấy bài tập với ID: " + baitapID);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
     private Connection conn;

    public BaitapDao() {
        try {
            // Kết nối tới SQL Server (Cập nhật URL, user, password theo cấu hình của bạn)
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
            
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách tất cả bài tập
    public List<ChuDe_BaiTap.model.Baitap> getAllBaitap() {
        List<ChuDe_BaiTap.model.Baitap> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM Baitap";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChuDe_BaiTap.model.Baitap bt = new ChuDe_BaiTap.model.Baitap();
                 bt.setTenBaitap(rs.getString("Ten_baitap"));
                    bt.setMucDo(rs.getString("Muc_do"));
                    bt.setSoSet(rs.getInt("So_Set"));
                    bt.setSoRep(rs.getInt("So_Rep"));
                    bt.setCalo(rs.getInt("Calo"));
                    bt.setHinhAnh(rs.getString("Hinh_Anh"));
                danhSach.add(bt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Lấy bài tập theo tên
    public ChuDe_BaiTap.model.Baitap getBaitapByName(String tenBai) {
        String sql = "SELECT * FROM Baitap WHERE Baitap_ID  = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenBai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ChuDe_BaiTap.model.Baitap bt = new ChuDe_BaiTap.model.Baitap();
                    bt.setTenBaitap(rs.getString("Ten_baitap"));
                    bt.setMucDo(rs.getString("Muc_do"));
                    bt.setSoSet(rs.getInt("So_Set"));
                    bt.setSoRep(rs.getInt("So_Rep"));
                    bt.setCalo(rs.getInt("Calo"));
                    bt.setHinhAnh(rs.getString("Hinh_Anh"));
                    return bt;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
