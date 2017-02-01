/* Author: Giacomo Barbieri */

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

    }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
} else
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
          'Error: The Geolocation service failed.' :
          'Error: Your browser doesn\'t support geolocation.');
}

function genCooGeo(){
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
        } else
            alert('Geocode was not successful for the following reason: ' + status);
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
        } else
            alert('Geocode was not successful for the following reason: ' + status);
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