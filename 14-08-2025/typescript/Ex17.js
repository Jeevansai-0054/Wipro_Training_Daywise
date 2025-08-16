var Calculator = /** @class */ (function () {
    function Calculator() {
    }
    Calculator.prototype.add = function (a, b) {
        return a + b;
    };
    Calculator.prototype.subtract = function (a, b) {
        return a - b;
    };
    return Calculator;
}());
var calc = new Calculator();
var sum = calc.add(10, 5);
var difference = calc.subtract(10, 5);
console.log("➕ Sum:", sum);
console.log("➖ Difference:", difference);
