/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.UserModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

// LocalDate type: {year: int, month: int, day:int}
/**
 *
 * @author vntin
 */
public abstract class Person {
    
    private String id;
    private String fullname;
    private String phone_number;
    protected LocalDate birthDate;

    // Mặc định constructor
    public Person() {
        this.id = "";
        this.fullname = "";
        this.phone_number = "";
        this.birthDate = LocalDate.of(1990, 1, 1);
    }

    // Tham số constructor
    public Person(String id, String fullname, String phone_number, LocalDate birthDate) {
        this.id = id;
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.birthDate = birthDate;
    }

    // Sao chép constructor
    public Person(Person person) {
        this.id = person.id;
        this.fullname = person.fullname;
        this.phone_number = person.phone_number;
        this.birthDate = person.birthDate;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for fullname
    public String getFullname() {
        return fullname;
    }

    // Setter for fullname
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    // Getter for phone_number
    public String getPhone_number() {
        return phone_number;
    }

    // Setter for phone_number
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    // Getter for birthDate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Setter for birthDate
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    /*
    create setter, getter 
    create code 
    */
    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
