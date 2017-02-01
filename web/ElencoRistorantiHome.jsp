<%@page import="restaurants.Cusine"%>
<%@page import="restaurants.Restaurant"%>
<%@page import="java.math.BigDecimal"%>
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
        <link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/elencoRistorantiHome.css" />
        
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
        <div class="container">
            <div class="row row-centered">
                <table id="DataTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr style="background-color: rgba(198, 239, 255, 1);">
                            <th>NOME</th>
                            <th>VALUTAZIONE</th>
                            <th>CLASSIFICA</th>
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
                            <th>Classifica</th>
                            <th>Review</th>
                            <th>Min</th>
                            <th>Max</th>
                            <th>Indirizzo</th>
                            <th>Cucine</th>
                        </tr>
                    </tfoot>
                    <tbody style="background-color: white;">
                        <%  ArrayList <Restaurant> ALR = (ArrayList <Restaurant>) request.getAttribute("listaRistoranti");
                            for (Restaurant rest : ALR) {
                        %>
                        <tr>
                            <td>
                                <!-- bottone SUBMIT che contiene il nome del ristorante -->
                                <div class="bottom text-center">
                                    <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%= rest.getId() %> ' style="color: blue"><%= rest.getName().substring(0, 1).toUpperCase()+rest.getName().substring(1) %></a></b>
                                </div>
                                <br>
                                <div class="bottom text-center">
                                    <button class="btn btn-info btn-sm btn-justified" onclick="moveToLocation(<%= rest.getCordinate().getLatitude() %>,<%= rest.getCordinate().getLongitude() %>)">mappa</button>
                                </div>
                            </td>
                            <%  BigDecimal roundfinalPrice = new BigDecimal(rest.getGlobal_value()).setScale(1,BigDecimal.ROUND_HALF_UP); %>
                            <td background="Sfondi/sfondo.jpg"><b style="background-color: white;"> <%= roundfinalPrice %> </b></td>
                            <td> posizione in classiffica </td>
                            <td><%= rest.getN_reviews() %></td>
                            <td><%= rest.getPrice_range().getMin_value() %></td>
                            <td><%= rest.getPrice_range().getMax_value() %></td>
                            <td><%= rest.getCordinate().getAdrers().substring(0, 1).toUpperCase()+rest.getCordinate().getAdrers().substring(1) %> <%=rest.getCordinate().getNumero()%>, <%= rest.getCordinate().getCity().substring(0, 1).toUpperCase()+rest.getCordinate().getCity().substring(1) %>, <%= rest.getCordinate().getNazione().substring(0, 1).toUpperCase()+rest.getCordinate().getNazione().substring(1)%></td>
                            <td>                                                
                                <%
                                    int size = rest.getCusines().size();
                                    for(Cusine c : rest.getCusines()){
                                        if(size > 1){
                                %>
                                        <%=c.getName()+", "%>
                                <%
                                        }else{
                                %>
                                            <%=c.getName()%>
                                <%
                                        }
                                        size-=1;
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
                        responsive: true
                    });
                </script>

            </div>
        </div>
                    
                    
<!--MAPPPA-->

    <div id="map"></div>

    
    <script>
      var map; 
        
        
      function initMap() {
        var uluru = {lat: 43, lng: 12};
        
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 5,
          center: uluru
        });
        
        <% for(Restaurant rest : ALR){ %>
        var infowindow<%= rest.getId() %> = new google.maps.InfoWindow({
            content: "  <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%= rest.getId() %> ' style='color: blue'> <%= rest.getName() %></a></b>"
        });
        <%  } %>
            
        
        <% for(Restaurant rest : ALR){ %>
          var marker<%= rest.getId() %> = new google.maps.Marker({
              position: {lat:<%= rest.getCordinate().getLatitude() %>,lng:<%= rest.getCordinate().getLongitude() %>},
          map: map
        });
        <%  } %>
        
        
        <% for(Restaurant rest : ALR){ %>
        marker<%= rest.getId() %>.addListener('click', function() {
            infowindow<%= rest.getId() %>.open(map, marker<%= rest.getId() %>);
        });
        <%  } %>
      }
      


      function moveToLocation(lat, lng){
        var center = new google.maps.LatLng(lat, lng);
        // using global variable:
        map.panTo(center);
        map.setZoom(12);
      }

      
    </script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI&callback=initMap" async defer></script>



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
                            that
                                    .search( this.value )
                                .draw();
                        }
                    } );
                } );
            } );
          </script>
    </body>
</html>
