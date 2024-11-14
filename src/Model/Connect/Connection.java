package Model.Connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Connection {
    private final String url = "jdbc:mysql://localhost:3306/quanly_maytinh";  // Thay thế bằng thông tin đúng


    private final String username = "root";  // Thay thế bằng username của bạn
    private final String password = "123456789";  // Thay thế bằng mật khẩu của bạn
    
    // Phương thức kết nối tới MySQL
    public java.sql.Connection getJDBC() throws SQLException {
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
        return conn;
    }
    public int getRowCount(String tableName) {
        int rowCount = 0;
        try (java.sql.Connection conn = getJDBC()) {  // Sử dụng rõ ràng java.sql.Connection
            Statement stmt = conn.createStatement();
            String query = "SELECT COUNT(*) FROM " + tableName;  // Truy vấn số dòng trong bảng
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                rowCount = rs.getInt(1);  // Lấy số lượng dòng từ kết quả truy vấn
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin số dòng: " + e.getMessage());
        }
        return rowCount;  // Trả về số dòng
    }
    public static void main(String args[]) throws SQLException{
        Connection a = new Connection();
        a.getJDBC();
    }
}