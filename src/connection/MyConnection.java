
package connection;
import java.sql.*;

public class MyConnection {
  //Đây là chuỗi kết nối đến cơ sở dữ liệu SQL Server.
    private static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=shoping_online;user=sa;password=Hieuvlcm91;trustServerCertificate=true;characterEncoding=UTF-8";


    public static Connection getConnection() {
        Connection con = null;
        try {
            //để thiết lập kết nối với cơ sở dữ liệu. 
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected successfully.");
        } catch (SQLException ex) {
            System.out.println("Failed to connect.");
            ex.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
//        Connection connection = getConnection();
//        if (connection != null) {
//            try {
//                // Example: Retrieve data from the "Students" table
//                String query = "SELECT * FROM admin";
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(query);
//
//                // Process the ResultSet
//                while (resultSet.next()) {
//                    // Retrieve data from the ResultSet
//                    String id = resultSet.getString("email");
//                    String name = resultSet.getString("password");
//                    // Process retrieved data
//                    System.setProperty("console.encoding", "UTF-8");
//
//                    System.out.println("ID: " + id + ", Name: " +name);
//                    
//                }
//
//                // Close resources
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (SQLException ex) {
//                System.out.println("Error executing SQL query.");
//                ex.printStackTrace();
//            }
//        }
    }
    
    
}










