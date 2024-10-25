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
    
    private ArrayList<Detail_PRN> ListDetailPRN; // danh sách các chi tiết phiếu
    
    
    public Product_Receipt() {
        this.id_prn = new String("");
        this.id_staff = new String("");
        this.id_supplier = new String("");
        LocalDate ld = LocalDate.of(1990, 01, 01);
        LocalTime lt = LocalTime.of(01, 01, 01);
        this.date_import = LocalDateTime.of(ld, lt);
        this.ListDetailPRN = new ArrayList<Detail_PRN>();
    }

    public Product_Receipt(String id_prn,
            String id_supplier,
            String id_staff,
            LocalDate localdate,
            LocalTime localtime,
            ArrayList ListDetailPRN) {
        
        this.id_prn = new String(id_prn); 
        this.id_staff = new String(id_staff);
        this.date_import = LocalDateTime.of(localdate, localtime);
        this.id_supplier = new String(id_supplier);
        this.ListDetailPRN = new ArrayList<>(ListDetailPRN);
    }
    
    public Product_Receipt(Object product_Rece){
        if(product_Rece instanceof Product_Receipt){
            Product_Receipt pr = new Product_Receipt(product_Rece);
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
            this.date_import = pr.date_import;
            this.ListDetailPRN = new ArrayList<>(pr.ListDetailPRN);
        } else{
            Product_Receipt pr = new Product_Receipt();
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
            this.date_import = pr.date_import;
            this.ListDetailPRN = new ArrayList<>(pr.ListDetailPRN);
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
    
    public ArrayList getListDetailPRN(){
        return new ArrayList(this.ListDetailPRN);
    }
    
    public void setListDetailPRN(ArrayList ListDetailPRN){
        this.ListDetailPRN = new ArrayList<>(ListDetailPRN);
    }
    
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
    public Product_Receipt getPR_MySQL(String id){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Product_Receipt pr = new Product_Receipt();
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Product_Receipt WHERE id_prn=" + id +";"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_prn = new String(rs.getString("id_prn"));
                LocalDateTime date_import = rs.getTimestamp("date_import").toLocalDateTime();
                String id_staff = new String(rs.getString("id_staff"));
                String id_supplier = new String(rs.getString("id_supplier"));
                ArrayList<Detail_PRN> ListDetailPRN = new ArrayList<Detail_PRN>();
                pr.setId_PRN(id_prn); pr.setDateImport(date_import); pr.setId_staff(id_staff); pr.setId_supplier(id_supplier); pr.setListDetailPRN(ListDetailPRN);
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
        
        return new Product_Receipt(pr);
    }
}
