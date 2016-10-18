<%@page import="DoveCiboPK.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DoveCibo</title>

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
    <body>
        <%@ include file="navBar.jsp" %>
        <div class="container">
            <div class="row" style="margin-top: 25%;">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form>
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>

                        <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                            <input type="text" class="form-control" placeholder="Cerca un ristorante">
                            <span class="input-group-btn">
                                <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                <button class="btn btn-success" type="button" onclick="window.location.href = 'ristoranti.jsp'">Go!</button> 
                        </div> 
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
        </div>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
