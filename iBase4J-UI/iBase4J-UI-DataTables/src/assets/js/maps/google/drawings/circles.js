/* ------------------------------------------------------------------------------
*
*  # Circles
*
*  Specific JS code additions for maps_google_drawings.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // This example creates circles on the map, representing
    // populations in North America.

    // First, create an object containing LatLng and population for each city.
    var citymap = {};
    citymap['wroclaw'] = {
        center: new google.maps.LatLng(51.112, 17.052),
        population: 271485
    };
    citymap['budapest'] = {
        center: new google.maps.LatLng(47.481, 19.130),
        population: 840583
    };
    citymap['kernstadt'] = {
        center: new google.maps.LatLng(51.720, 8.764),
        population: 385779
    };
    citymap['nancy'] = {
        center: new google.maps.LatLng(48.688, 6.173),
        population: 603502
    };
    citymap['munich'] = {
        center: new google.maps.LatLng(48.154, 11.541),
        population: 594039
    };
    citymap['warsaw'] = {
        center: new google.maps.LatLng(52.232, 21.061),
        population: 492093
    };


    // Initialize
    var cityCircle;
    function initialize() {

        // Options
        var mapOptions = {
            zoom: 6,
            center: new google.maps.LatLng(50.059, 14.465),
            mapTypeId: google.maps.MapTypeId.TERRAIN
        };

        // Apply options
        var map = new google.maps.Map($('.map-drawing-circles')[0], mapOptions);


        // Construct the circle for each value in citymap.
        // Note: We scale the area of the circle based on the population.
        for (var city in citymap) {

            // Options
            var populationOptions = {
                strokeColor: '#b41b1b',
                strokeOpacity: 0.5,
                strokeWeight: 1,
                fillColor: '#b41b1b',
                fillOpacity: 0.2,
                map: map,
                center: citymap[city].center,
                radius: Math.sqrt(citymap[city].population) * 100
            };

            // Add the circle for this city to the map.
            cityCircle = new google.maps.Circle(populationOptions);
        }
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
