package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
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

public class FindAllCartsServlet extends HttpServlet{

    private static final Logger logger = Logger.getLogger(FindAllCartsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start FindAllCartsServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService service = (CartService)context.getBean("cartService");
        List<Cart> carts = service.findAll();
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION + "</br>\n<hr>\n";
        if(carts.isEmpty()) {
            page += "<p>No current carts.</p>";
            page += TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        for (Cart cart : carts) {
            page += "<p>Cart id - " + cart.getId() + " client id - " + cart.getClient().getId() + " order date - "
                    + cart.getDateOfCreation() + "</p>";
            List<Product> products = cart.getProducts();
            page += "<p>Products in cart.</p>";
            if (products.isEmpty()) {
                page += "<p>Cart is empty.</p>";
            }
            for (Product product : products) {
                page += "<p>Product id - " + product.getId() + ", product login - " + product.getProductName()
                        + ", product price - " + product.getProdutPrice() + "</p>";
            }
            page += "<hr>";
        }
        page += TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
