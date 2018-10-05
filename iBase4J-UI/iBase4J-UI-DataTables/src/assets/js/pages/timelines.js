/* ------------------------------------------------------------------------------
*
*  # Timelines
*
*  Specific JS code additions for Timeline pages set
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Charts
    // ------------------------------

    // Set paths
    require.config({
        paths: {
            echarts: 'assets/js/plugins/visualization/echarts'
        }
    });


    // Configuration
    require(
        [
            'echarts',
            'echarts/theme/limitless',
            'echarts/chart/line',
            'echarts/chart/bar'
        ],


        // Charts setup
        function (ec, limitless) {

            // Init
            var sales = ec.init(document.getElementById('sales'), limitless);
            var daily_stats = ec.init(document.getElementById('daily_stats'), limitless);


            // Sales chart options
            sales_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 15,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data:['Profit', 'Expenses', 'Income']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'value'
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    axisTick: {
                        show: false
                    },
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                }],

                // Add series
                series: [
                    {
                        name: 'Profit',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'inside'
                                }
                            }
                        },
                        data: [200, 170, 240, 244, 200, 220, 210]
                    },
                    {
                        name: 'Income',
                        type: 'bar',
                        stack: 'Total',
                        barWidth: 5,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [320, 302, 341, 374, 390, 450, 420]
                    },
                    {
                        name: 'Expenses',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'left'
                                }
                            }
                        },
                        data: [-120, -132, -101, -134, -190, -230, -210]
                    }
                ]
            };


            // Daily stats chart options
            daily_stats_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 40,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Enable drag recalculate
                calculable: true,

                // Add legend
                legend: {
                    data: ['Clicks','Visits','Sales']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['00:00','02:00','04:00','06:00','08:00','10:00','12:00','14:00','16:00','18:00','20:00','22:00']
                }],

                // Vertical axis
                yAxis: [
                    {
                        type: 'value',
                        name: 'Visits',
                        axisLabel: {
                            formatter: '{value}k'
                        }
                    },
                    {
                        type: 'value',
                        name: 'Clicks',
                        axisLabel: {
                            formatter: '{value}k'
                        }
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Clicks',
                        type: 'bar',
                        data: [2, 15, 27, 34, 40, 70, 100, 75, 50, 38, 22, 12]
                    },
                    {
                        name: 'Visits',
                        type: 'bar',
                        data: [13, 16, 33, 38, 48, 110, 125, 90, 68, 49, 35, 23]
                    },
                    {
                        name: 'Sales',
                        type: 'line',
                        yAxisIndex: 1,
                        data: [1, 5, 7, 10, 11, 13, 15, 18, 16, 14, 12, 6]
                    }
                ]
            };


            // Apply options
            sales.setOption(sales_options);
            daily_stats.setOption(daily_stats_options);


            // Resize charts
            window.onresize = function() {
                setTimeout(function() {
                    sales.resize();
                    daily_stats.resize();
                }, 200)
            }


            // Resize in tabs
            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                sales.resize();
                daily_stats.resize();
            });
        }
    );



    // Schedule
    // ------------------------------

    // Add events
    var eventsColors = [
        {
            title: 'Day off',
            start: '2014-11-01',
            color: '#DB7272'
        },
        {
            title: 'University',
            start: '2014-11-07',
            end: '2014-11-10',
            color: '#42A5F5'
        },
        {
            id: 999,
            title: 'Shopping',
            start: '2014-11-09T13:00:00',
            color: '#8D6E63'
        },
        {
            id: 999,
            title: 'Shopping',
            start: '2014-11-15T16:00:00',
            color: '#00BCD4'
        },
        {
            title: 'Conference',
            start: '2014-11-11',
            end: '2014-11-13',
            color: '#26A69A'
        },
        {
            title: 'Meeting',
            start: '2014-11-14T08:30:00',
            end: '2014-11-14T12:30:00',
            color: '#7986CB'
        },
        {
            title: 'Meeting',
            start: '2014-11-11T09:30:00',
            color: '#78909C'
        },
        {
            title: 'Happy Hour',
            start: '2014-11-12T14:30:00',
            color: '#26A69A'
        },
        {
            title: 'Dinner',
            start: '2014-11-13T19:00:00',
            color: '#FF7043'
        },
        {
            title: 'Birthday Party',
            start: '2014-11-13T03:00:00',
            color: '#4CAF50'
        }
    ];


    // Initialize with optinos
    $('.schedule').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        defaultView: 'agendaWeek',
        defaultDate: '2014-11-15',
        editable: true,
        events: eventsColors
    });


    // Render if inside hidden element
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $('.schedule').fullCalendar('render');
    });
    
});
