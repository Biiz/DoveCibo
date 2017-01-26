<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="DoveCiboPK.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css" rel="stylesheet"/>
        
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
        
        <style>
            body {
                background:none transparent;
            }
            h1 {
                /* text-shadow: 5px 5px 13px black; */
            }
            .ombra {
                /* box-shadow: 5px 5px 13px black; */
            }
            
            .ui-autocomplete {
            position: absolute;
            z-index: 1000;
            cursor: default;
            padding: 0;
            margin-top: 2px;
            list-style: none;
            background-color: #ffffff;
            border: 1px solid #ccc;
            -webkit-border-radius: 5px;
               -moz-border-radius: 5px;
                    border-radius: 5px;
            -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
               -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
                    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
        }
        .ui-autocomplete > li {
          padding: 3px 20px;
        }
        .ui-autocomplete > li.ui-state-focus {
          background-color: #DDD;
        }
        .ui-helper-hidden-accessible {
          display: none;
        }
        </style>
        
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
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;" >
                    <form action="CercaRistorantiHome" method="post" target="_blank">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>
                        <div class="form-group ui-widget">
                            <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                                <input pattern=".{3,}" type="text" name="go" class="form-control autocomplete" placeholder="Cerca un ristorante" required>
                                <span class="input-group-btn">
                                    <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                    <button class="btn btn-success" type="submit">Go!</button> 
                            </div> 
                        </div>
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
