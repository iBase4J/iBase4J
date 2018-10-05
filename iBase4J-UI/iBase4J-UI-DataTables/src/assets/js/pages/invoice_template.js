/* ------------------------------------------------------------------------------
*
*  # Invoice template
*
*  Specific JS code additions for invoice_template.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Setup CKEditor
    CKEDITOR.disableAutoInline = true;
    CKEDITOR.dtd.$removeEmpty['i'] = false;
    CKEDITOR.config.startupShowBorders = false;
    CKEDITOR.config.extraAllowedContent = 'table(*)';


    // Initialize inline editor
    var editor = CKEDITOR.inline('invoice-editable');
    
});
