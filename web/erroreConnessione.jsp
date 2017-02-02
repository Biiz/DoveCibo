<%--
    Notifica di un possibile errore nella connessione al database
--%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script type="text/javascript" src="/DoveCiboGit/script/goback_window.js"></script>
        <script type="text/javascript" src="/DoveCiboGit/script/goback_location.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
        <br><br><br>
        <div class="container">  
            <div class="alert alert-warning" role="alert">
                <!-- riceve l'attributo dalla servletRegistrazione-->
                <div id="tagline">
                    <h1>Impossibile connettersi al server DBMS</h1>
                </div>

                <hr align=?left? size=?1? width=?300? color=?grey? noshade>

                <!-- 1� row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-success btn-lg pull-right" onclick="window.location.href = 'home.jsp'"><span class="glyphicon glyphicon-home"></span> Torna alla home</button>
                        <button class="btn btn-sm btn-info" onclick="goBack()"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                    </div>
                </div>
            </div>
        </div><!-- fine container-->
    </body>
</html>
