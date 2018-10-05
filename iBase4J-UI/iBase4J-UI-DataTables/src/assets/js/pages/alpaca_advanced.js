/* ------------------------------------------------------------------------------
*
*  # Alpaca - Basic inputs
*
*  Specific JS code additions for alpaca_advanced.html page
*
*  Version: 1.0
*  Latest update: Mar 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {



    // Option trees
    // ------------------------------

    // Option tree field
    $("#alpaca-option-tree").alpaca({
        "schema": {
            "type": "number",
            "title": "What number would like for your sports jersey?"
        },
        "options": {
            "type": "optiontree",
            "tree": {
                "selectors": {
                    "sport": {
                        "schema": {
                            "type": "string"
                        },
                        "options": {
                            "type": "select",
                            "noneLabel": "Pick a Sport..."
                        }
                    },
                    "team": {
                        "schema": {
                            "type": "string"
                        },
                        "options": {
                            "type": "select",
                            "noneLabel": "Pick a Team..."
                        }
                    },
                    "player": {
                        "schema": {
                            "type": "string"
                        },
                        "options": {
                            "type": "select",
                            "noneLabel": "Pick a Player..."
                        }
                    }
                },
                "order": ["sport", "team", "player"],
                "data": [{
                    "value": 23,
                    "attributes": {
                        "sport": "Basketball",
                        "team": "Chicago Bulls",
                        "player": "Michael Jordan"
                    }
                }, {
                    "value": 33,
                    "attributes": {
                        "sport": "Basketball",
                        "team": "Chicago Bulls",
                        "player": "Scotty Pippen"
                    }
                }, {
                    "value": 4,
                    "attributes": {
                        "sport": "Football",
                        "team": "Green Bay Packers",
                        "player": "Brett Favre"
                    }
                }, {
                    "value": 19,
                    "attributes": {
                        "sport": "Baseball",
                        "team": "Milwaukee Brewers",
                        "player": "Robin Yount"
                    }
                }, {
                    "value": 99,
                    "attributes": {
                        "sport": "Hockey",
                        "player": "Wayne Gretzky"
                    }
                }],
                "horizontal": true
            },
            "focus": false
        }
    });


    // Using connector
    $("#alpaca-option-tree-connector").alpaca({
        "schemaSource": "../default/assets/demo_data/alpaca/optiontree-custom-schema.json",
        "optionsSource": "../default/assets/demo_data/alpaca/optiontree-custom-options.json",
        "options": {
            "focus": false
        }
    });



    // Input types
    // ------------------------------

    // Lowercase
    $("#alpaca-lowercase").alpaca({
        "data": "Ice cream is wonderful.",
        "schema": {
            "format": "lowercase"
        },
        "options": {
            "focus": false
        }
    });


    // Uppercase
    $("#alpaca-uppercase").alpaca({
        "data": "Ice cream is wonderful.",
        "schema": {
            "format": "uppercase"
        },
        "options": {
            "focus": false
        }
    });


    // Search type
    $("#alpaca-search").alpaca({
        "data": "Where for art thou Romeo?",
        "schema": {
            "type": "string"
        },
        "options": {
            "type": "search",
            "focus": false,
            "label": "Search"
        }
    });


    // Integer type
    $("#alpaca-integer").alpaca({
        "data": 20,
        "options": {
            "type": "integer",
            "label": "Age:",
            "focus": false
        },
        "schema": {
            "minimum": 18,
            "maximum": 25,
            "exclusiveMinimum": true,
            "exclusiveMaximum": true,
            "divisibleBy": 2
        }
    });


    // Password type
    $("#alpaca-password").alpaca({
        "data": "password",
        "schema": {
            "format": "password"
        },
        "options": {
            "focus": false
        }
    });


    // Email type
    $("#alpaca-email").alpaca({
        "data": "support",
        "schema": {
            "format": "email"
        },
        "options": {
            "focus": false
        }
    });


    // IP address type
    $("#alpaca-ipv4").alpaca({
        "data": "100.60",
        "schema": {
            "format": "ip-address"
        },
        "options": {
            "focus": false
        }
    });


    // URL type
    $("#alpaca-url").alpaca({
        "data": "http://www.alpacajs.org",
        "options": {
            "type": "url",
            "focus": false
        },
        "schema": {
            "format": "uri"
        }
    });


    // Currency type
    $("#alpaca-currency").alpaca({
        "options": {
            "type": "currency",
            "focus": false
        }
    });


    // Personal name type
    $("#alpaca-name").alpaca({
        "data": "Oscar Zoroaster Phadrig Isaac Norman Henkel Emmannuel Ambroise Diggs",
        "options": {
            "type": "personalname",
            "focus": false
        }
    });



    // File inputs
    // ------------------------------

    // Basic file input
    $("#alpaca-file").alpaca({
        "data": "",
        "options": {
            "type": "file",
            "label": "Ice Cream Photo:",
            "helper": "Pick your favorite ice cream picture.",
            "focus": false
        },
        "schema": {
            "type": "string",
            "format": "uri"
        }
    });


    // Static mode
    $("#alpaca-file-static").alpaca({
        "data": "/abc.html",
        "options": {
            "type": "file",
            "label": "Ice Cream Photo:",
            "helper": "Pick your favorite ice cream picture.",
            "focus": false
        },
        "schema": {
            "type": "string",
            "format": "uri"
        },
        "view": "bootstrap-display"
    });


    // Styled file input
    $("#alpaca-file-styled").alpaca({
        "data": "",
        "options": {
            "type": "file",
            "label": "Ice Cream Photo:",
            "helper": "Pick your favorite ice cream picture.",
            "id": "file-styled",
            "focus": false
        },
        "schema": {
            "type": "string",
            "format": "uri"
        },
        "postRender": function(control) {
            $("#file-styled").uniform({
                fileButtonClass: 'action btn bg-blue'
            });
        }
    });


    // Disabled file input
    $("#alpaca-file-disabled").alpaca({
        "data": "",
        "options": {
            "type": "file",
            "label": "Ice Cream Photo:",
            "helper": "Pick your favorite ice cream picture.",
            "disabled": true,
            "id": "file-styled-disabled",
            "focus": false
        },
        "schema": {
            "type": "string",
            "format": "uri"
        },
        "postRender": function(control) {
            $("#file-styled-disabled").uniform({
                fileButtonClass: 'action btn bg-blue'
            });
        }
    });



    // Selector helpers
    // ------------------------------

    // Country selector
    $("#alpaca-country").alpaca({
        "options": {
            "type": "country",
            "focus": false
        }
    });


    // Searchable country selector
    $("#alpaca-country-search").alpaca({
        "options": {
            "type": "country",
            "id": "country-search",
            "focus": false
        },
        "postRender": function(control) {
            $('#country-search').select2();
        }
    });


    // State selector
    $("#alpaca-state").alpaca({
        "options": {
            "type": "state",
            "focus": false
        }
    });

    // Searchable state selector
    $("#alpaca-state-search").alpaca({
        "options": {
            "type": "state",
            "id": "state-search",
            "focus": false
        },
        "postRender": function(control) {
            $('#state-search').select2();
        }
    });



    // CKEditor
    // ------------------------------

    // Full featured CKEditor
    $("#alpaca-ckeditor-full").alpaca({
        "data": "Ice cream is a <b>frozen</b> dessert usually made from <i>dairy products</i>, such as milk and cream, and often combined with fruits or other ingredients and flavors.",
        "options": {
            "type": "ckeditor"
        }
    });

});
