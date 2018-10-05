/* ------------------------------------------------------------------------------
*
*  # Fusion table
*
*  Specific JS code additions for maps_google_layers.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Initialize
    function initialize() {

        // Set coordinates
        var chicago = new google.maps.LatLng(41.850036, -87.6800523);

        // Options
        var mapOptions = {
            center: chicago,
            zoom: 12
        };

        // Apply options
        var map = new google.maps.Map($('.map-layer-fusion-tables')[0], mapOptions);

        // Add layers
        var layer = new google.maps.FusionTablesLayer({
            query: {
                select: '\'Geocodable address\'',
                from: '1mZ53Z70NsChnBMm-qEYmSDOvLXgrreLTkQUvvg'
            }
        });
        layer.setMap(map);
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
