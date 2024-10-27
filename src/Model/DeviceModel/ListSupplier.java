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
            ListSupplier listSupplierObj = new ListSupplier (listObj);
            this.listSupplier = new ArrayList<Supplier>(listSupplierObj.listSupplier);
            this.length = listSupplierObj.length;
        } else {
            ListSupplier listSupplierObj = new ArrayList <ListSupplier> (listObj);
            this.listSupplier = new ArrayList<ListSupplier>(listSupplierObj.listSupplier);
            this.length = listSupplierObj.length;
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

            String sql = "SELECT * FROM Supplier"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String ID_supplier = rs.getString("ID_supplier");
                String name_supplier = rs.getString("name_supplier");
                String address_supplier = rs.getString("address_supplier");
                String phone_supplier = rs.getString("phone_supplier");
                String country_id = rs.getString("country_id");
                Country country = new Country(country_id);

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

        return new ArrayList<Supplier>(suppliers);
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
}
