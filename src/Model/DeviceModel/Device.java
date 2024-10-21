/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import Model.DeviceModel.Device_Type;
/**
 *
 * @author vntin
 */
public class Device {
    private String id_device; //id 
    private String name_device; // tên thiết bị
    private Specification spec;  // thông số kỹ thuật
    private Device_Type dv;  // loại thiết bị
    private double price; // giá thành
    
    
    Device () {
        
    }
    
    Device (String id_device
            , String name_device
            , Specification spec
            , Device_Type dv
            , double price){
        
        
    }
    
    Device (Object device){
        
    }
    
    // define getter, setter funcs
    
    /*
        code *
    */
    
    private String getIdDevice(){
        return new String(this.id_device);
    }
    
    
    @Override
    public String toString(){
        return null;
    }  
}
