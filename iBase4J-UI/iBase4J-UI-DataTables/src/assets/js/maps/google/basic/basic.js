/* ------------------------------------------------------------------------------
*
*  # Basic map
*
*  Specific JS code additions for maps_google_basic.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

	var map;

	// Map settings
	function initialize() {

		// Optinos
		var mapOptions = {
			zoom: 12,
			center: new google.maps.LatLng(47.496, 19.037)
		};

		// Apply options
		map = new google.maps.Map($('.map-basic')[0], mapOptions);
	}

	// Load map
	google.maps.event.addDomListener(window, 'load', initialize);

});
