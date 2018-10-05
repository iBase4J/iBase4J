/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_advanced.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Conditional formatting
    // ------------------------------

    // Add data
    var hot_format_data = [
        ['', 'Kia', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
        ['2003', -38293, '', 38849, 32353, -47758, 'Read only'],
        ['2004', 23433, 88569, 48892, 12322, '', 27840],
        ['2005', 64393, -89432, 'Read only', 89390, 42853, -12228],
        ['2006', 45382, 57729, -48823, -12774, '', -98493],
        ['2007', -86433, 48923, -33378, 'Read only', 90043, 34982],
        ['2008', 45833, -12293, 12894, 78859, '', 43054],
        ['2009', 'Read only', '', 49950, -58823, -57882, 89954],
        ['2010', -85943, 90449, -38882, 34928, '', 23487],
        ['2011', 44950, -90092, 'Read only', '', 89003, 'Read only'],
        ['2012', 23486, 'Read only', 47729, 23945, -99001, 48995],
        ['2013', 90392, '', 48852, 17789, 32984, ''],
        ['2014', -47382, 88457, '', 58875, -45398, '']
    ];

    // Header row renderer
    function firstRowRenderer(instance, td, row, col, prop, value, cellProperties) {
        Handsontable.renderers.TextRenderer.apply(this, arguments);

        // Add styles to the table cell
        td.style.fontWeight = '500';
        td.style.color = '#1B5E20';
        td.style.background = '#E8F5E9';
    }
  
    // Negative values renderer
    function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
        Handsontable.renderers.TextRenderer.apply(this, arguments);

        // If row contains negative number, add class "negative"
        if (parseInt(value, 10) < 0) {
            td.className = 'text-danger';
        }

        // If empty cell, add grey background
        if (!value || value === '') {
            td.style.background = '#f5f5f5';
        }
    }

    // Maps function to lookup string
    Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);

    // Define element
    var hot_format = document.getElementById('hot_format');

    // Initialize with options
    hot_format_init = new Handsontable(hot_format, {
        data: hot_format_data,
        stretchH: 'all',
        afterSelection: function (row, col, row2, col2) {
            var meta = this.getCellMeta(row2, col2);

            if (meta.readOnly) {
                this.updateSettings({fillHandle: false});
            }
            else {
                this.updateSettings({fillHandle: true});
            }
        },
        cells: function (row, col, prop, td) {
            var cellProperties = {};

            if (row === 0 || this.instance.getData()[row][col] === 'Read only') {
                cellProperties.readOnly = true; // make cell read-only if it is first row or the text reads 'readOnly'
            }
            if (row === 0 || col === 0) {
                cellProperties.renderer = firstRowRenderer; // uses function directly
            }
            else {
                cellProperties.renderer = "negativeValueRenderer"; // uses lookup map
            }

            return cellProperties;
        }
    });



    // Sorting data
    // ------------------------------

    // Add sample data for multiple examples
    var hot_data = [
        [1, "George Washington", "http://en.wikipedia.org/wiki/George_Washington", "30/04/1789", "4/03/1797", "Virginia"],
        [2, "John Adams", "http://en.wikipedia.org/wiki/John_Adams", "4/03/1797", "4/03/1801", "Massachusetts"],
        [3, "Thomas Jefferson", "http://en.wikipedia.org/wiki/Thomas_Jefferson", "4/03/1801", "4/03/1809", "Virginia"],
        [4, "James Madison", "http://en.wikipedia.org/wiki/James_Madison", "4/03/1809", "4/03/1817", "Virginia"],
        [5, "James Monroe", "http://en.wikipedia.org/wiki/James_Monroe", "4/03/1817", "4/03/1825", "Virginia"],
        [6, "John Quincy Adams", "http://en.wikipedia.org/wiki/John_Quincy_Adams", "4/03/1825", "4/03/1829", "Massachusetts"],
        [7, "Andrew Jackson", "http://en.wikipedia.org/wiki/Andrew_Jackson", "4/03/1829", "4/03/1837", "Tennessee"],
        [8, "Martin Van Buren", "http://en.wikipedia.org/wiki/Martin_Van_Buren", "4/03/1837", "4/03/1841", "New York"],
        [9, "William Henry Harrison", "http://en.wikipedia.org/wiki/William_Henry_Harrison", "4/03/1841", "4/04/1841", "Ohio"],
        [10, "John Tyler", "http://en.wikipedia.org/wiki/John_Tyler", "4/04/1841", "4/03/1845", "Virginia"],
        [11, "James K. Polk", "http://en.wikipedia.org/wiki/James_K._Polk", "4/03/1845", "4/03/1849", "Tennessee"],
        [12, "Zachary Taylor", "http://en.wikipedia.org/wiki/Zachary_Taylor", "4/03/1849", "9/07/1850", "Louisiana"]
    ];

    // Define element
    var hot_sorting = document.getElementById('hot_sorting');

    // Initialize with options
    var hot_sorting_init = new Handsontable(hot_sorting, {
        data: hot_data,
        stretchH: 'all',
        rowHeaders: true,
        colHeaders: true,
        columnSorting: true,
        manualColumnResize: true,
        sortIndicator: true
    });



    // Pagination
    // ------------------------------

    // Setup data set
    var getData = (function () {

        // Data
        var hot_pagination_data = [
            [1, "George Washington", "http://en.wikipedia.org/wiki/George_Washington", "30/04/1789", "4/03/1797", "Virginia"],
            [2, "John Adams", "http://en.wikipedia.org/wiki/John_Adams", "4/03/1797", "4/03/1801", "Massachusetts"],
            [3, "Thomas Jefferson", "http://en.wikipedia.org/wiki/Thomas_Jefferson", "4/03/1801", "4/03/1809", "Virginia"],
            [4, "James Madison", "http://en.wikipedia.org/wiki/James_Madison", "4/03/1809", "4/03/1817", "Virginia"],
            [5, "James Monroe", "http://en.wikipedia.org/wiki/James_Monroe", "4/03/1817", "4/03/1825", "Virginia"],
            [6, "John Quincy Adams", "http://en.wikipedia.org/wiki/John_Quincy_Adams", "4/03/1825", "4/03/1829", "Massachusetts"],
            [7, "Andrew Jackson", "http://en.wikipedia.org/wiki/Andrew_Jackson", "4/03/1829", "4/03/1837", "Tennessee"],
            [8, "Martin Van Buren", "http://en.wikipedia.org/wiki/Martin_Van_Buren", "4/03/1837", "4/03/1841", "New York"],
            [9, "William Henry Harrison", "http://en.wikipedia.org/wiki/William_Henry_Harrison", "4/03/1841", "4/04/1841", "Ohio"],
            [10, "John Tyler", "http://en.wikipedia.org/wiki/John_Tyler", "4/04/1841", "4/03/1845", "Virginia"],
            [11, "James K. Polk", "http://en.wikipedia.org/wiki/James_K._Polk", "4/03/1845", "4/03/1849", "Tennessee"],
            [12, "Zachary Taylor", "http://en.wikipedia.org/wiki/Zachary_Taylor", "4/03/1849", "9/07/1850", "Louisiana"],
            [13, "Millard Fillmore", "http://en.wikipedia.org/wiki/Millard_Fillmore", "9/07/1850", "4/03/1853", "New York"],
            [14, "Franklin Pierce", "http://en.wikipedia.org/wiki/Franklin_Pierce", "4/03/1853", "4/03/1857", "New Hampshire"],
            [15, "James Buchanan", "http://en.wikipedia.org/wiki/James_Buchanan", "4/03/1857", "4/03/1861", "Pennsylvania"],
            [16, "Abraham Lincoln", "http://en.wikipedia.org/wiki/Abraham_Lincoln", "4/03/1861", "15/04/1865", "Illinois"],
            [17, "Andrew Johnson", "http://en.wikipedia.org/wiki/Andrew_Johnson", "15/04/1865", "4/03/1869", "Tennessee"],
            [18, "Ulysses S. Grant", "http://en.wikipedia.org/wiki/Ulysses_S._Grant", "4/03/1869", "4/03/1877", "Ohio"],
            [19, "Rutherford B. Hayes", "http://en.wikipedia.org/wiki/Rutherford_B._Hayes", "4/03/1877", "4/03/1881", "Ohio"],
            [20, "James A. Garfield", "http://en.wikipedia.org/wiki/James_A._Garfield", "4/03/1881", "19/09/1881", "Ohio"],
            [21, "Chester A. Arthur", "http://en.wikipedia.org/wiki/Chester_A._Arthur", "19/09/1881", "4/03/1885", "New York"],
            [22, "Grover Cleveland", "http://en.wikipedia.org/wiki/Grover_Cleveland", "4/03/1885", "4/03/1889", "New York"],
            [23, "Benjamin Harrison", "http://en.wikipedia.org/wiki/Benjamin_Harrison", "4/03/1889", "4/03/1893", "Indiana"],
            [24, "Grover Cleveland (2nd term)", "http://en.wikipedia.org/wiki/Grover_Cleveland", "4/03/1893", "4/03/1897", "New York"],
            [25, "William McKinley", "http://en.wikipedia.org/wiki/William_McKinley", "4/03/1897", "14/9/1901", "Ohio"],
            [26, "Theodore Roosevelt", "http://en.wikipedia.org/wiki/Theodore_Roosevelt", "14/9/1901", "04/03/09", "New York"],
            [27, "William Howard Taft", "http://en.wikipedia.org/wiki/William_Howard_Taft", "04/03/09", "04/03/13", "Ohio"],
            [28, "Woodrow Wilson", "http://en.wikipedia.org/wiki/Woodrow_Wilson", "04/03/13", "04/03/21", "New Jersey"],
            [29, "Warren G. Harding", "http://en.wikipedia.org/wiki/Warren_G._Harding", "04/03/21", "02/08/23", "Ohio"],
            [30, "Calvin Coolidge", "http://en.wikipedia.org/wiki/Calvin_Coolidge", "02/08/23", "04/03/29", "Massachusetts"],
            [31, "Herbert Hoover", "http://en.wikipedia.org/wiki/Herbert_Hoover", "04/03/29", "04/03/33", "Iowa"],
            [32, "Franklin D. Roosevelt", "http://en.wikipedia.org/wiki/Franklin_D._Roosevelt", "04/03/33", "12/04/45", "New York"],
            [33, "Harry S. Truman", "http://en.wikipedia.org/wiki/Harry_S._Truman", "12/04/45", "20/01/53", "Missouri"],
            [34, "Dwight D. Eisenhower", "http://en.wikipedia.org/wiki/Dwight_D._Eisenhower", "20/01/53", "20/01/61", "Texas"],
            [35, "John F. Kennedy", "http://en.wikipedia.org/wiki/John_F._Kennedy", "20/01/61", "22/11/63", "Massachusetts"],
            [36, "Lyndon B. Johnson", "http://en.wikipedia.org/wiki/Lyndon_B._Johnson", "22/11/63", "20/01/69", "Texas"],
            [37, "Richard Nixon", "http://en.wikipedia.org/wiki/Richard_Nixon", "20/01/69", "09/08/74", "California"],
            [38, "Gerald Ford", "http://en.wikipedia.org/wiki/Gerald_Ford", "09/08/74", "20/01/77", "Michigan"],
            [39, "Jimmy Carter", "http://en.wikipedia.org/wiki/Jimmy_Carter", "20/01/77", "20/01/81", "Georgia"],
            [40, "Ronald Reagan", "http://en.wikipedia.org/wiki/Ronald_Reagan", "20/01/81", "20/01/89", "California"],
            [41, "George H. W. Bush", "http://en.wikipedia.org/wiki/George_H._W._Bush", "20/01/89", "20/01/93", "Texas"],
            [42, "Bill Clinton", "http://en.wikipedia.org/wiki/Bill_Clinton", "20/01/93", "20/01/01", "Arkansas"],
            [43, "George W. Bush", "http://en.wikipedia.org/wiki/George_W._Bush", "20/01/01", "20/01/09", "Texas"],
            [44, "Barack Obama", "http://en.wikipedia.org/wiki/Barack_Obama", "20/01/09", "Incumbent", "Illinois"]
        ];

        // Paging setup
        return function () {
            var page  = parseInt(window.location.hash.replace('#', ''), 10) || 1,
                limit = 10,
                row   = (page - 1) * limit,
                count = page * limit,
                part  = [];

            for (;row < count;row++) {
                part.push(hot_pagination_data[row]);
            }

            // Toggling active class in pagination
            if(location.hash != "") {

                // Remove active class on load from the first item
                $('#hot-pagination > li').removeClass('active');

                // Remove active class from siblings on click
                $('#hot-pagination > li').on('click', function() {
                    $(this).siblings('li').removeClass('active');
                });

                // Add active class
                $('#hot-pagination > li').has('a[href="' + location.hash + '"]').addClass('active');
            }

            return part;
        }
    })();

    // Load data on hash change
    Handsontable.Dom.addEvent(window, 'hashchange', function (event) {
        hot_pagination_init.loadData(getData());
    });

    // Define element
    var hot_pagination = document.getElementById('hot_pagination');

    // Initialize with options
    var hot_pagination_init = new Handsontable(hot_pagination, {
        data: getData(),
        colHeaders: true,
        stretchH: 'all'
    });



    // Pre-populating new rows
    // ------------------------------

    // Add data
    var hot_populate_data = [
        [1, "George Washington", "http://en.wikipedia.org/wiki/George_Washington", "30/04/1789", "4/03/1797", "Virginia"],
        [2, "John Adams", "http://en.wikipedia.org/wiki/John_Adams", "4/03/1797", "4/03/1801", "Massachusetts"],
        [3, "Thomas Jefferson", "http://en.wikipedia.org/wiki/Thomas_Jefferson", "4/03/1801", "4/03/1809", "Virginia"],
        [4, "James Madison", "http://en.wikipedia.org/wiki/James_Madison", "4/03/1809", "4/03/1817", "Virginia"],
        [5, "James Monroe", "http://en.wikipedia.org/wiki/James_Monroe", "4/03/1817", "4/03/1825", "Virginia"],
        [6, "John Quincy Adams", "http://en.wikipedia.org/wiki/John_Quincy_Adams", "4/03/1825", "4/03/1829", "Massachusetts"],
        [7, "Andrew Jackson", "http://en.wikipedia.org/wiki/Andrew_Jackson", "4/03/1829", "4/03/1837", "Tennessee"],
        [8, "Martin Van Buren", "http://en.wikipedia.org/wiki/Martin_Van_Buren", "4/03/1837", "4/03/1841", "New York"],
        [9, "William Henry Harrison", "http://en.wikipedia.org/wiki/William_Henry_Harrison", "4/03/1841", "4/04/1841", "Ohio"],
        [10, "John Tyler", "http://en.wikipedia.org/wiki/John_Tyler", "4/04/1841", "4/03/1845", "Virginia"],
        [11, "James K. Polk", "http://en.wikipedia.org/wiki/James_K._Polk", "4/03/1845", "4/03/1849", "Tennessee"],
        [12, "Zachary Taylor", "http://en.wikipedia.org/wiki/Zachary_Taylor", "4/03/1849", "9/07/1850", "Louisiana"]
    ];

    // Cells template
    var tpl = ['one', 'two', 'three', 'four', 'five', 'six'];

    // Render empty row
    function isEmptyRow(instance, row) {
        var rowData = instance.getData()[row];

        for (var i = 0, ilen = rowData.length; i < ilen; i++) {
            if (rowData[i] !== null) {
                return false;
            }
        }

        return true;
    }

    // Render default values
    function defaultValueRenderer(instance, td, row, col, prop, value, cellProperties) {
        var args = arguments;

        if (args[5] === null && isEmptyRow(instance, row)) {
            args[5] = tpl[col];
            td.style.color = '#ccc';
        }
        else {
            td.style.color = '';
        }
        Handsontable.renderers.TextRenderer.apply(this, args);
    }

    // Define element
    var hot_populate = document.getElementById('hot_populate')

    // Initialize with options
    var hot_populate_init = new Handsontable(hot_populate, {
        data: hot_populate_data,
        startRows: 8,
        startCols: 5,
        minSpareRows: 1,
        colHeaders: true,
        stretchH: 'all',
        contextMenu: true,
        cells: function (row, col, prop) {
            var cellProperties = {};

            cellProperties.renderer = defaultValueRenderer;

            return cellProperties;
        },
        beforeChange: function (changes) {
            var instance = hot_populate_init,
            ilen = changes.length,
            clen = instance.colCount,
            rowColumnSeen = {},
            rowsToFill = {},
            i,
            c;

            for (i = 0; i < ilen; i++) {

                // If oldVal is empty
                if (changes[i][2] === null && changes[i][3] !== null) {
                    if (isEmptyRow(instance, changes[i][0])) {

                        // Add this row/col combination to cache so it will not be overwritten by template
                        rowColumnSeen[changes[i][0] + '/' + changes[i][1]] = true;
                        rowsToFill[changes[i][0]] = true;
                    }
                }
            }

            for (var r in rowsToFill) {
                if (rowsToFill.hasOwnProperty(r)) {
                    for (c = 0; c < clen; c++) {

                        // If it is not provided by user in this change set, take value from template
                        if (!rowColumnSeen[r + '/' + c]) {
                            changes.push([r, c, null, tpl[c]]);
                        }
                    }
                }
            }
        }
    });



    // Highlighting current
    // ------------------------------

    // Define element
    hot_highlight = document.getElementById('hot_highlight');

    // Initialize with options
    hot_highlight_init = Handsontable(hot_highlight, {
        data: hot_data,
        minRows: 5,
        minCols: 6,
        stretchH: 'all',
        currentRowClassName: 'active',
        currentColClassName: 'active',
        rowHeaders: true,
        colHeaders: true
    });

    // Select cell
    hot_highlight_init.selectCell(2,2);



    // Bootstrap integration
    // ------------------------------

    // Define element
    var hot_bootstrap = document.getElementById('hot_bootstrap');

    // Init with options
    hot_bootstrap_init = new Handsontable(hot_bootstrap, {
        data: hot_data,
        colHeaders: true,
        stretchH: 'all',
        fixedColumnsLeft: 2,
        tableClassName: ['table-hover', 'table-striped']
    });

});
