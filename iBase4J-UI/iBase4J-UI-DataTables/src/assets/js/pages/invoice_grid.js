/* ------------------------------------------------------------------------------
*
*  # Invoice grid
*
*  Specific JS code additions for invoice_grid.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Change vertical orientation of last 3 dropdowns in table
    $('.content > .row').slice(-1).find('.dropdown, .btn-group').addClass('dropup');


    // Styled checkboxes, radios
    $('.styled').uniform();

});
