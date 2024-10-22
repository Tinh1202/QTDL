/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Delivery;

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
    Detail_PRN(){
        id_prn = new String("");
        id_device = new String("");
        number = 1;
        price = 1;
    }
    Detail_PRN(String id_prn, String id_device, int number, double price){
        this.id_prn = new String(id_prn);
        this.id_device = new String(id_device);
        this.number = number;
        this.price = price;
    }
    Detail_PRN(Detail_PRN dp){
        this.id_prn = new String(dp.id_prn);
        this.id_device = new String(dp.id_device);
        this.number = dp.number;
        this.price = dp.price;
    }
    
    /*
        define getter, setter funcs
    */
    private String getId_prn(){
        return new String(this.id_prn);
    }
    private void setId_prn(String id_prn){
        this.id_prn = new String(id_prn);
    }
    private String getId_device(){
        return new String(this.id_device);
    }
    private void setId_device(String id_device){
        this.id_device = new String(id_device);
    }
    private int getNumber(){
        return number;
    }
    private void setNumber(int number){
        number = number;
    }
    private double getPrice(){
        return price;
    }
    private void setPrice(double price){
        price = price;
    }
    /**
     *
     * @return
     */

    
    @Override
    public String toString(){
        return null;
    }
    
    private double totalPrice(){ // tính giá của thiết bị * số lượng
        return (double) price * number;
    }
}
