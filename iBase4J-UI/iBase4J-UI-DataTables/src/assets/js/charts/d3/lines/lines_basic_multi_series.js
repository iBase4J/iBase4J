/* ------------------------------------------------------------------------------
 *
 *  # D3.js - multi series line chart
 *
 *  Demo d3.js multi series line chart setup with .tsv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    lineBasic('#d3-line-multi-series', 400);

    // Chart setup
    function lineBasic(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 100, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Format data
        var parseDate = d3.time.format("%Y%m%d").parse;

        // Colors
        var color = d3.scale.category10();



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
            .ticks(5)
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
            .y(function(d) { return y(d.temperature); });



        // Load data
        // ------------------------------

        d3.tsv("assets/demo_data/d3/lines/lines_multi_series.tsv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = parseDate(d.date);
            });


            // Set color domains
            // ------------------------------

            // Filter by date
            color.domain(d3.keys(data[0]).filter(function(key) { return key !== "date"; }));

            // Set colors
            var cities = color.domain().map(function(name) {
                return {
                    name: name,
                    values: data.map(function(d) {
                        return {date: d.date, temperature: +d[name]};
                    })
                }
            });



            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.extent(data, function(d) { return d.date; }));

            // Vertical
            y.domain([
                d3.min(cities, function(c) { return d3.min(c.values, function(v) { return v.temperature; }); }),
                d3.max(cities, function(c) { return d3.max(c.values, function(v) { return v.temperature; }); })
            ]);



            //
            // Append chart elements
            //

            // Bind data
            var city = svg.selectAll(".multiline-city")
                .data(cities)
                .enter()
                .append("g")
                    .attr("class", "multiline-city");

            // Add line
            city.append("path")
                .attr("class", "d3-line d3-line-medium")
                .attr("d", function(d) { return line(d.values); })
                .style("stroke", function(d) { return color(d.name); });

            // Add text
            city.append("text")
                .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
                .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.temperature) + ")"; })
                .attr("class", "d3-cities")
                .attr("x", 10)
                .attr("dy", ".35em")
                .text(function(d) { return d.name; });
        


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
                .text("Temperature (ºF)");
        })



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
            svg.selectAll('.d3-line').attr("d", function(d) { return line(d.values); });

            // Text
            svg.selectAll('.d3-cities').attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.temperature) + ")"; })
        }
    }
});
