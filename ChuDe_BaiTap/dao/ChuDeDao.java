/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChuDe_BaiTap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ChuDe_BaiTap.model.Chude;

/**
 *
 * @author PC
 */
public class ChuDeDao {
    static String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    static String getAll = "SELECT * FROM Chu_De";
    
    public static ArrayList<Chude> getAllChuDe() {
        ArrayList<Chude> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getAll);
            ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {                
                Chude cd = new Chude();
                cd.setChuDeID(rs.getInt("Chu_De_ID"));
                cd.setTenChuDe(rs.getString("Ten_chu_de"));
                cd.setMoTaChuDe(rs.getString("Mo_ta_chu_de"));
                cd.setHinhAnhChuDe(rs.getString("HinhAnh_chu_de"));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
