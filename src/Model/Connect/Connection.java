package Model.Connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Connection {
    private final String url = "jdbc:mysql://localhost:3306/qtdl";  // Thay thế bằng thông tin đúng


    private final String username = "root";  // Thay thế bằng username của bạn
    private final String password = "123456789";  // Thay thế bằng mật khẩu của bạn
    
    // Phương thức kết nối tới MySQL
    public java.sql.Connection getJDBC() throws SQLException {
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
        return conn;
    }
}