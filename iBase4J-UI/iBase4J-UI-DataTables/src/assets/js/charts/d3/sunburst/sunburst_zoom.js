/* ------------------------------------------------------------------------------
 *
 *  # D3.js - sunbirst diagram with zoom
 *
 *  Demo sunbirst diagram setup with interactive zoom
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    sunburstZoomable('#d3-sunburst-zoom', 400, 400);

    // Chart setup
    function sunburstZoomable(element, width, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var radius = Math.min(width, height) / 2;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .range([0, 2 * Math.PI]);

        // Vertical
        var y = d3.scale.sqrt()
            .range([0, radius]);

        // Colors
        var color = d3.scale.category20c();



        // Create chart
        // ------------------------------

        var svg = d3.select(element).append("svg")
            .attr("width", width)
            .attr("height", height)
            .append("g")
                .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");



        // Construct chart layout
        // ------------------------------

        // Partition layout
        var partition = d3.layout.partition()
            .value(function(d) { return d.size; });

        // Arc
        var arc = d3.svg.arc()
            .startAngle(function(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x))); })
            .endAngle(function(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x + d.dx))); })
            .innerRadius(function(d) { return Math.max(0, y(d.y)); })
            .outerRadius(function(d) { return Math.max(0, y(d.y + d.dy)); });



        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/sunburst/sunburst_basic.json", function(error, root) {

            // Append sunbirst
            var path = svg.selectAll(".d3-sunbirst")
                .data(partition.nodes(root))
                .enter()
                .append("path")
                    .attr("class", "d3-sunbirst")
                    .attr("d", arc)
                    .style("fill", function(d) { return color((d.children ? d : d.parent).name); })
                    .on("click", click);

            // Run transition on click
            function click(d) {
                path.transition()
                    .duration(750)
                    .attrTween("d", arcTween(d));
            }
        });


        // Interpolate the scales
        function arcTween(d) {
            var xd = d3.interpolate(x.domain(), [d.x, d.x + d.dx]),
            yd = d3.interpolate(y.domain(), [d.y, 1]),
            yr = d3.interpolate(y.range(), [d.y ? 20 : 0, radius]);
            return function(d, i) {
                return i
                ? function(t) { return arc(d); }
                : function(t) { x.domain(xd(t)); y.domain(yd(t)).range(yr(t)); return arc(d); };
            };
        }
    }
});
