/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import Model.Connect.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.*;
/**
 *
 * @author vntin
 */
public class Device_Type {
    private String id_type;
    private String name_type;
    
    // create constructor

    public Device_Type() {
        this.id_type = new String("");
        this.name_type = new String("");
    }
    
    public Device_Type(String id_type, String name_type){
        this.id_type = new String(id_type);
        this.name_type = new String(name_type);
    }
    
    public Device_Type(Object device_type){
        if (device_type instanceof Device_Type){
            Device_Type dv = new Device_Type(device_type);
            
            this.id_type = new String(dv.id_type);
            this.name_type = new String(dv.name_type);
        } else {
            Device_Type dv = new Device_Type();
            this.id_type = new String(dv.id_type);
            this.name_type = new String(dv.name_type);
        }
    }
    
    public String getIdType(){
        return new String(this.id_type);
    }
    
    public void setIdType(String name_type){
        this.id_type = new String(name_type);
    }
    
    public String getNameType(){
        return new String(this.name_type);
    }
    
    public void setNameType(String name_type){
        this.name_type = new String(name_type);
    }
    
    @Override
    public String toString(){
        Device_Type dv = new Device_Type(this);
        return "Id device type: " + dv.getIdType() + "\n"
                + "Name device type: " + dv.getNameType() + "\n";
    }
    
    // hàm lấy device type từ cột của device
    public Device_Type getDeviceType_MySQL(String id){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Device_Type dt = null;  // Đặt thành null để chỉ tạo khi có kết quả

    try {
        Model.Connect.Connection c = new Model.Connect.Connection();
        conn = c.getJDBC();
        String sql = "SELECT * FROM device_type WHERE id_type = ?"; // Thay "account" bằng tên bảng thực tế

        // Chuẩn bị câu lệnh SQL với các tham số
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);  // Tham số thứ nhất

        // Thực thi truy vấn
        rs = stmt.executeQuery();

        // Lấy dữ liệu từ ResultSet
        if (rs.next()) {
              dt = new Device_Type();
              dt.setIdType(rs.getString("id_type"));
              dt.setNameType(rs.getString("name_type"));
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

    return dt;
    }
    
    public static void main(String[] args){
        Device_Type dv = new Device_Type();
        dv = dv.getDeviceType_MySQL("DT001");
        System.out.println(dv.getNameType());
    }
}
