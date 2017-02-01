<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/notifica.css" />        

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body style="padding-top: 70px;" background="/DoveCiboGit/Sfondi/sfondo.jpg">
        <%@ include file="navBar.jsp" %>
        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">
                    <p style="color: black; font-size: 28px"><b>Azione</b></p>
                    <!-- effettiva tabella delle notifiche -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th><p style="color: black; font-size: 20px"><b>Foto</b></p></th>
                                <th><p style="color: black; font-size: 20px"><b>Azione</b></p></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="info">
                                <td style="vertical-align: middle;"> quì sta la foto</td>
                                <td style="vertical-align: middle;"> 

                                    <form method="POST" action="" >
                                        <input type="hidden" name="idGen" value="">
                                        <button style="align-items: left" type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Segnala foto</button>       
                                    </form>
                                    <button style="align-items: left" type="submit" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Mostra foto in un'altra scheda</button>       

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>   
        </div>
    </body>
</html>
