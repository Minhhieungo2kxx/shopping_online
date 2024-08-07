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

public class ProductDao {
//có nhiệm vụ thiết lập kết nối đến cơ sở dữ liệu và trả về đối tượng Connection.
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //lấy hàng tối đa của bảng Category
    public int getmaxrow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(pid) from product_table");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
// đếm số lượng bản ghi trong bảng category_table
    public int countCategoris() {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'Total' from category_table");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;

    }
//Phương thức này được sử dụng để lấy ra danh sách các danh mục từ bảng category_table.
    public String[] getCate() {
        String[] categoris = new String[countCategoris()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from category_table");
            int i = 0;
            while (rs.next()) {
                categoris[i] = rs.getString(2);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoris;
    }

    //kiểm tra  id Product đã tồn tại hay chua
    public boolean isIDexits(int id) {
        try {
            ps = con.prepareStatement("select * from product_table where pid=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //kiểm tra Product va Category đã tồn tại hay chua
    public boolean isProCateExits(String product, String cate) {
        try {
            ps = con.prepareStatement("select * from product_table where pname=? and cname=?");
            ps.setString(1, product);
            ps.setString(2, cate);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //chèn dữ liệu vào bảng 
    public void insert(int id, String pname, String cname, int qty, double price) {
        String sql = "INSERT INTO  product_table (pname,cname,pquanlity,pprice) VALUES (?,?,?,?)";

        try {

            ps = con.prepareStatement(sql);

            ps.setString(1, pname);
            ps.setString(2, cname);
            ps.setInt(3, qty);
            ps.setDouble(4, price);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Product added  thanh cong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //lay du lieu bang Products
    public void getProductTable(JTable table, String search) {
        String sql = "select * from product_table where concat(pid,pname,cname) like ? order by pid asc ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[10];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //cap nhat du lieu vao database Products
    public void update(int id, String pname, String cname, int qty, double price) {
        String sql = "update product_table set pname=?,cname=?, pquanlity=?,pprice=? where pid=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pname);
            ps.setString(2, cname);
            ps.setInt(3, qty);
            ps.setDouble(4, price);
            ps.setInt(5, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu  cập nhật thành công !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //xoa Produts 
    public void delete(int id) {
        int b = JOptionPane.showConfirmDialog(null, "Ban cho chac muon xoa Products nay khong", "Xoa Product", JOptionPane.OK_CANCEL_OPTION, 0);
        if (b == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from  product_table where pid=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Da xoa tai khoan Products");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
