/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin.QlyThucPham;

import Admin.AdminForm;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ViewQLTP extends javax.swing.JFrame {

    /**
     * Creates new form ViewQLTP
     */
    public ViewQLTP() {
        initComponents();
        ProductGUI();
    }
    String[] columnNames = {"Tên", "Mô tả", "Calo", "Protein", "Fiber", "Carb", "Fat", "Vitamin"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JTable table = new JTable(model);
    JLabel lblImg = new JLabel();
    JPanel PanelImg = new JPanel();

    public void ProductGUI() {
        setTitle("Quản lí thuc pham");
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
        JLabel titleLabel = new JLabel("Quản lí thực phẩm");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(640, 30, 400, 50);
        add(titleLabel);

        // Danh sách nhãn
        String[] labels = {"Tên", "Mô tả", "Calo", "Protein", "Fiber", "Carb", "Fat", "Vitamin", "Img"};
        int y = 180;

// Các JTextField riêng biệt
        JTextField txtTen = new JTextField();
        JTextField txtMota = new JTextField();
        JTextField txtCalo = new JTextField();
        JTextField txtProtein = new JTextField();
        JTextField txtFiber = new JTextField();
        JTextField txtCarb = new JTextField();
        JTextField txtFat = new JTextField();
        JTextField txtVitamin = new JTextField();
        JTextField txtImg = new JTextField();

// Duyệt để tạo label và component
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 18));
            lbl.setBounds(50, y, 140, 30);
            add(lbl);

            switch (i) {
                case 0: // Tên bài tập
                    txtTen.setBounds(200, y, 360, 35);
                    add(txtTen);
                    break;
                case 1: // Hình ảnh
                    txtMota.setBounds(200, y, 360, 35);
                    add(txtMota);
                    break;
                case 2: // Video
                    txtCalo.setBounds(200, y, 360, 35);
                    add(txtCalo);
                    break;
                case 3: // Chủ đề
                    txtProtein.setBounds(200, y, 360, 35);
                    add(txtProtein);
                    break;
                case 4: // HDCT
                    txtFiber.setBounds(200, y, 360, 35);
                    add(txtFiber);
                    break;
                case 5: // Số Set
                    txtCarb.setBounds(200, y, 360, 35);
                    add(txtCarb);
                    break;
                case 6: // Số Rep
                    txtFat.setBounds(200, y, 360, 35);
                    add(txtFat);
                    break;
                case 7: // Mức độ
                    txtVitamin.setBounds(200, y, 360, 35);
                    add(txtVitamin);
                    break;
                case 8:
                    txtImg.setBounds(200, y, 210, 35);
                    add(txtImg);
                    break;
            }
            y += 50;
        }

        PanelImg.setBounds(430, 580, 150, 150);
        add(PanelImg);

        lblImg.setBounds(0, 0, 150, 150);
        PanelImg.add(lblImg);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loadTableData();
            }
        });

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(60, 650, 100, 40);
        btnThem.setBackground(Color.BLACK);
        btnThem.setForeground(Color.WHITE);
        add(btnThem);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(300, 650, 100, 40);
        btnXoa.setBackground(Color.BLACK);
        btnXoa.setForeground(Color.WHITE);
        add(btnXoa);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(180, 650, 100, 40);
        btnSua.setBackground(Color.BLACK);
        btnSua.setForeground(Color.WHITE);
        add(btnSua);

        JButton btnThemAnh = new JButton("Thêm Ảnh");
        btnThemAnh.setBounds(180, 710, 100, 40);
        btnThemAnh.setBackground(Color.BLACK);
        btnThemAnh.setForeground(Color.WHITE);
        add(btnThemAnh);

        btnThemAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh");

                // Chỉ hiển thị các file ảnh
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh (*.jpg, *.png, *.gif)", "jpg", "png", "gif");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath(); // Lấy đường dẫn ảnh

                    txtImg.setText(imagePath); // Hiển thị đường dẫn lên txtImg
                    showImage(imagePath); // Hiển thị ảnh lên PanelImg
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ten = txtTen.getText().trim();
                    String moTa = txtMota.getText().trim();
                    float calo = Float.parseFloat(txtCalo.getText().trim());
                    float protein = Float.parseFloat(txtProtein.getText().trim());
                    float fiber = Float.parseFloat(txtFiber.getText().trim());
                    float carb = Float.parseFloat(txtCarb.getText().trim());
                    float fat = Float.parseFloat(txtFat.getText().trim());
                    float vitamin = Float.parseFloat(txtVitamin.getText().trim());
                    String img = txtImg.getText().trim();

                    if (QLTP_Service.createTP(ten, moTa, calo, protein, fiber, carb, fat, vitamin, img)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Thêm thực phẩm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thực phẩm thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ! (calo, protein, fiber, carb, fat, vitamin phải là số)");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi khi thêm thực phẩm!");
                    ex.printStackTrace();
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selectedIdThucPham == -1) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn thực phẩm cần sửa!");
                        return;
                    }

                    String ten = txtTen.getText().trim();
                    String moTa = txtMota.getText().trim();
                    float calo = Float.parseFloat(txtCalo.getText().trim());
                    float protein = Float.parseFloat(txtProtein.getText().trim());
                    float fiber = Float.parseFloat(txtFiber.getText().trim());
                    float carb = Float.parseFloat(txtCarb.getText().trim());
                    float fat = Float.parseFloat(txtFat.getText().trim());
                    float vitamin = Float.parseFloat(txtVitamin.getText().trim());
                    String img = txtImg.getText().trim();

                    if (QLTP_Service.updateTP(selectedIdThucPham, ten, moTa, calo, protein, fiber, carb, fat, vitamin, img)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật thực phẩm thành công!");
                        selectedIdThucPham = -1; // Reset ID sau khi cập nhật
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ! (calo, protein, fiber, carb, fat, vitamin phải là số)");
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
                    if (selectedIdThucPham == -1) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn thực phẩm cần xóa!");
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa thực phẩm này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (QLTP_Service.deleteTP(selectedIdThucPham)) {
                            loadTableData();
                            JOptionPane.showMessageDialog(rootPane, "Xóa thực phẩm thành công!");
                            selectedIdThucPham = -1; // Reset ID sau khi xóa
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Lỗi khi xóa thực phẩm!");
                    ex.printStackTrace();
                }
            }
        });

        // Table
        table.setPreferredSize(new Dimension(800, 600));
        table.setBounds(80, 140, 880, 400);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0 && row < quanly.size()) {
                    QLTP_Model thucPham = quanly.get(row);
                    selectedIdThucPham = thucPham.getThucPhamID();

                    txtTen.setText(thucPham.getThucPhamName());
                    txtMota.setText(thucPham.getMoTa());
                    txtCalo.setText(String.valueOf(thucPham.getCalo()));
                    txtProtein.setText(String.valueOf(thucPham.getProtein()));
                    txtFiber.setText(String.valueOf(thucPham.getFiber()));
                    txtCarb.setText(String.valueOf(thucPham.getCarb()));
                    txtFat.setText(String.valueOf(thucPham.getFat()));
                    txtVitamin.setText(String.valueOf(thucPham.getVitamin()));
                    txtImg.setText(thucPham.getImg());
                    showImage(thucPham.getImg());
                }
            }
        });

        txtImg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                showImage(txtImg.getText().trim());
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(600, 140, 880, 600);
        add(scrollPane);

        setVisible(false);
        return;
    }

    private int selectedIdThucPham = -1;

    private ArrayList<QLTP_Model> quanly = new ArrayList<>();

    private void loadTableData() {
        model.setRowCount(0);
        quanly.clear();
        List<QLTP_Model> dsThucPham = QLTP_Service.getAll();
        quanly.addAll(dsThucPham); // Cập nhật danh sách dữ liệu từ service

        for (QLTP_Model thucPham : dsThucPham) {
            Object[] rowData = {
                thucPham.getThucPhamName(),
                thucPham.getMoTa(),
                thucPham.getCalo(),
                thucPham.getCarb(),
                thucPham.getProtein(),
                thucPham.getFat(),
                thucPham.getFiber(),
                thucPham.getVitamin(),
                thucPham.getImg()
            };
            model.addRow(rowData);
        }
    }

    private void showImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Resize ảnh về 150x150
            icon = new ImageIcon(img);

            // Xóa ảnh cũ trước khi thêm ảnh mới
            PanelImg.removeAll();
            JLabel lblImage = new JLabel(icon);
            PanelImg.add(lblImage);
            PanelImg.revalidate();
            PanelImg.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Không thể tải ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(ViewQLTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewQLTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewQLTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewQLTP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQLTP().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
