package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Parking;
import com.jumpie.tombaza.back.repositories.ParkingRepository;

import java.util.List;

public class ParkingService {
    private static ParkingRepository parkingRepository;
    private static ParkingService instance;

    private ParkingService() {
        parkingRepository = ParkingRepository.getInstance();
    }

    public boolean create(Parking parking) throws ClassNotFoundException {
        return parkingRepository.create(parking) != null;
    }

    public Parking get(Parking parking) {
        return parkingRepository.getByID(parking);
    }

    public List<Parking> getAll() {
        return parkingRepository.getAll();
    }

    public Parking update(Parking parking) throws ClassNotFoundException {
        return parkingRepository.update(parking);
    }

    public boolean delete(Parking parking) {
        return parkingRepository.delete(parking);
    }

    public static synchronized ParkingService getInstance() {
        if (instance == null) instance = new ParkingService();
        return instance;
    }
}
