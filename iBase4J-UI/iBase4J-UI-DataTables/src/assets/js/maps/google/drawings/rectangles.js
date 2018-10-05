/* ------------------------------------------------------------------------------
*
*  # Rectangles
*
*  Specific JS code additions for maps_google_drawings.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Initialize
    function initialize() {

        // Options
        var mapOptions = {
            zoom: 11,
            center: new google.maps.LatLng(33.678176, -116.242568),
            mapTypeId: google.maps.MapTypeId.TERRAIN
        };

        // Apply options
        var map = new google.maps.Map($('.map-drawing-rectangle')[0], mapOptions);

        // Add rectangle
        var rectangle = new google.maps.Rectangle({
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            map: map,
            bounds: new google.maps.LatLngBounds(
                new google.maps.LatLng(33.671068, -116.25128),
                new google.maps.LatLng(33.685282, -116.233942)
            )
        });
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
