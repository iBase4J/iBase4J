/* ------------------------------------------------------------------------------
 *
 *  # D3.js - basic area chart
 *
 *  Demo d3.js area chart setup with .tsv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    areaBasic('#d3-area-basic', 400);

    // Chart setup
    function areaBasic(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 10, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Format data
        var parseDate = d3.time.format("%d-%b-%y").parse;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.time.scale()
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .range([height, 0]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom")
            .ticks(6)
            .tickFormat(d3.time.format("%b"));

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

        // Area
        var area = d3.svg.area()
            .x(function(d) { return x(d.date); })
            .y0(height)
            .y1(function(d) { return y(d.close); });



        // Load data
        // ------------------------------

        d3.tsv("assets/demo_data/d3/lines/lines_basic.tsv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = parseDate(d.date);
                d.close = +d.close;
            });


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.extent(data, function(d) { return d.date; }));

            // Vertical
            y.domain([0, d3.max(data, function(d) { return d.close; })]);


            //
            // Append chart elements
            //

            // Add area
            svg.append("path")
                .datum(data)
                .attr("class", "d3-area")
                .attr("fill", "#29B6F6")
                .attr("d", area);


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
                .style("fill", "#999")
                .style("font-size", 12)
                .style("text-anchor", "end")
                .text("Price ($)");

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

            // Area path
            svg.selectAll('.d3-area').attr("d", area);
        }
}
});
