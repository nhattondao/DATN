/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AfterLogin;

import CacLoaiTheTrang.dao.BaitapDao;
import CacLoaiTheTrang.dao.ThetrangcanhanDao;
import AfterLogin.UsersDao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import CacLoaiTheTrang.model.Baitap;
import CacLoaiTheTrang.model.Thetrangcanhan;
import AfterLogin.Users;

/**
 *
 * @author PC
 */
public class trangcanhan extends javax.swing.JFrame {
    JPanel bangbuoitap = new JPanel();
    private JButton[] weekButtons = new JButton[7];
    private int currentPage = 1;
    private int itemsPerPage = 1;

    public trangcanhan(int userID) {
        codegiaodien(userID);
    }

    
    public void codegiaodien(int userID){
        ArrayList<Thetrangcanhan> listTheTrang = ThetrangcanhanDao.ThetrangByID(userID);
        ArrayList<Users> listUsers = UsersDao.getUsersByID(userID);
        Baitap listBaiTap = BaitapDao.getBaitapById(userID);
        setTitle("Trang cá nhân");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(150, 50, 50));
        mainPanel.setLayout(null);
        
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(20, 20, 100, 100); // X=20, Y=20, width=100, height=100
        mainPanel.add(logoLabel);
        
        JPanel thoigiantap = new JPanel();
        thoigiantap.setBackground(new Color(150, 50, 50));
        thoigiantap.setBounds(350, 110, 900, 150);
        thoigiantap.setLayout(null);

        JPanel thetrang = new JPanel();
        thetrang.setBackground(new Color(150, 50, 50));
        thetrang.setBounds(40, 300, 500, 350);
        thetrang.setLayout(null);
        
        JPanel lichtap = new JPanel();
        lichtap.setBackground(new Color(150, 50, 50));
        lichtap.setBounds(650, 280, 700, 450);
        lichtap.setLayout(null);
        
        //bảng thời gian tập
        {JLabel tennguoidung = new JLabel(""+listUsers.get(0).getUsername());
        tennguoidung.setBounds(50, 120, 200, 30);
        tennguoidung.setForeground(Color.WHITE);
        tennguoidung.setFont(new Font("Arial", Font.BOLD, 20));
        thoigiantap.add(tennguoidung);
        
        JPanel avatar = new JPanel();
        avatar.setBackground(new Color(150, 50, 50));
        avatar.setBounds(50, 0, 120, 120);
        avatar.setLayout(null);
        thoigiantap.add(avatar);
        
        ImageIcon avatarImgIcon = new ImageIcon(getClass().getResource("/logo/avatar.png"));
        Image avatarImg = avatarImgIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon avatarIcon = new ImageIcon(avatarImg);
        JLabel avatarLabel = new JLabel(avatarIcon);
        avatarLabel.setBounds(0, 0, 120, 120); // X=20, Y=20, width=100, height=100
        avatar.add(avatarLabel);
        
        JLabel songaytap = new JLabel("2");
        songaytap.setBounds(295, 50, 100, 30);
        songaytap.setForeground(Color.WHITE);
        songaytap.setFont(new Font("Arial", Font.BOLD, 30));
        thoigiantap.add(songaytap);
        
        JLabel ngaytap = new JLabel("Ngày tập");
        ngaytap.setBounds(260, 80, 100, 30);
        ngaytap.setForeground(Color.WHITE);
        ngaytap.setFont(new Font("Arial", Font.BOLD, 20));
        thoigiantap.add(ngaytap);

        JLabel sobaidatap = new JLabel(""+listUsers.get(0).getBaiDaTap());
        sobaidatap.setBounds(430, 50, 100, 30);
        sobaidatap.setForeground(Color.WHITE);
        sobaidatap.setFont(new Font("Arial", Font.BOLD, 30));
        thoigiantap.add(sobaidatap);
        
        JLabel baidatap = new JLabel("Bài đã tập");
        baidatap.setBounds(420, 80, 100, 30);
        baidatap.setForeground(Color.WHITE);
        baidatap.setFont(new Font("Arial", Font.BOLD, 20));
        thoigiantap.add(baidatap);

        JLabel socalo = new JLabel("1000");
        socalo.setBounds(585, 50, 100, 30);
        socalo.setForeground(Color.WHITE);
        socalo.setFont(new Font("Arial", Font.BOLD, 30));
        thoigiantap.add(socalo);
        
        JLabel calo = new JLabel("Calories");
        calo.setBounds(580, 80, 100, 30);
        calo.setForeground(Color.WHITE);
        calo.setFont(new Font("Arial", Font.BOLD, 20));
        thoigiantap.add(calo);
        }
        
