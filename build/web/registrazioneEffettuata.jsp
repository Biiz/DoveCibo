<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="navBar.html" %>
        <title>DoveCibo</title>
    </head>
    <body>
        <br><br><br>
        <div class="container">  
            <div class="alert alert-success " role="alert">
                <!-- riceve l'attributo dalla servletRegistrazione-->
                <div id="tagline">
                    <h1>Registrazione effettuata con successo</h1>
                    <h2>manca la convalida con l'email</h2>
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
