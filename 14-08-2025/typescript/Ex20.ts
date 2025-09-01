
function pair<T, U>(first: T, second: U): [T, U] {
  return [first, second];
}


const result1 = pair<number, string>(101, "Jeevan");
const result2 = pair<boolean, number>(true, 42);
const result3 = pair<string, boolean>("Active", false);


console.log("Pair 1:", result1); 
console.log("Pair 2:", result2); 
console.log("Pair 3:", result3); 