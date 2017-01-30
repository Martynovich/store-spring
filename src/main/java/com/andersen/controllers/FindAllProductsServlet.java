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

public class FindAllProductsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String tables = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Client add</title>\n" +
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
                "</br>\n" +
                "</br>\n" +
                "</body>\n" +
                "</html>";
        out.println(tables);
        List<Product> products = service.findAll();
        if (products == null) {
            out.println("No products.");
            return;
        }
        for(Product product : products) {
            out.println("Product id - " + product.getId() + ", product login - " + product.getProductName()
                    + ", product price - " + product.getProdutPrice());
            out.println("</br>");
        }
    }
}
