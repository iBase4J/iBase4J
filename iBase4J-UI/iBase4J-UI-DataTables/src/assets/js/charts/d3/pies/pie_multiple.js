/* ------------------------------------------------------------------------------
 *
 *  # D3.js - multiple pie charts
 *
 *  Demo d3.js multiple pie charts setup
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    pieMultiple('#d3-pie-multiple', 110, 10);

    // Chart setup
    function pieMultiple(element, radius, margin) {


        // Basic setup
        // ------------------------------

        // Define the data as a two-dimensional array of numbers
        var data = [
            [11975,  5871, 8916, 2868],
            [ 1951, 10048, 2060, 6171],
            [ 8010, 16145, 8090, 8045],
            [ 1013,   990,  940, 6907]
        ];

        // Colors
        var colors = d3.scale.category10();


        // Create chart
        // ------------------------------

        // Insert an svg element (with margin) for each row in our dataset
        var svg = d3.select(element)
            .selectAll("svg")
            .data(data)
            .enter()
            .append("svg")
                .attr("width", (radius + margin) * 2)
                .attr("height", (radius + margin) * 2)
                .append("g")
                    .attr("class", "d3-arc")
                    .attr("transform", "translate(" + (radius + margin) + "," + (radius + margin) + ")");


        // Construct chart layout
        // ------------------------------

        // Arc
        var arc = d3.svg.arc()
            .innerRadius(0)
            .outerRadius(radius);


        //
        // Append chart elements
        //

        // The data for each svg element is a row of numbers (an array)
        svg.selectAll("path")
            .data(d3.layout.pie())
            .enter()
            .append("path")
                .attr("d", arc)
                .style("stroke", "#fff")
                .style("fill", function(d, i) { return colors(i); });
    }
});
