let a = ['name1', 'value1', 'name2', 'value2'];
let result = a.reduce((obj, value, index, array) => {
    if (index % 2 === 0) {
        obj[value] = array[index + 1];
    }
    return obj;
}, {});
console.log(result);
