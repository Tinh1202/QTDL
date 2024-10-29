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
public class ListDetailDN {
    private ArrayList<Detail_DeliveryNote>  ListDDN;
    private int length;

    public ListDetailDN() {
        this.ListDDN = new ArrayList<>();
        this.length = 0;
    }
    
    public ListDetailDN(ArrayList<Detail_DeliveryNote> ListDDN){
        this.ListDDN = new ArrayList<Detail_DeliveryNote>(ListDDN);
        this.length = ListDDN.size();
    }
    
    public ListDetailDN(Object ListObj){
        if (ListObj instanceof ListDetailDN){
            ListDetailDN listDT = new ListDetailDN(ListDDN);
            this.ListDDN = new ArrayList<Detail_DeliveryNote>(listDT.ListDDN);
            this.length = listDT.length;
        } else {
            ListDetailDN listDT = new ListDetailDN();
            this.ListDDN = new ArrayList<Detail_DeliveryNote>(listDT.ListDDN);
            this.length = listDT.length;
        }
    }
    
    public ArrayList<Detail_DeliveryNote> getListDDN(){
        return new ArrayList<Detail_DeliveryNote>(this.ListDDN);
    }
    
    public void setListDDN(ArrayList<Detail_DeliveryNote> ListDDN){
        this.ListDDN = new ArrayList<Detail_DeliveryNote>(ListDDN);
    }
    
    public int getLengthListDDN(){
        return this.length;
    }
    
    public ArrayList<Detail_DeliveryNote> ListDDN_MySQL(){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Detail_DeliveryNote> listDDN = new ArrayList<Detail_DeliveryNote>();       
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Detail_DeliveryNote;"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_dn = new String(rs.getString("id_dn"));
                String id_device = new String(rs.getString("id_device"));
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Detail_DeliveryNote ddn = new Detail_DeliveryNote(id_dn, id_device, quantity, price);
                listDDN.add(ddn);
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
        return new ArrayList<Detail_DeliveryNote>(listDDN); 
    }
    
    public ArrayList<Detail_DeliveryNote> ListDDN_MySQL(String id) {
        ArrayList<Detail_DeliveryNote> listDDN = new ArrayList<Detail_DeliveryNote>();  
        String sql = "SELECT * FROM Detail_DeliveryNote WHERE id_dn = ?"; // Sử dụng câu truy vấn với tham số
        try (java.sql.Connection conn = new Model.Connect.Connection().getJDBC();
            PreparedStatement stmt = conn.prepareStatement(sql)) {  
            stmt.setString(1, id); // Truyền tham số vào câu truy vấn
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id_dn = new String(rs.getString("id_dn"));
                    String id_device = new String(rs.getString("id_device"));
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    Detail_DeliveryNote ddn = new Detail_DeliveryNote(id_dn, id_device, quantity, price);
                    listDDN.add(ddn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Detail_DeliveryNote>(listDDN);
    }
    
    public void DisplayListDDN(){ //showing list object Specification from Mysql
        ArrayList<Detail_DeliveryNote> lst_ddn = ListDDN_MySQL();

        for (Detail_DeliveryNote ddn : lst_ddn) {
            System.out.println("Id delivery note: " + ddn.getId_dn() + "\n");
            System.out.println("Id device: " + ddn.getId_device() + "\n");
            System.out.println("Quantity: " + ddn.getQuantity() + "\n");
            System.out.println("Price: " + ddn.getPrice() + "\n");
        } 
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteHeadDDNFromList(ArrayList<Detail_DeliveryNote> lst_ddn){ // xóa ở đầu
        ArrayList<Detail_DeliveryNote> lst_ddn_new = new ArrayList<Detail_DeliveryNote>(lst_ddn);
        lst_ddn_new.remove(0);
        return lst_ddn_new;
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteTailDDNFromList(ArrayList<Detail_DeliveryNote> lst_ddn){ // xóa ở cuối
        ArrayList<Detail_DeliveryNote> lst_ddn_new = new ArrayList<Detail_DeliveryNote>(lst_ddn);
        lst_ddn_new.remove(lst_ddn_new.size() - 1);
        return lst_ddn_new;
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteIdDNFromList(ArrayList<Detail_DeliveryNote> lst_ddn, String id) { // xóa theo id_dn
        ArrayList lst_ddn_new = new ArrayList(lst_ddn);
        
        Iterator<Detail_DeliveryNote> iterator = lst_ddn_new.iterator();
        while (iterator.hasNext()) {
            Detail_DeliveryNote ddn = iterator.next();
            if (ddn.getId_dn().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_DeliveryNote>(lst_ddn_new);
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteIdDeviceFromList(ArrayList<Detail_DeliveryNote> lst_ddn, String id) { // xóa theo id_device
        ArrayList lst_ddn_new = new ArrayList(lst_ddn);
        
        Iterator<Detail_DeliveryNote> iterator = lst_ddn_new.iterator();
        while (iterator.hasNext()) {
            Detail_DeliveryNote ddn = iterator.next();
            if (ddn.getId_device().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_DeliveryNote>(lst_ddn_new);
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteIdDNFromList(ArrayList<Detail_DeliveryNote> lst_ddn, int quantity) { // xóa theo quantity
        ArrayList lst_ddn_new = new ArrayList(lst_ddn);
        
        Iterator<Detail_DeliveryNote> iterator = lst_ddn_new.iterator();
        while (iterator.hasNext()) {
            Detail_DeliveryNote ddn = iterator.next();
            if (ddn.getQuantity()== quantity) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_DeliveryNote>(lst_ddn_new);
    }
    
    public ArrayList<Detail_DeliveryNote> DeleteIdDNFromList(ArrayList<Detail_DeliveryNote> lst_ddn, double price) { // xóa theo price
        ArrayList lst_ddn_new = new ArrayList(lst_ddn);
        
        Iterator<Detail_DeliveryNote> iterator = lst_ddn_new.iterator();
        while (iterator.hasNext()) {
            Detail_DeliveryNote ddn = iterator.next();
            if (ddn.getPrice()== price) {
                iterator.remove();
            }
        }
        return new ArrayList<Detail_DeliveryNote>(lst_ddn_new);
    }
    
    public ArrayList<Detail_DeliveryNote> AddDDNToList(ArrayList<Detail_DeliveryNote> lst_ddn, Detail_DeliveryNote ddn){
        ArrayList<Detail_DeliveryNote> lst_ddn_new = new ArrayList<Detail_DeliveryNote>(lst_ddn);
        Detail_DeliveryNote ddn_new = new Detail_DeliveryNote(ddn);
        
        lst_ddn_new.add(ddn);
        return lst_ddn_new;
    }
    
   
    public static void main(String[] args){
        ListDetailDN a = new ListDetailDN();
        a.ListDDN_MySQL();
        a.DisplayListDDN();
    }
    
    
    
}
