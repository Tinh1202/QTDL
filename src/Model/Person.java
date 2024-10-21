/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// LocalDate type: {year: int, month: int, day:int}

/**
 *
 * @author vntin
 */
public class Person {
    
    private String id;
    private String fullname;
    private String phone_number;
    protected LocalDate birthDate;

    
    // create default constructor
    Person(){
        this.id = new String("");
        this.fullname = new String("");
        this.phone_number = new String("");
        this.birthDate = LocalDate.of(Integer.parseInt("1990"), Integer.parseInt("01"), Integer.parseInt("01"));
    }
    
    
    // create Person(parameters)
    /*
        create code
    */
    
    
   
    private String getId(){
        return this.id;
    }
    
    private void setId(String id){
        this.id = new String(id);
    }
    
    /*
    create setter, getter 
    create code 
    */
    
    
    //create function returns Person object
    /*
        code
    */
    
    @Override
    // return String Id, full name, birthdate, phone
    public String toString(){
        return null;
    }
   
}
