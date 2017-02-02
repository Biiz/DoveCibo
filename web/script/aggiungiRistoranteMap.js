/* Author: Giacomo Barbieri */

var geocoder;
var map;

function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var mapOptions = {
        zoom: 8,
        center: latlng
    };
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
            
            document.getElementById("afterGeocode").style.display = "block";
            document.getElementById("beforeGeocode").style.display = "none";
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}