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

    
    public Detail_PRN() {
        
    }
    /*
        create constructor
    */
    
    /*
        define getter, setter funcs
    */

    /**
     *
     * @return
     */

    
    @Override
    public String toString(){
        return null;
    }
    
    private double totalPrice(){ // tính giá của thiết bị * số lượng
        return 0.00;
    }
}
