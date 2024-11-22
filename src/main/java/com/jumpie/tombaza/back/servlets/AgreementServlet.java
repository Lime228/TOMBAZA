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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Agreement agreement = createAgreement(req);
                Agreement agr = AgreementService.get(agreement);
                req.setAttribute("agrem", agr);
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID. Задайте число.");
            }

        } else if (req.getParameter("create") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if(AgreementService.create(agreement)) req.setAttribute("wasCreated", "успешно создан");
                else req.setAttribute("wasCreated", "договор не был создан");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Agreement agreement = createAgreement(req);
            try {
                if (AgreementService.update(agreement)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","договор не был обновлен");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Agreement agreement = createAgreement(req);
            if (AgreementService.delete(agreement)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","договор не был удален");
        } else if (req.getParameter("getPassports") != null) {
            req.setAttribute("passports", ClientService.getAll());
        } else if (req.getParameter("getCars") != null) {
            req.setAttribute("cars", CarService.getAll());
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("agreements", AgreementService.getAll());
        }

        req.getRequestDispatcher("/agreement.jsp").forward(req, resp);
    }
    private Agreement createAgreement(HttpServletRequest req) {
        String id = req.getParameter("id");
        String vinNumber = req.getParameter("vinNumber");
        String passportNumber = req.getParameter("passportNumber");
        String rentPeriod = req.getParameter("rentPeriod");
        String rentPrice = req.getParameter("rentPrice");
        //проверку на пустоту?
        try {
            Agreement agreement = new Agreement(Integer.parseInt(id),
                    Integer.parseInt(rentPrice),
                    Integer.parseInt(rentPeriod),
                    vinNumber,
                    passportNumber);
            return agreement;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if(req.getParameter("id")!="") {
                return new Agreement(Integer.parseInt(req.getParameter("id")), 0, 0, "", "");
            }else return new Agreement(0, 0, 0, "", "");

        }
    }
}
