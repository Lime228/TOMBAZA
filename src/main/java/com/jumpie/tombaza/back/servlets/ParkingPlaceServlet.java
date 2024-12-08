package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.ParkingPlace;
import com.jumpie.tombaza.back.services.ParkingPlaceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceServlet extends HttpServlet {
    private static ParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/parkingplace.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("occupiedSlotRet", req.getParameter("occupiedSlot"));
        req.setAttribute("parkingIdRet", req.getParameter("parkingId"));
        req.setAttribute("floorRet", req.getParameter("floor"));


        if (req.getParameter("get") != null) {
            try {
                ParkingPlace parkingPlace = createParkinPlace(req);
                ParkingPlace pp = parkingPlaceService.get(parkingPlace);
                List<ParkingPlace> list = new ArrayList<>();
                list.add(pp);
                req.setAttribute("parkingPlaces", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }
        } else if (req.getParameter("getByParkingId") != null) {
            try {
                ParkingPlace parkingPlace = createParkinPlace(req);
                List<ParkingPlace> list = parkingPlaceService.getByParkingId(parkingPlace);
                req.setAttribute("parkingPlaces", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }
        } else if (req.getParameter("getByFloor") != null) {
            try {
                ParkingPlace parkingPlace = createParkinPlace(req);
                List<ParkingPlace> list = parkingPlaceService.getByFloor(parkingPlace);
                req.setAttribute("parkingPlaces", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("getByOccupiedSlot") != null) {
            try {
                ParkingPlace parkingPlace = createParkinPlace(req);
                List<ParkingPlace> list = parkingPlaceService.getByOccupiedSlot(parkingPlace);
                req.setAttribute("parkingPlaces", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }
            

        } else if (req.getParameter("create") != null) {
            ParkingPlace pp = createParkinPlace(req);
            try {
                if (parkingPlaceService.create(pp)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "место не было создано");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("createWithoutId") != null) {
            ParkingPlace pp = createParkinPlace(req);
            try {
                if (parkingPlaceService.createWithoutId(pp)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "место не было создано");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } else if (req.getParameter("update") != null) {
            ParkingPlace parkinPlace = createParkinPlace(req);
            try {
                if (parkingPlaceService.update(parkinPlace) != null)
                    req.setAttribute("error", "успешно обновлено");
                else req.setAttribute("error", "место не было обновлено");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("change") != null) {
            ParkingPlace parkinPlace = createParkinPlace(req);
            req.setAttribute("idRet", parkinPlace.getId());
            req.setAttribute("occupiedSlotRet", parkinPlace.getOccupiedSlot());
            req.setAttribute("parkingIdRet", parkinPlace.getParkingId());
            req.setAttribute("floorRet", parkinPlace.getFloor());


        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("parkingPlaces", parkingPlaceService.getAll());
        } else if (req.getParameter("getParkings") != null) {


        } else if (req.getParameter("deleteOther") != null) {
            ParkingPlace pp = createParkinPlace(req);
            if (parkingPlaceService.delete(pp)) req.setAttribute("error", "успешно удалено");
            else req.setAttribute("wasDeleted", "место не было удалено");
        }
        req.getRequestDispatcher("/parkingplace.jsp").forward(req, resp);
    }

    private ParkingPlace createParkinPlace(HttpServletRequest req) {
        String id = "0";
        String occupiedSlot;
        String parkingId;
        String floor;

        if (req.getParameter("getByParkingId") != null) {
            return new ParkingPlace(0, 0, Integer.parseInt(req.getParameter("parkingId")), (short) 0);
        } else if (req.getParameter("getByOccupiedSlot") != null) {
            return new ParkingPlace(0, Integer.parseInt(req.getParameter("occupiedSlot")), 0, (short) 0);
        } else if (req.getParameter("getByFloor") != null) {
            return new ParkingPlace(0, 0, 0, Short.parseShort(req.getParameter("floor")));
        }


        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("idOther");
            }
            occupiedSlot = req.getParameter("occupiedSlotOther");
            parkingId = req.getParameter("parkingIdOther");
            floor = req.getParameter("floorOther");
        } else {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("id");
            }
            occupiedSlot = req.getParameter("occupiedSlot");
            parkingId = req.getParameter("parkingId");
            floor = req.getParameter("floor");
        }


        try {
            ParkingPlace parkingPlace = new ParkingPlace(Integer.parseInt(id),
                    Integer.parseInt(occupiedSlot),
                    Integer.parseInt(parkingId),
                    Short.parseShort(floor));
            return parkingPlace;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new ParkingPlace(Integer.parseInt(req.getParameter("id")), 0, 0, (short) 0);
            } else return new ParkingPlace(0, 0, 0, (short) 0);
        }
    }
}
