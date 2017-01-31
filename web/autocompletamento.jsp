<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="DoveCiboPK.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/autocompletamento.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/js/bootstrap.js"></script>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <%
        HashSet<String> auto = (HashSet<String>) request.getAttribute("auto");    
        %>
        
        <script>
            $(function() {
              var availableTags = [
                  
                <% for(String a: auto){ %>
                       "<%= a %>" ,     
                <% } %>
                    
              ];

              $(".autocomplete").autocomplete({
                source: availableTags
              });
            });
        </script>
    </head>
    
    <body>
        <div class="container">
            <div class="row">

                <div class="col-md-3"></div>
                <div class="col-md-4" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;" >
                    <form id="formCercaRisto" action="CercaRistorantiHome" method="post" target="_blank">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>
                        <div class="form-group ui-widget">
                            <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                                <input id="input_form" pattern=".{3,}" type="text" name="go" class="form-control autocomplete" placeholder="Cerca un ristorante" required>
                                <span class="input-group-btn">
                                    <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                    <button class="btn btn-success" type="submit" onclick="allerta()" id="btn_submit">Go!</button>
                                    <script>
                                        function allerta(){
                                            if(document.getElementById('input_form').value != ''
                                                && document.getElementById('input_form').value.length >=3){
                                                document.getElementById('formCercaRisto').submit();
                                                setTimeout(function() {
                                                    //così dopo 250ms la finestra viene chiusa
                                                    parent.window.close();
                                                }, 250);
                                                
                                            }
                                        }
                                    </script>
                            </div> 
                        </div>
                    </form>                                 
                </div> 
                <div class="col-md-5"></div>
            </div>
        </div>
    </body>
</html>
