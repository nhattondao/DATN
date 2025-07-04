/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyThucPham;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class QLTP_Service {
    static String connectUrl = "jdbc:sqlserver://LAPTOP-IFP08H8J:1433;databaseName=Body_Safe;user=sa;password=123456;TrustServerCertificate=true;";
    static String getAllQuery = "Select * from Thuc_Pham";
static String createQuery = "INSERT INTO Thuc_Pham (ThucphamName, Mo_Ta, calo, carb, protein, fat, fiber, vitamin, Img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
static String updateQuery = "UPDATE Thuc_Pham SET ThucphamName = ?, Mo_Ta = ?, calo = ?, carb = ?, protein = ?, fat = ?, fiber = ?, vitamin = ?, Img = ? WHERE ThucPham_ID = ?;";
static String deleteQuery = "DELETE FROM Thuc_Pham WHERE ThucPham_ID = ?;";


    public static ArrayList<QLTP_Model> getAll() {
        ArrayList<QLTP_Model> Food = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(getAllQuery)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                QLTP_Model tp = new QLTP_Model();
                tp.setThucPhamID(rs.getInt("ThucPham_ID"));
                tp.setThucPhamName(rs.getString("ThucPhamName"));
                tp.setMoTa(rs.getString("Mo_Ta"));
                tp.setCalo(rs.getFloat("calo"));
                tp.setCarb(rs.getFloat("carb"));
                tp.setProtein(rs.getFloat("protein"));
                tp.setFat(rs.getFloat("fat"));
                tp.setFiber(rs.getFloat("fiber"));
                tp.setVitamin(rs.getFloat("vitamin"));
                tp.setImg(rs.getString("Img"));
                Food.add(tp);
            }
            return Food;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Food;
    }

    public static boolean createTP(String ThucPhamName, String Mo_Ta, float calo, float carb,float protein,float fat,float fiber,float vitamin,String Img) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(createQuery)) {
            pstm.setString(1, ThucPhamName);
            pstm.setString(2, Mo_Ta);
            pstm.setFloat(3, calo);
            pstm.setFloat(4, carb);
            pstm.setFloat(5, protein);
            pstm.setFloat(6, fat);
            pstm.setFloat(7, fiber);
            pstm.setFloat(8, vitamin);
            pstm.setString(9, Img);
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

    public static boolean updateTP(int ThucPham_ID,String ThucPhamName, String Mo_Ta, float calo, float carb,float protein,float fat,float fiber,float vitamin,String Img) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(updateQuery)) {
            pstm.setInt(10, ThucPham_ID);
            pstm.setString(1, ThucPhamName);
            pstm.setString(2, Mo_Ta);
            pstm.setFloat(3, calo);
            pstm.setFloat(4, carb);
            pstm.setFloat(5, protein);
            pstm.setFloat(6, fat);
            pstm.setFloat(7, fiber);
            pstm.setFloat(8, vitamin);
            pstm.setString(9, Img);
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

    public static boolean deleteTP(int ThucPhamID) {
        try (Connection con = DriverManager.getConnection(connectUrl); PreparedStatement pstm = con.prepareStatement(deleteQuery)) {
            pstm.setInt(1, ThucPhamID);
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
