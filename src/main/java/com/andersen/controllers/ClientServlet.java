package com.andersen.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Clients</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        .line {\n" +
                "            float: left;\n" +
                "            margin-left: 2px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"tables\">\n" +
                "        <div class=\"line\">\n" +
                "            <form name=\"checkClient\" method=\"get\" action=\"/client\">\n" +
                "                <input type=\"submit\" value=\"Client\">\n" +
                "            </form>\n" +
                "        </div>\n" +
                "        <div class=\"line\">\n" +
                "            <form name=\"checkProduct\" method=\"get\" action=\"/product\">\n" +
                "                <input type=\"submit\" value=\"Product\">\n" +
                "            </form>\n" +
                "        </div>\n" +
                "        <div class=\"line\">\n" +
                "            <form name=\"checkCart\" method=\"get\" action=\"/cart\">\n" +
                "                <input type=\"submit\" value=\"Cart\">\n" +
                "            </form>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    </div>\n" +
                "    </br>\n" +
                "    <p>Client page</p>\n" +
                "    <hr>\n" +
                "    <div id=\"create\">\n" +
                "    \t<p>Create client</p>\n" +
                "    \t<form name=\"createClient\" method=\"post\" action=\"createClient\">\n" +/////////////////
                "    \t\tLogin:<input type=\"text\" name=\"clientName\">\n" +
                "    \t\t<input type=\"submit\" value=\"Create\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"find\">\n" +
                "    \t<p>Find client by id</p>\n" +
                "    \t<form name=\"findClient\" method=\"post\" action=\"findClient\">\n" +/////////////////////
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToFind\">\n" +
                "    \t\t<input type=\"submit\" value=\"Find\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"findAll\">\n" +
                "        <p>Show all clients</p>\n" +
                "    \t<form name=\"findAllClients\" method=\"post\" action=\"findAllClients\">\n" +/////////////
                "    \t\t<input type=\"submit\" value=\"Show\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"update\">\n" +
                "    <p>Update client</p>\n" +
                "    \t<form name=\"updateClient\" method=\"post\" action=\"updateClient\">\n" +/////////////////
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToUpdate\">\n" +
                "    \t\t</br>\n" +
                "    \t\tNew login:<input type=\"text\" name=\"clientLoginToUpdate\">\n" +
                "    \t\t<input type=\"submit\" value=\"Update\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"delete\">\n" +
                "    <p>Delete client</p>\n" +
                "    \t<form name=\"deleteClient\" method=\"post\" action=\"deleteClient\">\n" + ////////////
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToDel\">\n" +
                "    \t\t<input type=\"submit\" value=\"Delete\">\n" +
                "    \t</form>\t\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "</body>\n" +
                "</html>");
    }
}
