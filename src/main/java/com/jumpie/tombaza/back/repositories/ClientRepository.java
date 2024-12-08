package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Client;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements Repository<Client> {
    private static ClientRepository instance;

    public static final String CLIENT_TABLE = "client";
    public static final String CLIENT_PASSPORT = "passport_number";
    public static final String CLIENT_NUMBER = "phone_number";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_NAME = "name";

    private ClientRepository() {
    }

    @Override
    public Client getByID(Client get) {
        String realID = get.getId();
        String ins = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_PASSPORT + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, realID);
            ResultSet res = prSt.executeQuery();
            res.next();
            Client client = new Client();
            client.setId(res.getString(1));
            client.setPhoneNumber(res.getString(2));
            client.setAddress(res.getString(3));
            client.setName(res.getString(4));
            return client;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> getByPhone(Client cli) {
        String phone = cli.getPhoneNumber();
        String ins = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_NUMBER + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, phone);
            ResultSet res = prSt.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (res.next()) {
                Client client = new Client();
                client.setId(res.getString(1));
                client.setPhoneNumber(res.getString(2));
                client.setAddress(res.getString(3));
                client.setName(res.getString(4));
                clients.add(client);
            }
            return clients;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> getByAddress(Client cli) {
        String address = cli.getAddress();
        String ins = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_ADDRESS + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, address);
            ResultSet res = prSt.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (res.next()) {
                Client client = new Client();
                client.setId(res.getString(1));
                client.setPhoneNumber(res.getString(2));
                client.setAddress(res.getString(3));
                client.setName(res.getString(4));
                clients.add(client);
            }
            return clients;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> getByName(Client cli) {
        String name = cli.getName();
        String ins = "SELECT * FROM " + CLIENT_TABLE + " WHERE " + CLIENT_NAME + "=?";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            prSt.setString(1, name);
            ResultSet res = prSt.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (res.next()) {
                Client client = new Client();
                client.setId(res.getString(1));
                client.setPhoneNumber(res.getString(2));
                client.setAddress(res.getString(3));
                client.setName(res.getString(4));
                clients.add(client);
            }
            return clients;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Client> getAll() {

        String ins = "SELECT * FROM " + CLIENT_TABLE;
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {

            ResultSet res = prSt.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (res.next()) {
                Client client = new Client();
                client.setId(res.getString(1));
                client.setPhoneNumber(res.getString(2));
                client.setAddress(res.getString(3));
                client.setName(res.getString(4));
                clients.add(client);
            }
            return clients;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Client create(Client add) throws ClassNotFoundException {
        Client cli = add;
        String ins = "INSERT INTO " + CLIENT_TABLE + "(" + CLIENT_PASSPORT + "," + CLIENT_NUMBER + "," + CLIENT_ADDRESS + "," + CLIENT_NAME + ")" + "VALUES(?,?,?,?)";
        ConnectJDBC con = ConnectJDBC.getInstance();
        try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
            return setClient(cli, prSt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client update(Client upd) throws ClassNotFoundException {
        Client cli = upd;
        if (getByID(cli) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "UPDATE " + CLIENT_TABLE + " SET " + CLIENT_PASSPORT + " = ?, " + CLIENT_NUMBER + " =?, " + CLIENT_ADDRESS + " =?, " + CLIENT_NAME + " =? WHERE " + CLIENT_PASSPORT + "=" + cli.getId();

            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                return setClient(cli, prSt);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else create(upd);
        return null;
    }

    private Client setClient(Client cli, PreparedStatement prSt) throws SQLException {
        prSt.setString(1, cli.getId());
        prSt.setString(2, cli.getPhoneNumber());
        prSt.setString(3, cli.getAddress());
        prSt.setString(4, cli.getName());
        prSt.executeUpdate();
        return cli;
    }

    @Override
    public boolean delete(Client del) {
        String realID = del.getId();
        if (getByID(del) != null) {
            ConnectJDBC con = ConnectJDBC.getInstance();
            String ins = "DELETE FROM " + CLIENT_TABLE + " WHERE " + CLIENT_PASSPORT + "=?";
            try (PreparedStatement prSt = con.getDbConnection().prepareStatement(ins)) {
                prSt.setString(1, realID);
                prSt.executeUpdate();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("Nothing to delete.");
        return false;
    }

    public static synchronized ClientRepository getInstance() {
        if (instance == null) instance = new ClientRepository();
        return instance;
    }
}
