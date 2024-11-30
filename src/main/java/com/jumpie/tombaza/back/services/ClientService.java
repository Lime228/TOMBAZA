package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Client;
import com.jumpie.tombaza.back.repositories.ClientRepository;

import java.util.List;

public class ClientService {
    private static ClientRepository clientRepository;
    private static ClientService instance;

    private ClientService() {
        clientRepository = ClientRepository.getInstance();
    }

    public static boolean create(Client cli) throws ClassNotFoundException {
        return clientRepository.create(cli)!=null;
    }


    public static Client get(Client cli) {
        return clientRepository.getByID(cli);
    }
    public static List<Client> getAll() {
        return clientRepository.getAll();
    }

    public static Client update(Client cli) throws ClassNotFoundException {
        return clientRepository.update(cli);
    }


    public static boolean delete(Client cli) {
        return clientRepository.delete(cli);
    }

    public static synchronized ClientService getInstance() {
        if (instance == null) instance = new ClientService();
        return instance;
    }
}
