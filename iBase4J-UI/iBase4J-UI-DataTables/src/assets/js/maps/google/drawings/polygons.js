/* ------------------------------------------------------------------------------
*
*  # Polygons
*
*  Specific JS code additions for maps_google_drawings.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // This example creates a simple polygon representing the Bermuda Triangle.
    // Note that the code specifies only three LatLng coordinates for the
    // polygon. The API automatically draws a
    // stroke connecting the last LatLng back to the first LatLng.

    function initialize() {

        // Options
        var mapOptions = {
            zoom: 5,
            center: new google.maps.LatLng(24.886436490787712, -70.2685546875),
            mapTypeId: google.maps.MapTypeId.TERRAIN
        };

        // Apply options
        var map = new google.maps.Map($('.map-drawing-polygon')[0], mapOptions);


        // Define the LatLng coordinates for the polygon's path. Note that there's
        // no need to specify the final coordinates to complete the polygon, because
        // The Google Maps JavaScript API will automatically draw the closing side.
        var bermudaTriangle;

        // Coordinates
        var triangleCoords = [
            new google.maps.LatLng(25.774252, -80.190262),
            new google.maps.LatLng(18.466465, -66.118292),
            new google.maps.LatLng(32.321384, -64.75737)
        ];

        // Polygon
        bermudaTriangle = new google.maps.Polygon({
            paths: triangleCoords,
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 1,
            fillColor: '#FF0000',
            fillOpacity: 0.2
        });

        bermudaTriangle.setMap(map);
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
