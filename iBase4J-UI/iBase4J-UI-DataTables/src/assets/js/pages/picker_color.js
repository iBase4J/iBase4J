/* ------------------------------------------------------------------------------
*
*  # Color pickers
*
*  Specific JS code additions for picker_color.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Color palette
    var demoPalette = [
        ["#000","#444","#666","#999","#ccc","#eee","#f3f3f3","#fff"],
        ["#f00","#f90","#ff0","#0f0","#0ff","#00f","#90f","#f0f"],
        ["#f4cccc","#fce5cd","#fff2cc","#d9ead3","#d0e0e3","#cfe2f3","#d9d2e9","#ead1dc"],
        ["#ea9999","#f9cb9c","#ffe599","#b6d7a8","#a2c4c9","#9fc5e8","#b4a7d6","#d5a6bd"],
        ["#e06666","#f6b26b","#ffd966","#93c47d","#76a5af","#6fa8dc","#8e7cc3","#c27ba0"],
        ["#c00","#e69138","#f1c232","#6aa84f","#45818e","#3d85c6","#674ea7","#a64d79"],
        ["#900","#b45f06","#bf9000","#38761d","#134f5c","#0b5394","#351c75","#741b47"],
        ["#600","#783f04","#7f6000","#274e13","#0c343d","#073763","#20124d","#4c1130"]
    ]



    // Basic examples
    // ------------------------------

    // Basic setup
    $(".colorpicker-basic").spectrum();


    // Clear selection
    $(".colorpicker-clear").spectrum({
    	allowEmpty: true
    });


    // Display color formats
    $(".colorpicker-show-input").spectrum({
    	showInput: true
    });


    // Display alpha channel
    $(".colorpicker-show-alpha").spectrum({
    	showAlpha: true
    });


    // Display initial color
    $(".colorpicker-show-initial").spectrum({
    	showInitial: true
    });


    // Display input and initial color
    $(".colorpicker-input-initial").spectrum({
    	showInitial: true,
    	showInput: true
    });


    // Full featured color picker
    $(".colorpicker-full").spectrum({
    	showInitial: true,
    	showInput: true,
    	showAlpha: true,
    	allowEmpty: true
    });


    // Container color
    $(".colorpicker-container-class").spectrum({
    	containerClassName: 'bg-slate'
    });


    // Replacer color
    $(".colorpicker-replacer-class").spectrum({
    	replacerClassName: 'bg-slate',
    });


    //
    // Toggle picker state
    //

    // If disabled by default
    var isDisabled = true;

    // Toggle state on button click
    $("#toggle-disabled").click(function() {
        if (isDisabled) {
            $(".colorpicker-disabled").spectrum("enable");
        }
        else {
            $(".colorpicker-disabled").spectrum("disable");
        }
        isDisabled = !isDisabled;
        return false;
    });

    // Initialize
    $(".colorpicker-disabled").spectrum({
        disabled: true
    });



    // Flat pickers
    // ------------------------------

    // Basic setup
    $(".colorpicker-flat").spectrum({
    	flat: true
    });


    // Display input field
    $(".colorpicker-flat-input").spectrum({
    	flat: true,
    	showInput: true
    });


    // Set picker color
    $(".colorpicker-flat-custom").spectrum({
    	flat: true,
    	containerClassName: 'bg-slate'
    });


    // Display color palette
    $(".colorpicker-flat-palette").spectrum({
    	flat: true,
    	showPalette: true,
    	showPaletteOnly: true,
    	togglePaletteOnly: true,
    	togglePaletteMoreText: 'More',
        togglePaletteLessText: 'Less',
        palette: demoPalette
    });


    // Display initial color
    $(".colorpicker-flat-initial").spectrum({
    	flat: true,
    	showInitial: true
    });


    // Full featued flat picker
    $(".colorpicker-flat-full").spectrum({
    	flat: true,
    	showInitial: true,
    	showInput: true,
    	showAlpha: true,
    	allowEmpty: true
    });



    // Color palettes
    // ------------------------------

    // Display color palette
    $(".colorpicker-palette").spectrum({
    	showPalette: true,
        palette: demoPalette
    });


    // Display palette only
    $(".colorpicker-palette-only").spectrum({
    	showPalette: true,
    	showPaletteOnly: true,
        palette: demoPalette
    });


    // Toggle palette
    $(".colorpicker-palette-toggle").spectrum({
    	showPalette: true,
    	showPaletteOnly: true,
    	togglePaletteOnly: true,
    	togglePaletteMoreText: 'More',
        togglePaletteLessText: 'Less',
        palette: demoPalette
    });


    // Selection palette
    $(".colorpicker-palette-selection").spectrum({
    	showPalette: true,
        palette: [],
        localStorageKey: "spectrum.homepage"
    });


    // Limit number of selections
    $(".colorpicker-palette-limit").spectrum({
    	showPalette: true,
    	palette: [ ],
    	selectionPalette: ["red", "green", "blue"],
        maxSelectionSize: 3
    });


    // Hide after select
    $(".colorpicker-palette-hide").spectrum({
    	showPalette: true,
    	hideAfterPaletteSelect: true,
        palette: demoPalette
    });



    // Events
    // ------------------------------

    // Change event
    $(".colorpicker-event-change").spectrum({
        change: function(c) {
            var label = $("#change-result");
            label.removeClass('hidden').html('Change called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
        }
    });


    // Move event
    $(".colorpicker-event-move").spectrum({
        move: function(c) {
            var label = $("#move-result");
            label.removeClass('hidden').html('Move called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
        }
    });


    // Hide event
    $(".colorpicker-event-hide").spectrum({
        hide: function(c) {
            var label = $("#hide-result");
            label.removeClass('hidden').html('Hide called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
        }
    });


    // Show event
    $(".colorpicker-event-show").spectrum({
        show: function(c) {
            var label = $("#show-result");
            label.removeClass('hidden').html('Show called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
        }
    });


    //
    // Drag start event
    //

    // Initialize
    $(".colorpicker-event-dragstart").spectrum();

    // Attach event
    $(".colorpicker-event-dragstart").on("dragstart.spectrum", function (e, c) {
        var label = $("#dragstart-result");
        label.removeClass('hidden').html('Dragstart called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
    });


    //
    // Drag stop event
    //

    // Initialize
    $(".colorpicker-event-dragstop").spectrum();

    // Attach event
    $(".colorpicker-event-dragstop").on("dragstop.spectrum", function (e, c) {
        var label = $("#dragstop-result");
        label.removeClass('hidden').html('Dragstop called: ' + '<span class="text-semibold">' + c.toHexString() + '</span>');
    });
    
});
