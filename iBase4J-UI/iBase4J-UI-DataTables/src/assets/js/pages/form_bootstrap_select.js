/* ------------------------------------------------------------------------------
*
*  # Bootstrap selects
*
*  Specific JS code additions for form_bootstrap_select.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Override defaults
    $.fn.selectpicker.defaults = {
        iconBase: '',
        tickIcon: 'icon-checkmark3'
    }



    // Basic setup
    // ------------------------------

    // Basic select
    $('.bootstrap-select').selectpicker();


    // Dynamic refresh
    $('.rm-alaska').click(function() {
        $('.refresh-example').find('[value=AK]').remove();
        $('.refresh-example').selectpicker('refresh');
    });
    $('.rm-hawaii').click(function() {
        $('.refresh-example').find('[value=HI]').remove();
        $('.refresh-example').selectpicker('refresh');
    });


    // Toggle state
    $('.sel-disable').click(function() {
        $('.disable-example').prop('disabled',true);
        $('.disable-example').selectpicker('refresh');
    });
    $('.sel-enable').click(function() {
        $('.disable-example').prop('disabled',false);
        $('.disable-example').selectpicker('refresh');
    });


    //
    // Additional styling
    //

    // Custom color result color
    $('.bootstrap-select-solid').selectpicker();
    $('.bootstrap-select-solid').on('show.bs.dropdown', function () {
        $(this).find('.dropdown-menu').addClass('bg-slate');
    });


    // Custom menu color
    $('.bootstrap-select-menu-color').selectpicker();
    $('.bootstrap-select-menu-color').on('show.bs.dropdown', function () {
        $(this).find('.dropdown-menu').addClass('bg-teal-400');
    });



    // Methods
    // ------------------------------

    // Single value method
    $('#set-single-value').on('click', function() {
        $('.set-single-value').selectpicker('val', 'NV');
    });


    // Multiple values method
    $('#set-multiple-values').on('click', function() {
        $('.set-multiple-values').selectpicker('val', ['NV','AZ']);
    });


    // Select all method
    $('#select-all-values').on('click', function() {
        $('.select-all-values').selectpicker('selectAll');
    });


    // Deselect all method
    $('#deselect-all-values').on('click', function() {
        $('.select-all-values').selectpicker('deselectAll');
    });


    // Show method
    $('#show-menu').on('click', function() {
        $('.toggle-menu').selectpicker('show');
    });


    // Hide method
    $('#hide-menu').on('click', function() {
        $('.toggle-menu').selectpicker('hide');
    });

});
