/* ------------------------------------------------------------------------------
*
*  # PNotify notifications
*
*  Specific JS code additions for components_notifications_pnotify.html page
*
*  Version: 1.1
*  Latest update: Feb 1, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Popup notifications
    // ------------------------------

    //
    // Notification styles
    //

    // Default style
    $('#pnotify-default').on('click', function () {
        new PNotify({
            title: 'Primary notice',
            text: 'Check me out! I\'m a notice.',
            icon: 'icon-warning22'
        });
    });


    // Styled left
    $('#pnotify-styled-left').on('click', function () {
        new PNotify({
            title: 'Left icon',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert alert-styled-left',
            type: 'info'
        });
    });

    // Styled right
    $('#pnotify-styled-right').on('click', function () {
        new PNotify({
            title: 'Right icon',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert alert-warning alert-styled-right',
            type: 'error'
        });
    });

    // Styled with arrow
    $('#pnotify-styled-arrow').on('click', function () {
        new PNotify({
            title: 'Notice with arrow',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert alert-styled-left alert-arrow-left',
            type: 'info'
        });
    });

    // Custom style
    $('#pnotify-custom-styled').on('click', function () {
        new PNotify({
            title: 'Custom color notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert alert-styled-left alert-styled-custom alert-arrow-left alpha-teal text-teal-800'
        });
    });


    //
    // Contextual alternatives
    //

    // Danger notification
    $('#pnotify-danger').on('click', function () {
        new PNotify({
            title: 'Danger notice',
            text: 'Check me out! I\'m a notice.',
            icon: 'icon-blocked',
            type: 'error'
        });
    });

    // Success notification
    $('#pnotify-success').on('click', function () {
        new PNotify({
            title: 'Success notice',
            text: 'Check me out! I\'m a notice.',
            icon: 'icon-checkmark3',
            type: 'success'
        });
    });

    // Info notification
    $('#pnotify-info').on('click', function () {
        new PNotify({
            title: 'Info notice',
            text: 'Check me out! I\'m a notice.',
            icon: 'icon-info22',
            type: 'info'
        });
    });


    //
    // Solid color style
    //

    // Solid primary
    $('#pnotify-solid-primary').on('click', function () {
        new PNotify({
            title: 'Primary notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-primary'
        });
    });

    // Solid danger
    $('#pnotify-solid-danger').on('click', function () {
        new PNotify({
            title: 'Danger notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-danger'
        });
    });

    // Solid success
    $('#pnotify-solid-success').on('click', function () {
        new PNotify({
            title: 'Success notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-success'
        });
    });

    // Solid warning
    $('#pnotify-solid-warning').on('click', function () {
        new PNotify({
            title: 'Warning notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-warning'
        });
    });

    // Solid info
    $('#pnotify-solid-info').on('click', function () {
        new PNotify({
            title: 'Info notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-info'
        });
    });


    // Custom solid color
    $('#pnotify-solid-custom').on('click', function () {
        new PNotify({
            title: 'Custom color notice',
            text: 'Check me out! I\'m a notice.',
            addclass: 'bg-teal'
        });
    });

    // Solid styled left
    $('#pnotify-solid-styled-left').on('click', function () {
        new PNotify({
            title: 'Left icon',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert bg-primary alert-styled-left'
        });
    });

    // Solid styled right
    $('#pnotify-solid-styled-right').on('click', function () {
        new PNotify({
            title: 'Right icon',
            text: 'Check me out! I\'m a notice.',
            addclass: 'alert bg-danger alert-styled-right',
            type: 'error'
        });
    });



    // Desktop notifications
    // ------------------------------

    // Default
    $('#pnotify-desktop-default').on('click', function () {
        PNotify.desktop.permission();
        (new PNotify({
            title: 'Default Desktop Notice',
            text: 'If you\'ve given me permission, I\'ll appear as a desktop notification.',
            desktop: {
                desktop: true,
                addclass: 'bg-green',
                icon: 'assets/images/pnotify/default.png'
            }
        })
        ).get().click(function(e) {
            if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
            alert('Hey! You clicked the desktop notification!');
        });
    });

    // Danger
    $('#pnotify-desktop-danger').on('click', function () {
        PNotify.desktop.permission();
        (new PNotify({
            title: 'Danger Desktop Notice',
            type: 'danger',
            text: 'I\'m a danger desktop notification, if you have given me a permission.',
            desktop: {
                desktop: true,
                icon: 'assets/images/pnotify/danger.png'
            }
        })
        ).get().click(function(e) {
            if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
            alert('Hey! You clicked the desktop notification!');
        });
    });

    // Success
    $('#pnotify-desktop-success').on('click', function () {
        PNotify.desktop.permission();
        (new PNotify({
            title: 'Success Desktop Notice',
            type: 'success',
            text: 'I\'m a success desktop notification, if you have given me a permission.',
            desktop: {
                desktop: true,
                icon: 'assets/images/pnotify/success.png'
            }
        })
        ).get().click(function(e) {
            if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
            alert('Hey! You clicked the desktop notification!');
        });
    });

    // Warning
    $('#pnotify-desktop-warning').on('click', function () {
        PNotify.desktop.permission();
        (new PNotify({
            title: 'Warning Desktop Notice',
            type: 'warning',
            text: 'I\'m a warning desktop notification, if you have given me a permission.',
            desktop: {
                desktop: true,
                icon: 'assets/images/pnotify/warning.png'
            }
        })
        ).get().click(function(e) {
            if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
            alert('Hey! You clicked the desktop notification!');
        });
    });

    // Info
    $('#pnotify-desktop-info').on('click', function () {
        PNotify.desktop.permission();
        (new PNotify({
            title: 'Info Desktop Notice',
            type: 'info',
            text: 'I\'m an info desktop notification, if you have given me a permission.',
            desktop: {
                desktop: true,
                icon: 'assets/images/pnotify/info.png'
            }
        })
        ).get().click(function(e) {
            if ($('.ui-pnotify-closer, .ui-pnotify-sticker, .ui-pnotify-closer *, .ui-pnotify-sticker *').is(e.target)) return;
            alert('Hey! You clicked the desktop notification!');
        });
    });



    // Options
    // ------------------------------

    // No title
    $('#pnotify-no-title').on('click', function () {
        new PNotify({
            text: 'Check me out! I\'m a notice without title.',
            addclass: 'bg-primary'
        });
    });

    // Rich content
    $('#pnotify-rich').on('click', function () {
        new PNotify({
            title: 'Rich content notice',
            text: 'Look at my beautiful <strong>strong</strong>, <a href="#" class="alert-link">linked</a>, <em>emphasized</em>, and <u>underlined</u> text with <i class="icon-make-group"></i> icon.',
            icon: 'icon-comment-discussion'
        });
    });

    // Close on click
    $('#pnotify-click').on('click', function () {
        var notice = new PNotify({
            title: 'Close on click',
            text: 'Click me anywhere to dismiss me.',
            addclass: 'bg-warning',
            hide: false,
            buttons: {
                closer: false,
                sticker: false
            }
        });
        notice.get().click(function() {
            notice.remove();
        });  
    });

    // Form
    $('#pnotify-form').on('click', function () {
        var notice = new PNotify({
            text: $('#form_notice').html(),
            width: '300px',
            hide: false,
            addclass: 'bg-slate',
            buttons: {
                closer: false,
                sticker: false
            },
            insert_brs: false
        });

        // Remove if cancelled
        notice.get().find('button[name=cancel]').on('click', function() {
            notice.remove();
        })

        // Submit form
        notice.get().submit(function() {
            var username = $(this).find('input[name=username]').val();
            if (!username) {
                alert('Please provide a username.');
                return false;
            }
            notice.update({
                title: 'Welcome',
                text: 'Successfully logged in as ' + username,
                addclass: 'bg-teal',
                icon: true,
                width: PNotify.prototype.options.width,
                hide: true,
                buttons: {
                    closer: true,
                    sticker: true
                }
            });
            return false;
        });
    });

    // Sticky notice
    $('#pnotify-sticky').on('click', function () {
        new PNotify({
            title: 'Sticky notice',
            text: 'Check me out! I\'m a sticky notice. You\'ll have to close me yourself.',
            addclass: 'bg-primary',
            hide: false
        });
    });

    // Sticky buttons
    $('#pnotify-sticky-buttons').on('click', function () {
        new PNotify({
            title: 'No sticky button notice',
            text: 'I\'m a sticky notice with no unsticky button. You\'ll have to close me yourself.',
            addclass: 'bg-primary',
            hide: false,
            buttons: {
                sticker: false
            }
        });
    });

    // Permanent buttons
    $('#pnotify-permanent-buttons').on('click', function () {
        new PNotify({
            title: 'Permanent buttons notice',
            text: 'My buttons are really lonely, so they\'re gonna hang out with us.',
            addclass: 'bg-slate',
            buttons: {
                closer_hover: false,
                sticker_hover: false
            }
        });
    });



    // Modules
    // ------------------------------

    // Confirm
    $('#pnotify-confirm').on('click', function () {

        // Setup
        var notice = new PNotify({
            title: 'Confirmation',
            text: '<p>Are you sure you want to discard changes?</p>',
            hide: false,
            type: 'warning',
            confirm: {
                confirm: true,
                buttons: [
                    {
                        text: 'Yes',
                        addClass: 'btn btn-sm btn-primary'
                    },
                    {
                        addClass: 'btn btn-sm btn-link'
                    }
                ]
            },
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            }
        })

        // On confirm
        notice.get().on('pnotify.confirm', function() {
            alert('Ok, cool.');
        })

        // On cancel
        notice.get().on('pnotify.cancel', function() {
            alert('Oh ok. Chicken, I see.');
        });
    });


    // Prompt
    $('#pnotify-prompt').on('click', function () {

        // Setup
        var notice = new PNotify({
            title: 'Input needed',
            text: 'What side would you like?',
            hide: false,
            confirm: {
                prompt: true,
                buttons: [
                    {
                        text: 'Yes',
                        addClass: 'btn btn-sm btn-primary'
                    },
                    {
                        addClass: 'btn btn-sm btn-link'
                    }
                ]
            },
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            }
        });

        // On confirm
        notice.get().on('pnotify.confirm', function(e, notice, val) {
            notice.cancelRemove().update({
                title: 'You\'ve chosen a side',
                text: 'You want ' + $('<div/>').text(val).html() + '.',
                icon: 'icon-checkmark3',
                type: 'success',
                delay: 2000,
                hide: true,
                confirm: {
                    prompt: false
                },
                buttons: {
                    closer: true,
                    sticker: true
                }
            });
        })

        // On cancel
        notice.get().on('pnotify.cancel', function(e, notice) {
            notice.cancelRemove().update({
                title: 'You don\'t want a side',
                text: 'No soup for you!',
                icon: 'icon-blocked',
                type: 'error',
                delay: 2000,
                hide: true,
                confirm: {
                    prompt: false
                },
                buttons: {
                    closer: true,
                    sticker: true
                }
            });
        });
    });


    // Multiline prompt
    $('#pnotify-multiline').on('click', function () {

        // Setup
        var notice = new PNotify({
            title: 'Input needed',
            text: 'Write me a poem, please.',
            hide: false,
            confirm: {
                prompt: true,
                prompt_multi_line: true,
                prompt_default: 'Roses are red,\nViolets are blue,\n',
                buttons: [
                    {
                        text: 'Yes',
                        addClass: 'btn btn-sm btn-primary'
                    },
                    {
                        addClass: 'btn btn-sm btn-link'
                    }
                ]
            },
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            }
        });

        // Confirm
        notice.get().on('pnotify.confirm', function(e, notice, val) {
            notice.cancelRemove().update({
                title: 'Your poem',
                text: $('<div/>').text(val).html(),
                icon: 'icon-checkmark3',
                type: 'success',
                hide: true,
                confirm: {
                    prompt: false
                },
                buttons: {
                    closer: true,
                    sticker: true
                }
            });
        });

        // On cancel
        notice.get().on('pnotify.cancel', function(e, notice) {
            notice.cancelRemove().update({
                title: 'You don\'t like poetry',
                text: 'Roses are dead,\nViolets are dead,\nI suck at gardening.',
                icon: 'icon-blocked',
                type: 'error',
                hide: true,
                confirm: {
                    prompt: false
                },
                buttons: {
                    closer: true,
                    sticker: true
                }
            });
        });
    });


    // Custom buttons
    $('#pnotify-buttons').on('click', function () {
        new PNotify({
            title: 'Choose a side',
            text: 'You have three options to choose from.',
            hide: false,
            width: 420,
            confirm: {
                confirm: true,
                buttons: [
                    {
                        text: 'Fries',
                        addClass: 'btn btn-sm bg-blue',
                        click: function(notice) {
                            notice.update({
                                title: 'You\'ve chosen a side',
                                text: 'You want fries.',
                                icon: 'icon-checkmark3',
                                type: 'success',
                                hide: true,
                                confirm: {
                                    confirm: false
                                },
                                buttons: {
                                    closer: true,
                                    sticker: true
                                }
                            });
                        }
                    },
                    {
                        text: 'Mashed Potatoes',
                        addClass: 'btn btn-sm bg-pink-400',
                        click: function(notice) {
                            notice.update({
                                title: 'You\'ve chosen a side',
                                text: 'You want mashed potatoes.',
                                icon: 'icon-checkmark3',
                                type: 'info',
                                hide: true,
                                confirm: {
                                    confirm: false
                                },
                                buttons: {
                                    closer: true,
                                    sticker: true
                                }
                            });
                        }
                    },
                    {
                        text: 'Fruit',
                        addClass: 'btn btn-sm bg-indigo-400',
                        click: function(notice) {
                            notice.update({
                                title: 'You\'ve chosen a side',
                                text: 'You want fruit.',
                                icon: 'icon-checkmark3',
                                type: 'info',
                                hide: true,
                                confirm: {
                                    confirm: false
                                },
                                buttons: {
                                    closer: true,
                                    sticker: true
                                }
                            });
                        }
                    }
                ]
            },
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            }
        });
    });


    // Permanotice
    $('#pnotify-permanotice').on('click', function () {
        var permanotice;
        if (permanotice) {
            permanotice.open();
        }
        else {
            permanotice = new PNotify({
                title: 'Now look here',
                text: 'There\'s something you need to know, and I won\'t go away until you come to grips with it.',
                addclass: 'bg-danger',
                hide: false,
                buttons: {
                    closer: false,
                    sticker: false
                }
            });
        }
    });


    // Callbacks
    $('#pnotify-callbacks').on('click', function () {
        var dont_alert = function() {};
        new PNotify({
            title: 'I\'m here',
            text: 'I have a callback for each of my events. I\'ll call all my callbacks while I change states.',
            addclass: 'bg-teal',
            before_init: function(opts) {
                dont_alert('I\'m called before the notice initializes. I\'m passed the options used to make the notice. I can modify them. Watch me replace the word callback with tire iron!');
                opts.text = opts.text.replace(/callback/g, 'tire iron');
            },
            after_init: function(PNotify) {
                dont_alert('I\'m called after the notice initializes. I\'m passed the PNotify object for the current notice: ' + PNotify + '\n\nThere are more callbacks you can assign, but this is the last notice you\'ll see. Check the code to see them all.');
            },
            before_open: function(PNotify) {
                var source_note = 'Return false to cancel opening.';
                dont_alert('I\'m called before the notice opens. I\'m passed the PNotify object for the current notice: ' + PNotify);
            },
            after_open: function(PNotify) {
                alert('I\'m called after the notice opens. I\'m passed the PNotify object for the current notice: ' + PNotify);
            },
            before_close: function(PNotify, timer_hide) {
                var source_note = 'Return false to cancel close. Use PNotify.queueRemove() to set the removal timer again.';
                dont_alert('I\'m called before the notice closes. I\'m passed the PNotify object for the current notice: ' + PNotify);
                dont_alert('I also have an argument called timer_hide, which is true if the notice was closed because the timer ran down. Value: ' + timer_hide);
            },
            after_close: function(PNotify, timer_hide) {
                alert('I\'m called after the notice closes. I\'m passed the PNotify object for the current notice: ' + PNotify);
                dont_alert('I also have an argument called timer_hide, which is true if the notice was closed because the timer ran down. Value: ' + timer_hide);
            }
        });
    });


    // Switching notices
    $('#pnotify-switching').on('click', function () {
        new PNotify({
            title: 'Notice',
            text: 'Right now I\'m a notice.',
            addclass: 'alert bg-primary alert-styled-right',
            before_close: function(PNotify) {
                PNotify.update({
                    title: 'Error',
                    text: 'Uh oh. Now I\'ve become an error.',
                    addclass: 'alert bg-danger alert-styled-right',
                    type: 'error',
                    before_close: function(PNotify) {
                        PNotify.update({
                            title: 'Success',
                            text: 'I fixed the error!',
                            addclass: 'alert bg-success alert-styled-right',
                            type: 'success',
                            before_close: function(PNotify) {
                                PNotify.update({
                                    title: 'Info',
                                    text: 'Everything\'s cool now.',
                                    addclass: 'alert bg-info alert-styled-right',
                                    type: 'info',
                                    before_close: null
                                });
                                PNotify.queueRemove();
                                return false;
                            }
                        });
                        PNotify.queueRemove();
                        return false;
                    }
                });
                PNotify.queueRemove();
                return false;
            }
        });
    });


    // Progress loader
    $('#pnotify-progress').on('click', function () {
        var cur_value = 1,
        progress;

        // Make a loader.
        var loader = new PNotify({
            title: "Creating series of tubes",
            text: '<div class="progress progress-striped active" style="margin:0">\
            <div class="progress-bar bg-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0">\
            <span class="sr-only">0%</span>\
            </div>\
            </div>',
            addclass: 'bg-primary',
            icon: 'icon-spinner4 spinner',
            hide: false,
            buttons: {
                closer: false,
                sticker: false
            },
            history: {
                history: false
            },
            before_open: function(PNotify) {
                progress = PNotify.get().find("div.progress-bar");
                progress.width(cur_value + "%").attr("aria-valuenow", cur_value).find("span").html(cur_value + "%");

                // Pretend to do something.
                var timer = setInterval(function() {
                    if (cur_value >= 100) {

                        // Remove the interval.
                        window.clearInterval(timer);
                        loader.remove();
                        return;
                    }
                    cur_value += 1;
                    progress.width(cur_value + "%").attr("aria-valuenow", cur_value).find("span").html(cur_value + "%");
                }, 65);
            }
        });
    });


    // Dynamic loader
    $('#pnotify-dynamic').on('click', function () {
        var percent = 0;
        var notice = new PNotify({
            text: "Please wait",
            addclass: 'bg-primary',
            type: 'info',
            icon: 'icon-spinner4 spinner',
            hide: false,
            buttons: {
                closer: false,
                sticker: false
            },
            opacity: .9,
            width: "170px"
        });

        setTimeout(function() {
        notice.update({
            title: false
        });
        var interval = setInterval(function() {
            percent += 2;
            var options = {
                text: percent + "% complete."
            };
            if (percent == 80) options.title = "Almost There";
            if (percent >= 100) {
                window.clearInterval(interval);
                options.title = "Done!";
                options.addclass = "bg-success";
                options.type = "success";
                options.hide = true;
                options.buttons = {
                    closer: true,
                    sticker: true
                };
                options.icon = 'icon-checkmark3';
                options.opacity = 1;
                options.width = PNotify.prototype.options.width;
            }
            notice.update(options);
            }, 120);
        }, 2000);
    });



    // Stacks
    // ------------------------------

    // Define directions
    var stack_top_left = {"dir1": "down", "dir2": "right", "push": "top"};
    var stack_bottom_left = {"dir1": "right", "dir2": "up", "push": "top"};
    var stack_bottom_right = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};
    var stack_custom_left = {"dir1": "right", "dir2": "down"};
    var stack_custom_right = {"dir1": "left", "dir2": "up", "push": "top"};
    var stack_custom_top = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 1};
    var stack_custom_bottom = {"dir1": "up", "dir2": "right", "spacing1": 1};


    //
    // Setup options for positions
    //

    // Top left
    function show_stack_top_left(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            addclass: "stack-top-left bg-primary",
            stack: stack_top_left
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-top-left bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-top-left bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-top-left bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Bottom left
    function show_stack_bottom_left(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            addclass: "stack-bottom-left bg-primary",
            stack: stack_bottom_left
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-bottom-left bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-bottom-left bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-bottom-left bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Bottom right
    function show_stack_bottom_right(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            addclass: "stack-bottom-right bg-primary",
            stack: stack_bottom_right
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-bottom-right bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-bottom-right bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-bottom-right bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Custom left position
    function show_stack_custom_left(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            addclass: "stack-custom-left bg-primary alert-styled-right",
            stack: stack_custom_left
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-custom-left bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-custom-left bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-custom-left bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Custom right position
    function show_stack_custom_right(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            addclass: "stack-custom-right bg-primary",
            stack: stack_custom_right
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-custom-right bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-custom-right bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-custom-right bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Custom top position
    function show_stack_custom_top(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            width: "100%",
            cornerclass: "no-border-radius",
            addclass: "stack-custom-top bg-primary",
            stack: stack_custom_top
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-custom-top bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-custom-top bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-custom-top bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }

    // Custom bottom position
    function show_stack_custom_bottom(type) {
        var opts = {
            title: "Over here",
            text: "Check me out. I'm in a different stack.",
            width: "100%",
            cornerclass: "no-border-radius",
            addclass: "stack-custom-bottom bg-primary",
            stack: stack_custom_bottom
        };
        switch (type) {
            case 'error':
            opts.title = "Oh No";
            opts.text = "Watch out for that water tower!";
            opts.addclass = "stack-custom-bottom bg-danger";
            opts.type = "error";
            break;

            case 'info':
            opts.title = "Breaking News";
            opts.text = "Have you met Ted?";
            opts.addclass = "stack-custom-bottom bg-info";
            opts.type = "info";
            break;

            case 'success':
            opts.title = "Good News Everyone";
            opts.text = "I've invented a device that bites shiny metal asses.";
            opts.addclass = "stack-custom-bottom bg-success";
            opts.type = "success";
            break;
        }
        new PNotify(opts);
    }


    //
    // Initialize
    //

    // Top left
    $('#pnotify-stack-top-left').on('click', function () {
        show_stack_top_left('primary');
    });

    // Bottom left
    $('#pnotify-stack-bottom-left').on('click', function () {
        show_stack_bottom_left('primary');
    });

    // Bottom right
    $('#pnotify-stack-bottom-right').on('click', function () {
        show_stack_bottom_right('danger');
    });

    // Custom left
    $('#pnotify-stack-custom-left').on('click', function () {
        show_stack_custom_left('success');
    });

    // Custom right
    $('#pnotify-stack-custom-right').on('click', function () {
        show_stack_custom_right('success');
    });

    // Custom top
    $('#pnotify-stack-custom-top').on('click', function () {
        show_stack_custom_top('info');
    });

    // Custom bottom
    $('#pnotify-stack-custom-bottom').on('click', function () {
        show_stack_custom_bottom('info');
    });
    
});
