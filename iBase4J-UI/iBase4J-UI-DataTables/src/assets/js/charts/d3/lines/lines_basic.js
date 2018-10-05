/* ------------------------------------------------------------------------------
 *
 *  # D3.js - basic line chart
 *
 *  Demo d3.js line chart setup with tooltip and .tsv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    lineBasic('#d3-line-basic', 400);

    // Chart setup
    function lineBasic(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Format data
        var parseDate = d3.time.format("%d-%b-%y").parse,
            bisectDate = d3.bisector(function(d) { return d.date; }).left,
            formatValue = d3.format(",.2f"),
            formatCurrency = function(d) { return "$" + formatValue(d); }



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
            .x(function(d) { return x(d.date); })
            .y(function(d) { return y(d.close); });



        // Load data
        // ------------------------------

        d3.tsv("assets/demo_data/d3/lines/lines_basic.tsv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = parseDate(d.date);
                d.close = +d.close;
            });

            // Sort data
            data.sort(function(a, b) {
                return a.date - b.date;
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

            // Add line
            svg.append("path")
                .datum(data)
                    .attr("class", "d3-line d3-line-medium")
                    .attr("d", line)
                    .style("fill", "none")
                    .style("stroke-width", 2)
                    .style("stroke", "#4CAF50");



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
                .text("Price ($)");




            // Append tooltip
            // -------------------------

            // Group elements
            var focus = svg.append("g")
                .attr("class", "d3-crosshair-pointer")
                .style("display", "none");

            // Pointer
            focus.append("circle")
                .attr("r", 3.5)
                .style("fill", "#fff")
                .style("stroke", "#4CAF50")
                .style("stroke-width", 2);

            // Text
            focus.append("text")
                .attr("dy", ".35em")
                .style("fill", "#333")
                .style("stroke", "none")

            // Overlay
            svg.append("rect")
                .attr("class", "d3-crosshair-overlay")
                .attr("width", width)
                .attr("height", height)
                .on("mouseover", function() { focus.style("display", null); })
                .on("mouseout", function() { focus.style("display", "none"); })
                .on("mousemove", mousemove);

            // Display tooltip on mousemove
            function mousemove() {
                var x0 = x.invert(d3.mouse(this)[0]),
                i = bisectDate(data, x0, 1),
                d0 = data[i - 1],
                d1 = data[i],
                d = x0 - d0.date > d1.date - x0 ? d1 : d0;
                focus.attr("transform", "translate(" + x(d.date) + "," + y(d.close) + ")");
                focus.select("text").text(formatCurrency(d.close)).attr("dx", -26).attr("dy", 30);
            }
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
            svg.selectAll('.d3-line').attr("d", line);

            // Crosshair
            svg.selectAll('.d3-crosshair-overlay').attr("width", width);
        }
    }
});
