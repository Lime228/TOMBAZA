package com.jumpie.tombaza.back.servlets;
import com.jumpie.tombaza.back.models.Fine;
import com.jumpie.tombaza.back.services.AgreementService;
import com.jumpie.tombaza.back.services.FineService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class FineServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/fine.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Fine fine = new Fine(Integer.parseInt(req.getParameter("id")),"",0,0);
                Fine fn = FineService.get(fine);
                req.setAttribute("id", fn.getId());
                req.setAttribute("description", fn.getFineDescription());
                req.setAttribute("fineCost", fn.getFineCost());
                req.setAttribute("agreementId", fn.getAgreementId());
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("create") != null) {
            Fine fine = createFine(req);
            try {
                if(FineService.create(fine)) req.setAttribute("wasCreated", "успешно создана");
                else req.setAttribute("wasCreated", "машина не была создана");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Fine fine = createFine(req);
            try {
                if (FineService.update(fine)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","машина не была обновлена");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Fine fine = createFine(req);
            if (FineService.delete(fine)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","клиент не был удален");
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("fines", FineService.getAll());
        }else if (req.getParameter("getAgreements") != null) {
            req.setAttribute("agreements", AgreementService.getAll());
        }


        req.getRequestDispatcher("/fine.jsp").forward(req, resp);
    }

    private Fine createFine(HttpServletRequest req) {
        String id = req.getParameter("id");
        String fineDescription = req.getParameter("fineDescription");
        String fineCost = req.getParameter("fineCost");
        String agreementId = req.getParameter("agreementId");

        //проверку на пустоту?
        try {
            Fine fine = new Fine(Integer.parseInt(id),fineDescription,Integer.parseInt(fineCost),Integer.parseInt(agreementId));
            return fine;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Fine(0,"",0,0);
            //это явно не должно быть так
        }
    }
}
