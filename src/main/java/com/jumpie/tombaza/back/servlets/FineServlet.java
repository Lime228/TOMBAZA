package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Fine;
import com.jumpie.tombaza.back.services.AgreementService;
import com.jumpie.tombaza.back.services.FineService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FineServlet extends HttpServlet {
    private FineService fineService = FineService.getInstance();
    private AgreementService agreementService = AgreementService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/fine.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("fineDescriptionRet", req.getParameter("fineDescription"));
        req.setAttribute("fineCostRet", req.getParameter("fineCost"));
        req.setAttribute("agreementIdRet", req.getParameter("agreementId"));


        if (req.getParameter("get") != null) {
            try {
                Fine fine = createFine(req);
                Fine fn = fineService.get(fine);
                List<Fine> list = new ArrayList<>();
                list.add(fn);
                req.setAttribute("fines", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }
        } else if (req.getParameter("getByAgId") != null) {
            try {
                Fine fine = createFine(req);
                List<Fine> list = fineService.getByAgreementId(fine);
                req.setAttribute("fines", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }


        } else if (req.getParameter("create") != null) {
            Fine fine = createFine(req);
            try {
                if (fineService.create(fine)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "машина не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("createWithoutId") != null) {
            Fine fine = createFine(req);
            try {
                if (fineService.createWithoutId(fine)) req.setAttribute("error", "успешно создана");
                else req.setAttribute("error", "машина не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } else if (req.getParameter("update") != null) {
            Fine fine = createFine(req);
            try {
                if (fineService.update(fine) != null) req.setAttribute("error", "успешно обновлено");
                else req.setAttribute("error", "машина не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("change") != null) {
            Fine fine = createFine(req);
            req.setAttribute("idRet", fine.getId());
            req.setAttribute("fineDescriptionRet", fine.getFineDescription());
            req.setAttribute("fineCostRet", fine.getFineCost());
            req.setAttribute("agreementIdRet", fine.getAgreementId());


        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("fines", fineService.getAll());
        } else if (req.getParameter("getAgreements") != null) {
            req.setAttribute("agreements", agreementService.getAll());


        } else if (req.getParameter("deleteOther") != null) {
            //нужно подтверждение
            Fine fine = createFine(req);
            if (fineService.delete(fine)) req.setAttribute("error", "успешно удалено");
            else req.setAttribute("error", "клиент не был удален");
        }


        req.getRequestDispatcher("/fine.jsp").forward(req, resp);
    }

    private Fine createFine(HttpServletRequest req) {
        String id = "0";
        String fineDescription;
        String fineCost;
        String agreementId;

        if (req.getParameter("getByAgId") != null) {
            return new Fine(0, "", 0, Integer.parseInt(req.getParameter("agreementId")));
        }


        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("idOther");
            }
            fineDescription = req.getParameter("fineDescriptionOther");
            fineCost = req.getParameter("fineCostOther");
            agreementId = req.getParameter("agreementIdOther");
        } else {
            if (req.getParameter("createWithoutId") == null) {
                id = req.getParameter("id");
            }
            fineDescription = req.getParameter("fineDescription");
            fineCost = req.getParameter("fineCost");
            agreementId = req.getParameter("agreementId");
        }


        try {
            Fine fine = new Fine(Integer.parseInt(id),
                    fineDescription,
                    Integer.parseInt(fineCost),
                    Integer.parseInt(agreementId));
            return fine;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Fine(Integer.parseInt(req.getParameter("id")), "", 0, 0);
            } else return new Fine(0, "", 0, 0);

        }
    }
}
