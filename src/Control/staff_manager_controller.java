/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Connect.Connection;
import Model.Delivery.Detail_PRN;
import Model.Delivery.ListProduct_Receipt;
import Model.Delivery.Product_Receipt;
import Model.DeviceModel.ListDevice;
import Model.UserModel.ListStaff;
import Model.UserModel.Staff;
import Model.UserModel.User_Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntin
 */
public class staff_manager_controller {
    
    public DefaultTableModel setDefaultTable(){
        String[] columns = {"ID staff", "fullname" , "Phone", "Birthdate", "Position"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        for (Staff staff : new ListStaff().ListStaff_MySQL()){
            Object[] row = {
                staff.getId(), 
                staff.getFullname(),
                staff.getPhone_number(),
                staff.getBirthDate(), 
                staff.getPosition()
            };
            model.addRow(row);
        }
        return model;
    }
    
    public void updateStaff_mysql(Staff staff){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "UPDATE staff SET fullname_staff = ?, phone_staff = ?, birthDate_staff = ?, position = ? WHERE id_staff = ?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, staff.getFullname());
            stmt.setString(2, staff.getPhone_number());
            stmt.setString(3, staff.getBirthDate().format(DateTimeFormatter.ISO_DATE));
            stmt.setString(4, staff.getPosition());
            stmt.setString(5, staff.getId());
            
            stmt.executeUpdate();
                                    
        } catch (SQLException ex) {
             ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public void delete_UserAccount(User_Account account){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "delete from account where id_account = ?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, account.getIdAccount());
            
            stmt.executeUpdate();
                                    
        } catch (SQLException ex) {
             ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public void delete_Staff(Staff staff){
        // detele account first
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "delete from staff where id_staff = ?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, staff.getId());
            
            stmt.executeUpdate();
                                    
        } catch (SQLException ex) {
             ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        delete_UserAccount(staff.getAccount());
    }
    
}
