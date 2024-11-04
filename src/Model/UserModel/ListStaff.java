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
import java.sql.*;
import Model.Connect.Connection;


public class ListStaff {
    ArrayList<Staff> ListStaff;
    int length;

    // Default constructor
    public ListStaff() {
        this.ListStaff = new ArrayList<>();
        this.length = 0;
    }

    // Constructor with an existing list of staff
    public ListStaff(ArrayList<Staff> listStaff) {
        this.ListStaff = new ArrayList<>(listStaff);
        this.length = listStaff.size();
    }

    // Copy constructor
    public ListStaff(Object listObj) {
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
                String id = rs.getString("id_staff");
                String fullname = rs.getString("fullname_staff");
                String phoneNumber = rs.getString("phone_staff");
                LocalDate birthDate = rs.getDate("birthDate_staff").toLocalDate();
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

    
    public Staff Staff_MySQL(String id_staff){
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Staff st = new Staff();

    try {
        Model.Connect.Connection c = new Connection() {};
        conn = c.getJDBC();

        String sql = "SELECT * FROM staff WHERE id_staff = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id_staff);

        rs = pstmt.executeQuery();
        if (rs.next()) {
            
            String id_staff_ = rs.getString("id_staff");
            String fullname_staff = rs.getString("fullname_staff");
            String phone_staff = rs.getString("phone_staff");
            
            LocalDate staff_birthdate = rs.getDate("birthDate_staff").toLocalDate();
            String position = rs.getString("position");
            
            st = new Staff(id_staff, fullname_staff, phone_staff, staff_birthdate, position);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return new Staff(st);
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
    
    
   
    
    public static void main(String[] args){
        Staff st = new Staff(new ListStaff().Staff_MySQL("S001"));
        System.out.println(st.toString());  // done
        
        ArrayList<Staff> lst_staff = new ListStaff().ListStaff_MySQL();
        
        for(Staff s : lst_staff){
            System.out.println(s.toString()); // done
        }
    }
 }

