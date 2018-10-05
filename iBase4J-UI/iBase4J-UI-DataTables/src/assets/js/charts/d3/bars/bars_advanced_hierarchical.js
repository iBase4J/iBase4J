/* ------------------------------------------------------------------------------
 *
 *  # D3.js - hierarchical bar chart
 *
 *  Demo d3.js hierarchical bar chart setup with .json data
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    stackedMultiples('#d3-hierarchical-bars', 400);

    // Chart setup
    function stackedMultiples(element, height) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var d3Container = d3.select(element),
            margin = {top: 25, right: 40, bottom: 20, left: 130},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5,
            barHeight = 30,
            duration = 750,
            delay = 25;



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.scale.linear()
            .range([0, width]);

        // Colors
        var color = d3.scale.ordinal()
            .range(["#26A69A", "#ccc"]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("top");



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

        // Partition
        var partition = d3.layout.partition()
            .value(function(d) { return d.size; });



        // Load data
        // ------------------------------

        d3.json("assets/demo_data/d3/bars/bars_hierarchical.json", function(error, root) {
            partition.nodes(root);
            x.domain([0, root.value]).nice();
            down(root, 0);
        });


        //
        // Append chart elements
        //

        // Add background bars
        svg.append("rect")
            .attr("class", "d3-bars-background")
            .attr("width", width)
            .attr("height", height)
            .style("fill", "#fff")
            .on("click", up);


        // Append axes
        // ------------------------------

        // Horizontal
        svg.append("g")
            .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong");


        // Append bars
        // ------------------------------

        // Create hierarchical structure
        function down(d, i) {
            if (!d.children || this.__transition__) return;
            var end = duration + d.children.length * delay;

            // Mark any currently-displayed bars as exiting.
            var exit = svg.selectAll(".enter")
                .attr("class", "exit");

            // Entering nodes immediately obscure the clicked-on bar, so hide it.
            exit.selectAll("rect").filter(function(p) { return p === d; })
                .style("fill-opacity", 1e-6);

            // Enter the new bars for the clicked-on data.
            // Per above, entering bars are immediately visible.
            var enter = bar(d)
                .attr("transform", stack(i))
                .style("opacity", 1);

            // Have the text fade-in, even though the bars are visible.
            // Color the bars as parents; they will fade to children if appropriate.
            enter.select("text").style("fill-opacity", 1e-6);
            enter.select("rect").style("fill", color(true));

            // Update the x-scale domain.
            x.domain([0, d3.max(d.children, function(d) { return d.value; })]).nice();

            // Update the x-axis.
            svg.selectAll(".d3-axis-horizontal").transition()
                .duration(duration)
                .call(xAxis);

            // Transition entering bars to their new position.
            var enterTransition = enter.transition()
                .duration(duration)
                .delay(function(d, i) { return i * delay; })
                .attr("transform", function(d, i) { return "translate(0," + barHeight * i * 1.2 + ")"; });

            // Transition entering text.
            enterTransition.select("text")
                .style("fill-opacity", 1);

            // Transition entering rects to the new x-scale.
            enterTransition.select("rect")
                .attr("width", function(d) { return x(d.value); })
                .style("fill", function(d) { return color(!!d.children); });

            // Transition exiting bars to fade out.
            var exitTransition = exit.transition()
                .duration(duration)
                .style("opacity", 1e-6)
                .remove();

            // Transition exiting bars to the new x-scale.
            exitTransition.selectAll("rect")
                .attr("width", function(d) { return x(d.value); });

            // Rebind the current node to the background.
            svg.select(".d3-bars-background")
                .datum(d)
                .transition()
                .duration(end);

            d.index = i;
        }

        // Return to parent level
        function up(d) {
            if (!d.parent || this.__transition__) return;
            var end = duration + d.children.length * delay;

            // Mark any currently-displayed bars as exiting.
            var exit = svg.selectAll(".enter")
                .attr("class", "exit");

            // Enter the new bars for the clicked-on data's parent.
            var enter = bar(d.parent)
                .attr("transform", function(d, i) { return "translate(0," + barHeight * i * 1.2 + ")"; })
                .style("opacity", 1e-6);

            // Color the bars as appropriate.
            // Exiting nodes will obscure the parent bar, so hide it.
            enter.select("rect")
                .style("fill", function(d) { return color(!!d.children); })
                .filter(function(p) { return p === d; })
                .style("fill-opacity", 1e-6);

            // Update the x-scale domain.
            x.domain([0, d3.max(d.parent.children, function(d) { return d.value; })]).nice();

            // Update the x-axis.
            svg.selectAll(".d3-axis-horizontal").transition()
                .duration(duration)
                .call(xAxis);

            // Transition entering bars to fade in over the full duration.
            var enterTransition = enter.transition()
                .duration(end)
                .style("opacity", 1);

            // Transition entering rects to the new x-scale.
            // When the entering parent rect is done, make it visible!
            enterTransition.select("rect")
                .attr("width", function(d) { return x(d.value); })
                .each("end", function(p) { if (p === d) d3.select(this).style("fill-opacity", null); });

            // Transition exiting bars to the parent's position.
            var exitTransition = exit.selectAll("g").transition()
                .duration(duration)
                .delay(function(d, i) { return i * delay; })
                .attr("transform", stack(d.index));

            // Transition exiting text to fade out.
            exitTransition.select("text")
                .style("fill-opacity", 1e-6);

            // Transition exiting rects to the new scale and fade to parent color.
            exitTransition.select("rect")
                .attr("width", function(d) { return x(d.value); })
                .style("fill", color(true));

            // Remove exiting nodes when the last child has finished transitioning.
            exit.transition()
                .duration(end)
                .remove();

            // Rebind the current parent to the background.
            svg.select(".d3-bars-background")
                .datum(d.parent)
                .transition()
                .duration(end);
        }

        // Creates a set of bars for the given data node, at the specified index.
        function bar(d) {
            var bar = svg.insert("g", ".d3-axis-vertical")
                .attr("class", "enter")
                .attr("transform", "translate(0,5)")
                .selectAll("g")
                .data(d.children)
                .enter()
                .append("g")
                    .style("cursor", function(d) { return !d.children ? null : "pointer"; })
                    .on("click", down);

            bar.append("text")
                .attr("x", -6)
                .attr("y", barHeight / 2)
                .attr("dy", ".35em")
                .style("text-anchor", "end")
                .text(function(d) { return d.name; });

            bar.append("rect")
                .attr("width", function(d) { return x(d.value); })
                .attr("height", barHeight);

            return bar;
        }

        // A stateful closure for stacking bars horizontally.
        function stack(i) {
            var x0 = 0;
            return function(d) {
                var tx = "translate(" + x0 + "," + barHeight * i * 1.2 + ")";
                x0 += x(d.value);
                return tx;
            };
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


            // Chart elements
            // -------------------------

            // Bars
            svg.selectAll('.enter rect').attr("width", function(d) { return x(d.value); });
        }
    }
});
