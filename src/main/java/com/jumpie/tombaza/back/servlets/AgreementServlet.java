package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Agreement;
import com.jumpie.tombaza.back.services.AgreementService;
import com.jumpie.tombaza.back.services.CarService;
import com.jumpie.tombaza.back.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AgreementServlet extends HttpServlet {
    private static AgreementService agreementService = AgreementService.getInstance();
    private static CarService carService = CarService.getInstance();
    private static ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("get") != null) {
            try {
                Agreement agreement = createAgreement(req);
                Agreement agr = agreementService.get(agreement);
                req.setAttribute("agrem", agr);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID. Задайте число.");
            }

        } else if (req.getParameter("create") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (agreementService.create(agreement)) req.setAttribute("wasCreated", "успешно создан");
                else req.setAttribute("wasCreated", "договор не был создан");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (agreementService.update(agreement) != null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated", "договор не был обновлен");
            } catch (Exception e) {
                e.printStackTrace();
            }
//        } else if (req.getParameter("delete") != null) {
//            //нужно подтверждение                            БОЛЕЕ НЕ ВОСТРЕБОВАНО ПОДЛЕЖИТ УДАЛЕНИЮ
//            Agreement agreement = createAgreement(req);
//            if (agreementService.delete(agreement)) req.setAttribute("wasDeleted", "успешно удалено");
//            else req.setAttribute("wasDeleted","договор не был удален");
        } else if (req.getParameter("getPassports") != null) {
            req.setAttribute("passports", clientService.getAll());
        } else if (req.getParameter("getCars") != null) {
            req.setAttribute("cars", carService.getAll());
        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("agreements", agreementService.getAll());
        } else if (req.getParameter("deleteOther") != null) {
            Agreement agreement = createAgreement(req);
            if (agreementService.delete(agreement)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted", "договор не был удален");
        }else if (req.getParameter("change") != null) {
            Agreement agreement = createAgreement(req);
            req.setAttribute("idRet", agreement.getId());
            req.setAttribute("rentPriceRet", agreement.getRentPrice());
            req.setAttribute("rentPeriodRet", agreement.getRentPeriod());
            req.setAttribute("passportNumberRet", agreement.getPassportNumber());
            req.setAttribute("vinNumberRet", agreement.getVinNumber());


        }

        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }

    private Agreement createAgreement(HttpServletRequest req) {
        String id;
        String rentPrice;
        String rentPeriod;
        String passportNumber;
        String vinNumber;

        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            id = req.getParameter("idOther");
            rentPrice = req.getParameter("rentPriceOther");
            rentPeriod = req.getParameter("rentPeriodOther");
            passportNumber = req.getParameter("passportNumberOther");
            vinNumber = req.getParameter("vinNumberOther");
        } else {
            id = req.getParameter("id");
            rentPrice = req.getParameter("rentPrice");
            rentPeriod = req.getParameter("rentPeriod");
            passportNumber = req.getParameter("passportNumber");
            vinNumber = req.getParameter("vinNumber");


        }
        //проверку на пустоту?
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
}
