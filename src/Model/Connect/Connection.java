package Model.Connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Connection {
    private final String url = "jdbc:mysql://localhost:3306/test";  // Thay thế bằng thông tin đúng
    private final String username = "root";  // Thay thế bằng username của bạn
    private final String password = "tvo229033@gmail";  // Thay thế bằng mật khẩu của bạn
    
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
    // Phương thức lấy dữ liệu và hiển thị
//    public void fetchData() {
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        
//        try {
//            // 1. Mở kết nối
//            conn = getJDBC();
//
//            // 2. Tạo statement để thực hiện truy vấn
//            stmt = conn.createStatement();
//
//            // 3. Thực thi truy vấn
//            String sql = "SELECT * from account;"; // Thay "users" bằng bảng của bạn
//            rs = stmt.executeQuery(sql);
//
//            // 4. Xử lý kết quả truy vấn
//            System.out.println("Dữ liệu từ bảng:");
//            while (rs.next()) {
//                // Lấy dữ liệu từ cột
//                String username = rs.getString("username");
//                
//
//                // Hiển thị dữ liệu ra console
//                System.out.println("Username: " + username);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 5. Đóng các tài nguyên để tránh rò rỉ
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Connection conn = new Connection();
//        conn.fetchData();  // Gọi phương thức để lấy và hiển thị dữ liệu
//    }
}