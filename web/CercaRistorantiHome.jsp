<%@page import="java.math.BigDecimal"%>
<%@page import="DoveCiboPK.*"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>
            body {
                background-image: url("img/img (7)b.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }

            tfoot input {
                width: 100%;
                padding: 3px;
                box-sizing: border-box;
            }

            #map {
                height: 400px;
                width: 100%;
            }
            #cont{
                height:150px;
                width:200px;
                position:relative;
            }

            #immagine{    
                position:absolute;
                left:0;
                top:0;
                height:150px;
                width:200px;
            }
            #testo{
                z-index:20;
                position:absolute;    
                color:white;
                font-size:20px;
                font-weight:bold;
                text-shadow: 0px 0px 8px #000,0px 0px 12px #000, 0px 0px 16px #000;
                left:10px;
                top:5px;
            }
        </style>
    </head>

    <body style="padding-top: 70px;">
        <%@ include file="navBar.jsp" %>

        <div class="container">
            <div class="row row-centered">

                <h2>Per cercare qualcosa tra i risultati utilizzare l'input di ricerca a destra.</h2>
                <h2>Per filtrare i risultati, utilizzare gli input sotto ad una colonna.</h2>
                <br><br>

                <table id="DataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr style="background-color: rgba(198, 239, 255, 1);">
                            <th>NOME</th>
                            <th>VALUTAZIONE</th>
                            <th>RANK</th>
                            <th>REVIEWS</th>
                            <th>€ MIN</th>
                            <th>€ MAX</th>
                            <th>INDIRIZZO</th>
                            <th>CUCINE</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Nome</th>
                            <th>Valutazione</th>
                            <th>Rank</th>
                            <th>Review</th>
                            <th>Min</th>
                            <th>Max</th>
                            <th>Indirizzo</th>
                            <th>Cucine</th>
                        </tr>
                    </tfoot>
                    <tbody style="background-color: white;">
                        <%  ArrayList<Restaurant> ALR = (ArrayList<Restaurant>) request.getAttribute("listaRistoranti");
                            ArrayList<Integer> classifica = (ArrayList<Integer>) request.getAttribute("classifica");
                            for (Restaurant rest : ALR) {
                        %>
                        <tr>
                            <td>
                                <!-- bottone SUBMIT che contiene il nome del ristorante -->
                                <div class="bottom text-center">
                                    <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%= rest.getId()%> ' style="color: blue"><%= rest.getName().substring(0, 1).toUpperCase() + rest.getName().substring(1)%></a></b>
                                </div>
                                <br>
                                <div class="bottom text-center">
                                    <button class="btn btn-info btn-sm btn-justified" onclick="moveToLocation(<%= rest.getCordinate().getLatitude()%>,<%= rest.getCordinate().getLongitude()%>)">mappa</button>
                                </div>
                            </td>
                            <% BigDecimal roundfinalPrice = new BigDecimal(rest.getGlobal_value()).setScale(1, BigDecimal.ROUND_HALF_UP);%>

                            <td>
                                <div id="cont">
                                    <p id="testo"> <%= roundfinalPrice%> </p>
                                    <img id="immagine" src="
                                        <% if (rest.getPhotos().size() != 0) {%>
                                        immaginiRistoranti/<%= rest.getPhotos().get(0).getPath()%><% } else{%>
                                        img/empty_img.jpg <% } %>
                                    "/>
                                    
                                </div>
                            </td>                           
                            <td><%= classifica.indexOf(rest.getId()) + 1%></td>
                            <td><%= rest.getN_reviews()%></td>
                            <td><%= rest.getPrice_range().getMin_value()%></td>
                            <td><%= rest.getPrice_range().getMax_value()%></td>
                            <td><%= rest.getCordinate().getAdrers().substring(0, 1).toUpperCase() + rest.getCordinate().getAdrers().substring(1)%> <%=rest.getCordinate().getNumero()%>, <%= rest.getCordinate().getCity().substring(0, 1).toUpperCase() + rest.getCordinate().getCity().substring(1)%>, <%= rest.getCordinate().getNazione().substring(0, 1).toUpperCase() + rest.getCordinate().getNazione().substring(1)%></td>
                            <td>                                                
                                <%
                                    int size = rest.getCusines().size();
                                    for (Cusine c : rest.getCusines()) {
                                        if (size > 1) {
                                %>
                                <%=c.getName() + ", "%>
                                <%
                                } else {
                                %>
                                <%=c.getName()%>
                                <%
                                        }
                                        size -= 1;
                                    }
                                %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <script>
                    $(document).ready(function () {
                        $('#DataTable').DataTable();
                    });

                    $('#DataTable').DataTable({
                        responsive: true,
                        "order": [[2, "asc"]]
                    });
                </script>




                <!--MAPPPA-->

                <div id="map" class="row">


                    <script>
                        var map;

                        function initMap() {
                            var uluru = {lat: 43, lng: 12};
                            map = new google.maps.Map(document.getElementById('map'), {
                                zoom: 5,
                                center: uluru
                            });



                        <% for (Restaurant rest : ALR) {%>
                            var infowindow<%= rest.getId()%> = new google.maps.InfoWindow({
                                content: "  <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%= rest.getId()%> ' style='color: blue'> <%= rest.getName()%></a></b>"
                            });
                        <%  } %>


                        <% for (Restaurant rest : ALR) {%>
                            var marker<%= rest.getId()%> = new google.maps.Marker({
                                position: {lat:<%= rest.getCordinate().getLatitude()%>, lng:<%= rest.getCordinate().getLongitude()%>},
                                map: map
                            });
                        <%  } %>


                        <% for (Restaurant rest : ALR) {%>
                            marker<%= rest.getId()%>.addListener('click', function () {
                                infowindow<%= rest.getId()%>.open(map, marker<%= rest.getId()%>);
                            });
                        <%  }%>
                        }



                        function moveToLocation(lat, lng) {
                            var center = new google.maps.LatLng(lat, lng);
                            // using global variable:
                            map.panTo(center);
                            map.setZoom(12);
                        }


                    </script>
                    <script async defer
                            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI&callback=initMap">
                    </script>

                </div>
            </div>
        </div>
        <!--FINE MAPPA-->



        <script>
            $(document).ready(function () {
                // Setup - add a text input to each footer cell
                $('#DataTable tfoot th').each(function () {
                    var title = $(this).text();
                    $(this).html('<input type="text" placeholder="' + title + '.. " />');
                });

                // DataTable
                var table = $('#DataTable').DataTable();

                // Apply the search
                table.columns().every(function () {
                    var that = this;

                    $('input', this.footer()).on('keyup change', function () {
                        if (that.search() !== this.value) {
                            that.search(this.value).draw();
                        }
                    });
                });


            });
        </script>
    </body>
</html>
