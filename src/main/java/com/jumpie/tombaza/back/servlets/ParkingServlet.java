package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Parking;
import com.jumpie.tombaza.back.services.ParkingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingServlet extends HttpServlet {
    private ParkingService parkingService = ParkingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/parking.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("maxCapacityRet", req.getParameter("maxCapacity"));
        req.setAttribute("parkingAddressRet", req.getParameter("parkingAddress"));


        if (req.getParameter("get") != null) {
            try {
                Parking parking = createParking(req);
                Parking p = parkingService.get(parking);
                List<Parking> list = new ArrayList<>();
                list.add(p);
                req.setAttribute("parkings", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }


        } else if (req.getParameter("create") != null) {
            Parking pp = createParking(req);
            try {
                if (parkingService.create(pp)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "парковка не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("createWithoutId") != null) {


        } else if (req.getParameter("update") != null) {
            Parking parkinPlace = createParking(req);
            try {
                if (parkingService.update(parkinPlace) != null) req.setAttribute("error", "успешно обновлено");
                else req.setAttribute("error", "парковка не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("change") != null) {


        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("parkings", parkingService.getAll());


        } else if (req.getParameter("deleteOther") != null) {
            Parking pp = createParking(req);
            if (parkingService.delete(pp)) req.setAttribute("error", "успешно удалено");
            else req.setAttribute("error", "паркоука не была удалена");
        }
        req.getRequestDispatcher("/parking.jsp").forward(req, resp);
    }

    private Parking createParking(HttpServletRequest req) {
        String id = "0";
        String maxCapacity;
        String parkingAddress;

        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("idOther");
            }
            maxCapacity = req.getParameter("maxCapacityOther");
            parkingAddress = req.getParameter("parkingAddressOther");

        } else {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("id");
            }
            maxCapacity = req.getParameter("maxCapacity");
            parkingAddress = req.getParameter("parkingAddress");
        }


        try {
            Parking parkingPlace = new Parking(Integer.parseInt(id),
                    Integer.parseInt(maxCapacity),
                    parkingAddress);
            return parkingPlace;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Parking(Integer.parseInt(req.getParameter("id")), 0, "");
            } else return new Parking(0, 0, "");
        }
    }
}
