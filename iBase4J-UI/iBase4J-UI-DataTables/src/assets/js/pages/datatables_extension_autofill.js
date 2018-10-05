/* ------------------------------------------------------------------------------
*
*  # Autofill extension for Datatables
*
*  Specific JS code additions for datatable_extension_autofill.html page
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
            targets: [ 5 ]
        }],
        dom: '<"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        }
    });


    // Basic initialization
    $('.datatable-autofill-basic').DataTable({
        autoFill: true
    });


    // Always confirm action
    $('.datatable-autofill-confirm').DataTable({
        autoFill: {
            alwaysAsk: true
        },
    });


    // Click focus
    $('.datatable-autofill-click').DataTable({
        autoFill: {
            focus: 'click'
        }
    });


    // Column selector
    $('.datatable-autofill-column').DataTable( {
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
        order: [[1, 'asc']],
        autoFill: {
            columns: ':not(:first-child)'
        }
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
