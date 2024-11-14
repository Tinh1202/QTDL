/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;
import Model.DeviceModel.Device_Type;
import java.util.*;
/**
 *
 * @author vntin
 */

//KHAI BÁO LỚP VÀ THUỘC TÍNH
public class Device {
    private String id_device; //id 
    private String name_device; // tên thiết bị
    private ListSpecification lst_spec;
    private Device_Type dv;  // loại thiết bị
    private double price; // giá thành
//    private ArrayList<Specifications> ListSpec;  // thêm vào array các thông số kỹ thuật
    
    //CONTRUCTOR KHÔNG THAM SỐ -->Khởi tạo các giá trị ban đầu
    public Device () {
        this.id_device = new String("");
        this.name_device = new String("");
        this.lst_spec = new ListSpecification();
        this.dv = new Device_Type();
        this.price = 0.0;
    }
    //CONTRUCTOR CÓ THAM SỐ --> Khỏi tạo các đối tượng để truyền vào
    public Device (String id_device
            , String name_device
            , ListSpecification lst_spec
            , Device_Type dv
            , double price){
    	this.id_device = new String(id_device);
        this.name_device = new String(name_device);
        this.lst_spec = lst_spec;  // đổi thành arrayList
        this.dv = dv;
        this.price = price;
    }
    public Device(String id_device, String name_device, Device_Type dv, double price) {
    this.id_device = new String(id_device);
    this.name_device = new String(name_device);
    this.lst_spec = new ListSpecification(); // Khởi tạo mặc định nếu không cần thiết
    this.dv = dv;
    this.price = price;
}
    //CONTRUCTOR COPY --> Sao chép tất cả các thuộc tính của đối tượng đó
    public Device (Object device){
        if (device instanceof Device) { //Kiểm tra đối tượng device có phải kiểu Device
            Device dev = (Device) device;
            this.id_device = new String(dev.getIdDevice()); // Lấy giá trị id_device từ đối tượng dev gán cho hiện tại
            this.name_device = new String(dev.getNameDevice());
            this.lst_spec = new ListSpecification(dev.getListSpec());  // tương tự
            this.dv = dev.getDeviceType();
            this.price = dev.getPrice();
        } else {
            Device dev = new Device();
            this.id_device = new String(dev.getIdDevice()); // Lấy giá trị id_device từ đối tượng dev gán cho hiện tại
            this.name_device = new String(dev.getNameDevice());
            this.lst_spec = new ListSpecification(dev.getListSpec());
            this.dv = dev.getDeviceType();
            this.price = dev.getPrice();
        }
    }
    
    //GETTER -> truy xuất giá trị  VÀ SETTER ->gán/thay đổi giá trị
    public String getIdDevice(){
        return new String(this.id_device);
    }
    public void setIdDevice(String id_device){
        this.id_device = new String(id_device);
        
    }
    
    public String getNameDevice(){
        return new String(this.name_device);
    }

    public void setNameDevice(String name_device){
        this.name_device = new String(name_device);
    }
    
    public ListSpecification getListSpec(){
        return new ListSpecification(this.lst_spec); //Lớp Specification --> constructor sao chép 
    }

    public void setListSpec(ListSpecification lst_spec){
        this.lst_spec = new ListSpecification(lst_spec); 
    }
    
    public Device_Type getDeviceType(){
        return new Device_Type(this.dv); // Lớp Device_Type --> constructor sao chép
    }

    public void setDeviceType(Device_Type dv){
        this.dv = new Device_Type(dv);
    }
    
    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }
    
    @Override
    public String toString(){
//        return null;
    	Device d = new Device(this);
    	return "Device ID: " + d.getIdDevice() + "\n"
        		+ "Device Name: " + d.getNameDevice() + "\n"
        		+ "Device Type: " + d.getDeviceType().getNameType() + "\n"
        		+ "Price: " + d.getPrice() + "\n";
    }  
    
    
    
     // done
    public static void main(String[] args){
        String id = new String("001");
        String name = new String("jshd");
        Device_Type dt = new Device_Type("001", "ádas");
        ListSpecification lst_spec = new ListSpecification();
        double price = 12323232.00;
        Device d = new Device(id, name, lst_spec, dt, price);
        System.out.println(d.toString());
    }
    
}
