/* ------------------------------------------------------------------------------
*
*  # BlockUI extension
*
*  Specific JS code additions for extension_blockui.html page
*
*  Version: 1.1
*  Latest update: Feb 5, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Page elements
    // ------------------------------

    // Block panel
    $('#block-panel').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Block sidebar
    $('#block-sidebar').on('click', function() {
        var block = $('.sidebar');
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#1b2024',
                opacity: 0.6,
                cursor: 'wait'
            },
            centerY: 0, 
            css: {
            	top: '180px',
                border: 0,
                color: '#fff',
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Block page
    $('#block-page').on('click', function() {
        $.blockUI({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#1b2024',
                opacity: 0.8,
                zIndex: 1200,
                cursor: 'wait'
            },
            css: {
                border: 0,
                color: '#fff',
                padding: 0,
                zIndex: 1201,
                backgroundColor: 'transparent'
            }
        });
    });



    // Overlays
    // ------------------------------

    // Basic overlay
    $('#basic-overlay').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Custom overlay
    $('#overlay-custom').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#0E8F92',
                opacity: 0.9,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                color: '#fff',
                backgroundColor: 'transparent'
            }
        });
    });


    // Overlay with custom color
    $('#overlay-cover').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#3F9EC3',
                opacity: 1,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                color: '#fff',
                backgroundColor: 'transparent'
            }
        });
    });


    // Overlay without text
    $('#no-message').on('click', function() {
        var block = $(this).parent();
        $(block).block({
        	message: null,
        	timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // No overlay
    $('#no-overlay').on('click', function() {
        var block = $(this).parent();
        $(block).block({
        	message: '<i class="icon-spinner4 spinner"></i>',
        	showOverlay: false,
        	timeout: 2000, //unblock after 2 seconds
            css: {
                border: 0,
                padding: 15,
                backgroundColor: '#283133',
                color: '#fff',
                width: 46,
                height: 46,
                lineHeight: 1,
                '-webkit-border-radius': '2px', 
                '-moz-border-radius': '2px', 
                opacity: 0.95
            }
        });
    });



    // Messages
    // ------------------------------

    // Default message
    $('#default-message').on('click', function() {
        var block = $(this).parent();
        $(block).block({
            message: '<span class="text-semibold">Please wait...</span>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Spinner only
    $('#spinner-only').on('click', function() {
        var block = $(this).parent();
        $(block).block({
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Custom message
    $('#custom-message').on('click', function() {
        var block = $(this).parent();
        $(block).block({
        	message: '<span class="text-semibold"><i class="icon-spinner4 spinner position-left"></i>&nbsp; Updating data</span>',
        	timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: '10px 15px',
                color: '#fff',
                width: 'auto',
                '-webkit-border-radius': 2,
                '-moz-border-radius': 2,
                backgroundColor: '#333'
            }
        });
    });


    // DOM message
    $('#dom-message').on('click', function() {
        var block = $(this).parent();
        $(block).block({
        	message: $(this).next('.blockui-message'),
        	timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
            	width: 'auto',
                '-webkit-border-radius': 2,
                '-moz-border-radius': 2,
            	padding: 0,
                border: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Multiple messages
    $('#multiple-messages').on('click', function() {
        var message = $(this).next('.blockui-message');
        var block = $(this).parent();
        $(block).block({ 
            message: message,
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
            	width: 100,
                '-webkit-border-radius': 2,
                '-moz-border-radius': 2,
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            },
            onBlock: function(){
           		clearTimeout();
           	}
        });

        window.setTimeout(function () {
           message.html('<i class="icon-spinner4 spinner"></i> <span class="text-semibold display-block">Loading</span>')
        }, 0); 

        window.setTimeout(function () {
           message.html('<i class="icon-spinner4 spinner"></i> <span class="text-semibold display-block">Please wait</span>')
        }, 2000); 

        window.setTimeout(function () {
           message.addClass('bg-danger').html('<i class="icon-warning"></i> <span class="text-semibold display-block">Load error</span>')
        }, 4000);

        window.setTimeout(function () {
           $(block).unblock({
           	onUnblock: function(){
           		message.removeClass('bg-danger');
           	}
           });
        }, 6000);
    });


    // Custom message animation
    $('#custom-message-animation').on('click', function() {
        var block = $(this).parent();
        $(block).block({
        	message: $('.blockui-animation-container'),
        	timeout: 3000, //unblock after 3 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
            	width: 36,
            	height: 36,
            	color: '#fff',
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });

        var animation = $(this).data("animation");
        $('.blockui-animation-container').addClass("animated " + animation).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function () {
            $(this).removeClass("animated " + animation);
        });
    });


    // Custom message position
    $('#custom-position').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            centerX: 0,
            centerY: 0,
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                width: 16,
                top: '15px',
                left: '',
                right: '15px',
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });



    // Unblock options
    // ------------------------------

    // Auto unblock
    $('#auto-unblock').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });
    });


    // Unblock on click
    $('#click-unblock').on('click', function() {
        var block = $(this).parent();
        $(block).block({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait'
            },
            css: {
            	width: 16,
                border: 0,
                padding: 0,
                backgroundColor: 'transparent'
            }
        });

        $('.blockOverlay').on('click', function() {
        	$(block).unblock();
        });
    });



    // Callbacks
    // ------------------------------

    // Block callback
    $('#block-callback').on('click', function() {
        $.blockUI({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            fadeIn: 800, 
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#1b2024',
                opacity: 0.8,
                zIndex: 1200,
                cursor: 'wait'
            },
            css: {
                border: 0,
                color: '#fff',
                zIndex: 1201,
                padding: 0,
                backgroundColor: 'transparent'
            },
            onBlock: function() { 
                alert('Page is now blocked. FadeIn completed.'); 
            } 
        });
    });


    // Unblock callback
    $('#unblock-callback').on('click', function() {
        $.blockUI({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            timeout: 2000, //unblock after 2 seconds
            overlayCSS: {
                backgroundColor: '#1b2024',
                opacity: 0.8,
                zIndex: 1200,
                cursor: 'wait'
            },
            css: {
                border: 0,
                color: '#fff',
                padding: 0,
                zIndex: 1201,
                backgroundColor: 'transparent'
            },
            onUnblock: function() { 
                alert('Page is now unblocked. FadeOut completed.'); 
            } 
        });
    });


    // Overlay callback
    $('#overlay-callback').on('click', function() {
        $.blockUI({ 
            message: '<i class="icon-spinner4 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#1b2024',
                opacity: 0.8,
                zIndex: 1200,
                cursor: 'wait'
            },
            css: {
                border: 0,
                color: '#fff',
                padding: 0,
                zIndex: 1201,
                backgroundColor: 'transparent'
            },
            onOverlayClick: $.unblockUI
        });
    });



    // Other options
    // ------------------------------

    // Growl notification
    $('#growl').on('click', function() {
        $.blockUI({ 
            message: $('.blockui-growl'), 
            fadeIn: 700, 
            fadeOut: 700, 
            timeout: 3000000, //unblock after 3 seconds
            showOverlay: false, 
            centerY: false, 
            css: { 
                width: '250px',
                backgroundColor: 'transparent',
                top: '20px',
                left: 'auto',
                right: '20px',
                border: 0,
                opacity: .95,
                zIndex: 1200,
            } 
        }); 
    });

});
