/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_types.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Numeric type
    // ------------------------------

    // Add data for multiple examples
    var car_data = [
        {car: "Mercedes G 500", year: 2009, price_usd: 32500, price_eur: 32500, share: 0.64},
        {car: "Chevrolet Camaro", year: 2011, price_usd: 42400, price_eur: 42400, share: 0.37},
        {car: "Dodge Charger", year: 2010, price_usd: 24900, price_eur: 24900, share: 0.33},
        {car: "Hummer H3", year: 2006, price_usd: 54000, price_eur: 54000, share: 0.15},
        {car: "Chevrolet Tahoe", year: 2012, price_usd: 29300, price_eur: 29300, share: 0.27},
        {car: "Toyota Land Cruiser", year: 2011, price_usd: 54500, price_eur: 54500, share: 0.43},
        {car: "Nissan GTR", year: 2010, price_usd: 44900, price_eur: 44900, share: 0.35},
        {car: "Porsche Cayenne", year: 2013, price_usd: 35000, price_eur: 35000, share: 0.63},
        {car: "Volkswagen Touareg", year: 2012, price_usd: 41000, price_eur: 41000, share: 0.15},
        {car: "BMW X5", year: 2014, price_usd: 48800, price_eur: 48800, share: 0.35},
        {car: "Audi Q7", year: 2013, price_usd: 21000, price_eur: 21000, share: 0.53},
        {car: "Cadillac Escalade", year: 2009, price_usd: 63900, price_eur: 63900, share: 0.38}
    ];

    // Define element
    var hot_numeric = document.getElementById('hot_numeric');

    // Initialize with options
    var hot_numeric_init = new Handsontable(hot_numeric, {
        data: car_data,
        stretchH: 'all',
        colHeaders: ['Car', 'Year', 'Price ($)', 'Price (€)', 'Market share'],
        columnSorting : true,
        sortIndicator: true,
        columns: [
            {
                data: 'car' // 1nd column is simple text, no special options here
            },
            {
                data: 'year',
                type: 'numeric',
                className: 'htLeft'
            },
            {
                data: 'price_usd',
                type: 'numeric',
                className: 'htLeft',
                format: '$0,0.00'
            },
            {
                data: 'price_eur',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $',
                language: 'de-DE' // i18n: use this for EUR (German)
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ]
    });



    // Date type
    // ------------------------------

    // Add sample data
    var hot_date_data = [
        {car: "Mercedes", model: "GL500", date: "11/01/2015", price: 32500, share: 0.64},
        {car: "Chevrolet", model: "Camaro", date: "11/02/2015", price: 42400, share: 0.37},
        {car: "Dodge", model: "Charger", date: "11/03/2015", price: 24900, share: 0.33},
        {car: "Hummer", model: "H3", date: "11/04/2015", price: 54000, share: 0.15},
        {car: "Chevrolet", model: "Tahoe", date: "11/05/2015", price: 29300, share: 0.27},
        {car: "Toyota", model: "Land Cruiser", date: "11/06/2015", price: 54500, share: 0.43},
        {car: "Nissan", model: "GTR", date: "11/07/2015", price: 44900, share: 0.35},
        {car: "Porsche", model: "Cayenne", date: "11/08/2015", price: 35000, share: 0.63},
        {car: "Volkswagen", model: "Touareg", date: "11/09/2015", price: 41000, share: 0.15},
        {car: "BMW", model: "X5", date: "11/10/2015", price: 48800, share: 0.35},
        {car: "Audi", model: "Q7", date: "11/11/2015", price: 21000, share: 0.53},
        {car: "Cadillac", model: "Escalade", date: "11/12/2015", price: 63900, share: 0.38}
    ];

    // Define element
    var hot_date = document.getElementById('hot_date');

    // Initialize with options
    var hot_date_init = new Handsontable(hot_date, {
        data: hot_date_data,
        stretchH: 'all',
        colHeaders: ['Car', 'Model', 'Date', 'Price (€)', 'Market share'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'date',
                type: 'date',
                dateFormat: 'MM/DD/YYYY',
                correctFormat: true
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $',
                language: 'de-DE'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ]
    });



    // Select type
    // ------------------------------

    // Define element
    var hot_select = document.getElementById("hot_select");

    // Initialize with options
    var hot_select_init = new Handsontable(hot_select, {
        data: car_data,
        colHeaders: true,
        stretchH: 'all',
        colHeaders: ['Car', 'Year', 'Price ($)', 'Market share'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'year',
                editor: 'select',
                selectOptions: ['2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
            },
            {
                data: 'price_usd',
                type: 'numeric',
                className: 'htLeft',
                format: '$0,0.00'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ]
    });



    // Dropdown type
    // ------------------------------

    // Define element
    var hot_dropdown = document.getElementById('hot_dropdown');

    // Initialize with options
    var hot_dropdown_init = new Handsontable(hot_dropdown, {
        data: car_data,
        colHeaders: ['Car', 'Year', 'Chassis color', 'Bumper color'],
        stretchH: 'all',
        columns: [
            {
                data: 'car'
            },
            {
                data: 'year',
                type: 'dropdown',
                source: ['2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016']
            },
            {
                data: 'price_usd',
                type: 'numeric',
                className: 'htLeft',
                format: '$0,0.00'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ]
    });



    // Handsontable type
    // ------------------------------

    // Add sample data
    var manufacturerData = [
        {name: 'BMW', country: 'Germany', owner: 'Bayerische Motoren Werke AG'},
        {name: 'Chrysler', country: 'USA', owner: 'Chrysler Group LLC'},
        {name: 'Nissan', country: 'Japan', owner: 'Nissan Motor Company Ltd'},
        {name: 'Suzuki', country: 'Japan', owner: 'Suzuki Motor Corporation'},
        {name: 'Toyota', country: 'Japan', owner: 'Toyota Motor Corporation'},
        {name: 'Volvo', country: 'Sweden', owner: 'Zhejiang Geely Holding Group'}
    ];

    // Years selection
    var years = ['2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016'],
        year,
        yearData = [];
    while (year = years.shift()) {
        yearData.push([
            [year]
        ]);
    }

    // Define element
    var hot_handsontable = document.getElementById('hot_handsontable');

    // Initialize with options
    var hot_handsontable_init = new Handsontable(hot_handsontable, {
        data: car_data,
        stretchH: 'all',
        colHeaders: ['Car', 'Year', 'Price ($)', 'Market share'],
        columns: [
            {
                data: 'car',
                type: 'handsontable',
                handsontable: {
                    colHeaders: ['Marque', 'Country', 'Parent company'],
                    stretchH: 'all',
                    data: manufacturerData
                }
            },
            {
                data: 'year',
                type: 'handsontable',
                handsontable: {
                    colHeaders: false,
                    stretchH: 'all',
                    data: yearData
                }
            },
            {
                data: 'price_usd',
                type: 'numeric',
                className: 'htLeft',
                format: '$0,0.00'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ]
    });

});
