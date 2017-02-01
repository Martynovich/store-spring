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

public class FindCartServlet extends HttpServlet{

    private static final Logger logger = Logger.getLogger(FindCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start FindCartServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService)context.getBean("cartService");
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION + "</br>\n";
        Integer cartId ;
        try {
            cartId = Integer.parseInt(req.getParameter("cartIdToFind"));
        } catch (Exception e) {
            page += "<p>Incorrect input. Please enter number.</p>";
            page += TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        Cart cart = cartService.findById(cartId);
        if (cart == null) {
            page += "<p>Cart with this id does not exist.</p>";
            page += TextHolder.END_OF_PAGE;
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
        page += TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
