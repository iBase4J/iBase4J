/* ------------------------------------------------------------------------------
*
*  # Fixed Header extension for Datatables
*
*  Specific JS code additions for datatable_extension_fixed_header.html page
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
            targets: [ 5 ]
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
    var table_basic = $('.datatable-header-basic').DataTable({
        fixedHeader: true
    });


    // Header and footer fixed
    var table_footer = $('.datatable-header-footer').DataTable({
        fixedHeader: {
            header: true,
            footer: true
        }
    });


    // Offset
    var table_offset = $('.datatable-header-offset').DataTable({
        fixedHeader: {
            header: true,
            
        }
    });

    // Init offset toggle
    var toggleType = document.querySelector('.toggle-offset');
    var toggleTypeInit = new Switchery(toggleType, { secondaryColor: '#FF7043'});

    // Toggle offset and fixed navbar
    toggleType.onchange = function() {
        if(toggleType.checked) {

            // Toggle necessary body and navbar classes
            $('body').children('.navbar').first().addClass('navbar-fixed-top');
            $('body').addClass('navbar-top');

            // Add offset to all
            table_basic.fixedHeader.headerOffset($('.navbar-fixed-top').height());
            table_footer.fixedHeader.headerOffset($('.navbar-fixed-top').height());
            table_reorder.fixedHeader.headerOffset($('.navbar-fixed-top').height());
            table_offset.fixedHeader.headerOffset($('.navbar-fixed-top').height());
        }
        else {

            // Toggle necessary body and navbar classes
            $('body').children('.navbar').first().removeClass('navbar-fixed-top');
            $('body').removeClass('navbar-top');

            // Remove offset from all
            table_basic.fixedHeader.headerOffset(0);
            table_footer.fixedHeader.headerOffset(0);
            table_reorder.fixedHeader.headerOffset(0);
            table_offset.fixedHeader.headerOffset(0);
        }
    };


    // ColReorder integration
    var table_reorder = $('.datatable-header-reorder').DataTable({
        fixedHeader: true,
        colReorder: true
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
