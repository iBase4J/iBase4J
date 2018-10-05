/* ------------------------------------------------------------------------------
*
*  # Detailed task view
*
*  Specific JS code additions for task_manager_detailed.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Datepicker
    $(".datepicker").datepicker({
        showOtherMonths: true,
        dateFormat: "d MM, y"
    });


    // Inline datepicker
    $(".datepicker-inline").datepicker({
        showOtherMonths: true,
        defaultDate: '07/26/2015'
    });


    // Switchery toggle
    var elems = Array.prototype.slice.call(document.querySelectorAll('.switchery'));
    elems.forEach(function(html) {
        var switchery = new Switchery(html);
    });


    // CKEditor
    CKEDITOR.replace( 'add-comment', {
        height: '200px',
        removeButtons: 'Subscript,Superscript',
        toolbarGroups: [
            { name: 'styles' },
            { name: 'editing',     groups: [ 'find', 'selection' ] },
            { name: 'forms' },
            { name: 'basicstyles', groups: [ 'basicstyles' ] },
            { name: 'paragraph',   groups: [ 'list', 'blocks', 'align' ] },
            { name: 'links' },
            { name: 'insert' },
            { name: 'colors' },
            { name: 'tools' },
            { name: 'others' },
            { name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] }
        ]
    });
    
});
