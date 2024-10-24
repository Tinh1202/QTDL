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
public class Person {
    
    private String id;
    private String fullname;
    private String phone_number;
    protected LocalDate birthDate;

    // Mặc định constructor
    public Person() {
        this.id = new String("");
        this.fullname = new String("");
        this.phone_number = new String("");
        this.birthDate = LocalDate.of(1990, 01, 01);
    }

    // Tham số constructor
    public Person(String id, String fullname, String phone_number, LocalDate birthDate) {
        this.id = new String(id);
        this.fullname = new String(fullname);
        this.phone_number = new String(phone_number);
        this.birthDate = birthDate;
    }

    // Sao chép constructor
    public Person(Person person) {
        if (person instanceof Person) {
            Person p = new Person(person);
            this.id = new String(p.id);
            this.fullname = new String(p.fullname);
            this.phone_number = new String(p.phone_number);
            this.birthDate = person.birthDate;
        } else {
            Person p = new Person();
            this.id = new String(p.id);
            this.fullname = new String(p.fullname);
            this.phone_number = new String(p.phone_number);
            this.birthDate = p.birthDate;
        }
    }

    // Getter for id
    public String getId() {
        return new String(this.id);
    }

    // Setter for id
    public void setId(String id) {
        this.id = new String(id);
    }

    // Getter for fullname
    public String getFullname() {
        return new String(this.fullname);
    }

    // Setter for fullname
    public void setFullname(String fullname) {
        this.fullname = new String(fullname);
    }

    // Getter for phone_number
    public String getPhone_number() {
        return new String(this.phone_number);
    }

    // Setter for phone_number
    public void setPhone_number(String phone_number) {
        this.phone_number = new String(phone_number);
    }

    // Getter for birthDate
    public LocalDate getBirthDate() {
        return this.birthDate;
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
