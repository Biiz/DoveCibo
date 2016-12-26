<%-- 
    Document   : provaStampa ALR
    Created on : 26-dic-2016, 15.15.28
    Author     : postal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DoveCiboPK.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                        <%  ArrayList <Restaurant> ALR = (ArrayList <Restaurant>) request.getAttribute("listaRistoranti");
                            for (Restaurant rest : ALR) {
                        %>
                        
                        
                        <%= rest.getName() %>
                        
                        <% } %>
    </body>
</html>
