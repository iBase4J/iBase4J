/* ------------------------------------------------------------------------------
*
*  # jQuery UI interactions
*
*  Specific JS code additions for jqueryui_interactions.html page
*
*  Version: 1.0
*  Latest update: Nov 12, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Draggable
    // -------------------------

    // Basic setup
    $(".draggable-element").draggable({
        containment: "#draggable-default-container"
    });


    //
    // Constrain movement
    //

    // Both
    $("#draggable-move-both").draggable({
        containment: "#draggable-containment-container"
    });

    // Vertical
    $("#draggable-move-vertical").draggable({
        containment: "#draggable-containment-container",
        axis: "y"
    });

    // Horizontal
    $("#draggable-move-horizontal").draggable({
        containment: "#draggable-containment-container",
        axis: "x"
    });
 

    //
    // Revert position
    //

    // Element
    $("#draggable-revert-element").draggable({
        containment: "#draggable-revert-container",
        revert: true
    });

    // Clone helper
    $("#draggable-revert-clone").draggable({
        containment: "#draggable-revert-container",
        revert: true,
        helper: "clone"
    });

    // Speed
    $("#draggable-revert-speed").draggable({
        containment: "#draggable-revert-container",
        revert: true,
        revertDuration: 1500
    });


    //
    // Cursors
    //

    // Move cursor
    $("#draggable-cursor-move").draggable({
        containment: "#draggable-cursor-container",
        cursor: "move"
    });

    // Crosshair cursor
    $( "#draggable-cursor-crosshair" ).draggable({
        containment: "#draggable-cursor-container",
        cursor: "crosshair"
    });

    // Bottom cursor
    $( "#draggable-cursor-bottom" ).draggable({
        containment: "#draggable-cursor-container",
        cursorAt: {
            bottom: 0
        }
    });


    //
    // Handles
    //

    // Text
    $( "#draggable-handle-text" ).draggable({
        containment: "#draggable-handle-container",
        handle: "span"
    });

    // Icon
    $( "#draggable-handle-icon" ).draggable({
        containment: "#draggable-handle-container",
        handle: ".handle-icon"
    });

    // Exception
    $( "#draggable-handle-exception" ).draggable({
        containment: "#draggable-handle-container",
        cancel: "span"
    });


    //
    // Events
    //

    // Define elements
    var $start_counter = $("#draggable-event-start"),
        $drag_counter = $("#draggable-event-drag"),
        $stop_counter = $("#draggable-event-stop"),
        counts = [0, 0, 0];


    // Start event
    $start_counter.draggable({
        containment: "#draggable-events-container",
        start: function() {
            counts[0]++;
            updateCounterStatus( $start_counter, counts[0]);
        }
    });

    // Drag event
    $drag_counter.draggable({
        containment: "#draggable-events-container",
        drag: function() {
            counts[1]++;
            updateCounterStatus($drag_counter, counts[1]);
        }
    });

    // Stop event
    $stop_counter.draggable({
        containment: "#draggable-events-container",
        stop: function() {
            counts[2]++;
            updateCounterStatus($stop_counter, counts[2]);
        }
    });

    // Update counter text
    function updateCounterStatus( $event_counter, new_count ) {
        $( ".event-count", $event_counter ).text( new_count );
    }



    // Droppable
    // -------------------------

    //
    // Basic functionality
    //

    // Drag
    $("#droppable-basic-element").draggable({
        containment: "#droppable-basic-container"
    });

    // Drop
    $("#droppable-basic-target").droppable({
        drop: function(event, ui) {
            $(this).addClass("bg-success-400 border-success-400 text-white").html("<span>Dropped!</span>");
        }
    });


    //
    // Accept drop
    //

    // Drag
    $("#droppable-accept-yes, #droppable-accept-no").draggable({
        containment: "#droppable-accept-container"
    });

    // Drop
    $("#droppable-accept-target").droppable({
        accept: "#droppable-accept-yes",
        drop: function(event, ui) {
            $(this).addClass("bg-success-400 border-success-400 text-white").html("<span>Dropped!</span>");
        }
    });


    //
    // Revert draggable position
    //

    // Drag (revert on drop)
    $("#droppable-revert-drop").draggable({
        containment: "#droppable-revert-container",
        revert: "valid"
    });

    // Drag (revert always except drop)
    $("#droppable-revert-except").draggable({
        containment: "#droppable-revert-container",
        revert: "invalid"
    });
 
    // Drop
    $("#droppable-revert-target").droppable({
        drop: function(event, ui) {
            $(this).addClass("bg-success-400 border-success-400 text-white").html("<span>Dropped!</span>");
        }
    });


    //
    // Visual feedback
    //

    // Drag
    $("#droppable-visual-element").draggable({
        containment: "#droppable-visual-container"
    });

    // Active drop
    $("#droppable-visual-target-active").droppable({
        containment: '#droppable-visual-container',
        accept: "#droppable-visual-element",
        activeClass: "bg-slate border-slate text-white",
        drop: function(event, ui) {
            $(this).addClass("bg-success-400 border-success-400 text-white").html("<span>Dropped!</span>");
        }
    });

    // Hover drop
    $("#droppable-visual-target-hover").droppable({
        containment: '#droppable-visual-container',
        hoverClass: "bg-blue border-blue text-white",
        drop: function(event, ui) {
            $(this).addClass("bg-teal-400 border-teal-400 text-white").html("<span>Dropped!</span>");
        }
    });



    // Resizable
    // -------------------------

    // Basic functionality
    $("#resizable-basic-element").resizable({
        minWidth: 50,
        minHeight: 50
    });

    // Animated resize
    $("#resizable-animate-element").resizable({
        minWidth: 50,
        minHeight: 50,
        animate: true
    });

    // Preserve aspect ratio
    $("#resizable-ratio-element").resizable({
        minWidth: 50,
        minHeight: 50,
        aspectRatio: 16 / 9
    });

    // Synchronous resize
    $("#resizable-sync-element1").resizable({
        minWidth: 50,
        minHeight: 50,
        alsoResize: "#resizable-sync-element2"
    });
    $("#resizable-sync-element2").resizable({
        minWidth: 50,
        minHeight: 50,
        alsoResize: "#resizable-sync-element1"
    });



    // Selectable
    // -------------------------

    // Basic functionality
    $("#selectable-basic").selectable();

    // Serialize
    $("#selectable-serialize").selectable({
        stop: function() {
            var result = $("#select-result").empty();
            $(".ui-selected", this).each(function() {
                var index = $("#selectable-serialize li").index(this);
                result.append(" #" + (index + 1));
            });
        }
    });



    // Sortable
    // -------------------------

    // Basic functionality
    $("#sortable-list-basic").sortable();
    $("#sortable-list-basic").disableSelection();


    // Placeholder
    $( "#sortable-list-placeholder" ).sortable({
        placeholder: "sortable-placeholder",
        start: function(e, ui){
            ui.placeholder.height(ui.item.outerHeight());
        }
    });
    $( "#sortable-list-placeholder" ).disableSelection();


    // Connected lists
    $("#sortable-list-first, #sortable-list-second").sortable({
        connectWith: ".selectable-demo-connected"
    }).disableSelection();


    //
    // Include/exclude items
    //

    // Specify sort items
    $("#sortable-list-specify").sortable({
        items: "li:not(.ui-state-disabled)"
    });

    // Exclude items
    $("#sortable-list-cancel").sortable({
        cancel: ".ui-state-disabled"
    });

    // Disable selections
    $("#sortable-list-specify li, #sortable-list-cancel li").disableSelection();

});
