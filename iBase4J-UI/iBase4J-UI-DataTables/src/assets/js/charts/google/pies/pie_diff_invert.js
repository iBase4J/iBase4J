/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - diff pie
 *
 *  Google Visualization diff pie chart with inverted behaviour demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Diff pie chart
// ------------------------------

// Initialize chart
google.load("visualization", '1.1', {packages:['corechart']});
google.setOnLoadCallback(drawChart);


// Chart settings
function drawChart() {

    // Old data
    var oldData = google.visualization.arrayToDataTable([
        ['Major', 'Degrees'],
        ['Business', 256070], ['Education', 108034],
        ['Social Sciences & History', 127101], ['Health', 81863],
        ['Psychology', 74194]
    ]);

    // New data
    var newData = google.visualization.arrayToDataTable([
        ['Major', 'Degrees'],
        ['Business', 358293], ['Education', 101265],
        ['Social Sciences & History', 172780], ['Health', 129634],
        ['Psychology', 97216]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 300,
        width: 500,
        chartArea: {
            left: 50,
            width: '90%',
            height: '90%'
        },
        diff: {
            oldData: {
                inCenter: false
            }
        }
    };


    // Attach chart to the DOM element
    var chartRadius = new google.visualization.PieChart($('#google-pie-diff-invert')[0]);

    // Set data
    var diffData = chartRadius.computeDiff(oldData, newData);

    // Draw our chart, passing in some options
    chartRadius.draw(diffData, options);
}