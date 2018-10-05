/* ------------------------------------------------------------------------------
*
*  # Dynamic tree views
*
*  Specific JS code additions for extra_trees.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic setup
    // ------------------------------

    // Basic example
    $(".tree-default").fancytree({
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    // Load JSON data
    $(".tree-ajax").fancytree({
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    // Embed JSON data
    $(".tree-json").fancytree({
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    // Child counter
    $(".tree-child-count").fancytree({
        extensions: ["childcounter"],
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        childcounter: {
            deep: true,
            hideZeros: true,
            hideExpanded: true
        },
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    // Drag and drop support
    $(".tree-drag").fancytree({
        extensions: ["dnd"],
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        dnd: {
            autoExpandMS: 400,
            focusOnClick: true,
            preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
            preventRecursiveMoves: true, // Prevent dropping nodes on own descendants
            dragStart: function(node, data) {
                return true;
            },
            dragEnter: function(node, data) {
                return true;
            },
            dragDrop: function(node, data) {

                // This function MUST be defined to enable dropping of items on the tree.
                data.otherNode.moveTo(node, data.hitMode);
            }
        },
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    // Editable nodes
    $(".tree-editable").fancytree({
        extensions: ["edit"],
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        edit: {
            adjustWidthOfs: 0,
            inputCss: {minWidth: "0"},
            triggerStart: ["f2", "dblclick", "shift+click", "mac+enter"],
            save: function(event, data) {
                alert("save " + data.input.val()); // Save data.input.val() or return false to keep editor open
            }
        }
    });



    // Selectable nodes
    // ------------------------------

    // Single selection
    $(".tree-radio").fancytree({
        checkbox: true,
        selectMode: 1,
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        }    
    });


    // Multiple selection
    $(".tree-checkbox").fancytree({
        checkbox: true,
        selectMode: 2,
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        }
    });


    // Disable selections
    $(".tree-checkbox-options").fancytree({
        checkbox: true,
        selectMode: 2
    });


    // Hierarchical select
    $(".tree-checkbox-hierarchical").fancytree({
        checkbox: true,
        selectMode: 3
    });


    //
    // Toggle checkbox state
    //

    // Initialize
    $(".tree-checkbox-toggle").fancytree({
        checkbox: true,
        selectMode: 2,
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        }    
    });

    // Initialize switchery toggle
    var switcherySelect = document.querySelector('.switchery-select');
    var initSelect = new Switchery(switcherySelect);

    // Change checkbox states
    switcherySelect.onchange = function() {
        if(switcherySelect.checked) {
            $(".tree-checkbox-toggle").fancytree("getTree").visit(function(node){
                node.setSelected(true);
            });
            return false;
        }
        else {
            $(".tree-checkbox-toggle").fancytree("getTree").visit(function(node){
                node.setSelected(false);
            });
            return false;
        }
    };



    // Advanced examples
    // ------------------------------

    //
    // Toggle state
    //

    // Initialize switchery toggle
    var switchery = document.querySelector('#tree-disabled');
    var init = new Switchery(switchery);

    // Do something on state change
    switchery.onchange = function() {
        if(switchery.checked) {
            $(".tree-toggle").fancytree("disable");
        }
        else {
            $(".tree-toggle").fancytree("enable");
        }
    };

    // Initialize
    $(".tree-toggle").fancytree({
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        init: function(event, data) {
            $('.has-tooltip .fancytree-title').tooltip();
        }
    });


    //
    // Sorting
    //

    // Initialize
    $(".tree-sorting").fancytree();

    // Sort tree
    $('.sort-tree').on('click', function() {
        var node = $(".tree-sorting").fancytree("getRootNode");
        node.sortChildren(null, true);
    });

    // Sort active nodes
    $('.sort-branch').on('click', function() {
        var node = $(".tree-sorting").fancytree("getActiveNode");

        // Custom compare function (optional) that sorts case insensitive
        var cmp = function(a, b) {
            a = a.title.toLowerCase();
            b = b.title.toLowerCase();
            return a > b ? 1 : a < b ? -1 : 0;
        };
        node.sortChildren(cmp, false);
    });


    //
    // Tree persistence
    //

    // Initialize
    $(".tree-persistence").fancytree({
        extensions: ["persist"],
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        checkbox: true,
        persist: {
            overrideSource: false, // true: cookie takes precedence over `source` data attributes.
            store: "auto" // 'cookie', 'local': use localStore, 'session': sessionStore
        },
        postProcess: function(event, data) {
            var prefix = data.node.getIndexHier() + ".";
            $.each(data.response, function(idx, childEntry) {
                if( childEntry.key == null ) {
                    childEntry.key = prefix + (idx + 1);
                    childEntry.title += " (" + childEntry.key + ")";
                }
            })
        }
    });
    var tree5 = $(".tree-persistence").fancytree("getTree");

    // Reset cookies on button click
    $('.reset-cookies').on('click', function() {
        tree5.clearCookies();
    });


    //
    // Table tree
    //

    $(".tree-table").fancytree({
        extensions: ["table"],
        checkbox: true,
        table: {
            indentation: 20,      // indent 20px per node level
            nodeColumnIdx: 2,     // render the node title into the 2nd column
            checkboxColumnIdx: 0  // render the checkboxes into the 1st column
        },
        source: {
            url: "assets/demo_data/fancytree/fancytree.json"
        },
        lazyLoad: function(event, data) {
            data.result = {url: "ajax-sub2.json"}
        },
        renderColumns: function(event, data) {
            var node = data.node,
            $tdList = $(node.tr).find(">td");

            // (index #0 is rendered by fancytree by adding the checkbox)
            $tdList.eq(1).text(node.getIndexHier()).addClass("alignRight");

            // (index #2 is rendered by fancytree)
            $tdList.eq(3).text(node.key);
            $tdList.eq(4).addClass('text-center').html("<input type='checkbox' class='styled' name='like' value='" + node.key + "'>");

            // Style checkboxes
            $(".styled").uniform({radioClass: 'choice'});
        }
    });

    // Handle custom checkbox clicks
    $(".tree-table").delegate("input[name=like]", "click", function (e){
        var node = $.ui.fancytree.getNode(e),
        $input = $(e.target);
        e.stopPropagation(); // prevent fancytree activate for this row
        if($input.is(":checked")){
            alert("like " + $input.val());
        }
        else{
            alert("dislike " + $input.val());
        }
    });
    
});
