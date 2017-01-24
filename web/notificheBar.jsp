<%@page import="DoveCiboPK.Notifica"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            body {
                
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.75);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
        </style>
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
                                <th><p style="color: black; font-size: 20px"><b>Nickname</b></p></th>
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
