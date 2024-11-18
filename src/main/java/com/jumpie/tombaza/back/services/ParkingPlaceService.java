package com.jumpie.tombaza.back.services;


import com.jumpie.tombaza.back.models.ParkingPlace;
import com.jumpie.tombaza.back.repositories.ParkingPlaceRepository;

import java.util.List;

public class ParkingPlaceService {
    public static boolean create(ParkingPlace parking) throws ClassNotFoundException {
        return ParkingPlaceRepository.getInstance().create(parking)!=null;
    }

    public static ParkingPlace get(ParkingPlace parking) {
        return ParkingPlaceRepository.getInstance().getByID(parking);
    }
    public static List<ParkingPlace> getAll() {
        return ParkingPlaceRepository.getInstance().getAll();
    }

    public static ParkingPlace update(ParkingPlace parking) throws ClassNotFoundException {
        return ParkingPlaceRepository.getInstance().update(parking);
    }

    public static boolean delete(ParkingPlace parking) {
        return ParkingPlaceRepository.getInstance().delete(parking);
    }
}
