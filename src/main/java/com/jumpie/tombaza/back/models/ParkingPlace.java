package com.jumpie.tombaza.back.models;

public class ParkingPlace extends Model<Integer> {
    private int occupiedSlot;
    private int parkingId;
    private short floor;

    public ParkingPlace() {
    }

    public ParkingPlace(int parkingPlaceId, int occupiedSlot, int parkingId, short floor) {
        setId(parkingPlaceId);
        setOccupiedSlot(occupiedSlot);
        setParkingId(parkingId);
        setFloor(floor);
    }


    public int getOccupiedSlot() {
        return occupiedSlot;
    }

    public void setOccupiedSlot(int occupiedSlot) {
        this.occupiedSlot = occupiedSlot;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public short getFloor() {
        return floor;
    }

    public void setFloor(short floor) {
        this.floor = floor;
    }

    public void print() {
        System.out.println(super.getId() + " " + this.occupiedSlot + " " + this.parkingId + " " + this.floor);
    }
    public String allInString() {
        String str = "ID: "+super.getId() + " OCCUPIEDSLOT: " + this.occupiedSlot + " PARKINGID: " + this.parkingId + " FLOOR: " + this.floor;
        return str;
    }
}
