/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Delivery.Detail_PRN;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import Model.Connect.Connection;
import Model.Delivery.ListDetailPRN;
import Model.DeviceModel.Device;
import Model.DeviceModel.ListDevice;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author vntin
 */
public class input_detail_controller {
    public boolean check_id(Detail_PRN detail_product_receipt, ArrayList<Detail_PRN> lst_detail_pr){
        boolean check = false;
        for (Detail_PRN detail : lst_detail_pr){
            if (detail.getIdDetail_PR().equals(detail_product_receipt.getIdDetail_PR())){
                check = true;
                break;
            }
        }
        return check;
    }
    
    public void insert_detail_prn_into_mysql(Detail_PRN detail_product_receipt){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "insert into detail_product_receipt (id_detail_pr, id_product_receipt, id_device, quantity) values (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, detail_product_receipt.getIdDetail_PR());
            stmt.setString(2, detail_product_receipt.getId_prn());
            stmt.setString(3, detail_product_receipt.getId_device());
            stmt.setInt(4, detail_product_receipt.getNumber());
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
    
    
    public void detete_detail_pr(Detail_PRN detail_product_receipt){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "DELETE FROM detail_product_receipt WHERE id_detail_pr = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, detail_product_receipt.getIdDetail_PR());

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
    
    public boolean check_id_random(String id){
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> lst_id = new ArrayList<String>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();

            String sql = "SELECT * FROM detail_product_receipt;";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                lst_id.add(rs.getString("id_detail_pr"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        boolean check = false; 
        
        for (String id_detail : lst_id){
            if (id.equals(id_detail)){
                check = true;
            }
        }
        
        return false;
    }
    
    
    public String create_random_id(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        String id_new_prn = new String("DPR" + randomString.toString());
        
        while (check_id_random(id_new_prn)){
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(characters.length());
                randomString.append(characters.charAt(index));
                id_new_prn = new String("DPR" + randomString.toString());
            }
        }
        
        return id_new_prn;
    }
    
    
    
    public DefaultTableModel setDefaultTable(){
        String[] columns_detail_prn = {"ID detail PR", "ID PR", "ID device", "Device's name", "Number", "Total devices"};
        DefaultTableModel model_detail = new DefaultTableModel(columns_detail_prn, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
        ArrayList<Detail_PRN> lst_detail_pr = new ListDetailPRN().ListDPRN_MySQL();
        for (Detail_PRN detail_prn : lst_detail_pr){
            Device device = new ListDevice().getDevice_MySQL(detail_prn.getId_device());
            Object[] row = {
                detail_prn.getIdDetail_PR(),
                detail_prn.getId_prn(),
                detail_prn.getId_device(),
                device.getNameDevice(),
                detail_prn.getNumber(),
                detail_prn.getNumber() * device.getPrice()
            };
            model_detail.addRow(row);
        }
        
        return model_detail;
    }
    
    
    public DefaultTableModel setDefaultTable(ArrayList<Detail_PRN> lst_drn){
        String[] columns_detail_prn = {"ID detail PR", "ID PR", "ID device", "Device's name", "Number", "Total devices"};
        DefaultTableModel model_detail = new DefaultTableModel(columns_detail_prn, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
        for (Detail_PRN detail_prn : lst_drn){
            Device device = new ListDevice().getDevice_MySQL(detail_prn.getId_device());
            Object[] row = {
                detail_prn.getIdDetail_PR(),
                detail_prn.getId_prn(),
                detail_prn.getId_device(),
                device.getNameDevice(),
                detail_prn.getNumber(),
                detail_prn.getNumber() * device.getPrice()
            };
            model_detail.addRow(row);
        }
        
        return model_detail;
    }
    
    public DefaultTableModel DefaultTable(){
        String[] columns_detail_prn = {"ID detail PR", "ID PR", "ID device", "Device's name", "Number", "Total devices"};
        DefaultTableModel model_detail = new DefaultTableModel(columns_detail_prn, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        return model_detail;
    }
    
    
    public void updateDetailPRN(Detail_PRN detail_prn) {
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;

        try {
            // Kết nối cơ sở dữ liệu
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            System.out.println("Kết nối thành công!");

            // Kiểm tra giá trị các tham số đầu vào
            System.out.println("ID Device: " + detail_prn.getId_device());
            System.out.println("Quantity: " + detail_prn.getNumber());
            System.out.println("ID Detail PR: " + detail_prn.getIdDetail_PR());

            String sql = "UPDATE detail_product_receipt SET id_device = ?, quantity = ? WHERE id_detail_pr = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, detail_prn.getId_device());
            stmt.setInt(2, detail_prn.getNumber());
            stmt.setString(3, detail_prn.getIdDetail_PR());

            int rowsUpdated = stmt.executeUpdate(); // Lấy số dòng cập nhật
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công! Số dòng cập nhật: " + rowsUpdated);
            } else {
                System.out.println("Không có dòng nào được cập nhật. Kiểm tra lại ID Detail PR.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Cập nhật không thành công. Lỗi: " + ex.getMessage());

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
}
