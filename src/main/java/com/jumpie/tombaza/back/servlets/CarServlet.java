package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.services.CarService;
import com.jumpie.tombaza.back.services.ParkingPlaceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarServlet extends HttpServlet {
    private CarService carService = CarService.getInstance();
    private ParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/car.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("colorRet", req.getParameter("color"));
        req.setAttribute("brandRet", req.getParameter("brand"));
        req.setAttribute("modelNameRet", req.getParameter("modelName"));
        req.setAttribute("releaseYearRet", req.getParameter("releaseYear"));
        req.setAttribute("parkingPlaceIdRet", req.getParameter("parkingPlaceId"));
        req.setAttribute("numberRet", req.getParameter("number"));

        if (req.getParameter("get") != null) {
            try {
                Car car = createCar(req);
                Car cr = carService.get(car);
                List<Car> list = new ArrayList<>();
                list.add(cr);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }
        } else if (req.getParameter("getByColor") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByColor(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой цвет.");
            }
        } else if (req.getParameter("getByBrand") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByBrand(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой бренд.");
            }
        } else if (req.getParameter("getByModel") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByModel(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, была задана пустая модель.");
            }
        } else if (req.getParameter("getByYear") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByYear(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой год.");
            }
        } else if (req.getParameter("getByPPlace") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByPPlace(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой цвет.");
            }
        } else if (req.getParameter("getByNumber") != null) {
            try {
                Car car = createCar(req);
                List<Car> list = carService.getByNumber(car);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой номер.");
            }


        } else if (req.getParameter("create") != null) {
            Car car = createCar(req);
            try {
                if (carService.create(car)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "машина не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } else if (req.getParameter("update") != null) {
            Car car = createCar(req);
            try {
                if (carService.update(car) != null) req.setAttribute("error", "успешно обновлено");
                else req.setAttribute("error", "машина не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("change") != null) {
            Car car = createCar(req);
            req.setAttribute("idRet", car.getId());
            req.setAttribute("colorRet", car.getColor());
            req.setAttribute("brandRet", car.getBrand());
            req.setAttribute("modelNameRet", car.getModelName());
            req.setAttribute("releaseYearRet", car.getReleaseYear());
            req.setAttribute("parkingPlaceIdRet", car.getParkingPlaceId());
            req.setAttribute("numberRet", car.getNumber());


        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("cars", carService.getAll());
        } else if (req.getParameter("getParkingPlaces") != null) {
            req.setAttribute("places", parkingPlaceService.getAll());


        } else if (req.getParameter("deleteOther") != null) {
            Car client = createCar(req);
            if (carService.delete(client)) req.setAttribute("error", "успешно удалено");
            else req.setAttribute("error", "клиент не был удален");
        }

        req.getRequestDispatcher("/car.jsp").forward(req, resp);
    }

    private Car createCar(HttpServletRequest req) {
        String id = "";
        String color;
        String brand;
        String modelName;
        String releaseYear;
        String parkingPlaceId;
        String number;

        if (req.getParameter("getByColor") != null) {
            return new Car("", req.getParameter("color"), "", "", "", 0, "");
        } else if (req.getParameter("getByBrand") != null) {
            return new Car("", "", "", req.getParameter("brand"), "", 0, "");
        } else if (req.getParameter("getByModel") != null) {
            return new Car("", "", req.getParameter("modelName"), "", "", 0, "");
        } else if (req.getParameter("getByYear") != null) {
            return new Car("", "", "", "", req.getParameter("releaseYear"), 0, "");
        } else if (req.getParameter("getByPPlace") != null) {
            return new Car("", "", "", "", "", Integer.parseInt(req.getParameter("parkingPlaceId")), "");
        } else if (req.getParameter("getByNumber") != null) {
            return new Car("", "", "", "", "", 0, req.getParameter("number"));
        }


        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            id = req.getParameter("idOther");
            color = req.getParameter("colorOther");
            brand = req.getParameter("brandOther");
            modelName = req.getParameter("modelNameOther");
            releaseYear = req.getParameter("releaseYearOther");
            parkingPlaceId = req.getParameter("parkingPlaceIdOther");
            number = req.getParameter("numberOther");
        } else {
            id = req.getParameter("id");
            color = req.getParameter("color");
            brand = req.getParameter("brand");
            modelName = req.getParameter("modelName");
            releaseYear = req.getParameter("releaseYear");
            parkingPlaceId = req.getParameter("parkingPlaceId");
            number = req.getParameter("number");
        }


        try {
            Car car = new Car(id,
                    color,
                    modelName,
                    brand,
                    releaseYear,
                    Integer.parseInt(parkingPlaceId),
                    number);
            return car;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Car(req.getParameter("id"), "", "", "", "", 0, "");
            } else return new Car("", "", "", "", "", 0, "");

        }
    }

    private void getMoreInfo(List<Car> cars, HttpServletRequest req) {
        //добавить инфы
        req.setAttribute("cars", cars);

    }
}
