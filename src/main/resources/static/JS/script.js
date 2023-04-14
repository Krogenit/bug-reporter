'use strict';


window.onload = initApp;

function initApp() {


    function addProject() {

        var newOptions = {

            "key_1": "test 1",

            "key_2": "test 2"

        };

        var select = $('#project-field');
        if (select.prop) {
            var options = select.prop('options');
        }
        else {
            var options = select.attr('options');
        }
        $.each(newOptions, function (val, text) {
            options[options.length] = new Option(text, val);
        });

    }


    addProject();




}
