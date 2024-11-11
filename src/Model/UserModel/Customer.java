/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.UserModel;
import java.time.LocalDate;
/**
 *
 * @author vntin
 */
import java.time.LocalDate;

public class Customer extends Person {
    private String address;

    // Mặc định constructor
    public Customer() {
        super();
        this.address = new String("");
    }

    // Tham số constructor
    public Customer(String id, String fullname, String phone_number, LocalDate birthdate, String address) {
        super(id, fullname, phone_number, birthdate);
        this.address = new String(address);
    }

    // Sao chép constructor
    public Customer(Customer customer) {
        super(customer.getId(), customer.getFullname(), customer.getPhone_number(), customer.getBirthDate());
        this.address = new String(customer.getAddress());
    }

    // Getter for address
    public String getAddress() {
        return new String(this.address);
    }

    // Setter for address
    public void setAddress(String address) {
        this.address = new String(address);
    }
    /*
    code *
    */

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", birthdate=" + getBirthDate() +
                ", address='" + this.address + '\'' +
                '}';
    }
}
