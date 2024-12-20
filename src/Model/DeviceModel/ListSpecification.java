/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

/**
 *
 * @author vntin
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;

import java.util.ArrayList;
import java.util.Iterator;

public class ListSpecification {
    private ArrayList<Specification>  ListSpec;
    
    public ListSpecification() {
        this.ListSpec = new ArrayList<>();
    }
    
    public ListSpecification(ArrayList<Specification> ListSpec){
        this.ListSpec = ListSpec;
    }
    
    public ListSpecification(Object ListObj){
        if (ListObj instanceof ListSpecification){
            ListSpecification listDT = (ListSpecification) ListObj;
            this.ListSpec = listDT.ListSpec;
        } else {
            ListSpecification listDT = new ListSpecification();
            this.ListSpec = listDT.ListSpec;
        }
    }
        
    public ArrayList<Specification> getListSpec(){
        return this.ListSpec;
    }
    
    public void setListSpec(ArrayList<Specification> ListSpec){
        this.ListSpec = ListSpec;
    }
    
    
    // lấy tất cả trong csdl
    public ArrayList<Specification> ListSpec_MySQL(){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Specification> listSpec = new ArrayList<Specification>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Specification;"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_device = new String(rs.getString("id_specification"));
                String name_spec = new String(rs.getString("name_specification"));
                String data_spec = new String(rs.getString("data_spec"));
                String id_manufacturer = new String(rs.getString("id_manufacturer"));
                Manufacturer manuf = new ListManuf().getManuf_MySQL(id_manufacturer);
                
                Specification spec = new Specification(id_device, name_spec, data_spec, manuf);
                listSpec.add(spec);
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
        
        return listSpec; 
    }
    
   
    // lấy thông số kỹ thuật bằng id
    public Specification Spec_MySQL(String id_spec){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
//      ArrayList<Specification> listSpec = new ArrayList<Specification>();
        Specification spec = new Specification();

        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            
            String sql = "SELECT * FROM Specification where id_specification = ?"; // Thay "users" bằng bảng của bạn
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id_spec);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_specification = new String(rs.getString("id_specification"));
                String name_spec = new String(rs.getString("name_specification"));
                String data_spec = new String(rs.getString("data_spec"));
                String id_manuf = new String(rs.getString("id_manufacturer"));
                Manufacturer manuf = new ListManuf().getManuf_MySQL(id_manuf);
                
                spec = new Specification(id_specification, name_spec, data_spec, manuf);
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
        
        return spec; 
    }
    
    
    public void DisplayListSpec(){ //showing list object Specification from Mysql
        ArrayList<Specification> lst_spec = ListSpec_MySQL();

        for (Specification spec : lst_spec) {
            System.out.println("Id specification: " + spec.getIdSpec() + "\n");
            System.out.println("Name specification: " + spec.getNameSpec() + "\n");
            System.out.println("Data: " + spec.getData() + "\n");
        } 
    }
    
    public ArrayList<Specification> DeleteHeadSpecFromList(ArrayList<Specification> lst_spec){ // xóa ở đầu
        ArrayList<Specification> lst_spec_new = new ArrayList<Specification>(lst_spec);
        lst_spec_new.remove(0);
        return lst_spec_new;
    }
    
    public ArrayList<Specification> DeleteTailSpecFromList(ArrayList<Specification> lst_spec){ // xóa ở cuối
        ArrayList<Specification> lst_spec_new = new ArrayList<Specification>(lst_spec);
        lst_spec_new.remove(lst_spec_new.size() - 1);
        return lst_spec_new;
    }
    
    public ArrayList<Specification> DeleteIdSpecFromList(ArrayList<Specification> lst_spec, String id) { // xóa theo id_spec
        ArrayList lst_spec_new = new ArrayList(lst_spec);
        
        Iterator<Specification> iterator = lst_spec_new.iterator();
        while (iterator.hasNext()) {
            Specification spec = iterator.next();
            if (spec.getIdSpec().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return new ArrayList<Specification>(lst_spec_new);
    }
    
    public ArrayList<Specification> DeleteNameSpecFromList(ArrayList<Specification> lst_spec, String name_spec){ // xóa theo tên thông số
        ArrayList<Specification> lst_spec_new = new ArrayList<Specification>(lst_spec);
        Iterator<Specification> iterator = lst_spec_new.iterator();
        
        while (iterator.hasNext()) {
            Specification spec = iterator.next();
            if (spec.getNameSpec().equalsIgnoreCase(name_spec)) {
                iterator.remove();
            }
        }
        return new ArrayList<Specification>(lst_spec_new);
    }
    
    //Overload DeleteSpecFromList(){} // tạo các hàm xóa theo tên, id, xóa đầu, xóa cuối, xóa theo index trong List Specification
    
    public ArrayList<Specification> AddSpecToList(ArrayList<Specification> lst_spec, Specification spec){ //thêm một object Specification vào List
        ArrayList<Specification> lst_spec_new = new ArrayList<Specification>(lst_spec);
        Specification spec_new = new Specification(spec);
        
        lst_spec_new.add(spec);
        return lst_spec_new;
    }
    
    
    // lấy danh sách thông số của một thiết bị
    public ArrayList<Specification> getSpec_From_ID_Device(String id_device){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Specification> listSpec = new ArrayList<Specification>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
           
            

            String sql = "SELECT * FROM device_specification where id_device = ?"; // Thay "users" bằng bảng của bạn
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id_device);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String id_specification = new String(rs.getString("id_specification"));
                
                Specification spec = Spec_MySQL(id_specification);
                
                listSpec.add(spec);
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
        
        return listSpec;
    }
  
    
    //done
    public static void main(String[] args){
        ArrayList<Specification> lst = new ListSpecification().getSpec_From_ID_Device("DEV001");
        for (Specification s : lst){
            System.out.println(s.toString());
        }
    }
}
