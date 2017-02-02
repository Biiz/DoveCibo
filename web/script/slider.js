/* Author: Giacomo Barbieri */

$(document).ready(function() {
    $("#price").slider({
        ticks: [0, 20, 40, 60, 80, 100],
        ticks_labels: ["€0", "€20", "€40", "€60", "€80", "€100"],
        ticks_snap_bounds: 30
    });
});