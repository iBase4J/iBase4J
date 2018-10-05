/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_ac_password.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Autocomplete lazy mode
    // ------------------------------

    // Add sample data
    var hot_date_data = [
        {car: "Mercedes", qty: 100, date: "11/01/2015", color: "yellow", price: 32500},
        {car: "Chevrolet", qty: 100, date: "11/02/2015", color: "red", price: 42400},
        {car: "Dodge", qty: 100, date: "11/03/2015", color: "orange", price: 24900},
        {car: "Hummer", qty: 100, date: "11/04/2015", color: "green", price: 54000},
        {car: "Suzuki", qty: 100, date: "11/05/2015", color: "blue", price: 29300},
        {car: "Toyota", qty: 100, date: "11/06/2015", color: "gray", price: 54500},
        {car: "Nissan", qty: 100, date: "11/07/2015", color: "black", price: 44900},
        {car: "Porsche", qty: 100, date: "11/08/2015", color: "white", price: 35000},
        {car: "Volkswagen", qty: 100, date: "11/09/2015", color: "purple", price: 41000},
        {car: "BMW", qty: 100, date: "11/10/2015", color: "lime", price: 48800},
        {car: "Audi", qty: 100, date: "11/11/2015", color: "olive", price: 21000},
        {car: "Cadillac", qty: 100, date: "11/12/2015", color: "cyan", price: 63900}
    ];

    // Define element
    var hot_ac_lazy = document.getElementById('hot_ac_lazy');

    // Initialize with options
    var hot_ac_lazy_init = new Handsontable(hot_ac_lazy, {
        data: hot_date_data,
        stretchH: 'all',
        colHeaders: ['Car', 'Qty', 'Date', 'Color', 'Price'],
        columns: [
            {
                data: 'car',
                type: 'autocomplete',
                source: ['Mercedes', 'Chevrolet', 'Dodge', 'Hummer', 'Suzuki', 'Toyota', 'Nissan', 'Porsche', 'Volkswagen', 'BMW', 'Audi', 'Cadillac'],
                strict: false
            },
            {
                data: 'qty',
                type: 'numeric',
                className: 'htLeft'
            },
            {
                data: 'date',
                type: 'date',
                dateFormat: 'MM/DD/YYYY',
                correctFormat: true,
                defaultDate: '01/01/1900'
            },
            {
                data: 'color',
                type: 'autocomplete',
                source: ['yellow', 'red', 'orange', 'green', 'blue', 'gray', 'black', 'white', 'purple', 'lime', 'olive', 'cyan'],
                strict: false
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            }
        ]
    });



    // Autocomplete strict mode
    // ------------------------------

    // Define element
    var hot_ac_strict = document.getElementById('hot_ac_strict');

    // Initialize with options
    var hot_ac_strict_init = new Handsontable(hot_ac_strict, {
        data: hot_date_data,
        stretchH: 'all',
        colHeaders: ['Car<br>(allowInvalid true)', 'Qty', 'Date', 'Color<br>(allowInvalid false)', 'Price'],
        columns: [
            {
                data: 'car',
                type: 'autocomplete',
                source: ['Mercedes', 'Chevrolet', 'Dodge', 'Hummer', 'Suzuki', 'Toyota', 'Nissan', 'Porsche', 'Volkswagen', 'BMW', 'Audi', 'Cadillac'],
                strict: true
            },
            {
                data: 'qty',
                className: 'htLeft',
                type: 'numeric'
            },
            {
                data: 'date',
                type: 'date',
                dateFormat: 'MM/DD/YYYY',
                correctFormat: true,
                defaultDate: '01/01/1900'
            },
            {
                data: 'color',
                type: 'autocomplete',
                source: ['yellow', 'red', 'orange', 'green', 'blue', 'gray', 'black', 'white', 'purple', 'lime', 'olive', 'cyan'],
                strict: true,
                allowInvalid: false
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            }
        ]
    });



    // Autocomplete strict mode (AJAX)
    // ------------------------------

    // Define element
    var hot_ac_ajax = document.getElementById('hot_ac_ajax');

    // Initialize with options
    var hot_ac_ajax_init = new Handsontable(hot_ac_ajax, {
        data: hot_date_data,
        stretchH: 'all',
        colHeaders: ['Car', 'Year', 'Chassis color', 'Bumper color'],
        columns: [
            {
                data: 'car',
                type: 'autocomplete',
                source: function (query, process) {
                    $.ajax({
                        url: 'assets/demo_data/handsontable/cars.json',
                        dataType: 'json',
                        data: {
                            query: query
                        },
                        success: function (response) {
                            console.log("response", response);
                            process(response.data);
                        }
                    });
                },
                strict: true
            },
            {
                data: 'qty',
                className: 'htLeft',
                type: 'numeric'
            },
            {
                data: 'date',
                type: 'date',
                dateFormat: 'MM/DD/YYYY',
                correctFormat: true,
                defaultDate: '01/01/1900'
            },
            {
                data: 'color',
                type: 'autocomplete',
                source: function (query, process) {
                    $.ajax({
                        url: 'assets/demo_data/handsontable/colors.json',
                        dataType: 'json',
                        data: {
                            query: query
                        },
                        success: function (response) {
                            console.log("response", response);
                            process(response.data);
                        }
                    });
                },
                strict: true,
                allowInvalid: false
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            }
        ]
    });




    // Password cell type
    // ------------------------------

    // Add sample data used in all password example
    var people = [
        {id: 1, name: {first: 'Joe', last: 'Fabiano'}, email: '<a href="#">joe.fabiano@ex.com</a>', password: 'djeiryt812ewe'},
        {id: 2, name: {first: 'Fred', last: 'Wecler'}, email: '<a href="#">fred.wecler@ex.com</a>', password: 'sdfw78273e2d'},
        {id: 3, name: {first: 'Steve', last: 'Wilson'}, email: '<a href="#">steve.wilson@ex.com</a>', password: 'asdk3uasce'},
        {id: 4, name: {first: 'Maria', last: 'Fernandez'}, email: '<a href="#">m.fernandez@ex.com</a>', password: '12438127we'},
        {id: 5, name: {first: 'Pierre', last: 'Barbault'}, email: '<a href="#">pierre.barbault@ex.com</a>', password: 'aksdiuw3'},
        {id: 6, name: {first: 'Nancy', last: 'Moore'}, email: '<a href="#">nancy.moore@ex.com</a>', password: '18894ksdf'},
        {id: 7, name: {first: 'Barbara', last: 'MacDonald'}, email: '<a href="#">b.macdonald@ex.com</a>', password: 'asd9834r3e'},
        {id: 8, name: {first: 'Wilma', last: 'Williams'}, email: '<a href="#">wilma.williams@ex.com</a>', password: '323908jffwe'},
        {id: 9, name: {first: 'Sasha', last: 'Silver'}, email: '<a href="#">sasha.silver@ex.com</a>', password: 's8884ijs3asdf2'},
        {id: 10, name: {first: 'Don', last: 'Pérignon'}, email: '<a href="#">don.pérignon@ex.com</a>', password: 'e89293234434f'},
        {id: 11, name: {first: 'Aaron', last: 'Kinley'}, email: '<a href="#">aaron.kinley@ex.com</a>', password: '12e3932asdw'}
    ];

    // Define element  
    var hot_password = document.getElementById('hot_password');

    // Initialize with options
    var hot_password_init = new Handsontable(hot_password, {
        data: people,
        stretchH: 'all',
        colHeaders: ['ID', 'First name', 'Last name', 'Email', 'Password'],
        columns: [
            {data: 'id'},
            {data: 'name.first'},
            {data: 'name.last'},
            {data: 'email', renderer: "html"},
            {data: 'password', type: 'password', width: 50}
        ]
    });



    // Fixed hash length
    // ------------------------------

    // Define element
    var hot_password_fixed = document.getElementById('hot_password_fixed');

    // Initialize with options
    var hot_password_fixed_init = new Handsontable(hot_password_fixed, {
        data: people,
        stretchH: 'all',
        colHeaders: ['ID', 'First name', 'Last name', 'Email', 'Password'],
        columns: [
            {data: 'id'},
            {data: 'name.first'},
            {data: 'name.last'},
            {data: 'email', renderer: "html"},
            {data: 'password', type: 'password', hashLength: 8, width: 50}
        ]
    });



    // Custom hash symbol
    // ------------------------------

    // Define element
    var hot_password_custom = document.getElementById('hot_password_custom');

    // initialize with options
    var hot_password_custom_init = new Handsontable(hot_password_custom, {
        data: people,
        stretchH: 'all',
        colHeaders: ['ID', 'First name', 'Last name', 'Email', 'Password'],
        columns: [
            {data: 'id'},
            {data: 'name.first'},
            {data: 'name.last'},
            {data: 'email', renderer: "html"},
            {data: 'password', type: 'password', hashSymbol: '&#9632;', width: 50}
        ]
    });

});
