/* ------------------------------------------------------------------------------
 *
 *  # D3.js - radial dendrogram layout
 *
 *  Demo of radial dendrogram layout setup with .json data source
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    dendrogramRadial('#d3-dendrogram-radial', 900);

    // Chart setup
    function dendrogramRadial(element, diameter) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element);


        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3Container.append("svg");

        // Add SVG group
        var svg = container
            .attr("width", diameter)
            .attr("height", diameter)
            .append("g")
                .attr("transform", "translate(" + (diameter / 2) + "," + (diameter / 2) + ")");



        // Construct chart layout
        // ------------------------------

        // Cluster
        var cluster = d3.layout.cluster()
            .size([360, (diameter / 2) - 150]);

        // Diagonal projection
        var diagonal = d3.svg.diagonal.radial()
            .projection(function(d) { return [d.y, d.x / 180 * Math.PI]; });


        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/tree/tree_data_dendrogram_radial.json", function(error, root) {

            var nodes = cluster.nodes(root);


            // Links
            // ------------------------------

            // Append link paths
            var link = svg.selectAll(".d3-tree-link")
                .data(cluster.links(nodes))
                .enter()
                .append("path")
                    .attr("class", "d3-tree-link")
                    .attr("d", diagonal)
                    .style("fill", "none")
                    .style("stroke", "#ddd")
                    .style("stroke-width", 1.5);


            // Nodes
            // ------------------------------

            // Append node group
            var node = svg.selectAll(".d3-tree-node")
                .data(nodes)
                .enter()
                .append("g")
                    .attr("class", "d3-tree-node")
                    .attr("transform", function(d) { return "rotate(" + (d.x - 90) + ")translate(" + d.y + ")"; })

            // Append circles
            node.append("circle")
                .attr("r", 4.5)
                .style("fill", "#fff")
                .style("stroke", "#2196F3")
                .style("stroke-width", 1.5);

            // Append text
            node.append("text")
                .attr("dy", ".31em")
                .attr("text-anchor", function(d) { return d.x < 180 ? "start" : "end"; })
                .attr("transform", function(d) { return d.x < 180 ? "translate(8)" : "rotate(180)translate(-8)"; })
                .style("font-size", 12)
                .text(function(d) { return d.name; });
        });
    }
});