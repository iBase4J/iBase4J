/* ------------------------------------------------------------------------------
*
*  # User list
*
*  Specific JS code additions for user_pages_list.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Scroll to the bottom of the chat
    $('.modal').on('shown.bs.modal', function() {
        $('.chat-list').animate({
            scrollTop: $(this).height()
        }, 500)
    });
    
});
