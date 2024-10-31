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
                String id = rs.getString("id");
                String fullname = rs.getString("fullname");
                String phoneNumber = rs.getString("phone_number");
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
}


