<%--
    Pagina che contiene le funzioni per localizzare un ristorante durante l'inserimento
--%>

<link rel="stylesheet" type="text/css" href="/DoveCiboGit/css/ristoranteMap.css" />

<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC2yRPFE60Fp4Q05ezqySYocW9zpmqeIwI" async defer></script>
<script type="text/javascript" src="/DoveCiboGit/script/aggiungiRistoranteMap.js"></script>
<script type="text/javascript" src="/DoveCiboGit/script/onload.js"></script>

<div id="map"></div>
<div style="margin: 15px;">
    <button type="button" class="btn btn-md btn-info btn-group-justified" style="margin-bottom:15px;" onclick="codeAddress()"><span class="glyphicon glyphicon-play-circle"></span> Genera coordinate</button>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label>Latitudine:</label>
                <input class="form-control" name="lat" id="lat" onchange="latFill()" placeholder="12.123" required readonly="readonly">
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Longitudine:</label>
                <input  class="form-control" name="lng" id="lng" onchange="lngFill()"  placeholder="12.123" required readonly="readonly">
            </div>
        </div>
    </div>
</div>
