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
    private String id_detail_pr;
    private String id_product_receipt;
    private String id_device;  // mã thiết bị
    private int number;  //  số lượng

    /*
        create constructor
    */
    public Detail_PRN(){
        this.id_detail_pr = new String("");
        this.id_product_receipt = new String("");
        this.id_device = new String("");
        this.number = 0;
    }
    public Detail_PRN(String id_detail_pr, String id_product_receipt, String id_device, int number){
        this.id_detail_pr = new String(id_detail_pr);
        this.id_product_receipt = new String(id_product_receipt);
        this.id_device = new String(id_device);
        this.number = number;
    }
    public Detail_PRN(Detail_PRN dp){
        this.id_detail_pr = new String(dp.id_detail_pr);
        this.id_product_receipt = new String(dp.id_detail_pr);
        this.id_device = new String(dp.id_device);
        this.number = dp.number;
    }
    
    /*
        define getter, setter funcs
    */
    public void setIdDetail_PR(String id_detail_pr){
        this.id_detail_pr = new String(id_detail_pr);
    }
    
    public String getIdDetail_PR(){
        return this.id_detail_pr;
    }
    
    public String getId_prn(){
        return new String(this.id_product_receipt);
    }
    public void setId_prn(String id_prn){
        this.id_product_receipt = new String(id_prn);
    }
    public String getId_device(){
        return new String(this.id_device);
    }
    public void setId_device(String id_device){
        this.id_device = new String(id_device);
    }
    public int getNumber(){
        return this.number;
    }
    public void setNumber(int number){
        this.number = number;
    }


    
    @Override
    public String toString(){
        return "id: " + this.getId_prn() + "\n"
                + "id device: " + this.getId_device() + "\n"
                + "Quantity: " + this.getNumber() + "\n";
    }
    

    
    // lấy một chi tiết phiếu theo id của chi tiết phiếu
    public Detail_PRN getDPRN_MySQL(String id) {
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Detail_PRN prn = new Detail_PRN();

        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();

            String sql = "SELECT * FROM detail_product_receipt WHERE id_detail_pr = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String id_detail_pr = rs.getString("id_detail_pr");
                String id_product_receipt = rs.getString("id_product_receipt");
                String id_device = rs.getString("id_device");
                int number = rs.getInt("number");

                prn.setIdDetail_PR(id_detail_pr);
                prn.setId_prn(id_product_receipt);
                prn.setId_device(id_device);
                prn.setNumber(number);
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

        return new Detail_PRN(prn);
    }

}
