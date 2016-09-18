<%-- 
    Document   : profiloUtente
    Created on : 18-set-2016, 16.49.45
    Author     : IO-PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>ModificaAccount</title>
        <%@ include file="navBar.html" %>  
    </head>
    <body>
        
        <script>
            
            //se il form è vuoto viene settato un valore di default
            function first_nameFill() {
                var x = document.getElementById("first_name");
                if(x.value==""){
                    x.value="NomeUtente";
                }
            }
            
            function last_nameFill() {
                var x = document.getElementById("last_name");
                if(x.value==""){
                    x.value="CognomeUtente";
                }
            }
            
            function emailFill() {
                var x = document.getElementById('email')
                if(x.value==""){
                    x.value="emailUtente@example.it";
                }
            }
            
            function nicknameFill() {
                var x = document.getElementById('nickname')
                if(x.value==""){
                    x.value="nicknameUtente";
                }
            }
        </script> 
        
        <form name="loginForm"  action="Servlet?" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-2 col-xs-1"></div>
                    <div class="col-md-6 col-sm-8 col-xs-10">
                        <div id="tagline">
                            <h1>Modifica Account</h1>
                        </div>
                        
                        <!-- 1° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>First name:</label>  
                                    <input type="text" id="first_name" onchange="first_nameFill()" class="form-control" name="first_name" pattern=".{3,255}" title="Il tuo nome" value="NomeUtente">
                                    <script>
                                        
                                    </script>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Last name:</label>          
                                    <input type="text" id="last_name" onchange="last_nameFill()" class="form-control" name="last_name" pattern=".{3,255}" title="Il tuo cognome" value="CognomeUtente">
                                </div>
                            </div>
                        </div>
                        
                        <!-- 2° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="basic-url">Email:</label>
                                <div class="form-group">
                                    <input type="email" id="email" onchange="emailFill()" class="form-control" name="email" placeholder="Email" pattern=".{3,255}" title="La tua email!" value="emailUtente@example.it">
                                </div>
                            </div>
                        </div>
                        
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        
                        <!-- 3° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nickname:</label>          
                                    <input type="text" id="nickname" onchange="nicknameFill()" class="form-control" name="nickname" placeholder="Nickname" pattern=".{3,255}" title="Inserisci il nickname!" value="nicknameUtente">
                                </div>
                            </div>
                            
                        </div>
                       
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        
                        <!-- 5° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Inserisci la vecchia password:</label>          
                                    <input type="password" class="form-control" name="password" placeholder="Password" pattern=".{3,255}" title="Inserisci la password!">
                                </div>
                            </div>
                        </div>
                        
                        <!-- 6° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Inserisci la nuova password:</label>          
                                    <input type="password" class="form-control" name="password" placeholder="Password" pattern=".{3,255}" title="Inserisci la password!">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Inserisci nuovamente la password:</label>
                                <div class="form-group">
                                    <input type="password" class="form-control" name="password" placeholder="Password" pattern=".{3,255}" title="Inserisci la password!">
                                </div>
                            </div>
                        </div>
                        
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        
                         <!-- 4° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success btn-lg pull-right"><span class="glyphicon glyphicon glyphicon-ok"></span> Conferma</button>
                                <button onclick="window.location.href = 'home.jsp'" type="reset" class="btn btn-sm btn-danger"><span class="glyphicon glyphicon-backward"></span> Annulla</button>
                                <button type="reset" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-remove"></span> Resetta Campi</button>
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
