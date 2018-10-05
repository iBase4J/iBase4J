/* ------------------------------------------------------------------------------
 *
 *  # D3.js - area chart with missing data
 *
 *  Demo d3.js area chart setup with tooltip and missing data
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    areaMissing('#d3-missing-data', 400);

    // Chart setup
    function areaMissing(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Data set
        var data = d3.range(60).map(function(i) {
          return {x: i / 59, y: i % 5 ? (Math.sin(i / 3) + 2) / 4 : null};
        });



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .range([height, 0]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left");


        // Add tooltip
        var tip = d3.tip()
            .attr('class', 'd3-tip')
            .offset([-10, 0])
            .html(function(d) {
                return d.x;
            });



        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3Container.append("svg");

        // Add SVG group
        var svg = container
            .datum(data)
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
                .call(tip);



        // Construct chart layout
        // ------------------------------

        // Line
        var line = d3.svg.line()
            .defined(function(d) { return d.y != null; })
            .x(function(d) { return x(d.x); })
            .y(function(d) { return y(d.y); });

        // Area
        var area = d3.svg.area()
            .defined(line.defined())
            .x(line.x())
            .y1(line.y())
            .y0(y(0));


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


        // Chart elements
        // ------------------------------

        // Add area
        svg.append("path")
            .attr("class", "d3-area")
            .attr("d", area)
            .style("fill", "#81C784");


        // Add line
        svg.append("path")
            .attr("class", "d3-line d3-line-medium")
            .attr("d", line)
            .style("stroke", "#43A047");

        // Add dots
        svg.selectAll(".d3-dot")
            .data(data.filter(function(d) { return d.y; }))
            .enter()
            .append("circle")
                .attr("class", "d3-dot")
                .attr("cx", line.x())
                .attr("cy", line.y())
                .attr("r", 3)
                .style("fill", "#fff")
                .style("stroke", "#43A047")
                .style("stroke-width", 1.5)
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide);



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

            // Horizontal range
            x.range([0, width]);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Line path
            svg.selectAll('.d3-line').attr("d", line);

            // Area path
            svg.selectAll('.d3-area').attr("d", area);

            // Dots
            svg.selectAll('.d3-dot').attr("cx", line.x());
        }
    }
});
