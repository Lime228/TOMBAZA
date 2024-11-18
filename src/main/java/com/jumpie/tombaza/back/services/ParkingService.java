package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Parking;
import com.jumpie.tombaza.back.repositories.ParkingRepository;

import java.util.List;

public class ParkingService {
    public static boolean create(Parking parking) throws ClassNotFoundException {
        return ParkingRepository.getInstance().create(parking)!=null;
    }


    public static Parking get(Parking parking) {
        return ParkingRepository.getInstance().getByID(parking);
    }
    public static List<Parking> getAll() {
        return ParkingRepository.getInstance().getAll();
    }

    public static Parking update(Parking parking) throws ClassNotFoundException {
        return ParkingRepository.getInstance().update(parking);
    }


    public static boolean delete(Parking parking) {
        return ParkingRepository.getInstance().delete(parking);
    }
}
