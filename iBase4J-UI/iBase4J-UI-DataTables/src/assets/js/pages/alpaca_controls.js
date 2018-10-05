/* ------------------------------------------------------------------------------
*
*  # Alpaca - Basic inputs
*
*  Specific JS code additions for alpaca_controls.html page
*
*  Version: 1.0
*  Latest update: Mar 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Checkboxes
    // ------------------------------

    // Checkbox with label
    $("#alpaca-checkbox-label").alpaca({
        "data": true,
        "options": {
            "label": "Question:",
            "rightLabel": "Do you like Alpaca?"
        }
    });


    // Display only mode
    $("#alpaca-checkbox-static").alpaca({
        "data": false,
        "view": "bootstrap-display",
        "options": {
            "label": "Registered?"
        }
    });


    // Styled checkbox
    $("#alpaca-checkbox-styled").alpaca({
        "data": true,
        "options": {
            "label": "Question:",
            "rightLabel": "Do you like Alpaca?",
            "fieldClass": "checkbox-styled"
        },
        "postRender": function(control) {
            $('.checkbox-styled').find('input[type=checkbox]').uniform();
        }
    });


    // Disabled checkbox
    $("#alpaca-checkbox-styled-disabled").alpaca({
        "data": true,
        "options": {
            "label": "Question:",
            "rightLabel": "Do you like Alpaca?",
            "fieldClass": "checkbox-styled-disabled",
            "disabled": true
        },
        "postRender": function(control) {
            $('.checkbox-styled-disabled').find('input[type=checkbox]').uniform();
        }
    });


    // Switchery toggle
    $("#alpaca-switchery").alpaca({
        "data": true,
        "options": {
            "label": "Question:",
            "rightLabel": "Do you like Alpaca?",
            "fieldClass": "switchery-demo"
        },
        "postRender": function(control) {

            // Init Switchery
            var elems = Array.prototype.slice.call(document.querySelectorAll('.switchery-demo input[type=checkbox]'));
            elems.forEach(function(html) {
              var switchery = new Switchery(html);
            });

            // Add proper spacing
            $('.switchery-demo').find('.checkbox').addClass('checkbox-switchery');
        }
    });


    // Switchery toggle
    $("#alpaca-switchery-disabled").alpaca({
        "data": true,
        "options": {
            "label": "Question:",
            "rightLabel": "Do you like Alpaca?",
            "fieldClass": "switchery-disabled-demo",
            "disabled": true
        },
        "postRender": function(control) {

            // Init Switchery
            var elems = Array.prototype.slice.call(document.querySelectorAll('.switchery-disabled-demo input[type=checkbox]'));
            elems.forEach(function(html) {
              var switchery = new Switchery(html);
            });

            // Add proper spacing
            $('.switchery-disabled-demo').find('.checkbox').addClass('checkbox-switchery');
        }
    });


    // Basic checkbox list
    $("#alpaca-checkbox-list").alpaca({
        "data": ["sandwich", "cookie", "drink"],
        "schema": {
            "type": "array",
            "enum": ["sandwich", "chips", "cookie", "drink"]
        },
        "options": {
            "type": "checkbox",
            "label": "What would you like with your order?",
            "optionLabels": ["A Sandwich", "Potato Chips", "A Cookie", "Soft Drink"]
        }
    });


    // Styled checkbox list
    $("#alpaca-checkbox-list-styled").alpaca({
        "data": ["sandwich", "cookie", "drink"],
        "schema": {
            "type": "array",
            "enum": ["sandwich", "chips", "cookie", "drink"]
        },
        "options": {
            "type": "checkbox",
            "label": "What would you like with your order?",
            "optionLabels": ["A Sandwich", "Potato Chips", "A Cookie", "Soft Drink"],
            "fieldClass": "checkbox-styled-list"
        },
        "postRender": function(control) {
            $('.checkbox-styled-list').find('input[type=checkbox]').uniform();
        }
    });



    // Radios
    // ------------------------------

    // Basic radios
    $("#alpaca-radio-basic").alpaca({
        "data": "green",
        "options": {
            "type": "radio",
            "label": "Favorite Color",
            "helper": "Pick your favorite color",
            "optionLabels": {
                "red": "Red",
                "green": "Green",
                "blue": "Blue",
                "white": "White",
                "black": "Black"
            }
        },
        "schema": {
            "required": true,
            "enum": ["red", "green", "blue", "white", "black"]
        }
    });


    // Disabled mode
    $("#alpaca-radio-basic-disabled").alpaca({
        "data": "Jimi Hendrix",
        "schema": {
            "enum": ["Jimi Hendrix", "Mark Knopfler", "Joe Satriani", "Eddie Van Halen", "Orianthi"]
        },
        "options": {
            "type": "radio",
            "label": "Who is your favorite guitarist?",
            "vertical": true,
            "disabled": true
        }
    });


    // Styled radios
    $("#alpaca-radio-styled").alpaca({
        "data": "Jimi Hendrix",
        "schema": {
            "enum": ["Jimi Hendrix", "Mark Knopfler", "Joe Satriani", "Eddie Van Halen", "Orianthi"]
        },
        "options": {
            "type": "radio",
            "label": "Who is your favorite guitarist?",
            "fieldClass": "radio-styled-demo",
            "vertical": true
        },
        "postRender": function(control) {
            $('.radio-styled-demo').find('input[type=radio]').uniform({
                radioClass: 'choice'
            });
        }
    });


    // Disabled mode
    $("#alpaca-radio-styled-disabled").alpaca({
        "data": "Jimi Hendrix",
        "schema": {
            "enum": ["Jimi Hendrix", "Mark Knopfler", "Joe Satriani", "Eddie Van Halen", "Orianthi"]
        },
        "options": {
            "type": "radio",
            "label": "Who is your favorite guitarist?",
            "vertical": true,
            "fieldClass": "radio-styled-disabled-demo",
            "disabled": true
        },
        "postRender": function(control) {
            $('.radio-styled-disabled-demo').find('input[type=radio]').uniform({
                radioClass: 'choice'
            });
        }
    });


    // Required radios
    $("#alpaca-radio-required").alpaca({
        "data": "Coffee2",
        "options": {
            "label": "Ice cream",
            "helper": "Guess my favorite ice cream?",
            "optionLabels": ["Vanilla Flavor", "Chocolate Flavor", "Coffee Flavor"]
        },
        "schema": {
            "required": true,
            "enum": ["Vanilla", "Chocolate", "Coffee"]
        }
    });


    // Options
    $("#alpaca-radio-options").alpaca({
        "data": "Jimi Hendrix",
        "schema": {
            "enum": ["Jimi Hendrix", "Mark Knopfler", "Joe Satriani", "Eddie Van Halen", "Orianthi"]
        },
        "options": {
            "type": "radio",
            "label": "Who is your favorite guitarist?",
            "removeDefaultNone": true,
            "vertical": true
        }
    });



    // Tokenfield
    // ------------------------------

    // Basic setup
    $("#alpaca-tokenfield").alpaca({
        "schema": {
            "title": "Character Names",
            "type": "string"
        },
        "options": {
            "type": "token",
            "focus": false,
            "tokenfield": {
                "autocomplete": {
                    "source": ["marty", "doc", "george", "biff", "lorraine", "mr. strickland"],
                    "delay": 100
                },
                "showAutocompleteOnFocus": true
            }
        },
        "data": "marty,doc,george,biff"
    });


    // Display only mode
    $("#alpaca-tokenfield-static").alpaca({
        "schema": {
            "title": "Character Names",
            "type": "string"
        },
        "options": {
            "type": "token",
            "focus": false,
            "tokenfield": {
                "autocomplete": {
                    "source": ["marty", "doc", "george", "biff", "lorraine", "mr. strickland"],
                    "delay": 100
                },
                "showAutocompleteOnFocus": true
            }
        },
        "data": "marty,doc,george,biff",
        "view": "bootstrap-display"
    });

});
