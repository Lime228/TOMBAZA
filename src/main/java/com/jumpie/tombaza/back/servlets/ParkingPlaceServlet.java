package com.jumpie.tombaza.back.servlets;
import com.jumpie.tombaza.back.models.ParkingPlace;
import com.jumpie.tombaza.back.services.ParkingPlaceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class ParkingPlaceServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/parkingplace.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                ParkingPlace parkingPlace = createParkinPlace(req);
                ParkingPlace pp = ParkingPlaceService.get(parkingPlace);
                req.setAttribute("id", pp.getId());
                req.setAttribute("occupiedSlot", pp.getOccupiedSlot());
                req.setAttribute("parkingId", pp.getParkingId());
                req.setAttribute("floor", pp.getFloor());
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("create") != null) {
            ParkingPlace pp = createParkinPlace(req);
            try {
                if(ParkingPlaceService.create(pp)) req.setAttribute("wasCreated", "успешно создана");
                else req.setAttribute("wasCreated", "место не было создано");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            ParkingPlace parkinPlace = createParkinPlace(req);
            try {
                if (ParkingPlaceService.update(parkinPlace)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","место не было обновлено");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            ParkingPlace pp = createParkinPlace(req);
            if (ParkingPlaceService.delete(pp)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","место не было удалено");
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("parkingPlaces", ParkingPlaceService.getAll());
        }
        req.getRequestDispatcher("/parkingplace.jsp").forward(req, resp);
    }

    private ParkingPlace createParkinPlace(HttpServletRequest req) {
        String id = req.getParameter("id");
        String occupiedSlot = req.getParameter("occupiedSlot");
        String parkingId = req.getParameter("parkingId");
        String floor = req.getParameter("floor");
        //проверку на пустоту?
        try {
            ParkingPlace parkingPlace = new ParkingPlace(Integer.parseInt(id),
                    Integer.parseInt(occupiedSlot),
                    Integer.parseInt(parkingId),
                    Short.parseShort(floor));
            return parkingPlace;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new ParkingPlace(Integer.parseInt(req.getParameter("id")),0,0, (short) 0);
            }else return new ParkingPlace(0,0,0, (short) 0);
        }
    }
}
