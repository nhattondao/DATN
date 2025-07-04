/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin.QlyBaiTap;

import Admin.AdminForm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class ViewQLBT extends javax.swing.JFrame {

    /**
     * Creates new form ViewQLBT
     */
    public ViewQLBT() {
        initComponents();
        
    }
    String[] columnNames = {"Tên bài tập", "Hình ảnh", "Video", "Chủ đề", "Số Set", "Số rep", "Mức độ", "Giới tính", "Calo"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    QLBT_Service baiTapSV = new QLBT_Service();
    JTable table = new JTable(model);

    public void BaiTapGUI() {
        setTitle("Quản lí bài tập");
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
        Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Logo
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(0, 0, 130, 130);
        add(logoLabel);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quản lí bài tập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(640, 30, 400, 50);
        add(titleLabel);

        // Danh sách nhãn
        String[] labels = {"Tên bài tập", "Hình ảnh", "Video", "Chủ đề", "HDCT", "Số Set", "Số rep", "Mức độ", "Giới tính", "Calo"};
        int y = 180;

// Các JTextField riêng biệt
        JTextField txtTenBaiTap = new JTextField();
        JTextField txtHinhAnh = new JTextField();
        JTextField txtVideo = new JTextField();
        JButton BtnHDCT = new JButton("HDCT");
        JTextField txtSoSet = new JTextField();
        JTextField txtSoRep = new JTextField();
        JTextField txtCalo = new JTextField();

// Các JComboBox riêng biệt
        JComboBox<String> cbbChuDe = new JComboBox<>(new String[]{"Tap co bung", "Tap co tay", "Tap chan", "Tap nguc"});
        JComboBox<String> cbbMucDo = new JComboBox<>(new String[]{"De", "Trung Binh", "Kho"});
        JComboBox<String> cbbGioiTinh = new JComboBox<>(new String[]{"Male", "Female", "All"});

// Duyệt để tạo label và component
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 18));
            lbl.setBounds(50, y, 140, 30);
            add(lbl);

            switch (i) {
                case 0: // Tên bài tập
                    txtTenBaiTap.setBounds(200, y, 360, 35);
                    add(txtTenBaiTap);
                    break;
                case 1: // Hình ảnh
                    txtHinhAnh.setBounds(200, y, 220, 35);
                    add(txtHinhAnh);
                    break;
                case 2: // Video
                    txtVideo.setBounds(200, y, 220, 35);
                    add(txtVideo);
                    break;
                case 3: // Chủ đề
                    cbbChuDe.setBounds(200, y, 360, 35);
                    add(cbbChuDe);
                    break;
                case 4: // HDCT
                    BtnHDCT.setBounds(200, y, 360, 35);
                    add(BtnHDCT);
                    break;
                case 5: // Số Set
                    txtSoSet.setBounds(200, y, 360, 35);
                    add(txtSoSet);
                    break;
                case 6: // Số Rep
                    txtSoRep.setBounds(200, y, 360, 35);
                    add(txtSoRep);
                    break;
                case 7: // Mức độ
                    cbbMucDo.setBounds(200, y, 360, 35);
                    add(cbbMucDo);
                    break;
                case 8: // Giới tính
                    cbbGioiTinh.setBounds(200, y, 360, 35);
                    add(cbbGioiTinh);
                    break;
                case 9:
                    txtCalo.setBounds(200, y, 360, 35);
                    add(txtCalo);
                    break;
            }
            y += 50;
        }

        // Buttons
        JButton btnLayAnh = new JButton("✓ Lấy ảnh");
        btnLayAnh.setBounds(450, 230, 110, 35);
        add(btnLayAnh);

        btnLayAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Chọn hình ảnh");

                // Chỉ cho phép chọn file ảnh
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh (JPG, PNG, GIF)", "jpg", "png", "gif");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtHinhAnh.setText(selectedFile.getAbsolutePath()); // Hiển thị đường dẫn file
                }
            }
        });

        JButton btnLayVideo = new JButton("✓ Lấy video");
        btnLayVideo.setBounds(450, 280, 110, 35);
        add(btnLayVideo);

        btnLayVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Chọn video");

                // Chỉ cho phép chọn file video
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Video (MP4, AVI, MKV)", "mp4", "avi", "mkv");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    txtVideo.setText(selectedFile.getAbsolutePath()); // Hiển thị đường dẫn file
                }
            }
        });

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(80, 690, 100, 40);
        btnThem.setBackground(Color.BLACK);
        btnThem.setForeground(Color.WHITE);
        add(btnThem);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(200, 690, 100, 40);
        btnXoa.setBackground(Color.BLACK);
        btnXoa.setForeground(Color.WHITE);
        add(btnXoa);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(320, 690, 100, 40);
        btnSua.setBackground(Color.BLACK);
        btnSua.setForeground(Color.WHITE);
        add(btnSua);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tenBaiTap = txtTenBaiTap.getText().trim();
                    String hinhAnh = txtHinhAnh.getText().trim();
                    String video = txtVideo.getText().trim();
                    String tenChuDe = (String) cbbChuDe.getSelectedItem();
                    int soSet = Integer.parseInt(txtSoSet.getText().trim());
                    int soRep = Integer.parseInt(txtSoRep.getText().trim());
                    String mucDo = (String) cbbMucDo.getSelectedItem();
                    String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
                    int calo = Integer.parseInt(txtCalo.getText().trim());

                    if (QLBT_Service.createBaiTap(tenBaiTap, hinhAnh, video, tenChuDe, soSet, soRep, mucDo, gioiTinh, calo)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Thêm bài tập thành công!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm bài tập thất bại!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ (số nguyên cho Set, Rep, Calo)");
                    ex.printStackTrace();
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selectedIdBaiTap == -1) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn bài tập cần sửa!");
                        return;
                    }

                    String tenBaiTap = txtTenBaiTap.getText().trim();
                    String hinhAnh = txtHinhAnh.getText().trim();
                    String video = txtVideo.getText().trim();
                    String tenChuDe = (String) cbbChuDe.getSelectedItem();
                    int soSet = Integer.parseInt(txtSoSet.getText().trim());
                    int soRep = Integer.parseInt(txtSoRep.getText().trim());
                    String mucDo = (String) cbbMucDo.getSelectedItem();
                    String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
                    int calo = Integer.parseInt(txtCalo.getText().trim());

                    if (QLBT_Service.updateBaiTap(selectedIdBaiTap, tenBaiTap, hinhAnh, video, tenChuDe, soSet, soRep, mucDo, gioiTinh, calo)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật bài tập thành công!");
                        selectedIdBaiTap = -1; // Reset ID sau khi cập nhật
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ (số nguyên cho Set, Rep, Calo)");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi không xác định!");
                    ex.printStackTrace();
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selectedIdBaiTap == -1) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn bài tập cần xóa!");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa bài tập này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (QLBT_Service.deleteBaiTap(selectedIdBaiTap)) {
                            loadTableData();
                            JOptionPane.showMessageDialog(rootPane, "Xóa bài tập thành công!");
                            selectedIdBaiTap = -1; // Reset ID sau khi xóa
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa bài tập!");
                    ex.printStackTrace();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadTableData();
            }
        });

        // Table
        table.setPreferredSize(new Dimension(800, 600)); // Giảm kích thước table
        table.setBounds(80, 140, 880, 400); // Đảm bảo cách lề phải 80px
        table.setModel(model);
        loadTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(600, 140, 880, 600);
        add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0 && row < quanly.size()) { // Kiểm tra hàng hợp lệ trong danh sách
                    QLBT_Model baiTap = quanly.get(row); // Lấy dữ liệu từ danh sách
                    selectedIdBaiTap = baiTap.getBaiTapID(); // Lưu ID bài tập

                    txtTenBaiTap.setText(baiTap.getTenBaiTap());
                    txtHinhAnh.setText(baiTap.getHinhAnh());
                    txtVideo.setText(baiTap.getVideo());
                    txtSoSet.setText(String.valueOf(baiTap.getSoSet()));
                    txtSoRep.setText(String.valueOf(baiTap.getSoRep()));
                    txtCalo.setText(String.valueOf(baiTap.getCalo()));
                    cbbChuDe.setSelectedItem(baiTap.getChuDe().getTenChuDe());
                    cbbMucDo.setSelectedItem(baiTap.getMucDo());
                    cbbGioiTinh.setSelectedItem(baiTap.getGioiTinh());
                }
            }
        });

        setVisible(true);
        return;
    }
    private int selectedIdBaiTap = -1;

    private ArrayList<QLBT_Model> quanly = new ArrayList<>();

    private void loadTableData() {
        model.setRowCount(0);
        quanly.clear();
        List<QLBT_Model> danhSachBaiTap = QLBT_Service.getAll();
        quanly.addAll(danhSachBaiTap); // Cập nhật danh sách dữ liệu từ service

        for (QLBT_Model baiTap : danhSachBaiTap) {
            Object[] rowData = {
                baiTap.getTenBaiTap(),
                baiTap.getHinhAnh(),
                baiTap.getVideo(),
                baiTap.getChuDe().getTenChuDe(),
                baiTap.getSoSet(),
                baiTap.getSoRep(),
                baiTap.getMucDo(),
                baiTap.getGioiTinh(),
                baiTap.getCalo()
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
            java.util.logging.Logger.getLogger(ViewQLBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewQLBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewQLBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewQLBT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQLBT().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
