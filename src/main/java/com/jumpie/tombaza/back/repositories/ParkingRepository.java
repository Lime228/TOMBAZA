package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Parking;
import com.jumpie.tombaza.back.models.ParkingPlace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingRepository implements Repository<Parking> {
    private static ParkingRepository instance;
    public static final String PARKING_TABLE = "parking";

    public static final String PARKING_ID = "parking_id";
    public static final String PARKING_MAX_CAPACITY = "max_capacity";
    public static final String PARKING_ADDRESS = "parking_address";

    private ParkingRepository() {}

    @Override
    public Parking getByID(Parking get) {
        int realID = get.getId();
        String ins = "SELECT * FROM " + PARKING_TABLE + " WHERE " + PARKING_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {

            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Parking p = new Parking();
            p.setId(res.getInt(1));
            p.setMaxCapacity(res.getInt(2));
            p.setParkingAddress(res.getString(3));
            return p;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Parking> getAll() {
        String ins = "SELECT * FROM " + PARKING_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {

            ResultSet res = prSt.executeQuery();
            List<Parking> parkings = new ArrayList<>();
            while (res.next()) {
                Parking p = new Parking();
                p.setId(res.getInt(1));
                p.setMaxCapacity(res.getInt(2));
                p.setParkingAddress(res.getString(3));
                parkings.add(p);
            }
            return parkings;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Parking create(Parking add) throws ClassNotFoundException {
        Parking p = add;
        String ins = "INSERT INTO " + PARKING_TABLE + "(" + PARKING_ID + "," + PARKING_MAX_CAPACITY + "," + PARKING_ADDRESS + ")" + "VALUES(?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(p.getId()));
            prSt.setString(2, String.valueOf(p.getMaxCapacity()));
            prSt.setString(3, p.getParkingAddress());
            prSt.executeUpdate();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parking createWithoutID(Parking add) throws ClassNotFoundException {
        Parking p = add;
        String ins = "INSERT INTO " + PARKING_TABLE + "("  + PARKING_MAX_CAPACITY + "," + PARKING_ADDRESS + ")" + "VALUES(?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(p.getMaxCapacity()));
            prSt.setString(2, p.getParkingAddress());
            prSt.executeUpdate();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Parking update(Parking upd) throws ClassNotFoundException {
        Parking p = upd;
        if (getByID(p) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + PARKING_TABLE + " SET " + PARKING_ID + " = ?, " + PARKING_MAX_CAPACITY + " =?, " + PARKING_ADDRESS + " =? WHERE " + PARKING_ID + "=" + p.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(p.getId()));
                prSt.setString(2, String.valueOf(p.getMaxCapacity()));
                prSt.setString(3, p.getParkingAddress());
                prSt.executeUpdate();
                return p;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else create(upd);
        return null;
    }

    @Override
    public boolean delete(Parking del) {
        int realID = del.getId();
        if (getByID(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + PARKING_TABLE + " WHERE " + PARKING_ID + "=?";
            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(realID));
                prSt.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("Nothing to delete.");
        return false;
    }

    public static synchronized ParkingRepository getInstance() {
        if (instance == null) instance = new ParkingRepository();
        return instance;
    }
}
