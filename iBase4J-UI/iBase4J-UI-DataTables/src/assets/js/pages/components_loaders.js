/* ------------------------------------------------------------------------------
*
*  # Page and progress loaders
*
*  Specific JS code additions for components_loaders.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // ========================================
    //
    // Progress bars
    //
    // ========================================


    // Basic progress bar
    // ------------------------------

    // Start
    $('#h-default-basic-start').click(function() {
        var $pb = $('#h-default-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar();
    });

    // Reset
    $('#h-default-basic-reset').click(function() {
        $('#h-default-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Progress bar in right direction
    // ------------------------------

    // Start
    $('#h-right-basic-start').click(function() {
        var $pb = $('#h-right-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar();
    });

    // Reset
    $('#h-right-basic-reset').click(function() {
        $('#h-right-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Progress bar with text fill
    // ------------------------------

    // Start
    $('#h-fill-basic-start').click(function() {
        var $pb = $('#h-fill-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar({display_text: 'fill'});
    });

    // Reset
    $('#h-fill-basic-reset').click(function() {
        $('#h-fill-basic .progress-bar').attr('data-transitiongoal', 0).progressbar({display_text: 'fill'});
    });
            


    // Progress bar with non-percentage text
    // ------------------------------

    // Start
    $('#h-fill-nonpercentage-basic-start').click(function() {
        var $pb = $('#h-fill-nonpercentage-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar({display_text: 'fill', use_percentage: false});
    });

    // Reset
    $('#h-fill-nonpercentage-basic-reset').click(function() {
        $('#h-fill-nonpercentage-basic .progress-bar').attr('data-transitiongoal', 0).progressbar({display_text: 'fill', use_percentage: false});
    });



    // Progress bar with centered text
    // ------------------------------

    // Start
    $('#h-center-basic-start').click(function() {
        var $pb = $('#h-center-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar({display_text: 'center'});
    });

    // Reset
    $('#h-center-basic-reset').click(function() {
        $('#h-center-basic .progress-bar').attr('data-transitiongoal', 0).progressbar({display_text: 'center'});
    });
            


    // Progress bar with centered non-percentage text
    // ------------------------------

    // Start
    $('#h-center-nonpercentage-basic-start').click(function() {
        var $pb = $('#h-center-nonpercentage-basic .progress-bar');
        $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
        $pb.progressbar({display_text: 'center', use_percentage: false});
    });

    // Reset
    $('#h-center-nonpercentage-basic-reset').click(function() {
        $('#h-center-nonpercentage-basic .progress-bar').attr('data-transitiongoal', 0).progressbar({display_text: 'center', use_percentage: false});
    });
            


    // Vertical progress bar
    // ------------------------------

    // Start
    $('#v-default-basic-start').click(function() {
        $('#v-default-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar();
        });
    });

    // Reset
    $('#v-default-basic-reset').click(function() {
        $('#v-default-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Bottom direction
    // ------------------------------

    // Start
    $('#v-bottom-basic-start').click(function() {
        $('#v-bottom-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar();
        });
    });

    // Reset
    $('#v-bottom-basic-reset').click(function() {
        $('#v-bottom-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Vertical progress bar with text fill
    // ------------------------------

    // Start
    $('#v-fill-basic-start').click(function() {
        $('#v-fill-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar({display_text: 'fill'});
        });
    });

    // Reset
    $('#v-fill-basic-reset').click(function() {
        $('#v-fill-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Vertical progress bar with non-percentage text
    // ------------------------------

    // Start
    $('#v-fill-nonpercentage-basic-start').click(function() {
        $('#v-fill-nonpercentage-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar({display_text: 'fill', use_percentage: false});
        });
    });

    // Reset
    $('#v-fill-nonpercentage-basic-reset').click(function() {
        $('#v-fill-nonpercentage-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Vertical progress bar with centered text
    // ------------------------------

    // Start
    $('#v-center-basic-start').click(function() {
        $('#v-center-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar({display_text: 'center'});
        });
    });

    // Reset
    $('#v-center-basic-reset').click(function() {
        $('#v-center-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });



    // Vertical progress bar with centered non-percentage text
    // ------------------------------

    // Start
    $('#v-center-nonpercentage-basic-start').click(function() {
        $('#v-center-nonpercentage-basic .progress-bar').each(function () {
            var $pb = $(this);
            $pb.attr('data-transitiongoal', $pb.attr('data-transitiongoal-backup'));
            $pb.progressbar({display_text: 'center', use_percentage: false});
        });
    });

    // Reset
    $('#v-center-nonpercentage-basic-reset').click(function() {
        $('#v-center-nonpercentage-basic .progress-bar').attr('data-transitiongoal', 0).progressbar();
    });




    // ========================================
    //
    // Loading spinners
    //
    // ========================================


    // Spinner #1
    // ------------------------------

    // Light
    $('#spinner-light').on('click', function() {
        var light = $(this).parent();
        $(light).block({
            message: '<i class="icon-spinner spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark').on('click', function() {
        var dark = $(this).parent();
        $(dark).block({
            message: '<i class="icon-spinner spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark).unblock();
        }, 2000);
    });



    // Spinner #2
    // ------------------------------

    // Light
    $('#spinner-light-2').on('click', function() {
        var light_2 = $(this).parent();
        $(light_2).block({
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_2).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-2').on('click', function() {
        var dark_2 = $(this).parent();
        $(dark_2).block({
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_2).unblock();
        }, 2000);
    });



    // Spinner #3
    // ------------------------------

    // Light
    $('#spinner-light-3').on('click', function() {
        var light_3 = $(this).parent();
        $(light_3).block({
            message: '<i class="icon-spinner3 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_3).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-3').on('click', function() {
        var dark_3 = $(this).parent();
        $(dark_3).block({
            message: '<i class="icon-spinner3 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_3).unblock();
        }, 2000);
    });



    // Spinner #4
    // ------------------------------

    // Light
    $('#spinner-light-4').on('click', function() {
        var light_4 = $(this).parent();
        $(light_4).block({
            message: '<i class="icon-spinner4 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_4).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-4').on('click', function() {
        var dark_4 = $(this).parent();
        $(dark_4).block({
            message: '<i class="icon-spinner4 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_4).unblock();
        }, 2000);
    });



    // Spinner #5
    // ------------------------------

    // Light
    $('#spinner-light-5').on('click', function() {
        var light_5 = $(this).parent();
        $(light_5).block({
            message: '<i class="icon-spinner6 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_5).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-5').on('click', function() {
        var dark_5 = $(this).parent();
        $(dark_5).block({
            message: '<i class="icon-spinner6 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_5).unblock();
        }, 2000);
    });



    // Spinner #6
    // ------------------------------

    // Light
    $('#spinner-light-6').on('click', function() {
        var light_6 = $(this).parent();
        $(light_6).block({
            message: '<i class="icon-spinner9 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_6).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-6').on('click', function() {
        var dark_6 = $(this).parent();
        $(dark_6).block({
            message: '<i class="icon-spinner9 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_6).unblock();
        }, 2000);
    });



    // Spinner #7
    // ------------------------------

    // Light
    $('#spinner-light-7').on('click', function() {
        var light_7 = $(this).parent();
        $(light_7).block({
            message: '<i class="icon-spinner10 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_7).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-7').on('click', function() {
        var dark_7 = $(this).parent();
        $(dark_7).block({
            message: '<i class="icon-spinner10 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_7).unblock();
        }, 2000);
    });



    // Spinner #8
    // ------------------------------

    // Light
    $('#spinner-light-8').on('click', function() {
        var light_8 = $(this).parent();
        $(light_8).block({
            message: '<i class="icon-spinner11 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_8).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-8').on('click', function() {
        var dark_8 = $(this).parent();
        $(dark_8).block({
            message: '<i class="icon-spinner11 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_8).unblock();
        }, 2000);
    });



    // Spinner #9
    // ------------------------------

    // Light
    $('#spinner-light-9').on('click', function() {
        var light_9 = $(this).parent();
        $(light_9).block({
            message: '<i class="icon-sync spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        window.setTimeout(function () {
            $(light_9).unblock();
        }, 2000);
    });

    // Dark
    $('#spinner-dark-9').on('click', function() {
        var dark_9 = $(this).parent();
        $(dark_9).block({
            message: '<i class="icon-sync spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1B2024',
                opacity: 0.85,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        window.setTimeout(function () {
            $(dark_9).unblock();
        }, 2000);
    });

});
