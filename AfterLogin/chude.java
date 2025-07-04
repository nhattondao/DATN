/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AfterLogin;

import Baitap_LichTap.gui.lichtap;
import CacLoaiTheTrang.dao.BaitapDao;
import java.awt.BasicStroke;
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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import CacLoaiTheTrang.model.Baitap;
import ChuDe_BaiTap.view.trangchude;
import TEST.View.MH_Dinh_Duong;
import java.net.URL;

/**
 *
 * @author PC
 */
public class chude extends javax.swing.JFrame {

    private JButton[] weekButtons = new JButton[7];
    int UserID;

    /**
     * Creates new form chude
     */
    public chude() {
        //codegiaodien();
    }

    public void codegiaodien(int UserID) {
        setTitle("Home Gym Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Sidebar bên trái
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(150, 50, 50));
        sidebar.setBounds(0, 0, 300, 1080);
        sidebar.setLayout(null);

        JLabel titleLabel = new JLabel("Chào mừng bạn đến với Body Safe");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBounds(450, 65, 900, 40);
        add(titleLabel);

        // Logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 10, 180, 180);
        sidebar.add(logoLabel);

        // KCAL
        JLabel kcalLabel = new JLabel("KCAL: 1000");
        kcalLabel.setForeground(Color.WHITE);
        kcalLabel.setFont(new Font("Arial", Font.BOLD, 30));
        kcalLabel.setBounds(50, 370, 200, 50);
        sidebar.add(kcalLabel);

        //Bai tập đã hoàn thành
        JLabel workoutsLabel = new JLabel("Bài đã tập: 1");
        workoutsLabel.setForeground(Color.WHITE);
        workoutsLabel.setFont(new Font("Arial", Font.BOLD, 30));
        workoutsLabel.setBounds(50, 430, 200, 50);
        sidebar.add(workoutsLabel);

        // Nội dung chính
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(150, 50, 50));
        contentPanel.setBounds(300, 0, 1620, 1080);
        contentPanel.setLayout(null);

        {
            JPanel lichtap = new JPanel();
            lichtap.setBackground(new Color(150, 50, 50));
            lichtap.setBounds(0, 160, 745, 55);
            lichtap.setLayout(null);
            contentPanel.add(lichtap);

            JLabel textlichtap = new JLabel("Lịch tập");
            textlichtap.setBounds(0, 12, 300, 30);
            textlichtap.setForeground(Color.WHITE);
            textlichtap.setFont(new Font("Arial", Font.BOLD, 25));
            lichtap.add(textlichtap);

            JPanel bangtuan = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Vẽ nền bo tròn
                    g2d.setColor(new Color(200, 80, 80)); // Màu nền
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bo góc 30px

                    // Vẽ viền
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                }
            };

            bangtuan.setBounds(105, 0, 640, 55);
            bangtuan.setOpaque(false); // Để nền trong suốt
            bangtuan.setLayout(null);
            lichtap.add(bangtuan);

            JLabel week = new JLabel("Week");
            week.setBounds(30, 15, 300, 30);
            week.setForeground(Color.WHITE);
            week.setFont(new Font("Arial", Font.BOLD, 25));
            bangtuan.add(week);

            JPanel thanhgach = new JPanel();
            thanhgach.setBackground(Color.GREEN);
            bangtuan.add(thanhgach);

            // **Tạo các nút số tuần (1 - 7)**
            int xPos = 120; // Điều chỉnh vị trí ban đầu của số 1
            for (int i = 0; i < 7; i++) {
                int weekNum = i + 1;
                weekButtons[i] = new JButton(String.valueOf(weekNum));
                weekButtons[i].setBounds(xPos, 15, 40, 30); // Điều chỉnh kích thước để dễ đọc hơn
                weekButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
                weekButtons[i].setForeground(Color.WHITE);
                weekButtons[i].setBorder(null);
                weekButtons[i].setFocusPainted(false);
                weekButtons[i].setContentAreaFilled(false);

                final int selectedWeek = i + 1;

                // **Bắt sự kiện khi nhấn vào tuần**
                weekButtons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        thanhgach.setBounds(125 + (selectedWeek - 1) * 70, 45, 30, 5);

                    }
                });
                bangtuan.add(weekButtons[i]);
                xPos += 70; // Dịch chuyển vị trí của nút tiếp theo
            }

            setVisible(true);
        }

        JPanel levelsPanel = new JPanel();
        levelsPanel.setBackground(new Color(150, 50, 50));
        levelsPanel.setBounds(0, 150, 1200, 650);
        levelsPanel.setLayout(null);

        // Panel Bắt đầu
        // Panel Trung bình
        JPanel TrungBinhPanel = new JPanel();
        TrungBinhPanel.setBackground(new Color(200, 80, 80));
        TrungBinhPanel.setBounds(0, 130, 1200, 200);
        TrungBinhPanel.setLayout(null);

        JLabel intermediateLabel = new JLabel("Bài tập gợi ý");
        intermediateLabel.setForeground(Color.WHITE);
        intermediateLabel.setFont(new Font("Arial", Font.BOLD, 22));
        intermediateLabel.setBounds(20, 10, 200, 30);
        TrungBinhPanel.add(intermediateLabel);
        levelsPanel.add(TrungBinhPanel);

        // Hiển thị 4 ảnh ngang hàng
        ArrayList<Baitap> danhSachTrungBinh = BaitapDao.BaiTapByMucDo("Trung binh");
        int TrungBinhX = 20;
        for (int i = 0; i < danhSachTrungBinh.size() && i < 4; i++) {
            Baitap baitap = danhSachTrungBinh.get(i);

            // Tạo JButton cho hình ảnh
            JButton imgButton = createHinhAnh(baitap, TrungBinhX, i);

            // Xử lý sự kiện khi nhấn vào ảnh
            final int index = i + 1;
            imgButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Bạn đã chọn ảnh " + index);
                }
            });
            TrungBinhPanel.add(imgButton);

        }

        // Panel Nâng cao
        JPanel NangCaoPanel = new JPanel();
        NangCaoPanel.setBackground(new Color(180, 60, 60));
        NangCaoPanel.setBounds(0, 350, 1200, 250);
        NangCaoPanel.setLayout(null);

        JLabel advancedLabel = new JLabel("NÂNG CAO");
        advancedLabel.setForeground(Color.WHITE);
        advancedLabel.setFont(new Font("Arial", Font.BOLD, 22));
        advancedLabel.setBounds(20, 10, 200, 30);
        NangCaoPanel.add(advancedLabel);
        levelsPanel.add(NangCaoPanel);
        contentPanel.add(levelsPanel);

        // Hiển thị 4 ảnh ngang hàng
        ArrayList<Baitap> danhSachKho = BaitapDao.BaiTapByMucDo("Kho");
        int NangCaoX = 40;
        for (int i = 0; i < danhSachKho.size() && i < 4; i++) {
            Baitap baitap = danhSachKho.get(i);

            // Tạo JButton cho hình ảnh
            JPanel imgTextButton = createBaiTapPanel(baitap, NangCaoX, i);
            NangCaoPanel.add(imgTextButton);

        }

        menu(contentPanel, UserID);
        add(sidebar);
        add(contentPanel);
    }

    public void menu(JPanel menuPanel, int Userid) {
        String imagePath = "/img/image-removebg-preview.png"; // Đảm bảo đường dẫn đúng
        URL imageURL = getClass().getResource(imagePath);

        JButton menuButton; // Khai báo biến ngoài if để tránh lỗi

        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
            menuButton = new JButton(icon); // Thêm ảnh vào JButton
        } else {
            System.err.println("Không tìm thấy ảnh: " + imagePath);
            menuButton = new JButton("Menu"); // Hiển thị text thay vì ảnh nếu lỗi
        }

