<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <form name="loginForm"  action="ServletRegistrazione1" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10">
                        <div id="tagline">
                            <h1>Form di registrazione</h1>
                        </div>
                        <!-- 1° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nome:</label>          
                                    <input type="text" class="form-control" name="first_name" placeholder="Nome" pattern=".{3,255}" title="Inserisci il nome!" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Cognome:</label>          
                                    <input type="text" class="form-control" name="last_name" placeholder="Cognome" pattern=".{3,255}" title="Inserisci il cognome!" required>
                                </div>
                            </div>
                        </div>
                        <!-- 2° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="basic-url">Email:</label>
                                <div class="form-group">
                                    <input type="email" class="form-control" name="email" placeholder="Email" pattern=".{3,255}" title="Inserisci l'email!" required>
                                </div>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <!-- 3° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nickname:</label>          
                                    <input type="text" class="form-control" name="nickname" placeholder="Nickname" pattern=".{3,255}" title="Inserisci il nickname!" required>
                                </div>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <!-- 4° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Termini d'uso:</label>
                                    <div style="border: 1px solid #e5e5e5; height: 200px; overflow: auto; padding: 10px;">
                                        <p><b>Vendo l'anima</b> e rifiuto la salvezza.</p>
                                        <p>Mea culpa e solo mea per dannarme la <b>vida loca.</b></p>
                                        <p>Altri bla molto lunghi perchè devo mostrare che il riquadro ha la barretta di navigazione laterale:</p>
                                        <p><b>“Cool Story, Bro”</b> is a catchphrase often seen in image macros as a sarcastic response on message boards or in comments to posts that are deemed boring, pointless or too long to read.</p>
                                        <p>Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness.</p>
                                    </div>
                                </div>
                                <div class="checkbox pull-right">
                                    <label><input type="checkbox" id="checkbox" required>Accetto Termini e Condizioni d'uso</label>
                                </div>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Registrati</button>
                                <button onclick="window.location.href = 'home.jsp'" type="reset" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                <button type="reset" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-remove"></span> Cancella Campi</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                </div>
            </div>
        </form>
        <br>
        <br>
        <br>
    </body>
</html>
