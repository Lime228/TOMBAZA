package com.jumpie.tombaza.back.models;

public class Client extends Model<String> {
    //passportNumber - id
    private String phoneNumber;
    private String address;
    private String name;

    public Client() {
    }

    public Client(String passportNumber, String phoneNumber, String address, String name) {
        setId(passportNumber);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setName(name);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() <= 20) {
            this.phoneNumber = phoneNumber;
        } else error();

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() <= 255) {
            this.address = address;
        } else error();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 255) {
            this.name = name;
        } else error();

    }

    public void print() {
        System.out.println(super.getId() + " " + this.address + " " + this.phoneNumber + " " + this.name);
    }
    public String allInString() {
        String str = "ID:  " + super.getId() + " ADDRESS:  " + this.address + " PHONENUMBER:  " + this.phoneNumber + " NAME:  " + this.name;
        return str;
    }
}
