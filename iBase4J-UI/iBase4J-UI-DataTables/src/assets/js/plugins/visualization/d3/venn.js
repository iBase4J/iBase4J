(function(venn) {
    "use strict";
    /** given a list of set objects, and their corresponding overlaps.
    updates the (x, y, radius) attribute on each set such that their positions
    roughly correspond to the desired overlaps */
    venn.venn = function(sets, overlaps, parameters) {
        parameters = parameters || {};
        parameters.maxIterations = parameters.maxIterations || 500;
        var lossFunction = parameters.lossFunction || venn.lossFunction;
        var initialLayout = parameters.layoutFunction || venn.greedyLayout;

        // initial layout is done greedily
        sets = initialLayout(sets, overlaps);

        // transform x/y coordinates to a vector to optimize
        var initial = new Array(2*sets.length);
        for (var i = 0; i < sets.length; ++i) {
            initial[2 * i] = sets[i].x;
            initial[2 * i + 1] = sets[i].y;
        }

        // optimize initial layout from our loss function
        var totalFunctionCalls = 0;
        var solution = venn.fmin(
            function(values) {
                totalFunctionCalls += 1;
                var current = new Array(sets.length);
                for (var i = 0; i < sets.length; ++i) {
                    current[i] = {x: values[2 * i],
                                  y: values[2 * i + 1],
                                  radius : sets[i].radius,
                                  size : sets[i].size};
                }
                return lossFunction(current, overlaps);
            },
            initial,
            parameters);

        // transform solution vector back to x/y points
        var positions = solution.solution;
        for (i = 0; i < sets.length; ++i) {
            sets[i].x = positions[2 * i];
            sets[i].y = positions[2 * i + 1];
        }

        return sets;
    };

    /** Returns the distance necessary for two circles of radius r1 + r2 to
    have the overlap area 'overlap' */
    venn.distanceFromIntersectArea = function(r1, r2, overlap) {
        // handle complete overlapped circles
        if (Math.min(r1, r2) * Math.min(r1,r2) * Math.PI <= overlap) {
            return Math.abs(r1 - r2);
        }

        return venn.bisect(function(distance) {
            return venn.circleOverlap(r1, r2, distance) - overlap;
        }, 0, r1 + r2);
    };

    /// gets a matrix of euclidean distances between all sets in venn diagram
    venn.getDistanceMatrix = function(sets, overlaps) {
        // initialize an empty distance matrix between all the points
        var distances = [];
        for (var i = 0; i < sets.length; ++i) {
            distances.push([]);
            for (var j = 0; j < sets.length; ++j) {
                distances[i].push(0);
            }
        }

        // compute distances between all the points
        for (i = 0; i < overlaps.length; ++i) {
            var current = overlaps[i];
            if (current.sets.length !== 2) {
                continue;
            }

            var left = current.sets[0],
                right = current.sets[1],
                r1 = Math.sqrt(sets[left].size / Math.PI),
                r2 = Math.sqrt(sets[right].size / Math.PI),
                distance = venn.distanceFromIntersectArea(r1, r2, current.size);
            distances[left][right] = distances[right][left] = distance;
        }
        return distances;
    };

    /** Lays out a Venn diagram greedily, going from most overlapped sets to
    least overlapped, attempting to position each new set such that the
    overlapping areas to already positioned sets are basically right */
    venn.greedyLayout = function(sets, overlaps) {
        // give each set a default position + radius
        var setOverlaps = {};
        for (var i = 0; i < sets.length; ++i) {
            setOverlaps[i] = [];
            sets[i].radius = Math.sqrt(sets[i].size / Math.PI);
            sets[i].x = sets[i].y = 0;
        }

        // map each set to a list of all the other sets that overlap it
        for (i = 0; i < overlaps.length; ++i) {
            var current = overlaps[i];
            if (current.sets.length !== 2) {
                continue;
            }

            var weight = (current.weight == null) ? 1.0 : current.weight;
            var left = current.sets[0], right = current.sets[1];
            setOverlaps[left].push ({set:right, size:current.size, weight:weight});
            setOverlaps[right].push({set:left,  size:current.size, weight:weight});
        }

        // get list of most overlapped sets
        var mostOverlapped = [];
        for (var set in setOverlaps) {
            if (setOverlaps.hasOwnProperty(set)) {
                var size = 0;
                for (i = 0; i < setOverlaps[set].length; ++i) {
                    size += setOverlaps[set][i].size * setOverlaps[set][i].weight;
                }

                mostOverlapped.push({set: set, size:size});
            }
        }

        // sort by size desc
        function sortOrder(a,b) {
            return b.size - a.size;
        }
        mostOverlapped.sort(sortOrder);

        // keep track of what sets have been laid out
        var positioned = {};
        function isPositioned(element) {
            return element.set in positioned;
        }

        // adds a point to the output
        function positionSet(point, index) {
            sets[index].x = point.x;
            sets[index].y = point.y;
            positioned[index] = true;
        }

        // add most overlapped set at (0,0)
        positionSet({x: 0, y: 0}, mostOverlapped[0].set);

        // get distances between all points
        var distances = venn.getDistanceMatrix(sets, overlaps);

        for (i = 1; i < mostOverlapped.length; ++i) {
            var setIndex = mostOverlapped[i].set,
                overlap = setOverlaps[setIndex].filter(isPositioned);
            set = sets[setIndex];
            overlap.sort(sortOrder);

            if (overlap.length === 0) {
                throw "Need overlap information for set " + JSON.stringify( set );
            }

            var points = [];
            for (var j = 0; j < overlap.length; ++j) {
                // get appropriate distance from most overlapped already added set
                var p1 = sets[overlap[j].set],
                    d1 = distances[setIndex][overlap[j].set];

                // sample positions at 90 degrees for maximum aesthetics
                points.push({x : p1.x + d1, y : p1.y});
                points.push({x : p1.x - d1, y : p1.y});
                points.push({y : p1.y + d1, x : p1.x});
                points.push({y : p1.y - d1, x : p1.x});

                // if we have at least 2 overlaps, then figure out where the
                // set should be positioned analytically and try those too
                for (var k = j + 1; k < overlap.length; ++k) {
                    var p2 = sets[overlap[k].set],
                        d2 = distances[setIndex][overlap[k].set];

                    var extraPoints = venn.circleCircleIntersection(
                        { x: p1.x, y: p1.y, radius: d1},
                        { x: p2.x, y: p2.y, radius: d2});

                    for (var l = 0; l < extraPoints.length; ++l) {
                        points.push(extraPoints[l]);
                    }
                }
            }

            // we have some candidate positions for the set, examine loss
            // at each position to figure out where to put it at
            var bestLoss = 1e50, bestPoint = points[0];
            for (j = 0; j < points.length; ++j) {
                sets[setIndex].x = points[j].x;
                sets[setIndex].y = points[j].y;
                var loss = venn.lossFunction(sets, overlaps);
                if (loss < bestLoss) {
                    bestLoss = loss;
                    bestPoint = points[j];
                }
            }

            positionSet(bestPoint, setIndex);
        }

        return sets;
    };

    /// Uses multidimensional scaling to approximate a first layout here
    venn.classicMDSLayout = function(sets, overlaps) {
        // get the distance matrix
        var distances = venn.getDistanceMatrix(sets, overlaps);

        // get positions for each set
        var positions = mds.classic(distances);

        // translate back to (x,y,radius) coordinates
        for (var i = 0; i < sets.length; ++i) {
            sets[i].x = positions[i][0];
            sets[i].y = positions[i][1];
            sets[i].radius = Math.sqrt(sets[i].size / Math.PI);
        }
        return sets;
    };

    /** Given a bunch of sets, and the desired overlaps between these sets - computes
    the distance from the actual overlaps to the desired overlaps. Note that
    this method ignores overlaps of more than 2 circles */
    venn.lossFunction = function(sets, overlaps) {
        var output = 0;

        function getCircles(indices) {
            return indices.map(function(i) { return sets[i]; });
        }

        for (var i = 0; i < overlaps.length; ++i) {
            var area = overlaps[i], overlap;
            if (area.sets.length == 2) {
                var left = sets[area.sets[0]],
                    right = sets[area.sets[1]];
                overlap = venn.circleOverlap(left.radius, right.radius,
                                             venn.distance(left, right));
            } else {
                overlap = venn.intersectionArea(getCircles(area.sets));
            }

            var weight = (area.weight == null) ? 1.0 : area.weight;
            output += weight * (overlap - area.size) * (overlap - area.size);
        }

        return output;
    };

    /** Scales a solution from venn.venn or venn.greedyLayout such that it fits in
    a rectangle of width/height - with padding around the borders. also
    centers the diagram in the available space at the same time */
    venn.scaleSolution = function(solution, width, height, padding) {
        var minMax = function(d) {
            var hi = Math.max.apply(null, solution.map(
                                    function(c) { return c[d] + c.radius; } )),
                lo = Math.min.apply(null, solution.map(
                                    function(c) { return c[d] - c.radius;} ));
            return {max:hi, min:lo};
        };

        width -= 2*padding;
        height -= 2*padding;

        var xRange = minMax('x'),
            yRange = minMax('y'),
            xScaling = width  / (xRange.max - xRange.min),
            yScaling = height / (yRange.max - yRange.min),
            scaling = Math.min(yScaling, xScaling),

            // while we're at it, center the diagram too
            xOffset = (width -  (xRange.max - xRange.min) * scaling) / 2,
            yOffset = (height - (yRange.max - yRange.min) * scaling) / 2;

        for (var i = 0; i < solution.length; ++i) {
            var set = solution[i];
            set.radius = scaling * set.radius;
            set.x = padding + xOffset + (set.x - xRange.min) * scaling;
            set.y = padding + yOffset + (set.y - yRange.min) * scaling;
        }

        return solution;
    };

    // sometimes text doesn't fit inside the circle, if thats the case lets wrap
    // the text here such that it fits
    // todo: looks like this might be merged into d3 (
    // https://github.com/mbostock/d3/issues/1642),
    // also worth checking out is
    // http://engineering.findthebest.com/wrapping-axis-labels-in-d3-js/
    // this seems to be one of those things that should be easy but isn't
    function wrapText() {
        var text = d3.select(this),
            data = text.datum(),
            width = data.radius,
            words = data.label.split(/\s+/).reverse(),
            maxLines = 3,
            minChars = (data.label.length + words.length) / maxLines,
            word = words.pop(),
            line = [word],
            joined,
            lineNumber = 0,
            lineHeight = 1.1, // ems
            tspan = text.text(null).append("tspan").text(word);

        while (word = words.pop()) {
            line.push(word);
            joined = line.join(" ");
            tspan.text(joined);
            if (joined.length > minChars && tspan.node().getComputedTextLength() > width) {
                line.pop();
                tspan.text(line.join(" "));
                line = [word];
                tspan = text.append("tspan").text(word);
                lineNumber++;
            }
        }

        var initial = 0.35 - lineNumber * lineHeight / 2,
            x = Math.floor(data.textCenter.x),
            y = Math.floor(data.textCenter.y);

        text.selectAll("tspan")
            .attr("x", x)
            .attr("y", y)
            .attr("dy", function(d, i) {
                 return (initial + i * lineHeight) + "em";
            });
    }

    function weightedSum(a, b) {
        var ret = new Array(a[1].length || 0);
        for (var j = 0; j < ret.length; ++j) {
            ret[j] = a[0] * a[1][j] + b[0] * b[1][j];
        }
        return ret;
    }

    /** finds the zeros of a function, given two starting points (which must
     * have opposite signs */
    venn.bisect = function(f, a, b, parameters) {
        parameters = parameters || {};
        var maxIterations = parameters.maxIterations || 100,
            tolerance = parameters.tolerance || 1e-10,
            fA = f(a),
            fB = f(b),
            delta = b - a;

        if (fA * fB > 0) {
            throw "Initial bisect points must have opposite signs";
        }

        if (fA === 0) return a;
        if (fB === 0) return b;

        for (var i = 0; i < maxIterations; ++i) {
            delta /= 2;
            var mid = a + delta,
                fMid = f(mid);

            if (fMid * fA >= 0) {
                a = mid;
            }

            if ((Math.abs(delta) < tolerance) || (fMid === 0)) {
                return mid;
            }
        }
        return a + delta;
    };

    /** minimizes a function using the downhill simplex method */
    venn.fmin = function(f, x0, parameters) {
        parameters = parameters || {};

        var maxIterations = parameters.maxIterations || x0.length * 200,
            nonZeroDelta = parameters.nonZeroDelta || 1.1,
            zeroDelta = parameters.zeroDelta || 0.001,
            minErrorDelta = parameters.minErrorDelta || 1e-5,
            rho = parameters.rho || 1,
            chi = parameters.chi || 2,
            psi = parameters.psi || -0.5,
            sigma = parameters.sigma || 0.5,
            callback = parameters.callback;

        // initialize simplex.
        var N = x0.length,
            simplex = new Array(N + 1);
        simplex[0] = x0;
        simplex[0].fx = f(x0);
        for (var i = 0; i < N; ++i) {
            var point = x0.slice();
            point[i] = point[i] ? point[i] * nonZeroDelta : zeroDelta;
            simplex[i+1] = point;
            simplex[i+1].fx = f(point);
        }

        var sortOrder = function(a, b) { return a.fx - b.fx; };

        for (var iteration = 0; iteration < maxIterations; ++iteration) {
            simplex.sort(sortOrder);
            if (callback) {
                callback(simplex);
            }

            if (Math.abs(simplex[0].fx - simplex[N].fx) < minErrorDelta) {
                break;
            }

            // compute the centroid of all but the worst point in the simplex
            var centroid = new Array(N);
            for (i = 0; i < N; ++i) {
                centroid[i] = 0;
                for (var j = 0; j < N; ++j) {
                    centroid[i] += simplex[j][i];
                }
                centroid[i] /= N;
            }

            // reflect the worst point past the centroid  and compute loss at reflected
            // point
            var worst = simplex[N];
            var reflected = weightedSum([1+rho, centroid], [-rho, worst]);
            reflected.fx = f(reflected);

            var replacement = reflected;

            // if the reflected point is the best seen, then possibly expand
            if (reflected.fx <= simplex[0].fx) {
                var expanded = weightedSum([1+chi, centroid], [-chi, worst]);
                expanded.fx = f(expanded);
                if (expanded.fx < reflected.fx) {
                    replacement = expanded;
                }
            }

            // if the reflected point is worse than the second worst, we need to
            // contract
            else if (reflected.fx >= simplex[N-1].fx) {
                var shouldReduce = false;
                var contracted;

                if (reflected.fx <= worst.fx) {
                    // do an inside contraction
                    contracted = weightedSum([1+psi, centroid], [-psi, worst]);
                    contracted.fx = f(contracted);
                    if (contracted.fx < worst.fx) {
                        replacement = contracted;
                    } else {
                        shouldReduce = true;
                    }
                } else {
                    // do an outside contraction
                    contracted = weightedSum([1-psi * rho, centroid], [psi*rho, worst]);
                    contracted.fx = f(contracted);
                    if (contracted.fx <= reflected.fx) {
                        replacement = contracted;
                    } else {
                        shouldReduce = true;
                    }
                }

                if (shouldReduce) {
                    // do reduction. doesn't actually happen that often
                    for (i = 1; i < simplex.length; ++i) {
                        simplex[i] = weightedSum([1 - sigma, simplex[0]],
                                                 [sigma - 1, simplex[i]]);
                        simplex[i].fx = f(simplex[i]);
                    }
                }
            }

            simplex[N] = replacement;
        }

        simplex.sort(sortOrder);
        return {f : simplex[0].fx,
                solution : simplex[0]};
    };

    /** returns a svg path of the intersection area of a bunch of circles */
    venn.intersectionAreaPath = function(circles) {
        var stats = {};
        venn.intersectionArea(circles, stats);
        var arcs = stats.arcs;

        if (arcs.length === 0) {
            return "M 0 0";
        }

        var ret = ["\nM", arcs[0].p2.x, arcs[0].p2.y];
        for (var i = 0; i < arcs.length; ++i) {
            var arc = arcs[i], r = arc.circle.radius, wide = arc.width > r;
            ret.push("\nA", r, r, 0, wide ? 1 : 0, 1, arc.p1.x, arc.p1.y);
        }

        return ret.join(" ");
    };

    // computes the center for text by sampling perimiter of circle, and taking
    // the average of points on perimeter that are only in that circle
    function computeTextCenters(sets, width, height, diagram) {
        // basically just finding the center point of each region by sampling
        // points in a grid and taking the average sampled point for each region
        // There is probably an analytic way of computing this exactly, but
        // this works well enough for our purposes
        var sums = [];
        for (var i = 0; i < sets.length; ++i) {
            sums.push({'x' : 0, 'y' : 0, 'count' : 0});
        }

        var samples = 32;
        for (var i = 0; i < samples; ++i) {
            var x = i * width / samples;
            for (var j = 0; j < samples; ++j) {
                var y = j * height / samples;
                var point = {'x' : x, 'y' : y};

                var contained = []

                for (var k = 0; k < sets.length; ++k) {
                    if (venn.distance(point, sets[k]) <= sets[k].radius) {
                        contained.push(k);
                    }
                }
                if (contained.length == 1) {
                    var sum = sums[contained[0]];
                    sum.x += point.x;
                    sum.y += point.y;
                    sum.count += 1;
                }
            }
        }

        for (var i = 0; i < sets.length; ++i) {
            var sum = sums[i];
            if (sum.count) {
                sets[i].textCenter = { 'x' : sum.x / sum.count,
                                       'y' : sum.y / sum.count};
            } else {
                // no sampled points, possibly completely overlapped (or tiny)
                // use circle centre
                sets[i].textCenter = { 'x' : sets[i].x,
                                       'y' : sets[i].y};
            }
        }
    }

    venn.drawD3Diagram = function(element, dataset, width, height, parameters) {
        parameters = parameters || {};

        var colours = d3.scale.category10(),
            padding = ('padding' in parameters) ? parameters.padding : 6;

        dataset = venn.scaleSolution(dataset, width, height, padding);
        computeTextCenters(dataset, width, height);

        var svg = element.append("svg")
                .attr("width", width)
                .attr("height", height);

        var diagram = svg.append( "g" );

        var nodes = diagram.append("g").selectAll("circle")
                         .data(dataset)
                         .enter()
                         .append("g");

        var circles = nodes.append("circle")
               .attr("r",  function(d) { return d.radius; })
               .style("fill-opacity", 0.3)
               .attr("cx", function(d) { return d.x; })
               .attr("cy", function(d) { return d.y; })
               .style("fill", function(d, i) { return colours(i); });

        var text = nodes.append("text")
               .attr("dy", ".35em")
               .attr("x", function(d) { return Math.floor(d.textCenter.x); })
               .attr("y", function(d) { return Math.floor(d.textCenter.y); })
               .attr("text-anchor", "middle")
               .style("fill", function(d, i) { return colours(i); })
               .call(function (text) { text.each(wrapText); });

        return {'svg' : svg,
                'nodes' : nodes,
                'circles' : circles,
                'text' : text };
    };

    venn.updateD3Diagram = function(diagram, dataset, parameters) {
        parameters = parameters || {};
        var padding = ('padding' in parameters) ? parameters.padding : 6,
            duration = ('duration' in parameters) ? parameters.duration : 400;

        var svg = diagram.svg,
            width = parseInt(svg.attr('width'), 10),
            height = parseInt(svg.attr('height'), 10);

        dataset = venn.scaleSolution(dataset, width, height, padding);
        computeTextCenters(dataset, width, height);

        var transition = diagram.nodes
            .data(dataset)
            .transition()
            .duration(duration);

        transition.select("circle")
            .attr("cx", function(d) { return d.x; })
            .attr("cy", function(d) { return d.y; })
            .attr("r",  function(d) { return d.radius; });

        // transtitioning the text is a little tricky in the case
        // of wrapping. so lets basically transition unwrapped text
        // and at the end of the transition we'll wrap it again
        transition.select("text")
            .text(function (d) { return d.label; } )
            .each("end", wrapText)
            .attr("x", function(d) { return Math.floor(d.textCenter.x); })
            .attr("y", function(d) { return Math.floor(d.textCenter.y); });
    };

    var SMALL = 1e-10;

    /** Returns the intersection area of a bunch of circles (where each circle
     is an object having an x,y and radius property) */
    venn.intersectionArea = function(circles, stats) {
        // get all the intersection points of the circles
        var intersectionPoints = getIntersectionPoints(circles);

        // filter out points that aren't included in all the circles
        var innerPoints = intersectionPoints.filter(function (p) {
            return venn.containedInCircles(p, circles);
        });

        var arcArea = 0, polygonArea = 0, arcs = [], i;

        // if we have intersection points that are within all the circles,
        // then figure out the area contained by them
        if (innerPoints.length > 1) {
            // sort the points by angle from the center of the polygon, which lets
            // us just iterate over points to get the edges
            var center = venn.getCenter(innerPoints);
            for (i = 0; i < innerPoints.length; ++i ) {
                var p = innerPoints[i];
                p.angle = Math.atan2(p.x - center.x, p.y - center.y);
            }
            innerPoints.sort(function(a,b) { return b.angle - a.angle;});

            // iterate over all points, get arc between the points
            // and update the areas
            var p2 = innerPoints[innerPoints.length - 1];
            for (i = 0; i < innerPoints.length; ++i) {
                var p1 = innerPoints[i];

                // polygon area updates easily ...
                polygonArea += (p2.x + p1.x) * (p1.y - p2.y);

                // updating the arc area is a little more involved
                var midPoint = {x : (p1.x + p2.x) / 2,
                                y : (p1.y + p2.y) / 2},
                    arc = null;

                for (var j = 0; j < p1.parentIndex.length; ++j) {
                    if (p2.parentIndex.indexOf(p1.parentIndex[j]) > -1) {
                        // figure out the angle halfway between the two points
                        // on the current circle
                        var circle = circles[p1.parentIndex[j]],
                            a1 = Math.atan2(p1.x - circle.x, p1.y - circle.y),
                            a2 = Math.atan2(p2.x - circle.x, p2.y - circle.y);

                        var angleDiff = (a2 - a1);
                        if (angleDiff < 0) {
                            angleDiff += 2*Math.PI;
                        }

                        // and use that angle to figure out the width of the
                        // arc
                        var a = a2 - angleDiff/2,
                            width = venn.distance(midPoint, {
                                x : circle.x + circle.radius * Math.sin(a),
                                y : circle.y + circle.radius * Math.cos(a)
                            });

                        // pick the circle whose arc has the smallest width
                        if ((arc === null) || (arc.width > width)) {
                            arc = { circle : circle,
                                    width : width,
                                    p1 : p1,
                                    p2 : p2};
                        }
                    }
                }
                arcs.push(arc);
                arcArea += venn.circleArea(arc.circle.radius, arc.width);
                p2 = p1;
            }
        } else {
            // no intersection points, is either disjoint - or is completely
            // overlapped. figure out which by examining the smallest circle
            var smallest = circles[0];
            for (i = 1; i < circles.length; ++i) {
                if (circles[i].radius < smallest.radius) {
                    smallest = circles[i];
                }
            }

            // make sure the smallest circle is completely contained in all
            // the other circles
            var disjoint = false;
            for (i = 0; i < circles.length; ++i) {
                if (venn.distance(circles[i], smallest) > Math.abs(smallest.radius - circles[i].radius)) {
                    disjoint = true;
                    break;
                }
            }

            if (disjoint) {
                arcArea = polygonArea = 0;

            } else {
                arcArea = smallest.radius * smallest.radius * Math.PI;
                arcs.push({circle : smallest,
                           p1: { x: smallest.x,        y : smallest.y + smallest.radius},
                           p2: { x: smallest.x - SMALL, y : smallest.y + smallest.radius},
                           width : smallest.radius * 2 });
            }
        }

        polygonArea /= 2;
        if (stats) {
            stats.area = arcArea + polygonArea;
            stats.arcArea = arcArea;
            stats.polygonArea = polygonArea;
            stats.arcs = arcs;
            stats.innerPoints = innerPoints;
            stats.intersectionPoints = intersectionPoints;
        }

        return arcArea + polygonArea;
    };

    /** returns whether a point is contained by all of a list of circles */
    venn.containedInCircles = function(point, circles) {
        for (var i = 0; i < circles.length; ++i) {
            if (venn.distance(point, circles[i]) > circles[i].radius + SMALL) {
                return false;
            }
        }
        return true;
    };

    /** Gets all intersection points between a bunch of circles */
    function getIntersectionPoints(circles) {
        var ret = [];
        for (var i = 0; i < circles.length; ++i) {
            for (var j = i + 1; j < circles.length; ++j) {
                var intersect = venn.circleCircleIntersection(circles[i],
                                                              circles[j]);
                for (var k = 0; k < intersect.length; ++k) {
                    var p = intersect[k];
                    p.parentIndex = [i,j];
                    ret.push(p);
                }
            }
        }
        return ret;
    }

    venn.circleIntegral = function(r, x) {
        var y = Math.sqrt(r * r - x * x);
        return x * y + r * r * Math.atan2(x, y);
    };

    /** Returns the area of a circle of radius r - up to width */
    venn.circleArea = function(r, width) {
        return venn.circleIntegral(r, width - r) - venn.circleIntegral(r, -r);
    };


    /** euclidean distance between two points */
    venn.distance = function(p1, p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                         (p1.y - p2.y) * (p1.y - p2.y));
    };


    /** Returns the overlap area of two circles of radius r1 and r2 - that
    have their centers separated by distance d. Simpler faster
    circle intersection for only two circles */
    venn.circleOverlap = function(r1, r2, d) {
        // no overlap
        if (d >= r1 + r2) {
            return 0;
        }

        // completely overlapped
        if (d <= Math.abs(r1 - r2)) {
            return Math.PI * Math.min(r1, r2) * Math.min(r1, r2);
        }

        var w1 = r1 - (d * d - r2 * r2 + r1 * r1) / (2 * d),
            w2 = r2 - (d * d - r1 * r1 + r2 * r2) / (2 * d);
        return venn.circleArea(r1, w1) + venn.circleArea(r2, w2);
    };


    /** Given two circles (containing a x/y/radius attributes),
    returns the intersecting points if possible.
    note: doesn't handle cases where there are infinitely many
    intersection points (circles are equivalent):, or only one intersection point*/
    venn.circleCircleIntersection = function(p1, p2) {
        var d = venn.distance(p1, p2),
            r1 = p1.radius,
            r2 = p2.radius;

        // if to far away, or self contained - can't be done
        if ((d >= (r1 + r2)) || (d <= Math.abs(r1 - r2))) {
            return [];
        }

        var a = (r1 * r1 - r2 * r2 + d * d) / (2 * d),
            h = Math.sqrt(r1 * r1 - a * a),
            x0 = p1.x + a * (p2.x - p1.x) / d,
            y0 = p1.y + a * (p2.y - p1.y) / d,
            rx = -(p2.y - p1.y) * (h / d),
            ry = -(p2.x - p1.x) * (h / d);

        return [{ x: x0 + rx, y : y0 - ry },
                { x: x0 - rx, y : y0 + ry }];
    };

    /** Returns the center of a bunch of points */
    venn.getCenter = function(points) {
        var center = { x: 0, y: 0};
        for (var i =0; i < points.length; ++i ) {
            center.x += points[i].x;
            center.y += points[i].y;
        }
        center.x /= points.length;
        center.y /= points.length;
        return center;
    };
}(window.venn = window.venn || {}));
