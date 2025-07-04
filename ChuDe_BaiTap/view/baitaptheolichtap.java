/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ChuDe_BaiTap.view;

import ChuDe_BaiTap.dao.BaitapDao;
import ChuDe_BaiTap.dao.StreakDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import ChuDe_BaiTap.model.Baitap;
import ChuDe_BaiTap.model.Streak;

/**
 *
 * @author PC
 */
public class baitaptheolichtap extends javax.swing.JFrame {
    ArrayList<Streak> listStreak = new ArrayList<>();
    private JButton[] weekButtons = new JButton[7];
    ArrayList<JPanel> danhSachBuoiTap = new ArrayList<>();
    JPanel contentLichTap = new JPanel();
    JPanel contentPanel = new JPanel();
    private int currentPage = 1;
    private int itemsPerPage = 4;
    /**
     * Creates new form chude
     */
    public baitaptheolichtap(int User,int selectedWeek) {
        hienthiStreak(User);
        codegiaodien(User,selectedWeek);
    }
    
    public void codegiaodien(int User,int selectedWeek){
        setTitle("Home Gym Tracker");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Sidebar bên trái
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(150, 50, 50));
        sidebar.setBounds(0, 0, 300, 1080);
        sidebar.setLayout(null);

        // Nội dung chính
        contentLichTap.setBackground(new Color(150, 50, 50));
        contentLichTap.setBounds(300, 0, 1620, 160);
        contentLichTap.setLayout(null);
        
        // Nội dung chính
        contentPanel.setBackground(new Color(150, 50, 50));
        contentPanel.setBounds(300, 160, 1620, 920);
        contentPanel.setLayout(null);
        
        // Logo
        {ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
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
        
        ImageIcon streakImgIcon = new ImageIcon(getClass().getResource("/img/Streak.png"));
        Image streakImg = streakImgIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon streak = new ImageIcon(streakImg);
        JLabel streakJL = new JLabel(streak);
        streakJL.setBounds(0, 0, 180, 180);
        hinhanhstreak.add(streakJL);
        
        JLabel baistreak = new JLabel(""+listStreak.get(0).getStreak());
        baistreak.setForeground(Color.WHITE);
        baistreak.setFont(new Font("Arial", Font.BOLD, 35));
        baistreak.setBounds(70, 120, 200, 50);
        hinhanhstreak.add(baistreak);
        hinhanhstreak.setComponentZOrder(baistreak, 0);
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
        
        
        JPanel buoitap = new JPanel();
        buoitap.setBackground(new Color(150, 50, 50));
        buoitap.setBounds(440, 50, 170, 100);
        buoitap.setLayout(null);
        contentLichTap.add(buoitap);
        
        JLabel tieudebuoitap = new JLabel("Buổi tập: "+listStreak.get(0).getStreakId());
        tieudebuoitap.setForeground(Color.WHITE);
        tieudebuoitap.setFont(new Font("Arial", Font.BOLD, 30));
        tieudebuoitap.setBounds(0, 0, 200, 50);
        buoitap.add(tieudebuoitap);
        
        JLabel thoigian = new JLabel(""+listStreak.get(0).getStartDate());
        thoigian.setForeground(Color.WHITE);
        thoigian.setFont(new Font("Arial", Font.BOLD, 20));
        thoigian.setBounds(25, 30, 200, 50);
        buoitap.add(thoigian);
        
        JLabel ngay = new JLabel("Ngày:th7");
        ngay.setForeground(Color.WHITE);
        ngay.setFont(new Font("Arial", Font.BOLD, 20));
        ngay.setBounds(25, 55, 200, 50);
        buoitap.add(ngay);
        //buoi 1
        {ArrayList<Baitap> danhSachBaiTap = BaitapDao.getBaiTapByWeek(User, selectedWeek);
            int start = (currentPage - 1) * itemsPerPage; 
            // Panel đầu tiên tương ứng với phần từ r đầu tiên trong List chuyên đề
            int end = Math.min(start + itemsPerPage, danhSachBaiTap.size());
            for (int i = start; i < end; i++) {
                Baitap baitap = danhSachBaiTap.get(i);

                // Chỉ thêm một panel cho mỗi bài tập
                int x = (i % 2 == 0) ? 0 : 620;  // Cột trái hoặc phải
                int y = (i < 2) ? 10 : 320;      // Hàng trên hoặc dưới

                JPanel buoiPanel = taoPanelBuoiTap(baitap, x, y);
                contentPanel.add(buoiPanel);
            }
        }
        
        menu(contentLichTap);
        add(sidebar);
        add(contentPanel);
        add(contentLichTap);
    }
    
