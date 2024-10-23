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

//KHAI BÁO LỚP VÀ THUỘC TÍNH
public class Device {
    private String id_device; //id 
    private String name_device; // tên thiết bị
    private Specification spec;  // thông số kỹ thuật
    private Device_Type dv;  // loại thiết bị
    private double price; // giá thành
    
    //CONTRUCTOR KHÔNG THAM SỐ -->Khởi tạo các giá trị ban đầu
    Device () {
        this.id_device = new String("");
        this.name_device = new String("");
        this.spec = new Specification();
        this.dv = new Device_Type();
        this.price = 0.0;
    }
    //CONTRUCTOR CÓ THAM SỐ --> Khỏi tạo các đối tượng để truyền vào
    Device (String id_device
            , String name_device
            , Specification spec
            , Device_Type dv
            , double price){
    	this.id_device = new String(id_device);
        this.name_device = new String(name_device);
        this.spec = spec;
        this.dv = dv;
        this.price = price;
    }
    //CONTRUCTOR COPY --> Sao chép tất cả các thuộc tính của đối tượng đó
    Device (Object device){
        if (device instanceof Device) { //Kiểm tra đối tượng device có phải kiểu Device
            Device dev = (Device) device;
            this.id_device = new String(dev.getIdDevice()); // Lấy giá trị id_device từ đối tượng dev gán cho hiện tại
            this.name_device = new String(dev.getNameDevice());
            this.spec = dev.getSpec();
            this.dv = dev.getDeviceType();
            this.price = dev.getPrice();
        }
    }
    
    // define getter, setter funcs
    
    /*
        code *
    */
    
    //GETTER -> truy xuất giá trị  VÀ SETTER ->gán/thay đổi giá trị
    private String getIdDevice(){
        return new String(this.id_device);
    }
    private String setIdDevice(String id_device){
        return new String(this.id_device);
    }
    
    private String getNameDevice(){
        return new String(this.name_device);
    }

    private void setNameDevice(String name_device){
        this.name_device = new String(name_device);
    }
    
    private Specification getSpec(){
        return new Specification(this.spec); //Lớp Specification --> constructor sao chép 
    }

    private void setSpec(Specification spec){
        this.spec = new Specification(spec); 
    }
    
    private Device_Type getDeviceType(){
        return new Device_Type(this.dv); // Lớp Device_Type --> constructor sao chép
    }

    private void setDeviceType(Device_Type dv){
        this.dv = new Device_Type(dv);
    }
    
    private double getPrice(){
        return this.price;
    }

    private void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString(){
//        return null;
    	Device d = new Device(this);
    	return "Device ID: " + d.getIdDevice() + "\n"
        		+ "Device Name: " + d.getNameDevice() + "\n"
        		+ "Specification: " + d.getSpec() + "\n"
        		+ "Device Type: " + d.getDeviceType() + "\n"
        		+ "Price: " + d.getPrice() + "\n";
    }  
}
