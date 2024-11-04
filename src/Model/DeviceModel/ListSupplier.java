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

 public class ListSupplier {
    private ArrayList<Supplier> listSupplier;
    private int length;

    // Constructor không tham số
    public ListSupplier() {
        this.listSupplier = new ArrayList<>();
        this.length = 0;
    }

    // Constructor có tham số
    public ListSupplier(ArrayList<Supplier> listSupplier) {
        this.listSupplier = new ArrayList<Supplier>(listSupplier);
        this.length = listSupplier.size();
    }

    // Constructor sao chép đối tượng
    public ListSupplier(Object listObj) {
    if (listObj instanceof ListSupplier) {
        ListSupplier listSupplierObj = (ListSupplier) listObj;
        this.listSupplier = new ArrayList<>(listSupplierObj.listSupplier); // Sử dụng ArrayList<Supplier>
        this.length = listSupplierObj.length;
    } else {
        this.listSupplier = new ArrayList<>(); // Khởi tạo mảng trống nếu không phải là ListSupplier
        this.length = 0;
    }
}

    // Getter và Setter cho listSupplier
    public ArrayList<Supplier> getListSupplier() {
        return new ArrayList<Supplier>(this.listSupplier);
    }

    public void setListSupplier(ArrayList<Supplier> listSupplier) {
        this.listSupplier = new ArrayList<Supplier>(this.listSupplier);
        this.length = listSupplier.size();
    }

    public int getLength() {
        return this.length;
    }

    // Lấy danh sách nhà cung cấp từ cơ sở dữ liệu
    public ArrayList<Supplier> getSuppliersFromDatabase() {
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

        try {
            Model.Connect.Connection connection = new Connection();
            conn = connection.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM supplier"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ID_supplier = rs.getString("id_supplier");
                String name_supplier = rs.getString("name_supplier");
                String address_supplier = rs.getString("address_supplier");
                String phone_supplier = rs.getString("phone_supplier");
                String country_id = rs.getString("id_country");
                Country country = new Country().getCountry_MySQL(country_id);

                Supplier supplier = new Supplier(ID_supplier, name_supplier, address_supplier, phone_supplier, country);
                suppliers.add(supplier);
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

        return suppliers;
    }
    
    // lấy theo id supplier
    public Supplier getSupplier_MySQL(String id_supplier){
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Supplier s = null;
        
        Supplier supplier = new Supplier();
        
        try {
            Model.Connect.Connection connection = new Connection();
            conn = connection.getJDBC();

            String sql = "SELECT * FROM Supplier where id_supplier = ?"; 
            
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, id_supplier);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String ID_supplier = rs.getString("id_supplier");
                String name_supplier = rs.getString("name_supplier");
                String address_supplier = rs.getString("address_supplier");
                String phone_supplier = rs.getString("phone_supplier");
                
                String country_id = rs.getString("id_country");
                Country country = new Country().getCountry_MySQL(country_id);

                supplier = new Supplier(ID_supplier, name_supplier, address_supplier, phone_supplier, country);
                
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
        
        return supplier;
    }

    // Hiển thị danh sách nhà cung cấp
    public void displayListSupplier() {
        ArrayList<Supplier> suppliers = getSuppliersFromDatabase();

        for (Supplier supplier : suppliers) {
            System.out.println(supplier.toString());
        }
    }

    // Thêm nhà cung cấp vào danh sách
    public ArrayList<Supplier> addSupplierToList(ArrayList<Supplier> supplierList, Supplier supplier) {
        ArrayList<Supplier> newSupplierList = new ArrayList<Supplier>(supplierList);
        newSupplierList.add(supplier);
        return newSupplierList;
    }

    // Xóa nhà cung cấp ở đầu danh sách
    public ArrayList<Supplier> deleteHeadSupplierFromList(ArrayList<Supplier> supplierList) {
        ArrayList<Supplier> newSupplierList = new ArrayList<Supplier>(supplierList);
        if (!newSupplierList.isEmpty()) {
            newSupplierList.remove(0);
        }
        return newSupplierList;
    }

    // Xóa nhà cung cấp ở cuối danh sách
    public ArrayList<Supplier> deleteTailSupplierFromList(ArrayList<Supplier> supplierList) {
        ArrayList<Supplier> newSupplierList = new ArrayList<Supplier>(supplierList);
        if (!newSupplierList.isEmpty()) {
            newSupplierList.remove(newSupplierList.size() - 1);
        }
        return newSupplierList;
    }

    // Xóa nhà cung cấp theo ID
    public ArrayList<Supplier> deleteSupplierById(ArrayList<Supplier> supplierList, String ID) {
        ArrayList<Supplier> newSupplierList = new ArrayList<Supplier>(supplierList);

        Iterator<Supplier> iterator = newSupplierList.iterator();
        while (iterator.hasNext()) {
            Supplier supplier = iterator.next();
            if (supplier.getIDSupplier().equalsIgnoreCase(ID)) {
                iterator.remove();
            }
        }

        return newSupplierList;
    }

    // Xóa nhà cung cấp theo tên
    public ArrayList<Supplier> deleteSupplierByName(ArrayList<Supplier> supplierList, String name) {
        ArrayList<Supplier> newSupplierList = new ArrayList<Supplier>(supplierList);

        Iterator<Supplier> iterator = newSupplierList.iterator();
        while (iterator.hasNext()) {
            Supplier supplier = iterator.next();
            if (supplier.getNameSupplier().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }

        return newSupplierList;
    }
    
    public static void main(String[] args){
        Supplier s = new ListSupplier().getSupplier_MySQL("SUP001");
        System.out.println(s.toString()); // done
        
        ArrayList<Supplier> lst_supplier = new ArrayList<Supplier>(new ListSupplier().getSuppliersFromDatabase());
        
        for (Supplier su : lst_supplier){  
            System.out.println(su.toString()); // done
        }
    }
}
