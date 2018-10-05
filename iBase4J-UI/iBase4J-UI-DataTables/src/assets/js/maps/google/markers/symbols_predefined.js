/* ------------------------------------------------------------------------------
*
*  # Predefined marker symbols
*
*  Specific JS code additions for maps_google_markers.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // This example uses a symbol to add a vector-based icon to a marker.
    // The symbol uses one of the predefined vector paths ('CIRCLE') supplied by the
    // Google Maps JavaScript API.

    // Setup map
    function initialize() {

        // Options
        var mapOptions = {
            zoom: 12,
            center: new google.maps.LatLng(48.220, 16.380)
        };

        // Apply options
        var map = new google.maps.Map($('.map-symbol-predefined')[0], mapOptions);

        // Setup markers
        var marker = new google.maps.Marker({
            position: map.getCenter(),
            icon: {
                path: google.maps.SymbolPath.CIRCLE,
                scale: 10
            },
            draggable: true,
            map: map
        });
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
