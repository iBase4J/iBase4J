/* ------------------------------------------------------------------------------
*
*  # Datatables data sources
*
*  Specific JS code additions for datatable_data_sources.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Table setup
    // ------------------------------

    // Setting datatable defaults
    $.extend( $.fn.dataTable.defaults, {
        autoWidth: false,
        columnDefs: [{ 
            orderable: false,
            width: '100px',
            targets: [ 5 ]
        }],
        dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        },
        drawCallback: function () {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
        }
    });


    // HTML sourced data
    $('.datatable-html').dataTable();


    // AJAX sourced data
    $('.datatable-ajax').dataTable({
        ajax: 'assets/demo_data/tables/datatable_ajax.json'
    });


    // Javascript sourced data
    var dataSet = [
        ['Trident','Internet Explorer 4.0','Win 95+','4','X'],
        ['Trident','Internet Explorer 5.0','Win 95+','5','C'],
        ['Trident','Internet Explorer 5.5','Win 95+','5.5','A'],
        ['Trident','Internet Explorer 6','Win 98+','6','A'],
        ['Gecko','Firefox 1.0','Win 98+ / OSX.2+','1.7','A'],
        ['Gecko','Firefox 1.5','Win 98+ / OSX.2+','1.8','A'],
        ['Gecko','Firefox 2.0','Win 98+ / OSX.2+','1.8','A'],
        ['Gecko','Firefox 3.0','Win 2k+ / OSX.3+','1.9','A'],
        ['Gecko','Camino 1.0','OSX.2+','1.8','A'],
        ['Gecko','Camino 1.5','OSX.3+','1.8','A'],
        ['Webkit','Safari 1.2','OSX.3','125.5','A'],
        ['Webkit','Safari 1.3','OSX.3','312.8','A'],
        ['Webkit','Safari 2.0','OSX.4+','419.3','A'],
        ['Presto','Opera 7.0','Win 95+ / OSX.1+','-','A'],
        ['Presto','Opera 7.5','Win 95+ / OSX.2+','-','A'],
        ['Misc','NetFront 3.1','Embedded devices','-','C'],
        ['Misc','NetFront 3.4','Embedded devices','-','A'],
        ['Misc','Dillo 0.8','Embedded devices','-','X'],
        ['Misc','Links','Text only','-','X']
    ];

    $('.datatable-js').dataTable({
        data: dataSet,
        columnDefs: []
    });


    // Nested object data
    $('.datatable-nested').dataTable({
        ajax: 'assets/demo_data/tables/datatable_nested.json',
        columns: [
            {data: "name[, ]"},
            {data: "hr.0" },
            {data: "office"},
            {data: "extn"},
            {data: "hr.2"},
            {data: "hr.1"}
        ]
    });


    // Generate content for a column
    var table = $('.datatable-generated').DataTable({
        ajax: 'assets/demo_data/tables/datatable_ajax.json',
        columnDefs: [{
            targets: 2,
            data: null,
            defaultContent: "<button class='label label-default'>Show</button>"
        },
        { 
            orderable: false,
            targets: [0, 2]
        }]
    });
    
    $('.datatable-generated tbody').on('click', 'button', function () {
        var data = table.row($(this).parents('tr')).data();
        alert(data[0] +"'s location is: "+ data[ 2 ]);
    });



    // External table additions
    // ------------------------------

    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });
    
});
