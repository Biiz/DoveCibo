
<style>

    #map {
        height: 500px;
        width: 500px;
        margin: auto;
    }

</style>

<script>
    var geocoder;
    var map;
    function initialize() {
        geocoder = new google.maps.Geocoder();
        var latlng = new google.maps.LatLng(-34.397, 150.644);
        var mapOptions = {
            zoom: 8,
            center: latlng
        }
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
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

                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }

</script>

<body onload="initialize()" >
    <div style="margin: 15px;">
        <button type="button" class="btn btn-md btn-info btn-group-justified" style="margin-bottom:15px;" onclick="codeAddress()"><span class="glyphicon glyphicon-play-circle"></span> Genera coordinate</button>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label>Latitudine:</label>          
                    <input class="form-control" name="lat" id="lat" placeholder="12.12345678901234">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>Longitudine:</label>          
                    <input  class="form-control" name="lng" id="lng"   placeholder="12.12345678901234">
                </div>
            </div>
        </div>

    </div>
    <div id="map"></div>
</body>

<script  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI&callback=initMap"
async defer></script>

