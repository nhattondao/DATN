/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ChuDe_BaiTap.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import ChuDe_BaiTap.model.Chude;
import ChuDe_BaiTap.model.Baitap;

import java.awt.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import ChuDe_BaiTap.model.Baitap;
import ChuDe_BaiTap.model.Chude;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dinh van
 */
public class MH_SauChuDe extends javax.swing.JFrame {

    private JPanel exercisesPanel;
    private JPanel baitapPanel;
    private Chude selectedChuDe;

    public MH_SauChuDe(Chude chuDe) {
        this.selectedChuDe = chuDe;
        initUI();
    }

    private void initUI() {
        setTitle("Body-Safe Nutrition App - " + selectedChuDe.getTenChuDe());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(150, 50, 50));

        // Tiêu đề chủ đề
        JLabel titleLabel = new JLabel(selectedChuDe.getTenChuDe(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(150, 50, 50));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.WEST);

        // Panel chứa danh sách bài tập
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(150, 50, 50));

        // Dòng tiêu đề "Danh sách bài tập"
        JLabel danhSachLabel = new JLabel("Danh sách bài tập", SwingConstants.LEFT);
        danhSachLabel.setFont(new Font("Arial", Font.BOLD, 20));
        danhSachLabel.setForeground(Color.WHITE);
        mainPanel.add(danhSachLabel, BorderLayout.NORTH);

        // Panel chứa danh sách bài tập
        JPanel danhSachBaiTapPanel = new JPanel();
        danhSachBaiTapPanel.setLayout(new BoxLayout(danhSachBaiTapPanel, BoxLayout.Y_AXIS));
        danhSachBaiTapPanel.setBackground(new Color(150, 50, 50));

        JScrollPane scrollPane = new JScrollPane(danhSachBaiTapPanel);
        scrollPane.setPreferredSize(new Dimension(50, 40));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(new Color(150, 50, 50));

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Tạo nút "Quay lại"
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);

// Thêm sự kiện quay lại trang chủ đề
        backButton.addActionListener(e -> {
            new trangchude().setVisible(true); // Mở lại trang chủ đề
            dispose(); // Đóng cửa sổ hiện tại
        });

// Thêm nút vào phần header hoặc phía dưới
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout để căn trái
        buttonPanel.setBackground(new Color(150, 50, 50));
        buttonPanel.add(backButton);

// Thêm khoảng cách dưới cùng của buttonPanel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10,50, 10)); // Thay đổi giá trị 30 để điều chỉnh khoảng cách

        add(buttonPanel, BorderLayout.SOUTH);

        loadExercises(danhSachBaiTapPanel);
    }

    private void loadExercises(JPanel danhSachBaiTapPanel) {
        danhSachBaiTapPanel.removeAll();
        danhSachBaiTapPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Dàn hàng ngang, khoảng cách 20px

        if (selectedChuDe == null) {
            JOptionPane.showMessageDialog(this, "Không có chủ đề nào được chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("🔍 Đang lấy bài tập cho chủ đề ID: " + selectedChuDe.getChuDeID());

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true")) {

            String query = "SELECT Baitap_ID, Ten_baitap, Hinh_Anh FROM Baitap WHERE Chu_de_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, selectedChuDe.getChuDeID());
            ResultSet rs = stmt.executeQuery();

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int baitapID = rs.getInt("Baitap_ID");
                String tenBaitap = rs.getString("Ten_baitap");
                String hinhAnh = rs.getString("Hinh_Anh");

                System.out.println("✅ Lấy bài tập: " + tenBaitap);

                // Load và scale ảnh (150x150)
                ImageIcon icon = new ImageIcon(hinhAnh);
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);

                // Tạo JButton chứa ảnh bài tập
                JButton btnBaiTap = new JButton(scaledIcon);
                btnBaiTap.setPreferredSize(new Dimension(160, 160)); // Kích thước nút
                btnBaiTap.setFocusPainted(false);
                
                btnBaiTap.setBackground(Color.WHITE);

                // Khi nhấn vào bài tập, mở trang bài tập
                btnBaiTap.addActionListener(e -> openExercisePage(baitapID));

                // Tạo JLabel chứa tên bài tập
                JLabel lblTenBaiTap = new JLabel(tenBaitap, SwingConstants.CENTER);
                lblTenBaiTap.setFont(new Font("Arial", Font.BOLD, 16));
                lblTenBaiTap.setForeground(Color.WHITE);

                // Tạo JPanel chứa cả ảnh và tên bài tập
                JPanel baiTapPanel = new JPanel();
                baiTapPanel.setLayout(new BorderLayout());
                baiTapPanel.setPreferredSize(new Dimension(180, 220)); // Kích thước cố định
                baiTapPanel.setBackground(new Color(10, 50, 50));
                baiTapPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Thêm vào panel bài tập
                baiTapPanel.add(btnBaiTap, BorderLayout.CENTER);
                baiTapPanel.add(lblTenBaiTap, BorderLayout.SOUTH);

                // Thêm panel vào danh sách
                danhSachBaiTapPanel.add(baiTapPanel);
            }

            if (!hasData) {
                System.out.println("⚠ Không có bài tập nào trong chủ đề này!");
                JLabel noDataLabel = new JLabel("⚠ Không có bài tập nào!", SwingConstants.CENTER);
                noDataLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                noDataLabel.setForeground(Color.YELLOW);
                danhSachBaiTapPanel.add(noDataLabel);
            }

            danhSachBaiTapPanel.revalidate();
            danhSachBaiTapPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openExercisePage(int baitapID) {
        SwingUtilities.invokeLater(() -> {
            new MH_Baitap_Long(baitapID).setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1111, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MH_SauChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MH_SauChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MH_SauChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MH_SauChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Chude dummyChuDe = new Chude(10, "Tập Ngực", "Các bài tập phát triển cơ ngực", "/Users/x/Documents/DATN_TON/Anhdaidien/Nguc.jpg");
                new MH_SauChuDe(dummyChuDe).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
