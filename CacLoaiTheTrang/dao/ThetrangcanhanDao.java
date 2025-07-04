/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CacLoaiTheTrang.dao;

import CacLoaiTheTrang.model.Thetrangcanhan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ThetrangcanhanDao {

    static String connectionString = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAll = "SELECT * FROM Thetrangcanhan WHERE User_ID = ?";
    static String update = "UPDATE Thetrangcanhan SET Chieu_Cao = ?, Can_Nang = ? WHERE User_ID = ?";

    public static ArrayList<Thetrangcanhan> ThetrangByID(int userID) {
        ArrayList<Thetrangcanhan> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString); PreparedStatement pstm = con.prepareStatement(getAll);) {
            pstm.setInt(1, userID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Thetrangcanhan tt = new Thetrangcanhan();
                tt.setTheTrangID(rs.getInt("Thetrang_ID"));
                tt.setUserID(rs.getInt("User_ID"));
                tt.setChieuCao(rs.getFloat("Chieu_Cao"));
                tt.setCanNang(rs.getFloat("Can_Nang"));
                tt.setLoaiCoThe(rs.getString("Loai_co_the"));
                tt.setCoTheMongMuon(rs.getString("Co_the_mong_muon"));
                list.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateWeight(Float newWeight, int userID) {
        String sql = "UPDATE Thetrangcanhan "
                + "SET Can_Nang = ? "
                + "FROM Thetrangcanhan t "
                + "JOIN users u ON t.User_ID = u.User_ID "
                + "WHERE u.User_ID = ?";
        try (Connection conn = DriverManager.getConnection(connectionString); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, newWeight);
            stmt.setInt(2, userID);

            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean insertHeight(Float newHeight, int userID) {
        String sql = "INSERT INTO Thetrangcanhan (User_ID, Chieu_Cao) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(connectionString); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setFloat(2, newHeight);

            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
