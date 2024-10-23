/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import Model.DeviceModel.Device;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
        LocalDate ld = LocalDate.of(1990, 01, 01);
        LocalTime lt = LocalTime.of(01, 01, 01);
        this.datetime_shipment = LocalDateTime.of(ld, lt);
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
    
    private LocalDateTime getLocalDateTime(){
        return this.datetime_shipment;
    }
    
    private void setLocalDateTime(LocalDateTime ldt){
        this.datetime_shipment = ldt;
    }
   
    
    @Override
    public String toString(){
        return "Id delivery note: " + this.getId_Dn() + "\n"
                + "Id staff: " + this.getId_Staff() + "\n"
                + "Id Customer: " + this.getId_Customer() + "\n"
                + "Date get delivery note: " + this.datetime_shipment.format(DateTimeFormatter.ISO_DATE);
    }
}
