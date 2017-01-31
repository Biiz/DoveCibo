<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/cercaRistoranti.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    
    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>
        <br>
        
        <!-- bottoni-radio (toggle di java) che si possono premere uno per volta -->
        <div class="row row-centered" style="padding-bottom: 20px;">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                <div data-toggle="buttons">
                    <label class="btn btn-info btn-lg active" style="margin-bottom: 10px;">
                        <input type="radio" name="valutazione" id="option1" checked> Valutazione
                    </label>
                    <label class="btn btn-info btn-lg" style="margin-bottom: 10px;">
                        <input type="radio" name="fascia_di_prezzo" id="option2"> Fascia di prezzo
                    </label>
                    <label class="btn btn-info btn-lg" style="margin-bottom: 10px;">
                        <input type="radio" name="vicinanza" id="option3" > Vicinanza
                    </label>
                </div>
            </div>
        </div>
    </body>
</html>
