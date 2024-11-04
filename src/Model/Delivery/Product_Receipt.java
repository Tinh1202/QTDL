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
import Model.DeviceModel.Supplier;
import Model.UserModel.ListStaff;
import java.util.ArrayList;

import Model.DeviceModel.*;

/**
 *
 * @author vntin
 */

// lớp phiếu nhập
public class Product_Receipt {  // phiếu nhập ( Nhập sản phẩm vào kho) 
    private String id_prn; // mã phiếu nhập
    private LocalDateTime date_import;  // ngày nhập kho
    private Staff staff; // id nhân viên
    private Supplier supplier; // mã nhà cung cấp
    private ArrayList<Detail_PRN> listDetailPRN; // danh sách các chi tiết phiếu

    
    
    public Product_Receipt() {
        this.id_prn = new String("");
        this.staff = new Staff();
        this.supplier = new Supplier();
        LocalDate ld = LocalDate.of(1990, 01, 01);
        LocalTime lt = LocalTime.of(01, 01, 01);
        this.date_import = LocalDateTime.of(ld, lt);
        this.listDetailPRN = new ArrayList<Detail_PRN>();

    }

    public Product_Receipt(String id_prn,
            LocalDateTime date_import,
            Staff staff,
            Supplier supplier,
            ArrayList<Detail_PRN> listDetailPRN) {

        
        this.id_prn = new String(id_prn); 
        this.staff = new Staff(staff);
        this.date_import = date_import;
        this.supplier = new Supplier(supplier);
        this.listDetailPRN = new ArrayList<Detail_PRN>(listDetailPRN);

    }
    
    public Product_Receipt(Object product_Rece){
        if(product_Rece instanceof Product_Receipt){
            Product_Receipt pr = (Product_Receipt) product_Rece;
            this.id_prn = new String(pr.id_prn);
            this.staff = pr.staff;
            this.supplier = pr.supplier;
            this.date_import = pr.date_import;
            this.listDetailPRN = new ArrayList<Detail_PRN>(pr.listDetailPRN);

        } else{
            Product_Receipt pr = new Product_Receipt();
            this.id_prn = new String(pr.id_prn);
            this.staff = pr.staff;
            this.supplier = pr.supplier;
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
    public Staff get_staff(){
        return new Staff(this.staff);
    }
    public void set_staff(Staff staff){
        this.staff = new Staff(staff);
    }
    public Supplier get_supplier(){
        return new Supplier(this.supplier);
    }
    public void set_supplier(Supplier supplier){
        this.supplier = new Supplier(this.supplier);
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
    

    
    private double Total_Price(){  // return total price of devices from detail_prn list
        return 0.00;
    }
    
    @Override
    public String toString(){
        return "Id: " + this.getId_PRN() + "\n"
                + "staff: " + this.get_staff().getFullname() + "\n"
                + "supplier: " + this.get_supplier().getNameSupplier() + "\n"
                + "Date: " + this.getDateImport().format(DateTimeFormatter.ISO_DATE);
    }
    
    
    // lấy phiếu nhập bằng id
    public Product_Receipt getPR_MySQL(String id) {
    java.sql.Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Product_Receipt pr = new Product_Receipt();

    /*
    phiếu nhập Product_Receipt {id, date, Staff Object, Supplier Object, ListDetailPRN}
    */
    
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
            
            
            Staff st = new ListStaff().Staff_MySQL(id_staff);
            Supplier supplier = new ListSupplier().getSupplier_MySQL(id_supplier);
            ArrayList<Detail_PRN> lst_prn = new ArrayList<Detail_PRN>(new ListDetailPRN().ListDPRN_MySQL(id_prn));
            
            pr = new Product_Receipt(id_prn, date_import, st, supplier, lst_prn);
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

    public static void main(String[] args){
        Product_Receipt pr = new Product_Receipt().getPR_MySQL("PR001");
        System.out.println(pr.toString());
    }
    
}
