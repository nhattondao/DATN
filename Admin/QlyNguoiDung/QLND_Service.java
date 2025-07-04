/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyNguoiDung;

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
public class QLND_Service {

    static String connectUrl = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAllQuery = "Select * from Users";
    static String createQuery = "INSERT INTO Users (Username, Password, Email, DoB, Gender, Roles, Status) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
    static String updateQuery = "UPDATE Users SET Username = ?,Password = ?,Email = ?,DoB = ?,Gender = ?,Roles = ?,Status = ? WHERE User_ID = ?;";
    static String deleteQuery = "DELETE FROM Users WHERE User_ID = ?";

    public static ArrayList<QLND_Model> getAll() {
        ArrayList<QLND_Model> user = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(getAllQuery)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLND_Model nd = new QLND_Model();
                nd.setUserId(rs.getInt("User_ID"));
                nd.setUsername(rs.getString("Username"));
                nd.setPassword(rs.getString("Password"));
                nd.setEmail(rs.getString("Email"));
                nd.setDob(rs.getInt("DoB"));
                nd.setGender(rs.getString("Gender"));
                nd.setRoles(rs.getString("Roles"));
                nd.setStatus(rs.getString("Status"));
                user.add(nd);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(connectUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu email đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createND(String Username, String Password, String Email, int DoB, String Gender, String Roles, String Status) {
        if (isEmailExists(Email)) {
            JOptionPane.showMessageDialog(null, "Email đã tồn tại, vui lòng chọn email khác!");
            return false;
        }
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(createQuery)) {
            pstm.setString(1, Username);
            pstm.setString(2, Password);
            pstm.setString(3, Email);
            pstm.setInt(4, DoB);
            pstm.setString(5, Gender);
            pstm.setString(6, Roles);
            pstm.setString(7, Status);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateND(int User_ID, String Username, String Password, String Email, int DoB, String Gender, String Roles, String Status) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(updateQuery)) {
            pstm.setInt(8, User_ID);
            pstm.setString(1, Username);
            pstm.setString(2, Password);
            pstm.setString(3, Email);
            pstm.setInt(4, DoB);
            pstm.setString(5, Gender);
            pstm.setString(6, Roles);
            pstm.setString(7, Status);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteND(int User_ID) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(deleteQuery)) {
            pstm.setInt(1, User_ID);
            int row = pstm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
