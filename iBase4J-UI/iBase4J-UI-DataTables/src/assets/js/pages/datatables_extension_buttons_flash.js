/* ------------------------------------------------------------------------------
*
*  # Buttons extension for Datatables. Flash examples
*
*  Specific JS code additions for datatable_extension_buttons_flash.html page
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
        dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        }
    });


    // Basic initialization
    $('.datatable-button-flash-basic').DataTable({
        buttons: {
            dom: {
                button: {
                    className: 'btn btn-default'
                }
            },
            buttons: [
                {extend: 'copyFlash'},
                {extend: 'csvFlash'},
                {extend: 'excelFlash'},
                {extend: 'pdf'}
            ]
        }
    });


    // Custom file name
    $('.datatable-button-flash-name').DataTable({
        buttons: {
            dom: {
                button: {
                    className: 'btn btn-default'
                }
            },
            buttons: [
                {
                    extend: 'excelFlash',
                    title: 'Data export in Excel'
                },
                {
                    extend: 'pdfFlash',
                    title: 'Data export in PDF'
                }
            ]
        }
    });


    // Custom message
    $('.datatable-button-flash-message').DataTable({
        buttons: [
            {
                extend: 'pdfFlash',
                text: 'Export to PDF',
                className: 'btn bg-blue',
                message: 'This is a custom text added in table configuration.'
            }
        ]
    });


    // File size and orientation
    $('.datatable-button-flash-size').DataTable({
        buttons: [
            {
                extend: 'pdfFlash',
                text: 'Export to PDF',
                className: 'btn bg-blue',
                orientation: 'landscape',
                pageSize: 'LEGAL'
            }
        ]
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
