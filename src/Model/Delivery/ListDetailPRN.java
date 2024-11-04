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

import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author vntin
 */

// danh sách các chi tiết phiếu xuất
public class ListDetailPRN {
    private ArrayList<Detail_PRN> ListPRN;
    private int length;

    public ListDetailPRN() {
        this.ListPRN = new ArrayList<>();
        this.length = 0;
    }
    
    public ListDetailPRN(ArrayList<Detail_PRN> ListPRN){
        this.ListPRN = new ArrayList<Detail_PRN>(ListPRN);
        this.length = ListPRN.size();
    }
    
    public ListDetailPRN(Object ListObj){
        if (ListObj instanceof ListDetailPRN){
            ListDetailPRN listDT = new ListDetailPRN(ListPRN);
            this.ListPRN = new ArrayList<Detail_PRN>(listDT.ListPRN);
            this.length = listDT.length;
        } else {
            ListDetailPRN listDT = new ListDetailPRN();
            this.ListPRN = new ArrayList<Detail_PRN>(listDT.ListPRN);
            this.length = listDT.length;
        }
    }
    
    public ArrayList<Detail_PRN> getListDPRN(){
        return new ArrayList<Detail_PRN>(this.ListPRN);
    }
    
    public void setListDPRN(ArrayList<Detail_PRN> ListPRN){
        this.ListPRN = new ArrayList<Detail_PRN>(ListPRN);
    }
    
    public int getLengthListDPRN(){
        return this.length;
    }
    
    // lấy tất cả các chi tiết phiếu từng sản phẩm trong csdl
    public ArrayList<Detail_PRN> ListDPRN_MySQL(){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Detail_PRN> listPRN = new ArrayList<Detail_PRN>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Detail_PRN;"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_prn = new String(rs.getString("id_prn"));
                String id_device = new String(rs.getString("id_device"));
                int number = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Detail_PRN prn = new Detail_PRN(id_prn, id_device, number, price);
                listPRN.add(prn);
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
        
        return new ArrayList<Detail_PRN>(listPRN); 
    }
    
    // Lấy các chi tiết phiếu nhập bằng ID chi tiết phiếu nhập
//    public ArrayList<Detail_PRN> ListDPRN_MySQL(String id){
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs1 = null;
//        ArrayList<Detail_PRN> listPRN = new ArrayList<Detail_PRN>();
//        
//        try {
//            Model.Connect.Connection c = new Connection();
//            conn = c.getJDBC();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM Detail_PRN WHERE id_prn = " + new String(id) + ";" ; // Thay "users" bằng bảng của bạn 
//            rs1 = stmt.executeQuery(sql);
//            while (rs1.next()) {
//                String id_prn = new String(rs1.getString("id_prn"));
//                String id_device = new String(rs1.getString("id_device"));
//                int number = rs1.getInt("quantity");
//                double price = rs1.getDouble("price");
//                Detail_PRN prn = new Detail_PRN(id_prn, id_device, number, price);
//                listPRN.add(prn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs1 != null) rs1.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return new ArrayList<Detail_PRN>(listPRN); 
//    }
    public ArrayList<Detail_PRN> ListDPRN_MySQL(String id) {
    ArrayList<Detail_PRN> listPRN = new ArrayList<Detail_PRN>();
    
    String sql = "SELECT * FROM Detail_PRN WHERE id_prn = ?"; // Sử dụng câu truy vấn với tham số
    
    try (java.sql.Connection conn = new Model.Connect.Connection().getJDBC();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setString(1, id); // Truyền tham số vào câu truy vấn
        try (ResultSet rs1 = stmt.executeQuery()) {
            while (rs1.next()) {
                String id_prn = rs1.getString("id_prn");
                String id_device = rs1.getString("id_device");
                int number = rs1.getInt("quantity");
                double price = rs1.getDouble("price");
                
                Detail_PRN prn = new Detail_PRN(id_prn, id_device, number, price);
                listPRN.add(prn);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return listPRN;
}

    public void DisplayListDPRN(){ //showing list object Specification from Mysql
        ArrayList<Detail_PRN> lst_prn = ListDPRN_MySQL();

        for (Detail_PRN prn : lst_prn) {
            System.out.println("Id prn: " + prn.getId_prn() + "\n");
            System.out.println("Id device: " + prn.getId_device() + "\n");
            System.out.println("Number: " + prn.getNumber() + "\n");
            System.out.println("Price: " + prn.getPrice() + "\n");
        } 
    }
    
    public ArrayList<Detail_PRN> DeleteHeadDPRNFromList(ArrayList<Detail_PRN> lst_prn){ // xóa ở đầu
        ArrayList<Detail_PRN> lst_prn_new = new ArrayList<Detail_PRN>(lst_prn);
        lst_prn_new.remove(0);
        return lst_prn_new;
    }
    
    public ArrayList<Detail_PRN> DeleteTailDPRNFromList(ArrayList<Detail_PRN> lst_prn){ // xóa ở cuối
        ArrayList<Detail_PRN> lst_prn_new = new ArrayList<Detail_PRN>(lst_prn);
        lst_prn_new.remove(lst_prn_new.size() - 1);
        return lst_prn_new;
    }
    
    public ArrayList<Detail_PRN> DeleteIdPRNFromList(ArrayList<Detail_PRN> lst_prn, String id) { // xóa theo id_prn
        ArrayList lst_prn_new = new ArrayList(lst_prn);
        
        Iterator<Detail_PRN> iterator = lst_prn_new.iterator();
        while (iterator.hasNext()) {
            Detail_PRN prn = iterator.next();
            if (prn.getId_prn().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_PRN>(lst_prn_new);
    }
    public ArrayList<Detail_PRN> DeleteIdDeviceFromList(ArrayList<Detail_PRN> lst_prn, String id) { // xóa theo id_device
        ArrayList lst_prn_new = new ArrayList(lst_prn);
        
        Iterator<Detail_PRN> iterator = lst_prn_new.iterator();
        while (iterator.hasNext()) {
            Detail_PRN prn = iterator.next();
            if (prn.getId_device().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_PRN>(lst_prn_new);
    }
    public ArrayList<Detail_PRN> DeleteNumberFromList(ArrayList<Detail_PRN> lst_prn, int number) { // xóa theo number
        ArrayList lst_prn_new = new ArrayList(lst_prn);
        
        Iterator<Detail_PRN> iterator = lst_prn_new.iterator();
        while (iterator.hasNext()) {
            Detail_PRN prn = iterator.next();
            if (prn.getNumber() == number) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_PRN>(lst_prn_new);
    }
    public ArrayList<Detail_PRN> DeleteNumberFromList(ArrayList<Detail_PRN> lst_prn, double price) { // xóa theo price
        ArrayList lst_prn_new = new ArrayList(lst_prn);
        
        Iterator<Detail_PRN> iterator = lst_prn_new.iterator();
        while (iterator.hasNext()) {
            Detail_PRN prn = iterator.next();
            if (prn.getPrice() == price) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_PRN>(lst_prn_new);
    }
    
    public ArrayList<Detail_PRN> AddDPRNToList(ArrayList<Detail_PRN> lst_prn, Detail_PRN prn){
        ArrayList<Detail_PRN> lst_prn_new = new ArrayList<Detail_PRN>(lst_prn);
        Detail_PRN prn_new = new Detail_PRN(prn);
        
        lst_prn_new.add(prn);
        return lst_prn_new;
    }
    

    
}
