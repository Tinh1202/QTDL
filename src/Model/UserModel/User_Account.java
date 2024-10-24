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

public class User_Account { // account login 
    private String username;
    private String password;

    // Mặc định constructor
    public User_Account() {
        this.username = "";
        this.password = "";
    }

    // Tham số constructor
    public User_Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // create getter, setter
    /*
        code *
    */
    
    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

     // SHA-256/bcrypt
    public User_Account hashingAccount(String username, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(username.getBytes());
            byte[] hashedUsername = md.digest();
            md.update(password.getBytes());
            byte[] hashedPassword = md.digest();
            return new User_Account(bytesToHex(hashedUsername), bytesToHex(hashedPassword));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // chuyển đổi bytes to hex
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    //Kiểm tra thông tin tài khoản
    public boolean CheckAccount(User_Account account) {
        // Simulate database check (this should be replaced with actual database logic)
        User_Account dbAccount = hashingAccount(account.getUsername(), account.getPassword());
        return this.username.equals(dbAccount.getUsername()) && this.password.equals(dbAccount.getPassword());
    }

    // Phương thức đăng nhập
    public void Login(String username, String password) {
        User_Account hashedAccount = hashingAccount(username, password);
        if (CheckAccount(hashedAccount)) {
            System.out.println("Login successful!");
            WriteLog();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    //Phương thức ghi lại nhật ký
    private void WriteLog() {
        System.out.println("User " + this.username + " logged in at " + java.time.LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "User_Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
