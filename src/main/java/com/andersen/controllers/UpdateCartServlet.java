package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
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

public class UpdateCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start UpdateCartServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService)context.getBean("cartService");
        ProductService productService = (ProductService)context.getBean("productService");
        List<Product> products = productService.findAll();
        Integer cartId;
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION;
        try {
            cartId = Integer.parseInt(req.getParameter("cartIdToUpdate"));
        } catch (Exception e ) {
            page += "</br><p>Incorrect input.Please enter number.</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        Cart cart = cartService.findById(cartId);
        if(cart == null) {
            page += "</br><p>Cart with this id does not exist..</p>" + TextHolder.END_OF_PAGE;
            out.println(page);
            return;
        }
        StringBuilder cartProductsStringBuilder = new StringBuilder();
        for(Product p : cart.getProducts()) {
            cartProductsStringBuilder.append(p.getProductName());
            cartProductsStringBuilder.append(",");
        }
        String cartProductsString = cartProductsStringBuilder.toString();
        page +=  "<form  id=\"upCart\" name=\"UpdateOrder\" method=\"post\" action=\"/updatedCart\">\n" +
                "</br>\n" +
                "<p>Enter new client id. Current client id - " + cart.getClient().getId() + ". For hold current client, enter current client id.</p>\n" +
                "New client id:<input type=\"text\" name=\"newClientId\">\n" +
                "</br>\n" +
                "</br>\n" +
                "    Select product:<br>\n" +
                "    <select name=\"product\" onchange=\"selectProduct (this.value)\">";
        for (Product p : products) {
            page = page + "<option value=\"" + p.getProductName() +"\">" + p.getProductName() + " price:" + p.getProdutPrice() + "</option>\n";
        }



        page += " </select>\n" +
                "    <input type=\"button\" value=\"add\" onclick=\"addClick ()\"/>\n" +
                "    <input type=\"submit\" value=\"Update cart\" onclick=\"updateCart ()\">\n" +
                "\t<input type=\"hidden\" name=\"allProducts\" value=\"" + cartProductsString + "\"/>\n" +
                "\t<input type=\"hidden\" name=\"cartId\" value=\"" + cart.getId() + "\"/>\n" +
                "</form>\n" +
                "<div id=\"products\">";
        for(Product p : cart.getProducts()){
            page += "<div class=\""+ p.getProductName() +"\">" + p.getProductName() +"<input value=\"del\" onclick=\"delProd (this)\" type=\"button\"></div>";
        }

        page += "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    \tvar product = document.getElementsByName('product')[0].value;\n" +
                "    \tvar products = document.getElementsByName('allProducts')[0];\n" +
                "    \tvar delBtn = \"<input type=\\\"button\\\" value=\\\"del\\\" onclick=\\\"delProd (this)\\\"/>\";\n" +
                "    \tfunction selectProduct (p) {\n" +
                "    \t\tproduct = p;\n" +
                "    \t}\n" +
                "    \tfunction addClick () {\n" +
                "    \t\tvar parentElement = document.getElementById(\"products\");\n" +
                "    \t\tvar div = document.createElement('div');\n" +
                "    \t\tdiv.className = product;\n" +
                "    \t\tdiv.innerHTML += product;\n" +
                "    \t\tdiv.innerHTML += delBtn;\n" +
                "    \t\tparentElement.appendChild(div);\n" +
                "    \t\tproducts.value += (product + ','); \n" +
                "    \t}\n" +
                "    \tfunction delProd (btn) {\n" +
                "    \t\tvar newCart = products.value;\n" +
                "    \t\tproducts.value = newCart.replace((btn.parentNode.className + \",\"), \"\");\n" +
                "    \t\tbtn.parentNode.remove();\n" +
                "\n" +
                "    \t}\n" +
                "    \tfunction updateCart () {\n" +
                "    \t\tdocument.getElementById('upCart').submit();\n" +
                "    \t}\n" +
                "    </script>\n" + TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
