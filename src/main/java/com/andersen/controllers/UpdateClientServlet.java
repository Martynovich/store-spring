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

public class UpdateClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateClientServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start UpdateClientServlet doPost.");
        Integer id = Integer.parseInt(req.getParameter("clientIdToUpdate"));
        String newLogin = req.getParameter("clientLoginToUpdate");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService service = (ClientService) context.getBean("clientService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String tables = TextHolder.TABLE_SELECTION + "</br>\n</br>\n" + TextHolder.END_OF_PAGE;
        out.println(tables);
        List<Client> clients = service.findAll();
        for (Client c : clients) {
            if (c.getId() == id) {
                c.setLogin(newLogin);
                service.update(c);
                out.println("</br>");
                out.println("Client updated");
                return;
            }
        }
        out.println("</br>");
        out.println("Client with this id does not exist");
    }
}
