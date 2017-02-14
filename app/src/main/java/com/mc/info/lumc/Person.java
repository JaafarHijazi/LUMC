package com.mc.info.lumc;

import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Person  implements Serializable{
    private String firstName,
            lastName,
            phone,
            email,
            id,
            bloodGroup,
            height,
            weight;
    private Address address;

    public Person(){
        this.id="";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.email = "";
        this.address = new Address();
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Person(String id, String firstName, String lastName, String phone, String email, Address address) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
            this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;

    }
}
