package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Client;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
import com.andersen.service.ClientService;
import com.andersen.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatedCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService)context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        CartService cartService = (CartService)context.getBean("cartService");
        Integer clientId = Integer.parseInt(req.getParameter("clientId"));
        String selectedProducts = req.getParameter("allProducts");
        Client client = clientService.findById(clientId);
        List<Product> allProducts = productService.findAll();
        Cart cart = new Cart();
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
        if(selectedProducts.equals(""))  {
            page += "<p>Cant create cart without products.</p>";
            page += "</body>\n" +
                    "</html>";
            out.println(page);
            return;
        }
        cart.setClient(client);
        List<String> products = Arrays.asList(selectedProducts.split(","));
        List<Product> cartProducts = new ArrayList<Product>();
        for (String productName : products) {
            for (Product p : allProducts) {
                if(p.getProductName().equals(productName)) {
                    cartProducts.add(p);
                }
            }
        }
        cart.setProducts(cartProducts);
        cartService.create(cart);
        page += "<p>Cart is added.</p>";
        page += "</body>\n" +
                "</html>";
        out.println(page);
    }
}
