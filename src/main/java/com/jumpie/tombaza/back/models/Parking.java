package com.jumpie.tombaza.back.models;

public class Parking extends Model<Integer> {
    private int maxCapacity;
    private String parkingAddress;

    public Parking() {
    }

    public Parking(int parkingId, int maxCapacity, String parkingAddress) {
        setId(parkingId);
        setMaxCapacity(maxCapacity);
        setParkingAddress(parkingAddress);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        if (parkingAddress.length() <= 255) {
            this.parkingAddress = parkingAddress;
        } else error();
    }

    public void print() {
        System.out.println(super.getId() + " " + this.maxCapacity + " " + this.parkingAddress);
    }

    public String allInString() {
        String str = "ID: " + super.getId() + " CAPACITY " + this.maxCapacity + " ADDRESS: " + this.parkingAddress;
        return str;
    }
}
