package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<Car> {
    private static CarRepository instance;
    public static final String CAR_TABLE = "car";

    public static final String CAR_VIN_NUMBER = "vin_number";
    public static final String CAR_COLOR = "color";
    public static final String CAR_BRAND = "brand";
    public static final String CAR_MODEL_NAME = "model_name";
    public static final String CAR_RELEASE_YEAR = "release_year";
    public static final String CAR_PARKING_PLACE = "parking_place_id";
    public static final String CAR_NUMBER = "number";

    @Override
    public Car getByID(Car get) {
        String realID = get.getId();
        String ins = "SELECT * FROM " + CAR_TABLE + " WHERE " + CAR_VIN_NUMBER + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            prSt.setString(1, realID);
            ResultSet res = prSt.executeQuery();
            res.next();
            Car car = new Car();
            car.setId(res.getString(1));
            car.setColor(res.getString(2));
            car.setBrand(res.getString(3));
            car.setModelName(res.getString(4));
            car.setReleaseYear(res.getString(5));
            car.setParkingPlaceId(res.getInt(6));
            car.setNumber(res.getString(7));


            return car;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Car> getAll() {
        String ins = "SELECT * FROM " + CAR_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            ResultSet res = prSt.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (res.next()) {
                Car car = new Car();
                car.setId(res.getString(1));
                car.setColor(res.getString(2));
                car.setBrand(res.getString(3));
                car.setModelName(res.getString(4));
                car.setReleaseYear(res.getString(5));
                car.setParkingPlaceId(res.getInt(6));
                car.setNumber(res.getString(7));
                cars.add(car);
            }

            return cars;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Car create(Car add) throws ClassNotFoundException {
        Car car = add;
        String ins = "INSERT INTO " + CAR_TABLE + "(" + CAR_VIN_NUMBER + "," + CAR_COLOR + "," + CAR_BRAND + "," + CAR_MODEL_NAME + "," + CAR_RELEASE_YEAR + "," + CAR_PARKING_PLACE + "," + CAR_NUMBER +")" + "VALUES(?,?,?,?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            prSt.setString(1, car.getId());
            prSt.setString(2, car.getColor());
            prSt.setString(3, car.getBrand());
            prSt.setString(4, car.getModelName());
            prSt.setString(5, car.getReleaseYear());
            prSt.setString(6, String.valueOf(car.getParkingPlaceId()));
            prSt.setString(7, car.getNumber());
            prSt.executeUpdate();
            return car;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Car update(Car upd) throws ClassNotFoundException {
        Car car = upd;
        if (getByID(car)!=null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + CAR_TABLE + " SET " + CAR_VIN_NUMBER + " = ?, " + CAR_COLOR + " =?, " + CAR_BRAND + " =?, " + CAR_MODEL_NAME + " =?, " + CAR_RELEASE_YEAR + " =?, " + CAR_PARKING_PLACE + " =?, " + CAR_NUMBER + " =? WHERE " + CAR_VIN_NUMBER + "=" + car.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
                prSt.setString(1, car.getId());
                prSt.setString(2, car.getColor());
                prSt.setString(3, car.getBrand());
                prSt.setString(4, car.getModelName());
                prSt.setString(5, String.valueOf(car.getReleaseYear()));
                prSt.setString(6, String.valueOf(car.getParkingPlaceId()));
                prSt.setString(7, car.getNumber());
                prSt.executeUpdate();
                return car;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    @Override
    public boolean delete(Car del) {
        String realID = del.getId();
        if (getByID(del)!=null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + CAR_TABLE + " WHERE " + CAR_VIN_NUMBER + "=?";
            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
                prSt.setString(1, realID);
                prSt.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("Nothing to delete.");
        return false;
    }

    public static synchronized CarRepository getInstance() {
        if (instance == null) instance = new CarRepository();
        return instance;
    }
}
