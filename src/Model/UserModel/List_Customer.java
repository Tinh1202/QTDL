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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import Model.Connect.Connection;


public class List_Customer {
    private ArrayList<Customer> ListCustomer;
    private int length;

    // Default constructor
    public List_Customer() {
        this.ListCustomer = new ArrayList<>();
        this.length = 0;
    }

    // Constructor with an existing list of customers
    public List_Customer(ArrayList<Customer> listCustomer) {
        this.ListCustomer = new ArrayList<>(listCustomer);
        this.length = listCustomer.size();
    }

    // Copy constructor
    public List_Customer(Object listObj) {
        if (listObj instanceof List_Customer) {
            List_Customer listCustomerObj = (List_Customer) listObj;
            this.ListCustomer = new ArrayList<>(listCustomerObj.ListCustomer);
            this.length = listCustomerObj.length;
        } else {
            this.ListCustomer = new ArrayList<>();
            this.length = 0;
        }
    }

    // Getter for list of customers
    public ArrayList<Customer> getListCustomer() {
        return new ArrayList<>(this.ListCustomer);
    }

    // Setter for list of customers
    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.ListCustomer = new ArrayList<>(listCustomer);
        this.length = listCustomer.size();
    }

    // Method to get length of the list
    public int getLength() {
        return this.length;
    }

    // Load the customer list from the MySQL database
    public ArrayList<Customer> ListCustomer_MySQL() {
        ArrayList<Customer> listCustomer = new ArrayList<>();
        java.sql.Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Customer;"; // Replace with your actual table name
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id_customer");
                String fullname = rs.getString("fullname_customer");
                String phoneNumber = rs.getString("phone_customer");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                String address = rs.getString("address");
                Customer customer = new Customer(id, fullname, phoneNumber, birthDate, address);
                listCustomer.add(customer);
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

        return listCustomer;
    }

    
    public Customer getCustomer_MySQL(String id_customer){
        java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = new Customer();

    try {
        Model.Connect.Connection c = new Connection() {};
        conn = c.getJDBC();

        String sql = "SELECT * FROM customer WHERE id_customer = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id_customer);

        rs = pstmt.executeQuery();
        if (rs.next()) {
            
            String id_customer_n = rs.getString("id_customer");
            String fullname_customer = rs.getString("fullname_customer");
            String phone_customer = rs.getString("phone_customer");
            
            LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
            String address = rs.getString("address");
            customer = new Customer(id_customer_n, fullname_customer, phone_customer, birthDate, address);
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

    return new Customer(customer);
    }
    
    // Display the list of customers
    public void DisplayListCustomer() {
        ArrayList<Customer> listCustomer = ListCustomer_MySQL();
        for (Customer customer : listCustomer) {
            System.out.println(customer.toString());
        }
    }

    // Delete the first customer in the list
    public ArrayList<Customer> DeleteHeadCustomer() {
        ArrayList<Customer> newList = new ArrayList<>(this.ListCustomer);
        if (!newList.isEmpty()) {
            newList.remove(0);
        }
        return newList;
    }

    // Delete the last customer in the list
    public ArrayList<Customer> DeleteTailCustomer() {
        ArrayList<Customer> newList = new ArrayList<>(this.ListCustomer);
        if (!newList.isEmpty()) {
            newList.remove(newList.size() - 1);
        }
        return newList;
    }

    // Delete a customer by ID
    public ArrayList<Customer> DeleteCustomerById(String id) {
        ArrayList<Customer> newList = new ArrayList<>(this.ListCustomer);
        Iterator<Customer> iterator = newList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getId().equalsIgnoreCase(id)) {
                iterator.remove();
            }
        }
        return newList;
    }

    // Delete a customer by name
    public ArrayList<Customer> DeleteCustomerByName(String name) {
        ArrayList<Customer> newList = new ArrayList<>(this.ListCustomer);
        Iterator<Customer> iterator = newList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getFullname().equalsIgnoreCase(name)) {
                iterator.remove();
            }
        }
        return newList;
    }

    // Add a new customer to the list
    public ArrayList<Customer> AddCustomer(Customer customer) {
        ArrayList<Customer> newList = new ArrayList<>(this.ListCustomer);
        newList.add(new Customer(customer));
        return newList;
    }
    
    public static void main(String[] args){
        Customer c = new List_Customer().getCustomer_MySQL("C001"); // done
        System.out.println(c.toString());
        
        
        ArrayList<Customer> lst_customer = new ArrayList<Customer>(new List_Customer().ListCustomer_MySQL());
        for (Customer cu : lst_customer){
            System.out.println(cu.toString()); // done
        }
    }
}


