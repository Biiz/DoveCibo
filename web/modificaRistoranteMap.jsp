<%@page import="restaurants.Restaurant"%>
<link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/ristoranteMap.css" />

<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI" async defer></script>
<%
    Restaurant Res = (Restaurant) request.getSession(false).getAttribute("ristorante");
%>
<script>
    var geocoder;
    var map;

   function initMap(latitudine,longitudine) {
        geocoder = new google.maps.Geocoder();
        
        var uluru = {lat: latitudine, lng: longitudine};
        
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 10,
          center: uluru
        });
        
        var infowindow<%= Res.getId() %> = new google.maps.InfoWindow({
            content: "  <b><%= Res.getName() %></b>"
        });

        var marker<%= Res.getId() %> = new google.maps.Marker({
              position: {lat:<%= Res.getCordinate().getLatitude() %>,lng:<%= Res.getCordinate().getLongitude() %>},
          map: map
        });
        
        marker<%= Res.getId() %>.addListener('click', function() {
            infowindow<%= Res.getId() %>.open(map, marker<%= Res.getId() %>);
        });
      }
    
    function codeAddress() {
        var address =
                document.getElementById('txtVia').value + " , " +
                document.getElementById('txtNumero').value + " , " +
                document.getElementById('txtCity').value + " , " +
                document.getElementById('txtNazione').value;
        geocoder.geocode({'address': address}, function (results, status) {
            if (status == 'OK') {
                map.setCenter(results[0].geometry.location);

                document.getElementById("lat").value = results[0].geometry.location.lat();
                document.getElementById("lng").value = results[0].geometry.location.lng();
                
                
                var infowindow<%= Res.getId() %> = new google.maps.InfoWindow({
                    content: "  <b><%= Res.getName() %></b>"
                });
                
                var marker<%= Res.getId() %>= new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
                
                marker<%= Res.getId() %>.addListener('click', function() {
                    infowindow<%= Res.getId() %>.open(map, marker<%= Res.getId() %>);
                });
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }
</script>
<script type="text/javascript"> 
    window.onload = function () { 
        initMap(<%=Res.getCordinate().getLatitude()%>,<%=Res.getCordinate().getLongitude()%>);
    }; 
</script>

<div id="map"></div>
<div style="margin: 15px;">
    <button type="button" class="btn btn-md btn-info btn-group-justified" style="margin-bottom:15px;" onclick="codeAddress()"><span class="glyphicon glyphicon-play-circle"></span> Genera coordinate</button>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label>Latitudine:</label>
                <input class="form-control" name="lat" id="lat" onchange="latFill()" value="<%= Res.getCordinate().getLatitude() %>" required readonly="readonly">
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="form-group">
                <label>Longitudine:</label>
                <input class="form-control" name="lng" id="lng" onchange="lngFill()" value="<%= Res.getCordinate().getLongitude() %>" required readonly="readonly">
            </div>
        </div>
    </div>
</div>
