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

public class ListManuf {
    private ArrayList<Manufacturer> listManufacturer;
    private int length;

    // Constructor không tham số
    public ListManuf() {
        this.listManufacturer = new ArrayList<>();
        this.length = 0;
    }

    // Constructor có tham số
    public ListManuf(ArrayList<Manufacturer> listManufacturer) {
        this.listManufacturer = new ArrayList<Manufacturer>(listManufacturer);
        this.length = listManufacturer.size();
    }

    // Constructor sao chép đối tượng
    public ListManuf(Object listObj) {
        if (listObj instanceof ListManuf) {
            ListManuf listManuf = new ListManuf (listObj);
            this.listManufacturer = new ArrayList<Manufacturer>(listManuf.listManufacturer);
            this.length = listManuf.length;
        } else {
            ListManuf listManuf = new ListManuf ();
            this.listManufacturer = new ArrayList<Manufacturer>(listManuf.listManufacturer);
            this.length = 0;
        }
    }

    // Getter và Setter cho listManufacturer
    public ArrayList<Manufacturer> getListManufacturer() {
        return new ArrayList<Manufacturer>(this.listManufacturer);
    }

    public void setListManufacturer(ArrayList<Manufacturer> listManufacturer) {
        this.listManufacturer = new ArrayList<Manufacturer>(listManufacturer);
        this.length = listManufacturer.size();
    }

    public int getLength() {
        return this.length;
    }

    // Lấy danh sách nhà sản xuất từ cơ sở dữ liệu
    public ArrayList<Manufacturer> getManufacturersFromDatabase() {
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        try {
            Model.Connect.Connection connection = new Connection();
            conn = connection.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Manufacturer"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id_manuf = rs.getString("id_manuf");
                String name_manuf = rs.getString("name_manuf");
                String country_id = rs.getString("country_id");
                Country country = new Country(country_id); 

                Manufacturer manufacturer = new Manufacturer(id_manuf, name_manuf, country);
                manufacturers.add(manufacturer);
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

        return new ArrayList<>(manufacturers);
    }

    // Hiển thị danh sách nhà sản xuất
    public void displayListManufacturer() {
        ArrayList<Manufacturer> manufacturers = getManufacturersFromDatabase();

        for (Manufacturer manufacturer : manufacturers) {
            System.out.println(manufacturer.toString());
        }
    }

    // Thêm nhà sản xuất vào danh sách
    public ArrayList<Manufacturer> addManufacturerToList(ArrayList<Manufacturer> manufacturerList, Manufacturer manufacturer) {
        ArrayList<Manufacturer> newManufacturerList = new ArrayList<Manufacturer>(manufacturerList);
        newManufacturerList.add(manufacturer);
        return newManufacturerList;
    }

    // Xóa nhà sản xuất ở đầu danh sách
    public ArrayList<Manufacturer> deleteHeadManufacturerFromList(ArrayList<Manufacturer> manufacturerList) {
        ArrayList<Manufacturer> newManufacturerList = new ArrayList<Manufacturer>(manufacturerList);
        if (!newManufacturerList.isEmpty()) {
            newManufacturerList.remove(0);
        }
        return newManufacturerList;
    }

    // Xóa nhà sản xuất ở cuối danh sách
    public ArrayList<Manufacturer> deleteTailManufacturerFromList(ArrayList<Manufacturer> manufacturerList) {
        ArrayList<Manufacturer> newManufacturerList = new ArrayList<Manufacturer>(manufacturerList);
        if (!newManufacturerList.isEmpty()) {
            newManufacturerList.remove(newManufacturerList.size() - 1);
        }
        return newManufacturerList;
    }

    // Xóa nhà sản xuất theo id
    public ArrayList<Manufacturer> deleteManufacturerById(ArrayList<Manufacturer> manufacturerList, String id) {
        ArrayList<Manufacturer> newManufacturerList = new ArrayList<Manufacturer>(manufacturerList);

        Iterator<Manufacturer> iterator = newManufacturerList.iterator();
        while (iterator.hasNext()) {
            Manufacturer manufacturer = iterator.next();
            if (manufacturer.getIdManuf().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }

        return newManufacturerList;
    }

    // Xóa nhà sản xuất theo tên
    public ArrayList<Manufacturer> deleteManufacturerByName(ArrayList<Manufacturer> manufacturerList, String name) {
        ArrayList<Manufacturer> newManufacturerList = new ArrayList<Manufacturer>(manufacturerList);

        Iterator<Manufacturer> iterator = newManufacturerList.iterator();
        while (iterator.hasNext()) {
            Manufacturer manufacturer = iterator.next();
            if (manufacturer.getNameManuf().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }

        return newManufacturerList;
    }
}