/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import Model.DeviceModel.Country;
import java.util.*;
/**
 *
 * @author vntin
 */
public class Manufacturer {
    private String id_manuf;
    private String name_manuf;
    private Country country;
    
    // create default constructor

    Manufacturer() {
        
    }
    
    Manufacturer(String id_manuf, String name_manuf, Country country){
        
    }
    
    Manufacturer(Object manufacturer){
        
    }
    
    //define setter, getter
    /*
     --> code *
    */
    
    @Override
    public String toString(){
        Manufacturer manuf = new Manufacturer(this);
        // return ->
        return null;
    }
}
