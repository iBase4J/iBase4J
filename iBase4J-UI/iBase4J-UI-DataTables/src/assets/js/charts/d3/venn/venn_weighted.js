/* ------------------------------------------------------------------------------
 *
 *  # D3.js - weighted Venn diagram
 *
 *  Venn diagram demo with weights
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {


    // Data set
    // ------------------------------

    // Circles
    var sets = [{label: "0", size: 7000},
               {label: "1", size: 7000},
               {label: "2", size: 2000},
               {label: "3", size: 11000},
               {label: "4", size: 11000}];

    // Overlaps
    var overlaps = [{id: 1, sets: [2,0], size: 300},
                   {id: 2, sets: [2,1], size: 300},
                   {id: 4, sets: [2,3], size: 100},
                   {id: 5, sets: [2,4], size: 100},
                   {sets: [0,0], size: 0, weight: 1e-10},
                   {sets: [0,1], size: 0, weight: 1e-10},
                   {sets: [0,3], size: 0, weight: 1e-10},
                   {sets: [0,4], size: 0, weight: 1e-10},
                   {sets: [1,0], size: 0, weight: 1e-10},
                   {sets: [1,1], size: 0, weight: 1e-10},
                   {sets: [1,3], size: 0, weight: 1e-10},
                   {sets: [1,4], size: 0, weight: 1e-10},
                   {sets: [3,0], size: 0, weight: 1e-10},
                   {sets: [3,1], size: 0, weight: 1e-10},
                   {sets: [3,3], size: 0, weight: 1e-10},
                   {sets: [3,4], size: 0, weight: 1e-10},
                   {sets: [4,0], size: 0, weight: 1e-10},
                   {sets: [4,1], size: 0, weight: 1e-10},
                   {sets: [4,3], size: 0, weight: 1e-10},
                   {sets: [4,4], size: 0, weight: 1e-10}];


    // Initialize chart
    // ------------------------------

    // Colors
    var colours = d3.scale.category10();


    // Bind data
    sets = venn.venn(sets, overlaps);


    // Draw diagram
    var diagram = venn.drawD3Diagram(d3.select("#d3-venn-weighted"), sets, 350, 350);


    // Circle styles
    diagram.circles
        .style("fill-opacity", .7)
        .style("stroke-opacity", 0)
        .style("fill", function(d,i) { return colours(i); })
        .style("stroke", function(d,i) { return colours(i); });


    // Text styles
    diagram.text
        .style("fill", "white")
        .style("font-weight", "500");
});
