package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.ParkingPlace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceRepository implements Repository<ParkingPlace> {
    private static ParkingPlaceRepository instance;

    public static final String PARKINGP_TABLE = "parking_place";
    public static final String PARKINGP_ID = "parking_palce_id";
    public static final String PARKINGP_SLOT = "occupied_slot";
    public static final String PARKINGP_PARKING_ID = "parking_id";
    public static final String PARKINGP_FLOOR = "floor";

    private ParkingPlaceRepository() {
    }

    @Override
    public ParkingPlace getByID(ParkingPlace get) {
        int realID = get.getId();
        String ins = "SELECT * FROM " + PARKINGP_TABLE + " WHERE " + PARKINGP_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {

            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            ParkingPlace pp = new ParkingPlace();
            pp.setId(res.getInt(1));
            pp.setOccupiedSlot(res.getInt(2));
            pp.setParkingId(res.getInt(3));
            pp.setFloor(Short.parseShort(res.getString(4)));

            return pp;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ParkingPlace> getAll() {
        String ins = "SELECT * FROM " + PARKINGP_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<ParkingPlace> list = new ArrayList<>();
            while (res.next()) {
                ParkingPlace pp = new ParkingPlace();
                pp.setId(res.getInt(1));
                pp.setOccupiedSlot(res.getInt(2));
                pp.setParkingId(res.getInt(3));
                pp.setFloor(Short.parseShort(res.getString(4)));
                list.add(pp);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ParkingPlace create(ParkingPlace add) throws ClassNotFoundException {
        ParkingPlace pp = add;
        String ins = "INSERT INTO " + PARKINGP_TABLE + "(" + PARKINGP_ID + "," + PARKINGP_SLOT + "," + PARKINGP_PARKING_ID + "," + PARKINGP_FLOOR + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(pp.getId()));
            prSt.setString(2, String.valueOf(pp.getOccupiedSlot()));
            prSt.setString(3, String.valueOf(pp.getParkingId()));
            prSt.setString(4, String.valueOf(pp.getFloor()));
            prSt.executeUpdate();
            return pp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ParkingPlace createWithoutID(ParkingPlace add) throws ClassNotFoundException {
        ParkingPlace pp = add;
        String ins = "INSERT INTO " + PARKINGP_TABLE + "(" + PARKINGP_SLOT + "," + PARKINGP_PARKING_ID + "," + PARKINGP_FLOOR + ")" + "VALUES(?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(pp.getOccupiedSlot()));
            prSt.setString(2, String.valueOf(pp.getParkingId()));
            prSt.setString(3, String.valueOf(pp.getFloor()));
            prSt.executeUpdate();
            return pp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ParkingPlace update(ParkingPlace upd) throws ClassNotFoundException {
        ParkingPlace pp = upd;
        if (getByID(pp) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + PARKINGP_TABLE + " SET " + PARKINGP_ID + " = ?, " + PARKINGP_SLOT + " =?, " + PARKINGP_PARKING_ID + " =?, " + PARKINGP_FLOOR + " =? WHERE " + PARKINGP_ID + "=" + pp.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, String.valueOf(pp.getId()));
                prSt.setString(2, String.valueOf(pp.getOccupiedSlot()));
                prSt.setString(3, String.valueOf(pp.getParkingId()));
                prSt.setString(4, String.valueOf(pp.getFloor()));
                prSt.executeUpdate();
                return pp;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else create(upd);
        return null;
    }

    @Override
    public boolean delete(ParkingPlace del) {
        int realID = del.getId();
        if (getByID(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + PARKINGP_TABLE + " WHERE " + PARKINGP_ID + "=?";
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

    public static synchronized ParkingPlaceRepository getInstance() {
        if (instance == null) instance = new ParkingPlaceRepository();
        return instance;
    }
}
