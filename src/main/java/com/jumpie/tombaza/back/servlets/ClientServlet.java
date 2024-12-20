package com.jumpie.tombaza.back.servlets;

import com.jumpie.tombaza.back.models.Client;
import com.jumpie.tombaza.back.services.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientServlet extends HttpServlet {
    private ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("idRet", req.getParameter("id"));
        req.setAttribute("phoneNumberRet", req.getParameter("phoneNumber"));
        req.setAttribute("addressRet", req.getParameter("address"));
        req.setAttribute("nameRet", req.getParameter("name"));

        if (req.getParameter("get") != null) {
            try {
                Client client = createClient(req);
                Client cli = clientService.get(client);
                List<Client> list = new ArrayList<>();
                list.add(cli);
                req.setAttribute("clients", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий ID.");
            }

        } else if (req.getParameter("getByPNumber") != null) {
            try {
                Client client = createClient(req);
                List<Client> list = clientService.getByPhone(client);
                req.setAttribute("clients", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий номер.");
            }

        } else if (req.getParameter("getByAddress") != null) {
            try {
                Client client = createClient(req);
                List<Client> list = clientService.getByAddress(client);
                req.setAttribute("clients", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, был задан пустой или несуществующий адрес.");
            }

        } else if (req.getParameter("getByName") != null) {
            try {
                Client client = createClient(req);
                List<Client> list = clientService.getByName(client);
                req.setAttribute("clients", list);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "Вероятно, было задано пустое или несуществующее имя.");
            }


        } else if (req.getParameter("create") != null) {
            Client client = createClient(req);
            try {
                if (clientService.create(client)) req.setAttribute("error", "Успешно создан.");
                else req.setAttribute("error", "Клиент не был создан.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        } else if (req.getParameter("update") != null) {
            Client client = createClient(req);
            try {
                if (clientService.update(client) != null) req.setAttribute("errror", "Успешно обновлено.");
                else req.setAttribute("error", "Клиент не был обновлен.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (req.getParameter("change") != null) {
            Client client = createClient(req);
            req.setAttribute("idRet", client.getId());
            req.setAttribute("phoneNumberRet", client.getPhoneNumber());
            req.setAttribute("addressRet", client.getAddress());
            req.setAttribute("nameRet", client.getName());


        } else if (req.getParameter("getAll") != null) {
            req.setAttribute("clients", clientService.getAll());
        } else if (req.getParameter("deleteOther") != null) {
            Client client = createClient(req);
            if (clientService.delete(client)) req.setAttribute("error", "Успешно удалено.");
            else req.setAttribute("error", "Клиент не был удален.");
        }

        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }

    private Client createClient(HttpServletRequest req) {
        String id;
        String phoneNumber;
        String address;
        String name;

        if (req.getParameter("getByPNumber") != null) {
            return new Client("", req.getParameter("phoneNumber"), "", "");
        } else if (req.getParameter("getByAddress") != null) {
            return new Client("", "", req.getParameter("address"), "");
        } else if (req.getParameter("getByName") != null) {
            return new Client("", "", "", req.getParameter("name"));
        }


        if (req.getParameter("deleteOther") != null || req.getParameter("change") != null) {
            id = req.getParameter("idOther");
            phoneNumber = req.getParameter("phoneNumberOther");
            address = req.getParameter("addressOther");
            name = req.getParameter("nameOther");
        } else {
            id = req.getParameter("id");
            phoneNumber = req.getParameter("phoneNumber");
            address = req.getParameter("address");
            name = req.getParameter("name");
        }


        try {
            Client client = new Client(id,
                    phoneNumber,
                    address,
                    name);
            return client;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            if (req.getParameter("id") != "") {
                return new Client(req.getParameter("id"), "", "", "");
            } else return new Client("", "", "", "");

        }
    }
}
