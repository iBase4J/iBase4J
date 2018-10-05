/* ------------------------------------------------------------------------------
*
*  # Invoice archive
*
*  Specific JS code additions for invoice_archive.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Table setup
    // ------------------------------

    // Initialize
    $('.invoice-archive').DataTable({
        autoWidth: false,
        columnDefs: [
            {
                width: '30px',
                targets: 0
            },
            {
                visible: false,
                targets: 1
            },
            { 
                orderable: false,
                width: '100px',
                targets: 7
            },
            {
                width: '15%',
                targets: [4, 5]
            },
            {
                width: '15%',
                targets: 6
            },
            {
                width: '15%',
                targets: 3
            }
        ],
        order: [[ 0, 'desc' ]],
        dom: '<"datatable-header"fl><"datatable-scroll-lg"t><"datatable-footer"ip>',
        language: {
            search: '<span>Filter:</span> _INPUT_',
            lengthMenu: '<span>Show:</span> _MENU_',
            paginate: { 'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;' }
        },
        lengthMenu: [ 25, 50, 75, 100 ],
        displayLength: 25,
        drawCallback: function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;
 
            api.column(1, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="active border-double"><td colspan="8" class="text-semibold">'+group+'</td></tr>'
                    );
 
                    last = group;
                }
            });

            $('.select').select2({
                width: '150px',
                minimumResultsForSearch: Infinity
            });

            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function(settings) {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
            $('.select').select2().select2('destroy');
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
