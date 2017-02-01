package com.andersen.controllers;

public class TextHolder {
    static final String END_OF_PAGE = "</body>\n</html>";
    static final String TABLE_SELECTION = "<!DOCTYPE html>\n" +
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
            "</div>\n";
}
