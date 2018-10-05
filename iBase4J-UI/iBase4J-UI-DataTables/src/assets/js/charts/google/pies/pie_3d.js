/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - 3D pie
 *
 *  Google Visualization 3D pie chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// 3D pie chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawPie3d);


// Chart settings
function drawPie3d() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['Work',     11],
        ['Eat',      2],
        ['Commute',  2],
        ['Watch TV', 2],
        ['Sleep',    7]
    ]);


    // Options
    var options_pie_3d = {
        fontName: 'Roboto',
        is3D: true,
        height: 300,
        width: 540,
        chartArea: {
            left: 50,
            width: '95%',
            height: '95%'
        }
    };


    // Instantiate and draw our chart, passing in some options.
    var pie_3d = new google.visualization.PieChart($('#google-pie-3d')[0]);
    pie_3d.draw(data, options_pie_3d);
}