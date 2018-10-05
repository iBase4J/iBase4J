/* ------------------------------------------------------------------------------
 *
 *  # D3.js - Venn diagram with tooltip
 *
 *  Venn diagram demo setup with interactive data tooltip
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {


    // Data set
    // ------------------------------

    // Circles
    var sets = [
        {label: 'SE', size: 28},
        {label: 'Treat', size: 35},
        {label: 'Anti-CCP', size: 108},
        {label: 'DAS28', size: 106}
    ];

    // Overlaps
    var overlaps = [
        {sets: [0,1], size: 1},
        {sets: [0,2], size: 1},
        {sets: [0,3], size: 14},
        {sets: [1,2], size: 6},
        {sets: [1,3], size: 0},
        {sets: [2,3], size: 1},
        {sets: [0,2,3], size: 1},
        {sets: [0,1,2], size: 0},
        {sets: [0,1,3], size: 0},
        {sets: [1,2,3], size: 0},
        {sets: [0,1,2,3], size: 0}
    ];


    // Initialize chart
    // ------------------------------

    // Define colors
    var colours = d3.scale.category10();

    // Get positions for each set
    sets = venn.venn(sets, overlaps);

    // Draw the diagram in the 'venn' div
    var diagram = venn.drawD3Diagram(d3.select("#d3-venn-tooltip"), sets, 350, 350);


    // Add tooltip
    // ------------------------------

    // Add a tooltip showing the size of each set/intersection
    var tooltip = d3.select("body").append("div")
        .attr("class", "venntooltip");

    d3.selection.prototype.moveParentToFront = function() {
        return this.each(function(){
            this.parentNode.parentNode.appendChild(this.parentNode);
        });
    };

    // Text styling
    diagram.text.style("fill", "white").style("font-weight", "500").style("cursor", "pointer");

    // Hover on all the circles
    diagram.circles
        .style("stroke-opacity", 0)
        .style("stroke", "white")
        .style("stroke-width", "2")
        .style("fill-opacity", .7);


    // Add events
    diagram.nodes
        .on("mousemove", function() {
            tooltip.style("left", (d3.event.pageX + 20) + "px")
                   .style("top", (d3.event.pageY - 15) + "px");
        })
        .on("mouseover", function(d, i) {
            var selection = d3.select(this).select("circle");
            selection.moveParentToFront()
                .transition()
                .style("fill-opacity", .7)
                .style("cursor", "pointer")
                .style("stroke-opacity", 1);

            tooltip.transition().style("display", "block");
            tooltip.text(d.size + " users");
        })
        .on("mouseout", function(d, i) {
            d3.select(this).select("circle").transition()
                .style("fill-opacity", .7)
                .style("stroke-opacity", 0);

            tooltip.transition().style("display", "none");
        });


        // Draw a path around each intersection area, add hover there as well
        diagram.svg.selectAll("path")
            .data(overlaps)
            .enter()
            .append("path")
            .attr("d", function(d) { 
                return venn.intersectionAreaPath(d.sets.map(function(j) { return sets[j]; })); 
            })
            .style("fill-opacity","0")
            .style("fill", "#333")
            .style("stroke-opacity", 0)
            .style("stroke", "white")
            .style("stroke-width", "2")
            .on("mouseover", function(d, i) {
                d3.select(this).transition()
                    .style("fill-opacity", .1)
                    .style("stroke-opacity", 1);

                tooltip.transition().style("display", "block");
                tooltip.text(d.size + " users");
            })
            .on("mouseout", function(d, i) {
                d3.select(this).transition()
                    .style("fill-opacity", 0)
                    .style("stroke-opacity", 0);

                tooltip.transition().style("display", "none");
            })
            .on("mousemove", function() {
                tooltip.style("left", (d3.event.pageX + 20) + "px")
                       .style("top", (d3.event.pageY - 15) + "px");
            });
});
