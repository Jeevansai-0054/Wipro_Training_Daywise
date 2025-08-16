var x = 4; // global scope variable
 
const z = 9; //constant variable
 
function f() {
  x = 9;
  let y = 8; // block scope variable
  console.log("x in function is " + x);
 console.log("y in function is " + y);
 
  if (true) {
    let z = 18;
    console.log("y in if block is " + y); // y is block scoped
  }
console.log("z in function is " + z); // z is accessible here as it is a constant
}
f(); // function call
// console.log("y outside  function is " + y);
 