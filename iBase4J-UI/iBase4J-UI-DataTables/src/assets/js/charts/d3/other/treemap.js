/* ------------------------------------------------------------------------------
 *
 *  # D3.js - zoomable treemap
 *
 *  Demo of treemap setup with zoom and .json data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Create Uniform checkbox
    $(".treemap_actions").uniform({
        radioClass: 'choice'
    });


    // Initialize chart
    treemap('#d3-treemap', 800);

    // Chart setup
    function treemap(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            width = d3Container.node().getBoundingClientRect().width,
            root,
            node;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear().range([0, height]);

        // Colors
        var color = d3.scale.category20();


    
        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3Container.append("svg");

        // Add SVG group
        var svg = container
            .attr("width", width)
            .attr("height", height)
            .append("g")
                .attr("transform", "translate(.5,.5)")
                .style("font-size", 12)
                .style("overflow", "hidden")
                .style("text-indent", 2);



        // Construct chart layout
        // ------------------------------

        // Treemap
        var treemap = d3.layout.treemap()
            .round(false)
            .size([width, height])
            .sticky(true)
            .value(function(d) { return d.size; });



        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/other/treemap.json", function(data) {
            node = root = data;
            var nodes = treemap.nodes(root)
                .filter(function(d) { return !d.children; });


            // Add cells
            // ------------------------------

            // Bind data
            var cell = svg.selectAll(".d3-treemap-cell")
                .data(nodes)
                .enter()
                .append("g")
                    .attr("class", "d3-treemap-cell")
                    .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
                    .style("cursor", "pointer")
                    .on("click", function(d) { return zoom(node == d.parent ? root : d.parent); });

            // Append cell rects
            cell.append("rect")
                .attr("width", function(d) { return d.dx - 1; })
                .attr("height", function(d) { return d.dy - 1; })
                .style("fill", function(d, i) { return color(i); });

            // Append text
            cell.append("text")
                .attr("x", function(d) { return d.dx / 2; })
                .attr("y", function(d) { return d.dy / 2; })
                .attr("dy", ".35em")
                .attr("text-anchor", "middle")
                .text(function(d) { return d.name; })
                .style("fill", "#fff")
                .style("opacity", function(d) { d.width = this.getComputedTextLength(); return d.dx > d.width ? 1 : 0; });
        }); 


        // Change data
        // ------------------------------

        d3.selectAll(".treemap_actions").on("change", change);

        // Change data function
        function change() {
            treemap.value(this.value == "size" ? size : count).nodes(root);
            zoom(node);
        }

        // Size
        function size(d) {
            return d.size;
        }

        // Count
        function count(d) {
            return 1;
        }

        // Zoom
        function zoom(d) {
            var kx = width / d.dx, ky = height / d.dy;
            x.domain([d.x, d.x + d.dx]);
            y.domain([d.y, d.y + d.dy]);

        // Cell transition
        var t = svg.selectAll(".d3-treemap-cell").transition()
            .duration(500)
            .attr("transform", function(d) { return "translate(" + x(d.x) + "," + y(d.y) + ")"; });

            // Cell rect transition
            t.select("rect")
                .attr("width", function(d) { return kx * d.dx - 1; })
                .attr("height", function(d) { return ky * d.dy - 1; })

            // Text transition
            t.select("text")
                .attr("x", function(d) { return kx * d.dx / 2; })
                .attr("y", function(d) { return ky * d.dy / 2; })
                .style("opacity", function(d) { return kx * d.dx > d.width ? 1 : 0; });

            node = d;
            d3.event.stopPropagation();
        }

        // Add click event
        d3.select(window).on("click", function() { zoom(root); });



        // Resize chart
        // ------------------------------

        // Call function on window resize
        d3.select(window).on('resize', resize);

        // Call function on sidebar width change
        d3.select('.sidebar-control').on('click', resize);

        // Resize function
        // 
        // Since D3 doesn't support SVG resize by default,
        // we need to manually specify parts of the graph that need to 
        // be updated on window resize
        function resize() {

            // Layout variables
            width = d3Container.node().getBoundingClientRect().width;


            // Layout
            // -------------------------

            // Main svg width
            container.attr("width", width);

            // Width of appended group
            svg.attr("width", width);


            // Horizontal range
            x.range([0, width]);

            // Redraw chart
            zoom(root);
        }
    }
});