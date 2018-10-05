/* ------------------------------------------------------------------------------
*
*  # Control positioning
*
*  Specific JS code additions for maps_google_controls.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Initialize
    function initialize() {

    // Optinos
    var mapOptions = {
        zoom: 12,
        center: new google.maps.LatLng(-28.643387, 153.612224),
        mapTypeControl: true,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.BOTTOM_CENTER
        },
        panControl: true,
        panControlOptions: {
            position: google.maps.ControlPosition.TOP_RIGHT
        },
        zoomControl: true,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.LARGE,
            position: google.maps.ControlPosition.LEFT_CENTER
        },
        scaleControl: true,
        streetViewControl: true,
        streetViewControlOptions: {
            position: google.maps.ControlPosition.LEFT_TOP
        }
    }

    // Apply options
    var map = new google.maps.Map($('.map-control-positioning')[0], mapOptions);
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
