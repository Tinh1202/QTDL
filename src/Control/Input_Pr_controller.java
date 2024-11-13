/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Delivery.Detail_PRN;
import Model.Delivery.ListProduct_Receipt;
import Model.Delivery.Product_Receipt;
import Model.DeviceModel.ListDevice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntin
 */
public class Input_Pr_controller {
    
    // hàm kiểm tra có trùng id 
    public boolean check_id_pr(String id){
        ArrayList<Product_Receipt> lst_pr = new ListProduct_Receipt().ListPR_MySQL();
        boolean check = false;
        for (Product_Receipt pr : lst_pr){
            if (pr.getId_PRN().equals(id)){
                check = true;
                break;
            }
        }
        return check;
    }
    
    
    public String random_id_product_receipt(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        String id_new_prn = new String("PR" + randomString.toString());
        
        while (check_id_pr(id_new_prn)){
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(characters.length());
                randomString.append(characters.charAt(index));
                id_new_prn = new String("PR" + randomString.toString());
            }
        }
        
        return id_new_prn;
    }
    
    
    public void insert_product_receipt(Product_Receipt pr) {
    java.sql.Connection conn = null;
    PreparedStatement stmt = null;

    try {
        // Kết nối cơ sở dữ liệu
        Model.Connect.Connection c = new Model.Connect.Connection() {};
        conn = c.getJDBC();
        
        // Câu lệnh SQL để chèn dữ liệu
        String sql = "INSERT INTO product_receipt (id_product_receipt, date_import, id_staff, id_supplier) VALUES (?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);

        // Thiết lập giá trị cho các tham số
        stmt.setString(1, pr.getId_PRN());
        
        // Chuyển đổi LocalDateTime sang Timestamp
        if (pr.getDateImport() != null) {
            stmt.setTimestamp(2, Timestamp.valueOf(pr.getDateImport()));
        } else {
            stmt.setNull(2, java.sql.Types.TIMESTAMP);
        }

        stmt.setString(3, pr.get_staff().getId());
        stmt.setString(4, pr.get_supplier().getIDSupplier());

        // Thực thi câu lệnh
        stmt.executeUpdate();
        System.out.println("Insert thành công!");

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
    
    
    public void delete_product_receipt(Product_Receipt pr){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            conn = c.getJDBC();
            String sql = "DELETE FROM product_receipt WHERE id_product_receipt = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pr.getId_PRN());

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
    
    
    // set lại bảng trong mysql
    public DefaultTableModel setDefaultTable() {
        String[] columns_list_pr = {"ID Product Receipt", "Date import", "Supplier", "Total cost"};
        DefaultTableModel model = new DefaultTableModel(columns_list_pr, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
        ArrayList<Product_Receipt> lst_pr = new ListProduct_Receipt().ListPR_MySQL();
        for (Product_Receipt pr : lst_pr){
            double total_cost = 0.0;
            for (Detail_PRN dpr : pr.getListDetailPRN()){
                total_cost += new ListDevice().getDevice_MySQL(dpr.getId_device()).getPrice() * dpr.getNumber();
            }
            Object[] row = {
                pr.getId_PRN(), 
                pr.getDateImport(), 
                pr.get_supplier().getNameSupplier(), 
                total_cost
            };
            model.addRow(row);
        }
        return model;
    }
    
//    public static void main(String[] args){
//        Product_Receipt pr = new Product_Receipt("001",);
//    }
}
