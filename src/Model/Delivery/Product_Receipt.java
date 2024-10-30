/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import Model.UserModel.Staff;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;
import java.util.ArrayList;
/**
 *
 * @author vntin
 */
public class Product_Receipt {  // phiếu nhập ( Nhập sản phẩm vào kho) 
    private String id_prn; // mã phiếu nhập
    private LocalDateTime date_import;  // ngày nhập kho
    private String id_staff; // id nhân viên
    private String id_supplier; // mã nhà cung cấp
    
    private ArrayList<Detail_PRN> listDetailPRN; // danh sách các chi tiết phiếu
    
    
    public Product_Receipt() {
        this.id_prn = new String("");
        this.id_staff = new String("");
        this.id_supplier = new String("");
        LocalDate ld = LocalDate.of(1990, 01, 01);
        LocalTime lt = LocalTime.of(01, 01, 01);
        this.date_import = LocalDateTime.of(ld, lt);
        this.listDetailPRN = new ArrayList<Detail_PRN>();
    }

    public Product_Receipt(String id_prn,
            String id_supplier,
            String id_staff,
            LocalDateTime date_import,
            ArrayList<Detail_PRN> listDetailPRN) {
        
        this.id_prn = new String(id_prn); 
        this.id_staff = new String(id_staff);
        this.date_import = date_import;
        this.id_supplier = new String(id_supplier);
        this.listDetailPRN = new ArrayList<Detail_PRN>(listDetailPRN);
    }
    
    public Product_Receipt(Object product_Rece){
        if(product_Rece instanceof Product_Receipt){
            Product_Receipt pr = new Product_Receipt(product_Rece);
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
            this.date_import = pr.date_import;
            this.listDetailPRN = new ArrayList<Detail_PRN>(pr.listDetailPRN);
        } else{
            Product_Receipt pr = new Product_Receipt();
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
            this.date_import = pr.date_import;
            this.listDetailPRN = new ArrayList<Detail_PRN>(pr.listDetailPRN);
        }
    }

    //define getter, setter
    public String getId_PRN(){
        return new String(this.id_prn);
    }
    public void setId_PRN(String id_prn){
        this.id_prn = new String(id_prn);
    }
    public String getId_staff(){
        return new String(this.id_staff);
    }
    public void setId_staff(String id_staff){
        this.id_staff = new String(id_staff);
    }
    public String getId_supplier(){
        return new String(this.id_supplier);
    }
    public void setId_supplier(String id_supplier){
        this.id_supplier = new String(this.id_supplier);
    }
    
    public LocalDateTime getDateImport(){ // localDateTime type
        return this.date_import; // YYYY-MM-DD HH-MM-SS
    }

    public void setDateImport(LocalDateTime ldt) {
        this.date_import = ldt;
    }
    
    public ArrayList<Detail_PRN> getListDetailPRN(){
        return new ArrayList<Detail_PRN>(this.listDetailPRN);
    }
    
    public void setListDetailPRN(ArrayList<Detail_PRN> lst){
//        ListDetailPRN l = new ListDetailPRN(lst);
        
        this.listDetailPRN = lst;
    }
    
//    public void setListDetailPRN(ListDetailPRN listDetailPRN) {
//    try {
//        // Kiểm tra nếu listDetailPRN là null, gán một ListDetailPRN rỗng để tránh NullPointerException
//        if (listDetailPRN == null) {
//            this.listDetailPRN = new ListDetailPRN(new ArrayList<>()); // Khởi tạo với danh sách rỗng
//        } else {
//            this.listDetailPRN = new ListDetailPRN(listDetailPRN); // Khởi tạo với giá trị được truyền vào
//        }
//        
//        // In độ dài danh sách sau khi khởi tạo
//        System.out.print(this.listDetailPRN.getLengthListDPRN());
//    } catch (Exception e) {
//        System.out.print("Lỗi: " + e.getMessage());
//    }
//}

    
    private double Total_Price(){  // return total price of devices from detail_prn list
        return 0.00;
    }
    
    @Override
    public String toString(){
        return "Id: " + this.getId_PRN() + "\n"
                + "Id staff: " + this.getId_staff() + "\n"
                + "Id supplier: " + this.getId_supplier() + "\n"
                + "Date: " + this.getDateImport().format(DateTimeFormatter.ISO_DATE);
    }
    
    
    // Lấy phiếu nhập từ cơ sở dữ liệu dùng ID
//    public Product_Receipt getPR_MySQL(String id){
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        Product_Receipt pr = new Product_Receipt();
//        ListDetailPRN listDetailPRN = new ListDetailPRN();
//        
//        try {
//            Model.Connect.Connection c = new Connection();
//            conn = c.getJDBC();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM Product_Receipt WHERE id_prn=" + id +";"; // Thay "users" bằng bảng của bạn
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String id_prn = new String(rs.getString("id_prn"));
//                LocalDateTime date_import = rs.getTimestamp("date_import").toLocalDateTime();
//                String id_staff = new String(rs.getString("id_staff"));
//                String id_supplier = new String(rs.getString("id_supplier"));
//                pr.setId_PRN(id_prn); pr.setDateImport(date_import); pr.setId_staff(id_staff); pr.setId_supplier(id_supplier);
//                
//                // lấy các chi tiết phiếu nhập (Detail PRN) có id = id_prn trong csdl
//                
//                ArrayList<Detail_PRN> listDPRN = new ArrayList<Detail_PRN>(listDetailPRN.ListDPRN_MySQL(id));
//                ListDetailPRN listDetailPRN_new = new ListDetailPRN(listDPRN);
//                pr.setListDetailPRN(listDetailPRN_new);
//                
//                // -> sau đó setter cho PR
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return new Product_Receipt(pr);
//    }
    public Product_Receipt getPR_MySQL(String id) {
    java.sql.Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Product_Receipt pr = new Product_Receipt();
    ListDetailPRN listDetailPRN = new ListDetailPRN();

    try {
        Model.Connect.Connection c = new Connection();
        conn = c.getJDBC();

        String sql = "SELECT * FROM Product_Receipt WHERE id_prn = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        rs = pstmt.executeQuery();
        if (rs.next()) {
            String id_prn = rs.getString("id_prn");
            LocalDateTime date_import = rs.getTimestamp("date_import").toLocalDateTime();
            String id_staff = rs.getString("id_staff");
            String id_supplier = rs.getString("id_supplier");

            pr.setId_PRN(id_prn);
            pr.setDateImport(date_import);
            pr.setId_staff(id_staff);
            pr.setId_supplier(id_supplier);
            
            // Lấy các chi tiết phiếu nhập (Detail PRN) có id = id_prn trong cơ sở dữ liệu
            ArrayList<Detail_PRN> listDPRN = new ArrayList<>(listDetailPRN.ListDPRN_MySQL(id_prn));
//            ListDetailPRN listDetailPRN_new = new ListDetailPRN(listDPRN);
            pr.setListDetailPRN(listDPRN);
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

    return new Product_Receipt(pr);
}

      
        
}
