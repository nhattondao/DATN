/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChuDe_BaiTap.dao;

import ChuDe_BaiTap.model.Thetrangcanhan;
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
       private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    static String getAll = "SELECT * FROM Thetrangcanhan WHERE User_ID = ?";
    static String update = "UPDATE Thetrangcanhan SET Chieu_Cao = ?, Can_Nang = ? WHERE User_ID = ?";
    
    
    public static ArrayList<Thetrangcanhan> ThetrangByID(int userID) {
        ArrayList<Thetrangcanhan> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
            PreparedStatement pstm = con.prepareStatement(getAll);) {
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
    
    public static boolean sua(int id, float chieuCao, float canNang) {
        String update = "UPDATE Thetrangcanhan SET Chieu_Cao = ?, Can_Nang = ? WHERE Thetrang_ID = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstm = con.prepareStatement(update)) {
            pstm.setFloat(1, chieuCao);
            pstm.setFloat(2, canNang);
            pstm.setInt(3, id);
            int row = pstm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
