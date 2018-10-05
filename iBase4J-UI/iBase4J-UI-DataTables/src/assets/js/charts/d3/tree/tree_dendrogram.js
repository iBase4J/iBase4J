/* ------------------------------------------------------------------------------
 *
 *  # D3.js - cluster dendrogram
 *
 *  Demo of cluster dendrogram setup in cartesian orientation
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    treeCluster('#d3-tree-dendrogram', 800);

    // Chart setup
    function treeCluster(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 0, right: 0, bottom: 0, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;



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

        // Cluster
        var cluster = d3.layout.cluster()
            .size([height, width - 180]);

        // Diagonal projection
        var diagonal = d3.svg.diagonal()
            .projection(function(d) { return [d.y, d.x]; });



        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/tree/tree_data_dendrogram.json", function(error, root) {

            var nodes = cluster.nodes(root),
                links = cluster.links(nodes);


            // Links
            // ------------------------------

            // Append link group
            var linkGroup = svg.append("g")
                .attr("class", "d3-tree-link-group");

            // Append link path
            var link = linkGroup.selectAll(".d3-tree-link")
                .data(links)
                .enter()
                .append("path")
                    .attr("class", "d3-tree-link")
                    .style("fill", "none")
                    .style("stroke", "#ddd")
                    .style("stroke-width", 1.5)
                    .attr("d", diagonal);


            // Nodes
            // ------------------------------

            // Append node group
            var nodeGroup = svg.append("g")
                .attr("class", "d3-tree-node-group");

            // Append node
            var node = nodeGroup.selectAll(".d3-tree-node")
                .data(nodes)
                .enter()
                .append("g")
                    .attr("class", "d3-tree-node")
                    .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })

            // Append node circles
            node.append("circle")
                .style("fill", "#fff")
                .style("stroke", "#2196F3")
                .style("stroke-width", 1.5)
                .attr("r", 4.5);

            // Append node text
            var text = node.append("text")
                .attr("dx", function(d) { return d.children ? -10 : 10; })
                .attr("dy", 4)
                .style("text-anchor", function(d) { return d.children ? "end" : "start"; })
                .style("font-size", 12)
                .style("background-color", "#fff")
                .text(function(d) { return d.name; });



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
                width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
                nodes = cluster.nodes(root),
                links = cluster.links(nodes);

                // Layout
                // -------------------------

                // Main svg width
                container.attr("width", width + margin.left + margin.right);

                // Width of appended group
                svg.attr("width", width + margin.left + margin.right);


                // Tree size
                cluster.size([height, width - 180]);


                // Chart elements
                // -------------------------

                // Link paths
                svg.selectAll(".d3-tree-link").attr("d", diagonal)

                // Node paths
                svg.selectAll(".d3-tree-node").attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });
            }
        });
    }
});