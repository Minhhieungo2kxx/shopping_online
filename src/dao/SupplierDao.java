package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SupplierDao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    //lấy hàng tối đa của bảng supplier  

    public int getmaxrow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(sid) from supplier_table");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    //kiểm tra email đã tồn tại hay chua co

    public boolean isEmailexits(String email) {
        try {
            ps = con.prepareStatement("select * from supplier_table where semail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //kiem tra xem co ten nha cung cap chua
    public boolean isUsernameExits(String name) {
        try {
            ps = con.prepareStatement("select * from supplier_table where sname=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    //chèn dữ liệu vào bảng nha cung cap

    public void insert(int id, String username, String email, String password,
            String phone, String address) {
        String sql = "INSERT INTO supplier_table (sid,sname,semail,spassword,sphone,saddress) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, phone);
            ps.setString(6, address);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Nha cung cap dang ki thanh cong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //lay du lieu nguoi dung o bang nha cung cap

    public void getSupplierValue(JTable table, String search) {
        String sql = "select * from supplier_table where concat(sid,sname,semail) like ? order by sid asc ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //cap nhat du lieu vao database supplier
    public void update(int id, String username, String email, String password,
            String phone, String address) {
        String sql = "update supplier_table set sname=?,semail=?, spassword=?,sphone=?,saddress=? where sid=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setInt(6, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu  cập nhật thành công !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//xoa nha cung cap

    public void delete(int id) {
        int b = JOptionPane.showConfirmDialog(null, "Ban cho chac muon xoa tai khoan nay khong", "Xoa tai khoan", JOptionPane.OK_CANCEL_OPTION, 0);
        if (b == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from supplier_table where sid=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Da xoa tai khoan");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    //lay gia tri id nha cung cap

    public int getIDsupplier(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select sid from supplier_table where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }
//lay ten nha cung cap
    public String getsuppliername(String email) {
       String supplier="";
        try {
            ps = con.prepareStatement("select sname from supplier_table where semail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
               
              supplier=rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplier;

    }

    //lay giatri nha cung cap
    public String[] getvalueSupplier(int id) {
        String[] value = new String[6];
        try {
            ps = con.prepareStatement("select * from supplier_table where sid=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);

            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;

    }

    public int countSupplier() {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'Total' from supplier_table");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;

    }
    public String[] getSupplier() {
        String[] suplier = new String[countSupplier()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from supplier_table");
            int i = 0;
            while (rs.next()) {
                suplier[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suplier;
    }
}
