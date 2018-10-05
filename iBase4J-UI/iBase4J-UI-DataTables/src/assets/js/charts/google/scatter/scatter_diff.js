/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - diff scatter
 *
 *  Google Visualization diff scatter chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Diff scatter chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawScatterDiff);


// Chart settings
function drawScatterDiff() {

    // Old data
    var oldData = google.visualization.arrayToDataTable([
        ['', 'Medicine 1', 'Medicine 2'],
        [23, null, 12], [9, null, 39], [15, null, 28],
        [37, null, 30], [21, null, 14], [12, null, 18],
        [29, null, 34], [ 8, null, 12], [38, null, 28],
        [35, null, 12], [26, null, 10], [10, null, 29],
        [11, null, 10], [27, null, 38], [39, null, 17],
        [34, null, 20], [38, null,  5], [33, null, 27],
        [23, null, 39], [12, null, 10], [ 8, 15, null],
        [39, 15, null], [27, 31, null], [30, 24, null],
        [31, 39, null], [35,  6, null], [ 5,  5, null],
        [19, 39, null], [22,  8, null], [19, 23, null],
        [27, 20, null], [11,  6, null], [34, 33, null],
        [38,  8, null], [39, 29, null], [13, 23, null],
        [13, 36, null], [39,  6, null], [14, 37, null], [13, 39, null]
    ]);

    // New data
    var newData = google.visualization.arrayToDataTable([
        ['', 'Medicine 1', 'Medicine 2'],
        [22, null, 12], [7, null, 40], [14, null, 31],
        [37, null, 30], [18, null, 17], [9, null, 20],
        [26, null, 36], [5, null, 13], [36, null, 30],
        [35, null, 15], [24, null, 12], [7, null, 31],
        [10, null, 12], [24, null, 40], [37, null, 18],
        [32, null, 21], [35, null, 7], [31, null, 30],
        [21, null, 42], [12, null, 10], [10, 13, null],
        [40, 12, null], [28, 29, null], [32, 22, null],
        [31, 37, null], [38, 5, null], [6, 4, null],
        [21, 36, null], [22, 8, null], [21, 22, null],
        [28, 17, null], [12, 5, null], [37, 30, null],
        [41, 7, null], [41, 27, null], [15, 20, null],
        [14, 36, null], [42, 3, null], [14, 37, null], [15, 36, null]
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
            minValue: 0
        },
        vAxis: {
            gridlines: {
                color: '#e5e5e5',
                count: 5
            },
            minValue: 0
        },
        legend: {
        position: 'top',
        alignment: 'center',
            textStyle: {
                fontSize: 12
            }
        },
        pointSize: 10,
        diff: {
            oldData: {
                opacity: 0.5
            }
        },
    };


    // Attach chart to the DOM element
    var chartOldOpacity = new google.visualization.ScatterChart($('#google-scatter-diff')[0]);

    // Set data
    var diffData = chartOldOpacity.computeDiff(oldData, newData);

    // Draw our chart, passing in some options
    chartOldOpacity.draw(diffData, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawScatterDiff();
    }
});


