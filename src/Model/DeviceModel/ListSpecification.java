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
    private int length;

    public ListSpecification() {
        this.ListSpec = new ArrayList<>();
        this.length = 0;
    }
    
    public ListSpecification(ArrayList<Specification> ListSpec){
        this.ListSpec = new ArrayList<Specification>(ListSpec);
        this.length = ListSpec.size();
    }
    
    public ListSpecification(Object ListObj){
        if (ListObj instanceof ListSpecification){
            ListSpecification listDT = new ListSpecification(ListSpec);
            this.ListSpec = new ArrayList<Specification>(listDT.ListSpec);
            this.length = listDT.length;
        } else {
            ListSpecification listDT = new ListSpecification();
            this.ListSpec = new ArrayList<Specification>(listDT.ListSpec);
            this.length = listDT.length;
        }
    }
        
    public ArrayList<Specification> getListSpec(){
        return new ArrayList<Specification>(this.ListSpec);
    }
    
    public void setListSpec(ArrayList<Specification> ListSpec){
        this.ListSpec = new ArrayList<Specification>(ListSpec);
    }

    public int getLengthListSpec(){
        return this.length;
    }
    
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
                String id_device = new String(rs.getString("id_device"));
                String name_spec = new String(rs.getString("name_spec"));
                String data_spec = new String(rs.getString("data_spec"));
                Specification spec = new Specification(id_device, name_spec, data_spec);
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
        
        return new ArrayList<Specification>(listSpec); 
    }
    
   
    // lấy danh sách các thông số bằng ID,...
    public ArrayList<Specification> ListSpec_MySQL(String id){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Specification> listSpec = new ArrayList<Specification>();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Specification where id_spec = " + id + ";"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_device = new String(rs.getString("id_device"));
                String name_spec = new String(rs.getString("name_spec"));
                String data_spec = new String(rs.getString("data_spec"));
                Specification spec = new Specification(id_device, name_spec, data_spec);
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
        
        return new ArrayList<Specification>(listSpec); 
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
  
}