// Thiết lập thuộc tính cho JButton
        menuButton.setBounds(1130, 30, 80, 80);
        menuButton.setBackground(Color.GRAY);
        menuButton.setBorder(null); // Loại bỏ viền để đẹp hơn

        menuPanel.add(menuButton);

        JPopupMenu menu = new JPopupMenu();
        JMenuItem closeItem = new JMenuItem("                X");
        JMenuItem accountItem = new JMenuItem("Tài khoản");
        JMenuItem scheduleItem = new JMenuItem("Lịch tập");
        JMenuItem topicItem = new JMenuItem("Chủ đề");
        JMenuItem foodItem = new JMenuItem("Thực phẩm");

        menu.add(closeItem);
        menu.add(accountItem);
        menu.add(scheduleItem);
        menu.add(topicItem);
        menu.add(foodItem);

        accountItem.addActionListener(e -> {
            dispose();
            trangcanhan tcn = new trangcanhan(Userid);
            tcn.codegiaodien(Userid);
        });

        scheduleItem.addActionListener(e -> {
            dispose();
            trangchude tcd = new trangchude();
            
        });

        topicItem.addActionListener(e -> {
            dispose();
            lichtap lt = new lichtap(Userid);
        });

        foodItem.addActionListener(e -> {
            dispose();
            MH_Dinh_Duong dd = new MH_Dinh_Duong();
            dd.setVisible(true);
        });


        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.show(menuButton, 0, menuButton.getHeight());
            }
        });

        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
            }
        });
    }

    public JButton createHinhAnh(Baitap bt, int x, int i) {
        ImageIcon ThapIcon = new ImageIcon(bt.getHinhAnh()); // Lấy đường dẫn ảnh từ DB
        Image ThapImage = ThapIcon.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH);
        ImageIcon ThapImgIcon = new ImageIcon(ThapImage);

        JButton imgButton = new JButton(ThapImgIcon);
        imgButton.setBounds(x + (i * 300), 40, 200, 160);
        imgButton.setBorderPainted(false); // Loại bỏ viền
        imgButton.setContentAreaFilled(false); // Loại bỏ nền
        imgButton.setFocusPainted(false); // Loại bỏ viền khi nhấn
        imgButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Thay đổi con trỏ khi di chuột vào

        return imgButton;
    }

    public JButton createHinhAnhTron(Baitap bt) {
        ImageIcon icon = new ImageIcon(bt.getHinhAnh());
        Image image = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JButton button = new JButton(scaledIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
                    g2.setClip(shape);
                    super.paintComponent(g);
                }
            }
        };

        button.setPreferredSize(new Dimension(160, 160));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public JPanel createBaiTapPanel(Baitap bt, int x, int i) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setBounds(x + (i * 300), 40, 160, 200);

        // Tạo hình ảnh tròn
        JButton Text = createHinhAnhTron(bt);

        // Tạo label cho tên bài tập
        JLabel label = new JLabel(bt.getTenBaitap(), SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // Sắp xếp layout
        panel.add(Text, BorderLayout.CENTER);
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
            java.util.logging.Logger.getLogger(chude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chude.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chude().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
