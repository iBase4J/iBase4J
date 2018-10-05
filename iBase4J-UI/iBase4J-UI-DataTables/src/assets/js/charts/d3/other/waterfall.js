/* ------------------------------------------------------------------------------
 *
 *  # D3.js - waterfall chart
 *
 *  Demo of waterfall chart setup with .csv data source and rotated axis labels
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    waterfall('#d3-waterfall', 400); // initialize chart

    // Chart setup
    function waterfall(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 10, bottom: 100, left: 50},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom,
            padding = 0.3;

        // Format data
        function dollarFormatter(n) {
            n = Math.round(n);
            var result = n;
            if (Math.abs(n) > 1000) {
                result = Math.round(n/1000) + 'K';
            }
            return '$' + result;
        }



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.ordinal()
            .rangeRoundBands([0, width], padding);

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
            .orient("left")
            .tickFormat(function(d) { return dollarFormatter(d); });



        // Create chart
        // ------------------------------

        // Container
        var container = d3Container.append("svg")

        // SVG element
        var svg = container
            .attr('width', width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");



        // Load data
        // ------------------------------

        d3.csv("assets/demo_data/d3/other/waterfall.csv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.value = +d.value;
            });

            // Transform data (i.e., finding cumulative values and total) for easier charting
            var cumulative = 0;
            for (var i = 0; i < data.length; i++) {
                data[i].start = cumulative;
                cumulative += data[i].value;
                data[i].end = cumulative;
                data[i].class = ( data[i].value >= 0 ) ? 'positive' : 'negative'
            }
            data.push({
                name: 'Total',
                end: cumulative,
                start: 0,
                class: 'total'
            });


            // Set input domains
            // ------------------------------

            // Horizontal
            x.domain(data.map(function(d) { return d.name; }));

            // Vertical
            y.domain([0, d3.max(data, function(d) { return d.end; })]);



            //
            // Append chart elements
            //

            // Append axes
            // ------------------------------

            // Horizontal
            svg.append("g")
                .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong")
                .attr("transform", "translate(0," + height + ")")
                .call(xAxis)
                .selectAll("text")  
                    .style("text-anchor", "end")
                    .attr("dx", "-15px")
                    .attr("dy", "-6px")
                    .attr("transform", function(d) {
                        return "rotate(-90)" 
                    });

            // Vertical
            svg.append("g")
                .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
                .call(yAxis);


            // Append bars
            // ------------------------------

            // Bind data
            var bar = svg.selectAll(".d3-waterfall-bar")
                .data(data)
                .enter()
                .append("g")
                    .attr("class", function(d) { return "d3-waterfall-bar " + d.class })
                    .attr("transform", function(d) { return "translate(" + x(d.name) + ",0)"; });

            // Append bar rects
            bar.append("rect")
                .attr("y", function(d) { return y( Math.max(d.start, d.end) ); })
                .attr("height", function(d) { return Math.abs( y(d.start) - y(d.end) ); })
                .attr("width", x.rangeBand());

            // Append text
            bar.append("text")
                .attr("x", x.rangeBand() / 2)
                .attr("y", function(d) { return y(d.end) + 5; })
                .attr("dy", function(d) { return ((d.class=='negative') ? '-' : '') + "1.5em" })
                .style("fill", "#fff")
                .style("text-anchor", "middle")
                .text(function(d) { return dollarFormatter(d.end - d.start);});

            // Apply colors
            bar.filter(function(d) { return d.class == "positive" }).select('rect').style("fill", "#EF5350");
            bar.filter(function(d) { return d.class == "negative" }).select('rect').style("fill", "#66BB6A");
            bar.filter(function(d) { return d.class == "total" }).select('rect').style("fill", "#42A5F5");

            // Add connector line
            bar.filter(function(d) { return d.class != "total" })
                .append("line")
                    .attr("class", "d3-waterfall-connector")
                    .attr("x1", x.rangeBand() + 5 )
                    .attr("y1", function(d) { return y(d.end) })
                    .attr("x2", x.rangeBand() / ( 1 - padding) - 5)
                    .attr("y2", function(d) { return y(d.end) })
                    .style("stroke", "#999")
                    .style("stroke-dasharray", 3);
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
            x.rangeRoundBands([0, width], padding);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis).selectAll('text').style("text-anchor", "end").attr("dy", "-6px");


            // Chart elements
            // -------------------------

            // Bar group
            svg.selectAll(".d3-waterfall-bar").attr("transform", function(d) { return "translate(" + x(d.name) + ",0)"; });

            // Bar rect
            svg.selectAll(".d3-waterfall-bar rect").attr("width", x.rangeBand());

            // Bar text
            svg.selectAll(".d3-waterfall-bar text").attr("x", x.rangeBand() / 2);

            // Connector line
            svg.selectAll(".d3-waterfall-connector").attr("x1", x.rangeBand() + 5 ).attr("x2", x.rangeBand() / ( 1 - padding) - 5 );
        }
    }
});