        //bảng thể trạng của bạn
        {JLabel thetrangcuaban = new JLabel("Thể trạng của bạn");
        thetrangcuaban.setBounds(30, 30, 300, 30);
        thetrangcuaban.setForeground(Color.WHITE);
        thetrangcuaban.setFont(new Font("Arial", Font.BOLD, 25));
        thetrang.add(thetrangcuaban);
        
        JLabel sochieucao = new JLabel("" + listTheTrang.get(0).getChieuCao()+ " cm");
        sochieucao.setBounds(40, 100, 200, 30);
        sochieucao.setForeground(Color.WHITE);
        sochieucao.setFont(new Font("Arial", Font.BOLD, 35));
        thetrang.add(sochieucao);
        
        JLabel chieucao = new JLabel("Chiều cao");
        chieucao.setBounds(40, 140, 200, 30);
        chieucao.setForeground(Color.WHITE);
        chieucao.setFont(new Font("Arial", Font.BOLD, 25));
        thetrang.add(chieucao);
        
        JLabel socannang = new JLabel("" + listTheTrang.get(0).getCanNang() + " kg");
        socannang.setBounds(40, 200, 200, 40);
        socannang.setForeground(Color.WHITE);
        socannang.setFont(new Font("Arial", Font.BOLD, 35));
        thetrang.add(socannang);
        
        JLabel cannang = new JLabel("Cân nặng");
        cannang.setBounds(40, 250, 200, 30);
        cannang.setForeground(Color.WHITE);
        cannang.setFont(new Font("Arial", Font.BOLD, 25));
        thetrang.add(cannang);
        
        JLabel fat = new JLabel(""+ listTheTrang.get(0).getLoaiCoThe());
        fat.setBounds(250, 90, 200, 40);
        fat.setForeground(Color.WHITE);
        fat.setFont(new Font("Arial", Font.BOLD, 35));
        thetrang.add(fat);
        
        JLabel fatbody = new JLabel("Body hiện tại");
        fatbody.setBounds(250, 125, 200, 30);
        fatbody.setForeground(Color.WHITE);
        fatbody.setFont(new Font("Arial", Font.BOLD, 25));
        thetrang.add(fatbody);
        
        JLabel fit = new JLabel(""+ listTheTrang.get(0).getCoTheMongMuon());
        fit.setBounds(250, 210, 200, 40);
        fit.setForeground(Color.WHITE);
        fit.setFont(new Font("Arial", Font.BOLD, 35));
        thetrang.add(fit);
        
        JLabel fitbody = new JLabel("Body mong muốn");
        fitbody.setBounds(250, 245, 300, 30);
        fitbody.setForeground(Color.WHITE);
        fitbody.setFont(new Font("Arial", Font.BOLD, 25));
        thetrang.add(fitbody);
        }
        
        //bảng hình minh họa
        {
        
        JLabel textlichtap = new JLabel("Lịch tập");
        textlichtap.setBounds(30, 12, 300, 30);
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

        bangtuan.setBounds(30, 50, 640, 55);
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

        bangbuoitap.setBackground(new Color(200, 80, 80));
        bangbuoitap.setBounds(30, 120, 640, 310);
        bangbuoitap.setLayout(null);
        lichtap.add(bangbuoitap);

        
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
                    bangbuoitap.removeAll();
                    bangbuoitap.revalidate();
                    bangbuoitap.repaint();// Xóa nội dung cũ trước khi cập nhật

                    ArrayList<Baitap> danhSachBaiTap = BaitapDao.getBaiTapByWeek(userID, selectedWeek);
                    int start = (currentPage - 1) * itemsPerPage; 
                    // Panel đầu tiên tương ứng với phần từ r đầu tiên trong List chuyên đề
                    int end = Math.min(start + itemsPerPage, danhSachBaiTap.size());
                    for (int i = start; i < end; i++) {
                        Baitap baitap = danhSachBaiTap.get(i);

                        JPanel buoiPanel = taoPanelBuoiTap(baitap);
                        bangbuoitap.add(buoiPanel);
                    }
                    // Cập nhật lại giao diện sau khi thay đổi
                    bangbuoitap.revalidate();
                    bangbuoitap.repaint();

                }
            });

            bangtuan.add(weekButtons[i]);
            xPos += 70; // Dịch chuyển vị trí của nút tiếp theo
        }

        setVisible(true);
        }
        
        
        add(mainPanel);
        mainPanel.add(thoigiantap);
        mainPanel.add(thetrang);
        mainPanel.add(lichtap);
    }

    
    private JPanel taoPanelBuoiTap(Baitap baitap) {
        JPanel panel = new JPanel(); // Tạo một panel mới, không dùng bangbuoitap
        panel.setBackground(new Color(200, 80, 80));
        panel.setBounds(0, 0, 650, 310);
        panel.setLayout(null);

        JLabel buoi = new JLabel("Buổi " + baitap.getBaitapID() + ": " + baitap.getTenBaitap());
        buoi.setBounds(30, 55, 300, 35);
        buoi.setForeground(Color.WHITE);
        buoi.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(buoi); // Thêm vào panel mới

        JLabel mucdo = new JLabel("Mức độ: " + baitap.getMucDo());
        mucdo.setBounds(30, 105, 300, 35);
        mucdo.setForeground(Color.WHITE);
        mucdo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(mucdo); // Thêm vào panel mới

        ImageIcon ThapIcon = new ImageIcon(baitap.getHinhAnh()); // Lấy đường dẫn ảnh từ DB
        Image ThapImage = ThapIcon.getImage().getScaledInstance(330, 310, Image.SCALE_SMOOTH);
        JLabel hinhanhLabel = new JLabel(new ImageIcon(ThapImage)); // Thêm hình vào JLabel
        hinhanhLabel.setBounds(0, 0, 330, 310);

        JPanel hinhanh1 = new JPanel();
        hinhanh1.setBackground(new Color(0, 0, 0));
        hinhanh1.setBounds(310, 0, 330, 310);
        hinhanh1.setLayout(null);
        hinhanh1.add(hinhanhLabel); // Thêm JLabel vào JPanel
        panel.add(hinhanh1); // Thêm vào panel mới

        JButton startButton = new JButton("Bắt đầu");
        startButton.setBounds(85, 200, 120, 40);
        panel.add(startButton); // Thêm vào panel mới

        return panel; // Trả về panel mới thay vì bangbuoitap
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
            java.util.logging.Logger.getLogger(trangcanhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(trangcanhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(trangcanhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(trangcanhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new trangcanhan(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
