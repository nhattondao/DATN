/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DangNhap_DK;

import Admin.AdminForm;
import AfterLogin.chude;
import DangNhap_DK.dangKi_Fr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.util.regex.*;
/**
 *
 * @author Admin
 */
public class DangNhap_Frame extends javax.swing.JFrame {

    /**
     * Creates new form DangNhap_Frame
     */
    public DangNhap_Frame() {
        initComponents();
        createAndShowGUI();
    }
    
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    private static final String ADMIN_EMAIL = "bodysafe77@gmail.com";
    private static final String ADMIN_PASSWORD = "Botgymdambodysafe.";

    public void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(null);

        Color bgColor = new Color(160, 50, 50);
        getContentPane().setBackground(bgColor);
        URL imageUrl = DangNhap_Frame.class.getResource("/img/logo.png");
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image img = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        originalIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(originalIcon);
        logoLabel.setBounds(200, 160, 500, 500);
        add(logoLabel);

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(800, 160, 600, 500);
        loginPanel.setBackground(new Color(190, 100, 100));
        loginPanel.setLayout(null);
        add(loginPanel);

        JLabel titleLabel = new JLabel("Đăng nhập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(220, 20, 200, 40);
        loginPanel.add(titleLabel);

        JLabel emailLabel = new JLabel("Email hoặc User");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setBounds(50, 80, 200, 30);
        loginPanel.add(emailLabel);
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setBounds(50, 110, 500, 40);
        loginPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Mật Khẩu");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setBounds(50, 170, 200, 30);
        loginPanel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBounds(50, 200, 500, 40);
        loginPanel.add(passwordField);

        JCheckBox showPassword = new JCheckBox("Hiển thị mật khẩu");
        showPassword.setBackground(new Color(190, 100, 100));
        showPassword.setBounds(50, 250, 200, 20);
        loginPanel.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBounds(225, 280, 150, 50);
        loginPanel.add(loginButton);

        JLabel forgotPasswordLabel = new JLabel("Quên mật khẩu?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        forgotPasswordLabel.setBounds(440, 250, 200, 20);
        loginPanel.add(forgotPasswordLabel);

        JLabel registerLabel = new JLabel("Bạn mới biết đến Body-Safe? ");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registerLabel.setBounds(160, 380, 250, 20);
        loginPanel.add(registerLabel);
        JLabel registerLink = new JLabel("Đăng Ký");
        registerLink.setFont(new Font("Arial", Font.BOLD, 16));
        registerLink.setForeground(Color.RED);
        registerLink.setBounds(380, 380, 100, 20);
        loginPanel.add(registerLink);
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                dangKi_Fr manDki = new dangKi_Fr();
                manDki.setVisible(true);
            }
        });

        loginButton.addActionListener(e -> {
            String identifier = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (identifier.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                String query = "SELECT User_ID,Roles FROM users WHERE (Username = ? OR Email = ?) AND Password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, identifier);
                    stmt.setString(2, identifier);
                    stmt.setString(3, password);
                    ResultSet rs = stmt.executeQuery();
                    int UserID = - 1;

                    if (rs.next()) {
                        String role = rs.getString("Roles");
                        setVisible(false);
                        AdminForm ad = new AdminForm();
                        if ("Admin".equalsIgnoreCase(role)) {
                            JOptionPane.showMessageDialog(ad, "Đăng nhập admin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            ad.setVisible(true);

                        } else {
                            JOptionPane.showMessageDialog(this, "Đăng nhập user thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            chude mhchinh = new chude();
                            dispose();
                            mhchinh.codegiaodien(UserID);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
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
            java.util.logging.Logger.getLogger(DangNhap_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
