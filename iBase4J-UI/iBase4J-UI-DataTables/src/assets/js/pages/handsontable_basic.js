/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_basic.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic configuration
    // ------------------------------

    // Add data for all examples
    var car_data = [
        {car: "Mercedes", model: "GL500", year: 2009, color: "blue", price: 32500},
        {car: "Chevrolet", model: "Camaro", year: 2012, color: "red", price: 42400},
        {car: "Dodge", model: "Charger", year: 2011, color: "white", price: 24900},
        {car: "Hummer", model: "H3", year: 2014, color: "black", price: 54000},
        {car: "Chevrolet", model: "Tahoe", year: 2009, color: "purple", price: 29300},
        {car: "Toyota", model: "Land Cruiser", year: 2007, color: "lime", price: 54500},
        {car: "Nissan", model: "GTR", year: 2009, color: "cyan", price: 44900},
        {car: "Porsche", model: "Cayenne", year: 2012, color: "yellow", price: 35000},
        {car: "Volkswagen", model: "Touareg", year: 2010, color: "crimson", price: 41000},
        {car: "BMW", model: "X5", year: 2010, color: "orange", price: 48800},
        {car: "Audi", model: "Q7", year: 2009, color: "green", price: 21000},
        {car: "Cadillac", model: "Escalade", year: 2012, color: "silver", price: 63900}
    ];

    // Define element
    var hot_basic = document.getElementById('hot_basic');

    // Initialize with options
    var hot_basic_init = new Handsontable(hot_basic, {
        data: car_data,
        stretchH: 'all'
    });



    // Column headers
    // ------------------------------

    // Define element
    var hot_col_headers = document.getElementById('hot_col_headers');

    // Initialize with options
    var hot_col_headers_init = new Handsontable(hot_col_headers, {
        data: car_data,
        colHeaders: true,
        stretchH: 'all'
    });



    // Row headers
    // ------------------------------

    // Define element
    var hot_row_headers = document.getElementById('hot_row_headers');

    // Initialize with options
    var hot_row_headers_init = new Handsontable(hot_row_headers, {
        data: car_data,
        colHeaders: true,
        rowHeaders: true,
        stretchH: 'all'
    });



    // Custom headers text
    // ------------------------------

    // Define element
    var hot_headers = document.getElementById('hot_headers');

    // Initialize with options
    var hot_headers_init = new Handsontable(hot_headers, {
        data: car_data,
        rowHeaders: true,
        colHeaders: ['Brand', 'Model', 'Year', 'Color', 'Price'],
        stretchH: 'all'
    });


    // Comments
    // ------------------------------

    // Define element
    var hot_comments = document.getElementById('hot_comments');

    // Init with options
    var hot_comments_init = new Handsontable(hot_comments, {
        data: car_data,
        rowHeaders: true,
        colHeaders: true,
        stretchH: 'all',
        comments: true,
        cell: [
            {row: 1, col: 1, comment: 'The most nice looking muscle car'},
            {row: 2, col: 2, comment: 'Another comment'}
        ]
    });



    // Custom borders
    // ------------------------------

    // Add data
    var hot_borders_data = Handsontable.helper.createSpreadsheetData(40, 10);

    // Define element
    var hot_borders = document.getElementById('hot_borders');

    // Init with options
    hot_borders_init = Handsontable(hot_borders, {
        data: hot_borders_data,
        rowHeaders: true,
        stretchH: 'all',
        fixedColumnsLeft: 2,
        fixedRowsTop: 2,
        colHeaders: true,
        customBorders: [
            {
                range: {
                    from: {
                        row: 1,
                        col: 1
                    },
                    to: {
                        row: 3,
                        col: 4
                    }
                },
                top: {
                    width: 2,
                    color: '#5292F7'
                },
                left: {
                    width: 2,
                    color: 'orange'
                },
                bottom: {
                    width: 2,
                    color: 'red'
                },
                right: {
                    width: 2,
                    color: 'magenta'
                }
            },
            {
                row: 2,
                col: 2,
                left: {
                    width: 2,
                    color: 'red'
                },
                right: {
                    width: 1,
                    color: 'green'
                }
            }
        ]
    });

});
