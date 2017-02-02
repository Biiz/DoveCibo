<%@page import="users.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Pagina per modificare i dati del profilo di un utente
--%>
<!DOCTYPE HTML>
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
        <%
            session = request.getSession(false);
            if(session != null && session.getAttribute("User") != null){
                User user = (User) session.getAttribute("User");
        %> 
        
        <form name="loginForm"  action="UserUpdate" method="post">
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
                                    <label>Nome:</label>  
                                    <input type="text" id="first_name" class="form-control" name="first_name" pattern=".{3,255}" title="Il tuo nome" value="<%=user.getName() %>" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Cognome:</label>          
                                    <input type="text" id="last_name" class="form-control" name="last_name" pattern=".{3,255}" title="Il tuo cognome" value="<%=user.getSurname() %>" required>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 2° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="basic-url">Email:</label>
                                <div class="form-group">
                                    <input type="email" id="email" class="form-control" name="email" pattern=".{3,255}" title="La tua email!" value="<%=user.getEmail() %>" required>
                                </div>
                            </div>
                        </div>
                        
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        
                        <!-- 3° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nickname:</label>          
                                    <input type="text" id="nickname" class="form-control" name="nickname" pattern=".{3,255}" title="Inserisci il nickname!" value="<%=user.getNickname() %>" disabled>
                                </div>
                            </div>
                            
                        </div>
                       
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                       
                        <!-- 6° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Password:</label>          
                                    <input type="password"  id="password" class="form-control" name="password" pattern=".{8,255}" title="Inserisci la password che sia almeno di 8 caratteri" value="<%=user.getPassword() %>" required>
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
    <%
        }
    %>
    </body>
