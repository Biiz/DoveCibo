<%-- 
    Document   : profiloUtente
    Created on : 18-set-2016, 16.49.45
    Author     : IO-PC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

    <head>
        <title>ModificaAccount</title>
        <%@ include file="navBar.jsp" %>  
    </head>
    <body>    
    
        <%
            Cookie cookiess[] = request.getCookies();
            if(cookiess != null && cookiess.length > 1){
                DoveCiboPK.DB_Manager db = new DoveCiboPK.DB_Manager ();
                String nickName = cookiess[1].getName();
                DoveCiboPK.User u = new DoveCiboPK.User (-1,"","",nickName,"","","");
                db.CheckProfilo(u);
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
                                    <label>First name:</label>  
                                    <input type="text" id="first_name" class="form-control" name="first_name" pattern=".{3,255}" title="Il tuo nome" value="<%=u.getName()%>" required>
                                    <script>
                                        
                                    </script>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Last name:</label>          
                                    <input type="text" id="last_name" class="form-control" name="last_name" pattern=".{3,255}" title="Il tuo cognome" value="<%=u.getSurname()%>" required>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 2° row-->
                        <div class="row">
                            <div class="col-md-12">
                                <label for="basic-url">Email:</label>
                                <div class="form-group">
                                    <input type="email" id="email" class="form-control" name="email" pattern=".{3,255}" title="La tua email!" value="<%=u.getEmail()%>" required>
                                </div>
                            </div>
                        </div>
                        
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                        
                        <!-- 3° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nickname:</label>          
                                    <input type="text" id="nickname" class="form-control" name="nickname" pattern=".{3,255}" title="Inserisci il nickname!" value="<%=nickName%>" disabled>
                                </div>
                            </div>
                            
                        </div>
                       
                        <hr align=”left” size=”1″ width=”300″ color=”grey” noshade>
                       
                        <!-- 6° row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>password:</label>          
                                    <input type="password"  id="password" class="form-control" name="password" pattern=".{3,255}" title="Inserisci la password!" value="<%=u.getPassword()%>" required>
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

