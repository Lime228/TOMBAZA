package com.jumpie.tombaza.back.services;

import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.repositories.CarRepository;

import java.util.List;

public class CarService {
    public static boolean create(Car car) throws ClassNotFoundException {
        return CarRepository.getInstance().create(car)!=null;
    }


    public static Car get(Car car) {
        return CarRepository.getInstance().getByID(car);
    }
    public static List<Car> getAll() {
        return CarRepository.getInstance().getAll();
    }

    public static Car update(Car car) throws ClassNotFoundException {
        return CarRepository.getInstance().update(car);
    }



    public static boolean delete(Car car) {
        return CarRepository.getInstance().delete(car);
    }
}
