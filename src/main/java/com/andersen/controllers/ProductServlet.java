package com.andersen.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Products</title>\n" +
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
                "    <p>Product page</p>\n" +
                "    <hr>\n" +
                "    <div id=\"create\">\n" +
                "    \t<p>Create product</p>\n" +
                "    \t<form name=\"createProduct\" method=\"post\" action=\"createProduct\">\n" +/////////////
                "    \t\tLogin:<input type=\"text\" name=\"productNameCr\"></br>\n" +
                "            Price:<input type=\"text\" name=\"productPriceCr\">\n" +
                "    \t\t<input type=\"submit\" value=\"Create\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"find\">\n" +
                "    \t<p>Find product by id</p>\n" +
                "    \t<form name=\"findProduct\" method=\"post\" action=\"findProduct\">\n" +///////////////
                "    \t\tProduct id:<input type=\"text\" name=\"productIdToFind\">\n" +
                "    \t\t<input type=\"submit\" value=\"Find\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"findAll\">\n" +
                "        <p>Show all products</p>\n" +
                "    \t<form name=\"findAllProducts\" method=\"post\" action=\"findAllProducts\">\n" +//////////
                "    \t\t<input type=\"submit\" value=\"Show\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"update\">\n" +
                "    <p>Update product</p>\n" +
                "    \t<form name=\"updateProducts\" method=\"post\" action=\"updateProduct\">\n" +///////////////
                "    \t\tProduct id:<input type=\"text\" name=\"productIdToUpdate\"></br>\n" +
                "    \t\tNew name:<input type=\"text\" name=\"productNameToUpdate\"></br>\n" +
                "    \t\tNew price:<input type=\"text\" name=\"productPriceToUpdate\">\n" +
                "    \t\t<input type=\"submit\" value=\"Update\">\n" +
                "    \t</form>\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "    <div id=\"delete\">\n" +
                "    <p>Delete product</p>\n" +
                "    \t<form name=\"deleteProduct\" method=\"post\" action=\"deleteProduct\">\n" +/////
                "    \t\tProduct id:<input type=\"text\" name=\"productIdToDel\">\n" +
                "    \t\t<input type=\"submit\" value=\"Delete\">\n" +
                "    \t</form>\t\n" +
                "    </div>\n" +
                "    <hr>\n" +
                "</body>\n" +
                "</html>");

    }
}
