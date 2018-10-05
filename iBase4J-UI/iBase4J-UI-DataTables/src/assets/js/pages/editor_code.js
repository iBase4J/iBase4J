/* ------------------------------------------------------------------------------
*
*  # Ace code editor
*
*  Specific JS code additions for editor_code.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Javascript editor
    var js_editor = ace.edit("javascript_editor");
        js_editor.setTheme("ace/theme/monokai");
        js_editor.getSession().setMode("ace/mode/javascript");
        js_editor.setShowPrintMargin(false);


    // HTML editor
    var html_editor = ace.edit("html_editor");
        html_editor.setTheme("ace/theme/monokai");
        html_editor.getSession().setMode("ace/mode/html");
        html_editor.setShowPrintMargin(false);


    // CSS editor
    var css_editor = ace.edit("css_editor");
        css_editor.setTheme("ace/theme/monokai");
        css_editor.getSession().setMode("ace/mode/css");
        css_editor.setShowPrintMargin(false);


    // JSON editor
    var json_editor = ace.edit("json_editor");
        json_editor.setTheme("ace/theme/monokai");
        json_editor.getSession().setMode("ace/mode/json");
        json_editor.setShowPrintMargin(false);


    // LESS editor
    var less_editor = ace.edit("less_editor");
        less_editor.setTheme("ace/theme/monokai");
        less_editor.getSession().setMode("ace/mode/less");
        less_editor.setShowPrintMargin(false);


    // PHP editor
    var php_editor = ace.edit("php_editor");
        php_editor.setTheme("ace/theme/monokai");
        php_editor.getSession().setMode("ace/mode/php");
        php_editor.setShowPrintMargin(false);


    // Ruby editor
    var ruby_editor = ace.edit("ruby_editor");
        ruby_editor.setTheme("ace/theme/monokai");
        ruby_editor.getSession().setMode("ace/mode/ruby");
        ruby_editor.setShowPrintMargin(false);


    // SASS editor
    var sass_editor = ace.edit("sass_editor");
        sass_editor.setTheme("ace/theme/monokai");
        sass_editor.getSession().setMode("ace/mode/sass");
        sass_editor.setShowPrintMargin(false);


    // Coffee editor
    var coffee_editor = ace.edit("coffee_editor");
        coffee_editor.setTheme("ace/theme/monokai");
        coffee_editor.getSession().setMode("ace/mode/coffee");
        coffee_editor.setShowPrintMargin(false);


    // Handlebars editor
    var handlebars_editor = ace.edit("handlebars_editor");
        handlebars_editor.setTheme("ace/theme/monokai");
        handlebars_editor.getSession().setMode("ace/mode/handlebars");
        handlebars_editor.setShowPrintMargin(false);

});
