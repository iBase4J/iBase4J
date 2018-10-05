/* ------------------------------------------------------------------------------
 *
 *  # Dimple.js - single horizontal grouped line
 *
 *  Demo of a grouped line chart. Data stored in .tsv file format
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Construct chart
    var svg = dimple.newSvg("#dimple-line-horizontal-single-grouped", "100%", 500);


    // Chart setup
    // ------------------------------

    d3.tsv("assets/demo_data/dimple/demo_data.tsv", function (data) {

        // Filter data
        data = dimple.filterData(data, "Owner", ["Aperture", "Black Mesa"]);


        // Create chart
        // ------------------------------

        // Define chart
        var myChart = new dimple.chart(svg, data);

        // Set bounds
        myChart.setBounds(0, 0, "100%", "100%");

        // Set margins
        myChart.setMargins(40, 10, 0, 30);


        // Create axes
        // ------------------------------

        // Horizontal
        var x = myChart.addCategoryAxis("x", ["Owner", "Month"]);
            x.addGroupOrderRule("Date");

        // Vertical
        var y = myChart.addMeasureAxis("y", "Unit Sales");


        // Construct layout
        // ------------------------------

        // Add line
        var s = myChart
            .addSeries("Owner", dimple.plot.line)
            .interpolation = "basis";

        // Bar space
        s.barGap = 0.05;


        // Add styles
        // ------------------------------

        // Font size
        x.fontSize = "12";
        y.fontSize = "12";

        // Font family
        x.fontFamily = "Roboto";
        y.fontFamily = "Roboto";


        //
        // Draw chart
        //

        // Draw
        myChart.draw();

        // Remove axis titles
        x.titleShape.remove();
        y.titleShape.remove();


        // Resize chart
        // ------------------------------

        // Add a method to draw the chart on resize of the window
        $(window).on('resize', resize);
        $('.sidebar-control').on('click', resize);

        // Resize function
        function resize() {
            setTimeout(function() {

                // Redraw chart
                myChart.draw(0, true);

                // Remove axis titles
                x.titleShape.remove();
                y.titleShape.remove();
            }, 100)
        }
    });
});
