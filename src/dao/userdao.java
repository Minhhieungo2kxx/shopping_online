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

public class userdao {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    //lấy hàng tối đa của bảng người dùng
    public int getmaxrow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(uid) from user_table");
            while (rs.next()) {
                row = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    //kiểm tra email đã tồn tại
    public boolean isEmailexits(String email) {
        try {
            ps = con.prepareStatement("select * from user_table where uemail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    //chèn dữ liệu vào bảng sử dụng

    public void insert(String username, String email, String password,
            String question, String answer, String address) {
        String sql = "INSERT INTO user_table (uname,uemail, upassword, usecquestion, uanswer, uaddress) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, question);
            ps.setString(5, answer);
            ps.setString(6, address);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Nguoi dung dang ki thanh cong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//lay gia tri nguoi dung ra

    public String[] getvalueuser(int id) {
        String[] value = new String[7];
        try {
            ps = con.prepareStatement("select * from user_table where uid=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);

            }

        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;

    }
//lay gia tri id nguoi dung

    public int getiduser(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select uid from user_table where uemail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }
    //xoa tai khoan nguoi 

    public void delete(int id) {
        int b = JOptionPane.showConfirmDialog(null, "Ban cho chac muon xoa tai khoan nay khong", "Xoa tai khoan", JOptionPane.OK_CANCEL_OPTION, 0);
        if (b == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from user_table where uid=?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Da xoa tai khoan");
                }
            } catch (SQLException ex) {
                Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    //lay du lieu nguoi dung
    public void getUserTable(JTable table, String search) {
        String sql = "select * from user_table where concat(uid,uname,uemail) like ? order by uid asc ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//cap nhat du lieu vao database user
    public void update(int id,String username, String email, String password,
            String question, String answer, String address) {
        String sql = "update user_table set uname=?,uemail=?, upassword=?, usecquestion=?, uanswer=?, uaddress=? where uid=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, question);
            ps.setString(5, answer);
            ps.setString(6, address);
            ps.setInt(7, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Dữ liệu  cập nhật thành công !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
