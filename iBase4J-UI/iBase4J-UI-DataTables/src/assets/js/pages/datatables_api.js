/* ------------------------------------------------------------------------------
*
*  # Datatables API
*
*  Specific JS code additions for datatable_api.html page
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


    // Single row selection
    var singleSelect = $('.datatable-selection-single').DataTable();
    $('.datatable-selection-single tbody').on('click', 'tr', function() {
        if ($(this).hasClass('success')) {
            $(this).removeClass('success');
        }
        else {
            singleSelect.$('tr.success').removeClass('success');
            $(this).addClass('success');
        }
    });


    // Multiple rows selection
    $('.datatable-selection-multiple').DataTable();
    $('.datatable-selection-multiple tbody').on('click', 'tr', function() {
        $(this).toggleClass('success');
    });


    // Individual column searching with text inputs
    $('.datatable-column-search-inputs tfoot td').not(':last-child').each(function () {
        var title = $('.datatable-column-search-inputs thead th').eq($(this).index()).text();
        $(this).html('<input type="text" class="form-control input-sm" placeholder="Search '+title+'" />');
    });
    var table = $('.datatable-column-search-inputs').DataTable();
    table.columns().every( function () {
        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            that.search(this.value).draw();
        });
    });


    // Individual column searching with selects
    $('.datatable-column-search-selects').DataTable({
        initComplete: function () {
            this.api().columns().every( function() {
                var column = this;
                var select = $('<select class="filter-select" data-placeholder="Filter"><option value=""></option></select>')
                    .appendTo($(column.footer()).not(':last-child').empty())
                    .on('change', function() {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    });
 
                column.data().unique().sort().each( function (d, j) {
                    select.append('<option value="'+d+'">'+d+'</option>')
                });
            });
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


    // Enable Select2 select for individual column searching
    $('.filter-select').select2();
    
});
