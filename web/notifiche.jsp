<%@page import="notifiche.Notifica"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/notifica.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body style="padding-top: 70px;" background="/DoveCiboGit/Sfondi/sfondo.jpg">
        <%@ include file="navBar.jsp" %>      
        <%
            ArrayList <Notifica> Notifiche = (ArrayList <Notifica>) request.getAttribute("notifiche");
            ArrayList <Integer> id = (ArrayList <Integer>) request.getAttribute("id_ristoranti");
        %>      
        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">
                    <p style="color: black; font-size: 28px"><b>NOTIFICHE</b></p>
                    <!-- effettiva tabella delle notifiche -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th><p style="color: black; font-size: 20px"><b>Nickname</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Descrizione</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Gestione</b></p></th>
                            </tr>
                        </thead>
                        <tbody>                   
                            <% 
                                int index = 0;
                                for (Notifica n : Notifiche) { %>                          
                            <tr class="info">
                                <td style="vertical-align: middle;"> <b><%= n.getUser().getNickname() %></b></td>
                                <td style="vertical-align: middle;"> <%= n.getDescrizione() %></td>
                                <td style="vertical-align: middle;">                                  
                                    <% if( n.getTipo().equals("nuovaRec") ){ %>
                                    <form method="POST" action="ServletAggiungiRepile" >
                                        <input type="hidden" name="ristorante" value="<%= id.get(index) %>">
                                        <input type="hidden" name="commento" value="<%=n.getIdGen()%>">
                                        <input type="text" class="form-control" id="descrizione" name="descrizione" pattern=".{1,25}" required>
                                        <button style="align-items: left" type="submit" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Rispondi alla recensione</button>                                              
                                    </form>
                                    <%   index++; }%>
                                    
                                    <% if( n.getTipo().equals("confermaRep") ){ %>
                                    <form method="POST" action="ServletAccettaaRisposta" >
                                        <input type="hidden" name="idGen" value="<%=n.getIdGen()%>">
                                        <button style="align-items: left" type="submit" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Accetta risposta</button>       
                                    </form>
                                    <form method="POST" action="ServletRifiutaRisposta" >
                                        <input type="hidden" name="idGen" value="<%=n.getIdGen()%>">
                                        <button style="align-items: left" type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> rifiuta risposta</button>       
                                    </form>                                    
                                    <% } %>
                                    
                                    <% if( n.getTipo().equals("reclama") ){ %>
                                    <form method="POST" action="ServletAccettaReclamo" >
                                        <input type="hidden" name="idGen" value="<%=n.getIdGen()%>">
                                        <button style="align-items: left" type="submit" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Accetta reclamo</button>       
                                    </form>
                                    <form method="POST" action="ServletRifiutaReclamo" >
                                        <input type="hidden" name="idGen" value="<%=n.getIdGen()%>">
                                        <button style="align-items: left" type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> rifiuta reclamo</button>       
                                    </form>
                                    <% } %>
                                                                       
                                    <% if( n.getTipo().equals("nuovaFoto") ){%>
                                    <form method="POST" action="ServletModificaFoto" >
                                        <input type="hidden" name="foto" value="<%=n.getIdGen()%>">
                                        <input type="hidden" name="val" value="1">
                                        <button style="align-items: left" type="submit" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Segnala la photo </button>       
                                    </form>                                       
                                        <img src="ImmaginiCaricate/<%= n.getFoto().getPath() %>" width=50 height=50 >
                                    <% } %>
                                    
                                    <% if( n.getTipo().equals("invalidaFoto") ){ %>
                                    <form method="POST" action="ServletModificaFoto" >
                                        <input type="hidden" name="foto" value="<%=n.getIdGen()%>">
                                        <input type="hidden" name="val" value="0">
                                        <button style="align-items: left" type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Cancella foto </button>       
                                    </form>
                                    <form method="POST" action="ServletModificaFoto" >
                                        <input type="hidden" name="foto" value="<%=n.getIdGen()%>">
                                        <input type="hidden" name="val" value="2">
                                        <button style="align-items: left" type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Cancella notifica </button>       
                                    </form>                                      
                                        <img src="ImmaginiCaricate/<%= n.getFoto().getPath() %>"  width=50 height=50 >
                                    <% } %>                                   
                                </td>
                            </tr>                           
                            <%
                                } 
                            %>                           
                        </tbody>
                    </table>
                </div>
            </div>   
        </div>
    </body>
</html>
