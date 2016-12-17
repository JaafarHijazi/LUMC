package com.mc.info.lumc;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class Person {
    private int id;
    private String firstName,
            lastName,
            phone,
            email;
    private Address address;

    public Person(){
        this.id=-1;
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.email = "";
        this.address = new Address();
    }

    public Person(int id, String firstName, String lastName, String phone, String email, Address address) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
