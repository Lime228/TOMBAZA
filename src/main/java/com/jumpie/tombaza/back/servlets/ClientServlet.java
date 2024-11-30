package com.jumpie.tombaza.back.servlets;
import com.jumpie.tombaza.back.models.Client;
import com.jumpie.tombaza.back.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class ClientServlet extends HttpServlet{
    private static ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        if(req.getParameter("get") != null) {
            try {
                Client client = createClient(req);
                Client cli = clientService.get(client);
                req.setAttribute("id", cli.getId());
                req.setAttribute("phoneNumber", cli.getPhoneNumber());
                req.setAttribute("address", cli.getAddress());
                req.setAttribute("name", cli.getName());
            } catch (Exception e){
                e.printStackTrace();
                req.setAttribute("error", "вероятно, был задан пустой ID.");
            }

        } else if (req.getParameter("create") != null) {
            Client client = createClient(req);
            try {
                if(clientService.create(client)) req.setAttribute("wasCreated", "успешно создан");
                else req.setAttribute("wasCreated", "клиент не был создан");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("update") != null) {
            Client client = createClient(req);
            try {
                if (clientService.update(client)!=null) req.setAttribute("wasUpdated", "успешно обновлено");
                else req.setAttribute("wasUpdated","клиент не был обновлен");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("delete") != null) {
            //нужно подтверждение
            Client client = createClient(req);
            if (clientService.delete(client)) req.setAttribute("wasDeleted", "успешно удалено");
            else req.setAttribute("wasDeleted","клиент не был удален");
        }else if (req.getParameter("getAll") != null) {
            req.setAttribute("clients", clientService.getAll());
        }

        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }

    private Client createClient(HttpServletRequest req) {
        String id = req.getParameter("id");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String name = req.getParameter("name");
        //проверку на пустоту?
        try {
            Client client = new Client(id,
                    phoneNumber,
                    address,
                    name);
            return client;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Client(req.getParameter("id"),"","","");
            }else return new Client("","","","");

        }
    }
}
