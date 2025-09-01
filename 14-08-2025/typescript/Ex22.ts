
class Stack<T> {
  private items: T[] = [];

  
  push(item: T): void {
    this.items.push(item);
  }

  
  pop(): T | undefined {
    return this.items.pop();
  }

 
  peek(): T | undefined {
    return this.items[this.items.length - 1];
  }
}

// ✅ Example usage
const numberStack = new Stack<number>();
numberStack.push(10);
numberStack.push(20);
numberStack.push(30);

console.log("Peek:", numberStack.peek()); // 30
console.log("Pop:", numberStack.pop());   // 30
console.log("Peek after pop:", numberStack.peek()); // 20

const stringStack = new Stack<string>();
stringStack.push("Jeevan");
stringStack.push("Frontend");
stringStack.push("TypeScript");

console.log("Peek:", stringStack.peek()); // "TypeScript"
console.log("Pop:", stringStack.pop());   // "TypeScript"
console.log("Peek after pop:", stringStack.peek()); // "Frontend"