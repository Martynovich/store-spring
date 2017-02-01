package com.andersen.controllers;

import com.andersen.domain.Cart;
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

public class DeleteCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start DeleteCartServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService) context.getBean("cartService");
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION;
        Integer cartId ;
        try{
            cartId = Integer.parseInt(req.getParameter("cartIdToDel"));
        } catch (Exception e) {
            page += "</br><p>Incorrect cart id input.Please enter number.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        Cart cart = cartService.findById(cartId);
        if (cart == null) {
            page += "</br><p>Cart with this id does not exist.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        cartService.delete(cart);
        page += "</br><p>Cart deleted.</p>" + TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
