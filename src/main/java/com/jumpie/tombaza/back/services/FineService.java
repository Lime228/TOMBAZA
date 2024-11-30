package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Fine;
import com.jumpie.tombaza.back.repositories.FineRepository;

import java.util.List;

public class FineService {
    private static FineRepository fineRepository;
    private static FineService instance;

    private FineService(){
        fineRepository = FineRepository.getInstance();
    }

    public static boolean create(Fine fine) throws ClassNotFoundException {
        return fineRepository.create(fine) != null;
    }


    public static Fine get(Fine fine) {
        return fineRepository.getByID(fine);
    }
    public static List<Fine> getAll() {
        return fineRepository.getAll();
    }

    public static Fine update(Fine fine) throws ClassNotFoundException {
        return fineRepository.update(fine);
    }

    public static boolean delete(Fine fine) {
        return fineRepository.delete(fine);
    }

    public static synchronized FineService getInstance() {
        if (instance == null) instance = new FineService();
        return instance;
    }
}

