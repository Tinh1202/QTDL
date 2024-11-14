/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;
import Model.DeviceModel.ListSupplier;
import Model.DeviceModel.Supplier;
import Model.UserModel.ListStaff;
import Model.UserModel.Staff;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author vntin
 */
public class ListProduct_Receipt {
    private ArrayList<Product_Receipt>  ListPR;
    private int length;

    public ListProduct_Receipt() {
        this.ListPR = new ArrayList<>();
        this.length = 0;
    }
    
    public ListProduct_Receipt(ArrayList<Product_Receipt> ListPR){
        this.ListPR = new ArrayList<Product_Receipt>(ListPR);
        this.length = ListPR.size();
    }
    
    public ListProduct_Receipt(Object ListObj){
        if (ListObj instanceof ListProduct_Receipt){
            ListProduct_Receipt listDT = new ListProduct_Receipt(ListPR);
            this.ListPR = new ArrayList<Product_Receipt>(listDT.ListPR);
            this.length = listDT.length;
        } else {
            ListProduct_Receipt listDT = new ListProduct_Receipt();
            this.ListPR = new ArrayList<Product_Receipt>(listDT.ListPR);
            this.length = listDT.length;
        }
    }
    public ArrayList<Product_Receipt> getListProductReceipt(){
        return new ArrayList<Product_Receipt>(this.ListPR);
    }
    
    public void setListProductReceipt(ArrayList<Product_Receipt> ListPR){
        this.ListPR = new ArrayList<Product_Receipt>(ListPR);
    }
    
    public int getLengthListProductReceipt(){
        return this.length;
    }
    
