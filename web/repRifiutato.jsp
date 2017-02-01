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
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

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
                    <h1>Errore inserimento risposta</h1>
                    <h2>non e' possibile inserire la risposta al commento in quanto hai gia' risposto al commento</h2>
                </div>

                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>

                <!-- 1° row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-sm btn-default" onclick="window.location.href = 'home.jsp'" ><span class="glyphicon glyphicon-home"></span> Torna alla Home</button>
                        <button class="btn btn-sm btn-info" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Indietro</button>
                        <!-- script per tornare indietro di pagina nel browser-->
                        <script>
                            function goBack() {
                                location.replace(document.referrer);
                            }
                        </script>
                    </div>
                </div>
            </div>
        </div><!-- fine container-->
    </body>
</html>
