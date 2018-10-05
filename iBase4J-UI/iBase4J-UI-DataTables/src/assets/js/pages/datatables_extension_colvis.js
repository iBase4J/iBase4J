/* ------------------------------------------------------------------------------
*
*  # Columns Visibility (Buttons) extension for Datatables
*
*  Specific JS code additions for datatable_extension_colvis.html page
*
*  Version: 1.2
*  Latest update: Mar 6, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Table setup
    // ------------------------------

    // Setting datatable defaults
    $.extend( $.fn.dataTable.defaults, {
        autoWidth: false,
        dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        }
    });

    
    // Basic example
    $('.datatable-colvis-basic').DataTable({
        buttons: [
            {
                extend: 'colvis',
                className: 'btn btn-default'
            }
        ]
    });


    // Multi-column layout
    $('.datatable-colvis-multi').DataTable({
        buttons: [
            {
                extend: 'colvis',
                text: '<i class="icon-three-bars"></i> <span class="caret"></span>',
                className: 'btn bg-blue btn-icon',
                collectionLayout: 'fixed two-column'
            }
        ]
    });


    // Restore column visibility
    $('.datatable-colvis-restore').DataTable({
        buttons: [
            {
                extend: 'colvis',
                text: '<i class="icon-grid7"></i> <span class="caret"></span>',
                className: 'btn bg-teal-400 btn-icon',
                postfixButtons: [ 'colvisRestore' ]
            }
        ],
        columnDefs: [
            {
                targets: -1,
                visible: false
            }
        ]
    });


    // State saving
    $('.datatable-colvis-state').DataTable({
        buttons: [
            {
                extend: 'colvis',
                text: '<i class="icon-grid3"></i> <span class="caret"></span>',
                className: 'btn bg-indigo-400 btn-icon'
            }
        ],
        stateSave: true,
        columnDefs: [
            {
                targets: -1,
                visible: false
            }
        ]
    });


    // Column groups
    $('.datatable-colvis-group').DataTable({
        buttons: {
            buttons: [
                {
                    extend: 'colvisGroup',
                    text: 'Office info',
                    className: 'btn btn-default',
                    show: [0, 1, 2],
                    hide: [3, 4, 5]
                },
                {
                    extend: 'colvisGroup',
                    className: 'btn btn-default',
                    text: 'HR info',
                    show: [3, 4, 5],
                    hide: [0, 1, 2]
                },
                {
                    extend: 'colvisGroup',
                    className: 'btn btn-default',
                    text: 'Show all',
                    show: ':hidden'
                }
            ]
        }
    });



    // External table additions
    // ------------------------------

    // Launch Uniform styling for checkboxes
    $('.ColVis_Button').addClass('btn btn-primary btn-icon').on('click mouseover', function() {
        $('.ColVis_collection input').uniform();
    });


    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder', 'Type to filter...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });
    
});
