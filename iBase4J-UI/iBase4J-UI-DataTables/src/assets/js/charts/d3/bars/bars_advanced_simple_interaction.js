/* ------------------------------------------------------------------------------
 *
 *  # D3.js - bars with simple interaction
 *
 *  Demo d3.js bar chart setup with animated data source change
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Create Uniform checkbox
    $(".toggle-dataset").uniform();


    // Initialize chart
    interaction('#d3-simple-interaction', 400);

    // Chart setup
    function interaction(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 10},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Demo data set
        var dataset = [40.5, 33.1, 31.6, 31.0, 29.9, 28.9, 25.2, 25.2, 24.8, 24.3, 24.0, 22.6, 20.5, 19.5, 19.0, 18.9, 18.8, 18.5, 18.4, 17.6, 17.1];



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.ordinal()
            .domain(d3.range(dataset.length))
            .rangeRoundBands([0, width], 0.05);

        // Vertical
        var y = d3.scale.linear()
            .domain([0, d3.max(dataset)])
            .range([0, height]);

        // Colors
        var colors = d3.scale.category20();



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");



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



        // Add tooltip
        // ------------------------------

        // Create tooltip
        var tip = d3.tip()
            .attr('class', 'd3-tip')
            .offset([-10, 0])
            .html(function(d) { return d })

        // Initialize tooltip
        svg.call(tip);


        //
        // Append chart elements
        //

        // Append bars
        // ------------------------------

        // Add bars
        var drawBars = svg.selectAll(".d3-bar")
            .data(dataset)
            .enter()
            .append("rect")
                .attr("class", "d3-bar")
                .attr("x", function(d, i) { return x(i) })
                .attr("width", x.rangeBand())
                .attr("height", 0)
                .attr("y", height)
                .attr("fill", function(d, i) { return colors(i); })
                .style("cursor", "pointer")
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide)

        // Add bar transition
        drawBars.transition()
            .delay(200)
            .duration(1000)
            .attr("height", function(d) { return y(d) })
            .attr("y", function(d) { return height - y(d) })


        // Add text labels
        var drawLabels = svg.selectAll(".value-label")
            .data(dataset)
            .enter()
            .append("text")
                .attr("class", "value-label")
                .attr("x", function(d, i) { return x(i) + x.rangeBand() / 2 })
                .attr("y", function(d) { return height - y(d) + 25; })
                .style('opacity', 0)
                .style("text-anchor", "middle")
                .style("fill", "white")
                .text(function(d) {return d;});

        // Add text label transition
        drawLabels.transition()
            .delay(1000)
            .duration(500)
            .style('opacity', 1);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");



        // Change data sets
        // ------------------------------

        $('.toggle-dataset').on('change', function() {
            if(this.checked) {

                dataset = [8.4, 12.1, 25.5, 10.3, 11.7, 10.9, 13.3, 23.1, 15.4, 12.3, 17.8, 18.8, 14.7, 8.8, 11.2, 10.2, 17.1, 14.5, 11.9, 7.3, 7.4];

                // Update all rects
                svg.selectAll("rect")
                    .data(dataset)
                    .transition()
                        .delay(0)
                        .duration(1000)
                        .ease('cubic-in-out')
                        .attr("y", function(d) { return height - y(d) })
                        .attr("height", function(d) { return y(d) })
                        .style("fill", colors)

                // Update labels
                var drawNewlabels = svg.selectAll("text")
                    .data(dataset)
                    .attr("x", function(d, i) {return x(i) + x.rangeBand() / 2 })
                    .attr("y", function(d) {return height - y(d) + 25 })
                    .style('opacity', 0)
                    .text(function(d) {return d;});

                // Transition
                drawNewlabels.transition()
                    .delay(1000)
                    .duration(500)
                    .style('opacity', 1)
            }
            else {

                dataset = [40.5, 33.1, 31.6, 31.0, 29.9, 28.9, 25.2, 25.2, 24.8, 24.3, 24.0, 22.6, 20.5, 19.5, 19.0, 18.9, 18.8, 18.5, 18.4, 17.6, 17.1];

                // Update all rects
                svg.selectAll("rect")
                    .data(dataset)
                    .transition()
                        .delay(0)
                        .duration(1000)
                        .ease('cubic-in-out')
                        .attr("y", function(d) { return height - y(d) })
                        .attr("height", function(d) { return y(d) })
                        .style("fill", function(d, i) { return colors(i); });


                /* Update labels */
                var drawFirstlabels = svg.selectAll("text")
                    .data(dataset)
                    .attr("x", function(d, i) {return x(i) + x.rangeBand() / 2 })
                    .attr("y", function(d) {return height - y(d) + 25 })
                    .style('opacity', 0)
                    .text(function(d) {return d;});

                drawFirstlabels.transition()
                    .delay(1000)
                    .duration(500)
                    .style('opacity', 1);
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
            x.rangeRoundBands([0, width], 0.05);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);


            // Chart elements
            // -------------------------

            // Bars
            svg.selectAll('.d3-bar').attr("x", function(d, i) { return x(i) }).attr("width", x.rangeBand());

            // Text label
            svg.selectAll(".value-label").attr("x", function(d, i) { return x(i) + x.rangeBand() / 2 });
        }
    }
});
