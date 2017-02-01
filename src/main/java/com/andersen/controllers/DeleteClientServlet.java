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

public class DeleteClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteClientServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start DeleteClientServlet doPost.");
        Integer id = Integer.parseInt(req.getParameter("clientIdToDel"));
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService service = (ClientService) context.getBean("clientService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String tables = TextHolder.TABLE_SELECTION + "</br>\n</br>\n" + TextHolder.END_OF_PAGE;
        out.println(tables);
        List<Client> clients = service.findAll();
        for(Client c : clients) {
            if (c.getId() == id) {
                service.delete(c);
                out.println("</br>");
                out.println("Client " + c.getLogin() + " deleted");
                return;
            }
        }
        out.println("</br>");
        out.println("Client with this id does not exist");
    }
}
