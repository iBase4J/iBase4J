/* ------------------------------------------------------------------------------
*
*  # Noty and jGrowl notifications
*
*  Specific JS code additions for components_notifications_other.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Noty plugin
    // ------------------------------

    var notes = [];

    // Text options
    notes['alert'] = 'Best check yo self, you\'re not looking too good.';
    notes['error'] = 'Change a few things up and try submitting again.';
    notes['success'] = 'You successfully read this important alert message.';
    notes['information'] = 'This alert needs your attention, but it\'s not super important.';
    notes['warning'] = 'Warning! Best check yo self, you\'re not looking too good.';
    notes['confirm'] = 'Do you want to continue?';

    // Initialize
    $('.noty-runner').click(function () {
        var self = $(this);
        noty({
            width: 200,
            text: notes[self.data('type')],
            type: self.data('type'),
            dismissQueue: true,
            timeout: 4000,
            layout: self.data('layout'),
            buttons: (self.data('type') != 'confirm') ? false : [
                {
                    addClass: 'btn btn-primary btn-xs',
                    text: 'Ok',
                    onClick: function ($noty) { //this = button element, $noty = $noty element
                        $noty.close();
                        noty({
                            force: true,
                            text: 'You clicked "Ok" button',
                            type: 'success',
                            layout: self.data('layout')
                        });
                    }
                },
                {
                    addClass: 'btn btn-danger btn-xs',
                    text: 'Cancel',
                    onClick: function ($noty) {
                        $noty.close();
                        noty({
                            force: true,
                            text: 'You clicked "Cancel" button',
                            type: 'error',
                            layout: self.data('layout')
                        });
                    }
                }
            ]
        });
        return false;
    });



    // jGrowl plugin
    // ------------------------------

    // Defaults override - hide "close all" button
    $.jGrowl.defaults.closer = false;


    //
    // Contextual options
    //

    // Solid color theme
    $('#jgrowl-default').on('click', function () {
        $.jGrowl('We are glad to see you again', {
            header: 'Good morning!',
            theme: 'bg-primary'
        });
    });

    // Danger notification
    $('#jgrowl-danger').on('click', function () {
        $.jGrowl('Change a few things up and try submitting again', {
            header: 'Oh snap!',
            theme: 'bg-danger'
        });
    });

    // Success notification
    $('#jgrowl-success').on('click', function () {
        $.jGrowl('You successfully read this important alert message', {
            header: 'Well done!',
            theme: 'bg-success'
        });
    });

    // Warning notification
    $('#jgrowl-warning').on('click', function () {
        $.jGrowl('Better check yourself, you\'re not looking too good', {
            header: 'Attention Here!',
            theme: 'bg-warning'
        });
    });

    // Info notification
    $('#jgrowl-info').on('click', function () {
        $.jGrowl('This alert needs your attention, but it\'s not super important.', {
            header: 'Heads up!',
            theme: 'bg-info'
        });
    });



    //
    // Notification styling
    //

    // Solid left
    $('#jgrowl-solid-styled-left').on('click', function () {
        $.jGrowl('Solid color notification with left icon', {
            header: 'Left icon',
            theme: 'alert-styled-left bg-danger'
        });
    });

    // Solid right
    $('#jgrowl-solid-styled-right').on('click', function () {
        $.jGrowl('Solid color notification with right icon', {
            header: 'Right icon',
            theme: 'alert-styled-right bg-info'
        });
    });

    // Solid custom
    $('#jgrowl-solid-custom-styled').on('click', function () {
        $.jGrowl('Notification with custom colors', {
            header: 'Custom styling',
            theme: 'bg-teal alert-styled-left alert-styled-custom'
        });
    });


    // Styled left
    $('#jgrowl-styled-left').on('click', function () {
        $.jGrowl('Notification with left contextual icon', {
            header: 'Left icon',
            theme: 'alert-bordered alert-styled-left alert-danger'
        });
    });

    // Styled right
    $('#jgrowl-styled-right').on('click', function () {
        $.jGrowl('Notification with right contextual icon', {
            header: 'Right icon',
            theme: 'alert-bordered alert-styled-right alert-danger'
        });
    });

    // Custom style
    $('#jgrowl-custom-styled').on('click', function () {
        $.jGrowl('Notification with custom style', {
            header: 'Custom style',
            theme: 'alert-styled-left alert-styled-custom alpha-teal text-teal-900'
        });
    });

    // Styled with arrow
    $('#jgrowl-styled-arrow').on('click', function () {
        $.jGrowl('Styled alert with arrow', {
            header: 'Styled with arrow',
            theme: 'alert-styled-left alert-arrow-left alert-primary'
        });
    });


    // Rounded
    $('#jgrowl-rounded').on('click', function () {
        $.jGrowl('Alert with rounded corners', {
            theme: 'bg-primary alert-rounded'
        });
    });

    // Basic style
    $('#jgrowl-alert-default').on('click', function () {
        $.jGrowl('Default alert style example', {
            header: 'Default alert style',
            theme: 'alert-bordered alert-primary'
        });
    });



    //
    // Options
    //

    // Sticky notification
    $('#jgrowl-sticky').on('click', function () {
        $.jGrowl('I am a sticky message', {
            header: 'Sticky message',
            sticky: true,
            theme: 'bg-slate-600'
        });
    });

    // Long life message
    $('#jgrowl-long-life').on('click', function () {
        $.jGrowl('A message that will live a little longer.', {
            header: 'Long life message',
            life: 10000,
            theme: 'bg-slate-600'
        });
    });

    // Callbacks
    $('#jgrowl-callbacks').on('click', function () {
        $.jGrowl('Check out your console', {
            theme: 'bg-slate-600',
            life: 5000,
            header: 'Callbacks',
            beforeOpen: function(e,m,o) {
                console.log("I am going to be opened!", this);
            },
            afterOpen: function(e,m,o) {
                console.log("I am opened!", this);
            },
            close: function(e,m,o) {
                console.log("I am closed!", this);
            }
        });
    });

    // Animation options
    $('#jgrowl-animation').on('click', function () {
        $.jGrowl('I am a notice!', {
            speed: 100,
            theme: 'bg-danger',
            header: 'Fast animation speed'
        });
    });



    //
    // Positions
    //

    // Top left
    $('#jgrowl-top-left').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'top-left',
            theme: 'bg-teal',
            header: 'Top Left position'
        });
    });

    // Top center
    $('#jgrowl-top-center').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'top-center',
            theme: 'bg-teal',
            header: 'Top Center position'
        });
    });

    // Top right
    $('#jgrowl-top-right').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'top-right',
            theme: 'bg-teal',
            header: 'Top Right position'
        });
    });


    // Center
    $('#jgrowl-center').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'center',
            theme: 'bg-danger',
            header: 'Center position'
        });
    });


    // Bottom left
    $('#jgrowl-bottom-left').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'bottom-left',
            theme: 'bg-info',
            header: 'Bottom Left position'
        });
    });

    // Bottom right
    $('#jgrowl-bottom-right').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'bottom-right',
            theme: 'bg-info',
            header: 'Bottom Right position'
        });
    });

    // Bottom center
    $('#jgrowl-bottom-center').on('click', function () {
        $('body').find('.jGrowl').attr('class', '').attr('id', '').hide();
        $.jGrowl('I am a jGrowl notice!', {
            position: 'bottom-center',
            theme: 'bg-info',
            header: 'Bottom Center position'
        });
    });
    
});
