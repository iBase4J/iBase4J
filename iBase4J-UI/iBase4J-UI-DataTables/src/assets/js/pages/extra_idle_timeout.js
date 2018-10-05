/* ------------------------------------------------------------------------------
*
*  # Session timeout
*
*  Specific JS code additions for extra_session_timeout.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Idle timeout
    $.sessionTimeout({
        heading: 'h5',
        title: 'Idle Timeout',
        message: 'Your session is about to expire. Do you want to stay connected?',
        warnAfter: 5000,
        redirAfter: 15000,
        keepAliveUrl: '/',
        redirUrl: 'login_unlock.html',
        logoutUrl: 'login_advanced.html'
    });
    
});
