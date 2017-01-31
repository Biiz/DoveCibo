<%@page import="java.util.ArrayList"%>
<%@page import="DoveCiboPK.*"%>

<!DOCTYPE html>
<html>
    <head>
        <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI" async defer></script>
        
        <style>
            #map {
                height: 400px;
                width: 100%;
            }
        </style>
    </head>
    
    <body>
        <div id="map"></div>
        <% 
            ArrayList <Restaurant> ALR = new ArrayList<Restaurant>();

            if ( new DB_Manager().tuttiRistoranti(ALR)) {
                for (Restaurant rest : ALR) {
                    if ( ! new DB_Manager().cercaUser_perId(rest.getCreator()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if ( ! new DB_Manager().setOrariPerRistorante(rest))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);   

                    if ( ! new DB_Manager().cercaPriceRange_perId(rest.getPrice_range()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if ( ! new DB_Manager().cercaCoordinate_perId(rest.getCordinate()))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);

                    if ( ! new DB_Manager().cercaCusines_perRistoranye(rest))
                        request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("erroreConnessione.jsp").forward(request, response);
            }
        %>
        
        <% for(Restaurant rest : ALR){ %>
        <%= rest.getName()  %>
        <% } %>

        <script>
            var map;
            
            function initMap() {
                var uluru = {lat: 43, lng: 12};

                map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 5,
                    center: uluru
                });

                <% for(Restaurant rest : ALR) { %>
                    var infowindow<%= rest.getId() %> = new google.maps.InfoWindow({
                        content: "  <b><a href='/DoveCiboGit/ServletGetRistorante?idR=<%= rest.getId() %> ' style='color: blue'> <%= rest.getName() %></a></b>"
                    });
                <%  } %>


                <% for(Restaurant rest : ALR) { %>
                    var marker<%= rest.getId() %> = new google.maps.Marker({
                        position: {lat:<%= rest.getCordinate().getLatitude() %>,lng:<%= rest.getCordinate().getLongitude() %>},
                        map: map
                    });
                <%  } %>

                <% for(Restaurant rest : ALR) { %>
                    marker<%= rest.getId() %>.addListener('click', function() {
                        infowindow<%= rest.getId() %>.open(map, marker<%= rest.getId() %>);
                    });
                <%  } %>
            }
        </script>
    </body>
</html>