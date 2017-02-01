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

public class UpdateProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start UpdateProductServlet doPost.");
        Integer id = Integer.parseInt(req.getParameter("productIdToUpdate"));
        String newName = req.getParameter("productNameToUpdate");
        Integer newPrice = Integer.parseInt(req.getParameter("productPriceToUpdate"));
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String tables = TextHolder.TABLE_SELECTION + "</br>\n</br>\n" + TextHolder.END_OF_PAGE;
        out.println(tables);
        List<Product> products = service.findAll();
        for (Product p : products) {
            if (p.getId() == id) {
                p.setProductName(newName);
                p.setProdutPrice(newPrice);
                service.update(p);
                out.println("</br>");
                out.println("Product updated");
                return;
            }
        }
        out.println("</br>");
        out.println("Product with this id does not exist");
    }
}
