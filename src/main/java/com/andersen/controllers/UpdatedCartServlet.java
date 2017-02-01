package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Client;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
import com.andersen.service.ClientService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdatedCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdatedCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start UpdatedCartServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        CartService cartService = (CartService) context.getBean("cartService");
        Integer cartId = Integer.parseInt(req.getParameter("cartId"));
        Integer newClientId;
        String selectedProducts = req.getParameter("allProducts");
        List<Product> allProducts = productService.findAll();
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION + "</br>\n";
        try {
            newClientId = Integer.parseInt(req.getParameter("newClientId"));
        } catch (Exception e ) {
            page += "</br><p>Incorrect client id input.Please enter number.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        Client newOwner = clientService.findById(newClientId);
        if(newOwner == null) {
            page += "</br><p>Client with this id does not exist.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        if(selectedProducts.equals(""))  {
            page += "<p>Can't create cart without products.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        Cart cart = cartService.findById(cartId);
        cart.setClient(newOwner);
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
        cartService.update(cart);
        page += "<p>Cart is updated.</p>" + TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
