    let obj1 = { a: 1, b: 2 };
let obj2 = { b: 3, c: 4 };

let mergedObj = { ...obj1, ...obj2 };
console.log(mergedObj); 


let arr1 = [1, 2, 3];
let arr2 = [4, 5, 6];

let newArr = [4, 5, ...arr1, 6, 7, ...arr2, 8, 9];
console.log(newArr); 


let numbers = [1, 2, 3];

function sum(a, b, c) {
    return a + b + c;
}

console.log(sum(...numbers)); 

