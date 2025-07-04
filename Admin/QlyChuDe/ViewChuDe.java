/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Admin.QlyChuDe;

import Admin.AdminForm;
import Admin.QlyNguoiDung.QLND_Model;
import Admin.QlyNguoiDung.QLND_Service;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ViewChuDe extends javax.swing.JFrame {

    /**
     * Creates new form ViewChuDe
     */
    public ViewChuDe() {
        initComponents();
        ChuDeGUI();
    }

    String[] columnNames = {"ID Chủ Đề", "Tên Chủ Đề", "Mô tả chủ đề"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    QLCD_Service ChuDeSV = new QLCD_Service();
    JTable table = new JTable(model);

    public void ChuDeGUI() {
        setTitle("Quản lí chủ đề");
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
        JLabel titleLabel = new JLabel("Quản lí chủ đề");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(640, 30, 400, 50);
        add(titleLabel);

        JLabel lb1 = new JLabel("Chủ Đề");
        JLabel lb2 = new JLabel("Mô Tả");
        JLabel lb3 = new JLabel("Id_CĐ");
        lb3.setBounds(28, 170, 80, 60);
        lb3.setFont(new Font("Arial", Font.BOLD, 20));
        lb3.setForeground(Color.WHITE);
        lb1.setBounds(28, 260, 80, 60);
        lb1.setForeground(Color.WHITE);
        lb1.setFont(new Font("Arial", Font.BOLD, 20));
        lb2.setBounds(28, 350, 80, 60);
        lb2.setForeground(Color.WHITE);
        lb2.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb1);
        add(lb2);
        add(lb3);

        JTextArea txtMoTa = new JTextArea();
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        JScrollPane scrollPaneTXT = new JScrollPane(txtMoTa);
        scrollPaneTXT.setBounds(120, 360, 360, 180);
        scrollPaneTXT.setPreferredSize(new Dimension(360, 180));
        add(scrollPaneTXT);
        JTextField txtChuDe = new JTextField();
        txtChuDe.setBounds(120, 278, 360, 30);
        add(txtChuDe);

        JTextField txtIdChuDe = new JTextField();
        txtIdChuDe.setBounds(120, 187, 360, 30);
        add(txtIdChuDe);
        txtIdChuDe.setEditable(false);

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(120, 630, 100, 40);
        btnThem.setBackground(Color.BLACK);
        btnThem.setForeground(Color.WHITE);
        add(btnThem);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBounds(240, 630, 100, 40);
        btnXoa.setBackground(Color.BLACK);
        btnXoa.setForeground(Color.WHITE);
        add(btnXoa);

        JButton btnSua = new JButton("Sửa");
        btnSua.setBounds(360, 630, 100, 40);
        btnSua.setBackground(Color.BLACK);
        btnSua.setForeground(Color.WHITE);
        add(btnSua);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String TenChuDe = txtChuDe.getText().trim();
                    String MoTaChuDe = txtMoTa.getText().trim();

                    if (QLCD_Service.createCD(TenChuDe, MoTaChuDe)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Thêm chủ đề thành công!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Thêm chủ đề thất bại!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ (ID)");
                    ex.printStackTrace();
                }
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int IdChuDe = Integer.parseInt(txtIdChuDe.getText().trim());
                    String TenChuDe = txtChuDe.getText().trim();
                    String MoTaChuDe = txtMoTa.getText().trim();
                    if (QLCD_Service.updateCD(IdChuDe, TenChuDe, MoTaChuDe)) {
                        loadTableData();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật chủ đề thành công!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật chủ đề thất bại!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Dữ liệu không hợp lệ (ID)");
                    ex.printStackTrace();
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn người dùng cần xóa.");
                    return;
                }

                int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString()); // Lấy ID từ cột đầu tiên của dòng đã chọn
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chủ đề này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    QLCD_Service service = new QLCD_Service();
                    service.deleteCD(id);
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");

                    // Cập nhật lại bảng sau khi xóa
                    loadTableData();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                qly = QLCD_Service.getAll();
                loadTableData();
            }
        });

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
                    QLCD_Model chuDe = qly.get(row); // Lấy dữ liệu từ danh sách

                    txtIdChuDe.setText(String.valueOf(chuDe.getChuDeID()));
                    txtChuDe.setText(chuDe.getTenChuDe());
                    txtMoTa.setText(chuDe.getMoTaChuDe());
                }
            }
        });

        setVisible(true);
        return;
    }

    private ArrayList<QLCD_Model> qly = new ArrayList<>();

    private void loadTableData() {
        model.setRowCount(0);
        qly.clear();
        List<QLCD_Model> danhSachChuDe = QLCD_Service.getAll();
        qly.addAll(danhSachChuDe);
        for (QLCD_Model chuDe : danhSachChuDe) {
            Object[] rowData = {
                chuDe.getChuDeID(),
                chuDe.getTenChuDe(),
                chuDe.getMoTaChuDe()
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
            java.util.logging.Logger.getLogger(ViewChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewChuDe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChuDe().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
