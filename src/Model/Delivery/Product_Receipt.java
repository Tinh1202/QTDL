/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import Model.UserModel.Staff;
import java.time.LocalDateTime;
import java.time.*;
import java.util.*;

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
    
    
    Product_Receipt() {
        id_prn = new String("");
        id_staff = new String("");
        id_supplier = new String("");
        date_import = LocalDateTime.of(2023, 11, 28, 10, 30, 0);
        ListDetailPRN = new ArrayList<Detail_PRN>();
    }

    Product_Receipt(String id_prn, String id_supplier, String id_staff, LocalDate localdate, LocalTime localtime) {
        this.id_prn = new String(id_prn);
        this.id_staff = new String(id_staff);
        this.date_import = LocalDateTime.of(localdate, localtime);
        this.id_supplier = new String(id_supplier);
    }
    
    Product_Receipt(Object product_Rece){
        if(product_Rece instanceof Product_Receipt){
            Product_Receipt pr = new Product_Receipt(product_Rece);
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
        } else{
            Product_Receipt pr = new Product_Receipt();
            this.id_prn = new String(pr.id_prn);
            this.id_staff = new String(pr.id_staff);
            this.id_supplier = new String(pr.id_supplier);
        }
    }

    //define getter, setter
    private String getId_PRN(){
        return new String(this.id_prn);
    }
    private void setId_PRN(String id_prn){
        this.id_prn = new String(id_prn);
    }
    private String getId_staff(){
        return new String(this.id_staff);
    }
    private void setId_staff(String id_staff){
        this.id_staff = new String(id_staff);
    }
    private String getId_supplier(){
        return new String(this.id_supplier);
    }
    private void setId_supplier(String id_supplier){
        this.id_supplier = new String(this.id_supplier);
    }
    /*
        code *
    */
    
    private double Total_Price(){  // return total price of devices from detail_prn list
        return 0.00;
    }
    
    @Override
    public String toString(){
        return null;
    }
    
}
