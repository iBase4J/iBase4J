/* ------------------------------------------------------------------------------
 *
 *  # Dimple.js - grouped multiple vertical lines
 *
 *  Demo of grouped multiple line chart. Data stored in .tsv file format
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Construct chart
    var svg = dimple.newSvg("#dimple-line-vertical-multiple-grouped", "100%", 500);


    // Chart setup
    // ------------------------------

    d3.tsv("assets/demo_data/dimple/demo_data.tsv", function (data) {

        // Filter data
        data = dimple.filterData(data, "Owner", ["Aperture", "Black Mesa"])


        // Create chart
        // ------------------------------

        // Define chart
        var myChart = new dimple.chart(svg, data);

        // Set bounds
        myChart.setBounds(0, 0, "100%", "100%");

        // Set margins
        myChart.setMargins(70, 0, 90, 30);


        // Create axes
        // ------------------------------

        // Horizontal
        var x = myChart.addMeasureAxis("x", "Unit Sales");

        // Vertical
        var y = myChart.addCategoryAxis("y", ["Owner", "Month"]);
            y.addGroupOrderRule("Date");


        // Construct layout
        // ------------------------------

        // Add line
        var s = myChart
            .addSeries(["Brand"], dimple.plot.line)
            .interpolation = "basis";

        // Bar space
        s.barGap = 0.05;


        // Add legend
        // ------------------------------

        var myLegend = myChart
            .addLegend("100%", 0, 0, "100%", "right");


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

        // Position legend text
        myLegend.shapes.selectAll("text").attr("dy", "1");

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

                // Position legend text
                myLegend.shapes.selectAll("text").attr("dy", "1");

                // Remove axis titles
                x.titleShape.remove();
                y.titleShape.remove();
            }, 100)
        }
    });
});