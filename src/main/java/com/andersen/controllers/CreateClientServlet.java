package com.andersen.controllers;

import com.andersen.domain.Client;
import com.andersen.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CreateClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateClientServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start CreateClientServlet doPost.");
        String clientLogin = req.getParameter("clientName");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService service = (ClientService) context.getBean("clientService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String page = TextHolder.TABLE_SELECTION + "</br>\n</br>\n" + TextHolder.END_OF_PAGE;
        List<Client> clientList = service.findAll();
        out.println(page);
        for (Client client : clientList) {
            if (clientLogin.equals("") || clientLogin == null) {
                out.println("\nLogin is empty.");
                return;
            }
            if (client.getLogin().equals(clientLogin)) {
                out.println("\nLogin is already exist.");
                return;
            }
        }
        Client newClient = new Client(clientLogin);
        service.create(newClient);
        out.println("\nClient " + clientLogin + " is added.");
    }
}
