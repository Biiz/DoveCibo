<%@page isErrorPage="true" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Pagina semplice per gli errori non gestiti
--%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body style="padding-top: 70px;">
        
        <br><br><br>
        <div class="container">  
            <div class="alert alert-warning" role="alert">
                <!-- riceve l'attributo dalla servletRegistrazione-->
                <div id="tagline">
                    <h1>Errore</h1>
                </div>

                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>

                <!-- 1° row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-success btn-lg pull-right" onclick="window.location.href = 'home.jsp'"><span class="glyphicon glyphicon-home"></span> Torna alla home</button>
                    </div>
                </div>
            </div>
        </div><!-- fine container-->
    </body>
</html>
