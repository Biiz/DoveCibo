<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Notifica dell’accettazione di una risposta di un  ristoratore ad un commento da parte dell’Admin
--%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
        <br><br><br>
        <div class="container">  
            <div class="alert alert-success " role="alert">
                
                <div id="tagline">
                    <h1>Accetta risposta</h1>
                    <h2>Conferma di risposta avvenuta con successo!</h2>
                </div>

                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>

                <!-- 1° row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <form method="GET" action="ServletNotifiche" >
                            <button class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon-tags" type="submit"></span> Torna alle notifiche</button>
                        </form>
                        <button class="btn btn-sm btn-default" onclick="window.location.href = 'home.jsp'"><span class="glyphicon glyphicon-home"></span> Torna alla home</button>
                    </div>
                </div>
            </div>
        </div><!-- fine container-->
    </body>
</html>