    // trả về danh sách các phiếu nhập <Array> : lấy phiếu nhập đưa vào mảng
    public ArrayList<Product_Receipt> ListPR_MySQL(){
    java.sql.Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ArrayList<Product_Receipt> listPR = new ArrayList<Product_Receipt>(); // mảng các phiếu nhập
    
    try {
        Model.Connect.Connection c = new Connection();
        conn = c.getJDBC();
        stmt = conn.createStatement();

        String sql = "SELECT * FROM product_receipt;"; // bảng phiếu nhập -> chọn tất cả các record
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String id_prn = rs.getString("id_product_receipt");
            LocalDateTime date_import = rs.getTimestamp("date_import").toLocalDateTime();
            String id_staff = rs.getString("id_staff");
            String id_supplier = rs.getString("id_supplier");
            
            // tạo đối tượng các chi tiết phiếu mỗi sản phẩm có id = id_prn
            ArrayList<Detail_PRN> list_detailPRN = new ListDetailPRN().ListDPRN_MySQL(id_prn);
            
            Staff st = new ListStaff().Staff_MySQL(id_staff);
            Supplier sup = new ListSupplier().getSupplier_MySQL(id_supplier);
            
            Product_Receipt pr = new Product_Receipt();
            pr.setId_PRN(id_prn); pr.setDateImport(date_import); pr.set_staff(st); pr.set_supplier(sup);
            pr.setListDetailPRN(list_detailPRN);


            listPR.add(pr);
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
    return listPR;
}

    
    
    
    
    public void DisplayListPR(){ //showing list object Specification from Mysql
        ArrayList<Product_Receipt> lst_pr = ListPR_MySQL();

        for (Product_Receipt pr : lst_pr) {
            System.out.println("Id prn: " + pr.getId_PRN() + "\n");
            System.out.println("Date import: " + pr.getDateImport() + "\n");
            System.out.println("staff: " + pr.get_staff().getFullname() + "\n");
            System.out.println("supplier: " + pr.get_supplier().getNameSupplier() + "\n");
        } 
    }
    
    
    // Trả về danh sách các phiếu nhập kiểu Đối tượng ListProduct-Receipt
    
    
    
//    public ArrayList<Product_Receipt> DeleteHeadPRFromList(ArrayList<Product_Receipt> lst_pr){ // xóa ở đầu
//        ArrayList<Product_Receipt> lst_pr_new = new ArrayList<Product_Receipt>(lst_pr);
//        lst_pr_new.remove(0);
//        return lst_pr_new;
//    }
//    
//    public ArrayList<Product_Receipt> DeleteTailPRFromList(ArrayList<Product_Receipt> lst_pr){ // xóa ở cuối
//        ArrayList<Product_Receipt> lst_pr_new = new ArrayList<Product_Receipt>(lst_pr);
//        lst_pr_new.remove(lst_pr_new.size() - 1);
//        return lst_pr_new;
//    }
//    
//    public ArrayList<Product_Receipt> DeleteIdPRNFromList(ArrayList<Product_Receipt> lst_pr, String id) { // xóa theo id_prn
//        ArrayList lst_pr_new = new ArrayList(lst_pr);
//        
//        Iterator<Product_Receipt> iterator = lst_pr_new.iterator();
//        while (iterator.hasNext()) {
//            Product_Receipt pr = iterator.next();
//            if (pr.getId_PRN().equalsIgnoreCase(id)) {
//                iterator.remove();
//            }
//        }
//        return new ArrayList<Product_Receipt>(lst_pr_new);
//    }
//    
//    public ArrayList<Product_Receipt> DeleteIdStaffFromList(ArrayList<Product_Receipt> lst_pr, String id) { // xóa theo id_staff
//        ArrayList lst_pr_new = new ArrayList(lst_pr);
//        
//        Iterator<Product_Receipt> iterator = lst_pr_new.iterator();
//        while (iterator.hasNext()) {
//            Product_Receipt pr = iterator.next();
//            if (pr.get_staff().equalsIgnoreCase(id)) {
//                iterator.remove();
//            }
//        }
//        return new ArrayList<Product_Receipt>(lst_pr_new);
//    }
//    public ArrayList<Product_Receipt> DeleteIdSupplierFromList(ArrayList<Product_Receipt> lst_pr, String id) { // xóa theo id_supplier
//        ArrayList lst_pr_new = new ArrayList(lst_pr);
//        
//        Iterator<Product_Receipt> iterator = lst_pr_new.iterator();
//        while (iterator.hasNext()) {
//            Product_Receipt pr = iterator.next();
//            if (pr.get_supplier().equalsIgnoreCase(id)) {
//                iterator.remove();
//            }
//        }
//        return new ArrayList<Product_Receipt>(lst_pr_new);
//    }
//    public ArrayList<Product_Receipt> DeleteIdStaffFromList(ArrayList<Product_Receipt> lst_pr, LocalDateTime date_delete) { // xóa theo date
//        ArrayList lst_pr_new = new ArrayList(lst_pr);
//        
//        Iterator<Product_Receipt> iterator = lst_pr_new.iterator();
//        while (iterator.hasNext()) {
//            Product_Receipt pr = iterator.next();
//            if (pr.getDateImport().isEqual(date_delete)) {
//                iterator.remove();
//            }
//        }
//        return new ArrayList<Product_Receipt>(lst_pr_new);
//    }
//    public ArrayList<Product_Receipt> AddPRToList(ArrayList<Product_Receipt> lst_pr, Product_Receipt pr){
//        ArrayList<Product_Receipt> lst_pr_new = new ArrayList<Product_Receipt>(lst_pr);
//        Product_Receipt pr_new = new Product_Receipt(pr);
//        
//        lst_pr_new.add(pr);
//        return lst_pr_new;
//    }
    
    
    // done
    public static void main(String[] args){
        ListProduct_Receipt a = new ListProduct_Receipt();
        ArrayList<Product_Receipt> lst = a.ListPR_MySQL();
        
        for (Product_Receipt p : lst){
            for (Detail_PRN d : p.getListDetailPRN()){
                System.out.println(d.getIdDetail_PR());
            }
        }
        
    }
}