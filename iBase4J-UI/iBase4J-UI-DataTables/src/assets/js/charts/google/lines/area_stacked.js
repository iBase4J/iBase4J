/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - stacked area
 *
 *  Google Visualization stacked area chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Stacked area
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawAreaStackedChart);


// Chart settings
function drawAreaStackedChart() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Year', 'Cars', 'Trucks', 'Drones', 'Segways'],
        ['2013',  870,  460, 310, 220],
        ['2014',  460,   720, 220, 460],
        ['2015',  930,  640, 340, 330],
        ['2016',  1000,  400, 180, 500]
    ]);

    // Options
    var options_area_stacked = {
        fontName: 'Roboto',
        height: 400,
        curveType: 'function',
        fontSize: 12,
        areaOpacity: 0.4,
        chartArea: {
            left: '5%',
            width: '90%',
            height: 350
        },
        isStacked: true,
        pointSize: 4,
        tooltip: {
            textStyle: {
                fontName: 'Roboto',
                fontSize: 13
            }
        },
        lineWidth: 1.5,
        vAxis: {
            title: 'Number values',
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

    // Draw chart
    var area_stacked_chart = new google.visualization.AreaChart($('#google-area-stacked')[0]);
    area_stacked_chart.draw(data, options_area_stacked);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawAreaStackedChart();
    }
});
