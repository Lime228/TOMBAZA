package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Agreement;
import com.jumpie.tombaza.back.repositories.AgreementRepository;

import java.util.List;

public class AgreementService {
    public static boolean create(Agreement agr) throws ClassNotFoundException {
        return AgreementRepository.getInstance().create(agr) != null;
    }


    public static Agreement get(Agreement agr) {
        return AgreementRepository.getInstance().getByID(agr);
    }
    public static List<Agreement> getAll() {
        return AgreementRepository.getInstance().getAll();
    }

    public static Agreement update(Agreement agr) throws ClassNotFoundException {
        return AgreementRepository.getInstance().update(agr);
    }


    public static boolean delete(Agreement agr) {
        return AgreementRepository.getInstance().delete(agr);
    }
}
