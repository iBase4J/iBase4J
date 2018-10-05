/* ------------------------------------------------------------------------------
*
*  # Task grid view
*
*  Specific JS code additions for task_manager_grid.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Reverse last 3 dropdowns orientation
    $('.content > .row').slice(-1).find('.dropdown, .btn-group').addClass('dropup');


    // Multiple switchery toggles
    if (Array.prototype.forEach) {
        var elems = Array.prototype.slice.call(document.querySelectorAll('.switch-mode'));

        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    }
    else {
        var elems = document.querySelectorAll('.switch-mode');

        for (var i = 0; i < elems.length; i++) {
            var switchery = new Switchery(elems[i]);
        }
    }
    
});
