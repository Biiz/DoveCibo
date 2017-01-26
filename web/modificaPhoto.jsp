<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      

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

        <div class="modal-dialog modal-lg" >
            <div class="modal-content colonna2">
                <div class="modal-body">

                    <p style="color: black; font-size: 28px"><b>Gestione Foto</b></p>
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
