/* Author: Giacomo Barbieri */

$(document).ready(function () {
    $('#DataTable').DataTable();
});

$('#DataTable').DataTable({
    responsive: true,
    "order": [[2, "asc"]]
});
