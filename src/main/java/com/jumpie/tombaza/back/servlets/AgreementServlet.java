package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Agreement;
import com.jumpie.tombaza.back.models.Car;
import com.jumpie.tombaza.back.models.Client;
import com.jumpie.tombaza.back.services.AgreementService;
import com.jumpie.tombaza.back.services.CarService;
import com.jumpie.tombaza.back.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgreementServlet extends HttpServlet {
    private AgreementService agreementService = AgreementService.getInstance();
    private CarService carService = CarService.getInstance();
    private ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("rentPriceRet", req.getParameter("rentPrice"));
        req.setAttribute("rentPeriodRet", req.getParameter("rentPeriod"));
        req.setAttribute("passportNumberRet", req.getParameter("passportNumber"));
        req.setAttribute("vinNumberRet", req.getParameter("vinNumber"));

        if (req.getParameter("get") != null) {
            try {
                Agreement agreement = createAgreement(req);
                Agreement agr = agreementService.get(agreement);
                List<Agreement> list = new ArrayList<>();
                list.add(agr);
                getMoreInfo(list, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий ID. Задайте число.");
            }

        } else if (req.getParameter("getByPassport") != null) {
            try {
                Agreement agreement = createAgreement(req);
                List<Agreement> agr = agreementService.getByPassport(agreement);
                getMoreInfo(agr, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий паспорт.");
            }
        } else if (req.getParameter("getByCar") != null) {
            try {
                Agreement agreement = createAgreement(req);
                List<Agreement> agr = agreementService.getByCar(agreement);
                getMoreInfo(agr, req);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий ВИН-номер.");
            }


        } else if (req.getParameter("create") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (agreementService.create(agreement)) req.setAttribute("error", "Успешно создан.");
                else req.setAttribute("error", "Договор не был создан.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("createWithoutId") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (agreementService.createWithoutId(agreement)) req.setAttribute("error", "Успешно создан.");
                else req.setAttribute("error", "Договор не был создан.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } else if (req.getParameter("update") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (agreementService.update(agreement) != null) req.setAttribute("error", "Успешно обновлено.");
                else req.setAttribute("error", "Договор не был обновлен.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("change") != null) {
            Agreement agreement = createAgreement(req);
            req.setAttribute("idRet", agreement.getId());
            req.setAttribute("rentPriceRet", agreement.getRentPrice());
            req.setAttribute("rentPeriodRet", agreement.getRentPeriod());
            req.setAttribute("passportNumberRet", agreement.getPassportNumber());
            req.setAttribute("vinNumberRet", agreement.getVinNumber());


        } else if (req.getParameter("getPassports") != null) {
            req.setAttribute("passports", clientService.getAll());
        } else if (req.getParameter("getCars") != null) {
            req.setAttribute("cars", carService.getAll());
        } else if (req.getParameter("getAll") != null) {
            List<Agreement> agreements = agreementService.getAll();
            getMoreInfo(agreements, req);


        } else if (req.getParameter("deleteOther") != null) {
            Agreement agreement = createAgreement(req);
            if (agreementService.delete(agreement)) req.setAttribute("error", "Успешно удалено.");
            else req.setAttribute("error", "Договор не был удален.");
        }
        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }

    private Agreement createAgreement(HttpServletRequest req) {
        String id = "0";
        String rentPrice;
        String rentPeriod;
        String passportNumber;
        String vinNumber;

        if (req.getParameter("getByCar") != null) {
            return new Agreement(0, 0, 0, "", vinNumber = req.getParameter("vinNumber"));
        } else if (req.getParameter("getByPassport") != null) {
            return new Agreement(0, 0, 0, req.getParameter("passportNumber"), "");
        }


        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("idOther");
            }
            rentPrice = req.getParameter("rentPriceOther");
            rentPeriod = req.getParameter("rentPeriodOther");
            passportNumber = req.getParameter("passportNumberOther");
            vinNumber = req.getParameter("vinNumberOther");
        } else {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("id");
            }
            rentPrice = req.getParameter("rentPrice");
            rentPeriod = req.getParameter("rentPeriod");
            passportNumber = req.getParameter("passportNumber");
            vinNumber = req.getParameter("vinNumber");
        }


        try {
            Agreement agreement = new Agreement(Integer.parseInt(id),
                    Integer.parseInt(rentPrice),
                    Integer.parseInt(rentPeriod),
                    passportNumber,
                    vinNumber);
            return agreement;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Agreement(Integer.parseInt(req.getParameter("id")), 0, 0, "", "");
            } else return new Agreement(0, 0, 0, "", "");

        }

    }

    private void getMoreInfo(List<Agreement> agreements, HttpServletRequest req) {
        List<Car> cars = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        for (Agreement agreement : agreements) {
            cars.add(carService.get(new Car(agreement.getVinNumber().toString(), "", "", "", "", 0, "")));
            clients.add(clientService.get(new Client(agreement.getPassportNumber(), "", "", "")));
        }

        List<Agreement> newAgreements = new ArrayList<>();
        req.setAttribute("allAgreements", agreements.size());
        req.setAttribute("agreements", agreements);
        req.setAttribute("carsInfo", cars);
        req.setAttribute("clientsInfo", clients);

    }
}
