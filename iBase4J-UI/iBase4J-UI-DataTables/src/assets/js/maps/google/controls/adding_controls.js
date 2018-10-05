/* ------------------------------------------------------------------------------
*
*  # Adding controls
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
			zoom: 11,
			center: new google.maps.LatLng(48.136, 11.574),
			panControl: false,
			zoomControl: false,
			scaleControl: true
		}

		// Apply options
		var map = new google.maps.Map($('.map-adding-controls')[0], mapOptions);
	}

	// Load map
	google.maps.event.addDomListener(window, 'load', initialize);

});
