/* ------------------------------------------------------------------------------
 *
 *  # D3.js - interactive Venn diagram
 *
 *  Venn diagram demo setup with interactions
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


    // Draw diagram
    var diagram = venn.drawD3Diagram(d3.select("#d3-venn-interactive"), venn.venn(sets, overlaps), 350, 350);


    // Circle styles
    diagram.circles
        .style("fill-opacity", 0)
        .style("stroke-width", 4)
        .style("stroke-opacity", .6)
        .style("cursor", "pointer")
        .style("fill", function(d,i) { return colours(i); })
        .style("stroke", function(d,i) { return colours(i); });


    // Text styles
    diagram.text
        .style("fill", function(d,i) { return colours(i)})
        .style("cursor", "pointer")
        .style("font-size", "14px")
        .style("font-weight", "500");


    // Add interaction
    diagram.nodes
        .on("mouseover", function(d, i) {
            var node = d3.select(this).transition();
            node.select("circle").style("fill-opacity", .1);
            node.select("text").style("font-weight", "500").style("font-size", "16px");
        })
        .on("mouseout", function(d, i) {
            var node = d3.select(this).transition();
            node.select("circle").style("fill-opacity", 0);
            node.select("text").style("font-weight", "500").style("font-size", "14px");
        })
        .on("click", function(d, i) {
            alert("You have clicked on one of the rings!")
        });
});
