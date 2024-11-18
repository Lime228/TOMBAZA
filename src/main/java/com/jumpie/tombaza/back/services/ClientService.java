package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Client;
import com.jumpie.tombaza.back.repositories.ClientRepository;

import java.util.List;


public class ClientService {

    public static boolean create(Client cli) throws ClassNotFoundException {
        return ClientRepository.getInstance().create(cli)!=null;
    }


    public static Client get(Client cli) {
        return ClientRepository.getInstance().getByID(cli);
    }
    public static List<Client> getAll() {
        return ClientRepository.getInstance().getAll();
    }

    public static Client update(Client cli) throws ClassNotFoundException {
        return ClientRepository.getInstance().update(cli);
    }


    public static boolean delete(Client cli) {
        return ClientRepository.getInstance().delete(cli);
    }
}
