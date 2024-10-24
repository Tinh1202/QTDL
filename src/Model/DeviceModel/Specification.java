/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

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
public class Specification { // lớp thông số kỹ thuật
    private String id_spec;
    private String name_spec; // tên thông số
    private String data;   // số liệu kỹ thuật
    
    
    public Specification() {
        this.name_spec = new String("");
        this.data = new String("");
        this.id_spec = new String("");
    }
    
    public Specification(String id_spec, String name_spec, String data){
        this.id_spec = new String(id_spec);
        this.name_spec = new String(name_spec);
        this.data = new String(data);
    }
    
    public Specification(Object spec){
        if (spec instanceof Specification){
            Specification spec_new = new Specification(spec);
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
            this.id_spec = new String(spec_new.id_spec);
        } else {
            Specification spec_new = new Specification();
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
            this.id_spec = new String(spec_new.id_spec);
        }
    }
    
    public String getNameSpec(){
        return new String(this.name_spec);
    }
    
    public void setNameSpec(String name_spec){
        this.name_spec = new String(name_spec);
    }
    
    public String getData(){
        return new String(this.data);
    }
    
    public void setData(String data){
        this.data = new String(this.data);
    }
    
    public String getIdSpec(){
        return new String(this.id_spec);
    }
    
    public void setIdSpec(String id_spec){
        this.id_spec = new String(id_spec);
    }
    
    @Override
    public String toString(){
        Specification spec = new Specification(this);
        return "Id specification: " + this.getIdSpec() + "\n"
                + "Name specification: " + spec.getNameSpec() + "\n"
                + "Data specification: " + spec.getData() + "\n";
    }
    
    public Specification getSpec_MySQL(String id){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Specification spec = new Specification();
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Specification WHERE id_spec=" + id +";"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_device = new String(rs.getString("id_device"));
                String name_spec = new String(rs.getString("name_spec"));
                String data_spec = new String(rs.getString("data_spec"));
                spec.setIdSpec(id_spec); spec.setNameSpec(name_spec); spec.setData(data_spec);
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
        
        return new Specification(spec);
    }
}
