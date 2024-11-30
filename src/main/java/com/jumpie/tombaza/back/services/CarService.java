package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.repositories.CarRepository;

import java.util.List;

public class CarService {
    private static CarRepository carRepository;
    private static CarService instance;

    private CarService() {
        carRepository =CarRepository.getInstance();
    }

    public boolean create(Car car) throws ClassNotFoundException {
        return carRepository.create(car)!=null;
    }

    public static Car get(Car car) {
        return carRepository.getByID(car);
    }
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    public Car update(Car car) throws ClassNotFoundException {
        return carRepository.update(car);
    }

    public boolean delete(Car car) {
        return carRepository.delete(car);
    }

    public static synchronized CarService getInstance(){
        if (instance == null) instance = new CarService();
        return instance;
    }
}
