<%-- 
    Document   : password_dimenticata
    Created on : May 20, 2016, 4:26:27 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Recupera Credenziali</title>
        <%@ include file="navBar.jsp" %>
        <script>
            function validateForm() {
                var x = document.forms["loginForm"]["mail"].value;
                if (x == null || x == "") {
                    alert("inserire l'email");
                    return false;
                }
            }
        </script>   
    </head>
    <body>
        <form name="Verifica_credenziali" method="post" action="SendEmail.jsp" onsubmit="return validateForm()">
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
                                    <input type="text" class="form-control" name="email" placeholder="Email">
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
