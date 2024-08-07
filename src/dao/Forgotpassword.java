package dao;

import Users.forgetpassword;
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Forgotpassword {
//được gọi để thiết lập kết nối đến cơ sở dữ liệu.
    Connection con = MyConnection.getConnection();
//một giao diện trong gói java.sql được sử dụng để thực thi các truy vấn đã được biên dịch (prepared) trong cơ sở dữ liệu.
    PreparedStatement ps;
//sử dụng để thực thi các truy vấn tĩnh trong cơ sở dữ liệu.
    Statement st;
// là một giao diện trong gói java.sql được sử dụng để lưu trữ kết quả của một truy vấn cơ sở dữ liệu
    ResultSet rs;

    
//kiểm tra xem email này có tồn tại trong cơ sở dữ liệu hay không.

    public boolean isEmailexits(String email) {
        try {
            ps = con.prepareStatement("select * from user_table where uemail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                forgetpassword.jTextField1.setText(rs.getString(5));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Email vua nhap khong ton tai");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Forgotpassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
 //phải là câu trả lời đúng cho câu hỏi bảo mật của email đã cho hay không.
    public boolean getanswer(String email, String newanser) {
        try {
            ps = con.prepareStatement("select * from user_table where uemail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String answer = rs.getString(6);
                if (newanser.equals(answer)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Cau tra loi cua ban khong dung");

                    return false;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Forgotpassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    //đặt mật khẩu mới
//    public void setpasswordnew(String email, String password) {
//        String sql = "update user_table set upassword=? where uemail=?";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, password);
//            ps.setString(2, email);
//           if(ps.executeUpdate()>0){
//               JOptionPane.showMessageDialog(null, "Mat khau da cap nhap thanh cong");
//
//           }
//        } catch (SQLException ex) {
//            Logger.getLogger(Forgotpassword.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
 // cập nhật mật khẩu mới cho email tương ứng trong cơ sở dữ liệu.
    public void setpasswordnew(List<String> params) {
    String email = params.get(0);
    String password = params.get(1);
    
    String sql = "update user_table set upassword=? where uemail=?";
    try {
        ps = con.prepareStatement(sql);
        
        // Đặt các giá trị từ List vào PreparedStatement
        ps.setString(1, password);
        ps.setString(2, email);

        if (ps.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Mật khẩu đã được cập nhật thành công");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Forgotpassword.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
