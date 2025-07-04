/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin.QlyNguoiDung;

import Admin.AdminForm;
import Admin.QlyBaiTap.QLBT_Model;
import Admin.QlyBaiTap.QLBT_Service;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ViewQLND extends javax.swing.JFrame {

    /**
     * Creates new form ViewQLND
     */
    public ViewQLND() {
        initComponents();
    }

    String[] columnNames = {"ID", "Username", "Password", "Email", "Năm Sinh", "Giới tính", "Role", "Status"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    QLND_Service UserSV = new QLND_Service();
    JTable table = new JTable(model);

    public void UserGUI() {
        setTitle("Quản lí nguoi dung");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(150, 50, 50));

        // Mũi tên quay lại
        JLabel backLabel = new JLabel("←");
        backLabel.setFont(new Font("Arial", Font.BOLD, 34));
        backLabel.setForeground(Color.BLACK);
        backLabel.setBounds(30, 120, 40, 40);
        add(backLabel);

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminForm ad = new AdminForm();
                ad.setVisible(true);
                setVisible(false);
            }
        });

        ImageIcon originalIcon = new ImageIcon("F:/CodeJava/Body_Safe/Picture/snapedit_1741363788304.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Logo
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(0, 0, 130, 130);
        add(logoLabel);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quản lí người dùng");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(640, 30, 400, 50);
        add(titleLabel);

        // Danh sách nhãn
        String[] labels = {"ID", "Username", "Password", "Email", "Năm sinh", "Giới tính", "Role", "Status", "Tìm kiếm"};
        int y = 180;

// Các JTextField riêng biệt
        JTextField txtID = new JTextField();
        JTextField txtUsername = new JTextField();
        JTextField txtPassword = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtDOB = new JTextField();
        JTextField txtTimKiem = new JTextField();

        JComboBox<String> cbbGioiTinh = new JComboBox<>(new String[]{"Female", "Male"});
        JComboBox<String> cbbAdmin = new JComboBox<>(new String[]{"User", "Admin"});
        JComboBox<String> cbbStatus = new JComboBox<>(new String[]{"HD", "NHD"});

// Duyệt để tạo label và component
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 18));
            lbl.setBounds(50, y, 140, 30);
            add(lbl);

            switch (i) {
                case 0:
                    txtID.setBounds(200, y, 360, 35);
                    add(txtID);
                    break;
                case 1:
                    txtUsername.setBounds(200, y, 360, 35);
                    add(txtUsername);
                    break;
                case 2:
                    txtPassword.setBounds(200, y, 360, 35);
                    add(txtPassword);
                    break;
                case 3:
                    txtEmail.setBounds(200, y, 360, 35);
                    add(txtEmail);
                    break;
                case 4:
                    txtDOB.setBounds(200, y, 360, 35);
                    add(txtDOB);
                    break;
                case 5:
                    cbbGioiTinh.setBounds(200, y, 360, 35);
                    add(cbbGioiTinh);
                    break;
                case 6:
                    cbbAdmin.setBounds(200, y, 360, 35);
                    add(cbbAdmin);
                    break;
                case 7:
                    cbbStatus.setBounds(200, y, 360, 35);
                    add(cbbStatus);
                    break;
                case 8:
                    txtTimKiem.setBounds(200, y, 220, 35);
                    add(txtTimKiem);
                    break;
            }
            y += 50;
        }

        JButton btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setBounds(450, 580, 110, 35);
        add(btnTimKiem);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(200, 645, 100, 40);
        btnXoa.setBackground(Color.BLACK);
        btnXoa.setForeground(Color.WHITE);
        add(btnXoa);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(320, 645, 100, 40);
        btnSua.setBackground(Color.BLACK);
        btnSua.setForeground(Color.WHITE);
        add(btnSua);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                qly = QLND_Service.getAll();
                loadTableData();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(txtID.getText().trim());
                    String username = txtUsername.getText().trim();
                    String password = txtPassword.getText().trim();
                    String email = txtEmail.getText().trim();
                    int dob = Integer.parseInt(txtDOB.getText().trim());
                    String gender = (String) cbbGioiTinh.getSelectedItem();
                    String roles = (String) cbbAdmin.getSelectedItem();
                    String status = (String) cbbStatus.getSelectedItem();

                    if (QLND_Service.updateND(userId, username, password, email, dob, gender, roles, status)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật người dùng thành công!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật người dùng thất bại!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ (ID, Năm sinh phải là số)");
                    ex.printStackTrace();
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // Lấy dòng được chọn trong JTable
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn người dùng cần xóa.");
                    return;
                }

                int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString()); // Lấy ID từ cột đầu tiên của dòng đã chọn
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa người dùng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    QLND_Service service = new QLND_Service();
                    service.deleteND(id);
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");

                    // Cập nhật lại bảng sau khi xóa
                    loadTableData();
                }
            }
        });

        // Table
        table.setPreferredSize(new Dimension(800, 600)); // Giảm kích thước table
        table.setBounds(80, 140, 880, 400); // Đảm bảo cách lề phải 80px

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(600, 140, 880, 600);
        add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0 && row < qly.size()) { // Kiểm tra hàng hợp lệ
                    QLND_Model user = qly.get(row); // Lấy dữ liệu từ danh sách

                    txtID.setText(String.valueOf(user.getUserId()));
                    txtUsername.setText(user.getUsername());
                    txtPassword.setText(user.getPassword());
                    txtEmail.setText(user.getEmail());
                    txtDOB.setText(String.valueOf(user.getDob()));

                    cbbGioiTinh.setSelectedItem(user.getGender());
                    cbbAdmin.setSelectedItem(user.getRoles());
                    cbbStatus.setSelectedItem(user.getStatus());
                }
            }
        });

        setVisible(true);

        return;
    }

    private ArrayList<QLND_Model> qly = new ArrayList<>();

    private void loadTableData() {
        model.setRowCount(0);
        qly.clear();
        List<QLND_Model> danhSachNguoiDung = QLND_Service.getAll();
        qly.addAll(danhSachNguoiDung); // Cập nhật danh sách dữ liệu từ service

        for (QLND_Model User : danhSachNguoiDung) {
            Object[] rowData = {
                User.getUserId(),
                User.getUsername(),
                User.getPassword(),
                User.getEmail(),
                User.getDob(),
                User.getGender(),
                User.getRoles(),
                User.getStatus()
            };
            model.addRow(rowData);
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
            java.util.logging.Logger.getLogger(ViewQLND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewQLND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewQLND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewQLND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQLND().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
