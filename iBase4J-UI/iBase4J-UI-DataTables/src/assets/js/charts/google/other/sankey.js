/* ------------------------------------------------------------------------------
 *
 *  # Google Visualization - sankey diagram
 *
 *  Google Visualization sankey diagram demonstration
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */


// Sankey diagram
// ------------------------------

// Initialize chart
google.load("visualization", "1", {packages:["sankey"]});
google.setOnLoadCallback(drawSankey);


// Chart settings
function drawSankey() {

    // Data
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'From');
    data.addColumn('string', 'To');
    data.addColumn('number', 'Weight');
    data.addRows([
        [ 'Brazil', 'Portugal', 4 ],
        [ 'Brazil', 'France', 1 ],
        [ 'Brazil', 'Spain', 1 ],
        [ 'Brazil', 'England', 1 ],
        [ 'Canada', 'Portugal', 1 ],
        [ 'Canada', 'France', 4 ],
        [ 'Canada', 'England', 1 ],
        [ 'Mexico', 'Portugal', 1 ],
        [ 'Mexico', 'France', 1 ],
        [ 'Mexico', 'Spain', 4 ],
        [ 'Mexico', 'England', 1 ],
        [ 'USA', 'Portugal', 1 ],
        [ 'USA', 'France', 1 ],
        [ 'USA', 'Spain', 1 ],
        [ 'USA', 'England', 4 ],
        [ 'Portugal', 'Angola', 2 ],
        [ 'Portugal', 'Senegal', 1 ],
        [ 'Portugal', 'Morocco', 1 ],
        [ 'Portugal', 'South Africa', 3 ],
        [ 'France', 'Angola', 1 ],
        [ 'France', 'Mali', 3 ],
        [ 'France', 'Morocco', 3 ],
        [ 'France', 'South Africa', 1 ],
        [ 'Spain', 'Senegal', 1 ],
        [ 'Spain', 'Morocco', 3 ],
        [ 'Spain', 'South Africa', 1 ],
        [ 'England', 'Angola', 1 ],
        [ 'England', 'Senegal', 1 ],
        [ 'England', 'Morocco', 2 ],
        [ 'England', 'South Africa', 4 ],
        [ 'South Africa', 'India', 1 ],
        [ 'South Africa', 'Japan', 3 ],
        [ 'Angola', 'China', 2 ],
        [ 'Angola', 'India', 1 ],
        [ 'Angola', 'Japan', 3 ],
        [ 'Senegal', 'China', 2 ],
        [ 'Senegal', 'India', 1 ],
        [ 'Senegal', 'Japan', 3 ],
        [ 'Mali', 'China', 2 ],
        [ 'Mali', 'India', 1 ],
        [ 'Mali', 'Japan', 3 ],
        [ 'Morocco', 'China', 2 ],
        [ 'Morocco', 'India', 1 ],
        [ 'Morocco', 'Japan', 3 ],
        [ 'Morocco', 'Senegal', 1 ]
    ]);


    // Options
    var options = {
        height: 600,
        sankey: { 
            link: {
                color: {
                    fill: '#eee',
                    fillOpacity: 0.3
                }
            },
            node: {
                width: 8,
                nodePadding: 80,
                label: {
                    fontName: 'Roboto',
                    fontSize: 13
                } 
            } 
        },
    };


    // Draw chart
    var chart = new google.visualization.Sankey($('#google-sankey')[0]);
    chart.draw(data, options);
}


// Resize chart
// ------------------------------

$(function () {

    // Resize chart on sidebar width change and window resize
    $(window).on('resize', resize);
    $(".sidebar-control").on('click', resize);

    // Resize function
    function resize() {
        drawSankey();
    }
});
