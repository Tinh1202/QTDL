/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import Model.DeviceModel.Device;
import java.time.*;
import java.util.*;

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
    
    
    Delivery_Note() {
        
    }

    Delivery_Note(String id_dn, String id_staff, String id_customer, LocalDateTime datetime_shipment) {
        this.id_dn = id_dn;
        this.id_staff = id_staff;
        this.id_customer = id_customer;
        this.datetime_shipment = datetime_shipment;
    }
    
    Delivery_Note(Object dn){
        
    }
    
    /*
    misc code *
    */
    
    @Override
    public String toString(){
        return null;
    }
}
