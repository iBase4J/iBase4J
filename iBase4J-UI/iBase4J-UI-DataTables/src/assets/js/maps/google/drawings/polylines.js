/* ------------------------------------------------------------------------------
*
*  # Polylines
*
*  Specific JS code additions for maps_google_drawings.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // This example creates an interactive map which constructs a
    // polyline based on user clicks. Note that the polyline only appears
    // once its path property contains two LatLng coordinates.

    var poly;
    var map;

    // Initialize
    function initialize() {

        // Options
        var mapOptions = {
            zoom: 8,
            center: new google.maps.LatLng(41.651, -0.894) // Center the map on Chicago, USA.
        };

        // Apply options
        map = new google.maps.Map($('.map-drawing-polyline')[0], mapOptions);


        // Polyline options
        var polyOptions = {
            strokeColor: '#555',
            strokeOpacity: 1.0,
            strokeWeight: 2
        };

        // Apply options
        poly = new google.maps.Polyline(polyOptions);
        poly.setMap(map);

        // Add a listener for the click event
        google.maps.event.addListener(map, 'click', addLatLng);
    }


    //
    // Handles click events on a map, and adds a new point to the Polyline.
    // @param {google.maps.MouseEvent} event

    function addLatLng(event) {

        var path = poly.getPath();

        // Because path is an MVCArray, we can simply append a new coordinate
        // and it will automatically appear.
        path.push(event.latLng);

        // Add a new marker at the new plotted point on the polyline.
        var marker = new google.maps.Marker({
            position: event.latLng,
            title: '#' + path.getLength(),
            map: map
        });
    }


    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
