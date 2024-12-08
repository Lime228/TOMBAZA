package com.jumpie.tombaza.back.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
    //я знаю что так хранить не надо, мне просто лень переделывать 😭😭😭
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUsername = "root";
    protected String dbPassword = "55317899871355";
    protected String dbName = "car_arend";
    private static ConnectJDBC instance;

    private ConnectJDBC() {
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connection, dbUsername, dbPassword);
    }

    public static synchronized ConnectJDBC getInstance() {
        if (instance == null) instance = new ConnectJDBC();
        return instance;
    }
}
