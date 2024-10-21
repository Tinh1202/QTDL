/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;
import java.util.*;
/**
 *
 * @author vntin
 */
public class Detail_DeliveryNote { // chi tiết phiếu xuất kho cho một thiết bị
    private String id_dn; // mã phiếu xuất (delivery note) 
    private String id_device; // mã thiết bị
    private int quantity; // số lượng 
    private double price;

    
    // default constructor
    Detail_DeliveryNote() {
        
    }

    Detail_DeliveryNote(String id_dn, String id_device, int quantity, double price) {
        this.id_dn = id_dn;
        this.id_device = id_device;
        this.quantity = quantity;
        this.price = price;
    }
    
    Detail_DeliveryNote(Object detail_dn){
        
    }
    
    private double Calculate_TotalPrice(){ // tính tổng đơn giá = số lượng * đơn giá
        return 0.00;
    }
    
    @Override
    public String toString(){
        return null;
    }
}
