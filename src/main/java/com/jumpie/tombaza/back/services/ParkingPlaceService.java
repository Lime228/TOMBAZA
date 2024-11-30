package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.ParkingPlace;
import com.jumpie.tombaza.back.repositories.ParkingPlaceRepository;

import java.util.List;

public class ParkingPlaceService {
    private static ParkingPlaceRepository parkingPlaceRepository;
    private static ParkingPlaceService instance;

    private ParkingPlaceService() {
        parkingPlaceRepository = ParkingPlaceRepository.getInstance();
    }

    public static boolean create(ParkingPlace parking) throws ClassNotFoundException {
        return parkingPlaceRepository.create(parking)!=null;
    }

    public static ParkingPlace get(ParkingPlace parking) {
        return parkingPlaceRepository.getByID(parking);
    }
    public static List<ParkingPlace> getAll() {
        return parkingPlaceRepository.getAll();
    }

    public static ParkingPlace update(ParkingPlace parking) throws ClassNotFoundException {
        return parkingPlaceRepository.update(parking);
    }

    public static boolean delete(ParkingPlace parking) {
        return parkingPlaceRepository.delete(parking);
    }

    public static synchronized ParkingPlaceService getInstance() {
        if (instance == null) instance = new ParkingPlaceService();
        return instance;
    }
}
