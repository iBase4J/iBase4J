/* ------------------------------------------------------------------------------
*
*  # Media library
*
*  Specific JS code additions for gallery_library.html page
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
        columnDefs: [
            {
            	orderable: false,
                width: '20px',
                targets: 0
            },
            {
            	orderable: false,
                width: '100px',
                targets: 1
            },
	        { 
	            orderable: false,
	            width: '90px',
	            targets: 6
	        }
        ],
        order: [[ 2, "asc" ]],
        lengthMenu: [ 25, 50, 75, 100 ],
        displayLength: 25,
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


    //
    // Single row selection
    //

    // Initialize table
    var media_library = $('.media-library').DataTable({
        drawCallback: function () {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').addClass('dropup');
        },
        preDrawCallback: function() {
            $(this).find('tbody tr').slice(-3).find('.dropdown, .btn-group').removeClass('dropup');
        }
    });

    // Toggle success class
    $('.media-library tbody td input[type=checkbox]').on('change', function () {
        if ($(this).is(':checked')) {
        	$(this).parents('tr').addClass('success');
            $.uniform.update();
        }
        else {
        	$(this).parents('tr').removeClass('success');
            $.uniform.update();
        }
    });



    // External table additions
    // ------------------------------

    // Lightbox
    $('[data-popup="lightbox"]').fancybox({
        padding: 3
    });


    // Styles checkboxes, radios
    $('.styled').uniform({
    	radioClass: 'choice'
    });


    // Add placeholder to the datatable filter option
    $('.dataTables_filter input[type=search]').attr('placeholder','Type to filter...');


    // Enable Select2 select for the length option
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        width: 'auto'
    });
    
});
