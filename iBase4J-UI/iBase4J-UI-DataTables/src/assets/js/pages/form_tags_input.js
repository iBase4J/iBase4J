/* ------------------------------------------------------------------------------
*
*  # Tag inputs
*
*  Specific JS code additions for form_tag_inputs.html page
*
*  Version: 1.2
*  Latest update: Aug 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // ========================================
    //
    // Tokenfield
    //
    // ========================================


    // Basic initialization
    $('.tokenfield').tokenfield();


    // Styling
    // ------------------------------

    //
    // Primary
    //

    // Add class on init
    $('.tokenfield-primary').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-primary')
    });

    // Initialize plugin
    $('.tokenfield-primary').tokenfield();

    // Add class when token is created
    $('.tokenfield-primary').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-primary')
    });


    //
    // Teal
    //

    // Add class on init
    $('.tokenfield-teal').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-teal')
    });

    // Initialize plugin
    $('.tokenfield-teal').tokenfield();

    // Add class when token is created
    $('.tokenfield-teal').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-teal')
    });


    //
    // Purple
    //

    // Add class on init
    $('.tokenfield-purple').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-purple')
    });

    // Initialize plugin
    $('.tokenfield-purple').tokenfield();

    // Add class when token is created
    $('.tokenfield-purple').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-purple')
    });


    //
    // Success
    //

    // Add class on init
    $('.tokenfield-success').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-success')
    });

    // Initialize plugin
    $('.tokenfield-success').tokenfield();

    // Add class when token is created
    $('.tokenfield-success').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-success')
    });


    //
    // Danger
    //

    // Add class on init
    $('.tokenfield-danger').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-danger')
    });

    // Initialize plugin
    $('.tokenfield-danger').tokenfield();

    // Add class when token is created
    $('.tokenfield-danger').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-danger')
    });


    //
    // Warning
    //

    // Add class on init
    $('.tokenfield-warning').on('tokenfield:initialize', function (e) {
        $(this).parent().find('.token').addClass('bg-warning')
    });

    // Initialize plugin
    $('.tokenfield-warning').tokenfield();

    // Add class when token is created
    $('.tokenfield-warning').on('tokenfield:createdtoken', function (e) {
        $(e.relatedTarget).addClass('bg-warning')
    });



    // Other examples
    // ------------------------------

    //
    // Typeahead support
    //

    // Use Bloodhound engine
    var engine = new Bloodhound({
        local: [
            {value: 'red'},
            {value: 'blue'},
            {value: 'green'} ,
            {value: 'yellow'},
            {value: 'violet'},
            {value: 'brown'},
            {value: 'purple'},
            {value: 'black'},
            {value: 'white'}
        ],
        datumTokenizer: function(d) {
            return Bloodhound.tokenizers.whitespace(d.value);
        },
        queryTokenizer: Bloodhound.tokenizers.whitespace
    });

    // Initialize engine
    engine.initialize();

    // Initialize tokenfield
    $('.tokenfield-typeahead').tokenfield({
        typeahead: [null, {
            displayKey: 'value',
            source: engine.ttAdapter()
        }]
    });



    // Methods
    // ------------------------------

    // Set tokens
    $('#set-tokens').on('click', function() {
        $('#set-tokens-field').tokenfield('setTokens', ['blue','red','white']);
    })


    // Create tokens
    $('#create-token').on('click', function() {
        $('#create-token-field').tokenfield('createToken', { value: 'new', label: 'New token' });
    })


    //
    // Disable, enable
    //

    // Initialize plugin
    $('.tokenfield-disable').tokenfield();

    // Disable on click
    $('#disable').on('click', function() {
        $('.tokenfield-disable').tokenfield('disable');
    });

    // Enabe on click
    $('#enable').on('click', function() {
        $('.tokenfield-disable').tokenfield('enable');
    });


    //
    // Readonly, writeable
    //

    // Initialize plugin
    $('.tokenfield-readonly').tokenfield();

    // Readonly on click
    $('#readonly').on('click', function() {
        $('.tokenfield-readonly').tokenfield('readonly');
    });

    // Writeable on click
    $('#writeable').on('click', function() {
        $('.tokenfield-readonly').tokenfield('writeable');
    });


    //
    // Destroy, create
    //

    // initialize plugin
    $('.tokenfield-destroy').tokenfield();

    // Destroy on click
    $('#destroy').on('click', function() {
        $('.tokenfield-destroy').tokenfield('destroy');
    });

    // Create on click
    $('#create').on('click', function() {
        $('.tokenfield-destroy').tokenfield();
    });




    // ========================================
    //
    // Tags input
    //
    // ========================================


    // Display values
    $('.tags-input, [data-role="tagsinput"], .tagsinput-max-tags, .tagsinput-custom-tag-class').on('change', function(event) {
        var $element = $(event.target),
            $container = $element.parent().parent('.content-group');

        if (!$element.data('tagsinput'))
        return;

        var val = $element.val();
        if (val === null)
        val = "null";
    
        $('pre.val > code', $container).html( ($.isArray(val) ? JSON.stringify(val) : "\"" + val.replace('"', '\\"') + "\"") );
        $('pre.items > code', $container).html(JSON.stringify($element.tagsinput('items')));
        Prism.highlightAll();
    }).trigger('change');



    // Basic examples
    // ------------------------------

    // Basic initialization
    $('.tags-input').tagsinput();


    // Allow dublicates
    $('.tags-input-dublicates').tagsinput({
        allowDuplicates: true
    });


    // Set maximum allowed tags
    $('.tagsinput-max-tags').tagsinput({
        maxTags: 5
    });


    // Custom tag class
    $('.tagsinput-custom-tag-class').tagsinput({
        tagClass: function(item){
            return 'label bg-success';
        }
    });


    //
    // Typeahead implementation
    //

    // Matcher
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

    // Data
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

    // Attach typeahead
    $('.tagsinput-typeahead').tagsinput('input').typeahead(
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
    ).bind('typeahead:selected', $.proxy(function (obj, datum) {  
        this.tagsinput('add', datum.value);
        this.tagsinput('input').typeahead('val', '');
    }, $('.tagsinput-typeahead')));


    //
    // Objects as tags
    //

    // Use Bloodhound engine
    var countries = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('text'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 10,
        prefetch: {
            url: 'assets/demo_data/tags_input/cities.json'
        }
    });

    // Kicks off the loading/processing of `local` and `prefetch`
    countries.initialize();

    // Define element
    var elt = $('.tagsinput-tag-objects');

    // Initialize
    elt.tagsinput({
        itemValue: 'value',
        itemText: 'text',
        typeaheadjs: {
            name: 'countries',
            displayKey: 'text',
            source: countries.ttAdapter()
        }
    });

    // Add data
    elt.tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });
    elt.tagsinput('add', { "value": 4 , "text": "Washington"  , "continent": "America"   });
    elt.tagsinput('add', { "value": 7 , "text": "Sydney"      , "continent": "Australia" });
    elt.tagsinput('add', { "value": 10, "text": "Beijing"     , "continent": "Asia"      });
    elt.tagsinput('add', { "value": 13, "text": "Cairo"       , "continent": "Africa"    });


    //
    // Categorize tags
    //

    // Use Bloodhound engine
    var continents = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('continent'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 10,
        prefetch: {
            url: 'assets/demo_data/tags_input/cities.json'
        }
    });

    // Kicks off the loading/processing of `local` and `prefetch`
    continents.initialize();

    // Define element
    var elt2 = $('.tagsinput-tag-categorizing');

    // Initialize
    elt2.tagsinput({
        tagClass: function(item) {
            switch (item.continent) {
                case 'Europe'   : return 'label bg-indigo-400';
                case 'America'  : return 'label bg-danger';
                case 'Australia': return 'label bg-success';
                case 'Africa'   : return 'label bg-primary';
                case 'Asia'     : return 'label bg-pink-400';
            }
        },
        itemValue: 'value',
        itemText: 'text',
        typeaheadjs: {
            name: 'continents',
            displayKey: 'text',
            source: continents.ttAdapter()
        }
    });

    // Add data
    elt2.tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });
    elt2.tagsinput('add', { "value": 4 , "text": "Washington"  , "continent": "America"   });
    elt2.tagsinput('add', { "value": 7 , "text": "Sydney"      , "continent": "Australia" });
    elt2.tagsinput('add', { "value": 10, "text": "Beijing"     , "continent": "Asia"      });
    elt2.tagsinput('add', { "value": 13, "text": "Cairo"       , "continent": "Africa"    });


    //
    // Methods
    //

    // Define elements
    var tagsMethods = $('.tagsinput-add-tag, .tagsinput-remove-tag, .tagsinput-remove-tags, .tagsinput-destroy, .tagsinput-refresh');

    // Initialize
    tagsMethods.tagsinput({
        itemValue: 'id',
        itemText: 'text'
    });

    // Add values
    tagsMethods.tagsinput('add', {id: 1, text: 'Amsterdam'});
    tagsMethods.tagsinput('add', {id: 2, text: 'Washington'});
    tagsMethods.tagsinput('add', {id: 3, text: 'Sydney'});

    // "Add" methos
    $('.add-tag-button').on('click', function() {
        $('.tagsinput-add-tag').tagsinput('add', {id: 4, text: 'Beijing'});
        $(this).addClass('disabled');
    });

    // "Remove" method
    $('.remove-tag-button').on('click', function() {
        $('.tagsinput-remove-tag').tagsinput('remove', {id: 3, text: 'Sydney'});
        $(this).addClass('disabled');
    });

    // "Remove all" method
    $('.remove-all-tags-button').on('click', function() {
        $('.tagsinput-remove-tags').tagsinput('removeAll');
        $(this).addClass('disabled');
    });

    // "Destroy" method
    $('.destroy-tagsinput-button').on('click', function() {
        $('.tagsinput-destroy').tagsinput('destroy');
        $(this).addClass('disabled');
    });

});
