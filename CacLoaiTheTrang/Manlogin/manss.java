package Manlogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class manss {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(manss::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Body-Safe Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Color bgColor = new Color(160, 50, 50);
        frame.getContentPane().setBackground(bgColor);

        ImageIcon logoIcon = new ImageIcon("C:/Users/DELL/Desktop/anhlogo.png");
        Image img = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(30, 30, 150, 150);
        frame.add(logoLabel);

        JLabel titleLabel = new JLabel("Bạn đã sẵn sàng tập luyện cùng chúng tôi?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(600, 400, 800, 50);
        frame.add(titleLabel);

        JButton startButton = new JButton("Bắt đầu");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBounds(860, 500, 200, 60);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        frame.add(startButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(1720, 20, 150, 30);
        
        JMenu menu = new JMenu("☰");
        JMenuItem loginItem = new JMenuItem("Đăng nhập");
        JMenuItem registerItem = new JMenuItem("Đăng ký");
        JMenuItem forgotPasswordItem = new JMenuItem("Quên mật khẩu");
        
        menu.add(loginItem);
        menu.add(registerItem);
        menu.add(forgotPasswordItem);
        menuBar.add(menu);
        frame.add(menuBar);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Chào mừng bạn đến với Body-Safe!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
