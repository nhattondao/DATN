/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ChuDe_BaiTap.view;

import ChuDe_BaiTap.dao.ChuDeDao;
import ChuDe_BaiTap.model.Chude;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author PC
 */
public class trangchude extends javax.swing.JFrame {

    /**
     * Creates new form trangchude
     */
    public trangchude() {
        codegiaodien();
    }

    public void codegiaodien() {
        setTitle("Home Gym Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Sidebar bên trái
        // Nội dung chính
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(150, 50, 50));
        contentPanel.setBounds(0, 0, 1920, 1080);
        contentPanel.setLayout(null);

        // Thêm logo vào góc trên bên trái
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(150, 50, 50));
        logoPanel.setBounds(10, 10, 130, 130); // Vị trí và kích thước của logo
        logoPanel.add(logoLabel);
        contentPanel.add(logoPanel); // Thêm logoPanel vào contentPanel

        // Thêm dòng chủ đề mới
        JLabel subTitleLabel = new JLabel("Chủ đề luyện tập", SwingConstants.CENTER);
        subTitleLabel.setForeground(Color.WHITE); // Màu chữ
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Kích thước và kiểu chữ
        subTitleLabel.setBounds(0, 50, 1920, 50); // Vị trí và kích thước
        contentPanel.add(subTitleLabel); // Thêm vào contentPanel

// Thêm dòng chữ "Danh sách chủ đề"
        JLabel titleLabel = new JLabel("Danh sách chủ đề", SwingConstants.LEFT);
        titleLabel.setForeground(Color.WHITE); // Màu chữ
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Kích thước và kiểu chữ
        titleLabel.setBounds(200, 160, 1920, 40); // Cập nhật vị trí để ở dưới dòng chủ đề mới
        contentPanel.add(titleLabel); // Thêm vào contentPanel

        JPanel NangCaoPanel = new JPanel();
        NangCaoPanel.setBackground(new Color(150, 50, 50));
        NangCaoPanel.setBounds(230, 200, 1200, 200); // Vị trí
        NangCaoPanel.setLayout(null);
        contentPanel.add(NangCaoPanel);

        // Tải tất cả các chủ đề từ cơ sở dữ liệu
        ArrayList<Chude> danhSachKho = ChuDeDao.getAllChuDe();
        int NangCaoX = 40; // Vị trí bắt đầu cho các chủ đề
        for (int i = 0; i < danhSachKho.size(); i++) {
            Chude chude = danhSachKho.get(i);
            JPanel imgTextButton = createBaiTapPanel(chude, NangCaoX, i);
            NangCaoPanel.add(imgTextButton);
            NangCaoX += 200; // Điều chỉnh khoảng cách giữa các chủ đề
        }

        // Nút bài tập Dễ
        JLabel deLabel = new JLabel("Dễ");
        deLabel.setForeground(Color.WHITE);
        deLabel.setFont(new Font("Arial", Font.BOLD, 24));
        deLabel.setBounds(200, 480, 100, 30); // Thay đổi y từ 360 thành 460
        contentPanel.add(deLabel);

        // Hình ảnh cho JLabel
        ImageIcon DeIcon = new ImageIcon(getClass().getResource("/ChuDe_BaiTap/img/DE.png"));
        Image DeImage = DeIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon DeImgIcon = new ImageIcon(DeImage);
        JLabel DeLabelWithImage = new JLabel(DeImgIcon);
        DeLabelWithImage.setBounds(200, 550, 350, 350); // Thay đổi x từ 40 thành 80
        DeLabelWithImage.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Thay đổi con trỏ khi di chuột vào

        // Thêm MouseListener cho sự kiện click
        DeLabelWithImage.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Baitaptheomucdo("De").setVisible(true);
                dispose(); // Đóng trang chủ
            }
        });
        contentPanel.add(DeLabelWithImage);

        // Nút bài tập Trung Bình
        JLabel trungBinhLabel = new JLabel("Trung Bình");
        trungBinhLabel.setForeground(Color.WHITE);
        trungBinhLabel.setFont(new Font("Arial", Font.BOLD, 24));
        trungBinhLabel.setBounds(700, 480, 200, 30); // Thay đổi y từ 360 thành 460
        contentPanel.add(trungBinhLabel);

        // Hình ảnh cho JLabel
        ImageIcon TrungBinhIcon = new ImageIcon(getClass().getResource("/ChuDe_BaiTap/img/TRUNGBINH.png"));
        Image TrungBinhImage = TrungBinhIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon TrungBinhImgIcon = new ImageIcon(TrungBinhImage);
        JLabel TrungBinhLabelWithImage = new JLabel(TrungBinhImgIcon);
        TrungBinhLabelWithImage.setBounds(700, 550, 350, 350); // Vị trí không thay đổi
        TrungBinhLabelWithImage.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Thay đổi con trỏ khi di chuột vào

        // Thêm MouseListener cho sự kiện click
        TrungBinhLabelWithImage.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Baitaptheomucdo("Trung binh").setVisible(true);
                dispose(); // Đóng trang chủ
            }
        });
        contentPanel.add(TrungBinhLabelWithImage);

        // Nút bài tập Khó
        JLabel khoLabel = new JLabel("Khó");
        khoLabel.setForeground(Color.WHITE);
        khoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        khoLabel.setBounds(1200, 480, 100, 30); // Thay đổi y từ 360 thành 460
        contentPanel.add(khoLabel);

        // Hình ảnh cho JLabel
        ImageIcon KhoIcon = new ImageIcon(getClass().getResource("/ChuDe_BaiTap/img/KHO.png"));
        Image KhoImage = KhoIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon KhoImgIcon = new ImageIcon(KhoImage);
        JLabel KhoLabelWithImage = new JLabel(KhoImgIcon);
        KhoLabelWithImage.setBounds(1200, 550, 350, 350); // Vị trí không thay đổi
        KhoLabelWithImage.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Thay đổi con trỏ khi di chuột vào

        // Thêm MouseListener cho sự kiện click
        KhoLabelWithImage.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Baitaptheomucdo("Kho").setVisible(true);
                dispose(); // Đóng trang chủ
            }
        });
        contentPanel.add(KhoLabelWithImage);
        add(contentPanel);
    }

    public JPanel createBaiTapPanel(Chude cd, int x, int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBounds(x, 0, 160, 200); // Cập nhật vị trí x

        // Tạo JButton cho hình ảnh
        ImageIcon icon = new ImageIcon(cd.getHinhAnhChuDe());
        Image image = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JButton button = new JButton(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                // Thiết lập hình tròn cho JButton
                if (g instanceof Graphics2D) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
                    g2.setClip(shape);
                    super.paintComponent(g);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(160, 160); // Kích thước hình tròn
            }
        };

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Tạo label cho tên bài tập
        JLabel label = new JLabel(cd.getTenChuDe(), SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // Thêm sự kiện nhấn nút để mở trang chủ đề
        button.addActionListener(e -> {
            new MH_SauChuDe(cd).setVisible(true);
            dispose();
        });

        // Sắp xếp layout
        panel.add(button, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        return panel;
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
            java.util.logging.Logger.getLogger(trangchude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(trangchude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(trangchude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(trangchude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new trangchude().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
