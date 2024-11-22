package com.jumpie.tombaza.back.servlets;
import com.jumpie.tombaza.back.models.Parking;
import com.jumpie.tombaza.back.services.ParkingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class ParkingServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/parking.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Parking parking = createParking(req);
                Parking p = ParkingService.get(parking);
                req.setAttribute("id", p.getId());
                req.setAttribute("maxCapacity", p.getMaxCapacity());
                req.setAttribute("parkingAddress", p.getParkingAddress());
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("create") != null) {
            Parking pp = createParking(req);
            try {
                if(ParkingService.create(pp)) req.setAttribute("wasCreated", "успешно создана");
                else req.setAttribute("wasCreated", "парковка не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Parking parkinPlace = createParking(req);
            try {
                if (ParkingService.update(parkinPlace)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","парковка не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Parking pp = createParking(req);
            if (ParkingService.delete(pp)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","паркоука не была удалена");
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("parkings", ParkingService.getAll());
        }
        req.getRequestDispatcher("/parking.jsp").forward(req, resp);
    }

    private Parking createParking(HttpServletRequest req) {
        String id = req.getParameter("id");
        String maxCapacity = req.getParameter("maxCapacity");
        String parkingAddress = req.getParameter("parkingAddress");
        //проверку на пустоту?
        try {
            Parking parkingPlace = new Parking(Integer.parseInt(id),
                    Integer.parseInt(maxCapacity),
                    parkingAddress);
            return parkingPlace;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Parking(Integer.parseInt(req.getParameter("id")),0,"");
            }else return new Parking(0,0,"");
        }
    }
}
