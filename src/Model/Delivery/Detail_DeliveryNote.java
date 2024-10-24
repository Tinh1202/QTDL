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
    public Detail_DeliveryNote() {
        id_dn = new String("");
        id_device = new String("");
        quantity = 0;
        price = 0.0;
    }

    public Detail_DeliveryNote(String id_dn, String id_device, int quantity, double price) {
        this.id_dn = new String(id_dn);
        this.id_device = new String(id_device);
        this.quantity = quantity;
        this.price = price;
    }
    
    public Detail_DeliveryNote(Object detail_dn){
        if(detail_dn instanceof Detail_DeliveryNote){
            Detail_DeliveryNote ddn = new Detail_DeliveryNote(detail_dn);
            this.id_dn = new String(ddn.id_dn);
            this.id_device = new String(ddn.id_device);
            this.quantity = ddn.quantity;
            this.price = ddn.price;
        } else{
            Detail_DeliveryNote ddn = new Detail_DeliveryNote();
            this.id_dn = new String(ddn.id_dn);
            this.id_device = new String(ddn.id_device);
            this.quantity = ddn.quantity;
            this.price = ddn.price;
        }
    }
    
    private double Calculate_TotalPrice(){ // tính tổng đơn giá = số lượng * đơn giá
        return (double) this.getQuantity() * this.getPrice();
    }
    
    public String getId_dn(){
        return new String(this.id_dn);
    }
    public void setId_dn(String id_dn){
        this.id_dn = new String(id_dn);
    }
    public String getId_device(){
        return new String(this.id_device);
    }
    public void setId_device(String id_device){
        this.id_dn = new String(id_device);
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    
    
    @Override
    public String toString(){
        return "Id Delivery Note: " + this.getId_dn() + "\n"
                + "Id device: " + this.getId_device() + "\n"
                + "Quantity: " + this.getQuantity() + "\n"
                + "Price: " + this.getPrice() + "\n";
    }
    
}
