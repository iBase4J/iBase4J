/* ------------------------------------------------------------------------------
 *
 *  # D3.js - arc tween animation
 *
 *  Demo d3.js demonstration of arc tween animation
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Initialize chart
    donutTweenAnimation('#d3-donut-arc-tween', 120);

    // Chart setup
    function donutTweenAnimation(element, radius) {


        // Basic setup
        // ------------------------------

        // Define main variables
        var τ = 2 * Math.PI;


        // Create chart
        // ------------------------------

        // Add SVG element
        var container = d3.select(element).append("svg");

        // Add SVG group
        var svg = container
            .attr("width", radius * 2)
            .attr("height", radius * 2)
            .append("g")
                .attr("transform", "translate(" + radius + "," + radius + ")");


        // Construct chart layout
        // ------------------------------

        // Arc
        var arc = d3.svg.arc()
            .outerRadius(radius)
            .innerRadius(radius / 1.4)
            .startAngle(0);


        //
        // Append chart elements
        //

        // Add the background arc, from 0 to 100% (τ).
        var background = svg.append("path")
            .datum({endAngle: τ})
            .style("fill", "#eee")
            .attr("d", arc);

        // Add the foreground arc in orange, currently showing 12.7%.
        var foreground = svg.append("path")
            .datum({endAngle: .127 * τ})
            .style("fill", "#7986CB")
            .attr("d", arc);

        // Start a transition to a new random angle
        setInterval(function() {
            foreground.transition()
                .duration(750)
                .call(arcTween, Math.random() * τ);
        }, 1500);

        // Creates a tween on the specified transition's "d" attribute, transitioning
        // any selected arcs from their current angle to the specified new angle.
        function arcTween(transition, newAngle) {
            transition.attrTween("d", function(d) {

                // Interpolate between the two angles
                var interpolate = d3.interpolate(d.endAngle, newAngle);

                // Return value of the attrTween
                return function(t) {

                    // Calculate the current arc angle based on the transition time, t
                    d.endAngle = interpolate(t);

                    // Lastly, compute the arc path given the updated data
                    return arc(d);
                };
            });
        }
    }
});
