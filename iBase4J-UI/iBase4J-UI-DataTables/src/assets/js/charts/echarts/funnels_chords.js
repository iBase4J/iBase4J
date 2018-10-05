/* ------------------------------------------------------------------------------
 *
 *  # Echarts - funnels and chords
 *
 *  Funnels and chords chart configurations
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

    // Set paths
    // ------------------------------

    require.config({
        paths: {
            echarts: 'assets/js/plugins/visualization/echarts'
        }
    });


    // Configuration
    // ------------------------------

    require(
        [
            'echarts',
            'echarts/theme/limitless',
            'echarts/chart/funnel',
            'echarts/chart/pie',
            'echarts/chart/chord'
        ],


        // Charts setup
        function (ec, limitless) {


            // Initialize charts
            // ------------------------------

            var funnel_asc = ec.init(document.getElementById('funnel_asc'), limitless);
            var funnel_desc = ec.init(document.getElementById('funnel_desc'), limitless);
            var funnel_left = ec.init(document.getElementById('funnel_left'), limitless);
            var funnel_right = ec.init(document.getElementById('funnel_right'), limitless);
            var funnel_multiple_overlay = ec.init(document.getElementById('funnel_multiple_overlay'), limitless);
            var funnel_multiple_separate = ec.init(document.getElementById('funnel_multiple_separate'), limitless);

            var chord_basic = ec.init(document.getElementById('chord_basic'), limitless);
            var chord_sorting = ec.init(document.getElementById('chord_sorting'), limitless);
            var chord_non_ribbon = ec.init(document.getElementById('chord_non_ribbon'), limitless);
            var chord_scale = ec.init(document.getElementById('chord_scale'), limitless);



            // Charts setup
            // ------------------------------                    

            //
            // Basic funnel options
            //

            funnel_desc_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    y: 75,
                    orient: 'vertical',
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        magicType: {
                            show: true,
                            title: {
                                pie: 'Switch to pies',
                                funnel: 'Switch to funnel',
                            },
                            type: ['pie', 'funnel'],
                            option: {
                                pie: {
                                    radius: '75%',
                                    center: ['50%', '55%']
                                }
                            }
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    x: 'left',
                    y: 75,
                    orient: 'vertical',
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Statistics',
                        type: 'funnel',
                        x: '25%',
                        x2: '25%',
                        y: '17.5%',
                        height: '80%',
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'left'
                                }
                            }
                        },
                        data: [
                            {value: 60, name: 'Safari'},
                            {value: 40, name: 'Firefox'},
                            {value: 20, name: 'Chrome'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Internet Explorer'}
                        ]
                    }
                ]
            };


            //
            // Funnel sorting options
            //

            funnel_asc_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    y: 75,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        magicType: {
                            show: true,
                            title: {
                                pie: 'Switch to pies',
                                funnel: 'Switch to funnel',
                            },
                            type: ['pie', 'funnel'],
                            option: {
                                pie: {
                                    radius: '75%',
                                    center: ['50%', '55%']
                                }
                            }
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    x: 'left',
                    y: 75,
                    orient: 'vertical',
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Statistics',
                        type: 'funnel',
                        x: '25%',
                        x2: '25%',
                        y: '17.5%',
                        height: '80%',
                        sort: 'ascending',
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'left'
                                }
                            }
                        },
                        data: [
                            {value: 60, name: 'Safari'},
                            {value: 40, name: 'Firefox'},
                            {value: 20, name: 'Chrome'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Internet Explorer'}
                        ]
                    }
                ]
            };


            //
            // Left funnel options
            //

            funnel_left_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    y: 75,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        magicType: {
                            show: true,
                            title: {
                                pie: 'Switch to pies',
                                funnel: 'Switch to funnel',
                            },
                            type: ['pie', 'funnel'],
                            option: {
                                pie: {
                                    radius: '75%',
                                    center: ['50%', '55%']
                                }
                            }
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    y: 75,
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Statistics',
                        type: 'funnel',
                        funnelAlign: 'left',
                        x: '25%',
                        x2: '25%',
                        y: '17.5%',
                        width: '50%',
                        height: '80%',
                        data: [
                            {value: 60, name: 'Safari'},
                            {value: 30, name: 'Firefox'},
                            {value: 10, name: 'Chrome'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Internet Explorer'}
                        ]
                    }
                ]
            };


            //
            // Right funnel options
            //

            funnel_right_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    y: 75,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        magicType: {
                            show: true,
                            title: {
                                pie: 'Switch to pies',
                                funnel: 'Switch to funnel',
                            },
                            type: ['pie', 'funnel'],
                            option: {
                                pie: {
                                    radius: '75%',
                                    center: ['50%', '55%']
                                }
                            }
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    y: 75,
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Statistics',
                        type: 'funnel',
                        funnelAlign: 'right',
                        x: '25%',
                        x2: '25%',
                        y: '17.5%',
                        width: '50%',
                        height: '80%',
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'left'
                                }
                            }
                        },
                        data: [
                            {value: 60, name: 'Safari'},
                            {value: 30, name: 'Firefox'},
                            {value: 10, name: 'Chrome'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Internet Explorer'}
                        ]
                    }
                ]
            };


            //
            // Multiple funnels (overlay) options
            //

            funnel_multiple_overlay_options = {

                // Add colors
                color: [
                    'rgba(255, 69, 0, 0.5)',
                    'rgba(255, 150, 0, 0.5)',
                    'rgba(255, 200, 0, 0.5)',
                    'rgba(155, 200, 50, 0.5)',
                    'rgba(55, 200, 100, 0.5)'
                ],

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    y: 75,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    data: ['Chrome','Opera','Safari','Firefox','IE'],
                    orient: 'vertical',
                    x: 'left',
                    y: 75
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Expected',
                        type: 'funnel',
                        y: '17.5%',
                        x: '25%',
                        x2: '25%',
                        width: '50%',
                        height: '80%',
                        itemStyle: {
                            normal: {
                                label: {
                                    formatter: '{b}'
                                },
                                labelLine: {
                                    show: false
                                }
                            },
                            emphasis: {
                                label: {
                                    position: 'inside',
                                    formatter: '{b}: {c}%'
                                }
                            }
                        },
                        data: [
                            {value: 45, name: 'IE'},
                            {value: 60, name: 'Firefox'},
                            {value: 70, name: 'Safari'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Chrome'}
                        ]
                    },
                    {
                        name: 'Result',
                        type: 'funnel',
                        y: '17.5%',
                        x: '25%',
                        x2: '25%',
                        width: '50%',
                        height: '80%',
                        maxSize: '80%',
                        itemStyle: {
                            normal: {
                                borderColor: '#fff',
                                borderWidth: 2,
                                label: {
                                    position: 'inside',
                                    formatter: '{c}%',
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            emphasis: {
                                label: {
                                    position: 'inside',
                                    formatter: '{b}: {c}%'
                                }
                            }
                        },
                        data: [
                            {value: 30, name: 'IE'},
                            {value: 48, name: 'Firefox'},
                            {value: 66, name: 'Safari'},
                            {value: 69, name: 'Opera'},
                            {value: 80, name: 'Chrome'}
                        ]
                    }
                ]
            };


            //
            // Multiple funnels (separate) options
            //

            funnel_multiple_separate_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source information',
                    y: 100
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c}%"
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    y: 'center',
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    y: '40%',
                    data: ['Chrome','Opera','Safari','Firefox','IE','','Android','Windows','OS X','BlackBerry','Others']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [
                    {
                        name: 'Browser',
                        type: 'funnel',
                        x: '35%',
                        width: '40%',
                        height: '43%',
                        y: '3%',
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'left'
                                }
                            }
                        },
                        data: [
                            {value: 60, name: 'Safari'},
                            {value: 30, name: 'Firefox'},
                            {value: 10, name: 'IE'},
                            {value: 80, name: 'Opera'},
                            {value: 100, name: 'Chrome'}
                        ]
                    },
                    {
                        name: 'Operating system',
                        type: 'funnel',
                        x: '35%',
                        width: '40%',
                        height: '43%',
                        y: '55%',
                        sort: 'ascending',
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'right'
                                }
                            }
                        },
                        data: [
                            {value: 60, name: 'Android'},
                            {value: 30, name: 'Windows'},
                            {value: 10, name: 'OS X'},
                            {value: 80, name: 'BlackBerry'},
                            {value: 100, name: 'Others'}
                        ]
                    }
                ]
            };


            //
            // Basic chord options
            //

            chord_basic_options = {

                // Add title
                title: {
                    text: 'Webkit dependencies',
                    subtext: 'Demo stuff taken from d3.js',
                    x: 'right'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        if (params.indicator2) { // is edge
                            return params.value.weight;
                        } else { // is node
                            return params.name
                        }
                    }
                },

                // Add legend
                legend: {
                    x: 'left',
                    orient: 'vertical',
                    data: ['Group1','Group2', 'Group3', 'Group4']
                },

                // Add series
                series: [
                    {
                        type: 'chord',
                        data: [
                            {name: 'Group1'},
                            {name: 'Group2'},
                            {name: 'Group3'},
                            {name: 'Group4'}
                        ],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false
                                }
                            }
                        },
                        matrix: [
                            [11975,  5871, 8916, 2868],
                            [ 1951, 10048, 2060, 6171],
                            [ 8010, 16145, 8090, 8045],
                            [ 1013,   990,  940, 6907]
                        ]
                    }
                ]
            };


            //
            // Funnel scales options
            //

            chord_scale_options = {

                // Add title
                title: {
                    text: 'Webkit dependencies',
                    subtext: 'Demo stuff taken from d3.js',
                    x: 'right'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        if (params.indicator2) { // is edge
                            return params.value.weight;
                        } else { // is node
                            return params.name
                        }
                    }
                },

                // Add legend
                legend: {
                    x: 'left',
                    orient: 'vertical',
                    data: ['Group1','Group2', 'Group3', 'Group4']
                },

                // Add series
                series: [
                    {
                        type: 'chord',
                        showScale: true,
                        showScaleText: true,
                        clockWise: true,
                        data: [
                            {name: 'Group1'},
                            {name: 'Group2'},
                            {name: 'Group3'},
                            {name: 'Group4'}
                        ],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false
                                }
                            }
                        },
                        matrix: [
                            [11975,  5871, 8916, 2868],
                            [ 1951, 10048, 2060, 6171],
                            [ 8010, 16145, 8090, 8045],
                            [ 1013,   990,  940, 6907]
                        ]
                    }
                ]
            };


            //
            // Chord sorting options
            //

            chord_sorting_options = {

                // Add title
                title: {
                    text: 'Fußball Bundesliga',
                    subtext: 'Players effectiveness',
                    x: 'right'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        if (params.indicator2) { // is edge
                            return params.indicator2 + ': ' + params.indicator;
                        }
                        else { // is node
                            return params.name
                        }
                    }
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['Arsenal', 'Bayern', 'Dortmund']
                },

                // Add series
                series: [
                    {
                        type: 'chord',
                        sort: 'ascending',
                        sortSub: 'descending',
                        showScale: false,
                        itemStyle: {
                            normal: {
                                label: {
                                    rotate: true
                                }
                            }
                        },
                        nodes: [
                            {name: 'Gibbs'},
                            {name: 'Ozil'},
                            {name: 'Podolski'},
                            {name: 'Neuer'},
                            {name: 'Boateng'},
                            {name: 'Schweinsteiger'},
                            {name: 'Ram'},
                            {name: 'Cross'},
                            {name: 'Muller'},
                            {name: 'Goetze'},
                            {name: 'Hummels'},
                            {name: 'Reus'},
                            {name: 'Durm'},
                            {name: 'Sahin'},
                            {name: 'Arsenal'},
                            {name: 'Bayern'},
                            {name: 'Dortmund'}
                        ],
                        links: [
                            {source: 'Arsenal', target: 'Gibbs', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Arsenal', target: 'Ozil', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Arsenal', target: 'Podolski', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Neuer', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Boateng', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Schweinsteiger', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Ram', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Cross', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Muller', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Goetze', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Hummels', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Reus', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Durm', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Sahin', weight: 0.9, name: 'Effectiveness'},

                            // Ribbon Type
                            {target: 'Arsenal', source: 'Gibbs', weight: 1},
                            {target: 'Arsenal', source: 'Ozil', weight: 1},
                            {target: 'Arsenal', source: 'Podolski', weight: 1},
                            {target: 'Bayern', source: 'Neuer', weight: 1},
                            {target: 'Bayern', source: 'Boateng', weight: 1},
                            {target: 'Bayern', source: 'Schweinsteiger', weight: 1},
                            {target: 'Bayern', source: 'Ram', weight: 1},
                            {target: 'Bayern', source: 'Cross', weight: 1},
                            {target: 'Bayern', source: 'Muller', weight: 1},
                            {target: 'Bayern', source: 'Goetze', weight: 1},
                            {target: 'Dortmund', source: 'Hummels', weight: 1},
                            {target: 'Dortmund', source: 'Reus', weight: 1},
                            {target: 'Dortmund', source: 'Durm', weight: 1},
                            {target: 'Dortmund', source: 'Sahin', weight: 1}
                        ]
                    }
                ]
            };


            //
            // Non-ribbon chord options
            //

            chord_non_ribbon_options = {

                // Add title
                title: {
                    text: 'Fußball Bundesliga',
                    subtext: 'Players effectiveness',
                    x: 'right'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        if (params.indicator2) { // is edge
                            return params.indicator2 + ': ' + params.indicator;
                        }
                        else { // is node
                            return params.name
                        }
                    }
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['Arsenal', 'Bayern', 'Dortmund']
                },

                // Add series
                series: [
                    {
                        type: 'chord',
                        sort: 'ascending',
                        sortSub: 'descending',
                        showScale: false,
                        ribbonType: false,
                        radius: '68%',
                        minRadius: 7,
                        maxRadius: 20,
                        itemStyle: {
                            normal: {
                                chordStyle: {
                                    color: '#999'
                                },
                                label: {
                                    rotate: true
                                }
                            }
                        },
                        nodes: [
                            {name: 'Gibbs'},
                            {name: 'Ozil'},
                            {name: 'Podolski'},
                            {name: 'Neuer'},
                            {name: 'Boateng'},
                            {name: 'Schweinsteiger'},
                            {name: 'Ram'},
                            {name: 'Cross'},
                            {name: 'Muller'},
                            {name: 'Goetze'},
                            {name: 'Hummels'},
                            {name: 'Reus'},
                            {name: 'Durm'},
                            {name: 'Sahin'},
                            {name: 'Arsenal'},
                            {name: 'Bayern'},
                            {name: 'Dortmund'}
                        ],
                        links: [
                            {source: 'Arsenal', target: 'Gibbs', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Arsenal', target: 'Ozil', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Arsenal', target: 'Podolski', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Neuer', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Boateng', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Schweinsteiger', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Ram', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Cross', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Muller', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Bayern', target: 'Goetze', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Hummels', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Reus', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Durm', weight: 0.9, name: 'Effectiveness'},
                            {source: 'Dortmund', target: 'Sahin', weight: 0.9, name: 'Effectiveness'},

                            // Ribbon Type
                            {target: 'Arsenal', source: 'Gibbs', weight: 1},
                            {target: 'Arsenal', source: 'Ozil', weight: 1},
                            {target: 'Arsenal', source: 'Podolski', weight: 1},
                            {target: 'Bayern', source: 'Neuer', weight: 1},
                            {target: 'Bayern', source: 'Boateng', weight: 1},
                            {target: 'Bayern', source: 'Schweinsteiger', weight: 1},
                            {target: 'Bayern', source: 'Ram', weight: 1},
                            {target: 'Bayern', source: 'Cross', weight: 1},
                            {target: 'Bayern', source: 'Muller', weight: 1},
                            {target: 'Bayern', source: 'Goetze', weight: 1},
                            {target: 'Dortmund', source: 'Hummels', weight: 1},
                            {target: 'Dortmund', source: 'Reus', weight: 1},
                            {target: 'Dortmund', source: 'Durm', weight: 1},
                            {target: 'Dortmund', source: 'Sahin', weight: 1}
                        ]
                    }
                ]
            };



            // Apply options
            // ------------------------------

            funnel_asc.setOption(funnel_asc_options);
            funnel_desc.setOption(funnel_desc_options);
            funnel_left.setOption(funnel_left_options);
            funnel_right.setOption(funnel_right_options);
            funnel_multiple_overlay.setOption(funnel_multiple_overlay_options);
            funnel_multiple_separate.setOption(funnel_multiple_separate_options);

            chord_basic.setOption(chord_basic_options);
            chord_sorting.setOption(chord_sorting_options);
            chord_non_ribbon.setOption(chord_non_ribbon_options);
            chord_scale.setOption(chord_scale_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function (){
                    funnel_asc.resize();
                    funnel_desc.resize();
                    funnel_left.resize();
                    funnel_right.resize();
                    funnel_multiple_overlay.resize();
                    funnel_multiple_separate.resize();
                    chord_basic.resize();
                    chord_sorting.resize();
                    chord_non_ribbon.resize();
                    chord_scale.resize();
                }, 200);
            }
        }
    );
});
