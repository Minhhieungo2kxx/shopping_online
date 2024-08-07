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

public class PurchaseDao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //lấy hàng tối đa của bảng Purchase
    public int getmaxrow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(id) from purchase_table");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    //nhận giá trị người dùng

    public String[] getUServalue(String email) {
        String[] value = new String[3];
        String sql = "select uid,uname,uaddress from user_table where uemail= '" + email + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;

    }
    //  insert data into purchase table

    public void insert(int id, int uid, String uname, int pid, String pname, int qty, double price,
            double total, String pdate, String address, String rdate, String supplier, String status) {
        String sql = "INSERT INTO purchase_table (uid, uname, pid,product_name,qty, price, total, p_date,uaddress, receive_date, supplier, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, uid);
            ps.setString(2, uname);
            ps.setInt(3, pid);
            ps.setString(4, pname);
            ps.setInt(5, qty);
            ps.setDouble(6, price);
            ps.setDouble(7, total);
            ps.setString(8, pdate);
            ps.setString(9, address);
            ps.setString(10, rdate);
            ps.setString(11, supplier);
            ps.setString(12, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //get products quantity
    public int getQuy(int pid) {
        int quy = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select pquanlity from product_table where pid= " + pid + "");
            if (rs.next()) {
                quy = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quy;
    }

    //update quantity product
    public void Quyupdate(int pid, int qty) {
        String sql = "Update product_table set pquanlity=? where pid=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setInt(2, pid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //lay sản phẩm người dùng cụ thể đã mua
    public void getProductTable(JTable table, String search, int uid) {
        String sql = "select * from purchase_table where concat(id,pid,product_name) like ? and uid=? order by id asc ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(4);
                row[2] = rs.getString(5);
                row[3] = rs.getInt(6);
                row[4] = rs.getDouble(7);
                row[5] = rs.getDouble(8);
                row[6] = rs.getString(9);
                row[7] = rs.getString(11);
                row[8] = rs.getString(12);
                row[9] = rs.getString(13);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refund(int id) {
        int b = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn hoàn tiền Sản phẩm này không", "Hoan tra ", JOptionPane.OK_CANCEL_OPTION, 0);
        if (b == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from purchase_table where id=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Hoan tra thanh cong");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

//    public void getProductvalue(JTable table, String search) {
//        String sql = "select * from purchase_table where concat(id,pid,product_name) like ? and status='Chưa giải quyết' order by id asc ";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, "%" + search + "%");
//
//            rs = ps.executeQuery();
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            Object[] row;
//            while (rs.next()) {
//                row = new Object[13];
//                row[0] = rs.getInt(1);
//                row[1] = rs.getInt(2);
//                row[2] = rs.getString(3);
//                row[3] = rs.getInt(4);
//                row[4] = rs.getString(5);
//                row[5] = rs.getInt(6);
//                row[6] = rs.getDouble(7);
//                row[7] = rs.getDouble(8);
//                row[8] = rs.getString(9);
//                row[9] = rs.getString(10);
//                row[10] = rs.getString(11);
//                row[11] = rs.getString(12);
//                row[12] = rs.getString(13);
//               
//              
//                model.addRow(row);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //lay du lieu tu bang purchase roi ghi du lieu len man hinh giao dien
    public void getProductvalue(JTable table, String search) {
        String sql = "SELECT * FROM purchase_table WHERE CONCAT(id, pid,uname,product_name) LIKE ? AND status = ? ORDER BY id ASC";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "Chưa giải quyết");

            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //cập nhật thông tin nhà cung cấp trong cơ sở dữ liệu
    public void setSupplier(int id, String supps, String tt) {
        String sql = "Update purchase_table set supplier=?,status=? where id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, supps);
            ps.setString(2, tt);
            ps.setInt(3, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Nhà cung cấp đã được chọn thành công !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//cập nhật ngày nhận và trạng thái của một mặt hàng trong cơ sở dữ liệu
    public void setdateStatus(int id, String rdate, String tt) {
        String sql = "Update purchase_table set receive_date=?,status=? where id=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, rdate);
            ps.setString(2, tt);
            ps.setInt(3, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Sản phẩm được giao thành công !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //nhận tất cả các sản phẩm đã mua trên đường đi

    public void getonthewayProduct(JTable table, String search, String supplier) {
        String sql = "SELECT * FROM purchase_table WHERE CONCAT(id, pid,uname,product_name) LIKE ? and supplier=? AND status = ? ORDER BY id ASC";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, supplier);
            ps.setString(3, "Đang chuyển hàng");

            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //nhận sản phẩm được nhà cung cấp giao
    public void getSupDeliProduct(JTable table, String search, String supplier) {
        String sql = "SELECT * FROM purchase_table WHERE CONCAT(id, pid,uname,product_name) LIKE ? and supplier=? AND status = ? ORDER BY id ASC";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, supplier);
            ps.setString(3, "Đã nhận");

            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getDouble(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// lay thông tin giao dịch từ cơ sở dữ liệu và hiển thị lên một bảng
    public void Transaction(JTable table, String search) {
        String sql = "SELECT * FROM purchase_table WHERE CONCAT(id,pid,uid) LIKE ? AND status = ? ORDER BY id ASC";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "Đã nhận");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(4);
                row[3] = rs.getInt(6);
                row[4] = rs.getDouble(7);
                row[5] = rs.getDouble(8);
                row[6] = rs.getString(11);
                row[7] = rs.getString(12);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
