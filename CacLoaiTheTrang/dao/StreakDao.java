/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CacLoaiTheTrang.dao;

import static CacLoaiTheTrang.dao.ThetrangcanhanDao.connectionString;
import static CacLoaiTheTrang.dao.ThetrangcanhanDao.getAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import CacLoaiTheTrang.model.Baitap;
import CacLoaiTheTrang.model.Streak;

/**
 *
 * @author PC
 */
public class StreakDao {
   static String connectionString = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAll = "SELECT Streak_ID, User_ID, StartDate, LastDate, Streak FROM Streak WHERE User_ID = ?";
    
    public static ArrayList<Streak> StreakByID(int userID) {
        ArrayList<Streak> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getAll);) {
            pstm.setInt(1, userID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
                Streak streak = new Streak();
                streak.setStreakId(rs.getInt("Streak_ID"));
                streak.setUserId(rs.getInt("User_ID"));
                streak.setStartDate(rs.getDate("StartDate").toLocalDate()); // Convert java.sql.Date to LocalDate
                streak.setLastDate(rs.getDate("LastDate").toLocalDate());
                streak.setStreak(rs.getInt("Streak"));
                list.add(streak);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Baitap> StreakByID(int userID, int weekNum) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
