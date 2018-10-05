/* ------------------------------------------------------------------------------
 *
 *  # D3.js - basic Venn diagram
 *
 *  Basic demo d3.js Venn diagram setup
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

    // Draw diagram
    var diagram = venn.drawD3Diagram(d3.select("#d3-venn-basic"), venn.venn(sets, overlaps), 350, 350);


    // Make text semi bold
    diagram.text.style("font-weight", "500");
});
