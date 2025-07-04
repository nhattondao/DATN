/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DangNhap_DK;

import CacLoaiTheTrang.ViewChoTT.MH_13;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Admin
 */
public class MaXacNhan extends javax.swing.JFrame {

    /**
     * Creates new form MaXacNhan
     */
    public MaXacNhan() {
        initComponents();
    }

    private String email;
    private String correctCode;
    private JTextField codeField;
    private int UserID;

    public void Nhapmaxacnhan(String email, String correctCode, int IdUser) {
        this.email = email;
        this.correctCode = correctCode;
        this.UserID = IdUser;

        setTitle("Xác nhận mã");
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        getContentPane().setBackground(new Color(150, 50, 50));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(160, 50, 50));
        panel.setLayout(null);
        add(panel);

        JLabel titleLabel = new JLabel("Nhập mã xác nhận được gửi tới gmail của bạn");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(400, 260, 800, 50);
        panel.add(titleLabel);

        codeField = new JTextField();
        codeField.setFont(new Font("Arial", Font.PLAIN, 30));
        codeField.setBounds(600, 340, 400, 50);
        panel.add(codeField);

        JLabel resendLabel = new JLabel("Không nhận được mã? Gửi lại");
        resendLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        resendLabel.setForeground(Color.BLACK);
        resendLabel.setBounds(660, 400, 400, 40);
        panel.add(resendLabel);

        JButton backButton = new JButton("Quay lại");
        backButton.setFont(new Font("Arial", Font.BOLD, 28));
        backButton.setBounds(600, 460, 150, 60);
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dangKi_Fr dki = new dangKi_Fr();
            }
        });

        JButton sendButton = new JButton("Xác nhận");
        sendButton.setFont(new Font("Arial", Font.BOLD, 28));
        sendButton.setBounds(820, 460, 180, 60);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkVerificationCode();
            }
        });
        panel.add(sendButton);
        panel.setBounds(0, 0, 1920, 1080);

        revalidate();
        repaint();
        setVisible(true);
    }

    private void checkVerificationCode() {

        String enteredCode = codeField.getText();
        if (enteredCode.equals(correctCode)) {
            JOptionPane.showMessageDialog(this, "Xác nhận thành công! Tài khoản của bạn đã được kích hoạt.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            MH_13 mh = new MH_13(UserID);
            mh.setVisible(true);
            this.dispose(); // Nếu muốn đóng Nhapmaxacnhan
        } else {
            JOptionPane.showMessageDialog(this, "Mã xác nhận không chính xác. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(MaXacNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaXacNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaXacNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaXacNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaXacNhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
