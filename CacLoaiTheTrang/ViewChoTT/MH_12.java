/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CacLoaiTheTrang.ViewChoTT;

import CacLoaiTheTrang.dao.ThetrangcanhanDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import CacLoaiTheTrang.model.Thetrangcanhan;
import TEST.View.MH_2;


/**
 *
 * @author Admin
 */
public class MH_12 extends javax.swing.JFrame {
    ArrayList<Thetrangcanhan> listTheTrang = new ArrayList<>();
    /**
     * Creates new form MH_12
     */
    public MH_12(int UserID) {
        initComponents();
        mh12(UserID);
    }

    public void mh12(int userID) {
        setTitle("Height Input");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(150, 50, 50));

        // Logo
        ImageIcon logoIcon = new ImageIcon("F:\\CodeJava\\Body_Safe\\Picture\\snapedit_1741363788304.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogo);
        logoLabel.setBounds(20, 10, 80, 80);
        add(logoLabel);

        // Menu chính
        JButton menuButton = new JButton("☰");
        menuButton.setBounds(1250, 20, 40, 40);
        menuButton.setForeground(Color.BLACK);
        menuButton.setBorderPainted(false);
        add(menuButton);

        // Menu con
        JPopupMenu menuPopup = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Profile");
        JMenuItem menuItem2 = new JMenuItem("Settings");
        JMenuItem menuItem3 = new JMenuItem("Logout");

        menuPopup.add(menuItem1);
        menuPopup.add(menuItem2);
        menuPopup.add(menuItem3);

        menuButton.addActionListener(e -> menuPopup.show(menuButton, 0, menuButton.getHeight()));
        // Main Panel for Centering Content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(330, 204, 800, 400); // Căn giữa
        mainPanel.setBackground(new Color(150, 50, 50));
        add(mainPanel);

        JLabel titleLabel = new JLabel("WHAT'S YOUR CURRENT WEIGHT?");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBounds(180, 20, 480, 40);
        mainPanel.add(titleLabel);

        JLabel unitLabel = new JLabel("Units");
        unitLabel.setForeground(Color.WHITE);
        unitLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        unitLabel.setBounds(200, 80, 100, 30);
        mainPanel.add(unitLabel);

        JButton cmButton = new JButton("kg");
        cmButton.setBounds(300, 80, 60, 30);
        cmButton.setBackground(new Color(200, 200, 200));
        cmButton.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(cmButton);

        JLabel heightLabel = new JLabel("Weight (kg)");
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        heightLabel.setBounds(200, 140, 200, 30);
        mainPanel.add(heightLabel);

        JTextField weightField = new JTextField();
        weightField.setOpaque(false);
        weightField.setBackground(new Color(255, 255, 255, 100)); // Điều chỉnh độ mờ
        weightField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        weightField.setForeground(Color.WHITE);
        weightField.setFont(new Font("Arial", Font.PLAIN, 18));
        weightField.setBounds(200, 180, 400, 40);
        mainPanel.add(weightField);

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(320, 250, 160, 50);
        continueButton.setBackground(Color.BLACK);
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(continueButton);
        continueButton.addActionListener(e -> {
            String weightText = weightField.getText().trim();
            if (weightText.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Vui lòng nhập Cân nặng của bạn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Float weightValue = Float.parseFloat(weightText);
                if (weightValue < 100 || weightValue > 300) {
                    JOptionPane.showMessageDialog(mainPanel, "Cân nặng phải nằm trong khoảng từ 100 đến 300 cm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Cân nặng đã được chấp nhận: " + weightValue + userID + " cm", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    boolean ketQua = ThetrangcanhanDao.updateWeight(weightValue, userID);
                    if (ketQua) {
                        listTheTrang = ThetrangcanhanDao.ThetrangByID(userID);
                    }
                    dispose();
                    MH_2 mh2 = new MH_2();
                    mh2.initUI(userID);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "cân nặng phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(MH_12.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MH_12.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MH_12.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MH_12.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MH_12(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
