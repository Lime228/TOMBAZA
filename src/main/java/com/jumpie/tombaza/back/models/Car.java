package com.jumpie.tombaza.back.models;

public class Car extends Model<String> {
    //vinNumber - id
    private String color;
    private String brand;
    private String modelName;
    private String releaseYear;
    private int parkingPlaceId;
    private String number;

    public Car() {
    }

    public Car(String vinNumber, String color, String modelName, String brand, String releaseYear, int parkingPlaceId, String number) {
        setId(vinNumber);
        setColor(color);
        setModelName(modelName);
        setBrand(brand);
        setReleaseYear(releaseYear);
        setParkingPlaceId(parkingPlaceId);
        setNumber(number);
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color.length() <= 45) {
            this.color = color;
        } else error();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand.length() <= 45) {
            this.brand = brand;
        } else error();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (modelName.length() <= 45) {
            this.modelName = modelName;
        } else error();
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getParkingPlaceId() {
        return parkingPlaceId;
    }

    public void setParkingPlaceId(int parkingPlaceId) {
        this.parkingPlaceId = parkingPlaceId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number.length() <= 10) {
            this.number = number;
        } else error();
    }

    public void print() {
        System.out.println(super.getId() + " " + this.color + " " + this.brand + " " + this.modelName + " " + this.releaseYear + " " + this.parkingPlaceId + " " + this.number);
    }
    public String allInString(){
        String str = "ID:  "+ super.getId() + " COLOR:  " + this.color + " BRAND:  " + this.brand + " MODELNAME:  " + this.modelName + " RELEASEYEAR:  " + this.releaseYear + " PARKINGPLACEID:  " + this.parkingPlaceId + " NUMBER:  " + this.number;
        return str;
    }
}
