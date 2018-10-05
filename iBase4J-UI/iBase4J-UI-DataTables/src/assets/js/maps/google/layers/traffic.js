/* ------------------------------------------------------------------------------
*
*  # Traffic layer
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
		var myLatlng = new google.maps.LatLng(40.4122142, -3.7059725);

		// Options
		var mapOptions = {
			zoom: 12,
			center: myLatlng
		}

		// Apply options
		var map = new google.maps.Map($('.map-layer-traffic')[0], mapOptions);

		// Add layers
		var trafficLayer = new google.maps.TrafficLayer();
			trafficLayer.setMap(map);
	}

	// Load map
	google.maps.event.addDomListener(window, 'load', initialize);

});
