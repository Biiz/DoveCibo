<%@page import="DoveCiboPK.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            body {
                background-image: url('img/img (1)big.jpeg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            h1 {
                /* text-shadow: 5px 5px 13px black; */
            }
            .ombra {
                /* box-shadow: 5px 5px 13px black; */
            }
        </style>
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
        <div class="container">
            <div class="row" style="margin-top: 25%;">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form action="CercaRistorantiHome" method="post">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>

                        <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                            <input type="text" name="go" class="form-control" placeholder="Cerca un ristorante" required>
                            <span class="input-group-btn">
                                <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                <button class="btn btn-success" type="submit">Go!</button> 
                        </div> 
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
        </div>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
