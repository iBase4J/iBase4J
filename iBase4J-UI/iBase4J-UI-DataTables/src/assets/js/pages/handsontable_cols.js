/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_cols.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Fixed columns and rows
    // ------------------------------

    // Generate sample data
    var hot_fixed_data = Handsontable.helper.createSpreadsheetData(40, 40);

    // Define element
    var hot_fixed = document.getElementById('hot_fixed');

    // Initialize with options
    var hot_fixed_init = new Handsontable(hot_fixed, {
        data: hot_fixed_data,
        rowHeaders: true,
        colHeaders: true,
        stretchH: 'all',
        fixedRowsTop: 2,
        fixedColumnsLeft: 2
    });



    // Scrolling
    // ------------------------------

    // Generate sample data
    var hot_scroll_data = Handsontable.helper.createSpreadsheetData(250, 40);

    // Define element
    var hot_scroll = document.getElementById('hot_scroll');

    // Initialize with options
    var hot_scroll_init = new Handsontable(hot_scroll,{
        data: hot_scroll_data,
        stretchH: 'all',
        rowHeaders: true,
        colHeaders: true
    });



    // Columns freezing
    // ------------------------------

    // Generate sample data
    var hot_freezing_data = Handsontable.helper.createSpreadsheetData(40, 40);

    // Define element
    var hot_freezing = document.getElementById('hot_freezing');

    // Initialize with options
    var hot_freezing_init = new Handsontable(hot_freezing, {
        data: hot_freezing_data,
        rowHeaders: true,
        colHeaders: true,
        stretchH: 'all',
        fixedColumnsLeft: 2,
        contextMenu: ['row_above', 'row_below', '---------', 'freeze_column'],
        manualColumnFreeze: true
    });



    // Columns and rows moving
    // ------------------------------

    // Add data for multiple examples
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
    var hot_moving = document.getElementById('hot_moving');

    // Initialize with options
    var hot_moving_init = new Handsontable(hot_moving, {
        data: car_data,
        rowHeaders: true,
        colHeaders: ['Brand', 'Model', 'Year', 'Color', 'Price'],
        stretchH: 'all',
        manualColumnMove: true,
        manualRowMove: true
    });



    // Columns stretching
    // ------------------------------

    // Define element
    var hot_stretch = document.getElementById('hot_stretch');

    // Initialize with options
    var hot_stretch_init = new Handsontable(hot_stretch, {
        data: car_data,
        colWidths: [150, 150, 100, 120],
        rowHeaders: true,
        colHeaders: ['Brand', 'Model', 'Year', 'Color', 'Price'],
        stretchH: 'last'
    });



    // Resize
    // ------------------------------

    // Add data
    var hot_resize_data = Handsontable.helper.createSpreadsheetData(10, 10);

    // Initialize with options
    var hot_resize_init = new Handsontable(hot_resize, {
        data: car_data,
        rowHeaders: true,
        colHeaders: ['Brand', 'Model', 'Year', 'Color', 'Price'],
        stretchH: 'all',
        colWidths: [55, 80, 80, 80, 80, 80, 80],
        rowHeights: [50, 40, 100],
        manualColumnResize: true,
        manualRowResize: true
    });

});
