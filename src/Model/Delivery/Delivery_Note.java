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
        this.id_dn = new String("");
        this.id_staff = new String("");
        this.id_customer = new String("");
        this.datetime_shipment = LocalDateTime.of(2023, 11, 28, 10, 30, 0);
        this.ListDetailDN = new ArrayList<Detail_DeliveryNote>();
    }

    Delivery_Note(String id_dn, String id_staff, String id_customer, LocalDateTime datetime_shipment) {
        this.id_dn = new String(id_dn);
        this.id_staff = new String(id_staff);
        this.id_customer = new String(id_customer);
        this.datetime_shipment = datetime_shipment;
    }
    
    Delivery_Note(Delivery_Note dn){
        this.id_dn = new String(dn.id_dn);
        this.id_staff = new String(dn.id_staff);
        this.id_customer = new String(dn.id_customer);
    }
    
    /*
    misc code *
    */
    
    private String getId_Dn(){
        return new String(this.id_dn);
    }
    private void setId_Dn(String id_dn){
        this.id_dn = new String(id_dn);
    }
    private String getId_Staff(){
        return new String(this.id_staff);
    }
    private void setId_Staff(String id_staff){
        this.id_staff = new String(id_staff);
    }
    private String getId_Customer(){
        return new String(this.id_customer);
    }
    private void setIdCustomer(String id_customer){
        this.id_customer = new String(id_customer);
    }
//    private LocalDateTime getDatetimeShipment(){
//        return new LocalDateTime(this.datetime_shipment);
//    }
//    private LocalDateTime setDatetimeShipment(){   
//    }
    
    
    
    
    
    @Override
    public String toString(){
        return null;
    }
}
