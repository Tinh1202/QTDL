/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import java.util.*;
/**
 *
 * @author vntin
 */
public class Device_Type {
    private String id_type;
    private String name_type;
    
    // create constructor

    Device_Type() {
        this.id_type = new String("");
        this.name_type = new String("");
    }
    
    Device_Type(String id_type, String name_type){
        this.id_type = new String(id_type);
        this.name_type = new String(name_type);
    }
    
    Device_Type(Object device_type){
        if (device_type instanceof Device_Type){
            Device_Type dv = new Device_Type(device_type);
            
            this.id_type = new String(dv.id_type);
            this.name_type = new String(dv.name_type);
        } else {
            Device_Type dv = new Device_Type();
            this.id_type = new String(dv.id_type);
            this.name_type = new String(dv.name_type);
        }
    }
    
    private String getIdType(){
        return new String(this.id_type);
    }
    
    private void setIdType(String name_type){
        this.id_type = new String(name_type);
    }
    
    private String getNameType(){
        return new String(this.name_type);
    }
    
    private void setNameType(String name_type){
        this.name_type = new String(name_type);
    }
    
    public String toString(){
        Device_Type dv = new Device_Type(this);
        return "Id device type: " + dv.getIdType() + "\n"
                + "Name device type: " + dv.getNameType() + "\n";
    }
    

}
