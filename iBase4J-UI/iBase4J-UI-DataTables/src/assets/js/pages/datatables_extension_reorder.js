/* ------------------------------------------------------------------------------
*
*  # Reorder Columns extension for Datatables
*
*  Specific JS code additions for datatable_extension_reorder.html page
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
        colReorder: true,
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


    // Basic column reorder
    $('.datatable-reorder').DataTable();


    // Realtime updating
    $('.datatable-reorder-realtime').DataTable({
        colReorder: {
            realtime: true
        }
    });


    // Save state after reorder
    $('.datatable-reorder-state-saving').DataTable({
        stateSave: true
    });


    // Predefined column ordering
    $('.datatable-reorder-predefined').DataTable({
        colReorder: {
            order: [1, 3, 2, 4, 0, 5]
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