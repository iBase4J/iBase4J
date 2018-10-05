/* ------------------------------------------------------------------------------
*
*  # jQuery UI sliders
*
*  Specific JS code additions for jqueryui_sliders.html page
*
*  Version: 1.0
*  Latest update: Nov 12, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // jQuery UI sliders
    // ------------------------------

    //
    // Horizontal sliders
    //

    // Basic slider
    $(".ui-slider").slider();


    // Custom start value
    $(".ui-slider-value").slider({
        value: 20,
        min: 0,
        max: 40
    });


    // Snap to increments
    $(".ui-slider-increments").slider({
        value:100,
        min: 0,
        max: 500,
        step: 50
    });


    // Range slider
    $(".ui-slider-range").slider({
        range: true,
        min: 0,
        max: 60,
        values: [10, 50]
    });


    // Fixed minimum
    $(".ui-slider-range-min").slider({
        range: "min",
        value: 37,
        min: 1,
        max: 700
    });


    // Fixed maximum
    $(".ui-slider-range-max").slider({
        range: "max",
        min: 1,
        max: 10,
        value: 2
    });


    // Animated slider
    $(".ui-slider-animated").slider({
        range: "max",
        value: 50,
        animate: true
    });


    // Slider methods
    $(".ui-slider-methods").slider({
        range: true,
        min: 0,
        max: 60,
        values: [ 15, 45 ]
    });

    var sliderMethods = document.querySelector('.switchery');
    var sliderMethodsInit = new Switchery(sliderMethods);
    sliderMethods.onchange = function() {
        if(sliderMethods.checked) {
            $(".ui-slider-methods").slider('enable');
        }
        else {
            $(".ui-slider-methods").slider('disable'); 
        }
    };


    // Disabled slider
    $(".ui-slider-disabled").slider({
        range: "min",
        value: 50,
        disabled: true
    });



    //
    // Vertical sliders
    //

    // Basic
    $(".ui-slider-vertical-default > span").each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            animate: true,
            orientation: "vertical"
        });
    });


    // Range slider
    $(".ui-slider-vertical-range-min > span").each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            range: "min",
            animate: true,
            orientation: "vertical"
        });
    });


    // Fixed maximum
    $(".ui-slider-vertical-range-max > span").each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            range: "max",
            animate: true,
            orientation: "vertical"
        });
    });


    // Default handle
    $(".ui-slider-vertical-handle-default > span").each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            range: "min",
            animate: true,
            orientation: "vertical"
        });
    });



    // Slider pips
    // ------------------------------

    //
    // Horizontal sliders
    //

    // Basic
    $(".ui-slider-pips").slider({
        max: 14,
        value: 7
    });
    $(".ui-slider-pips").slider("pips");


    // With tooltip
    $(".ui-slider-floats").slider({
        max: 14,
        value: 7
    });
    $(".ui-slider-floats").slider("pips");
    $(".ui-slider-floats").slider("float");


    // Both pips and tooltip
    $(".ui-slider-floats-labels").slider({
        max: 6,
        value: 3
    });
    $(".ui-slider-floats-labels").slider("pips");
    $(".ui-slider-floats-labels").slider("float", {
        pips: true
    });


    // Label with pips
    $(".ui-slider-labels").slider({
        max: 8,
        value: 4
    });
    $(".ui-slider-labels").slider("pips" , {
        rest: "label"
    });


    // Hide rest of the points
    $(".ui-slider-limits").slider({
        max: 20,
        range: true,
        values: [ 4, 16 ]
    });
    $(".ui-slider-limits").slider("pips" , {
        rest: false
    });


    // Display pips only
    $(".ui-slider-pips-only").slider({
        max: 20
    });
    $(".ui-slider-pips-only").slider("pips", {
        first: "pip",
        last: "pip"
    });


    // Suffix and prefix
    $(".ui-slider-suffix-prefix").slider({
        max: 4,
        value: 2
    });
    $(".ui-slider-suffix-prefix").slider("pips", {
        rest: "label", 
        prefix: "$",
        suffix: ".00"
    });


    // Custom label text
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    $(".ui-slider-labels-custom").slider({
        min: 0,
        max: 11,
        value: 5
    });
    $(".ui-slider-labels-custom").slider("pips" , {
        rest: "label",
        labels: months
    });
    $(".ui-slider-labels-custom").on("slidechange", function(e,ui) {
        $("#ui-slider-labels-custom-output").html("You selected " + "<span class='text-danger'>" + months[ui.value] + "</span>");
    });


    // Label localization
    var hanziNums = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十"];

    $(".ui-slider-local").slider({
        min: 1,
        max: 10,
        value: 5
    });
    $(".ui-slider-local").slider("pips" , {
        labels: hanziNums,
        rest: "label"
    });
    $(".ui-slider-local").slider("float" , {
        labels: hanziNums
    });



    //
    // Vertical sliders
    //

    // Basic
    $(".ui-slider-vertical-pips > span").each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 10,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-pips > span").slider("pips");


    // With labels
    $( ".ui-slider-vertical-labels > span" ).each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 4,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-labels > span").slider("pips" , {
        rest: "label"
    });


    // With tooltip and handle
    $( ".ui-slider-vertical-minmax > span" ).each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 10,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-minmax > span").slider("pips");
    $(".ui-slider-vertical-minmax > span").slider("float");

});
