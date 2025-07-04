package ChuDe_BaiTap.view;

import ChuDe_BaiTap.dao.BaitapDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ChuDe_BaiTap.model.Baitap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MH_Baitap_Long extends JFrame {

     private JPanel contentPanel;
    private JLabel txtTenBai, txtMucDo, txtSoSet, txtSoReps, txtCalos, imgLabel;
    private BaitapDao baitapDao;

    public MH_Baitap_Long(int baitapID) {
        setTitle("Bài tập");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        baitapDao = new BaitapDao(); // Khởi tạo đối tượng DAO

        // Panel chính với màu nền đỏ
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(160, 50, 50));
        mainPanel.setLayout(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Logo
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(0, 0, 130, 130);
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        // Tiêu đề "Bài tập"
        JLabel titleLabel = new JLabel("Bài tập", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel chứa thông tin bài tập
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(800, 500));
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE); // Nền trắng để nổi bật hơn

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Khoảng cách giữa các thành phần
        gbc.anchor = GridBagConstraints.WEST; // Căn trái nội dung

        // Các nhãn thông tin bài tập
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblBai = new JLabel("Bài:");
        txtTenBai = new JLabel();
        contentPanel.add(lblBai, gbc);
        gbc.gridx = 1;
        contentPanel.add(txtTenBai, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblMucDo = new JLabel("Mức độ:");
        txtMucDo = new JLabel();
        contentPanel.add(lblMucDo, gbc);
        gbc.gridx = 1;
        contentPanel.add(txtMucDo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblSoSet = new JLabel("Số SET:");
        txtSoSet = new JLabel();
        contentPanel.add(lblSoSet, gbc);
        gbc.gridx = 1;
        contentPanel.add(txtSoSet, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblSoReps = new JLabel("Số REPS:");
        txtSoReps = new JLabel();
        contentPanel.add(lblSoReps, gbc);
        gbc.gridx = 1;
        contentPanel.add(txtSoReps, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblCalo = new JLabel("Calo:");
        txtCalos = new JLabel();
        contentPanel.add(lblCalo, gbc);
        gbc.gridx = 1;
        contentPanel.add(txtCalos, gbc);

        // Hình ảnh minh họa bài tập
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(200, 150)); // Đặt kích thước ảnh
        contentPanel.add(imgLabel, gbc);

        loadChiTietBaiTap(baitapID); // Tải thông tin bài tập theo ID

        // Nút "Bắt đầu"
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Không tràn vào ảnh
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnStart = new JButton("Bắt đầu");
        btnStart.setBackground(Color.RED);
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(btnStart, gbc);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng màn hình hiện tại
                new manhoanthanh().createAndShowGUI();
            }
        });
        
        // Căn giữa contentPanel trong mainPanel
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false); // Làm trong suốt để giữ màu nền mainPanel
        wrapperPanel.add(contentPanel);

        // Thêm vào mainPanel
        mainPanel.add(wrapperPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void loadChiTietBaiTap(int baitapID) {
        Baitap baitap = baitapDao.getBaitapById(baitapID); // Lấy bài tập theo ID
        if (baitap != null) {
            txtTenBai.setText(baitap.getTenBaitap());
            txtMucDo.setText(baitap.getMucDo());
            txtSoSet.setText(String.valueOf(baitap.getSoSet()));
            txtSoReps.setText(String.valueOf(baitap.getSoRep()));
            txtCalos.setText(String.valueOf(baitap.getCalo()));

            // Hiển thị hình ảnh nếu có
            if (baitap.getHinhAnh() != null && !baitap.getHinhAnh().isEmpty()) {
                imgLabel.setIcon(new ImageIcon(new ImageIcon(baitap.getHinhAnh())
                        .getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
            } else {
                imgLabel.setIcon(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bài tập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setText("Bài tập");

        jButton1.setText("Bắt đầu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel1)))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(263, 263, 263)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MH_Baitap_Long.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MH_Baitap_Long.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MH_Baitap_Long.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MH_Baitap_Long.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              int baitapID = 3002; // Thay đổi ID bài tập ở đây nếu cần
            new MH_Baitap_Long(baitapID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
