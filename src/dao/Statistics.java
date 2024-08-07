package dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Admin.admindashboard1;
import Suppiler.supplierdashboard1;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import Users.userdashboard1;

public class Statistics {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int Total(String tablename) {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) AS Tong FROM " + tablename);
            if (rs.next()) {
                total = rs.getInt("Tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
//tổng doanh thu từ bảng purchase_table trong cơ sở dữ liệu. 
    public double TotalSales() {
        double total = 0.0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT SUM(total) AS Tong FROM purchase_table");
            if (rs.next()) {
                total = rs.getDouble("Tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
//tổng doanh thu trong ngày hiện tại từ bảng purchase_table trong cơ sở dữ liệu.
    public double Totaldays() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        String today = df.format(date);

        double total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select sum(total) as 'Tong' from purchase_table where p_date = '" + today + "'");
            if (rs.next()) {
                total = rs.getDouble("Tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    //tinh tong so hoa don cua user da mua 
    public double TotalPurchase(int id) {
        double total = 0.0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT SUM(total) AS Tong FROM purchase_table where uid= " + id + "");
            if (rs.next()) {
                total = rs.getDouble("Tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    //tổng số lượng giao dịch đã được nhận từ một nhà cung cấp cụ thể
    public int Totaldeliveris(String name) {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) AS Tong FROM purchase_table where supplier = '" + name+ "' AND status =N'Đã nhận'");
            if (rs.next()) {
                total = rs.getInt("Tong");
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    //sử dụng để cập nhật thông tin trên bảng điều khiển của quản trị viên. 
    public void Admin() {
        admindashboard1.JCate.setText(String.valueOf(Total("category_table")));
        admindashboard1.JProducts.setText(String.valueOf(Total("product_table")));
        admindashboard1.JSuplier.setText(String.valueOf(Total("supplier_table")));
        admindashboard1.Jusers.setText(String.valueOf(Total("user_table")));
        admindashboard1.JSummonney.setText(String.valueOf(TotalSales()));
        admindashboard1.JTSales.setText(String.valueOf(Totaldays()));
    }
    //Users

    public void User(int id) {
        userdashboard1.jCate.setText(String.valueOf(Total("category_table")));
        userdashboard1.JProduct.setText(String.valueOf(Total("product_table")));
        userdashboard1.JPurchase.setText(String.valueOf(TotalPurchase(id)));

    }
    public void Supplier(String name){
        supplierdashboard1.JDelivery.setText(String.valueOf(Totaldeliveris(name)));
    }
}
