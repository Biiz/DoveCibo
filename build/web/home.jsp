<%@page import="DoveCiboPK.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DoveCibo</title>
        <link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css" rel="stylesheet"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/js/bootstrap.js"></script>

        <style>
            body {
                background-image: url('img/img (1)big.jpeg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
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
            border: 1px solid #ccc
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
        
        <script>
$(function() {
  var availableTags = [
    "ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++",
    "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran",
    "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl",
    "PHP", "Python", "Ruby", "Scala", "Scheme"
  ];
  
  $(".autocomplete").autocomplete({
    source: availableTags
  });
});
        </script>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <div class="container">
            <div class="row" style="margin-top: 25%;">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form action="CercaRistorantiHome" method="post">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>
                        <div class="form-group ui-widget">
                            <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                                <input type="text" name="go" class="form-control autocomplete" placeholder="Cerca un ristorante" required>
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
        <br><br><br><br><br>
        

        <br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>
