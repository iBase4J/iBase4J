/* ------------------------------------------------------------------------------
*
*  # Dropzone multiple file uploader
*
*  Specific JS code additions for uploader_dropzone.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Defaults
    Dropzone.autoDiscover = false;


    // Single file
    $("#dropzone_single").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        maxFilesize: 1, // MB
        maxFiles: 1,
        dictDefaultMessage: 'Drop file to upload <span>or CLICK</span>',
        autoProcessQueue: false,
        init: function() {
            this.on('addedfile', function(file){
                if (this.fileTracker) {
                this.removeFile(this.fileTracker);
            }
                this.fileTracker = file;
            });
        }
    });


    // Multiple files
    $("#dropzone_multiple").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        dictDefaultMessage: 'Drop files to upload <span>or CLICK</span>',
        maxFilesize: 0.1 // MB
    });


    // Accepted files
    $("#dropzone_accepted_files").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        dictDefaultMessage: 'Drop files to upload <span>or CLICK</span>',
        maxFilesize: 1, // MB
        acceptedFiles: 'image/*'
    });


    // Removable thumbnails
    $("#dropzone_remove").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        dictDefaultMessage: 'Drop files to upload <span>or CLICK</span>',
        maxFilesize: 1, // MB
        addRemoveLinks: true
    });


    // File limitations
    $("#dropzone_file_limits").dropzone({
        paramName: "file", // The name that will be used to transfer the file
        dictDefaultMessage: 'Drop files to upload <span>or CLICK</span>',
        maxFilesize: 0.4, // MB
        maxFiles: 4,
        maxThumbnailFilesize: 1,
        addRemoveLinks: true
    });
    
});
