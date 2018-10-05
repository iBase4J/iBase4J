/* ------------------------------------------------------------------------------
 *
 *  # D3.js - basic pie chart
 *
 *  Demo d3.js pie chart setup with .csv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    pieBasic('#d3-pie-basic', 120);

    // Chart setup
    function pieBasic(element, radius) {


        // Basic setup
        // ------------------------------

        // Colors
        var color = d3.scale.category20();


        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3.select(element).append("svg");

        // Add SVG group
        var svg = container
            .attr("width", radius * 2)
            .attr("height", radius * 2)
            .append("g")
                .attr("transform", "translate(" + radius + "," + radius + ")");


        // Construct chart layout
        // ------------------------------

        // Arc
        var arc = d3.svg.arc()
            .outerRadius(radius)
            .innerRadius(0);

        // Pie
        var pie = d3.layout.pie()
            .sort(null)
            .value(function(d) { return d.population; });


        // Load data
        // ------------------------------

        d3.csv("assets/demo_data/d3/pies/pies_basic.csv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.population = +d.population;
            });


            //
            // Append chart elements
            //

            // Bind data
            var g = svg.selectAll(".d3-arc")
                .data(pie(data))
                .enter()
                .append("g")
                    .attr("class", "d3-arc");

            // Add arc path
            g.append("path")
                .attr("d", arc)
                .style("stroke", "#fff")
                .style("fill", function(d) { return color(d.data.age); });

            // Add text labels
            g.append("text")
                .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
                .attr("dy", ".35em")
                .style("fill", "#fff")
                .style("font-size", 12)
                .style("text-anchor", "middle")
                .text(function(d) { return d.data.age; });
        });
    }
});
