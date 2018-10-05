/* ------------------------------------------------------------------------------
 *
 *  # D3.js - stacked nested area chart
 *
 *  Demo d3.js stacked nested area chart setup with .tsv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    areaNested('#d3-area-stacked-nest', 400);

    // Chart setup
    function areaNested(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 40},
            n = 3,
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Format data
        var format = d3.time.format("%m/%d/%y");



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.time.scale()
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .range([height, 0]);

        // Colors
        var z = d3.scale.linear()
            .domain([0, n - 1])
            .range(["#4DB6AC", "#B2DFDB"]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom")
            .ticks(d3.time.days);

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left");



        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3.select(element).append("svg");

        // Add SVG group
        var svg = container
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");



        // Construct chart layout
        // ------------------------------

        // Stack
        var stack = d3.layout.stack()
            .offset("zero")
            .values(function(d) { return d.values; })
            .x(function(d) { return d.date; })
            .y(function(d) { return d.value; });

        // Nest
        var nest = d3.nest()
            .key(function(d) { return d.key; });

        // Area
        var area = d3.svg.area()
            .interpolate("basis")
            .x(function(d) { return x(d.date); })
            .y0(function(d) { return y(d.y0); })
            .y1(function(d) { return y(d.y0 + d.y); });




        // Load data
        // ------------------------------

        d3.csv("assets/demo_data/d3/lines/lines_stacked_nest.csv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = format.parse(d.date);
                d.value = +d.value;
            });

            // Pull out nested entries
            var layers = stack(nest.entries(data));


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.extent(data, function(d) { return d.date; }));

            // Vertical
            y.domain([0, d3.max(data, function(d) { return d.y0 + d.y; })]);


            //
            // Append chart elements
            //

            // Add area
            svg.selectAll(".d3-area")
                .data(layers)
                .enter()
                .append("path")
                .attr("class", "d3-area")
                .attr("d", function(d) { return area(d.values); })
                .style("stroke", "#fff")
                .style("stroke-width", 0.5)
                .style("fill", function(d, i) { return z(i); });


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

            // Horizontal range
            x.range([0, width]);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Line path
            svg.selectAll('.d3-area').attr("d", function(d) { return area(d.values); })
        }
    }
});
