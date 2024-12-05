package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Agreement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgreementRepository implements Repository<Agreement> {
    private static AgreementRepository instance;
    private static final String AGREEMENT_TABLE = "agreement";

    private static final String AGREEMENT_ID = "agreement_id";
    private static final String AGREEMENT_RENT_PRICE = "rent_price";
    private static final String AGREEMENT_RENT_PERIOD = "rent_period";
    private static final String AGREEMENT_VIN_NUMBER = "vin_number";
    private static final String AGREEMENT_PASSPORT_NUMBER = "passport_number";

    private AgreementRepository() {}

    @Override
    public Agreement getByID(Agreement get) {
        int realID = get.getId();
        String ins = "SELECT * FROM " + AGREEMENT_TABLE + " WHERE " + AGREEMENT_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Agreement agreement = new Agreement();
            agreement.setId(res.getInt(1));
            agreement.setRentPrice(res.getInt(2));
            agreement.setRentPeriod(res.getInt(3));
            agreement.setPassportNumber(res.getString(4));
            agreement.setVinNumber(res.getString(5));

            return agreement;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Agreement> getByPassport(Agreement get) {
        String passportNumber = get.getPassportNumber();
        String ins = "SELECT * FROM " + AGREEMENT_TABLE + " WHERE " + AGREEMENT_PASSPORT_NUMBER + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, passportNumber);
            ResultSet res = prSt.executeQuery();
            List<Agreement> agreements = new ArrayList<>();
            while (res.next()) {
                Agreement agreement = new Agreement();
                agreement.setId(res.getInt(1));
                agreement.setRentPrice(res.getInt(2));
                agreement.setRentPeriod(res.getInt(3));
                agreement.setPassportNumber(res.getString(4));
                agreement.setVinNumber(res.getString(5));
                agreements.add(agreement);
            }
            return agreements;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Agreement> getByCar(Agreement get) {
        String vinNumber = get.getVinNumber();
        String ins = "SELECT * FROM " + AGREEMENT_TABLE + " WHERE " + AGREEMENT_VIN_NUMBER + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, vinNumber);
            ResultSet res = prSt.executeQuery();
            List<Agreement> agreements = new ArrayList<>();
            while (res.next()) {
                Agreement agreement = new Agreement();
                agreement.setId(res.getInt(1));
                agreement.setRentPrice(res.getInt(2));
                agreement.setRentPeriod(res.getInt(3));
                agreement.setPassportNumber(res.getString(4));
                agreement.setVinNumber(res.getString(5));
                agreements.add(agreement);
            }
            return agreements;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Agreement> getAll() {

        String ins = "SELECT * FROM " + AGREEMENT_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            ResultSet res = prSt.executeQuery();
            List<Agreement> agreements = new ArrayList<>();
            while (res.next()) {
                Agreement agreement = new Agreement();
                agreement.setId(res.getInt(1));
                agreement.setRentPrice(res.getInt(2));
                agreement.setRentPeriod(res.getInt(3));
                agreement.setPassportNumber(res.getString(4));
                agreement.setVinNumber(res.getString(5));
                agreements.add(agreement);
            }
            return agreements;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Agreement create(Agreement add) throws ClassNotFoundException {
        Agreement agr = add;
        String ins = "INSERT INTO " + AGREEMENT_TABLE + "(" + AGREEMENT_ID + "," + AGREEMENT_RENT_PRICE + "," + AGREEMENT_RENT_PERIOD + "," + AGREEMENT_PASSPORT_NUMBER+ "," +  AGREEMENT_VIN_NUMBER + ")" + "VALUES(?,?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getId());
            prSt.setInt(2, agr.getRentPrice());
            prSt.setInt(3, agr.getRentPeriod());
            prSt.setString(4, agr.getPassportNumber());
            prSt.setString(5, agr.getVinNumber());

            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Agreement createWithoutID(Agreement add) throws ClassNotFoundException {
        Agreement agr = add;
        String ins = "INSERT INTO " + AGREEMENT_TABLE + "(" + AGREEMENT_RENT_PRICE + "," + AGREEMENT_RENT_PERIOD + "," + AGREEMENT_PASSPORT_NUMBER + "," +  AGREEMENT_VIN_NUMBER + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setInt(1, agr.getRentPrice());
            prSt.setInt(2, agr.getRentPeriod());
            prSt.setString(3, agr.getPassportNumber());
            prSt.setString(4, agr.getVinNumber());
            prSt.executeUpdate();
            return agr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Agreement update(Agreement upd) throws ClassNotFoundException {
        Agreement agr = upd;
        if (getByID(agr) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + AGREEMENT_TABLE + " SET " + AGREEMENT_RENT_PRICE + " =?, " + AGREEMENT_RENT_PERIOD + " =?, " +  AGREEMENT_PASSPORT_NUMBER+ " =?, " + AGREEMENT_VIN_NUMBER + " =? WHERE " + AGREEMENT_ID + "=" + agr.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
//                prSt.setString(1, String.valueOf(agr.getId()));
                prSt.setString(1, String.valueOf(agr.getRentPrice()));
                prSt.setString(2, String.valueOf(agr.getRentPeriod()));
                prSt.setString(3, agr.getPassportNumber());
                prSt.setString(4, agr.getVinNumber());
                prSt.executeUpdate();
                return agr;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        } else create(upd);
        return null;
    }

    @Override
    public boolean delete(Agreement del) {
        int realID = del.getId();
        if (getByID(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + AGREEMENT_TABLE + " WHERE " + AGREEMENT_ID + "=?";
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

    public static synchronized AgreementRepository getInstance() {
        if (instance == null) instance = new AgreementRepository();
        return instance;
    }

}
