/* ------------------------------------------------------------------------------
 *
 *  # Dimple.js - basic pie
 *
 *  Demo of pie chart. Data stored in .tsv file format
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Construct chart
    var svg = dimple.newSvg("#dimple-pie-basic", 420, 300);


    // Chart setup
    // ------------------------------

    d3.tsv("assets/demo_data/dimple/demo_data.tsv", function (data) {


        // Create chart
        // ------------------------------

        // Define chart
        var myChart = new dimple.chart(svg, data);

        // Set bounds
        myChart.setBounds(0, 0, "100%", "100%")

        // Set margins
        myChart.setMargins(5, 5, 5, 5);


        // Add axes
        // ------------------------------

        var p = myChart.addMeasureAxis("p", "Unit Sales");


        // Construct layout
        // ------------------------------

        // Add pie
        myChart.addSeries("Owner", dimple.plot.pie);


        // Add styles
        // ------------------------------

        // Font size
        p.fontSize = "12";

        // Font family
        p.fontFamily = "Roboto";


        //
        // Draw chart
        //

        // Draw
        myChart.draw();
    });
});