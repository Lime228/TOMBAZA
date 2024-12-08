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

    public boolean create(ParkingPlace parking) throws ClassNotFoundException {
        return parkingPlaceRepository.create(parking) != null;
    }

    public boolean createWithoutId(ParkingPlace parking) throws ClassNotFoundException {
        return parkingPlaceRepository.create(parking) != null;
    }

    public ParkingPlace get(ParkingPlace parking) {
        return parkingPlaceRepository.getByID(parking);
    }

    public List<ParkingPlace> getByParkingId(ParkingPlace parking) {
        return parkingPlaceRepository.getByParkingId(parking);
    }

    public List<ParkingPlace> getByFloor(ParkingPlace parking) {
        return parkingPlaceRepository.getByFloor(parking);
    }

    public List<ParkingPlace> getByOccupiedSlot(ParkingPlace parking) {
        return parkingPlaceRepository.getByOccupiedSlot(parking);
    }

    public List<ParkingPlace> getAll() {
        return parkingPlaceRepository.getAll();
    }

    public ParkingPlace update(ParkingPlace parking) throws ClassNotFoundException {
        return parkingPlaceRepository.update(parking);
    }

    public boolean delete(ParkingPlace parking) {
        return parkingPlaceRepository.delete(parking);
    }

    public static synchronized ParkingPlaceService getInstance() {
        if (instance == null) instance = new ParkingPlaceService();
        return instance;
    }
}
