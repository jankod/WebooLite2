

let code = '  console.log("dela ", eleId); ';

let weboo = {};
weboo.commands = {}


d = {
  "name" : "hr_ja_weboo_js_HideElement",
  "eleId" : "22"
}
weboo.functions.hr_ja_weboo_js_HideElement = function() {
  //$("#"+eleId).hide();
    console.log("pozvao ", this.eleId);
}


function exe(d) {
    weboo.functions[d.name].apply(d)
}

exe(d);
