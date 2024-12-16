package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Fine;
import com.jumpie.tombaza.back.repositories.FineRepository;

import java.util.List;

public class FineService {
    private final FineRepository fineRepository;
    private static FineService instance;

    private FineService() {
        fineRepository = FineRepository.getInstance();
    }

    public boolean create(Fine fine) throws ClassNotFoundException {
        return fineRepository.create(fine) != null;
    }

    public boolean createWithoutId(Fine fine) throws ClassNotFoundException {
        return fineRepository.createWithoutID(fine) != null;
    }

    public Fine get(Fine fine) {
        return fineRepository.getByID(fine);
    }

    public List<Fine> getByAgreementId(Fine fine) {
        return fineRepository.getByAgreementId(fine);
    }

    public List<Fine> getAll() {
        return fineRepository.getAll();
    }

    public Fine update(Fine fine) throws ClassNotFoundException {
        return fineRepository.update(fine);
    }

    public boolean delete(Fine fine) {
        return fineRepository.delete(fine);
    }

    public static synchronized FineService getInstance() {
        if (instance == null) instance = new FineService();
        return instance;
    }
}

