<%@page import="DoveCiboPK.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>      
        <script src="http://code.jquery.com/jquery-1.12.3.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
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
        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <div class="container">
            <div class="row" style="margin-top: 15%;">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form action="CercaRistorantiHome" method="post">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>

                        <div class="input-group input-group-lg ombra" style="padding-bottom: 5%; ">
                            <input type="text" name="go" class="form-control" placeholder="Cerca un ristorante" required>
                            <span class="input-group-btn">
                                <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                <button class="btn btn-success" type="submit">Go!</button> 
                        </div> 
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
            <br>
            
            //RISTORANTI PER VICINANZA ORDINATI PER VALUE
            <iframe src="" id="elencoValore" style="width: 100%; height:400px; " ></iframe>
            
            
            //RISTORANTI PER VICINANZA UNO PER CATEGORIA
            <iframe src="" id="elencoCategoria" style="width: 100%; height:400px; " ></iframe>
            
            <script>
               
               
                 //RENDO INVISBILI IFRIME
                 document.getElementById("elencoValore").style.display = 'none';
                 document.getElementById("elencoCategoria").style.display = 'none';
                 
                 
                 if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function(position) {
                        var pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };
                        
                        //RENVO VISIBILI IFRIME
                        document.getElementById("elencoValore").style.display = 'block';
                        document.getElementById("elencoCategoria").style.display = 'block';
                        
                        document.getElementById("elencoValore").src ="ServletGetRistorantiHomeValue?lat="+pos.lat+"&lng="+pos.lng;
                        
                        document.getElementById("elencoCategoria").src ="ServletGetRistorantiHomeCucine?lat="+pos.lat+"&lng="+pos.lng;

                    infoWindow.setPosition(pos);
                    infoWindow.setContent('Location found.');
                    map.setCenter(pos);
                    }, function() {
                        handleLocationError(true, infoWindow, map.getCenter());
                    });
                } else {
                 // Browser doesn't support Geolocation
                    handleLocationError(false, infoWindow, map.getCenter());
                }
               
                    function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                        infoWindow.setPosition(pos);
                        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
                    }
               
               
                
            </script>
            
            
            <div class="row">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    
                        <form action="" method="post">
                            
                            <h2 style="color: black; font-size: 30px;">Ricerca per tipologia e vicinanza</h2>
<div class="row">
                            <div class="col-md-9">
                            <div class="input-group-md ombra" style="padding-bottom: 5%; ">
                                <input type="text" name="go" class="form-control" placeholder="Zona del ristorante" required>

                                <div class="form-group ">
                                    <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">Tipologia: </div>
                                        <select class="form-control" id="sel1">
                                            <option>Pizzeria</option>
                                            <option>Trattoria</option>
                                            <option>Ristorante</option>
                                            <option>Disco Restaurant</option>
                                            <option>Straniera</option>
                                            <option>Altro</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            </div>
                            <div class="col-md-3">
                            <button class="btn btn-success" type="submit">Go!</button>
                            </div>
                        </form>
                    </div>
                </div> 
                <div class="col-md-3"></div>
            </div>
            <br>
            <div class="row">

                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form action="" method="post">
 <h2 style="color: black; font-size: 30px;">Ricerca per classifica e vicinanza</h2>

                        <div class="input-group input-group-md ombra" style="padding-bottom: 5%; ">
                            <input type="text" name="go" class="form-control" placeholder="Zona del ristorante" required>
                            <span class="input-group-btn">
                                <!-- questo bottone submitta la ricerca, per ora linka solo la pagina dei ristoranti -->
                                <button class="btn btn-success" type="submit">Go!</button> 
                        </div> 
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
        </div>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
</html>