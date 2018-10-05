/* ------------------------------------------------------------------------------
*
*  # Select2 selects
*
*  Specific JS code additions for form_select2.html page
*
*  Version: 1.1
*  Latest update: Nov 20, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic examples
    // ------------------------------

    // Default initialization
    $('.select').select2({
        minimumResultsForSearch: Infinity
    });


    // Select with search
    $('.select-search').select2();


    // Fixed width. Single select
    $('.select-fixed-single').select2({
        minimumResultsForSearch: Infinity,
        width: 250
    });


    // Fixed width. Multiple selects
    $('.select-fixed-multiple').select2({
        minimumResultsForSearch: Infinity,
        width: 400
    });



    // Styling options
    // ------------------------------

    // Custom results color
    $('.select-results-color').select2({
        containerCssClass: 'bg-teal-400'
    });


    // Custom menu color
    $('.select-menu-color').select2({
        dropdownCssClass: 'bg-teal-400'
    });


    // Custom menu and results color
    $('.select-custom-colors').select2({
        containerCssClass: 'bg-indigo-400',
        dropdownCssClass: 'bg-indigo-400'
    });


    // Combine custom colors in multiple
    $('.select-menu2-color').select2({
        containerCssClass: 'bg-indigo-400',
        dropdownCssClass: 'bg-indigo-400'
    });

    // Menu border and text color
    $('.select-border-color').select2({
        dropdownCssClass: 'border-primary',
        containerCssClass: 'border-primary text-primary-700'
    });



    // Sizing options
    // ------------------------------

    // Large
    $('.select-size-lg').select2({
        containerCssClass: 'select-lg'
    });


    // Small
    $('.select-size-sm').select2({
        containerCssClass: 'select-sm'
    });


    // Mini
    $('.select-size-xs').select2({
        containerCssClass: 'select-xs'
    });



    // Advanced examples
    // ------------------------------

    // Minimum input length
    $(".select-minimum").select2({
        minimumInputLength: 2,
        minimumResultsForSearch: Infinity
    });


    // Allow clear selection
    $('.select-clear').select2({
        placeholder: "Select a State",
        allowClear: true
    });


    // Tagging support
    $(".select-multiple-tags").select2({
        tags: true
    });


    // Maximum input length
    $(".select-multiple-maximum-length").select2({
        tags: true,
        maximumInputLength: 5
    });


    // Tokenization
    $(".select-multiple-tokenization").select2({
        tags: true,
        tokenSeparators: [",", " "]
    });


    // Maximum selection
    $(".select-multiple-limited").select2({
        maximumSelectionLength: 3
    });


    // Maximum selections allowed
    $('.select-multiple-maximum').select2({
        maximumSelectionSize: 3
    });



    //
    // Drag and drop selected items
    //

    // Initialize with tags
    $(".select-multiple-drag").select2({
        containerCssClass: 'sortable-target'
    });

    // Add jQuery UI Sortable support
    $(".sortable-target .select2-selection__rendered").sortable({
        containment: '.sortable-target',
        items: '.select2-selection__choice:not(.select2-search--inline)'
    });



    //
    // Single select with icons
    //

    // Format icon
    function iconFormat(icon) {
        var originalOption = icon.element;
        if (!icon.id) { return icon.text; }
        var $icon = "<i class='icon-" + $(icon.element).data('icon') + "'></i>" + icon.text;

        return $icon;
    }

    // Initialize with options
    $(".select-icons").select2({
        templateResult: iconFormat,
        minimumResultsForSearch: Infinity,
        templateSelection: iconFormat,
        escapeMarkup: function(m) { return m; }
    });



    //
    // Customize matched results
    //

    // Setup matcher
    function matchStart (term, text) {
        if (text.toUpperCase().indexOf(term.toUpperCase()) == 0) {
            return true;
        }

        return false;
    }

    // Initialize
    $.fn.select2.amd.require(['select2/compat/matcher'], function (oldMatcher) {
        $(".select-matched-customize").select2({
            minimumResultsForSearch: Infinity,
            placeholder: "Select a State",
            matcher: oldMatcher(matchStart)
        });
    });



    //
    // Loading arrays of data
    //

    // Data
    var array_data = [
        {id: 0, text: 'enhancement'},
        {id: 1, text: 'bug'},
        {id: 2, text: 'duplicate'},
        {id: 3, text: 'invalid'},
        {id: 4, text: 'wontfix'}
    ];

    // Loading array data
    $(".select-data-array").select2({
        placeholder: "Click to load data",
        minimumResultsForSearch: Infinity,
        data: array_data
    });



    //
    // Loading remote data
    //

    // Format displayed data
    function formatRepo (repo) {
        if (repo.loading) return repo.text;

        var markup = "<div class='select2-result-repository clearfix'>" +
            "<div class='select2-result-repository__avatar'><img src='" + repo.owner.avatar_url + "' /></div>" +
            "<div class='select2-result-repository__meta'>" +
            "<div class='select2-result-repository__title'>" + repo.full_name + "</div>";

        if (repo.description) {
            markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
        }

        markup += "<div class='select2-result-repository__statistics'>" +
            "<div class='select2-result-repository__forks'>" + repo.forks_count + " Forks</div>" +
            "<div class='select2-result-repository__stargazers'>" + repo.stargazers_count + " Stars</div>" +
            "<div class='select2-result-repository__watchers'>" + repo.watchers_count + " Watchers</div>" +
            "</div>" +
            "</div></div>";

        return markup;
    }

    // Format selection
    function formatRepoSelection (repo) {
        return repo.full_name || repo.text;
    }

    // Initialize
    $(".select-remote-data").select2({
        ajax: {
            url: "https://api.github.com/search/repositories",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {

                // parse the results into the format expected by Select2
                // since we are using custom formatting functions we do not need to
                // alter the remote JSON data, except to indicate that infinite
                // scrolling can be used
                params.page = params.page || 1;

                return {
                    results: data.items,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
        minimumInputLength: 1,
        templateResult: formatRepo, // omitted for brevity, see the source of this page
        templateSelection: formatRepoSelection // omitted for brevity, see the source of this page
    });



    //
    // Programmatic access (single)
    //

    // Set/get value
    $('.select-access-value').select2({
        minimumResultsForSearch: Infinity,
        placeholder: "Select State..."
    });
    $(".access-get").click(function () { alert("Selected value is: "+$(".select-access-value").val()); });
    $(".access-set").click(function () { $(".select-access-value").val("CA").trigger("change"); });


    // Open/close menu
    $('.select-access-open').select2({
        minimumResultsForSearch: Infinity,
        placeholder: "Select State..."
    });
    $(".access-open").click(function () { $(".select-access-open").select2("open"); });
    $(".access-close").click(function () { $(".select-access-open").select2("close"); });


    // Enable/disable menu
    $('.select-access-enable').select2({
        minimumResultsForSearch: Infinity,
        placeholder: "Select State..."
    });
    $(".access-disable").click(function () { $(".select-access-enable").prop("disabled", true); });
    $(".access-enable").click(function () { $(".select-access-enable").prop("disabled", false); });


    // Destroy/create menu
    function create_menu() {
        $('.select-access-create').select2({
            minimumResultsForSearch: Infinity,
            placeholder: "Select State..."
        });
    }
    create_menu();
    $(".access-create").on("click", function () { return create_menu()});
    $(".access-destroy").on("click", function () { $('.select-access-create').select2("destroy"); });



    //
    // Programmatic access (multiple)
    //

    // Reacting to external value changes
    $(".select-access-multiple-value").select2();
    $(".change-to-ca").click(function() { $(".select-access-multiple-value").val("CA").trigger("change"); });
    $(".change-to-ak-co").click(function() { $(".select-access-multiple-value").val(["AK","CO"]).trigger("change"); });


    // Open/close menu
    $('.select-access-multiple-open').select2({
        minimumResultsForSearch: Infinity
    });
    $(".access-multiple-open").click(function () { $(".select-access-multiple-open").select2("open"); });
    $(".access-multiple-close").click(function () { $(".select-access-multiple-open").select2("close"); });


    // Enable/disable menu
    $('.select-access-multiple-enable').select2({
        minimumResultsForSearch: Infinity
    });
    $(".access-multiple-disable").click(function () { $(".select-access-multiple-enable").prop("disabled", true); });
    $(".access-multiple-enable").click(function () { $(".select-access-multiple-enable").prop("disabled", false); });


    // Destroy/create menu
    function create_menu_multiple() {
        $('.select-access-multiple-create').select2({
            minimumResultsForSearch: Infinity
        });
    }
    create_menu_multiple();
    $(".access-multiple-create").on("click", function () { return create_menu_multiple()});
    $(".access-multiple-destroy").on("click", function () { $('.select-access-multiple-create').select2("destroy"); });


    // Clear selection
    $('.select-access-multiple-clear').select2({
        minimumResultsForSearch: Infinity
    });
    $(".access-multiple-clear").on("click", function () { $(".select-access-multiple-clear").val(null).trigger("change"); });
    
});
