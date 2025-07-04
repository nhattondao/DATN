/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Baitap;

/**
 *
 * @author PC
 */
public class BaitapDao {
    static String connectionString = "jdbc:sqlserver://DESKTOP-4VITKAM:1433;databaseName=Body_Safe;trustServerCertificate=true;user=sa;password=123";
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
                bt.setGioiTinhBaiTap(rs.getString("gioi_tinh_bai_tap"));
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
        String sql = "SELECT * FROM Baitap WHERE Baitap_ID = ?"; // Tìm theo ID
        try (Connection conn = DriverManager.getConnection(connectionString);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, baitapID); // Dùng setInt thay vì setString
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
