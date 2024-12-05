package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Agreement;
import com.jumpie.tombaza.back.repositories.AgreementRepository;

import java.util.List;

public class AgreementService {
    private static AgreementRepository agreementRepository;
    private static AgreementService instance;

    private AgreementService() {
        agreementRepository = AgreementRepository.getInstance();
    }

    public boolean create(Agreement agr) throws ClassNotFoundException {
        return agreementRepository.create(agr) != null;
    }

    public boolean createWithoutId(Agreement agr) throws ClassNotFoundException {
        return agreementRepository.createWithoutID(agr) != null;
    }
    //добавить контейнер, сервисы - синглтоны

    public Agreement get(Agreement agr) {
        return agreementRepository.getByID(agr);
    }

    public List<Agreement> getByPassport(Agreement agr) {
        return agreementRepository.getByPassport(agr);
    }

    public List<Agreement> getByCar(Agreement agr) {
        return agreementRepository.getByCar(agr);
    }

    public List<Agreement> getAll() {
        return agreementRepository.getAll();
    }

    public Agreement update(Agreement agr) throws ClassNotFoundException {
        return agreementRepository.update(agr);
    }

    public boolean delete(Agreement agr) {
        return agreementRepository.delete(agr);
    }

    public static synchronized AgreementService getInstance(){
        if (instance == null) instance = new AgreementService();
        return instance;
    }
}
