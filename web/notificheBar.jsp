<%@page import="notifiche.Notifica"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Popup che mostra un’anteprima delle notifiche presenti
--%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/notifica.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>        
        <%
            ArrayList <Notifica> Notifiche = (ArrayList <Notifica>) request.getAttribute("notifiche");
            ArrayList <Integer> id = (ArrayList <Integer>) request.getAttribute("id_ristoranti");
        %>
        
        <p style="color: black; font-size: 28px"><b>NOTIFICHE</b></p>
        <!-- effettiva tabella delle notifiche -->
        <table class="table">
            <thead>
                <tr>
                    <th><p style="color: black; font-size: 20px"><b>Nome utente</b></p></th>
                    <th><p style="color: black; font-size: 20px"><b>Descrizione</b></p></th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Notifica n : Notifiche) { 
                %>

                <tr class="info">
                    <td style="vertical-align: middle;"> <b><%= n.getUser().getNickname() %></b></td>
                    <td style="vertical-align: middle;"> <%= n.getDescrizione() %></td>

                </tr>

                <%
                    } 
                %>
            </tbody>
        </table>
    </body>
</html>
