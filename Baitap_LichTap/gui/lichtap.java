/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Baitap_LichTap.gui;

import AfterLogin.trangcanhan;
import ChuDe_BaiTap.view.manhoanthanh;
import ChuDe_BaiTap.view.trangchude;
import TEST.View.MH_Dinh_Duong;
import dao.BaitapDao;
import dao.StreakDao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import model.Baitap;
import model.Streak;

/**
 *
 * @author PC
 */
public class lichtap extends javax.swing.JFrame {
    ArrayList<Streak> listStreak = new ArrayList<>();
    private JButton[] weekButtons = new JButton[7];
    JPanel contentLichTap = new JPanel();
    JPanel contentPanel = new JPanel();
    ArrayList<JPanel> danhSachBuoiTap = new ArrayList<>();
    private int currentPage = 1;
    private int itemsPerPage = 4;
    /**
     * Creates new form chude
     */
    public lichtap(int User) {
        hienthiStreak(User);
        codegiaodien(User);
    }
    
    public void codegiaodien(int User){
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

        // Trên
        contentLichTap.setBackground(new Color(150, 50, 50));
        contentLichTap.setBounds(300, 0, 1620, 160);
        contentLichTap.setLayout(null);
        
        // Nội dung chính
        contentPanel.setBackground(new Color(150, 50, 50));
        contentPanel.setBounds(300, 160, 1620, 920);
        contentPanel.setLayout(null);
        
        // Logo
        {ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Baitap_LichTap/logo/anhlogo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(20, 20, 100, 100);
        sidebar.add(logoLabel);}
        
        //streak
        {JPanel hinhanhstreak = new JPanel();
        hinhanhstreak.setBackground(new Color(150, 50, 50));
        hinhanhstreak.setBounds(50, 170, 180, 180);
        hinhanhstreak.setLayout(null);
        sidebar.add(hinhanhstreak);
        
        ImageIcon streakImgIcon = new ImageIcon(getClass().getResource("/Baitap_LichTap/logo/Streak.png"));
        Image streakImg = streakImgIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon streak = new ImageIcon(streakImg);
        JLabel streakJL = new JLabel(streak);
        streakJL.setBounds(0, 0, 180, 180);
        hinhanhstreak.add(streakJL);
        
//        JLabel baistreak = new JLabel(""+listStreak.get(0).getStreak());
//        baistreak.setForeground(Color.WHITE);
//        baistreak.setFont(new Font("Arial", Font.BOLD, 35));
//        baistreak.setBounds(70, 120, 200, 50);
//        hinhanhstreak.add(baistreak);
//        hinhanhstreak.setComponentZOrder(baistreak, 0);
        }
        
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
        
        
        
        //lich tap
        {JPanel lichtap = new JPanel();
        lichtap.setBackground(new Color(150, 50, 50));
        lichtap.setBounds(0, 80, 745, 55);
        lichtap.setLayout(null);
        contentLichTap.add(lichtap);
        
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
                    contentPanel.removeAll(); // Xóa nội dung cũ trước khi cập nhật

                    ArrayList<Baitap> danhSachBaiTap = BaitapDao.getBaiTapByWeek(User, selectedWeek);
                    int start = (currentPage - 1) * itemsPerPage; 
                    // Panel đầu tiên tương ứng với phần từ r đầu tiên trong List chuyên đề
                    int end = Math.min(start + itemsPerPage, danhSachBaiTap.size());
                    for (int i = start; i < end; i++) {
                        Baitap baitap = danhSachBaiTap.get(i);

                        // Chỉ thêm một panel cho mỗi bài tập
                        int x = (i % 2 == 0) ? 0 : 620;  // Cột trái hoặc phải
                        int y = (i < 2) ? 10 : 320;      // Hàng trên hoặc dưới

                        JPanel buoiPanel = taoPanelBuoiTap(baitap, x, y,User, selectedWeek);
                        contentPanel.add(buoiPanel);
                    }
                    // Cập nhật lại giao diện sau khi thay đổi
                    contentPanel.revalidate();
                    contentPanel.repaint();

                }
            });

            bangtuan.add(weekButtons[i]);
            xPos += 70; // Dịch chuyển vị trí của nút tiếp theo
        }

        setVisible(true);
        }
        
        //buoi 1
        contentPanel.add(createBuoiTapPanel("Buổi 1", "Dễ", "/Baitap_LichTap/logo/hinhanh.png", 0, 10,User));
        contentPanel.add(createBuoiTapPanel("Buổi 2", "Trung bình", "/Baitap_LichTap/logo/hinhanh.png", 620, 10,User));
        contentPanel.add(createBuoiTapPanel("Buổi 3", "Khó", "/Baitap_LichTap/logo/hinhanh.png", 0, 320,User));
        contentPanel.add(createBuoiTapPanel("Buổi 4", "Dễ", "/Baitap_LichTap/logo/hinhanh.png", 620, 320,User));

        menu(contentLichTap,User);
        add(sidebar);
        add(contentPanel);
        add(contentLichTap);
    }
    
    public void menu(JPanel menuPanel,int Userid){
    // Menu chính
    JButton menuButton = new JButton("☰");
    menuButton.setBounds(1150, 20, 50, 50);
    menuButton.setFont(new Font("Arial", Font.BOLD, 25));
    menuButton.setBackground(Color.BLACK);
    menuButton.setForeground(Color.WHITE);
    menuPanel.add(menuButton);

    JPopupMenu menu = new JPopupMenu();
    menu.setBackground(Color.DARK_GRAY);
    menu.setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền
    menu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

    JMenuItem closeItem = new JMenuItem("                X");
    JMenuItem accountItem = new JMenuItem("Tài khoản");
    JMenuItem LichTapItem = new JMenuItem("Lịch tập");
    JMenuItem topicItem = new JMenuItem("Chủ đề");
    JMenuItem foodItem = new JMenuItem("Thực phẩm");
    
    accountItem.addActionListener(e -> {
            dispose();
            trangcanhan tcn = new trangcanhan(Userid);
            tcn.codegiaodien(Userid);
        });

        LichTapItem.addActionListener(e -> {
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


    // Thêm vào menu
    menu.add(closeItem);
    menu.addSeparator();
    JMenuItem[] menuItems = {accountItem, LichTapItem, topicItem, foodItem};

    for (int i = 0; i < menuItems.length; i++) {
        JMenuItem item = menuItems[i];
        item.setBackground(Color.GRAY);
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Arial", Font.BOLD, 16));
        item.setOpaque(true);
        item.setBorderPainted(false); // Bỏ viền
        item.setMargin(new Insets(5, 10, 5, 10)); // Giữ khoảng cách hợp lý

        // Hiệu ứng hover
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item.setBackground(Color.LIGHT_GRAY);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item.setBackground(Color.GRAY);
            }
        });

        menu.add(item);
        if (i < menuItems.length - 1) {
            menu.addSeparator();
        }
    }

    menuButton.addActionListener(e -> menu.show(menuButton, 0, menuButton.getHeight()));
    closeItem.addActionListener(e -> menu.setVisible(false));

    // Menu thông báo
    JButton menuButton1 = new JButton("!");
    menuButton1.setBounds(1050, 20, 50, 50);
    menuButton1.setFont(new Font("Arial", Font.BOLD, 25));
    menuButton1.setBackground(Color.BLACK);
    menuButton1.setForeground(Color.WHITE);
    menuPanel.add(menuButton1);

    JPopupMenu thongbaomenu = new JPopupMenu();
    thongbaomenu.setBackground(Color.DARK_GRAY);
    thongbaomenu.setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền
    thongbaomenu.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));// Bỏ khoảng cách

    JMenuItem close = new JMenuItem("                X");
    JMenuItem lich = new JMenuItem("Lịch tập");

    thongbaomenu.add(close);
    thongbaomenu.addSeparator();
    thongbaomenu.add(lich);

    menuButton1.addActionListener(e -> thongbaomenu.show(menuButton1, 0, menuButton1.getHeight()));
}



    
    public void hienthiStreak(int userID){
        listStreak = StreakDao.StreakByID(userID);
    }
    
    private JPanel createBuoiTapPanel(String tenBuoi, String mucDo, String hinhAnhPath, int x, int y,int UserID) {
        JPanel bangbuoitap = new JPanel();
        bangbuoitap.setBackground(new Color(200, 80, 80));
        bangbuoitap.setBounds(x, y, 600, 290);
        bangbuoitap.setLayout(null);

        JLabel buoi = new JLabel(tenBuoi + ": bài tập");
        buoi.setBounds(50, 55, 300, 35);
        buoi.setForeground(Color.WHITE);
        buoi.setFont(new Font("Arial", Font.BOLD, 30));
        bangbuoitap.add(buoi);

        JLabel mucdoLabel = new JLabel("Mức độ: " + mucDo);
        mucdoLabel.setBounds(50, 105, 300, 35);
        mucdoLabel.setForeground(Color.WHITE);
        mucdoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bangbuoitap.add(mucdoLabel);

        JPanel hinhanh = new JPanel();
        hinhanh.setBackground(Color.BLACK);
        hinhanh.setBounds(310, 0, 290, 290);
        hinhanh.setLayout(null);
        bangbuoitap.add(hinhanh);

        ImageIcon minhhaIcon = new ImageIcon(getClass().getResource(hinhAnhPath));
        Image minhhaoanh = minhhaIcon.getImage().getScaledInstance(290, 290, Image.SCALE_SMOOTH);
        JLabel hinhanhmh = new JLabel(new ImageIcon(minhhaoanh));
        hinhanhmh.setBounds(0, 0, 290, 290);
        hinhanh.add(hinhanhmh);

        JButton startButton = new JButton("Bắt đầu");
        startButton.setBounds(85, 200, 120, 40);
        bangbuoitap.add(startButton);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng màn hình hiện tại
                baitaptheolichtap btlt = new baitaptheolichtap(UserID,1);
            }
        });
        
        return bangbuoitap;
    }
    
    private JPanel taoPanelBuoiTap(Baitap baitap, int x, int y,int User,int selectedWeek) {
        JPanel bangbuoitap = new JPanel();
        bangbuoitap.setBackground(new Color(200, 80, 80));
        bangbuoitap.setBounds(x, y, 600, 290);
        bangbuoitap.setLayout(null);

        JLabel buoi = new JLabel("Buổi " + baitap.getBaitapID() + ": " + baitap.getTenBaitap());
        buoi.setBounds(50, 55, 300, 35);
        buoi.setForeground(Color.WHITE);
        buoi.setFont(new Font("Arial", Font.BOLD, 30));
        bangbuoitap.add(buoi);

        JLabel mucdo = new JLabel("Mức độ: " + baitap.getMucDo());
        mucdo.setBounds(50, 105, 300, 35);
        mucdo.setForeground(Color.WHITE);
        mucdo.setFont(new Font("Arial", Font.BOLD, 20));
        bangbuoitap.add(mucdo);

        ImageIcon ThapIcon = new ImageIcon(baitap.getHinhAnh()); // Lấy đường dẫn ảnh từ DB
        Image ThapImage = ThapIcon.getImage().getScaledInstance(290, 290, Image.SCALE_SMOOTH);
        JLabel hinhanhLabel = new JLabel(new ImageIcon(ThapImage)); // Thêm hình vào JLabel
        hinhanhLabel.setBounds(0, 0, 290, 290);

        JPanel hinhanh1 = new JPanel();
        hinhanh1.setBackground(new Color(0, 0, 0));
        hinhanh1.setBounds(310, 0, 290, 290);
        hinhanh1.setLayout(null);
        hinhanh1.add(hinhanhLabel); // Thêm JLabel vào JPanel

        bangbuoitap.add(hinhanh1);

        
        JButton startButton = new JButton("Bắt đầu");
        startButton.setBounds(85, 200, 120, 40);
        bangbuoitap.add(startButton);
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new baitaptheolichtap(1,1).setVisible(true);
                dispose();
            }
        });

        return bangbuoitap;
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
            java.util.logging.Logger.getLogger(lichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lichtap(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
