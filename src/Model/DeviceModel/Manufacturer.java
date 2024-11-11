/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import Model.DeviceModel.Country.*;
import java.util.*;
/**
 *
 * @author vntin
 */
public class Manufacturer {
    private String id_manuf;   // Mã nhà sản xuất
    private String name_manuf; // Tên nhà sản xuất
    private String address_manuf;
    private String phone_manuf;
    private Country country;   // Quốc gia của nhà sản xuất
    
    // create default constructor

    public Manufacturer() {
    	this.id_manuf = new String("");
    	this.name_manuf = new String("");
        this.address_manuf = new String("");
        this.phone_manuf = new String("");
    	this.country = new Country();
    }
    // Constructor có tham số
    public Manufacturer(String id_manuf, String name_manuf, String address_manuf, String phone_manuf, Country country){
    	this.id_manuf = new String(id_manuf);
        this.name_manuf = new String(name_manuf);
        this.address_manuf = new String(address_manuf);
        this.phone_manuf = new String(phone_manuf);
        this.country = country;
    }
    // Constructor sao chép
    public Manufacturer(Object manufacturer){
    	if (manufacturer instanceof Manufacturer) { //Kiểm tra đối tượng device có phải kiểu Device
            Manufacturer manuf = (Manufacturer) manufacturer;
            this.id_manuf = new String(manuf.id_manuf);
            this.name_manuf = new String(manuf.name_manuf);
            this.address_manuf = new String(manuf.address_manuf);
            this.phone_manuf = new String(manuf.phone_manuf);
            this.country = new Country(manuf.country);
        } else {
            Manufacturer manuf = new Manufacturer();
            this.id_manuf = new String(manuf.id_manuf);
            this.name_manuf = new String(manuf.name_manuf);
            this.address_manuf = new String(manuf.address_manuf);
            this.phone_manuf = new String(manuf.phone_manuf);
            this.country = new Country(manuf.country);
        }
    }
    
    //GETTER VÀ SETTER
    
    public String getIdManuf(){
        return new String(this.id_manuf);
    }
    
    public void setIdManuf(String id_manuf){
        this.id_manuf = new String(id_manuf);
    }
    
    public String getNameManuf() {
        return new String(this.name_manuf);
    }

    public void setNameManuf(String name_manuf) {
        this.name_manuf = new String(name_manuf);
    }

    // Getter và Setter cho country
    public Country getCountry() {
        return new Country(this.country);  // Country --> copy constructor
    }

    public void setCountry(Country country) {
        this.country = new Country(country);  
    }
    
    public void setAddressManuf(String address_manuf){
        this.address_manuf = new String(address_manuf);
    }
    
    public String getAddressManuf(){
        return new String(this.address_manuf);
    }
    
    public void setPhoneManuf(String phone_manuf){
        this.phone_manuf = new String(phone_manuf);
    }
    
    public String getPhoneManuf(){
        return new String(this.phone_manuf);
    }
    
    @Override
    public String toString(){
        Manufacturer manuf = new Manufacturer(this);
//        return null;
        return "Manufacturer ID: " + manuf.getIdManuf() + "\n"
        + "Manufacturer Name: " + manuf.getNameManuf() + "\n"
        + "Country: " + manuf.getCountry().getNameCountry() + "\n"
        + "Address: " + manuf.getAddressManuf() + "\n" 
        + "Phone: " + manuf.getPhoneManuf() + "\n";
    }
    
    // done
    public static void main(String[] args){
        Manufacturer m = new Manufacturer();
        String id = new String("001");
        String name = new String("abc");
        String address = new String("ádasd");
        String phone = new String("áds");
        Country c = new Country("001", "VN");
        Manufacturer n = new Manufacturer(id, name, address, phone, c);
        System.out.println(n.toString());
    }
}
