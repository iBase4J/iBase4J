/* ------------------------------------------------------------------------------
*
*  # Editable component
*
*  Specific JS code additions for form_editable.html page
*
*  Version: 1.1
*  Latest update: Mar 5, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Override defaults
    // ------------------------------

    // Disable highlight
    $.fn.editable.defaults.highlight = false;

    // Output template
    $.fn.editableform.template = '<form class="editableform">' +
        '<div class="control-group">' +
        '<div class="editable-input"></div> <div class="editable-buttons"></div>' +
        '<div class="editable-error-block"></div>' +
        '</div> ' +
    '</form>'

    // Set popup mode as default
    $.fn.editable.defaults.mode = 'popup';

    // Buttons
    $.fn.editableform.buttons = 
        '<button type="submit" class="btn btn-primary btn-icon editable-submit"><i class="icon-check"></i></button>' +
        '<button type="button" class="btn btn-default btn-icon editable-cancel"><i class="icon-x"></i></button>';




    // Demo settings
    // ------------------------------

    // Toggle editable state
    var toggleState = document.querySelector('.switchery');
    var toggleStateInit = new Switchery(toggleState);
    toggleState.onchange = function() {
        if(toggleState.checked) {
            $('.editable').editable('enable');
        }
        else {
            $('.editable').editable('disable');
        }
    };


    // Write log in console
    function log(settings, response) {
        var s = [], str;
        s.push(settings.type.toUpperCase() + ' url = "' + settings.url + '"');
        for(var a in settings.data) {
            if(settings.data[a] && typeof settings.data[a] === 'object') {
                str = [];
                for(var j in settings.data[a]) {str.push(j+': "'+settings.data[a][j]+'"');}
                str = '{ '+str.join(', ')+' }';
            }
            else {
                str = '"'+settings.data[a]+'"';
            }
            s.push(a + ' = ' + str);
        }
        s.push('RESPONSE: status = ' + response.status);

        if(response.responseText) {
            if($.isArray(response.responseText)) {
                s.push('[');
                $.each(response.responseText, function(i, v){
                    s.push('{value: ' + v.value+', text: "'+v.text+'"}');
                }); 
                s.push(']');
            }
            else {
                s.push($.trim(response.responseText));
            }
        }
        s.push('--------------------------------------\n');
    };




    // Basic editable components
    // ------------------------------

    // Editable text field
    $('#text-field').editable();


    // Disable clear button
    $('#disabled-clear').editable({
        clear: false
    });


    // With helper text
    $('#text-field-help').editable();
    $('#text-field-help').on('shown', function(e, editable) {
        $('<span class="help-block">This is a help block</span>').insertAfter(editable.input.$input);
    });


    // Empty field
    $('#empty-field').editable();


    // Required text field
    $('#empty-field-validate').editable({
        validate: function(value) {
            if($.trim(value) == '') return 'This field is required';
        }
    });



    //
    // Textareas
    //

    // Textarea
    $('#textarea').editable({
        rows: 2,
        showbuttons: 'bottom'
    });


    // Elastic textarea
    $('#textarea-elastic').editable({
        showbuttons: 'bottom'
    });
    $('#textarea-elastic').on('shown', function(e, editable) {
        editable.input.$input.addClass('elastic');
        autosize($('.elastic'));
    });



    //
    // Buttons and icons
    //

    // Button variations
    $('#button-variation').editable();
    $('#button-variation').on('shown', function(e, editable) {
        editable.input.$input.parents('.editable-input').parent().find('.editable-submit').removeClass('btn-success').addClass('btn-danger');
        editable.input.$input.parents('.editable-input').parent().find('.editable-cancel').removeClass('btn-danger').addClass('btn-success');
    });


    // Icon variations
    $('#icon-variation').editable();
    $('#icon-variation').on('shown', function(e, editable) {
        editable.input.$input.parents('.editable-input').parent().find('.editable-submit').children().removeClass('icon-check').addClass('icon-task');
        editable.input.$input.parents('.editable-input').parent().find('.editable-cancel').children().removeClass('icon-x').addClass('icon-menu6');
    });



    //
    // Dates
    //

    // Date field
    $('#date').editable({
        showbuttons: 'bottom'
    });


    // Date picker
    $('#datepicker').editable({
        showbuttons: 'bottom'
    });


    // Date time
    $('#datetime').editable({
        combodate: {
            firstItem: 'name'
        },
        showbuttons: 'bottom'
    }); 



    //
    // Multiple fields
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/address',
        response: function(settings) {
            log(settings, this);
        }
    });

    // Initialize
    $('#multiple-fields').editable({
        url: '/address',
        showbuttons: 'bottom',
        value: {
            city: "Moscow", 
            street: "Lenina", 
            building: "12"
        },
        tpl: '<div class="editable-address form-group"><label>City: </label><input type="text" name="city" class="form-control"></div>'+
        '<div class="editable-address form-group"><label>Street: </label><input type="text" name="street" class="form-control"></div>'+
        '<div class="editable-address form-group"><label>Building: </label><input type="text" name="building" class="form-control"></div>',
        validate: function(value) {
            if(value.city == '') return 'city is required!'; 
        },
        display: function(value) {
        if(!value) {
            $(this).empty();
            return; 
        }
        var html = '<b>' + $('<div>').text(value.city).html() + '</b>, ' + $('<div>').text(value.street).html() + ' st., bld. ' + $('<div>').text(value.building).html();
            $(this).html(html); 
        }         
    });



    //
    // Input groups
    //

    // Addons
    $('#input-group-addon').editable();
    $('#input-group-addon').on('shown', function (e, editable) {
        editable.input.$input.wrap('<div class="input-group"></div>');
        $('<span class="input-group-addon"><i class="icon-mention"></i></span>').insertBefore(editable.input.$input);
    });


    // Buttons
    $('#input-group-button').editable();
    $('#input-group-button').on('shown', function (e, editable) {
        editable.input.$input.wrap('<div class="input-group"></div>');
        $('<div class="input-group-btn"><button type="button" class="btn btn-default">Button</button></div>').insertBefore(editable.input.$input);
    });


    // Dropdown
    $('#input-group-dropdown').editable();
    $('#input-group-dropdown').on('shown', function (e, editable) {
        editable.input.$input.wrap('<div class="input-group"></div>');
        $('<div class="input-group-btn"><button type="button" class="btn btn-default btn-icon" data-toggle="dropdown"><i class="icon-cog5"></i> <span class="caret"></span></button> <ul class="dropdown-menu"> <li><a href="#">Action</a></li> <li><a href="#">Another action</a></li> <li><a href="#">Something else here</a></li> </ul> </div>').insertBefore(editable.input.$input);
    });




    // Basic selects
    // ------------------------------

    // Basic select
    $('#select-default').editable({
        prepend: "Not selected",
        source: [
            {value: 1, text: 'Male'},
            {value: 2, text: 'Female'}
        ],
        display: function(value, sourceData) {
            var colors = {"": "gray", 1: "green", 2: "blue"},
            elem = $.grep(sourceData, function(o){return o.value == value;});

            if(elem.length) {    
                $(this).text(elem[0].text).css("color", colors[value]); 
            }
            else {
                $(this).empty(); 
            }
        }   
    });


    //
    // Dependent select
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/default-list',
        responseTime: 400,
        response: function(settings) {
            if(settings.data.value == 'err') {
                this.status = 500;  
                this.responseText = 'Validation error!'; 
            }
            else {
                this.responseText = '';  
            }
        }
    });

    // Data
    var sources = {
        1: [{value: 11, text: 11}, {value: 111, text: 111}], 
        2: [{value: 22, text: 22}, {value: 222, text: 222}] 
    };

    // Initialize first list
    $('#default-list').editable({
        url: '/default-list',    
        pk: 1,
        source: [{value: 1, text: 'text1'}, {value: 2, text: 'text2'}],
        title: 'Select1',
        success: function(response, newValue) {
            $('#dependent-list').editable('option', 'source', sources[newValue]);  
            $('#dependent-list').editable('setValue', null);
        }
    });

    // Initialize dependent list
    $('#dependent-list').editable({
        url: '/default-list',    
        pk: 1,    
        title: 'Select2',
        sourceError: 'Please, select value in first list' 
    });



    //
    // Select with remote source
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/remote',
        response: function(settings) {
            this.responseText = [ 
                {value: 0, text: 'Guest'},
                {value: 1, text: 'Service'},
                {value: 2, text: 'Customer'},
                {value: 3, text: 'Operator'},
                {value: 4, text: 'Support'},
                {value: 5, text: 'Admin'}
            ];
            log(settings, this);
        }        
    });

    // Initialize
    $('#select-default-remote').editable({
        source: '/remote',
        showbuttons: false 
    });



    //
    // Select with loading error
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/error',
        status: 500,
        response: function(settings) {
            this.responseText = 'Internal Server Error';
        }        
    });

    // Initialize
    $('#select-default-error').editable({
        source: '/error'
    });




    // Checkboxes and radios
    // ------------------------------

    // Single unstyled checkbox
    $('#single-unstyled-checkbox').editable({
        source: {'1': 'Enabled'},
        emptytext: 'Disabled',
        showbuttons: 'bottom',
        tpl: '<div class="checkbox"></div>'
    });


    // Single styled checkbox
    $('#single-styled-checkbox').editable({
        source: {'1': 'Enabled'},
        emptytext: 'Disabled',
        showbuttons: 'bottom',
        tpl: '<div class="checkbox"></div>'
    });
    $('#single-styled-checkbox').on('shown', function(e, editable) {
        editable.input.$input.uniform();
    });


    // Initialize uniform
    $(".styled, .multiselect-container input, .file-input > :file").uniform({
        radioClass: 'choice',
        fileButtonHtml: '<i class="icon-googleplus"></i>'
    });



    //
    // Checklists
    //

    // Unstyled checklist
    $('#unstyled-checklist').editable({
        source: [
            {value: 1, text: 'banana'},
            {value: 2, text: 'peach'},
            {value: 3, text: 'apple'},
            {value: 4, text: 'watermelon'},
            {value: 5, text: 'orange'}
        ],
        showbuttons: 'bottom',
        tpl: '<div class="checkbox"></div>'
    });

    // Styled checklist
    $('#styled-checklist').editable({
        source: [
            {value: 1, text: 'banana'},
            {value: 2, text: 'peach'},
            {value: 3, text: 'apple'},
            {value: 4, text: 'watermelon'},
            {value: 5, text: 'orange'}
        ],
        showbuttons: 'bottom',
        tpl: '<div class="checkbox"></div>'
    });

    // Update uniform dynamically
    $('#styled-checklist').on('shown', function(e, editable) {
        editable.input.$input.uniform();
    });



    //
    // Single switchery toggle
    //

    // Initialize plugin
    $('#switchery-checkbox').editable({
        source: {'1': 'Enabled'},
        emptytext: 'Disabled',
        showbuttons: 'bottom',
        tpl: '<div class="checkbox checkbox-switchery switchery-xs"></div>'
    });

    // Initialize plugin  and insert in editable popup on show
    $('#switchery-checkbox').on('shown', function (e, editable) {
        editable.input.$input.addClass('switcher-single');

        var elem = document.querySelector('.switcher-single');
        var init = new Switchery(elem);
    });



    //
    // Switchery checklist
    //

    // Initialize plugin
    $('#switchery-checklist').editable({
        source: [
            {value: 1, text: 'banana'},
            {value: 2, text: 'peach'},
            {value: 3, text: 'apple'},
            {value: 4, text: 'watermelon'},
            {value: 5, text: 'orange'}
        ],
        showbuttons: 'bottom',
        tpl: '<div class="checkbox checkbox-switchery switchery-xs"></div>'
    });

    // Initialize plugin  and insert in editable popup on show
    $('#switchery-checklist').on('shown', function(e, editable) {
        editable.input.$input.addClass('switcher');

        var elems = Array.prototype.slice.call(document.querySelectorAll('.switcher'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    });



    //
    // Unordered checkbox list
    //

    // Initialize editable
    $.mockjax({
        url: '/checkbox-unordered-list',
        status: 200,
        responseTime: 200
    });

    // Add data
    $.mockjax({
        url: '/source-ul',
        status: 200,
        responseTime: 400,
        response: function(settings) {
            this.responseText = [
                {value: 0, text: 'Guest'},
                {value: 1, text: 'Service'},
                {value: 2, text: 'Customer'},
                {value: 3, text: 'Operator'},
                {value: 4, text: 'Support'},
                {value: 5, text: 'Admin'}
            ];
        }        
    });

    // Initialize plugin
    $('#checkbox-unordered-list').editable({
        source: '/source-ul',
        url: '/checkbox-unordered-list',     
        display: function(value, sourceData) {
            var $el = $('#list'),
            checked, html = '';
            if(!value) {
                $el.empty();
                return;
            }            

            checked = $.grep(sourceData, function(o){
                return $.grep(value, function(v){ 
                    return v == o.value; 
                }).length;
            });

            $.each(checked, function(i, v) { 
                html+= '<li>'+$.fn.editableutils.escape(v.text)+'</li>';
            });

            if(html) html = '<ul class="list-inline" style="margin-top: 10px;">'+html+'</ul>';
            $el.html(html);
        },
        showbuttons: 'bottom',
        tpl: '<div class="checkbox"></div>'
    });

    // Initialize plugin  and insert in editable popup on show
    $('#checkbox-unordered-list').on('shown', function(e, editable) {
        editable.input.$input.uniform();
    });




    // Advanced initialization
    // ------------------------------

    //
    // Autotext option
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/autotext-url',
        status: 200,
        responseTime: 200
    });

    // Add data
    $.mockjax({
        url: '/groups',
        status: 200,
        responseTime: 400,
        response: function(settings) {
            this.responseText = [
                {value: 0, text: 'Guest'},
                {value: 1, text: 'Service'},
                {value: 2, text: 'Customer'},
                {value: 3, text: 'Operator'},
                {value: 4, text: 'Support'},
                {value: 5, text: 'Admin'}
            ];
        }        
    });

    // Initialize
    $('#editable-autotext').editable({
        url: '/autotext-url'
    });



    //
    // PUT method submit
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/editable-put-submit',
        responseTime: 200,
        response: function(settings) {
            console.log(settings);
        }
    }); 

    // Initialize
    $('#editable-put-submit').editable({
        url: '/editable-put-submit',    
        ajaxOptions: {
            type: 'put'
        }        
    });



    //
    // Render server response
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/editable-render-response',
        responseTime: 400,
        response: function(settings) {
            this.responseText = 'New value: <b>'+settings.data.value+'</b>';
        }
    });

    // Initialize
    $('#editable-render-response').editable({
        url: '/editable-render-response',    
        display: function(value, response) {
            $(this).html(response);
        }
    });



    //
    // Process JSON response
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/editable-json-response',
        responseTime: 200,
        response: function(settings) {
            if(settings.data.value) {
                this.responseText = '{"success": true}';
            }
            else {
                this.responseText = '{"success": false, "msg": "required"}';
            }
        }
    }); 

    // Initialize
    $('#editable-json-response').editable({
        url: '/editable-json-response',    
        ajaxOptions: {
            dataType: 'json'
        },
        success: function(response, newValue) {
            if(!response) {
                return "Unknown error!";
            }          

            if(response.success === false) {
                return response.msg;
            }
        }
    });



    //
    // Input types
    //

    // Simulate ajax requests
    $.mockjax({
        url: '/post-fields',
        response: function(settings) {
            log(settings, this);
        }
    });

    // Password
    $('#type-password').editable({
        url: '/post-fields',
        title: 'Enter your password'
    });

    // Email
    $('#type-email').editable({
        url: '/post-fields',
        title: 'Enter your email'
    });

    // Url
    $('#type-url').editable({
        url: '/post-fields',
        title: 'Enter URL'
    });

    // Tel
    $('#type-tel').editable({
        url: '/post-fields',
        title: 'Enter phone number'
    });

    // Number
    $('#type-number').editable({
        url: '/post-fields',
        title: 'Enter any number'
    });

    // Range
    $('#type-range').editable({
        url: '/post-fields',
        title: 'Number range'
    });

    // Time
    $('#type-time').editable({
        url: '/post-fields',
        title: 'Time'
    });




    // Plugins and extensions
    // ------------------------------

    //
    // Tag inputs
    //

    // Text tags input
    $('#input-tags-text').editable({
        showbuttons: 'bottom',
        clear: false,
        display: function(value) {
            $(this).html(value); 
            $(this).each(function() { 
                var text = $(this).text().split(',');
                for( var i = 0, len = text.length; i < len; i++ ) { 
                    text[i] = '<span>' + text[i] + '</span>'; 
                } 
                $(this).html(text.join(', '));
            });
        }
    });
    $('#input-tags-text').on('shown', function(e, editable) {
        editable.input.$input.tagsinput({
            maxTags: 5
        });
    });


    // Label tags input
    $('#input-tags-labels').editable({
        showbuttons: 'bottom',
        clear: false,
        display: function(value) {
            $(this).html(value); 
            $(this).each(function() { 
                var text = $(this).text().split(',');
                for( var i = 0, len = text.length; i < len; i++ ) { 
                    text[i] = '<span class="label label-primary">' + text[i] + '</span>'; 
                } 
                $(this).html(text.join(' '));
            });
        }
    });
    $('#input-tags-labels').on('shown', function(e, editable) {
        editable.input.$input.tagsinput({
            maxTags: 5
        });
    });



    //
    // Typeahead
    //

    // Initialize editable
    $('#editable-typeahead').editable({
        value: 'California'
    }); 

    // Typeahead
    $('#editable-typeahead').on('shown', function(e, editable) {
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

        // Initialize typeahead
        editable.input.$input.typeahead(
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
    });



    //
    // Touchspin spinners
    //

    // Basic
    $('#input-touchspin-basic').editable({
        clear: false
    });
    $('#input-touchspin-basic').on('shown', function(e, editable) {
        editable.input.$input.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2
        }).parent().parent().addClass('editable-touchspin');
    });


    // Advanced
    $('#input-touchspin-advanced').editable({
        clear: false
    });
    $('#input-touchspin-advanced').on('shown', function(e, editable) {
        editable.input.$input.TouchSpin({
            prefix: '<i class="icon-accessibility"></i>',
            postfix: '<i class="icon-paragraph-justify2"></i>'
        }).parent().parent().addClass('editable-touchspin');
    });



    //
    // Input mask
    //

    // Initialize editable
    $('#input-mask').editable({
        emptytext: 'Your credit card number'
    });

    // Initialize plugin  and insert in editable popup on show
    $('#input-mask').on('shown', function(e, editable) {
        editable.input.$input.inputmask({
            mask: '9999-9999-9999-9999'
        });
        $('<span class="help-block">9999-9999-9999-9999</span>').insertAfter(editable.input.$input);
    });



    //
    // Input formatter
    //

    // Initialize editable
    $('#input-formatter').editable({
        emptytext: 'Your birth date'
    });

    // Initialize plugin  and insert in editable popup on show
    $('#input-formatter').on('shown', function(e, editable) {
        editable.input.$input.formatter({
            pattern: '{{99}}/{{99}}/{{9999}}'
        });
        $('<span class="help-block">99/99/9999</span>').insertAfter(editable.input.$input);
    });




    // Select2 select
    // ------------------------------

    // Data
    var select2_countries = [];
    $.each(
        {
            "BD": "Bangladesh",
            "BE": "Belgium",
            "BF": "Burkina Faso",
            "BG": "Bulgaria",
            "BA": "Bosnia and Herzegovina",
            "BB": "Barbados",
            "WF": "Wallis and Futuna",
            "BL": "Saint Bartelemey",
            "BM": "Bermuda",
            "BN": "Brunei Darussalam",
            "BO": "Bolivia",
            "BH": "Bahrain",
            "BI": "Burundi",
            "BJ": "Benin",
            "BT": "Bhutan",
            "JM": "Jamaica",
            "BV": "Bouvet Island",
            "BW": "Botswana",
            "WS": "Samoa",
            "BR": "Brazil",
            "BS": "Bahamas",
            "JE": "Jersey",
            "BY": "Belarus",
            "O1": "Other Country",
            "LV": "Latvia",
            "RW": "Rwanda",
            "RS": "Serbia",
            "TL": "Timor-Leste",
            "RE": "Reunion",
            "LU": "Luxembourg",
            "TJ": "Tajikistan",
            "RO": "Romania",
            "PG": "Papua New Guinea",
            "GW": "Guinea-Bissau",
            "GU": "Guam",
            "GT": "Guatemala",
            "GS": "South Georgia and the South Sandwich Islands",
            "GR": "Greece",
        }, function(k, v) {
            select2_countries.push({id: k, text: v});
        }
    );


    // Single select
    $('#select2-single').editable({
        source: select2_countries,
        select2: {
            width: 200,
            placeholder: 'Select country',
            allowClear: true
        } 
    }); 


    // Multiple select
    $('#select2-multiple').editable({
        showbuttons: 'bottom',
        select2: {
            width: 300,
            tags: ['html', 'javascript', 'css', 'ajax'],
            tokenSeparators: [",", " "],
            multiple: true,
            placeholder: 'Select something'
        }
    }); 


    //
    // Remote source
    //

    // Initialize
    $('#select2-single-remote').editable({
        select2: {
            width: '300',
            placeholder: 'Select Country',
            minimumInputLength: 1,
            ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
                url: "http://api.rottentomatoes.com/api/public/v1.0/movies.json",
                dataType: 'jsonp',
                data: function (term, page) {
                    return {
                        q: term, // search term
                        page_limit: 10,
                        apikey: "ju6z9mjyajq2djue3gbvv26t" // please do not use so this example keeps working
                    };
                },
                results: function (data, page) { // parse the results into the format expected by Select2.

                    // since we are using custom formatting functions we do not need to alter remote JSON data
                    return {results: data.movies};
                }
            },
            initSelection: function(element, callback) {

                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
                var id=$(element).val();
                if (id!=="") {
                    $.ajax("http://api.rottentomatoes.com/api/public/v1.0/movies/"+id+".json", {
                        data: {
                            apikey: "ju6z9mjyajq2djue3gbvv26t"
                        },
                        dataType: "jsonp"
                    }).done(function(data) { callback(data); });
                }
            },
            formatResult: movieFormatResult, // omitted for brevity, see the source of this page
            formatSelection: movieFormatSelection,  // omitted for brevity, see the source of this page
            dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
            escapeMarkup: function (m) { return m; } // we do not want to escape markup since we are displaying html in results
        } 
    });

    // Format results
    function movieFormatResult(movie) {
        var markup = "<table class='movie-result'><tr>";
        if (movie.posters !== undefined && movie.posters.thumbnail !== undefined) {
            markup += "<td class='movie-image'><img src='" + movie.posters.thumbnail + "'/></td>";
        }
        markup += "<td class='movie-info'><div class='movie-title'>" + movie.title + "</div>";
        if (movie.critics_consensus !== undefined) {
            markup += "<div class='movie-synopsis'>" + movie.critics_consensus + "</div>";
        }
        else if (movie.synopsis !== undefined) {
            markup += "<div class='movie-synopsis'>" + movie.synopsis + "</div>";
        }
        markup += "</td></tr></table>";
        return markup;
    }

    // Format selection
    function movieFormatSelection(movie) {
        return movie.title;
    }

});
