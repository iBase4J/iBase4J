/* ------------------------------------------------------------------------------
*
*  # Control options
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

        // Options
        var mapOptions = {
            zoom: 6,
            center: new google.maps.LatLng(51.164, 10.454),
            mapTypeControl: true,
            mapTypeControlOptions: {
                style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
            },
            zoomControl: true,
            zoomControlOptions: {
                style: google.maps.ZoomControlStyle.SMALL
            }
        }

        // Apply options
        var map = new google.maps.Map($('.map-control-options')[0], mapOptions);
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
