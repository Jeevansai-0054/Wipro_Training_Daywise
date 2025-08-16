var Box = /** @class */ (function () {
    function Box(value) {
        this.value = value;
    }
    Box.prototype.getValue = function () {
        return this.value;
    };
    return Box;
}());
var numberBox = new Box(123);
var stringBox = new Box("Hello, Jeevan!");
var booleanBox = new Box(true);
console.log("Number Box:", numberBox.getValue()); // 123
console.log("String Box:", stringBox.getValue()); // "Hello, Jeevan!"
console.log("Boolean Box:", booleanBox.getValue()); // true
