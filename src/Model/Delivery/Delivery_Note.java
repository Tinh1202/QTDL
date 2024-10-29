/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import Model.DeviceModel.Device;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
public class Delivery_Note { // class phiếu xuất kho sản phẩm đến khách hàng
    private String id_dn; // mã phiếu nhập
    private String id_staff;  // mã nhân viên
    private String id_customer; // mã khách hàng
    private LocalDateTime datetime_shipment; // thời gian xuất phiếu
    private ArrayList<Detail_DeliveryNote> ListDetailDN; // danh sách các chi tiết phiếu
    
    
    public Delivery_Note() {
        this.id_dn = new String("");
        this.id_staff = new String("");
        this.id_customer = new String("");
        LocalDate ld = LocalDate.of(1990, 01, 01);
        LocalTime lt = LocalTime.of(01, 01, 01);
        this.datetime_shipment = LocalDateTime.of(ld, lt);
        this.ListDetailDN = new ArrayList<Detail_DeliveryNote>();
    }

    public Delivery_Note(String id_dn, String id_staff, String id_customer, LocalDateTime datetime_shipment) {
        this.id_dn = new String(id_dn);
        this.id_staff = new String(id_staff);
        this.id_customer = new String(id_customer);
        this.datetime_shipment = datetime_shipment;
    }
    
    public Delivery_Note(Delivery_Note dn){
        this.id_dn = new String(dn.id_dn);
        this.id_staff = new String(dn.id_staff);
        this.id_customer = new String(dn.id_customer);
    }
    
    /*
    misc code *
    */
    
    public String getId_Dn(){
        return new String(this.id_dn);
    }
    
    public void setId_Dn(String id_dn){
        this.id_dn = new String(id_dn);
    }
    
    public String getId_Staff(){
        return new String(this.id_staff);
    }
    
    public void setId_Staff(String id_staff){
        this.id_staff = new String(id_staff);
    }
    
    public String getId_Customer(){
        return new String(this.id_customer);
    }
    
    public void setIdCustomer(String id_customer){
        this.id_customer = new String(id_customer);
    }
    
    public LocalDateTime getLocalDateTime(){
        return this.datetime_shipment;
    }
    
    public void setLocalDateTime(LocalDateTime ldt){
        this.datetime_shipment = ldt;
    }
   
    
    @Override
    public String toString(){
        return "Id delivery note: " + this.getId_Dn() + "\n"
                + "Id staff: " + this.getId_Staff() + "\n"
                + "Id Customer: " + this.getId_Customer() + "\n"
                + "Date get delivery note: " + this.datetime_shipment.format(DateTimeFormatter.ISO_DATE);
    }
//    public Delivery_Note getDN_MySQL(String id){
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        Delivery_Note dn = new Delivery_Note();
//        try {
//            Model.Connect.Connection c = new Connection();
//            conn = c.getJDBC();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM Delivery_Note WHERE id_dn=" + id +";"; // Thay "users" bằng bảng của bạn
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String id_dn = new String(rs.getString("id_dn"));
//                String id_staff = new String(rs.getString("id_staff"));
//                String id_customer = new String(rs.getString("id_customer"));
//                LocalDateTime datetime_shipment = rs.getTimestamp("datetime_shipment").toLocalDateTime();
//                dn.setId_Dn(id_dn); dn.setId_Staff(id_staff); dn.setIdCustomer(id_customer); dn.setLocalDateTime(datetime_shipment);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return new Delivery_Note(dn);
//    }
    public Delivery_Note getDN_MySQL(String id) {
    java.sql.Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Delivery_Note dn = new Delivery_Note();
    try {
        Model.Connect.Connection c = new Connection();
        conn = c.getJDBC();      
        String sql = "SELECT * FROM Delivery_Note WHERE id_dn = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            String id_dn = rs.getString("id_dn");
            String id_staff = rs.getString("id_staff");
            String id_customer = rs.getString("id_customer");
            LocalDateTime datetime_shipment = rs.getTimestamp("date_shipment").toLocalDateTime();
            dn.setId_Dn(id_dn);
            dn.setId_Staff(id_staff);
            dn.setIdCustomer(id_customer);
            dn.setLocalDateTime(datetime_shipment);
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
    return new Delivery_Note(dn);
}

}
