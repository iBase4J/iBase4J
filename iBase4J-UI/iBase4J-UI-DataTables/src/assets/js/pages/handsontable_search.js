/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_search.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic search
    // ------------------------------

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
        {car: "Opel", model: "Insignia", year: "11/14/2015", price: 43900, share: 0.27}
    ];

    // Define element
    var hot_search_basic = document.getElementById('hot_search_basic');


    // Initialize with options
    var hot_search_basic_init = new Handsontable(hot_search_basic, {
        data: car_data,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        search: true,
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

    // Define search field
    var hot_search_basic_input = document.getElementById('hot_search_basic_input');

    // Setup matching function
    function onlyExactMatch(queryStr, value) {
        return queryStr.toString() === value.toString();
    }

    // Add event
    Handsontable.Dom.addEvent(hot_search_basic_input, 'keyup', function (event) {
        var queryResult = hot_search_basic_init.search.query(this.value);

        console.log(queryResult);
        hot_search_basic_init.render();
    });



    // Custom search result class
    // ------------------------------

    // Define element
    var hot_search_class = document.getElementById("hot_search_class");

    // Initialize with options
    var hot_search_class_init = new Handsontable(hot_search_class, {
        data: car_data,
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        stretchH: 'all',
        search: {
            searchResultClass: 'bg-blue'
        },
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

    // Define search field
    var hot_search_class_input = document.getElementById('hot_search_class_input');

    // Add event
    Handsontable.Dom.addEvent(hot_search_class_input,'keyup', function (event) {
        var queryResult = hot_search_class_init.search.query(this.value);

        console.log(queryResult);
        hot_search_class_init.render();
    });



    // Custom query method
    // ------------------------------

    // Define element
    var hot_search_query = document.getElementById("hot_search_query");

    // Initialize with options
    var hot_search_query_init = new Handsontable(hot_search_query, {
        data: car_data,
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        stretchH: 'all',
        search: {
            queryMethod: onlyExactMatch
        },
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

    // Define search field
    var hot_search_query_input = document.getElementById('hot_search_query_input');

    // Exact match function
    function onlyExactMatch(queryStr, value) {
        return queryStr.toString() === value.toString();
    };

    // Add event
    Handsontable.Dom.addEvent(hot_search_query_input,'keyup', function(event) {
        var queryResult = hot_search_query_init.search.query(this.value);

        console.log(queryResult);
        hot_search_query_init.render();
    });



    // Custom callback
    // ------------------------------

    // Define element
    var hot_search_callback = document.getElementById("hot_search_callback");

    // Initialize with options
    var hot_search_callback_init = new Handsontable(hot_search_callback, {
        data: car_data,
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Market share'],
        stretchH: 'all',
        search: {
            callback: searchResultCounter
        },
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year'
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


    // Define count element
    var resultCount = document.getElementById('result-count');

    // Search result count
    var searchResultCount = 0;
    function searchResultCounter(instance, row, col, value, result) {
        Handsontable.Search.DEFAULT_CALLBACK.apply(this, arguments);

        if (result) {
            searchResultCount++;
        }
    }


    // Define search field
    var hot_search_callback_input = document.getElementById('hot_search_callback_input');

    // Add event
    Handsontable.Dom.addEvent(hot_search_callback_input, 'keyup', function(event) {
        var queryResult;

        searchResultCount = 0;
        queryResult = hot_search_callback_init.search.query(this.value);
        console.log(queryResult);
        resultCount.innerText = searchResultCount.toString();
        hot_search_callback_init.render();
    });

});
