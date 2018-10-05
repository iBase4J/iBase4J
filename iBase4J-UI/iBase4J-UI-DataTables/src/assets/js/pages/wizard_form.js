/* ------------------------------------------------------------------------------
*
*  # Form wizard
*
*  Specific JS code additions for wizard_form.html page
*
*  Version: 1.1
*  Latest update: Dec 25, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Wizard examples
    // ------------------------------

    // Basic setup
    $(".form-basic").formwizard({
        disableUIStyles: true,
        disableInputFields: false,
        inDuration: 150,
        outDuration: 150
    });


    // Cancel the post
    $(".form-post-cancel").formwizard({
        disableUIStyles: true,
        disableInputFields: false,
        formPluginEnabled: true,
        inDuration: 150,
        outDuration: 150,
        formOptions: {
            success: function(data){
                swal({title: "Congratulations!", text: "You are registered now!", type: "success", timer: 2000, confirmButtonColor: "#43ABDB"})
            },
            dataType: 'json',
            resetForm: true, 
            beforeSubmit: function(){
                return confirm("This is the beforeSubmit callback, do you want to submit?");
            },
            beforeSend: function(){
                return confirm("This is the beforeSend callback, do you want to submit?");
            },
            beforeSerialize: function(){
                return confirm("This is the beforeSerialize callback, do you want to submit?");
            }
        }
    });


    // With validation
    $(".form-validation").formwizard({
        disableUIStyles: true,
        validationEnabled: true,
        inDuration: 150,
        outDuration: 150,
        validationOptions: {
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-error-label',
            successClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Styled checkboxes, radios, bootstrap switch
                if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container') ) {
                    if(element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
                        error.appendTo( element.parent().parent().parent().parent() );
                    }
                     else {
                        error.appendTo( element.parent().parent().parent().parent().parent() );
                    }
                }

                // Unstyled checkboxes, radios
                else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
                    error.appendTo( element.parent().parent().parent() );
                }

                // Input with icons and Select2
                else if (element.parents('div').hasClass('has-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Inline checkboxes, radios
                else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
                    error.appendTo( element.parent().parent() );
                }

                // Input group, styled file input
                else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                else {
                    error.insertAfter(element);
                }
            },
            rules: {
                email: {
                    email: true
                }
            }
        }
    });

    // Update single file input state
    $(".form-validation").on("step_shown", function(event, data) {
        $.uniform.update();
    });


    // AJAX form submit
    $(".form-ajax").formwizard({
        disableUIStyles: true,
        formPluginEnabled: true,
        disableInputFields: false,
        inDuration: 150,
        outDuration: 150,
        formOptions :{
            success: function(data){
                swal({title: "Congratulations!", text: "You are registered now!", type: "success", timer: 2000, confirmButtonColor: "#43ABDB"})
            },
            beforeSubmit: function(data){
                $("#ajax-data").css({borderTop: '1px solid #ddd', padding: 15}).html("<span class='text-semibold'>Data sent to the server:</span> " + $.param(data));
            },
            dataType: 'json',
            resetForm: true
        }
    });


    //
    // Add steps dynamically
    //

    // Initialize wizard
    $(".form-add-steps").formwizard({
        disableUIStyles: true,
        disableInputFields: false,
        inDuration: 150,
        outDuration: 150
    });


    // Append step on button click
    $("#add-step").on('click', function(){
        $(".step-wrapper").append($(".extra-steps .step:first"));
        $(".form-add-steps").formwizard("update_steps");  
        return false;
    });



    // Initialize plugins
    // ------------------------------

    // Select2 selects
    $('.select').select2();


    // Simple select without search
    $('.select-simple').select2({
        minimumResultsForSearch: Infinity
    });


    // Styled checkboxes and radios
    $('.styled').uniform({
        radioClass: 'choice'
    });


    // Styled file input
    $('.file-styled').uniform({
        fileButtonClass: 'action btn bg-blue'
    });


    // Uncomment if you use styled checkboxes/radios to update them dynamically when step changed
    $(".form-basic, .form-validation, .form-add-steps, .form-ajax").on("step_shown", function(event, data){
        //$.uniform.update();
    });
    
});
