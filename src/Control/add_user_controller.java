/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.UserModel.ListStaff;
import Model.UserModel.Staff;
import Model.UserModel.User_Account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

/**
 *
 * @author vntin
 */
public class add_user_controller {
    
    public boolean checkID_staff(String id_staff){
        boolean check = false;
        for (Staff staff : new ListStaff().ListStaff_MySQL()){
            if (staff.getId().equals(id_staff)){
                check = true;
            }
        }
        return check;
    }
    
    
    public String create_Staff_id(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        String id_staff = new String("STF" + randomString.toString());
        
        while (checkID_staff(id_staff)){
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(characters.length());
                randomString.append(characters.charAt(index));
            }
            id_staff = new String("STF" + randomString.toString());
        }
        return id_staff;
    }
    
    public void insertStaff(Staff staff){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();

            // Câu lệnh SQL để chèn dữ liệu
            String sql = "INSERT INTO staff (id_staff, fullname_staff, phone_staff, birthDate_staff, position, id_account) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số
            stmt.setString(1, staff.getId());
            stmt.setString(2, staff.getFullname());
            stmt.setString(3, staff.getPhone_number());
            stmt.setDate(4, java.sql.Date.valueOf(staff.getBirthDate()));
            stmt.setString(5, staff.getPosition());
            stmt.setString(6, staff.getAccount().getIdAccount());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Insert không thành công. Lỗi: " + ex.getMessage());

        } finally {
            // Đóng tài nguyên
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public void insertAccount(User_Account account){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();

            // Câu lệnh SQL để chèn dữ liệu
            String sql = "INSERT INTO account (id_account, username, passwd) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, account.getIdAccount());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getPassword());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Insert không thành công. Lỗi: " + ex.getMessage());

        } finally {
            // Đóng tài nguyên
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public boolean vertify_passwd(String id, String id_vertify){
        return id.equals(id_vertify);
    }
    
    
    public String create_id_account(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        String id_account = new String("ACC" + randomString.toString());
        return id_account;
    }
    
    
}
