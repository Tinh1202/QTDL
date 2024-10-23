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

public class Staff extends Person {
    private String position;

    // Mặc định constructor
    public Staff() {
        super();
        this.position = "";
    }

    // Tham số constructor
    public Staff(String id, String fullname, String phone_number, LocalDate birthDate, String position) {
        super(id, fullname, phone_number, birthDate);
        this.position = position;
    }

    // Sao chép constructor
    public Staff(Staff s) {
        super(s.getId(), s.getFullname(), s.getPhone_number(), s.getBirthDate());
        this.position = s.getPosition();
    }
    
    /*
    create getter, setter position
    */
    
    // Getter for position
   
    public String getPosition() {
        return position;
    }

    // Setter for position
    public void setPosition(String position) {
        this.position = position;
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

