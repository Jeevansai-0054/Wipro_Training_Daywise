
class Box<T> {
  value: T;

  constructor(value: T) {
    this.value = value;
  }

  getValue(): T {
    return this.value;
  }
}


const numberBox = new Box<number>(123);
const stringBox = new Box<string>("Hello, Jeevan!");
const booleanBox = new Box<boolean>(true);


console.log("Number Box:", numberBox.getValue());   // 123
console.log("String Box:", stringBox.getValue());   // "Hello, Jeevan!"
console.log("Boolean Box:", booleanBox.getValue()); // true