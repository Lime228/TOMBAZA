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

    public boolean create(Client cli) throws ClassNotFoundException {
        return clientRepository.create(cli)!=null;
    }


    public Client get(Client cli) {
        return clientRepository.getByID(cli);
    }

    public List<Client> getByName(Client cli) {
        return clientRepository.getByName(cli);
    }

    public List<Client> getByAddress(Client cli) {
        return clientRepository.getByAddress(cli);
    }

    public List<Client> getByPhone(Client cli) {
        return clientRepository.getByPhone(cli);
    }

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Client update(Client cli) throws ClassNotFoundException {
        return clientRepository.update(cli);
    }


    public boolean delete(Client cli) {
        return clientRepository.delete(cli);
    }

    public static synchronized ClientService getInstance() {
        if (instance == null) instance = new ClientService();
        return instance;
    }
}
