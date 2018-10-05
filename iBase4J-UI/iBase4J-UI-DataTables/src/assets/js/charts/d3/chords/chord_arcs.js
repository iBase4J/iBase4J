/* ------------------------------------------------------------------------------
 *
 *  # D3.js - chord arc tweens
 *
 *  Demo chord diagram setup with arc shapes animation
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {


    // Basic setup
    // ------------------------------

    // Data set
    var data = [
        [6,32,47,81,31,89,24,68,50,39],
        [37,83,57,80,87,7,85,7,68,17],
        [50,15,31,3,1,85,36,95,83,99],
        [19,99,97,79,74,43,78,18,4,57],
        [77,2,87,41,93,52,6,42,11,76],
        [91,56,97,65,23,60,63,68,45,48],
        [17,77,96,22,87,98,58,15,36,16],
        [44,54,60,69,36,44,76,58,50,16]
    ];



    // Construct layout
    // ------------------------------

    d3.chart = d3.chart || {};

    d3.chart.chord = function(container) {

        // Main variables
        var self = {},
            svg;

        // Layout variables
        var w = 400,
            h = 400,
            r0 = Math.min(w, h) * .37,
            r1 = r0 * 1.1;

        // Colors
        var fill = d3.scale.category20c();


        // Construct chart layout
        // ------------------------------

        // Chord layout
        var chord = d3.layout.chord()
            .padding(.05)
            .sortSubgroups(d3.descending);

        // Arc
        var arc = d3.svg.arc()
            .innerRadius(r0)
            .outerRadius(r1);


        // Setup chart
        // ------------------------------

        // Update chart
        self.update = function(data) {
           if (!chord.matrix()) {
               chord.matrix(data);
               self.render();
           } else {
               var old = {
                   groups: chord.groups()
               };
               chord.matrix(data);
               self.transition(old);
           }
        };

        // Clear chart
        self.clear = function() {
            d3.select(container).selectAll('svg').remove();
        };

        // Transition
        self.transition = function(old) {
            svg.selectAll(".d3-arc")
              .data(chord.groups)
              .transition()
              .duration(1500)
              .attrTween("d", arcTween(arc, old));
        };

        // Render chart
        self.render = function() {
            self.clear();

            // Create chart
            svg = d3.select(container)
                .append("svg")
                    .attr("width", w)
                    .attr("height", h)
                    .append("g")
                        .attr("transform", "translate(" + w / 2 + "," + h / 2 + ")");

            // Add arc
            svg.append("g")
                .selectAll("path")
                .data(chord.groups)
                .enter()
                .append("path")
                    .attr("class", "d3-arc")
                    .style("fill", function(d) { return fill(d.index); })
                    .style("stroke", function(d) { return fill(d.index); })
                    .attr("d", arc)
                    .on("mouseover", fade(.1, svg))
                    .on("mouseout", fade(1, svg));


            // Add ticks
            // ------------------------------

            // Group
            var ticks = svg.append("g")
                .selectAll("g")
                .data(chord.groups)
                .enter()
                .append("g")
                    .selectAll("g")
                    .data(groupTicks)
                    .enter()
                    .append("g")
                        .attr("transform", function(d) {
                            return "rotate(" + (d.angle * 180 / Math.PI - 90) + ")"
                            + "translate(" + r1 + ",0)";
                        });

            // Add tick line
            ticks.append("line")
                .attr("x1", 1)
                .attr("y1", 0)
                .attr("x2", 5)
                .attr("y2", 0)
                .style("stroke", "#000");

            // Add tick text
            ticks.append("text")
                .attr("x", 8)
                .attr("dy", ".35em")
                .attr("text-anchor", function(d) {
                    return d.angle > Math.PI ? "end" : null;
                })
                .attr("transform", function(d) {
                    return d.angle > Math.PI ? "rotate(180)translate(-16)" : null;
                })
                .style("font-size", 12)
                .text(function(d) { return d.label; });


            // Add chord
            // ------------------------------

            svg.append("g")
                .attr("class", "d3-chord")
                .selectAll("path")
                .data(chord.chords)
                .enter()
                .append("path")
                    .attr("d", d3.svg.chord().radius(r0))
                    .style("fill", function(d) { return fill(d.target.index); })
                    .style("stroke", "#000")
                    .style("stroke-width", 0.5)
                    .style("fill-opacity", 0.7)
        };

        return self;
    };



    // Utility functions
    // ------------------------------

    // Returns an array of tick angles and labels, given a group
    function groupTicks(d) {
        var k = (d.endAngle - d.startAngle) / d.value;
        return d3.range(0, d.value, 50).map(function(v, i) {
            return {
                angle: v * k + d.startAngle,
                label: i % 2 ? null : v
            };
        });
    }

    // Returns an event handler for fading a given chord group
    function fade(opacity, svg) {
        return function(g, i) {
            svg.selectAll(".d3-chord path").filter(function(d) {
                return d.source.index != i && d.target.index != i;
            })
            .transition()
            .style("opacity", opacity);
        };
    }

    // Interpolate the arcs in data space.
    function arcTween(arc, old) {
        return function(d,i) {
            var i = d3.interpolate(old.groups[i], d);
            return function(t) {
                return arc(i(t));
            }
        }
    }

    // Random data
    function random_matrix(size) {
        var matrix = [];
        for (var i=0; i<size; i++) {
            var row = [];
            for (var j=0; j<size; j++) {
                var num = Math.round(100*Math.pow(Math.random(),2)+1);
                row.push(num);
            }
            matrix.push(row);
        }
        return matrix;
    };




    // Initialize chart
    // ------------------------------

    for (var i=1; i<=3; i++) {
        initChord(i);
    }

    function initChord(i) {
        var chord = d3.chart.chord("#d3-chord-arc");
        chord.update(data);

        d3.select("#update-arc").on("click", function() {
            var data = random_matrix(8);
            chord.update(data);
        });
        d3.select("#clear-arc").on("click", function() {
            chord.clear();
        });
        d3.select("#render-arc").on("click", function() {
            chord.render();
        });
    }
});
