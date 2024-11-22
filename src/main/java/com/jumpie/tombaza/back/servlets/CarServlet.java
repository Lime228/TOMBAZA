package com.jumpie.tombaza.back.servlets;
import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.services.CarService;
import com.jumpie.tombaza.back.services.ParkingPlaceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class CarServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/car.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Car car = createCar(req);
                Car cr = CarService.get(car);
                req.setAttribute("id", cr.getId());
                req.setAttribute("color", cr.getColor());
                req.setAttribute("brand", cr.getBrand());
                req.setAttribute("modelName", cr.getModelName());
                req.setAttribute("releaseYear", cr.getReleaseYear());
                req.setAttribute("parkingPlaceId", cr.getParkingPlaceId());
                req.setAttribute("number", cr.getNumber());
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("create") != null) {
            Car car = createCar(req);
            try {
                if(CarService.create(car)) req.setAttribute("wasCreated", "успешно создана");
                else req.setAttribute("wasCreated", "машина не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Car car = createCar(req);
            try {
                if (CarService.update(car)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","машина не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Car client = createCar(req);
            if (CarService.delete(client)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","клиент не был удален");
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("cars", CarService.getAll());
        }else if (req.getParameter("getParkingPlaces") != null) {
            req.setAttribute("places", ParkingPlaceService.getAll());
        }

        req.getRequestDispatcher("/car.jsp").forward(req, resp);
    }

    private Car createCar(HttpServletRequest req) {
        String id = req.getParameter("id");
        String color = req.getParameter("color");
        String brand = req.getParameter("brand");
        String modelName = req.getParameter("modelName");
        String releaseYear = req.getParameter("releaseYear");
        String parkingPlaceId = req.getParameter("parkingPlaceId");
        String number = req.getParameter("number");
        //проверку на пустоту?
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
                return new Car(req.getParameter("id"),"","","","",0,"");
            }else return new Car("","","","","",0,"");

        }
    }
}
