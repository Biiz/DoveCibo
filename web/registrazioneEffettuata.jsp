<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
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
                <!-- riceve l'attributo dalla servletRegistrazione-->
                <div id="tagline">
                    <h1>Registrazione effettuata con successo</h1>
                    <h2>La password ti è stata inviata per email. Accedi e cambia la password.</h2>
                </div>

                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>

                <!-- 1° row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-success btn-lg pull-right" onclick="window.location.href = 'home.jsp'"><span class="glyphicon glyphicon-home"></span> Torna alla home</button>
                        <button class="btn btn-sm btn-default" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                        <!-- script per tornare indietro di pagina nel browser-->
                        <script>
                            function goBack() {
                                window.history.back();
                            }
                        </script>
                    </div>
                </div>
            </div>
        </div><!-- fine container-->
    </body>
</html>
