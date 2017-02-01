package com.andersen.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start CartServlet doGet.");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println(TextHolder.TABLE_SELECTION +
                "    </br>\n" +
                "    <p>Cart page</p>\n" +
                "    <hr>\n" +
                "    <div id=\"create\">\n" +
                "    \t<p>Create cart</p>\n" +
                "    \t<form name=\"createCart\" method=\"post\" action=\"createCart\">\n" +
                "    \t\tClient id:<input type=\"text\" name=\"clientIdToCr\">\n" +
                "    \t\t<input type=\"submit\" value=\"Create\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"find\">\n" +
                "    \t<p>Find cart by id</p>\n" +
                "    \t<form name=\"findCart\" method=\"post\" action=\"findCart\">\n" +
                "    \t\tCart id:<input type=\"text\" name=\"cartIdToFind\">\n" +
                "    \t\t<input type=\"submit\" value=\"Find\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"findAll\">\n" +
                "        <p>Show all carts</p>\n" +
                "    \t<form name=\"findAllCarts\" method=\"post\" action=\"findAllCarts\">\n" +
                "    \t\t<input type=\"submit\" value=\"Show\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"update\">\n" +
                "    <p>Update cart</p>\n" +
                "    \t<form name=\"updateCart\" method=\"post\" action=\"updateCart\">\n" +
                "    \t\tCart id:<input type=\"text\" name=\"cartIdToUpdate\">\n" +
                "    \t\t<input type=\"submit\" value=\"Update\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"delete\">\n" +
                "    <p>Delete cart</p>\n" +
                "    \t<form name=\"deleteCart\" method=\"post\" action=\"deleteCart\">\n" +
                "    \t\tCart id:<input type=\"text\" name=\"cartIdToDel\">\n" +
                "    \t\t<input type=\"submit\" value=\"Delete\">\n" +
                "    \t</form>\t\n" +
                "    </div>\n" +
                "    <hr>\n" + TextHolder.END_OF_PAGE);
    }
}
