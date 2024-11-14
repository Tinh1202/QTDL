package Model.UserModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vntin
 */
import Model.UserModel.Person;
import java.util.ArrayList;
import java.time.*;
public class Staff extends Person {
    private User_Account account;
    private String position;

    // Mặc định constructor
    public Staff() {
        super();
        this.account = new User_Account();
        this.position = new String("");
    }

    // Tham số constructor
    public Staff(String id, String fullname, String phone_number, LocalDate birthDate, String position, User_Account account) {
        super(id, fullname, phone_number, birthDate);
        this.account = account;
        this.position = position;
    }

    // Sao chép constructor
    public Staff(Staff s) {
        super(s.getId(), s.getFullname(), s.getPhone_number(), s.getBirthDate());
        this.account = s.account;
        this.position = s.position;
    }
    
    /*
    create getter, setter position
    */
    
    // Getter for position
    public void setAccount(User_Account account){
        this.account = account;
    }
    
    public User_Account getAccount(){
        return this.account;
    }
    
    
    public String getPosition() {
        return new String(this.position);
    }

    // Setter for position
    public void setPosition(String position) {
        this.position = new String(position);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Staff{" +
                "id='" + getId() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", position='" + position + '\'' +
                '}';
    }
}

