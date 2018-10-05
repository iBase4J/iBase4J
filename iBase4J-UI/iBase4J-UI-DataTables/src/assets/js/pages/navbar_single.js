/* ------------------------------------------------------------------------------
*
*  # Single navbar
*
*  Specific JS code additions for navbar_single.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Initialize switchery toggles
    // ------------------------------

    // Navbar type switchery toggle
    var toggleType = document.querySelector('.toggle-type');
    var toggleTypeInit = new Switchery(toggleType, {color: '#283133', secondaryColor: '#283133'});

    // Navbar position switchery toggle
    var togglePosition = document.querySelector('.toggle-position');
    var togglePositionInit = new Switchery(togglePosition, {color: '#283133', secondaryColor: '#283133'});



    // Change single navbar position
    // ------------------------------

    // Toggle navbar type state toggle
    toggleType.onchange = function() {
        if(toggleType.checked) {

            // Disable type switch
            togglePositionInit.disable();

            // Toggle necessary body and navbar classes
            $('body').children('.navbar').addClass('navbar-fixed-top');
            $('body').addClass('navbar-top');
        }
        else {

            // Enable type switch
            togglePositionInit.enable();


            // Toggle necessary body and navbar classes
            $('body').children('.navbar').removeClass('navbar-fixed-top');
            $('body').removeClass('navbar-top');
        }
    };

    // Toggle navbar position state toggle
    togglePosition.onchange = function() {
        if(togglePosition.checked) {
        
            // Disable position switch
            toggleTypeInit.disable();

            // Toggle necessary body and navbar classes
            $('body').children('.navbar').addClass('navbar-fixed-bottom');
            $('body').addClass('navbar-bottom');
        }
        else {

            // Enable position switch
            toggleTypeInit.enable();

            // Toggle necessary body and navbar classes
            $('body').children('.navbar').removeClass('navbar-fixed-bottom');
            $('body').removeClass('navbar-bottom');
        }
    };
    
});
