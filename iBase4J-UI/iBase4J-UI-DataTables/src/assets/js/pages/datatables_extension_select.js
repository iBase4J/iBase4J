/* ------------------------------------------------------------------------------
*
*  # Select extension for Datatables
*
*  Specific JS code additions for datatable_extension_select.html page
*
*  Version: 1.0
*  Latest update: Nov 9, 2015
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
            targets: [ 8 ]
        }],
        dom: '<"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
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


    // Basic initialization
    $('.datatable-select-basic').DataTable({
        select: true
    });


    // Single item selection
    $('.datatable-select-single').DataTable({
        select: {
            style: 'single'
        }
    });


    // Multiple items selection
    $('.datatable-select-multiple').DataTable({
        select: {
            style: 'multi'
        }
    });


    // Checkbox selection
    $('.datatable-select-checkbox').DataTable({
        columnDefs: [
            {
                orderable: false,
                className: 'select-checkbox',
                targets: 0
            },
            {
                orderable: false,
                width: '100px',
                targets: 6
            }
        ],
        select: {
            style: 'os',
            selector: 'td:first-child'
        },
        order: [[1, 'asc']]
    });


    // Buttons
    $('.datatable-select-buttons').DataTable({
        dom: '<"dt-buttons-full"B><"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        buttons: [
            {extend: 'selected', className: 'btn btn-default'},
            {extend: 'selectedSingle', className: 'btn btn-default'},
            {extend: 'selectAll', className: 'btn bg-blue'},
            {extend: 'selectNone', className: 'btn bg-blue'},
            {extend: 'selectRows', className: 'btn bg-teal-400'},
            {extend: 'selectColumns', className: 'btn bg-teal-400'},
            {extend: 'selectCells', className: 'btn bg-teal-400'}
        ],
        select: true
    });



    // External table additions
    // ------------------------------

    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');

    
});
