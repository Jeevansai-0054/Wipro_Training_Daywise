
class Calculator {
 
  add(a: number, b: number): number {
    return a + b;
  }

  
  subtract(a: number, b: number): number {
    return a - b;
  }
}


const calc = new Calculator();

const sum = calc.add(10, 5);
const difference = calc.subtract(10, 5);

console.log("➕ Sum:", sum);        
console.log("➖ Difference:", difference); 