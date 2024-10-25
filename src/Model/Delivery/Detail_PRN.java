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
/**
 *
 * @author vntin
 */
public class Detail_PRN { // class chi tiết phiếu nhập
    private String id_prn;
    private String id_device;  // mã thiết bị
    private int number;  //  số lượng
    private double price; // đơn giá

    
    /*
        create constructor
    */
    public Detail_PRN(){
        id_prn = new String("");
        id_device = new String("");
        number = 1;
        price = 1;
    }
    public Detail_PRN(String id_prn, String id_device, int number, double price){
        this.id_prn = new String(id_prn);
        this.id_device = new String(id_device);
        this.number = number;
        this.price = price;
    }
    public Detail_PRN(Detail_PRN dp){
        this.id_prn = new String(dp.id_prn);
        this.id_device = new String(dp.id_device);
        this.number = dp.number;
        this.price = dp.price;
    }
    
    /*
        define getter, setter funcs
    */
    public String getId_prn(){
        return new String(this.id_prn);
    }
    public void setId_prn(String id_prn){
        this.id_prn = new String(id_prn);
    }
    public String getId_device(){
        return new String(this.id_device);
    }
    public void setId_device(String id_device){
        this.id_device = new String(id_device);
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        number = number;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        price = price;
    }


    
    @Override
    public String toString(){
        return "id: " + this.getId_prn() + "\n"
                + "id device: " + this.getId_device() + "\n"
                + "Quantity: " + this.getNumber() + "\n"
                + "Total price: " + this.totalPrice() + "\n";
    }
    
    private double totalPrice(){ // tính giá của thiết bị * số lượng
        return (double) this.getPrice() * this.getNumber();
    }
    public Detail_PRN getDPRN_MySQL(String id){
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Detail_PRN prn = new Detail_PRN();
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Detail_PRN WHERE id_prn=" + id +";"; // Thay "users" bằng bảng của bạn
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id_prn = new String(rs.getString("id_prn"));
                String id_device = new String(rs.getString("id_device"));
                int number = rs.getInt("number");
                double price = rs.getDouble("price");
                prn.setId_prn(id_prn); prn.setId_device(id_device); prn.setNumber(number); prn.setPrice(price);
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
        
        return new Detail_PRN(prn);
    }
}
