/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyChuDe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class QLCD_Service {

    static String connectUrl = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAllQuery = "SELECT * FROM Chu_De";
    static String createQuery = "INSERT INTO Chu_De (Ten_Chu_De, Mo_Ta_Chu_De) VALUES (?, ?)";
    static String updateQuery = "UPDATE Chu_De SET Ten_Chu_De = ?, Mo_Ta_Chu_De = ? WHERE Chu_De_ID = ?";
    static String deleteQuery = "DELETE FROM Chu_De WHERE Chu_De_ID = ?";

    public static ArrayList<QLCD_Model> getAll() {
        ArrayList<QLCD_Model> danhSachChuDe = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(getAllQuery)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLCD_Model cd = new QLCD_Model();
                cd.setChuDeID(rs.getInt("Chu_De_ID"));
                cd.setTenChuDe(rs.getString("Ten_Chu_De"));
                cd.setMoTaChuDe(rs.getString("Mo_Ta_Chu_De"));
                danhSachChuDe.add(cd);
            }
            return danhSachChuDe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachChuDe;
    }

    public static boolean createCD(String tenChuDe, String moTaChuDe) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(createQuery)) {
            pstm.setString(1, tenChuDe);
            pstm.setString(2, moTaChuDe);
            int row = pstm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateCD(int chuDeID, String tenChuDe, String moTaChuDe) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(updateQuery)) {
            pstm.setString(1, tenChuDe);
            pstm.setString(2, moTaChuDe);
            pstm.setInt(3, chuDeID);
            int row = pstm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCD(int chuDeID) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(deleteQuery)) {
            pstm.setInt(1, chuDeID);
            int row = pstm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
