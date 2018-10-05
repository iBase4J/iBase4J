/* ------------------------------------------------------------------------------
 *
 *  # D3.js - stacked and multiple bars
 *
 *  Demo d3.js bar chart setup with animated transition between stacked and multiple bars
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Create Uniform checkbox
    $(".stacked-multiple").uniform({
        radioClass: 'choice'
    });


    // Initialize chart
    stackedMultiples('#d3-bar-stacked-multiples', 400);

    // Chart setup
    function stackedMultiples(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 60},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;


        // Format data
        var parseDate = d3.time.format("%Y-%m").parse,
            formatYear = d3.format("02d"),
            formatDate = function(d) { return "Q" + ((d.getMonth() / 3 | 0) + 1) + formatYear(d.getFullYear() % 100); };



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.ordinal()
            .rangeRoundBands([0, width], .2);

        // Vertical
        var y = d3.scale.ordinal()
            .rangeRoundBands([height, 0]);

        var y0 = d3.scale.ordinal()
            .rangeRoundBands([height, 0]);

        var y1 = d3.scale.linear();

        // Colors
        var color = d3.scale.category20();



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom")
            .tickFormat(formatDate);

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .ticks(10, "%");



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

        // Nest
        var nest = d3.nest()
            .key(function(d) { return d.browser; });

        // Stack
        var stack = d3.layout.stack()
            .values(function(d) { return d.values; })
            .x(function(d) { return d.date; })
            .y(function(d) { return d.value; })
            .out(function(d, y0) { d.valueOffset = y0; });




        // Load data
        // ------------------------------

        d3.tsv("assets/demo_data/d3/bars/bars_stacked_multiple.tsv", function(error, data) {

            // Pull out values
            data.forEach(function(d) {
                d.date = parseDate(d.date);
                d.value = +d.value;
            });

            // Nest values
            var dataByGroup = nest.entries(data);


            // Set input domains
            // ------------------------------

            // Stack
            stack(dataByGroup);

            // Horizontal
            x.domain(dataByGroup[0].values.map(function(d) { return d.date; }));

            // Vertical
            y0.domain(dataByGroup.map(function(d) { return d.key; }));
            y1.domain([0, d3.max(data, function(d) { return d.value; })]).range([y0.rangeBand(), 0]);


            //
            // Append chart elements
            //

            // Add bars
            // ------------------------------

            // Group bars
            var group = svg.selectAll(".d3-bar-group")
                .data(dataByGroup)
                .enter()
                .append("g")
                    .attr("class", "d3-bar-group")
                    .attr("transform", function(d) { return "translate(0," + y0(d.key) + ")"; });

            // Append text
            group.append("text")
                .attr("class", "d3-group-label")
                .attr("x", -12)
                .attr("y", function(d) { return y1(d.values[0].value / 2); })
                .attr("dy", ".35em")
                .style("text-anchor", "end")
                .text(function(d) { return d.key; });

            // Add bars
            group.selectAll(".d3-bar")
                .data(function(d) { return d.values; })
                .enter()
                .append("rect")
                    .attr("class", "d3-bar")
                    .attr("x", function(d) { return x(d.date); })
                    .attr("y", function(d) { return y1(d.value); })
                    .attr("width", x.rangeBand())
                    .attr("height", function(d) { return y0.rangeBand() - y1(d.value); })
                    .style("fill", function(d) { return color(d.browser); });


            // Append axes
            // ------------------------------

            // Horizontal
            group.filter(function(d, i) { return !i; }).append("g")
                .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong")
                .attr("transform", "translate(0," + (y0.rangeBand() + 1) + ")")
                .call(xAxis);

            // Vertical
            var verticalAxis = svg.append("g")
                .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
                .call(yAxis);

            // Appent text label
            verticalAxis.append("text")
                .attr('class', 'browser-label')
                .attr("x", -12)
                .attr("y", 12)
                .attr("dy", ".71em")
                .style("text-anchor", "end")
                .style("fill", "#999")
                .style("font-size", 12)
                .text("Browser");


            // Setup layout change
            // ------------------------------

            // Add change event
            d3.selectAll(".stacked-multiple").on("change", change);

            // Change value on page load
            var timeout = setTimeout(function() {
                d3.select("input[value=\"stacked\"]").property("checked", true).each(change);
                $.uniform.update();
            }, 2000);

            // Change function
            function change() {
                clearTimeout(timeout);
                if (this.value === "multiples") transitionMultiples();
                else transitionStacked();
            }

            // Transition to multiples
            function transitionMultiples() {
                var t = svg.transition().duration(750),
                g = t.selectAll(".d3-bar-group").attr("transform", function(d) { return "translate(0," + y0(d.key) + ")"; });
                g.selectAll(".d3-bar").attr("y", function(d) { return y1(d.value); });
                g.select(".d3-group-label").attr("y", function(d) { return y1(d.values[0].value / 2); })
            }

            // Transition to stacked
            function transitionStacked() {
                var t = svg.transition().duration(750),
                g = t.selectAll(".d3-bar-group").attr("transform", "translate(0," + y0(y0.domain()[0]) + ")");
                g.selectAll(".d3-bar").attr("y", function(d) { return y1(d.value + d.valueOffset) });
                g.select(".d3-group-label").attr("y", function(d) { return y1(d.values[0].value / 2 + d.values[0].valueOffset); })
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
            x.rangeRoundBands([0, width], .2);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Line path
            svg.selectAll('.d3-bar').attr("x", function(d) { return x(d.date); }).attr("width", x.rangeBand());
        }
    }
});
