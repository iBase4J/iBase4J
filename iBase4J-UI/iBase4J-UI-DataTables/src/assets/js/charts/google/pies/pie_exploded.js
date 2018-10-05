/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - sliced pie
 *
 *  Google Visualization sliced pie chart demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Sliced pie chart
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawExplodedPie);


// Chart settings
function drawExplodedPie() {

    // Data
    var data = google.visualization.arrayToDataTable([
        ['Language', 'Speakers (in millions)'],
        ['Assamese', 13],
        ['Bengali', 83],
        ['Gujarati', 46],
        ['Hindi', 90],
        ['Kannada', 38],
        ['Maithili', 20],
        ['Malayalam', 33],
        ['Marathi', 72],
        ['Oriya', 33],
        ['Punjabi', 29],
        ['Tamil', 61],
        ['Telugu', 74],
        ['Urdu', 52]
    ]);


    // Options
    var options = {
        fontName: 'Roboto',
        height: 300,
        width: 540,
        chartArea: {
            left: 50,
            width: '90%',
            height: '90%'
        },
        pieSliceText: 'label',
        slices: {
            2: {offset: 0.15},
            8: {offset: 0.1},
            10: {offset: 0.15},
            11: {offset: 0.1}
        }
    };


    // Instantiate and draw our chart, passing in some options.
    var pie_exploded = new google.visualization.PieChart($('#google-pie-exploded')[0]);
    pie_exploded.draw(data, options);
}