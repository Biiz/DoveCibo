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
        </style>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>

        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI" async defer></script>
        <script>

            var pos;

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    infoWindow.setPosition(pos);
                    infoWindow.setContent('Location found.');
                    map.setCenter(pos);
                }, function () {
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


            function genCooGeo() {

                document.getElementById("inlat").value = pos.lat;
                document.getElementById("inlng").value = pos.lng;
            }

            function genCooInd() {
                var address = document.getElementById("indirizzo").value;
                geocoder = new google.maps.Geocoder();
                geocoder.geocode({'address': address}, function (results, status) {
                    if (status == 'OK') {
                        document.getElementById("inlat").value = results[0].geometry.location.lat();
                        document.getElementById("inlng").value = results[0].geometry.location.lng();

                    } else {
                        alert('Geocode was not successful for the following reason: ' + status);
                    }
                });
            }




            function genCooGeo2() {

                document.getElementById("inlat2").value = pos.lat;
                document.getElementById("inlng2").value = pos.lng;
            }

            function genCooInd2() {
                var address = document.getElementById("indirizzo2").value;
                geocoder = new google.maps.Geocoder();
                geocoder.geocode({'address': address}, function (results, status) {
                    if (status == 'OK') {
                        document.getElementById("inlat2").value = results[0].geometry.location.lat();
                        document.getElementById("inlng2").value = results[0].geometry.location.lng();

                    } else {
                        alert('Geocode was not successful for the following reason: ' + status);
                    }
                });
            }
            
            function showGeneral(){
                document.getElementById("General").style.display = 'block';
                document.getElementById("TypeNearby").style.display = 'none';
                document.getElementById("StatsNearby").style.display = 'none';
            }
            function showTypeNearby(){
                document.getElementById("General").style.display = 'none';
                document.getElementById("TypeNearby").style.display = 'block';
                document.getElementById("StatsNearby").style.display = 'none';
            }
            function showStatsNearby(){
                document.getElementById("General").style.display = 'none';
                document.getElementById("TypeNearby").style.display = 'none';
                document.getElementById("StatsNearby").style.display = 'block';
            }
        </script>


        <div class="container">

            
            <!-- Menù bottoni per show/hide pannelli di ricerca -->
            <div class="row" style="margin-top: 15%;">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <h2 style="color: black; font-size: 30px;">Scegli tipologia di ricerca</h2>
                    <div class="row">
                        <div class="col-md-12">
                        <button class="btn btn-default" onClick="showGeneral()">Generale</button>
                        <button class="btn btn-default" onClick="showTypeNearby()">Posizione e tipologia</button>
                        <button class="btn btn-default" onClick="showStatsNearby()">Posizione e Classifica</button> 
                        </div>
                    </div> 
                    <br>
                </div> 
                <div class="col-md-3"></div>
            </div>
            
            <br>
            <!-- Pannello di ricerca generale -->
            <div class="row" id="General">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <form action="CercaRistorantiHome" method="post">
                        <h1 style="color: black; font-size: 50px; padding-top: 5%; ">Cerca un ristorante</h1>
                        <div class="input-group input-group-lg" style="padding-bottom: 5%; ">
                            <input type="text" name="go" class="form-control" placeholder="Cerca un ristorante" required>
                            <span class="input-group-btn">
                                <button class="btn btn-success" type="submit">Go!</button> 
                        </div> 
                    </form>                                 
                </div> 
                <div class="col-md-3"></div>
            </div>
            
            <br>
            <!-- Pannello di ricerca per Tipologia cucina e vicinanza -->
            <div class="row" id="TypeNearby" style="display: none;">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <h2 style="color: black; font-size: 30px;">Ricerca per tipologia e vicinanza</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="input-group input-group-md" style="padding-bottom: 5%; ">
                                <input type="text"  id="indirizzo" class="form-control" placeholder="Zona" >
                                <span class="input-group-btn">
                                    <button class="btn btn-info" onclick="genCooInd()" >Genera coordinate via indirizzo</button>
                            </div>
                        </div>
                    </div>
                    <p class="text-center"><b>Oppure</b></p>
                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-group-justified btn-info" onclick="genCooGeo()" >Genera coordinate Geolocalizzate</button>
                        </div>
                    </div>
                    <form action="ServletGetRistorantiHomeCucine" method="post">
                        <div class="row">
                            <br>
                            <div class="col-md-6">
                                <label>Latitudine:</label>
                                <input type="text" id="inlat" name="lat" class="form-control" placeholder="lat" required>
                            </div>
                            <div class="col-md-6">
                                <label>Longitudine:</label>
                                <input type="text" id="inlng" name="lng" class="form-control" placeholder="lng" required>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="input-group-md" style="padding-bottom: 5%; ">
                                    <div class="form-group ">
                                        <!-- <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label> -->
                                        <div class="input-group input-group-lg">
                                            <div class="input-group-addon">Tipologia: </div>
                                            <select class="form-control" id="sel1" name="cucina">
                                                <option>Pizzeria</option>
                                                <option>Trattoria</option>
                                                <option>Ristorante</option>
                                                <option>Disco Restaurant</option>
                                                <option>Straniera</option>
                                                <option>Altro</option>
                                            </select>
                                            <span class="input-group-btn">
                                                <button class="btn btn-lg btn-success" type="submit">Go!</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
            
            <br>
            <!-- Pannello di ricerca per vicinanza e classifica -->
            <div class="row" id="StatsNearby" style="display: none;">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="background-color: rgba(255, 255, 255, 0.80); border-radius: 5px;">
                    <h2 style="color: black; font-size: 30px;">Ricerca per classifica e vicinanza</h2>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="input-group input-group-md" style="padding-bottom: 5%; ">
                                <input type="text"  id="indirizzo2" class="form-control" placeholder="Zona" >
                                <span class="input-group-btn">
                                    <button class="btn btn-info" onclick="genCooInd2()" >Genera coordinate via indirizzo</button>
                            </div>
                        </div>
                    </div>
                    <p class="text-center"><b>Oppure</b></p>
                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-group-justified btn-info" onclick="genCooGeo2()" >Genera coordinate Geolocalizzate</button>
                        </div>
                    </div>
                    <form action="ServletGetRistorantiHomeValue" method="post">
                        <div class="row">
                            <br>
                            <div class="col-md-6">
                                <label>Latitudine:</label>
                                <input type="text" id="inlat2" name="lat" class="form-control" placeholder="lat" required>
                            </div>
                            <div class="col-md-6">
                                <label>Longitudine:</label>
                                <input type="text" id="inlng2" name="lng" class="form-control" placeholder="lng" required>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <button class="btn btn-success btn-lg pull-right " type="submit">Go!</button>
                            </div>
                        </div> 
                    </form>
                    <div class="row"><p> </p></div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            </body>
            </html>
