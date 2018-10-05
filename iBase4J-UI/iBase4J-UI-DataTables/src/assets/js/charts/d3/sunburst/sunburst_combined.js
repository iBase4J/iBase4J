/* ------------------------------------------------------------------------------
 *
 *  # D3.js - sunbirst diagram combined
 *
 *  Demo sunbirst diagram setup with interactive zoom and data update combination
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize Uniform plugin
    $('.combined-options input').uniform({
        radioClass: 'choice'
    });


    // Initialize chart
    sunburstZoomable('#d3-sunburst-combined', 400, 400);

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
        var color = d3.scale.category20();



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
            .sort(null)
            .value(function(d) { return 1; });

        // Arc
        var arc = d3.svg.arc()
            .startAngle(function(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x))); })
            .endAngle(function(d) { return Math.max(0, Math.min(2 * Math.PI, x(d.x + d.dx))); })
            .innerRadius(function(d) { return Math.max(0, y(d.y)); })
            .outerRadius(function(d) { return Math.max(0, y(d.y + d.dy)); });



        // Load data
        // ------------------------------

        // Keep track of the node that is currently being displayed as the root.
        var node;

        d3.json("assets/demo_data/d3/sunburst/sunburst_basic.json", function(error, root) {
            node = root;

            // Append sunbirst
            var path = svg.datum(root).selectAll(".d3-sunbirst")
                .data(partition.nodes)
                .enter()
                .append("path")
                    .attr("class", "d3-sunbirst")
                    .attr("d", arc)
                    .style("fill", function(d) { return color((d.children ? d : d.parent).name); })
                    .on("click", click)
                    .each(stash);

            // Change data
            d3.selectAll(".combined-options input").on("change", function change() {
                var value = this.value === "count"
                ? function() { return 1; }
                : function(d) { return d.size; };

                // Transition
                path
                    .data(partition.value(value).nodes)
                    .transition()
                        .duration(750)
                        .attrTween("d", arcTweenData);
            });

            // Animate on click
            function click(d) {
                node = d;
                path.transition()
                    .duration(750)
                    .attrTween("d", arcTweenZoom(d));
            }
        });


        // Setup for switching data: stash the old values for transition.
        function stash(d) {
            d.x0 = d.x;
            d.dx0 = d.dx;
        }

        // When switching data: interpolate the arcs in data space.
        function arcTweenData(a, i) {
            var oi = d3.interpolate({x: a.x0, dx: a.dx0}, a);
            function tween(t) {
                var b = oi(t);
                a.x0 = b.x;
                a.dx0 = b.dx;
                return arc(b);
            }
            if (i == 0) {
                // If we are on the first arc, adjust the x domain to match the root node
                // at the current zoom level. (We only need to do this once.)
                var xd = d3.interpolate(x.domain(), [node.x, node.x + node.dx]);
                return function(t) {
                    x.domain(xd(t));
                    return tween(t);
                };
            }
            else {
                return tween;
            }
        }

        // When zooming: interpolate the scales
        function arcTweenZoom(d) {
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
