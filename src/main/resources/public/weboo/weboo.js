const weboo = {
    commands: {},

    exeCommandsArray(data) {
        if (!Array.isArray(data)) {
            data = [data];
        }
        for (let num in data) {
            const c = data[num]
            weboo.commands[c.name].apply(c);
        }

    },

    exeCommand(c) {
        return weboo.commands[c.name].apply(c);
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
