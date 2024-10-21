/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import java.util.*;
import Model.DeviceModel.Country;
/**
 *
 * @author vntin
 */

// class nhà cung cấp
public class Supplier {
    private String ID_supplier; //mã nhà cung cấp
    private String name_supplier; //tên nhà cung cấp
    private String address_supplier; // địa chỉ
    private String phone_supplier; // sđt
    private Country country;
    
    
    // create default constructor
    Supplier(){
        
    }
    
    /*
        create other constructors
    --> code *
    */
    
    
    private String getIDSupplier(){
        return new String(this.ID_supplier);
    }
    
    private void setIDSupplier(String ID_supplier){
        this.ID_supplier = new String(ID_supplier);
    }
    
    /*
        create funcs setter, getter
    code *
    */
    
    @Override
    public String toString(){ // return id: id; name: name; phone: phone; address: address
        return null;
    }
    
    
}
