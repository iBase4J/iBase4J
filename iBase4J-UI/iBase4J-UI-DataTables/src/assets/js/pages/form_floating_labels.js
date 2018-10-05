/* ------------------------------------------------------------------------------
*
*  # Form layouts
*
*  Specific JS code additions for form layouts pages
*
*  Version: 1.0
*  Latest update: Mar 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Plugins (ordering matters)
    // ------------------------------

    // Basic initialization
    $('.token-field').tokenfield();


    // Basic initialization
    $('.tags-input').tagsinput();



    // Basic example
    // ------------------------------

    // Substring matches
    var substringMatcher = function(strs) {
        return function findMatches(q, cb) {
            var matches, substringRegex;

            // an array that will be populated with substring matches
            matches = [];

            // regex used to determine if a string contains the substring `q`
            substrRegex = new RegExp(q, 'i');

            // iterate through the pool of strings and for any string that
            // contains the substring `q`, add it to the `matches` array
            $.each(strs, function(i, str) {
                if (substrRegex.test(str)) {

                    // the typeahead jQuery plugin expects suggestions to a
                    // JavaScript object, refer to typeahead docs for more info
                    matches.push({ value: str });
                }
            });

            cb(matches);
        };
    };

    // Add data
    var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
        'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
        'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
        'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
        'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
        'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
        'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
        'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
        'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
    ];

    // Initialize
    $('.typeahead-basic').typeahead(
        {
            hint: true,
            highlight: true,
            minLength: 1
        },
        {
            name: 'states',
            displayKey: 'value',
            source: substringMatcher(states)
        }
    );



    // Floating labels
    // ------------------------------

    // Variables
    var onClass = "on";
    var showClass = "is-visible";


    // Setup
    $("input:not(.token-input):not(.bootstrap-tagsinput > input), textarea, select").on("checkval change", function () {

        // Define label
        var label = $(this).parents('.form-group-material').children(".control-label");

        // Toggle label
        if (this.value !== "") {
            label.addClass(showClass);
        }
        else {
            label.removeClass(showClass).addClass('animate');
        }

    }).on("keyup", function () {
        $(this).trigger("checkval");
    }).trigger("checkval").trigger('change');


    // Remove animation on page load
    $(window).on('load', function() {
        $(".form-group-material").children('.control-label.is-visible').removeClass('animate');
    });


    //
    // Setup plugins
    //

    // Tokenfield
    $(".token-field").on('tokenfield:createdtoken tokenfield:removedtoken change', function (e) {
        if($(this).parent().children().hasClass('token')) {
            $(this).parent().find('.token-input').attr('placeholder', '');
        }
        else {
            $(this).parent().find('.token-input').attr('placeholder', '- Tokenfield');
        }
    }).trigger('change');


    // Tags input
    $(".tags-input").on('itemAdded itemRemoved change', function (e) {
        if($(this).parent().find('.bootstrap-tagsinput').children().hasClass('label')) {
            $(this).parent().find('.bootstrap-tagsinput').children('input[type=text]').attr('placeholder', '');
        }
        else {
            $(this).parent().find('.bootstrap-tagsinput').children('input[type=text]').attr('placeholder', '- Bootstrap tags input');
        }
    }).trigger('change');



    // Plugins
    // ------------------------------

    // Basic example
    $(".touchspin-basic").TouchSpin({
        postfix: '<i class="icon-paragraph-justify2"></i>'
    });


    // Date
    $('[name="format-date"]').formatter({
        pattern: '{{99}}/{{99}}/{{9999}}'
    });


    // Basic example
    $('.maxlength').maxlength();


    // Basic initialization
    $('.multiselect').multiselect({
        nonSelectedText: 'Bootstrap multiselect',
        onChange: function() {
            $.uniform.update();
        }
    });


    // Basic initialization
    $(".selectbox").selectBoxIt({
        autoWidth: false
    });


    // Override defaults
    $.fn.selectpicker.defaults = {
        iconBase: '',
        tickIcon: 'icon-checkmark3'
    }



    // Basic setup
    // ------------------------------

    // Basic select
    $('.bootstrap-select').selectpicker();



    // Select2 select
    // ------------------------------

    // Basic
    $('.select').select2();



    // Styled form components
    // ------------------------------

    // Checkboxes, radios
    $(".styled, .multiselect-container input").uniform({ radioClass: 'choice' });

    // File input
    $(".file-styled").uniform({
        fileButtonClass: 'action btn bg-pink-400'
    });
    
});
