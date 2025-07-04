package ChuDe_BaiTap.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class manhoanthanh {

    public static void main(String[] args) {
        // Gọi phương thức để hiển thị giao diện
        SwingUtilities.invokeLater(() -> {
            new manhoanthanh().createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        // Tạo cửa sổ chính
        JFrame frame = new JFrame("Body Safe - Bài tập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(150, 50, 50));

        // Thêm logo vào giao diện
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
        JLabel logo = new JLabel(logoIcon);
        logo.setBounds(20, 20, 100, 100);
        frame.add(logo);

        // Thêm tiêu đề "Bài tập"
        JLabel title = new JLabel("Bài tập", SwingConstants.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(140, 20, 300, 30);
        frame.add(title);

        // Thêm các thành phần hiển thị như mức độ, sets, reps, hướng dẫn
        JLabel level = new JLabel();
        level.setForeground(Color.WHITE);
        level.setFont(new Font("Arial", Font.PLAIN, 14));
        level.setBounds(140, 50, 200, 20);
        frame.add(level);

        JLabel setsLabel = new JLabel();
        setsLabel.setForeground(Color.WHITE);
        setsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        setsLabel.setBounds(140, 320, 100, 30);
        frame.add(setsLabel);

        JLabel repsLabel = new JLabel();
        repsLabel.setForeground(Color.WHITE);
        repsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        repsLabel.setBounds(250, 320, 100, 30);
        frame.add(repsLabel);

        JLabel instructionLabel = new JLabel("Hướng dẫn chi tiết:");
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        instructionLabel.setBounds(500, 100, 200, 30);
        frame.add(instructionLabel);

        JTextArea instructionText = new JTextArea();
        instructionText.setLineWrap(true);
        instructionText.setWrapStyleWord(true);
        instructionText.setBackground(new Color(200, 100, 100));
        instructionText.setForeground(Color.WHITE);
        instructionText.setBounds(500, 130, 350, 100);
        frame.add(instructionText);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(500, 250, 300, 150);
        frame.add(imageLabel);

        // Thêm nút "Kết thúc bài tập"
        JButton finishButton = new JButton("Kết thúc bài tập");
        finishButton.setBounds(140, 380, 200, 50);
        finishButton.setBackground(Color.RED);
        finishButton.setForeground(Color.WHITE);
        frame.add(finishButton);

        // Khi nhấn nút "Kết thúc bài tập"
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Giả sử bạn có User_ID và Baitap_ID từ giao diện hoặc cơ sở dữ liệu
                int userId = 1;  // Thay đổi theo cách bạn lấy User_ID thực tế
                int baitapId = 1;  // Thay đổi theo cách bạn lấy Baitap_ID thực tế

                // Cập nhật vào bảng BaitapHoanthanh khi hoàn thành bài tập
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Body_safe;user=sa;password=123456;trustServerCertificate=true")) {
                    String updateSQL = "INSERT INTO BaitapHoanthanh (User_ID, Baitap_ID) VALUES (?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                        pstmt.setInt(1, userId);
                        pstmt.setInt(2, baitapId);
                        pstmt.executeUpdate();  // Thực thi câu lệnh SQL
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();  // In ra lỗi nếu có
                }

                frame.dispose(); // Đóng màn hình hiện tại
                showCompletedScreen(); // Hiển thị màn hình hoàn thành bài tập
            }
        });

        // Kết nối và lấy dữ liệu bài tập từ cơ sở dữ liệu
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-I1EE1UQ:1433;databaseName=Body_safe;user=sa;password=123;trustServerCertificate=true"); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery("SELECT Baitap.Ten_baitap, Baitap.Muc_do, Baitap.So_Set, Baitap.So_Rep, Baitap.Hinh_Anh, Huong_dan_chi_tiet.Noi_dung " +
                                             "FROM Baitap LEFT JOIN Huong_dan_chi_tiet ON Baitap.Huong_dan_chi_tiet_ID = Huong_dan_chi_tiet.HuongdanchitietID " +
                                             "WHERE Baitap.Baitap_ID = 1")) {
            if (rs.next()) {
                title.setText(rs.getString("Ten_baitap"));
                level.setText("Mức độ: " + rs.getString("Muc_do"));
                setsLabel.setText("Sets: " + rs.getInt("So_Set"));
                repsLabel.setText("Reps: " + rs.getInt("So_Rep"));
                instructionText.setText(rs.getString("Noi_dung"));

                String imagePath = rs.getString("Hinh_Anh");
                if (imagePath != null && !imagePath.isEmpty()) {
                    imageLabel.setIcon(new ImageIcon(imagePath));  // Nếu có hình ảnh, hiển thị
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }

    // Phương thức hiển thị màn hình hoàn thành bài tập
    private void showCompletedScreen() {
        JFrame completedFrame = new JFrame("Đã hoàn thành bài tập");
        completedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        completedFrame.setSize(900, 600);
        completedFrame.setLayout(null);
        completedFrame.getContentPane().setBackground(new Color(150, 50, 50));

        JLabel logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(20, 20, 100, 100);
        completedFrame.add(logo);

        JLabel title = new JLabel("ĐÃ HOÀN THÀNH BÀI TẬP", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(250, 30, 400, 40);
        completedFrame.add(title);

        JLabel imageLabel = new JLabel(new ImageIcon("exercise_complete.png"));
        imageLabel.setBounds(300, 100, 300, 200);
        completedFrame.add(imageLabel);

        JLabel message = new JLabel("Luyện tập là ý thức của bạn. Chúng tôi rất mong cơ thể bạn có thể khỏe hơn mỗi ngày", SwingConstants.CENTER);
        message.setForeground(Color.WHITE);
        message.setFont(new Font("Arial", Font.PLAIN, 16));
        message.setBounds(150, 320, 600, 30);
        completedFrame.add(message);

        // Thêm các nút "Tập lại" và "Bài tập mới"
        JButton retryButton = new JButton("Tập lại");
        retryButton.setBounds(250, 400, 150, 50);
        retryButton.setBackground(Color.RED);
        retryButton.setForeground(Color.WHITE);
        completedFrame.add(retryButton);

        JButton backButton = new JButton("Bài tập mới");
        backButton.setBounds(500, 400, 150, 50);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        completedFrame.add(backButton);

        // Nút "Tập lại"
        retryButton.addActionListener(e -> {
            completedFrame.dispose();
            createAndShowGUI();  // Tạo lại màn hình bài tập
        });

        // Nút "Bài tập mới"
        backButton.addActionListener(e -> completedFrame.dispose()); // Đóng màn hình hoàn thành

        completedFrame.setVisible(true);
    }
}
