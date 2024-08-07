package Admin;

import Users.Login;
import Users.purchasedetail;
import dao.Statistics;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import dao.SupplierDao;
import javax.swing.JOptionPane;

public class ManageuSupplier extends javax.swing.JFrame {

    Statistics st = new Statistics();
    SupplierDao sp = new SupplierDao();
    Color textPrimaryColor = new Color(192, 192, 192);
    Color primaryColor = new Color(250, 250, 210);
    int xx, xy;
    DefaultTableModel model;
    int rowindex;

    public ManageuSupplier() {
        initComponents();
        supplierTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        btnDelete1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel5MouseDragged(evt);
            }
        });
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 74, 214, 31));

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 181, 214, 31));
        jPanel5.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 268, 214, 28));
        jPanel5.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 370, 214, 28));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("ID Nhà cung cấp");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 37, 125, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Tên ");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 143, 70, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("Email");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 230, 53, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel8.setText("Mật khẩu");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 327, 96, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel9.setText("Số điện thoại");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 37, 240, -1));
        jPanel5.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 170, 221, 31));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setText("Địa chỉ");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 138, 115, -1));

        btnupdate.setBackground(new java.awt.Color(255, 255, 204));
        btnupdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(255, 0, 255));
        btnupdate.setText("Cập nhật");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        jPanel5.add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 255, -1, 36));

        btnClear.setBackground(new java.awt.Color(255, 255, 204));
        btnClear.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 51, 255));
        btnClear.setText("Hoàn Tác");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel5.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 365, 221, 37));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("X");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(926, 6, 26, -1));
        jPanel5.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 69, 221, 31));

        btnDelete1.setBackground(new java.awt.Color(255, 255, 204));
        btnDelete1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDelete1.setForeground(new java.awt.Color(255, 51, 255));
        btnDelete1.setText("Xóa");
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(794, 255, 96, 36));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("___________________________________________________________________________________________________________________________________________________________________________");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 433, -1, 33));

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID nhà cung cấp", "Tên ", "Email", "Mật khẩu", "Phone", "Điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 547, 946, 129));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 497, 228, 30));

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tìm Kiếm");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 506, 94, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void supplierTable() {
        sp.getSupplierValue(jTable1, "");
        model = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.BLACK);
        jTable1.setBackground(Color.white);
        jTable1.setSelectionBackground(Color.LIGHT_GRAY);

    }

    private void clear() {
        jTextField9.setText(String.valueOf(sp.getmaxrow()));
        jTextField10.setText("");
        jTextField11.setText("");
        jPasswordField1.setText("");
        jTextField14.setText("");
        jTextField13.setText("");
        jTable1.clearSelection();
        st.Admin();
    }

    public boolean isempty() {
        if (jTextField9.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon 1 nha cung cap", "Thong bao", 2);
            return false;
        }
        if (jTextField10.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username phai dung", "Thong bao", 2);
            return false;
        }
        if (jTextField11.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Email tài khoản của bạn", "Thông báo", 2);
            return false;
        }
        if (!jTextField11.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email Không đúng ", "Thông báo", 2);
            return false;
        }
        if (String.valueOf(jPasswordField1.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Đúng mật khẩu của bạn", "Thông báo", 2);
            return false;
        }

        if (jTextField14.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap so dien thoai", "Thong bao", 2);
            return false;
        }
        if (jTextField14.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Vui long nhap so dien thoai dung dinh dang", "Thong bao", 2);
            return false;
        }
        if (jTextField14.getText().length() < 10) {
            JOptionPane.showMessageDialog(this, "Vui long nhap so dien thoai dung dinh dang", "Thong bao", 2);
            return false;
        }
        if (jTextField13.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap Dia chi", "Thong bao", 2);
            return false;
        }

        return true;
    }


    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed
//nut cap nhat tren man hinh
    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if (isempty()) {
            if (!check()) {
                int id = Integer.parseInt(jTextField9.getText());
                String username = jTextField10.getText();
                String email = jTextField11.getText();
                String passwords = String.valueOf(jPasswordField1.getPassword());
                String phone = jTextField14.getText();
                String address = jTextField13.getText();
                sp.update(id, username, email, passwords, phone, address);
                jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID nhà cung cấp", "Tên", "Email",
                    "Mật khẩu", "Phone", "Địa chỉ"}));
                sp.getSupplierValue(jTable1, "");
                clear();
            }
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    public boolean check() {
        String newusername = jTextField10.getText();
        String newemail = jTextField11.getText();
//        String newphone = jTextField14.getText();
        String oldemail = model.getValueAt(rowindex, 2).toString();
        String oldusername = model.getValueAt(rowindex, 1).toString();
//        String oldphone = model.getValueAt(rowindex, 4).toString();

        if (newemail.equals(oldemail) && newusername.equals(oldusername)) {
            return false;
        } else {
            if (!newemail.equals(oldemail)) {
                boolean x = sp.isEmailexits(newemail);
                if (x) {
                    JOptionPane.showMessageDialog(this, "Email này đã tồn tại ", "Thông báo", 2);
                }
                return x;
            }
            if (!newusername.equals(oldusername)) {
                boolean x = sp.isUsernameExits(newusername);
                if (x) {
                    JOptionPane.showMessageDialog(this, "Ten này đã tồn tại ", "Thông báo", 2);
                }
                return x;
            }

        }

        return false;
    }

//nut hoan tac tren man hinh
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        setVisible(false);
//        admindashboard1.jPanel16.setBackground(primaryColor);
//        admindashboard1.jPanel17.setBackground(primaryColor);
//        admindashboard1.jLabel38.setForeground(textPrimaryColor);
//        admindashboard1.jLabel39.setVisible(false);
//        admindashboard1.jLabel40.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i = 0.2; i <= 1.0; i += 0.2) {
            String t = " " + i;
            float f = Float.parseFloat(t);
            this.setOpacity(f);
            try {
//Đây là một phương thức của lớp Thread trong Java. Nó được sử dụng để tạm dừng (sleep) luồng hiện tại trong một khoảng thời gian cố định, được chỉ định bằng mili giây. Trong trường hợp này, chúng ta dừng luồng hiện tại trong 50 mili giây.
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManageuSupplier.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_formWindowOpened

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        xx = getX();
        xy = getY();
    }//GEN-LAST:event_jPanel5MousePressed

    private void jPanel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel5MouseDragged
//click vao bang o giao dien
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        model = (DefaultTableModel) jTable1.getModel();
        rowindex = jTable1.getSelectedRow();
        jTextField9.setText(model.getValueAt(rowindex, 0).toString());
        jTextField10.setText(model.getValueAt(rowindex, 1).toString());
        jTextField11.setText(model.getValueAt(rowindex, 2).toString());
        jPasswordField1.setText(model.getValueAt(rowindex, 3).toString());
        jTextField14.setText(model.getValueAt(rowindex, 4).toString());
        jTextField13.setText(model.getValueAt(rowindex, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked
//nut xoa khi nguoi dung click
    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        if (jTextField9.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon 1 nha cung cap", "Thong bao", 2);
        } else {
            int id = Integer.parseInt(jTextField9.getText());
            sp.delete(id);
            jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID nhà cung cấp", "Tên", "Email",
                "Mật khẩu", "Phone", "Địa chỉ"}));
            sp.getSupplierValue(jTable1, "");
            clear();
        }

    }//GEN-LAST:event_btnDelete1ActionPerformed
//nut tim kiem 
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"ID nhà cung cấp", "Tên", "Email",
            "Mật khẩu", "Phone", "Địa chỉ"}));
        sp.getSupplierValue(jTable1, jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageuSupplier().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
