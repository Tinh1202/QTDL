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
    private Country country;   // Quốc gia của nhà sản xuất
    
    // create default constructor

    public Manufacturer() {
    	this.id_manuf = new String("");
    	this.name_manuf = new String("");
    	this.country = new Country();
    }
    // Constructor có tham số
    public Manufacturer(String id_manuf, String name_manuf, Country country){
    	this.id_manuf = new String(id_manuf);
        this.name_manuf = new String(name_manuf);
        this.country = country;
    }
    // Constructor sao chép
    public Manufacturer(Object manufacturer){
    	if (manufacturer instanceof Manufacturer) { //Kiểm tra đối tượng device có phải kiểu Device
            Manufacturer manuf = (Manufacturer) manufacturer;
            this.id_manuf = new String(manuf.id_manuf);
            this.name_manuf = new String(manuf.name_manuf);
            this.country = new Country(manuf.country);
        } else {
            Manufacturer manuf = new Manufacturer();
            this.id_manuf = new String(manuf.id_manuf);
            this.name_manuf = new String(manuf.name_manuf);
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
    //define setter, getter
    /*
     --> code *
    */
    
    @Override
    public String toString(){
        Manufacturer manuf = new Manufacturer(this);
//        return null;
        return "Manufacturer ID: " + manuf.getIdManuf() + "\n"
        + "Manufacturer Name: " + manuf.getNameManuf() + "\n"
        + "Country: " + manuf.getCountry().getNameCountry() + "\n";
    }
    
    // done
    public static void main(String[] args){
        Manufacturer m = new Manufacturer();
        String id = new String("001");
        String name = new String("abc");
        Country c = new Country("001", "VN");
        Manufacturer n = new Manufacturer(id, name, c);
        System.out.println(n.toString());
    }
}
