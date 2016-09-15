<%-- 
    Document   : form
    Created on : May 20, 2016, 2:06:08 PM
    Author     : Michael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Registrazione</title>
        <%@ include file="navBar.html" %>  
    </head>
    <body>
        <form name="loginForm"  action="ServletRegistrazione" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10">
                        <div id="tagline">
                            <h1>Register Form</h1>
                        </div>
                        <!-- 1° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>First name:</label>          
                                    <input type="text" class="form-control" name="first_name" placeholder="First name" pattern=".{3,255}" title="Inserisci il nome!" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Last name:</label>          
                                    <input type="text" class="form-control" name="last_name" placeholder="Last name" pattern=".{3,255}" title="Inserisci il cognome!" required>
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
                            <div class="col-md-6">
                                <label>Password:</label>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="password" placeholder="Password" pattern=".{3,255}" title="Inserisci la password!" required>
                                </div>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <!-- 4° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Terms of use:</label>
                                    <div style="border: 1px solid #e5e5e5; height: 200px; overflow: auto; padding: 10px;">
                                        <p><b>Vendo l'anima</b> e rifiuto la salvezza.</p>
                                        <p>Mea culpa e solo mea per dannarme la <b>vida loca.</b></p>
                                        <p>Altri bla molto lunghi perchè devo mostrare che il riquadro ha la barretta di navigazione laterale:</p>
                                        <p><b>“Cool Story, Bro”</b> is a catchphrase often seen in image macros as a sarcastic response on message boards or in comments to posts that are deemed boring, pointless or too long to read.</p>
                                        <p>Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness. Repeating this is pure lazyness.</p>
                                    </div>
                                </div>
                                <div class="checkbox pull-right">
                                    <label><input type="checkbox" id="checkbox" required>Accept Terms and Conditions.</label>
                                </div>
                            </div>
                        </div>
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Sign in</button>
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
