/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - trendlines
 *
 *  Google Visualization trendline chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Trendline chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawTrendline);


// Chart settings
function drawTrendline() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Week', 'Bugs', 'Tests'],
        [1, 175, 10],
        [2, 159, 20],
        [3, 126, 35],
        [4, 129, 40],
        [5, 108, 60],
        [6, 92, 70],
        [7, 55, 72],
        [8, 50, 97]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 400,
        curveType: 'function',
        fontSize: 12,
        chartArea: {
            left: '5%',
            width: '92%',
            height: 350
        },
        hAxis: {
            format: '#',
            viewWindow: {min: 0, max: 9},
            gridlines: {count: 10}
        },
        vAxis: {
            title: 'Bugs and tests',
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
        colors: ['#6D4C41', '#FB8C00'],
        trendlines: {
            0: {
                labelInLegend: 'Bug line',
                visibleInLegend: true,
            },
            1: {
                labelInLegend: 'Test line',
                visibleInLegend: true,
            }
        },
        legend: {
            position: 'top',
            alignment: 'end',
            textStyle: {
                fontSize: 12
            }
        }
    };


    // Draw chart
    var trendline = new google.visualization.ColumnChart($('#google-trendline')[0]);
    trendline.draw(data, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawTrendline();
    }
});
