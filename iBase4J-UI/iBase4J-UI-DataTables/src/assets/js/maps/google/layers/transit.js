/* ------------------------------------------------------------------------------
*
*  # Transit layer
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
		var myLatlng = new google.maps.LatLng(51.501904,-0.115871);

		// Options
		var mapOptions = {
			zoom: 14,
			center: myLatlng
		}

		// Apply options
		var map = new google.maps.Map($('.map-layer-transit')[0], mapOptions);

		// Add layers
		var transitLayer = new google.maps.TransitLayer();
			transitLayer.setMap(map);
	}

	// Load map
	google.maps.event.addDomListener(window, 'load', initialize);

});
