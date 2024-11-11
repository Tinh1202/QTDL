/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import java.util.*;
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
public class Detail_DeliveryNote { // chi tiết phiếu xuất kho cho một thiết bị
    private String id_detail_deliverynote;
    private String id_deliverynote; // mã phiếu xuất (delivery note) 
    private String id_device; // mã thiết bị
    private int quantity; // số lượng 

    
    // default constructor
    public Detail_DeliveryNote() {
        this.id_detail_deliverynote = new String("");
        this.id_deliverynote = new String("");
        this.id_device = new String("");
        this.quantity = 0;
    }

    public Detail_DeliveryNote(String id_detail_deliverynote, String id_deliverynote, String id_device, int quantity) {
        this.id_detail_deliverynote = new String(id_detail_deliverynote);
        this.id_deliverynote = new String(id_deliverynote);
        this.id_device = new String(id_device);
        this.quantity = quantity;
    }
    
    public Detail_DeliveryNote(Object detail_dn){
        if(detail_dn instanceof Detail_DeliveryNote){
            Detail_DeliveryNote ddn = (Detail_DeliveryNote) detail_dn;
            this.id_detail_deliverynote = new String(ddn.id_detail_deliverynote);
            this.id_deliverynote = new String(ddn.id_deliverynote);
            this.id_device = new String(ddn.id_device);
            this.quantity = ddn.quantity;
        } else{
            Detail_DeliveryNote ddn = new Detail_DeliveryNote();
            this.id_detail_deliverynote = new String(ddn.id_detail_deliverynote);
            this.id_deliverynote = new String(ddn.id_deliverynote);
            this.id_device = new String(ddn.id_device);
            this.quantity = ddn.quantity;
        }
    }
    
//    private double Calculate_TotalPrice(){ // tính tổng đơn giá = số lượng * đơn giá
//        return (double) this.getQuantity() * this.getPrice();
//    }
    
    public void setIdDetail_dn(String id_detail_deliverynote){
        this.id_detail_deliverynote = new String(id_detail_deliverynote);
    }
    
    public String getIdDetail_dn(){
        return this.id_detail_deliverynote;
    }
    
    public String getId_dn(){
        return new String(this.id_deliverynote);
    }
    public void setId_dn(String id_dn){
        this.id_deliverynote = new String(id_dn);
    }
    public String getId_device(){
        return new String(this.id_device);
    }
    public void setId_device(String id_device){
        this.id_device = new String(id_device);
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
//    public double getPrice(){
//        return this.price;
//    }
//    public void setPrice(double price){
//        this.price = price;
//    }
    
    
    @Override
    public String toString(){
        return "Id Delivery Note: " + this.getId_dn() + "\n"
                + "Id device: " + this.getId_device() + "\n"
                + "Quantity: " + this.getQuantity() + "\n";    
    
    }
    
    
    //  lấy chi tiết phiếu nhập theo từng thiết bị
    public Detail_DeliveryNote getDDN_MySQL(String id_device) {
    java.sql.Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Detail_DeliveryNote ddn = new Detail_DeliveryNote();

    try {
        Model.Connect.Connection c = new Connection();
        conn = c.getJDBC();

        String sql = "SELECT * FROM detail_deliverynote WHERE id_device = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id_device);

        rs = pstmt.executeQuery();
        if (rs.next()) {
            String id_detail_deliverynote = rs.getString("id_detail_deliverynote");
            String id_deliverynote = rs.getString("id_deliverynote");
            String id_dev = rs.getString("id_device");
            int quantity = rs.getInt("quantity");
//            double price = rs.getDouble("price");

            ddn.setIdDetail_dn(id_detail_deliverynote);
            ddn.setId_dn(id_deliverynote);
            ddn.setId_device(id_dev);
            ddn.setQuantity(quantity);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return ddn;
}
    
     // done
    public static void main(String[] args){
        Detail_DeliveryNote ddn = new Detail_DeliveryNote().getDDN_MySQL("DEV001");
        System.out.println(ddn.toString());
    }

}
