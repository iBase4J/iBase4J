/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - bubbles
 *
 *  Google Visualization bubble chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Bubble chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawBubbleChart);


// Chart settings
function drawBubbleChart() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['ID', 'Life Expectancy', 'Fertility Rate', 'Region'],
        ['CAN',    82.66,              1.67,      'North America'],
        ['DEU',    79.84,              1.36,      'Europe'],
        ['DNK',    70.6,               1.84,      'Europe'],
        ['EGY',    72.73,              2.78,      'Middle East'],
        ['GBR',    75.05,              2,         'Europe'],
        ['IRN',    72.49,              0.7,       'Middle East'],
        ['IRQ',    68.09,              4.77,      'Middle East'],
        ['ISR',    81.55,              3.96,      'Middle East'],
        ['RUS',    68.6,               1.54,      'Europe'],
        ['USA',    78.09,              3.05,      'North America']
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
        vAxis: {
            title: 'Fertility Rate',
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
        bubble: {
          textStyle: {
            auraColor: 'none',
            color: '#fff'
          },
          stroke: '#fff'
        },
        legend: {
            position: 'top',
            alignment: 'center',
            textStyle: {
                fontSize: 12
            }
        }
    };


    // Draw chart
    var bubble = new google.visualization.BubbleChart($('#google-bubble')[0]);
    bubble.draw(data, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawBubbleChart();
    }
});
