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
    private String ID_supplier;       // Mã nhà cung cấp
    private String name_supplier;     // Tên nhà cung cấp
    private String address_supplier;  // Địa chỉ nhà cung cấp
    private String phone_supplier;    // Số điện thoại nhà cung cấp
    private Country country;          // Quốc gia của nhà cung cấp
    
    
    // CONSTRUCTOR MẶC ĐỊNH KHÔNG THAM SỐ 
    Supplier(){
        this.ID_supplier = "";
        this.name_supplier = "";
        this.address_supplier = "";
        this.phone_supplier = "";
        this.country = new Country();
    }
    
    
    //CONSTRUCTOR CÓ THAM SỐ
    Supplier(String ID_supplier, String name_supplier, String address_supplier, String phone_supplier, Country country) {
        this.ID_supplier = ID_supplier;
        this.name_supplier = name_supplier;
        this.address_supplier = address_supplier;
        this.phone_supplier = phone_supplier;
        this.country = country;
    }
    /*
        create other constructors
    --> code *
    */
    // CONSTRUCTOR SAO CHÉP
    Supplier(Object supplier) {
        if (supplier instanceof Supplier) {
            Supplier s = new Supplier(supplier);
            this.ID_supplier = s.getIDSupplier();
            this.name_supplier = s.getNameSupplier();
            this.address_supplier = s.getAddressSupplier();
            this.phone_supplier = s.getPhoneSupplier();
            this.country = s.getCountry();
        } else {
            Supplier s = new Supplier();
            this.ID_supplier = new String(s.ID_supplier);
            this.name_supplier = new String(s.name_supplier);
            this.address_supplier = new String(s.address_supplier);
            this.phone_supplier = new String(s.phone_supplier);
            this.country = new Country(s.country);
        }
    }
    
    private String getIDSupplier(){
        return new String(this.ID_supplier);
    }
    
    private void setIDSupplier(String ID_supplier){
        this.ID_supplier = new String(ID_supplier);
    }
    
    private String getNameSupplier() {
        return new String(this.name_supplier);
    }
    
    private void setNameSupplier(String name_supplier) {
        this.name_supplier = new String(name_supplier);
    }
    
    private String getAddressSupplier() {
        return new String(this.address_supplier);
    }
    
    private void setAddressSupplier(String address_supplier) {
        this.address_supplier = new String(address_supplier);
    }
    
    private String getPhoneSupplier() {
        return new String(this.phone_supplier);
    }
    
    private void setPhoneSupplier(String phone_supplier) {
        this.phone_supplier = new String(phone_supplier);
    }
    
    private Country getCountry() {
        return new Country(this.country);  // Country --> constructor sao chép
    }
    private void setCountry(Country country) {
        this.country = new Country(country);  
    }
    /*
        create funcs setter, getter
    code *
    */
    
    @Override
    public String toString(){ // return id: id; name: name; phone: phone; address: address
//        return null;
    	return "ID: " + this.getIDSupplier() + "; "
        + "Name: " + this.getNameSupplier() + "; "
        + "Phone: " + this.getPhoneSupplier() + "; "
        + "Address: " + this.getAddressSupplier() + "; "
        + "Country: " + this.getCountry().toString();
    }
    
    
}
