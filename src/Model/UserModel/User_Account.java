/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.UserModel;

/**
 *
 * @author vntin
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import Model.Connect.*;
import Model.Delivery.Detail_DeliveryNote;
import java.sql.SQLException;
import java.sql.*;

public class User_Account { // account login 
    private String id_account;
    private String username;
    private String password;

    // Mặc định constructor
    public User_Account() {
        this.id_account = new String("");
        this.username = new String("");
        this.password = new String("");
    }

    // Tham số constructor
    public User_Account(String id_account, String username, String password) {
        this.id_account = new String(id_account);
        this.username = new String(username);
        this.password = new String(password);
    }
    
    // create getter, setter
    /*
        code *
    */
    public void setIdAccount(String id_account){
        this.id_account = new String(id_account);
    }
    
    
    // Getter for username
    public String getUsername() {
        return this.username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = new String(username);
    }

    // Getter for password
    public String getPassword() {
        return this.password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = new String(password);
    }

     // SHA-256/bcrypt
//    public User_Account hashingAccount(String username, String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(username.getBytes());
//            byte[] hashedUsername = md.digest();
//            md.update(password.getBytes());
//            byte[] hashedPassword = md.digest();
//            return new User_Account(bytesToHex(hashedUsername), bytesToHex(hashedPassword));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // chuyển đổi bytes to hex
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02x", b));
//        }
//        return sb.toString();
//    }
//
//    //Kiểm tra thông tin tài khoản
//    public boolean CheckAccount(User_Account account) {
//        // Simulate database check (this should be replaced with actual database logic)
//        User_Account dbAccount = hashingAccount(account.getUsername(), account.getPassword());
//        return this.username.equals(dbAccount.getUsername()) && this.password.equals(dbAccount.getPassword());
//    }
//
//    // Phương thức đăng nhập
//    public void Login(String username, String password) {
//        User_Account hashedAccount = hashingAccount(username, password);
//        if (CheckAccount(hashedAccount)) {
//            System.out.println("Login successful!");
//            WriteLog();
//        } else {
//            System.out.println("Login failed. Invalid username or password.");
//        }
//    }
//
//    //Phương thức ghi lại nhật ký
//    private void WriteLog() {
//        System.out.println("User " + this.username + " logged in at " + java.time.LocalDateTime.now());
//    }

    public User_Account getAccount_MySQL(String username, String password) {
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    User_Account account = null;  // Đặt thành null để chỉ tạo khi có kết quả

    try {
        Model.Connect.Connection c = new Model.Connect.Connection();
        conn = c.getJDBC();
        String sql = "SELECT * FROM account WHERE username = ? AND passwd = ?"; // Thay "account" bằng tên bảng thực tế

        // Chuẩn bị câu lệnh SQL với các tham số
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);  // Tham số thứ nhất
        stmt.setString(2, password);  // Tham số thứ hai

        // Thực thi truy vấn
        rs = stmt.executeQuery();

        // Lấy dữ liệu từ ResultSet
        if (rs.next()) {
            account = new User_Account();
            account.setIdAccount(rs.getString("id_account"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("passwd"));
            // Thiết lập thêm các thuộc tính khác của User_Account nếu có
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng các tài nguyên
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return account;
}

    
    
    public User_Account getAccount_MySQL(String id_account) {
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    User_Account account = null;  // Đặt thành null để chỉ tạo khi có kết quả

    try {
        Model.Connect.Connection c = new Model.Connect.Connection();
        conn = c.getJDBC();
        String sql = "SELECT * FROM account WHERE id_account = ?"; // Thay "account" bằng tên bảng thực tế

        // Chuẩn bị câu lệnh SQL với các tham số
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id_account);  // Tham số thứ nhất

        // Thực thi truy vấn
        rs = stmt.executeQuery();

        // Lấy dữ liệu từ ResultSet
        if (rs.next()) {
            account = new User_Account();
            account.setIdAccount(rs.getString("id_account"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("passwd"));
            // Thiết lập thêm các thuộc tính khác của User_Account nếu có
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng các tài nguyên
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return account;
}
    
    
    
    @Override
    public String toString() {
        return "User_Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    
    //done
    public static void main(String[] args){
        User_Account account = new User_Account();
        account = account.getAccount_MySQL("ACC001");
        System.out.println(account.toString());
        if (account == null) {
            System.out.print("null");
        }
        
    }
}
