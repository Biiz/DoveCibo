<%@page import="DoveCiboPK.Notifica"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
            .colonna2{
                background-color: rgba(255, 255, 255, 0.75);
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.7);
                border-radius: 5px;
            }
        </style>
    </head>
    
    <body style="padding-top: 70px;">
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
                                <td>
                                    
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
