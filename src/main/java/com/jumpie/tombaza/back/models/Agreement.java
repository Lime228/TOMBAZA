package com.jumpie.tombaza.back.models;

public class Agreement extends Model<Integer> {

    private int rentPrice;
    private int rentPeriod;
    private String vinNumber;
    private String passportNumber;

    public Agreement() {
    }

    public Agreement(int agreementId, int rentPrice, int rentPeriod, String vinNumber, String passportNumber) {
        setId(agreementId);
        setRentPrice(rentPrice);
        setRentPeriod(rentPeriod);
        setVinNumber(vinNumber);
        setPassportNumber(passportNumber);
    }


    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentPeriod() {
        return rentPeriod;
    }

    public void setRentPeriod(int rentPeriod) {
        this.rentPeriod = rentPeriod;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        if (vinNumber.length() <= 255) {
            this.vinNumber = vinNumber;
        } else error();

    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        if (passportNumber.length() <= 255) {
            this.passportNumber = passportNumber;
        } else error();

    }

    public void print() {
        System.out.println(super.getId() + " " + this.rentPrice + " " + this.rentPeriod + " " + this.vinNumber + " " + this.passportNumber);
    }

    public String allInString() {
        String str ="ID: " + super.getId() + " RENTPRICE: " + this.rentPrice + " RENTPERIOD: " + this.rentPeriod + " VINNUMBER: " + this.vinNumber + " PASSPORT: " + this.passportNumber;
        return str;
    }
}
