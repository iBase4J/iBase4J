/* ------------------------------------------------------------------------------
*
*  # Plupload multiple file uploader
*
*  Specific JS code additions for uploader_plupload.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Setup all runtimes
    $(".file-fileinput").pluploadQueue({
        runtimes: 'html5, html4, Flash, Silverlight',
        url: 'assets/demo_data/fileinput/plupload.json',
        chunk_size: '300Kb',
        unique_names: true,
        filters: {
            max_file_size: '300Kb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            }]
        },
        resize: {
            width: 320,
            height: 240,
            quality: 90
        }
    });


    // Setup flash version
    $(".flash-fileinput").pluploadQueue({

        // General settings
        runtimes: 'flash',
        url: 'assets/demo_data/fileinput/plupload.json',
        chunk_size: '300Kb',
        unique_names: true,
        filters: {
            max_file_size: '300Kb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            }]
        },

        // Resize images on clientside if we can
        resize: {
            width: 320,
            height: 240,
            quality: 90
        },

        // Flash settings
        flash_swf_url: 'assets/js/plugins/uploaders/plupload/files/Moxie.swf'
    });


    // Setup html5 version
    $(".html5-fileinput").pluploadQueue({

        // General settings
        runtimes: 'html5',
        url: 'assets/demo_data/fileinput/plupload.json',
        chunk_size: '300Kb',
        unique_names: true,
        filters: {
            max_file_size: '300Kb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            }]
        },

        // Resize images on clientside if we can
        resize: {
            width: 320,
            height: 240,
            quality: 90
        }
    });


    // Setup html4 version
    $(".html4-fileinput").pluploadQueue({
        runtimes: 'html4',
        url: 'assets/demo_data/fileinput/plupload.json',
        unique_names: true,
        filters: {
            max_file_size: '300Kb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            }]
        }
    });


    // Events
    $(".fileinput-events").pluploadQueue({

        // General settings
        runtimes: 'html5,flash,silverlight,html4',
        url: 'assets/demo_data/fileinput/plupload.json',
        chunk_size: '300Kb',
        unique_names: true,

        // Resize images on client-side if we can
        resize: {
            width: 320,
            height: 240,
            quality: 90
        },

        // Filters
        filters: {
            max_file_size: '300Kb',
            mime_types: [{
                title: "Image files",
                extensions: "jpg,gif,png"
            }]
        },

        // Flash settings
        flash_swf_url: '/plupload/js/Moxie.swf',

        // Silverlight settings
        silverlight_xap_url: '/plupload/js/Moxie.xap',

        // PreInit events, bound before any internal events
        preinit: {
            Init: function(up, info) {
                log('[Init]', 'Info:', info, 'Features:', up.features);
            },
            UploadFile: function(up, file) {
                log('[UploadFile]', file);
            }
        },

        // Post init events, bound after the internal events
        init: {
            Browse: function(up) {
                log('[Browse]'); // Called when file picker is clicked
            },

            Refresh: function(up) {
                log('[Refresh]'); // Called when the position or dimensions of the picker change
            },

            StateChanged: function(up) {
                log('[StateChanged]', up.state == plupload.STARTED ? "STARTED": "STOPPED"); // Called when the state of the queue is changed
            },

            QueueChanged: function(up) {
                log('[QueueChanged]'); // Called when queue is changed by adding or removing files
            },

            OptionChanged: function(up, name, value, oldValue) {
                log('[OptionChanged]', 'Option Name: ', name, 'Value: ', value, 'Old Value: ', oldValue); // Called when one of the configuration options is changed
            },

            BeforeUpload: function(up, file) {
                log('[BeforeUpload]', 'File: ', file); // Called right before the upload for a given file starts, can be used to cancel it if required
            },

            UploadProgress: function(up, file) {
                log('[UploadProgress]', 'File:', file, "Total:", up.total); // Called while file is being uploaded
            },

            FileFiltered: function(up, file) {
                log('[FileFiltered]', 'File:', file); // Called when file successfully files all the filters
            },

            FilesAdded: function(up, files) {
                log('[FilesAdded]'); // Called when files are added to queue

                plupload.each(files, function(file) {
                    log('  File:', file);
                });
            },

            FilesRemoved: function(up, files) {
                log('[FilesRemoved]'); // Called when files are removed from queue

                plupload.each(files, function(file) {
                    log('  File:', file);
                });
            },

            FileUploaded: function(up, file, info) {
                log('[FileUploaded] File:', file, "Info:", info); // Called when file has finished uploading
            },

            ChunkUploaded: function(up, file, info) {
                log('[ChunkUploaded] File:', file, "Info:", info); // Called when file chunk has finished uploading
            },

            UploadComplete: function(up, files) {
                log('[UploadComplete]'); // Called when all files are either uploaded or failed
            },

            Destroy: function(up) {
                log('[Destroy] '); // Called when fileinput is destroyed
            },

            Error: function(up, args) {
                log('[Error] ', args); // Called when error occurs
            }
        }
    });


    // Write log
    function log() {
        var str = "";

        plupload.each(arguments, function(arg) {
            var row = "";

            if (typeof(arg) != "string") {
                plupload.each(arg, function(value, key) {

                    // Convert items in File objects to human readable form
                    if (arg instanceof plupload.File) {

                        // Convert status to human readable
                        switch (value) {
                            case plupload.QUEUED:
                            value = 'QUEUED';
                            break;

                            case plupload.UPLOADING:
                            value = 'UPLOADING';
                            break;

                            case plupload.FAILED:
                            value = 'FAILED';
                            break;

                            case plupload.DONE:
                            value = 'DONE';
                            break;
                        }
                    }

                    if (typeof(value) != "function") {
                        row += (row ? ', ': '') + key + '=' + value;
                    }
                });

                str += row + " ";
            }
            else {
                str += arg + " ";
            }
        });

        var log = $('#log');
        log.append(str + "<br>");
        log.scrollTop(log[0].scrollHeight);
    }
    
});
