/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - scatter
 *
 *  Google Visualization scatter chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Scatter chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawScatter);


// Chart settings
function drawScatter() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Age', 'Weight'],
        [ 8,      12],
        [ 4,      6],
        [ 11,     14],
        [ 4,      5],
        [ 3,      3.5],
        [ 6.5,    7],
        [ 7,    10],
        [ 6.5,    12],
        [ 6,    13],
        [ 8,    16],
        [ 12,    17],
        [ 18,    8],
        [ 18,    9],
        [ 16,    12]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 450,
        fontSize: 12,
        chartArea: {
            left: '5%',
            width: '90%',
            height: 400
        },
        tooltip: {
            textStyle: {
                fontName: 'Roboto',
                fontSize: 13
            }
        },
        hAxis: {
            minValue: 0,
            maxValue: 15
        },
        vAxis: {
            title: 'Weight',
            titleTextStyle: {
                fontSize: 13,
                italic: false
            },
            gridlines:{
                color: '#e5e5e5',
                count: 10
            },
            minValue: 0,
            maxValue: 15
        },
        legend: 'none',
        pointSize: 10,
        colors: ['#E53935']
    };


    // Draw chart
    var scatter = new google.visualization.ScatterChart($('#google-scatter')[0]);
    scatter.draw(data, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawScatter();
    }
});
