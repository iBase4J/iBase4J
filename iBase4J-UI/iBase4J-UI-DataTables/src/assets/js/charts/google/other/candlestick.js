/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - candlestick
 *
 *  Google Visualization candlestick chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Candlestick chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["sankey"]});
google.setOnLoadCallback(drawCandlestick);


// Chart settings
function drawCandlestick() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['1', 20, 28, 38, 45],
        ['2', 31, 38, 55, 66],
        ['3', 50, 55, 77, 80],
        ['4', 77, 77, 66, 50],
        ['5', 68, 66, 22, 15],
        ['6', 12, 25, 40, 60],
        ['7', 10, 69, 39, 90],
        ['8', 18, 56, 62, 80],
        ['9', 10, 12, 40, 59],
        ['10', 30, 36, 48, 55],
        ['11', 78, 66, 42, 35],
        ['12', 32, 35, 46, 32],
        ['13', 65, 23, 54, 23],
        ['14', 60, 54, 43, 30],
        ['15', 12, 23, 45, 50],
        ['16', 4, 15, 60, 45],
        ['17', 5, 23, 25, 40],
        ['18', 90, 56, 45, 23],
        ['19', 65, 55, 45, 25],
        ['20', 43, 35, 23, 2],
        ['21', 12, 36, 58, 69],
        ['22', 18, 26, 46, 60],
        ['23', 60, 56, 23, 10],
        ['24', 45, 23, 11, 1],
        ['25', 4, 19, 40, 65],
        ['26', 50, 40, 22, 12],
        ['27', 67, 55, 34, 20],
        ['28', 4, 12, 45, 68],
        ['29', 34, 35, 56, 60],
        ['30', 53, 20, 12, 2],
        ['31', 25, 35, 45, 55]

        // Treat first row as data as well.
    ], true);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 400,
        fontSize: 12,
        chartArea: {
            left: '5%',
            width: '92%',
            height: 350
        },
        colors: ['#546E7A'],
        candlestick: {
            risingColor: {
                fill: '#546E7A',
                stroke: '#546E7A'
            }
        },
        tooltip: {
            textStyle: {
                fontName: 'Roboto',
                fontSize: 13
            }
        },
        vAxis: {
            title: 'Income and Expenses',
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
        legend: 'none'
    };


    // Draw chart
    var candlestick = new google.visualization.CandlestickChart($('#google-candlestick')[0]);
    candlestick.draw(data, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawCandlestick();
    }
});
