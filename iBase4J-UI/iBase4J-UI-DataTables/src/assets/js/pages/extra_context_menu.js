/* ------------------------------------------------------------------------------
*
*  # Context menu
*
*  Specific JS code additions for extra_context_menu.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic setup
    // ------------------------------

    // Initialize using JS
    $('.context-js').contextmenu({
        target: '.context-js-menu'
    });


    // Exclude elements
    $('.context-deactivate').contextmenu({
        target: '.context-deactivate-menu',
        before: function (e, element, target) {
            e.preventDefault();
            if (e.target.tagName == 'CODE') {
                e.preventDefault();
                this.closemenu();
                return false;
            }
            return true;
        }
    });


    // Dynamic replacement
    $('.context-dynamic').contextmenu({
        target: '.context-dynamic-menu',
        before: function(e) { 
            this.getMenu().find('li').eq(2).find('a').html('<i class="icon-stack"></i> Item has been changed');
            return true;
        }
    });


    // Name on selection
    $('.context-selection').contextmenu({
        target: '.context-selection-menu',
        onItem: function(context, e) {
            alert($(e.target).text());
        }
    });



    // Callbacks
    // ------------------------------

    // onShow callback
    $('.context-show-menu').on('show.bs.context',function () {
        alert('onShow event fired');
    });

    // onShown callback
    $('.context-shown-menu').on('shown.bs.context',function () {
        alert('onShown event fired');
    });

    // onHide callback
    $('.context-hide-menu').on('hide.bs.context',function () {
        alert('onHide event fired');
    });

    // onHidden callback
    $('.context-hidden-menu').on('hidden.bs.context',function () {
        alert('onHidden event fired');
    });
    
});
