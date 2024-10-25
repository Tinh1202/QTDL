/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;

/**
 *
 * @author vntin
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Iterator;
public class ListDeliveryNote {
    
    private ArrayList<Delivery_Note>  ListDN;
    private int length;

    public ListDeliveryNote() {
        this.ListDN = new ArrayList<>();
        this.length = 0;
    }
    
    public ListDeliveryNote(ArrayList<Delivery_Note> ListDN){
        this.ListDN = new ArrayList<Delivery_Note>(ListDN);
        this.length = ListDN.size();
    }
    
    public ListDeliveryNote(Object ListObj){
        if (ListObj instanceof ListDeliveryNote){
            ListDeliveryNote listDT = new ListDeliveryNote(ListDN);
            this.ListDN = new ArrayList<Delivery_Note>(listDT.ListDN);
            this.length = listDT.length;
        } else {
            ListDeliveryNote listDT = new ListDeliveryNote();
            this.ListDN = new ArrayList<Delivery_Note>(listDT.ListDN);
            this.length = listDT.length;
        }
    }
    
    public ArrayList<Delivery_Note> getListDN(){
        return new ArrayList<Delivery_Note>(this.ListDN);
    }
    
    public void setListDN(ArrayList<Delivery_Note> ListDN){
        this.ListDN = new ArrayList<Delivery_Note>(ListDN);
    }
    
    public int getLengthListDN(){
        return this.length;
    }
    
    public ArrayList<Delivery_Note> ListDN_MySQL(){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Delivery_Note> listDN = new ArrayList<Delivery_Note>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Delivery_Note;"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_dn = new String(rs.getString("id_dn"));
                String id_staff = new String(rs.getString("id_staff"));
                String id_customer = new String(rs.getString("id_customer"));
                LocalDateTime datetime_shipment = rs.getTimestamp("datetime_shipment").toLocalDateTime();
                Delivery_Note dn = new Delivery_Note(id_dn, id_staff, id_customer, datetime_shipment);
                listDN.add(dn);
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
        return new ArrayList<Delivery_Note>(listDN); 
    }
    
    public void DisplayListDN(){ //showing list object  from Mysql
        ArrayList<Delivery_Note> lst_dn = ListDN_MySQL();

        for (Delivery_Note dn : lst_dn) {
            System.out.println("Id Delivery note: " + dn.getId_Dn() + "\n");
            System.out.println("Id staff: " + dn.getId_Staff() + "\n");
            System.out.println("Id customer: " + dn.getId_Customer() + "\n");
            System.out.println("Date: " + dn.getLocalDateTime() + "\n");
        } 
    }
    
    public ArrayList<Delivery_Note> DeleteHeadDNFromList(ArrayList<Delivery_Note> lst_dn){ // xóa ở đầu
        ArrayList<Delivery_Note> lst_dn_new = new ArrayList<Delivery_Note>(lst_dn);
        lst_dn_new.remove(0);
        return lst_dn_new;
    }
    
    public ArrayList<Delivery_Note> DeleteTailDNFromList(ArrayList<Delivery_Note> lst_dn){ // xóa ở cuối
        ArrayList<Delivery_Note> lst_dn_new = new ArrayList<Delivery_Note>(lst_dn);
        lst_dn_new.remove(lst_dn_new.size() - 1);
        return lst_dn_new;
    }
    
    
    public ArrayList<Delivery_Note> DeleteIdDNFromList(ArrayList<Delivery_Note> lst_dn, String id) { // xóa theo id_dn
        ArrayList lst_dn_new = new ArrayList(lst_dn);
        
        Iterator<Delivery_Note> iterator = lst_dn_new.iterator();
        while (iterator.hasNext()) {
            Delivery_Note dn = iterator.next();
            if (dn.getId_Dn().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Delivery_Note>(lst_dn_new);
    }
    
    public ArrayList<Delivery_Note> DeleteIdStaffFromList(ArrayList<Delivery_Note> lst_dn, String id) { // xóa theo id_staff
        ArrayList lst_dn_new = new ArrayList(lst_dn);
        
        Iterator<Delivery_Note> iterator = lst_dn_new.iterator();
        while (iterator.hasNext()) {
            Delivery_Note dn = iterator.next();
            if (dn.getId_Staff().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Delivery_Note>(lst_dn_new);
    }
    
    public ArrayList<Delivery_Note> DeleteIdCustomerFromList(ArrayList<Delivery_Note> lst_dn, String id) { // xóa theo id_customer
        ArrayList lst_dn_new = new ArrayList(lst_dn);
        
        Iterator<Delivery_Note> iterator = lst_dn_new.iterator();
        while (iterator.hasNext()) {
            Delivery_Note dn = iterator.next();
            if (dn.getId_Customer().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Delivery_Note>(lst_dn_new);
    }
    
    public ArrayList<Delivery_Note> DeleteDateFromList(ArrayList<Delivery_Note> lst_dn, LocalDateTime date_delete) { // xóa theo date
        ArrayList lst_dn_new = new ArrayList(lst_dn);
        
        Iterator<Delivery_Note> iterator = lst_dn_new.iterator();
        while (iterator.hasNext()) {
            Delivery_Note dn = iterator.next();
            if (dn.getLocalDateTime().isEqual(date_delete)) {
                iterator.remove();
            }
        }
        return new ArrayList<Delivery_Note>(lst_dn_new);
    }
    
    public ArrayList<Delivery_Note> AddSpecToList(ArrayList<Delivery_Note> lst_dn, Delivery_Note dn){ //thêm một object vào List
        ArrayList<Delivery_Note> lst_dn_new = new ArrayList<Delivery_Note>(lst_dn);
        Delivery_Note dn_new = new Delivery_Note(dn);
        
        lst_dn_new.add(dn);
        return lst_dn_new;
    }
    
    
    
    
    
    
    
}
