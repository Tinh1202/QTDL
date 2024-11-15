/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.UserModel.Customer;
import Model.UserModel.List_Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
/**
 *
 * @author vntin
 */
public class customer_controller {
    
    public DefaultTableModel setDefaultTable(){
        String[] columns = {"ID", "Name", "Phone", "Birthdate", "Address"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Customer customer : new List_Customer().ListCustomer_MySQL()){
            Object[] row = {
              customer.getId(), 
                customer.getFullname(), 
                customer.getPhone_number(), 
                customer.getBirthDate(),
                customer.getAddress()
            };
            
            model.addRow(row);
        }
        return model;
    }
    
    public void insertCustomer(Customer customer){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();

            // Câu lệnh SQL để chèn dữ liệu
            String sql = "INSERT INTO customer (id_customer, fullname_customer, phone_customer, birthDate_customer, address_customer) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getFullname());
            stmt.setString(3, customer.getPhone_number());
            stmt.setString(4, customer.getBirthDate().format(DateTimeFormatter.ISO_DATE));
            stmt.setString(5, customer.getAddress());

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
    
    
    public boolean checkCustomerAny_DetailDeliverynote(Customer customer) {
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;
    try {
        // Kết nối cơ sở dữ liệu
        Model.Connect.Connection c = new Model.Connect.Connection() {};
        conn = c.getJDBC();

        String sql = "SELECT customer_exists(?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, customer.getId());

        // Sử dụng executeQuery() để thực thi câu lệnh SELECT
        resultSet = stmt.executeQuery();

        // Kiểm tra kết quả
        if (resultSet.next()) {
            return resultSet.getBoolean(1);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Lỗi: " + ex.getMessage());

    } finally {
        // Đóng tài nguyên
        try {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return false;
}

    
    public void deleteCustomer(Customer customer){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();

            if (checkCustomerAny_DetailDeliverynote(customer)){
                System.out.println("Can not delete customer");
            } else {
                String sql = "delete from customer where id_customer = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, customer.getId());
                stmt.executeUpdate();
            }
            
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
    
    
    public void updateCustomer(Customer customer){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();

            String sql = "UPDATE customer SET fullname_customer = ?, phone_customer = ?, birthDate_customer = ?, address_customer = ? WHERE id_customer = ?";
            stmt = conn.prepareStatement(sql);
            
            
            
            stmt.setString(1, customer.getFullname());
            stmt.setString(2, customer.getPhone_number());
            stmt.setDate(3, java.sql.Date.valueOf(customer.getBirthDate()));
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getId());
            
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
    
    
    public boolean check_id_customer(String id_customer){
        boolean check = false;
        for (Customer customer : new List_Customer().ListCustomer_MySQL()){
            if (customer.getId().equals(id_customer)){
                check = true;
            }
        }
        return check;
    }
    
    public String create_random_idCustomer(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        String id_customer = new String("CUST" + randomString.toString());
        
        while(check_id_customer(id_customer)){
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(characters.length());
                randomString.append(characters.charAt(index));
            }
            id_customer = new String("CUST" + randomString.toString());
        }
        
        return id_customer;
    }
    
    
    
    
}
