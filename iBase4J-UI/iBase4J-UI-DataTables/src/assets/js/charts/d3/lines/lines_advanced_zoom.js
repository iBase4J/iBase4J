/* ------------------------------------------------------------------------------
 *
 *  # D3.js - line chart with pan and zoom
 *
 *  Demo d3.js line chart setup with pan and zoom
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    panZoom('#d3-pan-zoom', 400);

    // Chart setup
    function panZoom(element, height) {


        // Basic setup
        // ------------------------------

        // Demo data set
        var data =  [
            [{'x':1,'y':0},{'x':2,'y':5},{'x':3,'y':10},{'x':4,'y':0},{'x':5,'y':6},{'x':6,'y':11},{'x':7,'y':9},{'x':8,'y':4},{'x':9,'y':11},{'x':10,'y':2}],
            [{'x':1,'y':1},{'x':2,'y':6},{'x':3,'y':11},{'x':4,'y':1},{'x':5,'y':7},{'x':6,'y':12},{'x':7,'y':8},{'x':8,'y':3},{'x':9,'y':13},{'x':10,'y':3}],
            [{'x':1,'y':2},{'x':2,'y':7},{'x':3,'y':12},{'x':4,'y':2},{'x':5,'y':8},{'x':6,'y':13},{'x':7,'y':7},{'x':8,'y':2},{'x':9,'y':4},{'x':10,'y':7}]
        ];

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

        // Colors
        var colors = ['#EF5350', '#5C6BC0', '#66BB6A']

   

        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .domain([0, 11])
            .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
            .domain([-1, 14])
            .range([height, 0]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .tickSize(-height)
            .tickPadding(10)  
            .tickSubdivide(true)  
            .orient("bottom");  

        // Vertical
        var yAxis = d3.svg.axis()
            .scale(y)
            .tickPadding(10)
            .tickSize(-width)
            .tickSubdivide(true)  
            .orient("left");



        // Add zoom
        // ------------------------------

        var zoom = d3.behavior.zoom()
            .x(x)
            .y(y)
            .scaleExtent([1, 10])
            .on("zoom", zoomed);  



        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3Container.append("svg");

        // Add SVG group
        var svg = container
            .call(zoom)
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");



        // Construct chart layout
        // ------------------------------

        // Line
        var line = d3.svg.line()
            .interpolate("monotone")
            .x(function(d) { return x(d.x); })
            .y(function(d) { return y(d.y); });



        //
        // Append chart elements
        //

        // Append axes
        // ------------------------------

        // Horizontal
        svg.append("g")
            .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong d3-grid")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

        // Vertical
        svg.append("g")
            .attr("class", "d3-axis d3-axis-vertical d3-axis-strong d3-grid")
            .call(yAxis);


        // Append line
        // ------------------------------

        // Add clip path
        svg.append("clipPath")
            .attr("id", "zoom-clip")
            .append("rect")
                .attr("width", width)
                .attr("height", height);

        // Add line
        var path = svg.selectAll('.d3-line')
            .data(data)
            .enter()
            .append("path")
                .attr("d", line)
                .attr("class", "d3-line d3-line-medium")
                .attr("clip-path", "url(#zoom-clip)")
                .style('stroke', function(d,i){      
                    return colors[i%colors.length];
                });


        // Append dots
        // ------------------------------

        // Group dots
        var points = svg.selectAll('.d3-dots')
            .data(data)
            .enter()
            .append("g")
                .attr("class", "d3-dots")
                .attr("clip-path", "url(#clip)");

        // Add dots
        points.selectAll('.d3-dot')
            .data(function(d, index) {     
                var a = [];
                d.forEach(function(point,i) {
                    a.push({'index': index, 'point': point});
                });   
                return a;
            })
            .enter()
            .append('circle')
                .attr('class', 'd3-dot')
                .attr("r", 3)
                .attr("transform", function(d) { 
                    return "translate(" + x(d.point.x) + "," + y(d.point.y) + ")"; }
                )
                .style("fill", "#fff")
                .style("stroke-width", 2)
                .style('stroke', function(d,i){  
                    return colors[d.index%colors.length];
                })  
                .style("cursor", "pointer");


        // Update elements on zoom
        // ------------------------------

        function zoomed() {
            svg.select(".d3-axis-horizontal").call(xAxis);
            svg.select(".d3-axis-vertical").call(yAxis);   
            svg.selectAll('.d3-line').attr('d', line); 

            points.selectAll('.d3-dot').attr("transform", function(d) { 
                return "translate(" + x(d.point.x) + "," + y(d.point.y) + ")"; }
            );  
        }



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
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;


            // Layout
            // -------------------------

            // Main svg width
            container.attr("width", width + margin.left + margin.right);

            // Width of appended group
            svg.attr("width", width + margin.left + margin.right);


            // Axes
            // -------------------------

            // Horizontal range
            x.range([0, width]);

            // Horizontal axis
            svg.selectAll('.d3-axis-horizontal').call(xAxis);

            svg.selectAll('.d3-axis-vertical').call(yAxis.tickSize(-width));


            // Chart elements
            // -------------------------

            // Mask
            svg.select('#zoom-clip rect').attr("width", width);

            // Line path
            svg.selectAll('.d3-line').attr("d", line);

            // Dots
            points.selectAll('.d3-dot').attr("transform", function(d) { 
                return "translate(" + x(d.point.x) + "," + y(d.point.y) + ")"; }
            );
        }
    }
});
