/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DangNhap_DK;

import CacLoaiTheTrang.ViewChoTT.MH_13;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.sql.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Admin
 */
public class dangKi_Fr extends javax.swing.JFrame {

    /**
     * Creates new form dangKi_Fr
     */
    public dangKi_Fr() {
        initComponents();
        RegisterForm();
    }

    private JTextField txtUsername, txtEmail, txtAge;
    private JPasswordField txtPassword;
    private JRadioButton maleButton, femaleButton, otherButton;
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Body_Safe;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    private static final String EMAIL_SENDER = "daonhatton517@gmail.com";
    private static final String EMAIL_PASSWORD = "nwuxxrtuqtdtzbwl";

    private static final String ADMIN_EMAIL = "bodysafe77@gmail.com";
    private static final String ADMIN_PASSWORD = "Botgymdambodysafe.";

    public void RegisterForm() {
        setTitle("Body-Safe Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(160, 50, 50));

        URL imageUrl = dangKi_Fr.class.getResource("/img/logo.png");
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image img = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        originalIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(originalIcon);
        logoLabel.setBounds(200, 160, 500, 500);
        add(logoLabel);

        // Panel chứa form đăng ký
        JPanel formPanel = new JPanel();
        formPanel.setBounds(770, 220, 670, 450);
        formPanel.setBackground(new Color(190, 100, 100));
        formPanel.setLayout(null);  // Dùng layout null để tự do điều chỉnh vị trí các thành phần
        add(formPanel); // Thêm vào JFrame

// Tiêu đề
        JLabel lblTitle = new JLabel("Đăng ký", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(240, 20, 200, 30); // Đã điều chỉnh lại vị trí của tiêu đề
        formPanel.add(lblTitle);

// Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 70, 100, 30);
        formPanel.add(lblUsername);

        txtUsername = new JTextField(20);
        txtUsername.setBounds(150, 70, 450, 30); // Đã điều chỉnh lại kích thước của JTextField
        formPanel.add(txtUsername);

// Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 120, 100, 30);
        formPanel.add(lblEmail);

        txtEmail = new JTextField(20);
        txtEmail.setBounds(150, 120, 450, 30); // Điều chỉnh lại kích thước của JTextField
        formPanel.add(txtEmail);

// Tuổi
        JLabel lblAge = new JLabel("Tuổi:");
        lblAge.setBounds(50, 170, 100, 30);
        formPanel.add(lblAge);

        txtAge = new JTextField(20);
        txtAge.setBounds(150, 170, 450, 30); // Điều chỉnh lại kích thước của JTextField
        formPanel.add(txtAge);

// Mật khẩu
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setBounds(50, 220, 100, 30);
        formPanel.add(lblPassword);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(150, 220, 450, 30); // Điều chỉnh lại kích thước của JTextField
        formPanel.add(txtPassword);

// Hiện mật khẩu
        JCheckBox showPassword = new JCheckBox("Hiện mật khẩu");
        showPassword.setBounds(150, 260, 200, 30);
        showPassword.setBackground(new Color(190, 100, 100));
        showPassword.addActionListener(e -> txtPassword.setEchoChar(showPassword.isSelected() ? '\0' : '*'));
        formPanel.add(showPassword);

// Giới tính
        JLabel lblGender = new JLabel("Giới tính:");
        lblGender.setBounds(50, 300, 100, 30);
        formPanel.add(lblGender);

        maleButton = new JRadioButton("Nam");
        femaleButton = new JRadioButton("Nữ");
        otherButton = new JRadioButton("Khác");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        maleButton.setBounds(150, 300, 60, 30);
        femaleButton.setBounds(210, 300, 60, 30);
        otherButton.setBounds(270, 300, 60, 30);

        formPanel.add(maleButton);
        formPanel.add(femaleButton);
        formPanel.add(otherButton);

// Nút đăng ký
        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.addActionListener(e -> validateForm(txtUsername, txtEmail, txtAge, txtPassword, maleButton, femaleButton, otherButton));

        JButton btnBack = new JButton("Quay lại");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                DangNhap_Frame dn = new DangNhap_Frame();
                dn.setVisible(true);
            }
        });
        
        btnRegister.setBounds(350, 350, 100, 30);
        btnBack.setBounds(210, 350, 100, 30);
        
        formPanel.add(btnRegister);
        formPanel.add(btnBack);  // Thêm btnBack vào formPanel
        setVisible(true);
    }

    private void validateForm(JTextField txtUsername, JTextField txtEmail, JTextField txtAge, JPasswordField txtPassword,
            JRadioButton maleButton, JRadioButton femaleButton, JRadioButton otherButton) {
        // Lấy thông tin từ các trường nhập liệu
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String ageText = txtAge.getText().trim();
        String gender = maleButton.isSelected() ? "Nam" : femaleButton.isSelected() ? "Nữ" : "Khác";
        String password = new String(txtPassword.getPassword()).trim(); // Lấy mật khẩu từ JPasswordField

        // Kiểm tra các trường bắt buộc
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập email!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra tuổi nhập vào là số hợp lệ
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tuổi phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kết nối đến cơ sở dữ liệu và kiểm tra nếu email đã tồn tại
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (isEmailExists(conn, email)) {
                JOptionPane.showMessageDialog(null, "Email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo mã xác nhận và gửi email
            String verificationCode = generateVerificationCode();
            sendEmail(email, verificationCode);

            String query = "INSERT INTO users (Username, Email, DoB, Password, Gender, Roles, Status) VALUES (?, ?, ?, ?, ?, 'User', 'HD')";
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setInt(3, age);
                stmt.setString(4, password); // Lưu mật khẩu vào cơ sở dữ liệu
                stmt.setString(5, gender);

                // Thực thi câu lệnh và lấy userID
                if (stmt.executeUpdate() > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userID = generatedKeys.getInt(1);

                        JOptionPane.showMessageDialog(null, "Đăng ký thành công! Kiểm tra email để xác nhận.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        MaXacNhan nhapma = new MaXacNhan();
                        nhapma.Nhapmaxacnhan(email, verificationCode, userID);
                        // Đóng frame đăng ký
                        dispose();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isEmailExists(Connection conn, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000)); // Tạo mã xác nhận ngẫu nhiên 6 chữ số
    }

    private void sendEmail(String recipient, String code) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_SENDER, EMAIL_PASSWORD); // Sử dụng thông tin email của bạn
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Mã xác nhận đăng ký");
            message.setText("Mã xác nhận của bạn là: " + code);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi gửi email: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(dangKi_Fr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dangKi_Fr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dangKi_Fr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dangKi_Fr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dangKi_Fr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
