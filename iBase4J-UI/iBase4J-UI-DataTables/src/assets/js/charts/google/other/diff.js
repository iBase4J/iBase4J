/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - diff chart
 *
 *  Google Visualization diff chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Diff chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawDiff);


// Chart settings
function drawDiff() {

    // Old data
    var oldData = google.visualization.arrayToDataTable([
        ['Name', 'Popularity'],
        ['Cesar', 425],
        ['Rachel', 420],
        ['Patrick', 290],
        ['Eric', 620],
        ['Eugene', 520],
        ['John', 460],
        ['Greg', 420],
        ['Matt', 410]
    ]);

    // New data
    var newData = google.visualization.arrayToDataTable([
        ['Name', 'Popularity'],
        ['Cesar', 307],
        ['Rachel', 360],
        ['Patrick', 200],
        ['Eric', 550],
        ['Eugene', 460],
        ['John', 320],
        ['Greg', 390],
        ['Matt', 360]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 400,
        fontSize: 12,
        chartArea: {
            left: '5%',
            width: '90%',
            height: 350
        },
        colors: ['#4CAF50'],
        tooltip: {
            textStyle: {
                fontName: 'Roboto',
                fontSize: 13
            }
        },
        vAxis: {
            title: 'Popularity',
            titleTextStyle: {
                fontSize: 13,
                italic: false
            },
            gridlines:{
                color: '#e5e5e5',
                count: 10
            },
            minValue: 0
        },
        legend: {
            position: 'top',
            alignment: 'end',
            textStyle: {
                fontSize: 12
            }
        }
    };


    // Attach chart to the DOM element
    var diff = new google.visualization.ColumnChart($('#google-diff')[0]);

    // Set data
    var diffData = diff.computeDiff(oldData, newData);

    // Draw our chart, passing in some options
    diff.draw(diffData, options);
};


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawDiff();
    }
});
