/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

/**
 *
 * @author vntin
 */

import java.util.*;

public class ListSupplier {
    private ArrayList<Supplier> list_suppliers;
    private int length;
    
    public ListSupplier(){
        this.list_suppliers = new ArrayList<Supplier>();
        this.length = 0;
    }
    
    public ListSupplier(ArrayList<Supplier> list_supplier){
        this.list_suppliers = new ArrayList<Supplier>(list_supplier);
        this.length = list_supplier.size();
    }
    
    
}
