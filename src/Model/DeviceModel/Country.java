/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import Model.Connect.*;
import java.util.ArrayList;

/**
 *
 * @author vntin
 */
public class Country {
    private String id_country;
    private String name_country;
    
    public Country(){ //default constructor
        this.id_country = new String("");
        this.name_country = new String("");
    }
    
    // constructor parameters
    public Country(String id_country, String name_country){
        this.id_country = new String(id_country);
        this.name_country = new String(name_country);
    }
    
    // constructor object
    public Country(Object country){
        if (country instanceof Country){
            Country c = (Country) country;
            
            this.id_country = new String(c.id_country);
            this.name_country = new String(c.name_country);
            
        } else {
            Country c = new Country();
            this.id_country = new String(c.id_country);
            this.name_country = new String(c.name_country);
        }
    }
    
    // define getter, setter
    public String getIdCountry(){
        return new String(this.id_country);
    }
    
    public void setIdCountry(String id_country){
        this.id_country = new String(id_country);
    }
    
    
    public String getNameCountry(){
        return new String(this.name_country);
    }
    
    public void setNameCountry(String name_country){
        this.name_country = new String(name_country);
    }
    
    @Override 
    public String toString(){  //toString funcs
        Country c = new Country(this);
        return "Country ID: " + c.getIdCountry() + "\n"
                + "Country Name: " + c.getNameCountry() + "\n";
    }
    
    
    // lấy thông tin country từ csdl
    public Country getCountry_MySQL(String id_country){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Country country = null;  // Đặt thành null để chỉ tạo khi có kết quả

    try {
        Model.Connect.Connection c = new Model.Connect.Connection();
        conn = c.getJDBC();
        String sql = "SELECT * FROM country WHERE id_country = ?"; // Thay "account" bằng tên bảng thực tế

        // Chuẩn bị câu lệnh SQL với các tham số
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id_country);  // Tham số thứ nhất

        // Thực thi truy vấn
        rs = stmt.executeQuery();

        // Lấy dữ liệu từ ResultSet
        if (rs.next()) {
              country = new Country();
              country.setIdCountry(rs.getString("id_country"));
              country.setNameCountry(rs.getString("name_country"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng các tài nguyên
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return country;
    }
    
    // lấy danh sách các country trong csdl
    public ArrayList<Country> getCountry_MySQL(){
        
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Country> lst_country = new ArrayList<Country>();

        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM country"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id_country = rs.getString("id_country");
                String name_country = rs.getString("name_country");

                Country country = new Country(id_country, name_country);
                
                lst_country.add(country);
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

        return new ArrayList<Country>(lst_country);
    }    
    
    
    
    // done
    public static void main(String[] args){
        Country c = new Country().getCountry_MySQL("CN");
        System.out.println(c.toString());
        
        ArrayList<Country> lst_c = c.getCountry_MySQL();
        
        for (Country a : lst_c) {
            System.out.println(a.toString());
        }
     }

}



