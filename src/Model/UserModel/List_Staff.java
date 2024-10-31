/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.UserModel;

/**
 *
 * @author vntin
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class List_Staff {
    private ArrayList<Staff> ListStaff;
    private int length;

    // Default constructor
    public List_Staff() {
        this.ListStaff = new ArrayList<>();
        this.length = 0;
    }

    // Constructor with an existing list of staff
    public List_Staff(ArrayList<Staff> listStaff) {
        this.ListStaff = new ArrayList<>(listStaff);
        this.length = listStaff.size();
    }

    // Copy constructor
    public List_Staff(Object listObj) {
        if (listObj instanceof ListStaff) {
            ListStaff listStaffObj = (ListStaff) listObj;
            this.ListStaff = new ArrayList<>(listStaffObj.ListStaff);
            this.length = listStaffObj.length;
        } else {
            this.ListStaff = new ArrayList<>();
            this.length = 0;
        }
    }

    // Getter for list of staff
    public ArrayList<Staff> getListStaff() {
        return new ArrayList<>(this.ListStaff);
    }

    // Setter for list of staff
    public void setListStaff(ArrayList<Staff> listStaff) {
        this.ListStaff = new ArrayList<>(listStaff);
        this.length = listStaff.size();
    }

    // Method to get length of the list
    public int getLength() {
        return this.length;
    }

    // Load the staff list from the MySQL database
    public ArrayList<Staff> ListStaff_MySQL() {
        ArrayList<Staff> listStaff = new ArrayList<>();
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Staff;"; // Replace with your actual table name
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String fullname = rs.getString("fullname");
                String phoneNumber = rs.getString("phone_number");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                String position = rs.getString("position");
                Staff staff = new Staff(id, fullname, phoneNumber, birthDate, position);
                listStaff.add(staff);
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

        return listStaff;
    }

    // Display the list of staff
    public void DisplayListStaff() {
        ArrayList<Staff> listStaff = ListStaff_MySQL();
        for (Staff staff : listStaff) {
            System.out.println(staff.toString());
        }
    }

    // Delete the first staff in the list
    public ArrayList<Staff> DeleteHeadStaff() {
        ArrayList<Staff> newList = new ArrayList<>(this.ListStaff);
        if (!newList.isEmpty()) {
            newList.remove(0);
        }
        return newList;
    }

    // Delete the last staff in the list
    public ArrayList<Staff> DeleteTailStaff() {
        ArrayList<Staff> newList = new ArrayList<>(this.ListStaff);
        if (!newList.isEmpty()) {
            newList.remove(newList.size() - 1);
        }
        return newList;
    }

    // Delete a staff by ID
    public ArrayList<Staff> DeleteStaffById(String id) {
        ArrayList<Staff> newList = new ArrayList<>(this.ListStaff);
        Iterator<Staff> iterator = newList.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getId().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return newList;
    }

    // Delete a staff by name
    public ArrayList<Staff> DeleteStaffByName(String name) {
        ArrayList<Staff> newList = new ArrayList<>(this.ListStaff);
        Iterator<Staff> iterator = newList.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (staff.getFullname().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }
        return newList;
    }

    // Add a new staff to the list
    public ArrayList<Staff> AddStaff(Staff staff) {
        ArrayList<Staff> newList = new ArrayList<>(this.ListStaff);
        newList.add(new Staff(staff));
        return newList;
    }
}

