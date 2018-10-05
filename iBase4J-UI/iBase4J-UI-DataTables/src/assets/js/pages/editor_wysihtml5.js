/* ------------------------------------------------------------------------------
*
*  # WYSIHTML5 editor
*
*  Specific JS code additions for editor_wysihtml5.html page
*
*  Version: 1.1
*  Latest update: Jun 8, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Default initialization
    $('.wysihtml5-default').wysihtml5({
        parserRules:  wysihtml5ParserRules,
        stylesheets: ["assets/css/components.css"]
    });


    // Simple toolbar
    $('.wysihtml5-min').wysihtml5({
        parserRules:  wysihtml5ParserRules,
        stylesheets: ["assets/css/components.css"],
        "font-styles": true, // Font styling, e.g. h1, h2, etc. Default true
        "emphasis": true, // Italics, bold, etc. Default true
        "lists": true, // (Un)ordered lists, e.g. Bullets, Numbers. Default true
        "html": false, // Button which allows you to edit the generated HTML. Default false
        "link": true, // Button to insert a link. Default true
        "image": false, // Button to insert an image. Default true,
        "action": false, // Undo / Redo buttons,
        "color": true // Button to change color of font
    });


    // Editor events
    $('.wysihtml5-init').on('click', function() {
        $(this).off('click').addClass('disabled');
        $('.wysihtml5-events').wysihtml5({
            parserRules:  wysihtml5ParserRules,
            stylesheets: ["assets/css/components.css"],
            events: {
                load: function() { 
                    $.jGrowl('Editor has been loaded.', { theme: 'bg-slate-700', header: 'WYSIHTML5 loaded' });
                },
                change_view: function() {
                    $.jGrowl('Editor view mode has been changed.', { theme: 'bg-slate-700', header: 'View mode' });
                }
            }
        });
    });


    // Style form components
    $('.styled').uniform();

});
