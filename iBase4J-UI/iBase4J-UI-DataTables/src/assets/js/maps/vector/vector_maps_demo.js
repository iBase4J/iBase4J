/* ------------------------------------------------------------------------------
*
*  # Vector maps
*
*  Specific JS code additions for maps_vector.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // World map
    $('.map-world').vectorMap({
        map: 'world_mill_en',
        backgroundColor: 'transparent',
        regionStyle: {
            initial: {
                fill: '#93D389'
            }
        }
    });


    // Custom markers
    $('.map-world-markers').vectorMap({
        map: 'world_mill_en',
        backgroundColor: 'transparent',
        scaleColors: ['#C8EEFF', '#0071A4'],
        normalizeFunction: 'polynomial',
        regionStyle: {
            initial: {
                fill: '#D6E1ED'
            }
        },
        hoverOpacity: 0.7,
        hoverColor: false,
        markerStyle: {
            initial: {
                r: 7,
                'fill': '#336BB5',
                'fill-opacity': 0.8,
                'stroke': '#fff',
                'stroke-width' : 1.5,
                'stroke-opacity': 0.9
            },
            hover: {
                'stroke': '#fff',
                'fill-opacity': 1,
                'stroke-width': 1.5
            }
        },
        focusOn: {
            x: 0.5,
            y: 0.5,
            scale: 2
        },
        markers: [
            {latLng: [41.90, 12.45], name: 'Vatican City'},
            {latLng: [43.73, 7.41], name: 'Monaco'},
            {latLng: [40.726, -111.778], name: 'Salt Lake City'},
            {latLng: [39.092, -94.575], name: 'Kansas City'},
            {latLng: [25.782, -80.231], name: 'Miami'},
            {latLng: [8.967, -79.458], name: 'Panama City'},
            {latLng: [19.400, -99.124], name: 'Mexico City'},
            {latLng: [40.705, -73.978], name: 'New York'},
            {latLng: [33.98, -118.132], name: 'Los Angeles'},
            {latLng: [47.614, -122.335], name: 'Seattle'},
            {latLng: [44.97, -93.261], name: 'Minneapolis'},
            {latLng: [39.73, -105.015], name: 'Denver'},
            {latLng: [41.833, -87.732], name: 'Chicago'},
            {latLng: [29.741, -95.395], name: 'Houston'},
            {latLng: [23.05, -82.33], name: 'Havana'},
            {latLng: [45.41, -75.70], name: 'Ottawa'},
            {latLng: [53.555, -113.493], name: 'Edmonton'},
            {latLng: [-0.23, -78.52], name: 'Quito'},
            {latLng: [18.50, -69.99], name: 'Santo Domingo'},
            {latLng: [4.61, -74.08], name: 'Bogotá'},
            {latLng: [14.08, -87.21], name: 'Tegucigalpa'},
            {latLng: [17.25, -88.77], name: 'Belmopan'},
            {latLng: [14.64, -90.51], name: 'New Guatemala'},
            {latLng: [-15.775, -47.797], name: 'Brasilia'},
            {latLng: [-3.790, -38.518], name: 'Fortaleza'},
            {latLng: [50.402, 30.532], name: 'Kiev'},
            {latLng: [53.883, 27.594], name: 'Minsk'},
            {latLng: [52.232, 21.061], name: 'Warsaw'},
            {latLng: [52.507, 13.426], name: 'Berlin'},
            {latLng: [50.059, 14.465], name: 'Prague'},
            {latLng: [47.481, 19.130], name: 'Budapest'},
            {latLng: [52.374, 4.898], name: 'Amsterdam'},
            {latLng: [48.858, 2.347], name: 'Paris'},
            {latLng: [40.437, -3.679], name: 'Madrid'},
            {latLng: [39.938, 116.397], name: 'Beijing'},
            {latLng: [28.646, 77.093], name: 'Delhi'},
            {latLng: [25.073, 55.229], name: 'Dubai'},
            {latLng: [35.701, 51.349], name: 'Tehran'},
            {latLng: [7.11, 171.06], name: 'Marshall Islands'},
            {latLng: [17.3, -62.73], name: 'Saint Kitts and Nevis'},
            {latLng: [3.2, 73.22], name: 'Maldives'},
            {latLng: [35.88, 14.5], name: 'Malta'},
            {latLng: [12.05, -61.75], name: 'Grenada'},
            {latLng: [13.16, -61.23], name: 'Saint Vincent and the Grenadines'},
            {latLng: [13.16, -59.55], name: 'Barbados'},
            {latLng: [17.11, -61.85], name: 'Antigua and Barbuda'},
            {latLng: [-4.61, 55.45], name: 'Seychelles'},
            {latLng: [7.35, 134.46], name: 'Palau'},
            {latLng: [42.5, 1.51], name: 'Andorra'},
            {latLng: [14.01, -60.98], name: 'Saint Lucia'},
            {latLng: [6.91, 158.18], name: 'Federated States of Micronesia'},
            {latLng: [1.3, 103.8], name: 'Singapore'},
            {latLng: [1.46, 173.03], name: 'Kiribati'},
            {latLng: [-21.13, -175.2], name: 'Tonga'},
            {latLng: [15.3, -61.38], name: 'Dominica'},
            {latLng: [-20.2, 57.5], name: 'Mauritius'},
            {latLng: [26.02, 50.55], name: 'Bahrain'},
            {latLng: [0.33, 6.73], name: 'São Tomé and Príncipe'}
        ]
    });


    // Country choropleth map
    $.getJSON('assets/demo_data/maps/vector/unemployment.json', function (data) {

        // Year
        var val = 2009;

        // Values
        statesValues = jvm.values.apply({}, jvm.values(data.states)),
        metroPopValues = Array.prototype.concat.apply([], jvm.values(data.metro.population)),
        metroUnemplValues = Array.prototype.concat.apply([], jvm.values(data.metro.unemployment));

        // Configuration
        $('.map-unemployment').vectorMap({
            map: 'us_aea_en',
            backgroundColor: 'transparent',
            markers: data.metro.coords,
            markerStyle: {
                initial: {
                    r: 6,
                    'fill-opacity': 0.9,
                    'stroke': '#fff',
                    'stroke-width' : 1.5,
                    'stroke-opacity': 0.95
                },
                hover: {
                    'stroke': '#fff',
                    'fill-opacity': 1,
                    'stroke-width': 1.5
                }
            },
            series: {
                markers: [
                    {
                        attribute: 'fill',
                        scale: ['#e67d64', '#e0330b'],
                        values: data.metro.unemployment[val],
                        min: jvm.min(metroUnemplValues),
                        max: jvm.max(metroUnemplValues)
                    },
                    {
                        attribute: 'r',
                        scale: [5, 20],
                        values: data.metro.population[val],
                        min: jvm.min(metroPopValues),
                        max: jvm.max(metroPopValues)
                    }
                ],
                regions: [{
                    scale: ['#DEEBF7', '#08519C'],
                    attribute: 'fill',
                    values: data.states[val],
                    min: jvm.min(statesValues),
                    max: jvm.max(statesValues)
                }]
            },

            onMarkerLabelShow: function(event, label, index) {
                label.html(
                    ''+data.metro.names[index]+'<br>'+
                    'Population: '+data.metro.population[val][index]+'<br>'+
                    'Unemployment rate: '+data.metro.unemployment[val][index]+'%'
                );
            },

            onRegionLabelShow: function(event, label, code) {
                label.html(
                    ''+label.html()+'<br>'+
                    'Unemployment rate: '+data.states[val][code]+'%'
                );
            }
        });

        // Draw map
        var mapObject = $('.map-unemployment').vectorMap('get', 'mapObject');
    });


    // Choropleth map
    $('.map-choropleth').vectorMap({
        map: 'world_mill_en',
        backgroundColor: 'transparent',
        series: {
            regions: [{
                values: gdpData,
                scale: ['#C8EEFF', '#0071A4'],
                normalizeFunction: 'polynomial'
            }]
        },
        onRegionLabelShow: function(e, el, code){
            el.html(el.html()+'<br>'+'GDP - '+gdpData[code]);
        }
    });


    //
    // Regions selection
    //

    // Set data
    markers = [
        {latLng: [52.50, 13.39], name: 'Berlin'},
        {latLng: [53.56, 10.00], name: 'Hamburg'},
        {latLng: [48.13, 11.56], name: 'Munich'},
        {latLng: [50.95, 6.96], name: 'Cologne'},
        {latLng: [50.11, 8.68], name: 'Frankfurt am Main'},
        {latLng: [48.77, 9.17], name: 'Stuttgart'},
        {latLng: [51.23, 6.78], name: 'Düsseldorf'},
        {latLng: [51.51, 7.46], name: 'Dortmund'},
        {latLng: [51.45, 7.01], name: 'Essen'},
        {latLng: [53.07, 8.80], name: 'Bremen'}
    ],
    cityAreaData = [
        887.70,
        755.16,
        310.69,
        405.17,
        248.31,
        207.35,
        217.22,
        280.71,
        210.32,
        325.42
    ]

    // Configuration
    var map = new jvm.WorldMap({
        container: $('.map-regions'),
        map: 'de_mill_en',
        backgroundColor: 'transparent',
        regionsSelectable: true,
        markersSelectable: true,
        markers: markers,
        markerStyle: {
            initial: {
                'fill': '#E77644',
                'stroke': '#fff',
                'stroke-width' : 1.5,
                'stroke-opacity': 0.9
            },
            hover: {
                'stroke': '#fff',
                'fill-opacity': 1,
                'stroke-width': 1.5
            },
            selected: {
                'fill': '#CA0020'
            }
        },
        regionStyle: {
            initial: {
                "stroke-width": 1.5,
                'stroke': '#fff',
                'fill': '#93D389'
            },
            selected: {
                'fill': '#00a2ca'
            }
        },
        series: {
            markers: [{
                attribute: 'r',
                scale: [5, 15],
                values: cityAreaData
            }]
        },
        onRegionSelected: function(){
            if (window.localStorage) {
                window.localStorage.setItem(
                    'jvectormap-selected-regions',
                    JSON.stringify(map.getSelectedRegions())
                );
            }
        },
        onMarkerSelected: function(){
            if (window.localStorage) {
                window.localStorage.setItem(
                    'jvectormap-selected-markers',
                    JSON.stringify(map.getSelectedMarkers())
                );
            }
        }
    });

    // Set regions
    map.setSelectedRegions( JSON.parse( window.localStorage.getItem('jvectormap-selected-regions') || '[]' ) );
    map.setSelectedMarkers( JSON.parse( window.localStorage.getItem('jvectormap-selected-markers') || '[]' ) );

});
