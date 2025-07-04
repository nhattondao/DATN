/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyBaiTap;

import Admin.QlyChuDe.QLCD_Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QLBT_Service {

    static String connectUrl = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";

    public static ArrayList<QLBT_Model> getAll() {
        ArrayList<QLBT_Model> baiTapList = new ArrayList<>();
        String query = """
            SELECT bt.BaiTap_ID, bt.Ten_baitap, bt.Hinh_Anh, bt.Video, 
                cd.Chu_De_ID AS ChuDe_ID, cd.Ten_Chu_De, cd.Mo_ta_chu_de,
                bt.So_Set, bt.So_Rep, bt.Muc_do, bt.gioi_tinh_bai_tap, bt.Calo
            FROM Baitap bt
            JOIN Chu_De cd ON bt.Chu_De_ID = cd.Chu_De_ID
            """;

        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement stm = con.prepareStatement(query); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                QLCD_Model chuDe = new QLCD_Model(
                        rs.getInt("ChuDe_ID"),
                        rs.getString("Ten_Chu_De"),
                        rs.getString("Mo_ta_chu_de")
                );

                QLBT_Model bt = new QLBT_Model(
                        rs.getInt("BaiTap_ID"),
                        rs.getString("Ten_baitap"),
                        rs.getString("Hinh_Anh"),
                        rs.getString("Video"),
                        chuDe, // Gán đối tượng QLCD_Model vào
                        rs.getInt("So_Set"),
                        rs.getInt("So_Rep"),
                        rs.getString("Muc_do"),
                        rs.getString("gioi_tinh_bai_tap"),
                        rs.getInt("Calo")
                );
                baiTapList.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baiTapList;
    }

    public static int getChuDeID(String tenChuDe) {
        String query = "SELECT Chu_De_ID FROM Chu_De WHERE Ten_Chu_De = ?";
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, tenChuDe);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Chu_De_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Không tìm thấy
    }

    public static boolean createBaiTap(String tenBaiTap, String hinhAnh, String video, String tenChuDe, int soSet, int soRep, String mucDo, String gioiTinh, int calo) {
        int chuDeID = getChuDeID(tenChuDe);
        if (chuDeID == -1) {
            return false; // Nếu chủ đề không tồn tại, không thêm bài tập
        }
        String query = "INSERT INTO Baitap (Ten_BaiTap, Hinh_Anh, Video, Chu_De_ID, So_Set, So_Rep, Muc_do, Gioi_tinh_bai_tap, Calo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, tenBaiTap);
            stm.setString(2, hinhAnh);
            stm.setString(3, video);
            stm.setInt(4, chuDeID);
            stm.setInt(5, soSet);
            stm.setInt(6, soRep);
            stm.setString(7, mucDo);
            stm.setString(8, gioiTinh);
            stm.setInt(9, calo);

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateBaiTap(int baiTapID, String tenBaiTap, String hinhAnh, String video, String tenChuDe, int soSet, int soRep, String mucDo, String gioiTinh, int calo) {
        int chuDeID = getChuDeID(tenChuDe);
        if (chuDeID == -1) {
            return false; // Nếu chủ đề không tồn tại, không cập nhật bài tập
        }
        String query = "UPDATE Baitap SET Ten_baitap = ?, Hinh_Anh = ?, Video = ?, Chu_De_ID = ?, So_Set = ?, So_Rep = ?, Muc_do = ?, gioi_tinh_bai_tap = ?, Calo = ? WHERE BaiTap_ID = ?";
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, tenBaiTap);
            stm.setString(2, hinhAnh);
            stm.setString(3, video);
            stm.setInt(4, chuDeID);
            stm.setInt(5, soSet);
            stm.setInt(6, soRep);
            stm.setString(7, mucDo);
            stm.setString(8, gioiTinh);
            stm.setInt(9, calo);
            stm.setInt(10, baiTapID);

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteBaiTap(int baiTapID) {
        String query = "DELETE FROM Baitap WHERE BaiTap_ID = ?";
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, baiTapID);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
