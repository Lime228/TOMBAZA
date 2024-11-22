package com.jumpie.tombaza.back.repositories;

import com.jumpie.tombaza.back.models.Model;

import java.sql.SQLException;

public interface Repository<T extends Model> {

    T getByID(T get) throws SQLException;

    T create(T add) throws SQLException, ClassNotFoundException;
    T update(T upd) throws SQLException, ClassNotFoundException;

    boolean delete(T del) throws SQLException;
}
