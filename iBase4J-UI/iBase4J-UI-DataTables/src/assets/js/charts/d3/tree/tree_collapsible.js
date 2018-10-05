/* ------------------------------------------------------------------------------
 *
 *  # D3.js - collapsible tree layout
 *
 *  Demo of tree layout setup with collapsible nodes
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    treeCollapsible('#d3-tree-collapsible', 800);

    // Chart setup
    function treeCollapsible(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 0, right: 0, bottom: 0, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5,
            i = 0,
            root;



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

        // Tree
        var tree = d3.layout.tree()
            .size([height, width - 180]);

        // Diagonal projection
        var diagonal = d3.svg.diagonal()
            .projection(function(d) { return [d.y, d.x]; });



        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/tree/tree_data_collapsible.json", function(error, json) {

            root = json;
            root.x0 = height/2;
            root.y0 = 0;

            // Toggle nodes function
            function toggleAll(d) {
                if (d.children) {
                    d.children.forEach(toggleAll);
                    toggle(d);
                }
            }

            // Initialize the display to show a few nodes
            root.children.forEach(toggleAll);
            toggle(root.children[1]);
            toggle(root.children[1].children[2]);
            toggle(root.children[9]);
            toggle(root.children[9].children[0]);

            update(root);
        });



        // Layout setup
        // ------------------------------

        // Update nodes
        function update(source) {

            // Set duration
            var duration = d3.event && d3.event.altKey ? 5000 : 500;

            // Compute the new tree layout.
            var nodes = tree.nodes(root).reverse();

            // Normalize for fixed-depth.
            //nodes.forEach(function(d) { d.y = d.depth * 250; });

            // Update the nodes…
            var node = svg.selectAll(".d3-tree-node")
                .data(nodes, function(d) { return d.id || (d.id = ++i); });


            // Enter nodes
            // ------------------------------

            // Enter any new nodes at the parent's previous position.
            var nodeEnter = node.enter().append("g")
                .attr("class", "d3-tree-node")
                .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
                .on("click", function(d) { toggle(d); update(d); });

            // Add node circles
            nodeEnter.append("circle")
                .attr("r", 1e-6)
                .style("fill", "#fff")
                .style("stroke", "#2196F3")
                .style("stroke-width", 1.5)
                .style("cursor", "pointer")
                .style("fill", function(d) { return d._children ? "#2196F3" : "#fff"; });

            // Add nodes text
            nodeEnter.append("text")
                .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
                .attr("dy", ".35em")
                .style("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
                .style("font-size", 12)
                .style("fill-opacity", 1e-6)
                .text(function(d) { return d.name; });


            // Update nodes
            // ------------------------------

            // Transition nodes to their new position.
            var nodeUpdate = node.transition()
                .duration(duration)
                .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

            // Update circle
            nodeUpdate.select("circle")
                .attr("r", 4.5)
                .style("fill", function(d) { return d._children ? "#2196F3" : "#fff"; });

            // Update text
            nodeUpdate.select("text")
                .style("fill-opacity", 1);


            // Exit nodes
            // ------------------------------

            // Transition exiting nodes to the parent's new position.
            var nodeExit = node.exit()
                .transition()
                    .duration(duration)
                    .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
                    .remove();

            // Update circles
            nodeExit.select("circle")
                .attr("r", 1e-6);

            // Update text
            nodeExit.select("text")
                .style("fill-opacity", 1e-6);


            // Links
            // ------------------------------

            // Update the links…
            var link = svg.selectAll(".d3-tree-link")
                .data(tree.links(nodes), function(d) { return d.target.id; });

            // Enter any new links at the parent's previous position.
            link.enter().insert("path", "g")
                .attr("class", "d3-tree-link")
                .style("fill", "none")
                .style("stroke", "#ddd")
                .style("stroke-width", 1.5)
                .attr("d", function(d) {
                    var o = {x: source.x0, y: source.y0};
                    return diagonal({source: o, target: o});
                })
                .transition()
                    .duration(duration)
                    .attr("d", diagonal);

            // Transition links to their new position.
            link.transition()
                .duration(duration)
                .attr("d", diagonal);

            // Transition exiting nodes to the parent's new position.
            link.exit().transition()
                .duration(duration)
                .attr("d", function(d) {
                var o = {x: source.x, y: source.y};
                    return diagonal({source: o, target: o});
                })
                .remove();

            // Stash the old positions for transition.
            nodes.forEach(function(d) {
                d.x0 = d.x;
                d.y0 = d.y;
            });


            // Resize chart
            // ------------------------------

            // Call function on window resize
            $(window).on('resize', resize);

            // Call function on sidebar width change
            $('.sidebar-control, .d3-tree-node circle').on('click', resize);


            // Resize function
            // 
            // Since D3 doesn't support SVG resize by default,
            // we need to manually specify parts of the graph that need to 
            // be updated on window resize
            function resize() {

                // Layout variables
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
                nodes = tree.nodes(root),
                links = tree.links(nodes);

                // Layout
                // -------------------------

                // Main svg width
                container.attr("width", width + margin.left + margin.right);

                // Width of appended group
                svg.attr("width", width + margin.left + margin.right);


                // Tree size
                tree.size([height, width - 180]);

                diagonal.projection(function(d) { return [d.y, d.x]; });


                // Chart elements
                // -------------------------

                // Link paths
                svg.selectAll(".d3-tree-link").attr("d", diagonal)

                // Node paths
                svg.selectAll(".d3-tree-node").attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });
            }
        }

        // Toggle childrens
        function toggle(d) {
            if (d.children) {
                d._children = d.children;
                d.children = null;
            }
            else {
                d.children = d._children;
                d._children = null;
            }
        }



    }
});