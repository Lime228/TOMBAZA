package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Agreement;
import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.services.AgreementService;
import com.jumpie.tombaza.back.services.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsServlet extends HttpServlet {
    private AgreementService agreementService = AgreementService.getInstance();
    private CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/stat.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getParameter("getCar") != null) {
            List<Car> cars = carService.getAll();
            List<Agreement> agreements = agreementService.getAll();
            String[] stat = new String[cars.size()];
            int i = 0;
            int counter;
            for (Car car : cars) {
                counter = 0;
                stat[i] = car.getBrand() + " " + car.getModelName() + " " + car.getId();
                for (Agreement agreement : agreements) {
                    if (car.getId().equals(agreement.getVinNumber())) {
                        counter++;
                    }
                }
                stat[i] = stat[i] + " была арендована " + counter + " раз \n";
                i++;

            }
            req.setAttribute("stats", stat);
        }
        req.getRequestDispatcher("/stat.jsp").forward(req, resp);
    }
}
