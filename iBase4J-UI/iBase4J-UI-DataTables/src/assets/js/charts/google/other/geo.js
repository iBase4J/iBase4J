/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - geo chart
 *
 *  Google Visualization geo chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Geo chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["geochart"]});
google.setOnLoadCallback(drawRegionsMap);


// Chart settings
function drawRegionsMap() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Country', 'Popularity'],
        ['Germany', 200],
        ['United States', 300],
        ['Brazil', 400],
        ['Canada', 500],
        ['France', 600],
        ['RU', 700]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 500,
        width: "100%",
        fontSize: 12,
        tooltip: {
            textStyle: {
                fontName: 'Roboto',
                fontSize: 13
            }
        }
    };


    // Draw chart
    var chart = new google.visualization.GeoChart($('#google-geo-region')[0]);
    chart.draw(data, options);
}
