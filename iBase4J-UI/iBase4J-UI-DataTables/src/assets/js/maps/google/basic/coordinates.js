/* ------------------------------------------------------------------------------
*
*  # Showing coordinates
*
*  Specific JS code additions for maps_google_basic.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Variables
    var map;
    var TILE_SIZE = 256;
    var chicago = new google.maps.LatLng(41.850033,-87.6500523);

    // Minimum and maximum values
    function bound(value, opt_min, opt_max) {
        if (opt_min != null) value = Math.max(value, opt_min);
        if (opt_max != null) value = Math.min(value, opt_max);
        return value;
    }

    // Degrees to radians
    function degreesToRadians(deg) {
        return deg * (Math.PI / 180);
    }

    // Radians to degrees
    function radiansToDegrees(rad) {
        return rad / (Math.PI / 180);
    }

    // Constructor
    function MercatorProjection() {
        this.pixelOrigin_ = new google.maps.Point(TILE_SIZE / 2, TILE_SIZE / 2);
        this.pixelsPerLonDegree_ = TILE_SIZE / 360;
        this.pixelsPerLonRadian_ = TILE_SIZE / (2 * Math.PI);
    }

    // From latitude to longitude
    MercatorProjection.prototype.fromLatLngToPoint = function(latLng, opt_point) {
        var me = this;
        var point = opt_point || new google.maps.Point(0, 0);
        var origin = me.pixelOrigin_;

        point.x = origin.x + latLng.lng() * me.pixelsPerLonDegree_;

        // Truncating to 0.9999 effectively limits latitude to 89.189. This is
        // about a third of a tile past the edge of the world tile.
        var siny = bound(Math.sin(degreesToRadians(latLng.lat())), -0.9999, 0.9999);
        point.y = origin.y + 0.5 * Math.log((1 + siny) / (1 - siny)) * -me.pixelsPerLonRadian_;
        return point;
    };

    // From longitude to latitude
    MercatorProjection.prototype.fromPointToLatLng = function(point) {
        var me = this;
        var origin = me.pixelOrigin_;
        var lng = (point.x - origin.x) / me.pixelsPerLonDegree_;
        var latRadians = (point.y - origin.y) / -me.pixelsPerLonRadian_;
        var lat = radiansToDegrees(2 * Math.atan(Math.exp(latRadians)) - Math.PI / 2);
        return new google.maps.LatLng(lat, lng);
    };

    // Create content
    function createInfoWindowContent() {
        var numTiles = 1 << map.getZoom();
        var projection = new MercatorProjection();
        var worldCoordinate = projection.fromLatLngToPoint(chicago);
        var pixelCoordinate = new google.maps.Point(worldCoordinate.x * numTiles, worldCoordinate.y * numTiles);
        var tileCoordinate = new google.maps.Point(
        Math.floor(pixelCoordinate.x / TILE_SIZE),
        Math.floor(pixelCoordinate.y / TILE_SIZE));

        return [
            'Chicago, IL',
            'LatLng: ' + chicago.lat() + ' , ' + chicago.lng(),
            'World Coordinate: ' + worldCoordinate.x + ' , ' + worldCoordinate.y,
            'Pixel Coordinate: ' + Math.floor(pixelCoordinate.x) + ' , ' + Math.floor(pixelCoordinate.y),
            'Tile Coordinate: ' + tileCoordinate.x + ' , ' + tileCoordinate.y + ' at Zoom Level: ' + map.getZoom()
        ].join('<br>');
    }

    // Initialize
    function initialize() {

        // Options
        var mapOptions = {
            zoom: 10,
            center: chicago
        };

        // Apply options
        map = new google.maps.Map($('.map-coordinates')[0], mapOptions);

        // Info window
        var coordInfoWindow = new google.maps.InfoWindow();
        coordInfoWindow.setContent(createInfoWindowContent());
        coordInfoWindow.setPosition(chicago);
        coordInfoWindow.open(map);

        // Add "Change" event
        google.maps.event.addListener(map, 'zoom_changed', function() {
            coordInfoWindow.setContent(createInfoWindowContent());
            coordInfoWindow.open(map);
        });
    }

    // Load map
    google.maps.event.addDomListener(window, 'load', initialize);

});
