/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.UserModel;
import java.time.LocalDate;
/**
 *
 * @author vntin
 */
public class Customer extends Person{
    private String address;
    
    Customer(){
        super();
        this.address = new String("");
    }
    
    Customer(String id, String fullname, String phone_number, LocalDate birthdate, String address){
        // super(id, fullname, phone_number, birthdate);
        this.address = new String(address);
    }
    
    Customer(Object customer){
        
    }
    
    // define setter, getter
    
    /*
    code *
    */
    
    @Override
    public String toString(){
        return null; // id, fullname, phone, birthdata, address
    }
    
    
}
