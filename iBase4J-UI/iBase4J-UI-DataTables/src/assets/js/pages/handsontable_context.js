/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_context.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic configuration
    // ------------------------------

    // Add sample data for all examples
    var car_data = [
        {car: "Mercedes", model: "GL500", year: "11/01/2015", price: 32500, share: 0.64},
        {car: "Chevrolet", model: "Camaro", year: "11/02/2015", price: 42400, share: 0.37},
        {car: "Dodge", model: "Charger", year: "11/03/2015", price: 24900, share: 0.33},
        {car: "Hummer", model: "H3", year: "11/04/2015", price: 54000, share: 0.15},
        {car: "Chevrolet", model: "Tahoe", year: "11/05/2015", price: 29300, share: 0.27},
        {car: "Toyota", model: "Land Cruiser", year: "11/06/2015", price: 54500, share: 0.43},
        {car: "Nissan", model: "GTR", year: "11/07/2015", price: 44900, share: 0.35},
        {car: "Porsche", model: "Cayenne", year: "11/08/2015", price: 35000, share: 0.63},
        {car: "Volkswagen", model: "Touareg", year: "11/09/2015", price: 41000, share: 0.15},
        {car: "BMW", model: "X5", year: "11/10/2015", price: 48800, share: 0.35},
        {car: "Audi", model: "Q7", year: "11/11/2015", price: 21000, share: 0.53},
        {car: "Cadillac", model: "Escalade", year: "11/12/2015", price: 63900, share: 0.38},
        {car: "Suzuki", model: "SX4", year: "11/13/2015", price: 23700, share: 0.8},
        {car: "Opel", model: "Insignia", year: "11/14/2015", price: 43900, share: 0.27},
        {car: "Ford", model: "Explorer", year: "11/15/2015", price: 68900, share: 0.17}
    ];

    // Define element
    var hot_context_basic = document.getElementById('hot_context_basic');

    // Initialize with options
    var hot_context_basic_init = new Handsontable(hot_context_basic, {
        data: car_data,
        rowHeaders: true,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        contextMenu: true,
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'date',
                dateFormat: 'MM/DD/YYYY'
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%',
                width: 50
            }
        ]
    });



    // Specific options
    // ------------------------------

    // Define element
    var hot_context_specific = document.getElementById('hot_context_specific');

    // Initialize with options
    var hot_context_specific_init = new Handsontable(hot_context_specific, {
        data: car_data,
        rowHeaders: true,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        contextMenu: ['row_above', 'row_below', 'remove_row'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'date',
                dateFormat: 'MM/DD/YYYY'
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%',
                width: 50
            }
        ]
    });



    // Custom configuration
    // ------------------------------

    // Define element
    var hot_context_custom = document.getElementById('hot_context_custom')

    // Initialize with options
    var hot_context_custom_init = new Handsontable(hot_context_custom, {
        data: car_data,
        rowHeaders: true,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'date',
                dateFormat: 'MM/DD/YYYY'
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%',
                width: 50
            }
        ]
    });

    // Update settings for context menu
    hot_context_custom_init.updateSettings({
        contextMenu: {
            callback: function (key, options) {
                if (key === 'about') {
                    setTimeout(function () {

                        // timeout is used to make sure the menu collapsed before alert is shown
                        alert("This is a context menu with default and custom options mixed");
                    }, 100);
                }
            },
            items: {
                "row_above": {
                    disabled: function () {

                        // if first row, disable this option
                        return hot_context_custom_init.getSelected()[0] === 0;
                    }
                },
                "row_below": {},
                "hsep1": "---------",
                "remove_row": {
                    name: 'Remove this row, ok?',
                    disabled: function () {

                        // if first row, disable this option
                        return hot_context_custom_init.getSelected()[0] === 0
                    }
                },
                "hsep2": "---------",
                "about": {name: 'About this menu'}
            }
        }
    });



    // Copy-paste configuration
    // ------------------------------

    // Define element
    var hot_context_copy = document.getElementById('hot_context_copy');

    // Initialize with options
    var hot_context_copy_init = new Handsontable(hot_context_copy, {
        data: car_data,
        rowHeaders: true,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'date',
                dateFormat: 'MM/DD/YYYY'
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%',
                width: 50
            }
        ],
        contextMenu: ['row_above', 'row_below', '---------', 'remove_row', 'copy', 'paste'],
        contextMenuCopyPaste: {
            swfPath: 'assets/swf/handsontable/zero_clipboard.swf'
        }
    });

});
