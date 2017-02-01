package com.andersen.controllers;

import com.andersen.domain.Product;
import com.andersen.service.ProductService;
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

public class CreateProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start CreateProductServlet doPost.");
        String productName = req.getParameter("productNameCr");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String tables = TextHolder.TABLE_SELECTION + "</br>\n</br>\n" + TextHolder.END_OF_PAGE;
        Integer price;
        out.println(tables);
        try {
            price = Integer.parseInt(req.getParameter("productPriceCr"));
        } catch (NumberFormatException e ) {
            out.println("</br>");
            out.println("The products price must by a number.");
            e.printStackTrace();
            return;
        }
        if ((productName == null)||(productName.equals(""))) {
            out.println("</br>");
            out.println("The product mast have a name.");
            return;
        }
        if((price == null)||(price == 0)) {
            out.println("</br>");
            out.println("The product mast have prise bigger then 0.");
            return;
        }
        List<Product> products = service.findAll();
        for (Product p : products) {
            if (p.getProductName().equals(productName)) {
                out.println("</br>");
                out.println("Product with this name is already exist.");
                return;
            }
        }
        Product newProduct = new Product(productName, price);
        service.create(newProduct);
        out.println("</br>");
        out.println("Product is added.");
    }
}
