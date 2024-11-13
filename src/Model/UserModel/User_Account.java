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
    
    public String getIdAccount(){
        return this.id_account;
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


    // check account
    public boolean Check_Account(String username, String password) {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User_Account account = null;

        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "SELECT * FROM account WHERE username = ? AND passwd = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                account = new User_Account();
                account.setIdAccount(rs.getString("id_account"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("passwd"));

                // Thiết lập tài khoản đã đăng nhập vào SessionManager
                Session_account.getInstance().setLoggedInUser(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return account != null;
    }

    
    public User_Account getAccount_Mysql(String id) {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User_Account account = null;

        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "SELECT * FROM account WHERE id_account = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                account = new User_Account();
                account.setIdAccount(rs.getString("id_account"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("passwd"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

}
