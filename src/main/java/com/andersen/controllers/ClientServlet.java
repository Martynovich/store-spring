package com.andersen.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start ClientServlet doGet.");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println(TextHolder.TABLE_SELECTION +
                "    </br>\n" +
                "    <p>Client page</p>\n" +
                "    <hr>\n" +
                "    <div id=\"create\">\n" +
                "    \t<p>Create client</p>\n" +
                "    \t<form name=\"createClient\" method=\"post\" action=\"createClient\">\n" +
                "    \t\tLogin:<input type=\"text\" name=\"clientName\">\n" +
                "    \t\t<input type=\"submit\" value=\"Create\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"find\">\n" +
                "    \t<p>Find client by id</p>\n" +
                "    \t<form name=\"findClient\" method=\"post\" action=\"findClient\">\n" +
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToFind\">\n" +
                "    \t\t<input type=\"submit\" value=\"Find\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"findAll\">\n" +
                "        <p>Show all clients</p>\n" +
                "    \t<form name=\"findAllClients\" method=\"post\" action=\"findAllClients\">\n" +
                "    \t\t<input type=\"submit\" value=\"Show\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"update\">\n" +
                "    <p>Update client</p>\n" +
                "    \t<form name=\"updateClient\" method=\"post\" action=\"updateClient\">\n" +
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToUpdate\">\n" +
                "    \t\t</br>\n" +
                "    \t\tNew login:<input type=\"text\" name=\"clientLoginToUpdate\">\n" +
                "    \t\t<input type=\"submit\" value=\"Update\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"delete\">\n" +
                "    <p>Delete client</p>\n" +
                "    \t<form name=\"deleteClient\" method=\"post\" action=\"deleteClient\">\n" +
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToDel\">\n" +
                "    \t\t<input type=\"submit\" value=\"Delete\">\n" +
                "    \t</form>\t\n" +
                "    </div>\n" +
                "    <hr>\n" + TextHolder.END_OF_PAGE);
    }
}
