/* ------------------------------------------------------------------------------
*
*  # jQuery UI Widgets
*
*  Specific JS code additions for jqueryui_components.html page
*
*  Version: 1.0
*  Latest update: Nov 12, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Buttons
    // -------------------------

    // Basic button
    $(".jui-button").button();


    // Button set
    $(".jui-button-set").buttonset();


    // Split button
    $(".jui-button-split").button().click(function() {
        alert("Running the last action");
    })
    .next()
    .button({
        text: false,
        icons: {
            primary: "icon-arrow-down22"
        }
    }).click(function() {
        var menu = $( this ).parent().next().show().position({
            my: "right top",
            at: "right bottom",
            of: this
        });

        $(document).one("click", function() {
            menu.hide();
        });
        return false;
    }).parent().buttonset().next().hide().menu();


    //
    // With icons
    //

    // Left icon
    $('.jui-button-icon-left').button({
        icons: {
            primary: "icon-twitter"
        }
    });


    // Right icon
    $('.jui-button-icon-right').button({
        icons: {
            secondary: "icon-dribbble3"
        }
    });


    // Left and right icons
    $('.jui-button-icon-both').button({
        icons: {
        primary: "icon-github4",
            secondary: "icon-arrow-down22"
        }
    });


    // Icons only
    $('.jui-button-icon-both-only').button({
        icons: {
            primary: "icon-github4",
            secondary: "icon-arrow-down22"
        },
        text: false
    });


    // Single icon only
    $('.jui-button-icon-only').button({
        icons: {
            primary: "icon-vimeo"
        },
        text: false
    });



    // Progress bars
    // -------------------------

    // Basic example
    $(".jui-progressbar").progressbar({
        value: 60
    });


    // Maximum value
    $(".jui-progressbar-max").progressbar({
        max: 1000,
        value: 400
    });


    // Indeterminate progress bar
    $(".jui-progressbar-indeterminate").progressbar({
        value: false
    }).on("create", function (event) {
        var target = $(event.target),
        progressbar = $(".jui-progressbar-indeterminate"),
        progressbarValue = progressbar.find(".ui-progressbar-value");
        progressbar.progressbar("option", "value", false);
    });


    //
    // Custom label
    //

    // Define elements
    var progressbar = $( ".jui-progressbar-custom" ),
        progressLabel = $(".ui-progress-label");

    // Initialize progress bar
    progressbar.progressbar({
        value: false,
        change: function() {
            progressLabel.text(progressbar.progressbar("value") + "%");
        },
        complete: function() {
            progressLabel.text("Complete!");
        }
    });

    // Custom progress function
    function progress() {
        var val = progressbar.progressbar("value") || 0;
        progressbar.progressbar("value", val + 2);

        if (val < 99) {
            setTimeout(progress, 80);
        }
    }

    // Update progress after 2000ms
    setTimeout(progress, 2000);



    // Dialogs
    // -------------------------

    // Basic example
    $('#jui-dialog-basic').dialog({
        autoOpen: false,
        width: 400
    });


    // With overlay
    $('#jui-dialog-overlay').dialog({
        autoOpen: false,
        modal: true,
        width: 400
    });


    // Animated
    $('#jui-dialog-animated').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        show: {
            effect: "fade",
            duration: 500
        },
        hide: {
            effect: "fade",
            duration: 500
        }
    });


    // With buttons
    $('#jui-dialog-buttons').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        buttons: {
            Save: function() {
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    // Buttons with icons
    $('#jui-dialog-buttons-icons').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        buttons: [
            {
                text: 'Submit',
                icons: {
                    primary: 'icon-checkmark5'
                },
                click: function() {
                    $(this).dialog('close');
                }
            },
            {
                text: 'Cancel',
                icons: {
                    primary: 'icon-cross3'
                },
                click: function() {
                    $(this).dialog('close');
                }
            }
        ]
    });


    // Disable resize
    $('#jui-dialog-resize').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        resizable: false
    });


    // Disable close on escape
    $('#jui-dialog-close-escape').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        closeOnEscape: false
    });


    // Disable drag
    $('#jui-dialog-drag-disabled').dialog({
        autoOpen: false,
        modal: true,
        width: 400,
        draggable: false
    });


    // Append to element
    $('#jui-dialog-append').dialog({
        appendTo: '#modal-append-target',
        autoOpen: false,
        modal: true,
        width: 400
    });


    //
    // With forms
    //

    // Vertical form dialog
    $('#jui-dialog-form-vertical').dialog({
        autoOpen: false,
        modal: true,
        width: 500,
        buttons: {
            Submit: function() {
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    // Horizontal form dialog
    $('#jui-dialog-form-horizontal').dialog({
        autoOpen: false,
        modal: true,
        width: 500,
        buttons: {
            Submit: function() {
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    // Inline form dialog
    $('#jui-dialog-form-inline').dialog({
        autoOpen: false,
        modal: true,
        width: 555,
        buttons: {
            Submit: function() {
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });


    //
    // Optional widths
    //

    // Default width
    $('#jui-dialog-width-default').dialog({
        autoOpen: false,
        modal: true
    });


    // Pixel width
    $('#jui-dialog-width-pixel').dialog({
        autoOpen: false,
        modal: true,
        width: 400
    });


    // Percentage width
    $('#jui-dialog-width-percentage').dialog({
        autoOpen: false,
        modal: true,
        width: '50%'
    });


    // Full width
    $('#jui-dialog-width-full').dialog({
        autoOpen: false,
        modal: true,
        width: '96%'
    });


    //
    // Dialog openers
    //


    $('#jui-dialog-basic-opener').click(function() {
        $('#jui-dialog-basic').dialog('open');
    });

    $('#jui-dialog-overlay-opener').click(function() {
        $('#jui-dialog-overlay').dialog('open');
    });

    $('#jui-dialog-animated-opener').click(function() {
        $('#jui-dialog-animated').dialog('open');
    });

    $('#jui-dialog-buttons-opener').click(function() {
        $('#jui-dialog-buttons').dialog('open');
    });

    $('#jui-dialog-buttons-icons-opener').click(function() {
        $('#jui-dialog-buttons-icons').dialog('open');
    });

    $('#jui-dialog-resize-opener').click(function() {
        $('#jui-dialog-resize').dialog('open');
    });

    $('#jui-dialog-close-escape-opener').click(function() {
        $('#jui-dialog-close-escape').dialog('open');
    });

    $('#jui-dialog-drag-disabled-opener').click(function() {
        $('#jui-dialog-drag-disabled').dialog('open');
    });

    $('#jui-dialog-append-opener').click(function() {
        $('#jui-dialog-append').dialog('open');
    });


    $('#jui-dialog-form-vertical-opener').click(function() {
        $('#jui-dialog-form-vertical').dialog('open');
    });

    $('#jui-dialog-form-horizontal-opener').click(function() {
        $('#jui-dialog-form-horizontal').dialog('open');
    });

    $('#jui-dialog-form-inline-opener').click(function() {
        $('#jui-dialog-form-inline').dialog('open');
    });


    $('#jui-dialog-width-default-opener').click(function() {
        $('#jui-dialog-width-default').dialog('open');
    });

    $('#jui-dialog-width-pixel-opener').click(function() {
        $('#jui-dialog-width-pixel').dialog('open');
    });

    $('#jui-dialog-width-percentage-opener').click(function() {
        $('#jui-dialog-width-percentage').dialog('open');
    });

    $('#jui-dialog-width-full-opener').click(function() {
        $('#jui-dialog-width-full').dialog('open');
    });

});
