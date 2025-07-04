/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin;

import Admin.QlyBaiTap.ViewQLBT;
import Admin.QlyChuDe.ViewChuDe;
import Admin.QlyNguoiDung.ViewQLND;
import Admin.QlyThucPham.ViewQLTP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Admin
 */
public class AdminForm extends javax.swing.JFrame {

    /**
     * Creates new form AdminForm
     */
    public AdminForm() {
        initComponents();
        AdminDashboard();
    }
    private JButton btnBaitap, btnUser, btnThucpham, btnChuDe;

    public void AdminDashboard() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(144, 60, 60));

        ImageIcon originalIcon = new ImageIcon("F:/CodeJava/Body_Safe/Picture/snapedit_1741363788304.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        // Logo
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(0, 0, 180, 180);
        add(logoLabel);

        // **TITLE CHÍNH**
        JLabel titleLabel = new JLabel("CHÀO MỪNG ADMIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 38));
        titleLabel.setForeground(Color.WHITE);
        //titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 580, 10, 0));

        // **PHẦN CHỨC NĂNG QUẢN LÝ (Ở NGAY TRÊN PANEL)**
        JLabel functionLabel = new JLabel("CHỨC NĂNG QUẢN LÝ");
        functionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        functionLabel.setForeground(new Color(100, 170, 255));
        //functionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        functionLabel.setBorder(BorderFactory.createEmptyBorder(30, 280, 0, 0));

        // **MAIN PANEL CHỨA 4 DÒNG (ĐẶT GIỮA MÀN HÌNH)**
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 15, 15)); // Khoảng cách lớn hơn
        mainPanel.setBackground(new Color(144, 60, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 350, 100, 350)); // Đẩy vào giữa

        btnBaitap = new JButton("QUẢN LÝ >");
        btnUser = new JButton("QUẢN LÝ >");
        btnThucpham = new JButton("QUẢN LÝ >");
        btnChuDe = new JButton("QUẢN LÝ >");

        ViewChuDe QLCD = new ViewChuDe();
        ViewQLTP QLTP = new ViewQLTP();

        QLCD.setVisible(false);
        QLTP.setVisible(false);
        btnBaitap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewQLBT QLBT = new ViewQLBT();
                QLBT.BaiTapGUI();
                QLBT.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
                QLBT.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewQLND QLND = new ViewQLND();
                QLND.UserGUI();
                QLND.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
                QLND.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        btnThucpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QLTP.setVisible(true);
                QLTP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
                QLTP.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        btnChuDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QLCD.setVisible(true);
                QLCD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setVisible(false);
                QLCD.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        mainPanel.add(createPanel("QUẢN LÝ BÀI TẬP", "Quản lý danh sách bài tập, thêm mới chỉnh sửa, xóa", btnBaitap));
        mainPanel.add(createPanel("QUẢN LÝ NGƯỜI DÙNG", "Kiểm tra và quản lý thông tin người dùng", btnUser));
        mainPanel.add(createPanel("QUẢN LÝ THỰC PHẨM", "Quản lý, kiểm tra danh sách thực phẩm", btnThucpham));
        mainPanel.add(createPanel("QUẢN LÝ CHỦ ĐỀ", "Quản lý, thêm, chỉnh sửa chủ đề", btnChuDe));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(144, 60, 60));
        centerPanel.add(functionLabel, BorderLayout.NORTH);
        centerPanel.add(mainPanel, BorderLayout.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createPanel(String title, String description, JButton button) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(144, 60, 60));
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 85, 168), 5));
        panel.setPreferredSize(new Dimension(500, 70));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 20, 0, 0));

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(Color.BLACK);
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 8, 0));

        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(0, 85, 168));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 45)); // Điều chỉnh kích thước theo yêu cầu

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(descriptionLabel);

        panel.add(textPanel, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(button);
        panel.add(buttonPanel, BorderLayout.EAST);

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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
