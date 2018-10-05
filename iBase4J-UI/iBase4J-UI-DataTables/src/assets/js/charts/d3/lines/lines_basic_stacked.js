/* ------------------------------------------------------------------------------
 *
 *  # D3.js - stacked area chart
 *
 *  Demo d3.js stacked area chart setup with .tsv data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    areaStacked('#d3-area-stacked', 400);

    // Chart setup
    function areaStacked(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 10, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Format data
        var parseDate = d3.time.format("%y-%b-%d").parse,
            formatPercent = d3.format(".0%");

        // Colors
        var color = d3.scale.category20();


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
            .orient("left")
            .tickFormat(formatPercent);



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
            .y0(function(d) { return y(d.y0); })
            .y1(function(d) { return y(d.y0 + d.y); });

        // Stack
        var stack = d3.layout.stack()
            .values(function(d) { return d.values; });





        // Load data
        // ------------------------------

        d3.tsv("assets/demo_data/d3/lines/lines_stacked.tsv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = parseDate(d.date);
            });


            // Set color domains
            // ------------------------------

            // Filter by date
            color.domain(d3.keys(data[0]).filter(function(key) { return key !== "date"; }));

            // Set colors
            var browsers = stack(color.domain().map(function(name) {
                return {
                    name: name,
                    values: data.map(function(d) {
                        return {date: d.date, y: d[name] / 100};
                    })
                };
            }));


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(d3.extent(data, function(d) { return d.date; }));


            //
            // Append chart elements
            //

            // Bind data
            var browser = svg.selectAll(".browser")
                .data(browsers)
                .enter()
                .append("g")
                    .attr("class", "browser");

            // Add area
            browser.append("path")
                .attr("class", "d3-area")
                .attr("d", function(d) { return area(d.values); })
                .style("fill", function(d) { return color(d.name); });

            // Add text
            browser.append("text")
                .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
                .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.y0 + d.value.y / 2) + ")"; })
                .attr("class", "d3-browsers")
                .attr("x", -15)
                .attr("dy", ".35em")
                .style("fill", "#fff")
                .style("text-anchor", "end")
                .text(function(d) { return d.name; });


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
            svg.selectAll('.d3-area').attr("d", function(d) { return area(d.values); });

            // Text
            svg.selectAll('.d3-browsers').attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.y0 + d.value.y / 2) + ")"; });
        }
    }
});
