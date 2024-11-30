package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Fine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FineRepository implements Repository<Fine> {
    private static FineRepository instance;
    public static final String FINE_TABLE = "fine";

    public static final String FINE_ID = "fine_id";
    public static final String FINE_DESCRIPTION = "fine_description";
    public static final String FINE_COST = "fine_cost";
    public static final String FINE_AGREEMENT = "agreement_id";

    private FineRepository() {}

    @Override
    public Fine getByID(Fine get) {
        int realID = get.getId();
        String ins = "SELECT * FROM " + FINE_TABLE + " WHERE " + FINE_ID + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();

        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            prSt.setString(1, String.valueOf(realID));
            ResultSet res = prSt.executeQuery();
            res.next();
            Fine fin = new Fine();
            fin.setId(res.getInt(1));
            fin.setFineDescription(res.getString(2));
            fin.setFineCost(res.getInt(3));
            fin.setAgreementId(res.getInt(4));
            return fin;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Fine> getAll() {
        String ins = "SELECT * FROM " + FINE_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            ResultSet res = prSt.executeQuery();
            List<Fine> fines = new ArrayList<>();
            while (res.next()) {
                Fine fin = new Fine();
                fin.setId(res.getInt(1));
                fin.setFineDescription(res.getString(2));
                fin.setFineCost(res.getInt(3));
                fin.setAgreementId(res.getInt(4));
                fines.add(fin);
            }
            return fines;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Fine create(Fine add) throws ClassNotFoundException {
        Fine fin = add;
        String ins = "INSERT INTO " + FINE_TABLE + "(" + FINE_ID + "," + FINE_DESCRIPTION + "," + FINE_COST + "," + FINE_AGREEMENT + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            prSt.setString(1, String.valueOf(fin.getId()));
            prSt.setString(2, fin.getFineDescription());
            prSt.setString(3, String.valueOf(fin.getFineCost()));
            prSt.setString(4, String.valueOf(fin.getAgreementId()));
            prSt.executeUpdate();
            return fin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Fine createWithoutID(Fine add) throws ClassNotFoundException {
        Fine fin = add;
        String ins = "INSERT INTO " + FINE_TABLE + "(" + FINE_DESCRIPTION + "," + FINE_COST + "," + FINE_AGREEMENT + ")" + "VALUES(?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
            prSt.setString(1, fin.getFineDescription());
            prSt.setString(2, String.valueOf(fin.getFineCost()));
            prSt.setString(3, String.valueOf(fin.getAgreementId()));
            prSt.executeUpdate();
            return fin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Fine update(Fine upd) throws ClassNotFoundException {
        Fine fin = upd;
        if (getByID(fin)!=null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + FINE_TABLE + " SET " + FINE_ID + " = ?, " + FINE_DESCRIPTION + " =?, " + FINE_COST + " =?, " + FINE_AGREEMENT + " =? WHERE " + FINE_ID + "=" + fin.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
                prSt.setString(1, String.valueOf(fin.getId()));
                prSt.setString(2, fin.getFineDescription());
                prSt.setString(3, String.valueOf(fin.getFineCost()));
                prSt.setString(4, String.valueOf(fin.getAgreementId()));
                prSt.executeUpdate();
                return fin;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else create(upd);
        return null;
    }

    @Override
    public boolean delete(Fine del) {
        int realID = del.getId();
        if (getByID(del)!=null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + FINE_TABLE + " WHERE " + FINE_ID + "=?";
            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)){
                prSt.setString(1, String.valueOf(realID));
                prSt.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("Nothing to delete.");
        return false;
    }

    public static synchronized FineRepository getInstance() {
        if (instance == null) instance = new FineRepository();
        return instance;
    }
}
