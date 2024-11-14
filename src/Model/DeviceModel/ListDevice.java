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
    private java.sql.Connection conn;

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

//    // Lấy danh sách thiết bị từ cơ sở dữ liệu
//    public ArrayList<Device> getDevicesFromDatabase() {
//        java.sql.Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        ArrayList<Device> devices = new ArrayList<Device>();
//
//        try {
//            Model.Connect.Connection connection = new Connection();
//            conn = connection.getJDBC();
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM device"; 
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String id_device = rs.getString("id_device");
//                String name_device = rs.getString("name_device");
//                double price = rs.getDouble("price");
//                String id_type = rs.getString("id_devicetype");
//                
//                // deivce {id_device, name_device, price, lst_devicetyoe, lst_specification}
//                
//                // Device Type Object
//                Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);
//                
//                // lst Specification
//                
////              ListSpecification lst_spec = new ListSpecification(new ListSpecification().ListSpec_MySQL(id_device));
//                
//                ListSpecification lst_spec = new ListSpecification();
//                ArrayList<Specification> specs = new ArrayList<Specification>(lst_spec.getSpec_From_ID_Device(id_device));
//                lst_spec.setListSpec(specs);
//
//                Device d = new Device(id_device, name_device, lst_spec, dt, price);
//                
//                devices.add(d);
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
//        return devices;
//    }
    
  public ArrayList<Device> getDevicesFromDatabase() {
    java.sql.Connection conn = null;
    CallableStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Device> devices = new ArrayList<Device>();

    try {
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();

        // Gọi thủ tục getDevices
        String sql = "{CALL getDevices()}";
        stmt = conn.prepareCall(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String id_device = rs.getString("id_device");
            String name_device = rs.getString("name_device");
            double price = rs.getDouble("price");
            String id_type = rs.getString("id_devicetype");

            // Device Type Object
            Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);

            // List Specification
            ListSpecification lst_spec = new ListSpecification();
            ArrayList<Specification> specs = new ArrayList<>(lst_spec.getSpec_From_ID_Device(id_device));
            lst_spec.setListSpec(specs);

            // Tạo đối tượng Device
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
//    public Device getDevice_MySQL(String id){
//        java.sql.Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Device device = new Device();
//        
//        try {
//            Model.Connect.Connection c = new Connection();
//            conn = c.getJDBC();
//            
//
//            String sql = "SELECT * FROM device where id_device = ?"; // Thay "users" bằng bảng của bạn
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, id);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                String id_device = new String(rs.getString("id_device"));
//                String name_device = new String(rs.getString("name_device"));
//                String id_type = new String(rs.getString("id_devicetype"));
//                double price = rs.getDouble("price");
//                
//                Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);
//                
//                ListSpecification lst_spec = new ListSpecification();
//                ArrayList<Specification> specs = new ArrayList<Specification>(lst_spec.getSpec_From_ID_Device(id_device));
//                lst_spec.setListSpec(specs);
//
//                device = new Device(id_device, name_device, lst_spec, dt, price);
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
//        return device;
//    }

  public Device getDevice_MySQL(String id) {
    java.sql.Connection conn = null;
    CallableStatement stmt = null;
    ResultSet rs = null;
    Device device = new Device();

    try {
        Model.Connect.Connection c = new Connection();
        conn = c.getJDBC();

        // Gọi thủ tục getDeviceById
        String sql = "{CALL getDeviceById(?)}";
        stmt = conn.prepareCall(sql);
        stmt.setString(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String id_device = rs.getString("id_device");
            String name_device = rs.getString("name_device");
            String id_type = rs.getString("id_devicetype");
            double price = rs.getDouble("price");

            // Lấy đối tượng Device_Type dựa trên id_type
            Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);

            // Lấy danh sách Specification dựa trên id_device
            ListSpecification lst_spec = new ListSpecification();
            ArrayList<Specification> specs = new ArrayList<>(lst_spec.getSpec_From_ID_Device(id_device));
            lst_spec.setListSpec(specs);

            // Tạo đối tượng Device
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
//    public ArrayList<Device> addDeviceToList(ArrayList<Device> deviceList, Device device) {
//        ArrayList<Device> newDeviceList = new ArrayList<Device>(deviceList);
//        newDeviceList.add(device);
//        return newDeviceList;
//    }

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
    
    
    
    //Code chức năng
//    public String generateNewDeviceId() {
//    String newId = "";
//    try {
//        Model.Connect.Connection connection = new Connection();
//        conn = connection.getJDBC();
//        Statement stmt = conn.createStatement();
//        
//        // Câu lệnh truy vấn để đếm số thiết bị hiện có
//        String sql = "SELECT COUNT(*) AS count FROM device";
//        ResultSet rs = stmt.executeQuery(sql);
//        
//        if (rs.next()) {
//            int count = rs.getInt("count") + 1;
//            newId = "DEV" + String.format("%03d", count); // DEV + số lượng, định dạng thành ba chữ số
//        }
//        
//        rs.close();
//        stmt.close();
//        conn.close();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return newId;
//}
    
    public String generateNewDeviceId() {
    String newId = "";
    java.sql.Connection conn = null;
    CallableStatement stmt = null;
    ResultSet rs = null;

    try {
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();

        // Gọi hàm generateNewDeviceId từ MySQL
        String sql = "SELECT generateNewDeviceId() AS newId";
        stmt = conn.prepareCall(sql);

        rs = stmt.executeQuery();
        if (rs.next()) {
            newId = rs.getString("newId");
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

    return newId;
}

    
    public void addDevice(String name, String idType, double price) {
    String newId = generateNewDeviceId();
    Device_Type deviceType = new Device_Type().getDeviceType_MySQL(idType); // Giả sử phương thức này lấy Device_Type dựa trên idType
    Device newDevice = new Device(newId, name, deviceType, price);

    try {
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();
        
        // Gọi thủ tục lưu trữ addDevice
        String sql = "{CALL addDevice(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        
        stmt.setString(1, newDevice.getIdDevice());
        stmt.setString(2, newDevice.getNameDevice());
        stmt.setString(3, idType); // Giả sử idType là khóa ngoại trong Device_Type
        stmt.setDouble(4, newDevice.getPrice());
        
        stmt.execute();
        
        stmt.close();
        conn.close();
        
        System.out.println("Thêm thiết bị thành công với ID: " + newId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void deleteDevice(String idDevice) {
    try {
        // Kết nối đến cơ sở dữ liệu
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();
        
        // Gọi thủ tục lưu trữ deleteDevice
        String sql = "{CALL deleteDevice(?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Thiết lập giá trị cho thủ tục
        stmt.setString(1, idDevice);
        
        // Thực thi thủ tục
        stmt.execute();
        
        System.out.println("Xóa thiết bị thành công với ID: " + idDevice);
        
        // Đóng các tài nguyên
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        // Kiểm tra xem lỗi có phải do thiết bị không tồn tại
        if (e.getSQLState().equals("45000")) {
            System.out.println("Không tìm thấy thiết bị với ID: " + idDevice);
        } else {
            e.printStackTrace();
        }
    }
}

    
//public void reorderDeviceIds() {
//    try {
//        Model.Connect.Connection connection = new Connection();
//        conn = connection.getJDBC();
//        
//        // Lấy tất cả các thiết bị hiện tại theo thứ tự tăng dần của id hiện tại
//        String sqlSelect = "SELECT id_device FROM device ORDER BY id_device";
//        Statement stmtSelect = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        ResultSet rs = stmtSelect.executeQuery(sqlSelect);
//        
//        int index = 1;
//        while (rs.next()) {
//            // Cập nhật lại id_device theo định dạng "DEV" + số thứ tự mới
//            String newId = "DEV" + String.format("%03d", index);
//            String oldId = rs.getString("id_device");
//
//            // Thực hiện câu lệnh cập nhật
//            String sqlUpdate = "UPDATE device SET id_device = ? WHERE id_device = ?";
//            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
//            stmtUpdate.setString(1, newId);
//            stmtUpdate.setString(2, oldId);
//            stmtUpdate.executeUpdate();
//            stmtUpdate.close();
//
//            index++;
//        }
//
//        rs.close();
//        stmtSelect.close();
//        conn.close();
//
//        System.out.println("Đã cập nhật lại id_device theo thứ tự.");
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
    
    public void reorderDeviceIds() {
    java.sql.Connection conn = null;
    CallableStatement stmt = null;

    try {
        // Kết nối đến cơ sở dữ liệu
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();

        // Gọi thủ tục reorderDeviceIds từ MySQL
        String sql = "{CALL reorderDeviceIds()}";
        stmt = conn.prepareCall(sql);

        // Thực thi thủ tục
        stmt.execute();

        System.out.println("Đã cập nhật lại id_device theo thứ tự.");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Đóng các tài nguyên
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public void updateDevice(String idDevice, String newName, String newIdType, double newPrice) {
    try {
        // Kết nối đến cơ sở dữ liệu
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();
        
        // Gọi thủ tục lưu trữ updateDevice
        String sql = "{CALL updateDevice(?, ?, ?, ?)}";
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Thiết lập giá trị cho các tham số trong thủ tục
        stmt.setString(1, idDevice);
        stmt.setString(2, newName);
        stmt.setString(3, newIdType);
        stmt.setDouble(4, newPrice);
        
        // Thực thi thủ tục
        stmt.execute();
        
        System.out.println("Cập nhật thiết bị thành công với ID: " + idDevice);
        
        // Đóng các tài nguyên
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        // Kiểm tra xem lỗi có phải do thiết bị không tồn tại
        if (e.getSQLState().equals("45000")) {
            System.out.println("Không tìm thấy thiết bị với ID: " + idDevice);
        } else {
            e.printStackTrace();
        }
    }
}

public Device findDeviceByIdInDatabase(String id) {
    java.sql.Connection conn = null;
    CallableStatement stmt = null;
    ResultSet rs = null;
    Device device = null;

    try {
        Model.Connect.Connection connection = new Connection();
        conn = connection.getJDBC();

        // Gọi thủ tục findDevice
        String sql = "{CALL findDevice(?)}";
        stmt = conn.prepareCall(sql);
        stmt.setString(1, id);

        // Thực thi thủ tục và lấy kết quả
        rs = stmt.executeQuery();

        if (rs.next()) {
            String id_device = rs.getString("id_device");
            String name_device = rs.getString("name_device");
            String id_type = rs.getString("id_devicetype");
            double price = rs.getDouble("price");

            // Lấy đối tượng Device_Type dựa trên id_type
            Device_Type dt = new Device_Type().getDeviceType_MySQL(id_type);

            // Lấy danh sách Specification dựa trên id_device
            ListSpecification lst_spec = new ListSpecification();
            ArrayList<Specification> specs = new ArrayList<>(lst_spec.getSpec_From_ID_Device(id_device));
            lst_spec.setListSpec(specs);

            // Tạo đối tượng Device
            device = new Device(id_device, name_device, lst_spec, dt, price);
        } else {
            System.out.println("Không tìm thấy thiết bị với ID: " + id);
        }
    } catch (SQLException e) {
        // Kiểm tra xem lỗi có phải là do thiết bị không tồn tại
        if (e.getSQLState().equals("45000")) {
            System.out.println("Không tìm thấy thiết bị với ID: " + id);
        } else {
            e.printStackTrace();
        }
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
   

    public static void main(String[] args){
        ListDevice l = new ListDevice();
        ArrayList<Device> lst_device = new ArrayList<Device>(l.getDevicesFromDatabase());
        
        for (Device d : lst_device){
            System.out.println(d.toString()); // done
        }
        
        Device device = new ListDevice().getDevice_MySQL("DEV001");
        System.out.println(device.toString()); // done
    }
   
}