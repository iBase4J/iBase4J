/* ------------------------------------------------------------------------------
 *
 *  # D3.js - spline transition
 *
 *  Demo d3.js line chart setup with spline transition
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    splineTrransition('#d3-spline-transition', 400);

    // Chart setup
    function splineTrransition(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 5, bottom: 5, left: 30},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Add data
        var n = 50,
            random = d3.random.normal(0, .35),
            data = d3.range(n).map(random);


        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .domain([1, n - 2])
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .domain([-1, 1])
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



        // Construct chart layout
        // ------------------------------

        // Line
        var line = d3.svg.line()
            .interpolate("basis")
            .x(function(d, i) { return x(i); })
            .y(function(d, i) { return y(d); });



        //
        // Append chart elements
        //

        // Add mask
        svg.append("defs")
            .append("clipPath")
                .attr("id", "transition-clip")
                .append("rect")
                    .attr("width", width)
                    .attr("height", height);


        // Append axes
        // ------------------------------

        // Horizontal
        svg.append("g")
            .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong")
            .attr("transform", "translate(0," + y(0) + ")")
            .call(xAxis);

        // Vertical
        svg.append("g")
            .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
            .call(yAxis);


        // Add line
        var path = svg.append("g")
            .attr("clip-path", "url(#transition-clip)")
            .append("path")
                .datum(data)
                .attr("d", line)
                .attr("class", "d3-line d3-line-medium")
                .style("stroke", "#607D8B");


        // Transition
        // ------------------------------

        // Initialize transition
        tick();

        // Setup transition
        function tick() {

            // push a new data point onto the back
            data.push(random());

            // redraw the line, and slide it to the left
            path
                .attr("d", line)
                .attr("transform", null)
                .transition()
                    .duration(500)
                    .ease("linear")
                    .attr("transform", "translate(" + x(0) + ",0)")
                    .each("end", tick);

            // pop the old data point off the front
            data.shift();
        }



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

            // Crosshair
            svg.selectAll('#transition-clip rect').attr("width", width);
        }
    }
});
