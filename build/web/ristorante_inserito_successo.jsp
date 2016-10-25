<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DoveCibo</title>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <br><br><br>
        <div class="container">  
            <div class="alert alert-success " role="alert">
                
                <div id="tagline">
                    <h1>Ristorante inserito con successo</h1>
                </div>

                <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>

                <!-- 1° row contenente i bottoni-->
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-success btn-lg pull-right" onclick="window.location.href = 'home.jsp'"><span class="glyphicon glyphicon-home"></span> Torna alla home</button>
                        <form action="VisualizzaRistoranti" method="post">
                            <div class="row text-center">
                                <button type="submit" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-eye-open"></span> Vedi i ristoranti inseriti</button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div><!-- fine container-->

    </body>
</html>
