package com.andersen.controllers;

import com.andersen.domain.Client;
import com.andersen.domain.Product;
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
import java.util.List;

public class CreateCartServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start CreateCartServlet doPost.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService)context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        Integer clientId = Integer.parseInt(req.getParameter("clientIdToCr"));
        List<Client> clients = clientService.findAll();
        List<Product> products = productService.findAll();
        boolean isClientExist = false;
        PrintWriter out = resp.getWriter();
        String page = TextHolder.TABLE_SELECTION;
        for (Client c : clients) {
            if (c.getId() == clientId) {
                isClientExist = true;
            }
        }
        if(!isClientExist) {
            page += TextHolder.END_OF_PAGE;
            out.println(page);
            out.println("</br>");
            out.println("</br>");
            out.println("Client with this id does not exist.");
            return;
        }
        page +=  "<form  id=\"crCart\" name=\"createOrder\" method=\"post\" action=\"/newCart\">\n" +
                "</br>\n" +
                "</br>\n" +
                "    Select product:<br>\n" +
                "    <select name=\"product\" onchange=\"selectProduct (this.value)\">";
        for (Product p : products) {
            page = page + "<option value=\"" + p.getProductName() +"\">" + p.getProductName() + " price:" + p.getProdutPrice() + "</option>\n";
        }

        page += " </select>\n" +
                "    <input type=\"button\" value=\"add\" onclick=\"addClick ()\"/>\n" +
                "    <input type=\"submit\" value=\"Create cart\" onclick=\"createCart ()\">\n" +
                "\t<input type=\"hidden\" name=\"allProducts\" value=\"\"/>\n" +
                "\t<input type=\"hidden\" name=\"clientId\" value=\"" + clientId + "\"/>\n" +
                "</form>\n" +
                "<div id=\"products\"></div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    \tvar product = document.getElementsByName('product')[0].value;\n" +
                "    \tvar products = document.getElementsByName('allProducts')[0];\n" +
                "    \tproducts.value = \"\";\n" +
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
                "    \tfunction createCart () {\n" +
                "    \t\tdocument.getElementById('crCart').submit();\n" +
                "    \t}\n" +
                "    </script>\n" + TextHolder.END_OF_PAGE;
        out.println(page);
    }
}
