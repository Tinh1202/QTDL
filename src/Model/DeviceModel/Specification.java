/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;
import java.util.ArrayList;

/**
 *
 * @author vntin
 */
public class Specification { // lớp thông số kỹ thuật
    private String id_spec;
    private String name_spec; // tên thông số
    private String data;   // số liệu kỹ thuật
    private Manufacturer manuf;
    
    public Specification() {
        this.name_spec = new String("");
        this.data = new String("");
        this.id_spec = new String("");
        this.manuf = new Manufacturer();
    }
    
    public Specification(String id_spec, String name_spec, String data, Manufacturer manuf){
        this.id_spec = new String(id_spec);
        this.name_spec = new String(name_spec);
        this.data = new String(data);
        this.manuf = new Manufacturer(manuf);
    }
    
    public Specification(Object spec){
        if (spec instanceof Specification){
            Specification spec_new = (Specification) spec;
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
            this.id_spec = new String(spec_new.id_spec);
            this.manuf = new Manufacturer(spec_new.manuf);
        } else {
            Specification spec_new = new Specification();
            this.name_spec = new String(spec_new.name_spec);
            this.data = new String(spec_new.data);
            this.id_spec = new String(spec_new.id_spec);
            this.manuf = new Manufacturer(spec_new.manuf);
        }
    }
    
    public String getNameSpec(){
        return new String(this.name_spec);
    }
    
    public void setNameSpec(String name_spec){
        this.name_spec = new String(name_spec);
    }
    
    public String getData(){
        return new String(this.data);
    }
    
    public void setData(String data){
        this.data = new String(this.data);
    }
    
    public String getIdSpec(){
        return new String(this.id_spec);
    }
    
    public void setIdSpec(String id_spec){
        this.id_spec = new String(id_spec);
    }
    
    public void setManuf(Manufacturer manuf){
        this.manuf = new Manufacturer(manuf);
    }
    
    public Manufacturer getManuf(){
        return this.manuf;
    }
    
    @Override
    public String toString(){
        Specification spec = new Specification(this);
        return "Id specification: " + this.getIdSpec() + "\n"
                + "Name specification: " + spec.getNameSpec() + "\n"
                + "Data specification: " + spec.getData() + "\n"
                + "Manufacturer: " + spec.manuf.getNameManuf();
    }
    
    // done
    public static void main(String[] args){
        
    }
    
}
