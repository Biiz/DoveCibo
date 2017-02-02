<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Pagina per il recupero delle credenziali
--%>

<!DOCTYPE HTML>
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
        <form name="Verifica_credenziali" method="post" action="ServletRecuperoCredenziali">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10">
                        <div id="tagline">
                            <h1>Recupero credenziali</h1>
                        </div>
                        <!-- 1° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="basic-url">Inserire email:</label>
                                <div class="form-group">
                                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                                </div>
                                <label for="basic-url">Il nickname e la password le saranno inviate via email</label>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Invia</button>
                                <button onclick="window.location.href = 'home.jsp'" type="reset" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                <button type="reset" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-remove"></span> Cancella Campo</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                </div>
            </div>
        </form>
    </body>
</html>
