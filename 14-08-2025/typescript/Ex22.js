var Stack = /** @class */ (function () {
    function Stack() {
        this.items = [];
    }
    Stack.prototype.push = function (item) {
        this.items.push(item);
    };
    Stack.prototype.pop = function () {
        return this.items.pop();
    };
    Stack.prototype.peek = function () {
        return this.items[this.items.length - 1];
    };
    return Stack;
}());
// ✅ Example usage
var numberStack = new Stack();
numberStack.push(10);
numberStack.push(20);
numberStack.push(30);
console.log("Peek:", numberStack.peek()); // 30
console.log("Pop:", numberStack.pop()); // 30
console.log("Peek after pop:", numberStack.peek()); // 20
var stringStack = new Stack();
stringStack.push("Jeevan");
stringStack.push("Frontend");
stringStack.push("TypeScript");
console.log("Peek:", stringStack.peek()); // "TypeScript"
console.log("Pop:", stringStack.pop()); // "TypeScript"
console.log("Peek after pop:", stringStack.peek()); // "Frontend"
