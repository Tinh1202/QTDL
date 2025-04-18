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

public class ListDeviceType {
    private ArrayList<Device_Type> listDeviceType;
    private int length;

    // Constructor không tham số
    public ListDeviceType() {
        this.listDeviceType = new ArrayList<>();
        this.length = 0;
    }

    // Constructor có tham số
    public ListDeviceType(ArrayList<Device_Type> listDeviceType) {
        this.listDeviceType = new ArrayList<Device_Type>(listDeviceType);
        this.length = listDeviceType.size();
    }

    // Constructor sao chép đối tượng
    public ListDeviceType(Object listObj) {
        if (listObj instanceof ListDeviceType) {
            ListDeviceType listDevType = (ListDeviceType) listObj;
            this.listDeviceType = new ArrayList<Device_Type>(listDevType.listDeviceType);
            this.length = listDevType.length;
        } else {
            ListDeviceType listDevType = new ListDeviceType ();
            this.listDeviceType = new ArrayList<Device_Type>(listDevType.listDeviceType);
            this.length = listDevType.length;
        }
    }

    // Getter và Setter cho listDeviceType
    public ArrayList<Device_Type> getListDeviceType() {
        return new ArrayList<Device_Type>(this.listDeviceType);
    }

    public void setListDeviceType(ArrayList<Device_Type> listDeviceType) {
        this.listDeviceType = new ArrayList<Device_Type>(listDeviceType);
        this.length = listDeviceType.size();
    }

    public int getLength() {
        return this.length;
    }

    // Lấy danh sách loại thiết bị từ cơ sở dữ liệu
    
//    public ArrayList<Device_Type> getDeviceTypesFromDatabase() {
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        ArrayList<Device_Type> deviceTypes = new ArrayList<Device_Type>();
//
//        try {
//            Model.Connect.Connection connection = new Connection();
//            conn = connection.getJDBC();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM devicetype"; 
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String id_type = rs.getString("id_devicetype");
//                String name_type = rs.getString("name_devicetype");
//
//                Device_Type deviceType = new Device_Type(id_type, name_type);
//                deviceTypes.add(deviceType);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new ArrayList<>(deviceTypes);
//    }

    public ArrayList<Device_Type> getDeviceTypesFromDatabase() {
    java.sql.Connection conn = null;
    CallableStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Device_Type> deviceTypes = new ArrayList<Device_Type>();

    try {
        // Kết nối tới cơ sở dữ liệu
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();

        // Gọi thủ tục đã định nghĩa trong cơ sở dữ liệu
        String sql = "{CALL GetDeviceTypes()}";
        stmt = conn.prepareCall(sql);

        // Thực thi thủ tục
        rs = stmt.executeQuery();

        // Xử lý kết quả truy vấn
        while (rs.next()) {
            String id_type = rs.getString("id_devicetype");
            String name_type = rs.getString("name_devicetype");

            Device_Type deviceType = new Device_Type(id_type, name_type);
            deviceTypes.add(deviceType);
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

    return new ArrayList<>(deviceTypes);
}

    
    // Hiển thị danh sách loại thiết bị
    public void displayListDeviceType() {
        ArrayList<Device_Type> deviceTypes = getDeviceTypesFromDatabase();

        for (Device_Type deviceType : deviceTypes) {
            System.out.println(deviceType.toString());
        }
    }

    // Thêm một loại thiết bị vào danh sách
    public ArrayList<Device_Type> addDeviceTypeToList(ArrayList<Device_Type> deviceTypeList, Device_Type deviceType) {
        ArrayList<Device_Type> newDeviceTypeList = new ArrayList<Device_Type>(deviceTypeList);
        newDeviceTypeList.add(deviceType);
        return newDeviceTypeList;
    }

    // Xóa loại thiết bị ở đầu danh sách
    public ArrayList<Device_Type> deleteHeadDeviceTypeFromList(ArrayList<Device_Type> deviceTypeList) {
        ArrayList<Device_Type> newDeviceTypeList = new ArrayList<Device_Type>(deviceTypeList);
        if (!newDeviceTypeList.isEmpty()) {
            newDeviceTypeList.remove(0);
        }
        return newDeviceTypeList;
    }

    // Xóa loại thiết bị ở cuối danh sách
    public ArrayList<Device_Type> deleteTailDeviceTypeFromList(ArrayList<Device_Type> deviceTypeList) {
        ArrayList<Device_Type> newDeviceTypeList = new ArrayList<Device_Type>(deviceTypeList);
        if (!newDeviceTypeList.isEmpty()) {
            newDeviceTypeList.remove(newDeviceTypeList.size() - 1);
        }
        return newDeviceTypeList;
    }

    // Xóa loại thiết bị theo id
    public ArrayList<Device_Type> deleteDeviceTypeById(ArrayList<Device_Type> deviceTypeList, String id) {
        ArrayList<Device_Type> newDeviceTypeList = new ArrayList<Device_Type>(deviceTypeList);

        Iterator<Device_Type> iterator = newDeviceTypeList.iterator();
        while (iterator.hasNext()) {
            Device_Type deviceType = iterator.next();
            if (deviceType.getIdType().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }

        return newDeviceTypeList;
    }

    // Xóa loại thiết bị theo tên
    public ArrayList<Device_Type> deleteDeviceTypeByName(ArrayList<Device_Type> deviceTypeList, String name) {
        ArrayList<Device_Type> newDeviceTypeList = new ArrayList<Device_Type>(deviceTypeList);

        Iterator<Device_Type> iterator = newDeviceTypeList.iterator();
        while (iterator.hasNext()) {
            Device_Type deviceType = iterator.next();
            if (deviceType.getNameType().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }

        return newDeviceTypeList;
    }
    
        public Device_Type findDeviceTypeById(String id) {
    // Lấy danh sách thiết bị từ cơ sở dữ liệu
    ArrayList<Device_Type> deviceTypes = getDeviceTypesFromDatabase();

    // Duyệt qua danh sách và tìm loại thiết bị có id_type tương ứng
    for (Device_Type deviceType : deviceTypes) {
        if (deviceType.getIdType().equalsIgnoreCase(id)) {
            return deviceType; // Trả về đối tượng Device_Type nếu tìm thấy
        }
    }

    return null; // Nếu không tìm thấy, trả về null
}
    // done
    public static void main(String[] args){
        ListDeviceType l = new ListDeviceType();
        ArrayList<Device_Type> lst = new ArrayList<>(l.getDeviceTypesFromDatabase());
        
        for (Device_Type t : lst){
            System.out.println(t.getIdType());
        }
    }
}