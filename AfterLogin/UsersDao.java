/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AfterLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import AfterLogin.Users;

/**
 *
 * @author PC
 */
public class UsersDao {
    static String connectionString = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAll = "SELECT * FROM users";
    static String getusersbyID = "SELECT * FROM users WHERE User_ID = ?";
    
    public static ArrayList<Users> getAllUsers() {
        ArrayList<Users> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getAll);
            ResultSet rs = pstm.executeQuery()) {
            
            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setDob(rs.getInt("DoB"));
                user.setGender(rs.getString("Gender"));
                user.setBaiDaTap(rs.getInt("BaiDaTap"));
                user.setRoles(rs.getString("Roles"));
                user.setStatus(rs.getString("Status"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Users> getUsersByID(int userID) {
        ArrayList<Users> list = new ArrayList<>();
        String query = "SELECT * FROM users WHERE User_ID = ?"; // Assuming you're using User_ID to filter
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstm = con.prepareStatement(query)) {

            pstm.setInt(1, userID);  // Setting the parameter correctly
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setDob(rs.getInt("DoB"));
                user.setGender(rs.getString("Gender"));
                user.setBaiDaTap(rs.getInt("BaiDaTap"));
                user.setRoles(rs.getString("Roles"));
                user.setStatus(rs.getString("Status"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
