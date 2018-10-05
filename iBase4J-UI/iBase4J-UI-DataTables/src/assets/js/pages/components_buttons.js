/* ------------------------------------------------------------------------------
*
*  # Buttons and button dropdowns
*
*  Specific JS code additions for components_buttons.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Buttons with progress/spinner
    // ------------------------------

    // Initialize on button click
    $('.btn-loading').click(function () {
        var btn = $(this);
        btn.button('loading')
        setTimeout(function () {
            btn.button('reset')
        }, 3000)
    });

    // Button with spinner
    Ladda.bind('.btn-ladda-spinner', {
        dataSpinnerSize: 16,
        timeout: 2000
    });

    // Button with progress
    Ladda.bind('.btn-ladda-progress', {
        callback: function(instance) {
            var progress = 0;
            var interval = setInterval(function() {
                progress = Math.min(progress + Math.random() * 0.1, 1);
                instance.setProgress(progress);

                if( progress === 1 ) {
                    instance.stop();
                    clearInterval(interval);
                }
            }, 200);
        }
    });



    // Animated dropdowns
    // ------------------------------

    // CSS3 animations
    $('.dropdown-animated, .btn-group-animated').on('show.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').addClass('animated bounceIn').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            $(this).removeClass('animated bounceIn')
        });
    });


    //
    // Velocity animations
    //

    // Open
    $('.dropdown-velocity, .btn-group-velocity').on('show.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').velocity('transition.slideUpIn', {
            duration: 200
        })
    });

    // Close
    $('.dropdown-velocity, .btn-group-velocity').on('hide.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').velocity('transition.slideLeftOut', {
            duration: 200,
            complete: function() {
                $(this).removeAttr('style');
            }
        })
    });


    //
    // jQuery animations
    //

    // Open
    $('.dropdown-fade, .btn-group-fade').on('show.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').fadeIn(250);
    });

    // Close
    $('.dropdown-fade, .btn-group-fade').on('hide.bs.dropdown', function(e){
        $(this).find('.dropdown-menu').fadeOut(250);
    });

});
