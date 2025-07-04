/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ChuDe_BaiTap.view;

import ChuDe_BaiTap.dao.BaitapDao;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ChuDe_BaiTap.model.Baitap;

/**
 *
 * @author x
 */
public class Baitaptheomucdo extends javax.swing.JFrame {

     public Baitaptheomucdo(String mucDo) {
        setTitle("Danh sách bài tập - " + mucDo);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(150, 50, 50));

        // Panel chứa logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(150, 50, 50));
        
        // Tải logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.setBackground(new Color(150, 50, 50));
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.WEST);

        // Tiêu đề chủ đề
        JLabel titleLabel = new JLabel("Mức độ bài tập: " + mucDo, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);



        // Panel chứa danh sách bài tập
        JPanel baiTapContainer = new JPanel();
        baiTapContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Dàn hàng ngang
        baiTapContainer.setBackground(new Color(150, 50, 50));

        // Lấy dữ liệu từ DB
        loadExercises(baiTapContainer, mucDo);

        JScrollPane scrollPane = new JScrollPane(baiTapContainer);
        scrollPane.setBackground(new Color(150, 50, 50));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Tạo nút "Quay lại"
        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);

        // Thêm sự kiện quay lại trang chủ đề
        backButton.addActionListener(e -> {
            new trangchude().setVisible(true);
            dispose(); // Đóng cửa sổ hiện tại
        });

        // Panel chứa nút "Quay lại"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(150, 50, 50));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadExercises(JPanel baiTapContainer, String mucDo) {
        baiTapContainer.removeAll(); // Xóa tất cả các thành phần trước đó

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true")) {

            String query = "SELECT Baitap_ID, Ten_baitap, Hinh_Anh FROM Baitap WHERE Muc_do = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, mucDo);
            ResultSet rs = stmt.executeQuery();

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int baitapID = rs.getInt("Baitap_ID");
                String tenBaitap = rs.getString("Ten_baitap");
                String hinhAnh = rs.getString("Hinh_Anh");

                System.out.println("✅ Lấy bài tập: " + tenBaitap);

                // Load và scale ảnh (100x100)
                ImageIcon icon = new ImageIcon(hinhAnh);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);

                // Tạo JButton chứa ảnh bài tập
                JButton btnBaiTap = new JButton(scaledIcon);
                btnBaiTap.setPreferredSize(new Dimension(120, 120)); // Kích thước nút
                btnBaiTap.setFocusPainted(false);
                btnBaiTap.setBackground(Color.WHITE); // Màu nền nút

                // Khi nhấn vào bài tập, mở trang bài tập
                btnBaiTap.addActionListener(e -> {
                    new MH_Baitap_Long(baitapID).setVisible(true);
                    dispose(); // Đóng trang hiện tại
                });

                // Tạo JLabel chứa tên bài tập
                JLabel lblTenBaiTap = new JLabel(tenBaitap, SwingConstants.CENTER);
                lblTenBaiTap.setFont(new Font("Arial", Font.BOLD, 14)); // Kích thước font
                lblTenBaiTap.setForeground(Color.WHITE);

                // Tạo JPanel chứa cả ảnh và tên bài tập
                JPanel baiTapPanel = new JPanel();
                baiTapPanel.setLayout(new BorderLayout());
                baiTapPanel.setPreferredSize(new Dimension(140, 180)); // Kích thước cố định
                baiTapPanel.setBackground(new Color(10, 50, 50)); // Màu nền của panel
                baiTapPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // Thêm vào panel bài tập
                baiTapPanel.add(btnBaiTap, BorderLayout.CENTER);
                baiTapPanel.add(lblTenBaiTap, BorderLayout.SOUTH);

                // Thêm panel vào danh sách
                baiTapContainer.add(baiTapPanel);
            }

            if (!hasData) {
                System.out.println("⚠ Không có bài tập nào cho mức độ này!");
                JLabel noDataLabel = new JLabel("⚠ Không có bài tập nào!", SwingConstants.CENTER);
                noDataLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                noDataLabel.setForeground(Color.YELLOW);
                baiTapContainer.add(noDataLabel);
            }

            baiTapContainer.revalidate();
            baiTapContainer.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Danh sách bài tập");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new Baitaptheomucdo("easy")); // Thay đổi mức độ ở đây
            frame.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
