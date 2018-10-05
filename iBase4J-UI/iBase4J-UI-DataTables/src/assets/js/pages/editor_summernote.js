/* ------------------------------------------------------------------------------
*
*  # Summernote editor
*
*  Specific JS code additions for editor_summernote.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic editors
    // ------------------------------

    // Default initialization
    $('.summernote').summernote();


    // Control editor height
    $('.summernote-height').summernote({
        height: 400
    });


    // Air mode
    $('.summernote-airmode').summernote({
        airMode: true
    });



    // Click to edit
    // ------------------------------

    // Edit
    $('#edit').on('click', function() {
        $('.click2edit').summernote({focus: true});
    })

    // Save
    $('#save').on('click', function() {
        var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
        $('.click2edit').destroy();
    })

    // Related form components
    // ------------------------------

    // Styled checkboxes/radios
    $(".link-dialog input[type=checkbox], .note-modal-form input[type=radio]").uniform({
        radioClass: 'choice'
    });


    // Styled file input
    $(".note-image-input").uniform({
        fileButtonClass: 'action btn bg-warning-400'
    });

});
