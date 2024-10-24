/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.StoreModel;

import java.util.*;

/**
 *
 * @author vntin
 */
public class Store {
    private String name_store;
    private String address_store;
    private String phone_store;
    
    
    public Store(){
        this.name_store = new String("");
        this.address_store = new String("");
        this.phone_store = new String("");
    }

    public Store(String name_store, String address_store, String phone_store) {
        this.name_store = new String(name_store);
        this.address_store = new String(address_store);
        this.phone_store = new String(phone_store);
    }
    
    public Store(Object store){
        if (store instanceof Store){
            Store s = new Store(store);
            this.name_store = new String(s.name_store);
            this.address_store = new String(s.address_store);
            this.phone_store = new String(s.phone_store);
        } else {
            Store s = new Store();
            this.name_store = new String(s.name_store);
            this.address_store = new String(s.address_store);
            this.phone_store = new String(s.phone_store);
        }
    }
    
    @Override
    public String toString(){
        return "Name store: " + this.name_store + "\n"
                + "Address store: " + this.address_store + "\n"
                + "Phone store: " + this.phone_store + "\n";
    }
}