    public void menu(JPanel menuPanel){
        //menu
        JButton menuButton = new JButton("☰");
        menuButton.setBounds(1150, 20, 50, 50);
        menuButton.setFont(new Font("Arial", Font.BOLD, 25));
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
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
        
        //menu thong bao
        JButton menuButton1 = new JButton("!");
        menuButton1.setBounds(1050, 20, 50, 50);
        menuButton1.setFont(new Font("Arial", Font.BOLD, 25));
        menuButton1.setBackground(Color.BLACK);
        menuButton1.setForeground(Color.WHITE);
        menuPanel.add(menuButton1);

        JPopupMenu thongbaomenu = new JPopupMenu();
        JMenuItem close = new JMenuItem("                X");
        JMenuItem lich = new JMenuItem("Lịch tập");
        thongbaomenu.add(close);
        thongbaomenu.add(lich);
        
        menuButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thongbaomenu.show(menuButton1, 0, menuButton1.getHeight());
            }
        });
    }
    
    public void hienthiStreak(int userID){
        listStreak = StreakDao.StreakByID(userID);
    }
    
    private JPanel taoPanelBuoiTap(Baitap baitap, int x, int y) {
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

        JPanel hinhanh = new JPanel();
        hinhanh.setBackground(new Color(0, 0, 0));
        hinhanh.setBounds(310, 0, 290, 290);
        hinhanh.setLayout(null);
        bangbuoitap.add(hinhanh);

        // Load hình ảnh bài tập
//        ImageIcon minhhaIcon = new ImageIcon(getClass().getResource(baitap.getHinhAnh()));
//        Image minhhaoanh = minhhaIcon.getImage().getScaledInstance(290, 290, Image.SCALE_SMOOTH);
//        JLabel hinhanhmh = new JLabel(new ImageIcon(minhhaoanh));
//        hinhanhmh.setBounds(0, 0, 290, 290);
//        hinhanh.add(hinhanhmh);

        JButton startButton = new JButton("Bắt đầu");
        startButton.setBounds(85, 200, 120, 40);
        bangbuoitap.add(startButton);

        return bangbuoitap;
    }
    
//    private void loadPage() {
//        contentPanel.removeAll();
//        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//        int start = (currentPage - 1) * itemsPerPage; 
//        // Panel đầu tiên tương ứng với phần từ r đầu tiên trong List chuyên đề
//        int end = Math.min(start + itemsPerPage, chuyendeList.size());
//        // Trang cuối có thể không full 4 chuyên đề nên phải lấy min
//        // Thêm chuyên đề vào panel
//        for (int i = start; i < end; i++) {
//            ChuyenDe cd = chuyendeList.get(i);
//            contentPanel.add(createChuyendePanel(cd));
//        }
//        // Nếu trang cuối có ít hơn 4 chuyên đề, thêm các panel rỗng để giữ layout
//        int emptyPanels = itemsPerPage - (end - start);
//        for (int i = 0; i < emptyPanels; i++) {
//            contentPanel.add(new JPanel()); // Panel rỗng để giữ bố cục 2x2
//        }
//
//        pageLabel.setText("Page: " + currentPage);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }
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
            java.util.logging.Logger.getLogger(baitaptheolichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(baitaptheolichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(baitaptheolichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(baitaptheolichtap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new baitaptheolichtap(1,1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
