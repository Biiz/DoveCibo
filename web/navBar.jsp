<div class="navbar navbar-inverse navbar-static-top navbar-fixed-top" role="navigation">

            <!-- bottone menù che compare quando la finestra è piccola-->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home.jsp">DoveCibo</a>
            </div>

            <!-- il contenuto del menù -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <%
                        if(request.getSession(false) == null || request.getSession(false).getAttribute("User") == null) {
                            %>
                            <!-- registrati -->
</a><li style="padding-right: 15px;" ><a href="aggiungiUtente.jsp"><span class="glyphicon glyphicon-pencil"></span><b> Registrati </b></a></li>
<!-- bottone che puppa la finestrella per accedere-->
<li><a href="#" data-toggle="modal" data-target="#accedi" style="padding-right: 35px;"><span class="glyphicon glyphicon-log-in"></span> Accedi</a></li>     
</ul> 
</div><!-- fine menù -->
</div><!-- fine navBar -->
                    <%        
                        }else{
                        DoveCiboPK.User user = (DoveCiboPK.User) request.getSession(false).getAttribute("User");
                                if (user.getRole().equals("1")) { 
                                        
                                                        %>
                    <!-- bottone che puppa la finestrella delle notifiche-->
                    <li><a href="#" data-toggle="modal" data-target="#notifiche" style="padding-right: 15px;"><span class="glyphicon glyphicon-tags"></span> Notifiche</a></li>

                    <!-- Nome e Cognome dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 35px;"><span class="glyphicon glyphicon-user"></span> <%=user.getName() %> <%=user.getSurname() %> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="profiloUtente.jsp"><span class="glyphicon glyphicon-cog" disabled></span> Profilo</a></li>
                            <li role="separator" class="divider"></li>
                            <form class="form" action="ExitProfilo" method="post">
                                <div class="row text-center">
                                <li><button href="home.jsp" type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Esci </button></li>
                                </div>
                            </form>
                        </ul>
                    </li>
                </ul> 
            </div><!-- fine menù -->
        </div><!-- fine navBar -->
        <%
            }
else if (user.getRole().equals("2")) {
        %>
        <!-- bottone che puppa la finestrella delle notifiche-->
    <li><a href="#" data-toggle="modal" data-target="#notifiche"><span class="glyphicon glyphicon-tags" style="padding-right: 15px;"></span> Notifiche</a></li>
    <!-- Ristorante dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 15px;"><span class="glyphicon glyphicon-cutlery"></span> Ristorante <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="aggiungiRistorante.jsp"><span class="glyphicon glyphicon-plus"></span> Aggiungi ristorante</a></li>
            <li role="separator" class="divider"></li>
            <form action="VisualizzaRistorantiUtente2" method="post">
                <div class="row text-center">
                    <li><button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-eye-open"></span> i tuoi ristoranti</button></li>
                </div>
            </form>
        </ul>
    </li>
    <!-- Nome e Cognome dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 35px;"><span class="glyphicon glyphicon-user"></span> <%=user.getName() %> <%=user.getSurname() %> <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="profiloUtente.jsp"><span class="glyphicon glyphicon-cog"></span> Profilo</a></li>
            <li role="separator" class="divider"></li>
            <form class="form" action="ExitProfilo" method="post">
                <div class="row text-center">
                <li><button href="home.jsp" type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Esci </button></li>
                </div>
            </form>
        </ul>
    </li>
</ul> 
</div><!-- fine menù -->
</div><!-- fine navBar -->
<%
    }
else if (user.getRole().equals("3")){
%>
  <!-- Ristorante dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 15px;"><span class="glyphicon glyphicon-cutlery"></span> Ristorante <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="aggiungiRistorante.jsp"><span class="glyphicon glyphicon-plus"></span> Aggiungi ristorante</a></li>
        </ul>
    </li>
<!-- Nome e Cognome dropdown -->

<li class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 35px;"><span class="glyphicon glyphicon-user"></span> <%=user.getName() %> <%=user.getSurname() %> <span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li><a href="profiloUtente.jsp"><span class="glyphicon glyphicon-cog"></span> Profilo</a></li>
        <li role="separator" class="divider"></li>
        <form class="form" action="ExitProfilo" method="post">
            <div class="row text-center">
                <li><button href="home.jsp" type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-log-out"></span> Esci </button></li>
            </div>
        </form>
    </ul>
</li>
</ul> 
</div><!-- fine menù -->
</div><!-- fine navBar -->
<%
        }
%>


<div class="modal fade" id="notifiche" role="dialog">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="embed-responsive embed-responsive-4by3">
                            <iframe class="embed-responsive-item" src="ServletNotificheBar?idU=<%= user.getId() %>"></iframe>
                        </div>
                    
                    </div>
                </div>   
            </div>
            <div class="modal-footer">
                <div class="bottom text-center">
                    <form method="GET" action="ServletNotifiche" >
                        <button class="btn btn-info btn-justified" type="submit">Vedi tutte le notifiche</button>
                    </form>
                </div>
            </div>
        </div>   
    </div>
</div> <!-- fine modal notifiche -->



<%
}
%>

<!-- Modal accedi-->
<div class="modal fade" id="accedi" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></button>
            </div>
            <div class="modal-body">
                <div class="row" style="">
                    <div class="col-md-12">
                        <form class="form" action="ServletLogin" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                                    <input type="text" class="form-control" name="nickname" placeholder="Nickname" value="user">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </div>
                                        <input type="password" class="form-control" name="password" placeholder="Password" value="user">
                                    </div>
                                </div>
                                <div class="help-block text-right">
                                    <a href="recupero_credenziali.jsp">Password dimenticata ?</a>
                                </div>
                            </div>

                            <button type="reset" class="btn btn-sm btn-warning">Clear</button>
                            <button href="home.jsp" type="submit" class="btn btn-success pull-right">Log in</button>
                        </form>
                    </div>
                </div>    
            </div>
            <div class="modal-footer">
                <div class="bottom text-center">
                    Prima volta ? <a href="aggiungiUtente.jsp"><span class="glyphicon glyphicon-pencil"></span><b> Registrati !</b></a>
                </div>
            </div>
        </div>   
    </div>
</div> <!-- fine modal accedi -->

<!-- Modal notifiche-->

