package com.jumpie.tombaza.back.services;


import com.jumpie.tombaza.back.models.Fine;
import com.jumpie.tombaza.back.repositories.FineRepository;

import java.util.List;

public class FineService {
    public static boolean create(Fine fine) throws ClassNotFoundException {
        return FineRepository.getInstance().create(fine) != null;
    }


    public static Fine get(Fine fine) {
        return FineRepository.getInstance().getByID(fine);
    }
    public static List<Fine> getAll() {
        return FineRepository.getInstance().getAll();
    }

    public static Fine update(Fine fine) throws ClassNotFoundException {
        return FineRepository.getInstance().update(fine);
    }


    public static boolean delete(Fine fine) {
        return FineRepository.getInstance().delete(fine);
    }
}
