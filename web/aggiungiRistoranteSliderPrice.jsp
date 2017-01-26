<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
