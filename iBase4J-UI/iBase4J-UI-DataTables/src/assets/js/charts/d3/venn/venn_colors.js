/* ------------------------------------------------------------------------------
 *
 *  # D3.js - custom diagram colors
 *
 *  Venn diagram demo with custom color options
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
    var diagram = venn.drawD3Diagram(d3.select("#d3-venn-colors"), venn.venn(sets, overlaps), 350, 350);


    // Style circles
    diagram.circles
        .style("fill-opacity", .7)
        .style("stroke-opacity", 0)
        .style("fill", function(d,i) { return colours(i); })
        .style("stroke", function(d,i) { return colours(i); });


    // Style text
    diagram.text
        .style("fill", "white")
        .style("font-weight", "500");
});
