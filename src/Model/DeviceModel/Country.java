/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

/**
 *
 * @author vntin
 */
public class Country {
    private String id_country;
    private String name_country;
    
    public Country(){ //default constructor
        this.id_country = new String("");
        this.name_country = new String("");
    }
    
    // constructor parameters
    public Country(String id_country, String name_country){
        this.id_country = new String(id_country);
        this.name_country = new String(name_country);
    }
    
    // constructor object
    public Country(Object country){
        if (country instanceof Country){
            Country c = new Country(country);
            
            this.id_country = new String(c.id_country);
            this.name_country = new String(c.name_country);
            
        } else {
            Country c = new Country();
            this.id_country = new String(c.id_country);
            this.name_country = new String(c.name_country);
        }
    }
    
    // define getter, setter
    public String getIdCountry(){
        return new String(this.id_country);
    }
    
    public void setIdCountry(String id_country){
        this.id_country = new String(id_country);
    }
    
    
    public String getNameCountry(){
        return new String(this.name_country);
    }
    
    public void setNameCountry(String name_country){
        this.name_country = new String(name_country);
    }
    
    @Override 
    public String toString(){  //toString funcs
        Country c = new Country(this);
        return "Country ID: " + c.getIdCountry() + "\n"
                + "Country Name: " + c.getNameCountry() + "\n";
    }
}



