/* ------------------------------------------------------------------------------
 *
 *  # Dimple.js - pies as scatter
 *
 *  Demo of scatter chart with pies. Data stored in .tsv file format
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Construct chart
    var svg = dimple.newSvg("#dimple-pie-scatter", "100%", 500);


    // Chart setup
    // ------------------------------

    d3.tsv("assets/demo_data/dimple/demo_data.tsv", function (data) {

        // Filter data
        data = dimple.filterData(data, "Date", "01/12/2011");


        // Create chart
        // ------------------------------

        // Define chart
        var myChart = new dimple.chart(svg, data);

        // Set bounds
        myChart.setBounds(0, 0, "100%", "100%");

        // Set margins
        myChart.setMargins(60, 35, 10, 45);


        // Add axes
        // ------------------------------

        // Horizontal
        var x = myChart.addMeasureAxis("x", "Price Monthly Change");

        // Vertical
        var y = myChart.addMeasureAxis("y", "Unit Sales Monthly Change");

        // Other axes
        myChart.addMeasureAxis("p", "Operating Profit");


        // Construct layout
        // ------------------------------

        // Add pie
        var pies = myChart.addSeries(["Owner", "Channel"], dimple.plot.pie);

        // Radius
        pies.radius = 40;


        // Add legend
        // ------------------------------

        var myLegend = myChart.addLegend(0, 5, "100%", 0, "right");


        // Add styles
        // ------------------------------

        // Font size
        x.fontSize = "12";
        y.fontSize = "12";

        // Font family
        x.fontFamily = "Roboto";
        y.fontFamily = "Roboto";

        // Legend font style
        myLegend.fontSize = "12";
        myLegend.fontFamily = "Roboto";


        //
        // Draw chart
        //

        // Draw
        myChart.draw();


        // Resize chart
        // ------------------------------

        // Add a method to draw the chart on resize of the window
        $(window).on('resize', resize);
        $('.sidebar-control').on('click', resize);

        // Resize function
        function resize() {

            // Redraw chart
            myChart.draw(0, true);
        }
    });
});