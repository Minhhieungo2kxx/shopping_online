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

public class CategoryDao {

    Connection con = MyConnection.getConnection();
 //được sử dụng để thực thi các câu lệnh SQL được tham số hóa
    PreparedStatement ps;
 //được sử dụng để thực thi các câu lệnh SQL tĩnh, tức là các câu lệnh không chứa tham số.
    Statement st;
    ResultSet rs;

   //giá trị lớn nhất của cột cid trong bảng category_table
    public int getmaxrow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(cid) from category_table");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

   // kiểm tra xem cname này có tồn tại trong cơ sở dữ liệu hay không. 
    public boolean isCategoryexits(String cname) {
        try {
            ps = con.prepareStatement("select * from category_table where cname=?");
            ps.setString(1, cname);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

   //thêm một bản ghi mới vào bảng category_table trong cơ sở dữ liệu với các giá trị tương ứng
    public void insert(int id, String cname, String desc) {
        String sql = "INSERT INTO category_table (cid,cname,cdesc) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, cname);
            ps.setString(3, desc);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Category added  thanh cong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //truy vấn cơ sở dữ liệu để lấy các bản ghi từ bảng category_table mà có cid hoặc cname
    //Tạo một câu lệnh SQL để lấy tất cả các bản ghi từ bảng category_table mà có cid hoặc cname chứa chuỗi search
//DefaultTableModel là một lớp trong Java Swing được sử dụng JTable. Nó cung cấp các phương thức để thao tác với dữ liệu bảng, bao gồm thêm và xóa hàng, cũng như thêm và xóa cột
    public void getCategoryTable(JTable table, String search) {
        String sql = "select * from category_table where concat(cid,cname) like ? order by cid asc ";
        try {
            ps = con.prepareStatement(sql);
 
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //cap nhat du lieu vao database Catagory
    public void update(int id, String name, String desc) {
        String sql = "update category_table set cname=?,cdesc=? where cid=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu  cập nhật thành công !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //kiểm tra  id Category đã tồn tại hay chua
    public boolean isIDexits(int id) {
        try {
            ps = con.prepareStatement("select * from category_table where cid=?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
   

//Hàm delete(int id) cho phép người dùng xác nhận trước khi xóa một danh mục khỏi cơ sở dữ liệu. 
    public void delete(int id) {
        int b = JOptionPane.showConfirmDialog(null, "Ban cho chac muon xoa Category nay khong", "Xoa tai khoan", JOptionPane.OK_CANCEL_OPTION, 0);
        if (b == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from category_table where cid=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Da xoa tai khoan Category");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
