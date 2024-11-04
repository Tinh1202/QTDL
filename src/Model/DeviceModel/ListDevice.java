/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DeviceModel;

/**
 *
 * @author vntin
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import Model.Connect.Connection;

import java.util.ArrayList;
import java.util.Iterator;

public class ListDevice {
    private ArrayList<Device> listDevice;
    private int length;

    // Constructor không tham số
    public ListDevice() {
        this.listDevice = new ArrayList<>();
        this.length = 0;
    }

    // Constructor có tham số
    public ListDevice(ArrayList<Device> listDevice) {
        this.listDevice = new ArrayList<Device>(listDevice);
        this.length = listDevice.size();
    }

    // Constructor sao chép đối tượng
    public ListDevice(Object listObj) {
        if (listObj instanceof ListDevice) {
            ListDevice listDev = (ListDevice) listObj;
            this.listDevice = new ArrayList<Device>(listDev.listDevice);
            this.length = listDev.length;
        } else {
            ListDevice listDev = new ListDevice();
            this.listDevice = new ArrayList<Device>(listDev.listDevice);
            this.length = listDev.length;
        }
    }

    // Getter và Setter cho listDevice
    public ArrayList<Device> getListDevice() {
        return new ArrayList<Device>(this.listDevice);
    }

    public void setListDevice(ArrayList<Device> listDevice) {
        this.listDevice = new ArrayList<Device>(listDevice);
        this.length = listDevice.size();
    }

    public int getLength() {
        return this.length;
    }

    // Lấy danh sách thiết bị từ cơ sở dữ liệu
    public ArrayList<Device> getDevicesFromDatabase() {
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Device> devices = new ArrayList<Device>();

        try {
            Model.Connect.Connection connection = new Connection();
            conn = connection.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM device"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id_device = rs.getString("id_device");
                String name_device = rs.getString("name_device");
                double price = rs.getDouble("price");
                String id_type = rs.getString("id_type");
                
                // deivce {id_device, name_device, price, lst_devicetyoe, lst_specification}
                
                // Device Type Object
                Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);
                
                // lst Specification
                
//              ListSpecification lst_spec = new ListSpecification(new ListSpecification().ListSpec_MySQL(id_device));
                
                ListSpecification lst_spec = new ListSpecification();
                ArrayList<Specification> specs = new ArrayList<Specification>(lst_spec.ListSpec_MySQL(id_device));
                lst_spec.setListSpec(specs);

                Device d = new Device(id_device, name_device, lst_spec, dt, price);
                
                devices.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return devices;
    }
    
    
    
    // lấy device theo id_device
    public Device getDevice_MySQL(String id){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Device device = new Device();
        
        try {
            Model.Connect.Connection c = new Connection();
            conn = c.getJDBC();
            

            String sql = "SELECT * FROM device where id_device = ?"; // Thay "users" bằng bảng của bạn
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_device = new String(rs.getString("id_device"));
                String name_device = new String(rs.getString("name_device"));
                String id_type = new String(rs.getString("id_type"));
                double price = rs.getDouble("price");
                
                Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);
                
                ListSpecification lst_spec = new ListSpecification();
                ArrayList<Specification> specs = new ArrayList<Specification>(lst_spec.ListSpec_MySQL(id_device));
                lst_spec.setListSpec(specs);

                device = new Device(id_device, name_device, lst_spec, dt, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return device;
    }

    // Hiển thị danh sách thiết bị
    public void displayListDevice() {
        ArrayList<Device> devices = getDevicesFromDatabase();

        for (Device device : devices) {
            System.out.println(device.toString());
        }
    }

    // Thêm một thiết bị vào danh sách
    public ArrayList<Device> addDeviceToList(ArrayList<Device> deviceList, Device device) {
        ArrayList<Device> newDeviceList = new ArrayList<Device>(deviceList);
        newDeviceList.add(device);
        return newDeviceList;
    }

    // Xóa thiết bị ở đầu danh sách
    public ArrayList<Device> deleteHeadDeviceFromList(ArrayList<Device> deviceList) {
        ArrayList<Device> newDeviceList = new ArrayList<Device>(deviceList);
        if (!newDeviceList.isEmpty()) {
            newDeviceList.remove(0);
        }
        return newDeviceList;
    }

    // Xóa thiết bị ở cuối danh sách
    public ArrayList<Device> deleteTailDeviceFromList(ArrayList<Device> deviceList) {
        ArrayList<Device> newDeviceList = new ArrayList<>(deviceList);
        if (!newDeviceList.isEmpty()) {
            newDeviceList.remove(newDeviceList.size() - 1);
        }
        return newDeviceList;
    }

    // Xóa thiết bị theo id
    public ArrayList<Device> deleteDeviceById(ArrayList<Device> deviceList, String id) {
        ArrayList<Device> newDeviceList = new ArrayList<>(deviceList);

        Iterator<Device> iterator = newDeviceList.iterator();
        while (iterator.hasNext()) {
            Device device = iterator.next();
            if (device.getIdDevice().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }

        return newDeviceList;
    }

    // Xóa thiết bị theo tên
    public ArrayList<Device> deleteDeviceByName(ArrayList<Device> deviceList, String name) {
        ArrayList<Device> newDeviceList = new ArrayList<>(deviceList);

        Iterator<Device> iterator = newDeviceList.iterator();
        while (iterator.hasNext()) {
            Device device = iterator.next();
            if (device.getNameDevice().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }

        return newDeviceList;
    }
    
    public static void main(String[] args){
        ListDevice l = new ListDevice();
        ArrayList<Device> lst_device = new ArrayList<Device>(l.getDevicesFromDatabase());
        
        for (Device d : lst_device){
            System.out.println(d.toString()); // done
        }
        
        Device device = new ListDevice().getDevice_MySQL("D001");
        System.out.println(device.toString()); // done
    }
   
}