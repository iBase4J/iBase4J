/* ------------------------------------------------------------------------------
 *
 *  # D3.js - grouped bar chart
 *
 *  Demo d3.js grouped bar chart setup with .csv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    barGrouped('#d3-bar-grouped', 400);

    // Chart setup
    function barGrouped(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 10, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x0 = d3.scale.ordinal()
            .rangeRoundBands([0, width], .1);

        var x1 = d3.scale.ordinal()
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .range([height, 0]);

        // Colors
        var color = d3.scale.ordinal()
        .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x0)
            .orient("bottom");

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .tickFormat(d3.format(".2s"));



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

        d3.csv("assets/demo_data/d3/bars/bars_grouped.csv", function(error, data) {

            // Filter values by key
            var ageNames = d3.keys(data[0]).filter(function(key) { return key !== "State"; });

            // Pull out values
            data.forEach(function(d) {
                d.ages = ageNames.map(function(name) { return {name: name, value: +d[name]}; });
            });


            // Set input domains
            // ------------------------------

            // Horizontal
            x0.domain(data.map(function(d) { return d.State; }));
            x1.domain(ageNames).rangeRoundBands([0, x0.rangeBand()]);

            // Vertical
            y.domain([0, d3.max(data, function(d) { return d3.max(d.ages, function(d) { return d.value; }); })]);


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
            var verticalAxis = svg.append("g")
                .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
                .call(yAxis);

            // Add text label
            verticalAxis.append("text")
                .attr("transform", "rotate(-90)")
                .attr("y", 10)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .style("fill", "#999")
                .style("font-size", 12)
                .text("Population");


            // Add bars
            // ------------------------------

            // Group values
            var state = svg.selectAll(".bar-group")
                .data(data)
                .enter()
                .append("g")
                    .attr("class", "bar-group")
                    .attr("transform", function(d) { return "translate(" + x0(d.State) + ",0)"; });

            // Append bars
            state.selectAll(".d3-bar")
                .data(function(d) { return d.ages; })
                .enter()
                .append("rect")
                    .attr("class", "d3-bar")
                    .attr("width", x1.rangeBand())
                    .attr("x", function(d) { return x1(d.name); })
                    .attr("y", function(d) { return y(d.value); })
                    .attr("height", function(d) { return height - y(d.value); })
                    .style("fill", function(d) { return color(d.name); });


            // Add legend
            // ------------------------------

            // Create legend
            var legend = svg.selectAll(".d3-legend")
                .data(ageNames.slice().reverse())
                .enter()
                .append("g")
                    .attr("class", "d3-legend")
                    .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });

            // Legend indicator
            legend.append("rect")
                .attr("x", width - 18)
                .attr("width", 18)
                .attr("height", 18)
                .style("fill", color);

            // Legend text
            legend.append("text")
                .attr("x", width - 24)
                .attr("y", 9)
                .attr("dy", ".35em")
                .style("text-anchor", "end")
                .style("font-size", 12)
                .text(function(d) { return d; });
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
            x0.rangeRoundBands([0, width], .1);
            x1.rangeRoundBands([0, x0.rangeBand()]);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Bar group
            svg.selectAll('.bar-group').attr("transform", function(d) { return "translate(" + x0(d.State) + ",0)"; });

            // Bars
            svg.selectAll('.d3-bar').attr("width", x1.rangeBand()).attr("x", function(d) { return x1(d.name); });

            // Legend
            svg.selectAll(".d3-legend text").attr("x", width - 24);
            svg.selectAll(".d3-legend rect").attr("x", width - 18);
        }
    }
});
