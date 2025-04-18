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
    public Supplier(){
        this.ID_supplier = "";
        this.name_supplier = "";
        this.address_supplier = "";
        this.phone_supplier = "";
        this.country = new Country();
    }
    
    
    //CONSTRUCTOR CÓ THAM SỐ
    public Supplier(String ID_supplier, String name_supplier, String address_supplier, String phone_supplier, Country country) {
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
    public Supplier(Object supplier) {
        if (supplier instanceof Supplier) {
            Supplier s = (Supplier) supplier;
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
    
    public String getIDSupplier(){
        return new String(this.ID_supplier);
    }
    
    public void setIDSupplier(String ID_supplier){
        this.ID_supplier = new String(ID_supplier);
    }
    
    public String getNameSupplier() {
        return new String(this.name_supplier);
    }
    
    public void setNameSupplier(String name_supplier) {
        this.name_supplier = new String(name_supplier);
    }
    
    public String getAddressSupplier() {
        return new String(this.address_supplier);
    }
    
    public void setAddressSupplier(String address_supplier) {
        this.address_supplier = new String(address_supplier);
    }
    
    public String getPhoneSupplier() {
        return new String(this.phone_supplier);
    }
    
    public void setPhoneSupplier(String phone_supplier) {
        this.phone_supplier = new String(phone_supplier);
    }
    
    public Country getCountry() {
        return new Country(this.country);  // Country --> constructor sao chép
    }
    public void setCountry(Country country) {
        this.country = new Country(country);  
    }
    
    @Override
    public String toString(){ // return id: id; name: name; phone: phone; address: address
//        return null;
    	return "ID: " + this.getIDSupplier() + "\n"
        + "Name: " + this.getNameSupplier() + "\n"
        + "Phone: " + this.getPhoneSupplier() + "\n"
        + "Address: " + this.getAddressSupplier() + "\n"
        + "Country: " + this.getCountry().getNameCountry() + "\n";
    }
}
