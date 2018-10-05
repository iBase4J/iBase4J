/* ------------------------------------------------------------------------------
 *
 *  # D3.js - donut chart entry animation
 *
 *  Demo d3.js donut chart setup with .csv data source and loading animation
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    donutEntryAnimation('#d3-donut-entry-animation', 120);

    // Chart setup
    function donutEntryAnimation(element, radius) {


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
            .innerRadius(radius / 1.75);

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
                .style("fill", function(d) { return color(d.data.age); })
                .transition()
                    .ease("linear")
                    .duration(1000)
                    .attrTween("d", tweenPie);

            // Add text labels
            g.append("text")
                .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
                .attr("dy", ".35em")
                .style("opacity", 0)
                .style("fill", "#fff")
                .style("text-anchor", "middle")
                .text(function(d) { return d.data.age; })
                .transition()
                    .ease("linear")
                    .delay(1000)
                    .duration(500)
                    .style("opacity", 1);


            // Tween
            function tweenPie(b) {
                b.innerRadius = 0;
                var i = d3.interpolate({startAngle: 0, endAngle: 0}, b);
                return function(t) { return arc(i(t)); };
            }


            // Animate donut
            // ------------------------------

            $('.donut-animation').on('click', function (b) {

                // Remove old paths
                svg.selectAll("path").remove();

                // Arc path
                g.append("path")
                    .attr("d", arc)
                    .style("stroke", "#fff")
                    .style("fill", function(d) { return color(d.data.age); })
                    .transition()
                        .ease("linear")
                        .duration(1000)
                        .attrTween("d", tweenPie);

                // Text labels
                g.append("text")
                    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
                    .style("opacity", 0)
                    .style("fill", "#fff")
                    .attr("dy", ".35em")
                    .style("text-anchor", "middle")
                    .text(function(d) { return d.data.age; })
                    .transition()
                        .ease("linear")
                        .delay(1000)
                        .duration(500)
                        .style("opacity", 1);
            });
        });
    }
});
