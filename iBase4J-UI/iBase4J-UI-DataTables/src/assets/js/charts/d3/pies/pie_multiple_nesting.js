/* ------------------------------------------------------------------------------
 *
 *  # D3.js - multiple nested pie charts
 *
 *  Demo d3.js multiple pie charts setup with nesting
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    pieMultipleNested('#d3-pie-nesting', 110, 10);

    // Chart setup
    function pieMultipleNested(element, radius, margin) {


        // Basic setup
        // ------------------------------

        // Main variables
        var marginTop = 20;

        // Colors
        var colors = d3.scale.category20c();


        // Load data
        // ------------------------------

        d3.csv("assets/demo_data/d3/pies/pies_nesting.csv", function(flights) {

            // Nest the flight data by originating airport
            var airports = d3.nest()
                .key(function(d) { return d.origin; })
                .entries(flights);


            // Create chart
            // ------------------------------

            // Insert an svg element (with margin) for each row in our dataset
            var svg = d3.select(element)
                .selectAll("svg")
                .data(airports)
                .enter()
                    .append("svg")
                        .attr("width", (radius + margin) * 2)
                        .attr("height", (radius + margin + marginTop) * 2)
                        .append("g")
                            .attr("transform", "translate(" + (radius + margin) + "," + (radius + margin + marginTop) + ")");



            // Construct chart layout
            // ------------------------------

            // Arc
            var arc = d3.svg.arc()
                .innerRadius(0)
                .outerRadius(radius);

            // Pie
            var pie = d3.layout.pie()
                .value(function(d) { return +d.count; })
                .sort(function(a, b) { return b.count - a.count; });


            //
            // Append chart elements
            //

            // Add a label for the airport
            svg.append("text")
                .attr("dy", ".35em")
                .attr("y", -130)
                .style("text-anchor", "middle")
                .style("font-weight", 500)
                .text(function(d) { return d.key; });


            // Pass the nested values to the pie layout
            var g = svg.selectAll("g")
                .data(function(d) { return pie(d.values); })
                .enter()
                .append("g")
                    .attr("class", "d3-arc");


            // Add a colored arc path, with a mouseover title showing the count
            g.append("path")
                .attr("d", arc)
                .style("stroke", "#fff")
                .style("fill", function(d) { return colors(d.data.carrier); })
                .append("title")
                    .text(function(d) { return d.data.carrier + ": " + d.data.count; });


            // Add a label to the larger arcs, translated to the arc centroid and rotated
            g.filter(function(d) { return d.endAngle - d.startAngle > .2; }).append("text")
                .attr("dy", ".35em")
                .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")rotate(" + angle(d) + ")"; })
                .style("fill", "#fff")
                .style("font-size", 12)
                .style("text-anchor", "middle")
                .text(function(d) { return d.data.carrier; });

            // Computes the label angle of an arc, converting from radians to degrees
            function angle(d) {
                var a = (d.startAngle + d.endAngle) * 90 / Math.PI - 90;
                return a > 90 ? a - 180 : a;
            }
        });
    }
});
