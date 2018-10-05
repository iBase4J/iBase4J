/* ------------------------------------------------------------------------------
*
*  # Handsontable - Excel-like tables with extensive funtionality
*
*  Specific JS code additions for handsontable_custom_checks.html page
*
*  Version: 1.0
*  Latest update: Nov 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Numeric type
    // ------------------------------

    // Define element
    var hot_html_cells_data = [
        {
            cover: "https://thumb-tf.s3.envato.com/files/154866588/icon.png",
            title: "<a href='http://themeforest.net/item/limitless-responsive-web-application-kit/13080328' target='_blank'>Limitless - Responsive Web Application Kit</a>",
            description: "Limitless is one of the best application templates ever built. Very flexible and clean admin template based on Bootstrap and Less. Available in 4 different layouts.",
            sales: "<strong>12930</strong>",
            comments: "<div class='text-orange mb-5'><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i></div> 35 ratings",
        },
        {
            cover: "https://thumb-tf.s3.envato.com/files/91482991/icon.png",
            title: "<a href='http://themeforest.net/item/londinium-responsive-bootstrap-3-admin-template/6978619' target='_blank'>Londinium - responsive bootstrap 3 admin template</a>",
            description: "Londinium – a new premium admin skin with lots of custom elements, plugins and very flexible content structure. Londinium is powered with Bootstrap framework.",
            sales: "<strong>8509</strong>",
            comments: "<div class='text-orange mb-5'><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i></div> 56 ratings",
        },
        {
            cover: "https://thumb-tf.s3.envato.com/files/84750409/icon.png",
            title: "<a href='http://themeforest.net/item/its-brain-responsive-bootstrap-3-admin-template/909197' target='_blank'>It's Brain - Responsive Bootstrap 3 Admin Template</a>",
            description: "It's Brain admin template is based on Bootstrap framework and includes a lot of different features. Comes with fixed and liquid layouts in both light and dark versions.",
            sales: "<strong>3902</strong>",
            comments: "<div class='text-orange mb-5'><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i><i class='icon-star-full2'></i></div> 453 ratings",
        }
    ];

    // Define element
    var hot_html_cells = document.getElementById('hot_html_cells');

    // Initialize with options
    var hot_html_cells_init = new Handsontable(hot_html_cells, {
        data: hot_html_cells_data,
        colWidths: [50, 120, 200, 60, 50],
        rowHeights: 111, // 80px img height + 30px margin + 1px border
        stretchH: 'all',
        colHeaders: ["Icon", "Name", "Description", "Sales", "Rating"],
        columns: [
            {data: "cover", renderer: coverRenderer},
            {data: "title", renderer: "html"},
            {data: "description", renderer: "html"},
            {data: "sales", renderer: "html", className: 'htCenter'},
            {data: "comments", renderer: "html", className: 'htCenter'},
        ]
    });


    // Original by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    function strip_tags(input, allowed) {
        var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
        commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;

        // Making sure the allowed arg is a string containing only tags in lowercase (<a><b><c>)
        allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');

        return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
        });
    }

    // Renderer for image
    function coverRenderer (instance, td, row, col, prop, value, cellProperties) {
        var escaped = Handsontable.helper.stringify(value),
            img;

        if (escaped.indexOf('http') === 0) {
            img = document.createElement('IMG');
            img.className = 'img-responsive';
            img.style.margin = '15px auto';
            img.style.height = 80;
            img.src = value;

            Handsontable.Dom.addEvent(img, 'mousedown', function (e){
                e.preventDefault(); // prevent selection quirk
            });

            Handsontable.Dom.empty(td);
            td.appendChild(img);
        }
        else {
            Handsontable.renderers.TextRenderer.apply(this, arguments); // render as text
        }

        return td;
    }



    // Rendering custom HTML in header
    // ------------------------------

    // Demo data for 2 examples
    var car_data = [
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
    var hot_html_header = document.getElementById('hot_html_header');

    // Initialize with options
    var hot_html_header_init = new Handsontable(hot_html_header, {
        data: car_data,
        stretchH: 'all',
        columns: [
            {
                data: 'car',
                renderer: customRenderer
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
                format: '0,0.00 $'
            },
            {
                data: 'share',
                type: 'numeric',
                className: 'htLeft',
                format: '0%'
            }
        ],
        colHeaders: function (col) {
            var txt;

            switch (col) {
                case 0:
                    txt = "<span class='position-left'>Brand</span> <input type='checkbox' class='hot-checker' ";
                    txt += isChecked ? 'checked="checked">' : '>';
                    return txt;

                case 1:
                    return '<strong>Model</strong>';

                case 2:
                    return '<i class="icon-calendar3 position-left"></i> Date';

                case 3:
                    return '<u><i>Price</i></u>';

                case 4:
                    return 'Share <sup>%</sup>';
            }
        }
    });

    // Setup custom renderer
    var isChecked;
    function customRenderer(instance, td) {
        Handsontable.renderers.TextRenderer.apply(this, arguments);

        if (isChecked) {
            td.style.backgroundColor = '#F1F8E9';
        }
        else {
            td.style.backgroundColor = '';
        }

        return td;
    }

    // Mousedown event
    Handsontable.Dom.addEvent(hot_html_header, 'mousedown', function (event) {
        if (event.target.nodeName == 'INPUT' && event.target.className == 'hot-checker') {
            event.stopPropagation();
        }
    });

    // Mouseup event
    Handsontable.Dom.addEvent(hot_html_header, 'mouseup', function (event) {
        if (event.target.nodeName == 'INPUT' && event.target.className == 'hot-checker') {
            isChecked = !event.target.checked;
            hot_html_header_init.render();
        }
    });



    // Change column type
    // ------------------------------

    // Define element
    var hot_change_type = document.getElementById('hot_change_type');

    // Setup columns
    var columns = [
        {type: 'numeric', data: 'car'},
        {type: 'numeric', data: 'model'},
        {type: 'date', data: 'date'},
        {type: 'numeric', data: 'price', className: 'htLeft', format: '0,0.00 $'},
        {type: 'numeric', data: 'share', className: 'htLeft', format: '0%'}
    ];

    // Initialize with options
    var hot_change_type = new Handsontable(hot_change_type,{
        data: car_data,
        colHeaders: true,
        stretchH: 'all',
        columns: columns,
        afterGetColHeader: function(col, TH) {
            var instance = this,
                menu = buildMenu(columns[col].type),
                button = buildButton();

            addButtonMenuEvent(button, menu);

            // Click event
            Handsontable.Dom.addEvent(menu, 'click', function (event) {
                if (event.target.nodeName == 'LI') {
                    setColumnType(col, event.target.data['colType'], instance);
                }
            });
            if (TH.firstChild.lastChild.nodeName === 'BUTTON') {
                TH.firstChild.removeChild(TH.firstChild.lastChild);
            }
            TH.firstChild.appendChild(button);
            TH.style['white-space'] = 'normal';
        }
    });

    // Add button in headers
    function addButtonMenuEvent(button, menu) {
        Handsontable.Dom.addEvent(button, 'click', function (event) {
            var changeTypeMenu, position, removeMenu;

            // Append menu
            document.body.appendChild(menu);

            // Cancel click events
            event.preventDefault();
            event.stopImmediatePropagation();

            // Add class name
            changeTypeMenu = document.querySelectorAll('.changeTypeMenu');

            // Hide menu
            for (var i = 0, len = changeTypeMenu.length; i < len; i++) {
                changeTypeMenu[i].style.display = 'none';
            }

            // Show menu
            menu.style.display = 'block';

            // Mneu positioning
            position = button.getBoundingClientRect();
            menu.style.top = (position.top + (window.scrollY || window.pageYOffset)) + 2 + 'px';
            menu.style.left = (position.left) - 137 + 'px';

            // Remove menu
            removeMenu = function (event) {
                if (event.target.nodeName == 'LI' && event.target.parentNode.className.indexOf('changeTypeMenu') !== -1) {
                    if (menu.parentNode) {
                        menu.parentNode.removeChild(menu);
                    }
                }
            };

            // Events
            Handsontable.Dom.removeEvent(document, 'click', removeMenu);
            Handsontable.Dom.addEvent(document, 'click', removeMenu);
        });
    }

    // Build menu
    function buildMenu(activeCellType){

        // Define elements
        var menu = document.createElement('UL'),
            types = ['text', 'numeric', 'date'],
            item;

        // Add class name
        menu.className = 'changeTypeMenu';

        // Menu items
        for (var i = 0, len = types.length; i< len; i++) {
            item = document.createElement('LI');
            if('innerText' in item) {
                item.innerText = types[i];
            } else {
                item.textContent = types[i];
            }

            item.data = {'colType': types[i]};

            if (activeCellType == types[i]) {
                item.className = 'active';
            }
            menu.appendChild(item);
        }

        return menu;
    }

    // Build button
    function buildButton() {

        // Define element
        var button = document.createElement('BUTTON');

        // Add attributes and extra content
        button.innerHTML = '<i class="icon-arrow-down22"></i>';
        button.className = 'changeType';

        return button;
    }

    // Set columns type
    function setColumnType(i, type, instance) {
        columns[i].type = type;
        instance.updateSettings({columns: columns});
        instance.validateCells(function() {
            instance.render();
        });
    }




    // Checkbox true/false values
    // ------------------------------

    // Add data
    var hot_checks_values_data = [
        {car: "Mercedes", model: "GL500", year: 2012, price: 32500, available: true},
        {car: "Chevrolet", model: "Camaro", year: 2012, price: 42400, available: false},
        {car: "Dodge", model: "Charger", year: 2012, price: 24900, available: true},
        {car: "Hummer", model: "H3", year: 2012, price: 54000, available: true},
        {car: "Chevrolet", model: "Tahoe", year: 2012, price: 29300, available: false},
        {car: "Toyota", model: "Land Cruiser", year: 2012, price: 54500, available: true},
        {car: "Nissan", model: "GTR", year: 2012, price: 44900, available: false},
        {car: "Porsche", model: "Cayenne", year: 2012, price: 35000, available: false},
        {car: "Volkswagen", model: "Touareg", year: 2012, price: 41000, available: true},
        {car: "BMW", model: "X5", year: 2012, price: 48800, available: true},
        {car: "Audi", model: "Q7", year: 2012, price: 21000, available: false},
        {car: "Cadillac", model: "Escalade", year: 2012, price: 63900, available: true}
    ];
  
    // Define element
    var hot_checks_values = document.getElementById('hot_checks_values');

    // Initialize with options
    var hot_checks_values_init = new Handsontable(hot_checks_values, {
        data: hot_checks_values_data,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Year', 'Price', 'Available'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'numeric',
                className: 'htLeft',
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'available',
                type: 'checkbox',
                className: 'htCenter',
                width: 20
            }
        ]
    });



    // Checkbox labels
    // ------------------------------

    // Add sample data
    var hot_checks_labels_data = [
        {car: "Mercedes", model: "GL500", date: "11/01/2015", price: 32500, share: 0.64, available: 'yes'},
        {car: "Chevrolet", model: "Camaro", date: "11/02/2015", price: 42400, share: 0.37, available: 'yes'},
        {car: "Dodge", model: "Charger", date: "11/03/2015", price: 24900, share: 0.33, available: 'no'},
        {car: "Hummer", model: "H3", date: "11/04/2015", price: 54000, share: 0.15, available: 'no'},
        {car: "Chevrolet", model: "Tahoe", date: "11/05/2015", price: 29300, share: 0.27, available: 'yes'},
        {car: "Toyota", model: "Land Cruiser", date: "11/06/2015", price: 54500, share: 0.43, available: 'yes'},
        {car: "Nissan", model: "GTR", date: "11/07/2015", price: 44900, share: 0.35, available: 'no'},
        {car: "Porsche", model: "Cayenne", date: "11/08/2015", price: 35000, share: 0.63, available: 'no'},
        {car: "Volkswagen", model: "Touareg", date: "11/09/2015", price: 41000, share: 0.15, available: 'yes'},
        {car: "BMW", model: "X5", date: "11/10/2015", price: 48800, share: 0.35, available: 'no'},
        {car: "Audi", model: "Q7", date: "11/11/2015", price: 21000, share: 0.53, available: 'no'},
        {car: "Cadillac", model: "Escalade", date: "11/12/2015", price: 63900, share: 0.38, available: 'yes'}
    ];

    // Define element
    var hot_checks_labels = document.getElementById('hot_checks_labels');

    // Initialize with options
    var hot_checks_labels_init = new Handsontable(hot_checks_labels, {
        data: hot_checks_labels_data,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Date', 'Price', 'Share'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'available',
                type: 'checkbox',
                checkedTemplate: 'yes',
                uncheckedTemplate: 'no',
                label: {
                    property: 'model' // Read value from row object
                }
            },
            {
                data: 'date',
                type: 'date'
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
                width: 30
            }
        ]
    });



    // Checkbox template
    // ------------------------------

    // Add data
    var hot_checks_template_data = [
        {car: "Mercedes", model: "GL500", year: 2012, price: 32500, available: 'no'},
        {car: "Chevrolet", model: "Camaro", year: 2012, price: 42400, available: 'yes'},
        {car: "Dodge", model: "Charger", year: 2012, price: 24900, available: 'no'},
        {car: "Hummer", model: "H3", year: 2012, price: 54000, available: 'yes'},
        {car: "Chevrolet", model: "Tahoe", year: 2012, price: 29300, available: 'yes'},
        {car: "Toyota", model: "Land Cruiser", year: 2012, price: 54500, available: 'no'},
        {car: "Nissan", model: "GTR", year: 2012, price: 44900, available: 'yes'},
        {car: "Porsche", model: "Cayenne", year: 2012, price: 35000, available: 'no'},
        {car: "Volkswagen", model: "Touareg", year: 2012, price: 41000, available: 'yes'},
        {car: "BMW", model: "X5", year: 2012, price: 48800, available: 'no'},
        {car: "Audi", model: "Q7", year: 2012, price: 21000, available: 'no'},
        {car: "Cadillac", model: "Escalade", year: 2012, price: 63900, available: 'yes'}
    ];

    // Define element
    var hot_checks_template = document.getElementById('hot_checks_template');

    // Initialize with options
    var hot_checks_template_init = new Handsontable(hot_checks_template, {
        data: hot_checks_template_data,
        stretchH: 'all',
        colHeaders: ['Brand', 'Model', 'Year', 'Price', 'Available'],
        columns: [
            {
                data: 'car'
            },
            {
                data: 'model'
            },
            {
                data: 'year',
                type: 'numeric',
                className: 'htLeft',
            },
            {
                data: 'price',
                type: 'numeric',
                className: 'htLeft',
                format: '0,0.00 $'
            },
            {
                data: 'available',
                type: 'checkbox',
                checkedTemplate: 'yes',
                className: 'htCenter',
                uncheckedTemplate: 'no',
                width: 20
            }
        ]
    });

});
