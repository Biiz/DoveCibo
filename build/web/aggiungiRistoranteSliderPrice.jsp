<%-- 
    Document   : aggiungiRistoranteSliderPrice
    Created on : 30-set-2016, 11.45.51
    Author     : Giacomo Barbieri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" media="all" />   
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.9.1.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="./bootstrap-slider/bootstrap-slider.css" media="all" />
        <script src="./bootstrap-slider/bootstrap-slider.js"></script>
    </head>
    <body>
        <input id="price" name="price" type="text" 
               class="span2" value="" data-slider-min="0" 
               data-slider-max="100" data-slider-step="5" 
               data-slider-value="[20,80]"
               data-slider-ticks="[0, 20, 40, 60, 80, 100]" 
               data-slider-ticks-snap-bounds="30" 
               data-slider-ticks-labels='["€0", "€20", "€40", "€60", "€80", "€100"]'/>  
            <script>
             $("#price").slider({
                 ticks: [0, 20, 40, 60, 80, 100],
                 ticks_labels: ["€0", "€20", "€40", "€60", "€80", "€100"],
                 ticks_snap_bounds: 30
             });
            </script>
    </body>
</html>
