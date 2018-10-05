/* ------------------------------------------------------------------------------
*
*  # Extended form controls
*
*  Specific JS code additions for form_controls_extended.html page
*
*  Version: 1.2
*  Latest update: Jul 4, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // ========================================
    //
    // Components
    //
    // ========================================


    // Input formatter
    // ------------------------------

    // Date
    $('[name="format-date"]').formatter({
        pattern: '{{99}}/{{99}}/{{9999}}'
    });

    // Credit card
    $('[name="format-credit-card"]').formatter({
        pattern: '{{9999}} - {{9999}} - {{9999}} - {{9999}}'
    });

    // Phone #
    $('.format-phone-number').formatter({
        pattern: '({{999}}) {{999}} - {{9999}}'
    });

    // Phone ext
    $('[name="format-phone-ext"]').formatter({
        pattern: '({{999}}) {{999}} - {{9999}} / {{a999}}'
    });

    // Currency
    $('[name="format-currency"]').formatter({
        pattern: '${{999}}.{{99}}'
    });

    // International phone
    $('[name="format-international-phone"]').formatter({
        pattern: '+3{{9}} {{999}} {{999}} {{999}}'
    });

    // Tax id
    $('[name="format-tax-id"]').formatter({
        pattern: '{{99}} - {{9999999}}'
    });

    // SSN
    $('[name="format-ssn"]').formatter({
        pattern: '{{999}} - {{99}} - {{9999}}'
    });

    // Product key
    $('[name="format-product-key"]').formatter({
        pattern: '{{a*}} - {{999}} - {{a999}}'
    });

    // Order #
    $('[name="format-order-number"]').formatter({
        pattern: '{{aaa}} - {{999}} - {{***}}'
    });

    // ISBN
    $('[name="format-isbn"]').formatter({
        pattern: '{{999}} - {{99}} - {{999}} - {{9999}} - {{9}}'
    });

    // Persistent
    $('[name="format-persistent"]').formatter({
        pattern: '+3 ({{999}}) {{999}} - {{99}} - {{99}}'
    });



    // Elastic textarea
    // ------------------------------

    // Basic example
    autosize($('.elastic'));

    // Manual trigger
    $('.elastic-manual-trigger').on('click', function() {
        var manual = autosize($('.elastic-manual'));
        $('.elastic-manual').val('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sed ultricies nibh, sed faucibus eros. Vivamus tristique fringilla ante, vitae pellentesque quam porta vel. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nunc vehicula gravida nisl non imperdiet. Mauris felis odio, vehicula et laoreet non, tempor non enim. Cras convallis sapien hendrerit nibh sagittis sollicitudin. Fusce nec ultricies justo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce ac urna in dui consequat cursus vel sit amet mauris. Proin nec bibendum arcu. Aenean sit amet nisi mi. Sed non leo nisl. Mauris leo odio, ultricies interdum ornare ac, posuere eu risus. Suspendisse adipiscing sapien sit amet gravida sollicitudin. Maecenas laoreet velit in dui adipiscing, vel fermentum tellus ullamcorper. Nullam et mi rhoncus, tempus nulla sit amet, varius ipsum.');
        autosize.update(manual);
    });

    // Destroy method
    var destroyAutosize = autosize($('.elastic-destroy'));
    $('.elastic-destroy-trigger').on('click', function() {
        autosize.destroy(destroyAutosize);
    });



    // Passy - password generator
    // ------------------------------

    // Input labels
    var $inputLabel = $('.label-indicator input');
    var $inputLabelAbsolute = $('.label-indicator-absolute input');
    var $inputGroup = $('.group-indicator input');

    // Output labels
    var $outputLabel = $('.label-indicator > span');
    var $outputLabelAbsolute = $('.label-indicator-absolute > span');
    var $outputGroup = $('.group-indicator > span');


    // Min input length
    $.passy.requirements.length.min = 4;


    // Strength meter
    var feedback = [
        {color: '#D55757', text: 'Weak', textColor: '#fff'},
        {color: '#EB7F5E', text: 'Normal', textColor: '#fff'},
        {color: '#3BA4CE', text: 'Good', textColor: '#fff'},
        {color: '#40B381', text: 'Strong', textColor: '#fff'}
    ];


    //
    // Setup strength meter
    //

    // Label indicator
    $inputLabel.passy(function(strength) {
        $outputLabel.text(feedback[strength].text);
        $outputLabel.css('background-color', feedback[strength].color).css('color', feedback[strength].textColor);
    });

    // Absolute positioned label
    $inputLabelAbsolute.passy(function(strength) {
        $outputLabelAbsolute.text(feedback[strength].text);
        $outputLabelAbsolute.css('background-color', feedback[strength].color).css('color', feedback[strength].textColor);
    });

    // Input group indicator
    $inputGroup.passy(function(strength) {
        $outputGroup.text(feedback[strength].text);
        $outputGroup.css('background-color', feedback[strength].color).css('border-color', feedback[strength].color).css('color', feedback[strength].textColor);
    });


    //
    // Initialize
    //

    // Label
    $('.generate-label').click(function() {
        $inputLabel.passy( 'generate', 12 );
    });

    // Absolute label
    $('.generate-label-absolute').click(function() {
        $inputLabelAbsolute.passy( 'generate', 10 );
    });

    // Group label
    $('.generate-group').click(function() {
        $inputGroup.passy( 'generate', 8 );
    });



    // Maxlength
    // ------------------------------

    // Basic example
    $('.maxlength').maxlength();

    // Threshold
    $('.maxlength-threshold').maxlength({
        threshold: 15
    });

    // Custom label color
    $('.maxlength-custom').maxlength({
        threshold: 10,
        warningClass: "label label-primary",
        limitReachedClass: "label label-danger"
    });

    // Options
    $('.maxlength-options').maxlength({
        alwaysShow: true,
        threshold: 10,
        warningClass: "text-success",
        limitReachedClass: "text-danger",
        separator: ' of ',
        preText: 'You have ',
        postText: ' chars remaining.',
        validate: true
    });

    // Always show label
    $('.maxlength-textarea').maxlength({
        alwaysShow: true
    });

    // Label position
    $('.maxlength-label-position').maxlength({
        alwaysShow: true,
        placement: 'top'
    });




    // ========================================
    //
    // Typeahead
    //
    // ========================================


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



    // Bloodhound engine
    // ------------------------------

    // Constructs the suggestion engine
    var states = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,

        // `states` is an array of state names defined in "The Basics"
        local: $.map(states, function(state) { return { value: state }; })
    });

    // Initialize engine
    states.initialize();

    // Initialize
    $('.typeahead-bloodhound').typeahead(
        {
            hint: true,
            highlight: true,
            minLength: 1
        },
        {
            name: 'states',
            displayKey: 'value',

            // `ttAdapter` wraps the suggestion engine in an adapter that
            // is compatible with the typeahead jQuery plugin
            source: states.ttAdapter()
        }
    );



    // Prefetched data
    // ------------------------------

    // Constructs the suggestion engine
    var countries = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 10,
        prefetch: {

            // url points to a json file that contains an array of country names, see
            // https://github.com/twitter/typeahead.js/blob/gh-pages/data/countries.json
            url: 'assets/demo_data/typeahead/countries.json',

            // the json file contains an array of strings, but the Bloodhound
            // suggestion engine expects JavaScript objects so this converts all of
            // those strings
            filter: function(list) {
                return $.map(list, function(country) { return { name: country }; });
            }
        }
    });

    // Initialize engine
    countries.initialize();

    // Passing in `null` for the `options` arguments will result in the default options being used
    $('.typeahead-prefetched').typeahead(null, {
        name: 'countries',
        displayKey: 'name',

        // `ttAdapter` wraps the suggestion engine in an adapter that
        // is compatible with the typeahead jQuery plugin
        source: countries.ttAdapter()
    });



    // Remote data
    // ------------------------------

    // Constructs the suggestion engine
    var bestPictures = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        prefetch: 'assets/demo_data/typeahead/movies.json',
        remote: 'assets/json/queries/%QUERY.json'
    });

    // Initialize engine
    bestPictures.initialize();

    // Initialize
    $('.typeahead-remote').typeahead(null, {
        name: 'best-pictures',
        displayKey: 'value',
        source: bestPictures.ttAdapter()
    });



    // Custom templates
    // ------------------------------

    // Initialize with opitons
    $('.typeahead-custom-templates').typeahead(null, {
        name: 'best-pictures',
        displayKey: 'value',
        source: bestPictures.ttAdapter(),
        templates: {
            empty: [
                '<div class="empty-message">',
                'unable to find any Best Picture winners that match the current query',
                '</div>'
            ].join('\n'),
            suggestion: Handlebars.compile('<div><strong>{{value}}</strong> – {{year}}</div>')
        }
    });



    // Multiple datasets
    // ------------------------------

    // Constructs the suggestion engine for 1st dataset
    var nbaTeams = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('team'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        prefetch: 'assets/demo_data/typeahead/nba.json'
    });

    // Constructs the suggestion engine for 2nd dataset
    var nhlTeams = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('team'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        prefetch: 'assets/demo_data/typeahead/nhl.json'
    });

    // Initialize engines
    nbaTeams.initialize();
    nhlTeams.initialize();

    // Initialize 1st dataset
    $('.typeahead-multiple-datasets').typeahead(
        {
            highlight: true
        },
        {
            name: 'group',
            displayKey: 'team',
            source: nbaTeams.ttAdapter(),
            templates: {
                header: '<span class="tt-heading">NBA Teams</span>'
            }
        },
        {
            name: 'group',
            displayKey: 'team',
            source: nhlTeams.ttAdapter(),
            templates: {
                header: '<span class="tt-heading">NHL Teams</span>'
            }
        }
    );



    // Dropdown height
    // ------------------------------

    // Initialize with options
    $('.typeahead-scrollable-menu').typeahead(null, {
        name: 'countries',
        displayKey: 'name',
        source: countries.ttAdapter()
    });



    // RTL support
    // ------------------------------

    // Constructs the suggestion engine
    var arabicPhrases = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('word'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        local: [
            { word: "الإنجليزية" },
            { word: "نعم" },
            { word: "لا" },
            { word: "مرحبا" },
            { word: "أهلا" }
        ]
    });

    // Initialize engine
    arabicPhrases.initialize();

    // Initialize
    $('.typeahead-rtl-support').typeahead(
        {
            hint: false
        },
        {
            name: 'arabic-phrases',
            displayKey: 'word',
            source: arabicPhrases.ttAdapter()
        }
    );

});
