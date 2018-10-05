/* ------------------------------------------------------------------------------
 *
 *  # D3.js - normalized bar chart
 *
 *  Demo d3.js normalized bar chart setup with .csv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    barNormalized('#d3-bar-normalized', 400);

    // Chart setup
    function barNormalized(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 130, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.ordinal()
            .rangeRoundBands([0, width], .1);

        // Vertical
        var y = d3.scale.linear()
            .rangeRound([height, 0]);

        // Color
        var color = d3.scale.ordinal()
            .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .tickFormat(d3.format(".0%"));



        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3Container.append("svg");

        // Add SVG group
        var svg = container
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");



        // Load data
        // ------------------------------

        d3.csv("assets/demo_data/d3/bars/bars_stacked.csv", function(error, data) {

            // Filter values by key
            color.domain(d3.keys(data[0]).filter(function(key) { return key !== "State"; }));

            // Pull out values
            data.forEach(function(d) {
                var y0 = 0;
                d.ages = color.domain().map(function(name) { return {name: name, y0: y0, y1: y0 += +d[name]}; });
                d.ages.forEach(function(d) { d.y0 /= y0; d.y1 /= y0; });
            });

            // Sort data
            data.sort(function(a, b) { return b.ages[0].y1 - a.ages[0].y1; });


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(data.map(function(d) { return d.State; }));



            //
            // Append chart elements
            //

            // Append axes
            // ------------------------------

            // Horizontal
            svg.append("g")
                .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong")
                .attr("transform", "translate(0," + height + ")")
                .call(xAxis);

            // Vertical
            svg.append("g")
                .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
                .call(yAxis);



            // Add bars
            // ------------------------------

            // Group values
            var state = svg.selectAll(".bar-group")
                .data(data)
                .enter()
                .append("g")
                    .attr("class", "bar-group")
                    .attr("transform", function(d) { return "translate(" + x(d.State) + ",0)"; });

            // Append bars
            state.selectAll(".d3-bar")
                .data(function(d) { return d.ages; })
                .enter()
                .append("rect")
                    .attr("class", "d3-bar")
                    .attr("width", x.rangeBand())
                    .attr("y", function(d) { return y(d.y1); })
                    .attr("height", function(d) { return y(d.y0) - y(d.y1); })
                    .style("fill", function(d) { return color(d.name); });



            // Add legend
            // ------------------------------

            // Create legend
            var legend = svg.select(".bar-group:last-child")
                .selectAll(".d3-legend")
                .data(function(d) { return d.ages; })
                .enter().append("g")
                .attr("class", "d3-legend")
                .attr("transform", function(d) { return "translate(" + x.rangeBand() + "," + y((d.y0 + d.y1) / 2) + ")"; });

            // Legend line
            legend.append("line")
                .attr("x2", 10)
                .attr("stroke", "#333")
                .attr("shape-rendering", "crispEdges");

            // Legend text
            legend.append("text")
                .attr("x", 15)
                .attr("dy", ".35em")
                .text(function(d) { return d.name; });
        });




        // Resize chart
        // ------------------------------

        // Call function on window resize
        $(window).on('resize', resize);

        // Call function on sidebar width change
        $('.sidebar-control').on('click', resize);

        // Resize function
        // 
        // Since D3 doesn't support SVG resize by default,
        // we need to manually specify parts of the graph that need to 
        // be updated on window resize
        function resize() {

            // Layout variables
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;


            // Layout
            // -------------------------

            // Main svg width
            container.attr("width", width + margin.left + margin.right);

            // Width of appended group
            svg.attr("width", width + margin.left + margin.right);


            // Axes
            // -------------------------

            // Horizontal ranges
            x.rangeRoundBands([0, width], .1);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Bar group
            svg.selectAll('.bar-group').attr("transform", function(d) { return "translate(" + x(d.State) + ",0)"; });

            // Bars
            svg.selectAll('.d3-bar').attr("width", x.rangeBand())

            // Legend
            svg.selectAll(".d3-legend").attr("transform", function(d) { return "translate(" + x.rangeBand() + "," + y((d.y0 + d.y1) / 2) + ")"; });
        }
    }
});
