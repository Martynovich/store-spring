package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FindCartServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService)context.getBean("cartService");
        PrintWriter out = resp.getWriter();
        String page = "<!DOCTYPE html>\n" +
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
                "    </br>\n";
        Integer cartId = null;
        try {
            cartId = Integer.parseInt(req.getParameter("cartIdToFind"));
        } catch (Exception e) {
            page += "<p>Incorrect input. Please enter number.</p>";
            page += "</body>\n" +
                    "</html>";
            out.println(page);
            return;
        }
        Cart cart = cartService.findById(cartId);
        if (cart == null) {
            page += "<p>Cart with this id does not exist.</p>";
            page += "</body>\n" +
                    "</html>";
            out.println(page);
            return;
        }
        page += "<p>Cart id - " + cart.getId() + " client id - " + cart.getClient().getId() + " order date - "
                + cart.getDateOfCreation() + "</p>";
        List<Product> products = cart.getProducts();
        page += "<p>Products in cart.</p>";
        for (Product product : products) {
            page += "Product id - " + product.getId() + ", product login - " + product.getProductName()
                    + ", product price - " + product.getProdutPrice() + "</p>";
        }
        page += "</body>\n" +
                "</html>";
        out.println(page);
    }
}
