const weboo = {
    commands: {},

    exeCommandsArray(data) {
        if (!Array.isArray(data)) {
            data = [data];
        }
        for (let num in data) {
            const c = data[num]
            this.exeCommand(c);
        }

    },

    exeCommand(c) {
        if (weboo.commands[c.name] == undefined) {
            console.error("Cannot find command ", c);
            alert("Cannot find command '" + c.name + "'");
        }
        return weboo.commands[c.name].apply(c);
    },

    handleEventOnServer(widgetId, eventName, handlerId, result) {
        const headers = {
            Weboo_widget_id: widgetId,
            Weboo_handler_id: handlerId,
            Weboo_event_name: eventName,
            Weboo_page_id: WEBOO_PAGE_ID
        }
        $.ajax({
            method: 'POST',
            url: '/weboo/event',
            headers: headers,
            data: result
        }).done(function (data, status) {
                if (status === "success") {
                    if ($.isArray(data.commands)) {
                        weboo.exeCommandsArray(data.commands)
                    }
                    // TODO:: errors handle
                }
                console.log("headers status", data, status);
            }
        );
    },

    form(formId) {
        $("#" + formId).on('submit', function (event) {
            event.preventDefault();
            event.stopPropagation();

            // <input type='hidden' name="Weboo_widget_id" value='{id}'>
            // <input type='hidden' name='weboo_handler_id' value='{weboo_handler_id}'>
            // <input type='hidden' name='weboo_event_type' value='{weboo_event_type}'>

            let form = $(this);

            let jsonForm = form.serializeObject();
            const headers = {
                weboo_widget_id: jsonForm.weboo_widget_id,
                weboo_handler_id: jsonForm.weboo_handler_id,
                weboo_event_type: jsonForm.weboo_event_type
            }
            delete jsonForm.weboo_widget_id;
            delete jsonForm.weboo_handler_id;
            delete jsonForm.weboo_event_type;

            //let actionUrl = form.attr('action');
            $.ajax({
                method: "POST",
                url: "/weboo/form", // actionUrl,
                data: JSON.stringify(jsonForm),
                contentType: "application/json",
                dataType: 'json',
                headers: headers
            }).done(function (res) {
                $('.invalid-feedback', form).remove();
                $('.is-invalid', form).removeClass('is-invalid');
                //form.removeClass("was-validated");
                console.log("res", res);
                // console.log("res.formErrors 2", res.formErrors);

                if (res.formErrors) {
                    res.formErrors.forEach((err) => {
                        console.log(`Error field '${err.field}' message: ${err.message}`);
                        $(`#${formId} [name='${err.field}']`).addClass('is-invalid')
                            .after(`<div class="invalid-feedback">
                              ${err.message}
                        </div>`);

                    });
                }
                if (res.commands) {
                    weboo.exeCommands(res);
                }

            }).fail(function (err) {
                console.log("error ", err.responseJSON)
                Swal.fire({
                    icon: 'error',
                    title: 'Oops... error',
                    html: err.responseJSON.message
                });
            })
        });
    }
}

// weboo = {}
// weboo.commands = {}


// weboo.exeCommands = function (data) {
//     if (!Array.isArray(data)) {
//         data = [data];
//     }
//     for (let num in data) {
//         const c = data[num]
//         weboo.commands[c.name].apply(c)
//     }
//
// }


// weboo.findEvents = function () {
// // Select all elements with the data-weboo-events attribute
//     const elements = document.querySelectorAll('[data-weboo-events]');
//
// // Iterate over the selected elements
//     elements.forEach(element => {
//
//         const ee = element.dataset.webooEvents;
//         console.log("ee", ee);
//
//
//         // const json = JSON.parse(ee);
//         // const jsCode = json[0].jsCode;
//         // console.log("jsCode ", jsCode);
//
//         // Get the value of the data-weboo-events attribute
//         const dataWebooEvents = element.getAttribute('data-weboo-events');
//
//         // Parse the JSON string to a JavaScript object
//         console.log("json", dataWebooEvents);
//         const events = JSON.parse(dataWebooEvents);
//
//         console.log("Events ", events);
//
//         // Iterate over the events array
//         events.forEach(event => {
//             // Get the jsCode property
//             const jsCode = event.jsCode;
//
//             // Create a new function with the jsCode as its body
//             const func = new Function('e', jsCode);
//
//             // Add an event listener to the element for the specified event name
//             element.addEventListener(event.eventName, func);
//         });
//     });
// }
//
// document.addEventListener('DOMContentLoaded', () => {
//     //weboo.findEvents();
// })
