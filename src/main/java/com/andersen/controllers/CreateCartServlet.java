package com.andersen.controllers;

import com.andersen.domain.Product;
import com.andersen.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CreateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService productService = (ProductService)context.getBean("productService");
        List<Product> products = productService.findAll();
        PrintWriter out = resp.getWriter();
        String page = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Create order</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        .line {\n" +
                "            float: left;\n" +
                "            margin-left: 2px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"container\">\n" +
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
                "</div>\n" +
                "<form method=\"post\" action=\"servlet\">\n" +
                "</br>\n" +
                "</br>\n" +
                "    Operation:<br>\n" +
                "    <select name=\"operation\">\n";
        for (Product p : products) {
            page = page + "<option value=\"" + p.getProductName() +"\">" + p.getProductName() +"</option>\n";
        }

        page = page + "        <option value=\"addition\">addition</option>\n" +
                "        <option value=\"subtraction\">subtraction</option>\n" +
                "        <option value=\"multiplication\">multiplication</option>\n" +
                "        <option value=\"division\">division</option>\n" +
                "    </select>\n" +
                "    <input type=\"submit\" value=\"Submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
        out.println(page);
    }
}
