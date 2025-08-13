function calculate(operation) {
  const num1 = parseFloat(document.getElementById("num1").value);
  const num2 = parseFloat(document.getElementById("num2").value);
  const resultDisplay = document.getElementById("result");

  if (isNaN(num1) || isNaN(num2)) {
    resultDisplay.textContent = "Result: Please enter valid numbers.";
    return;
  }

  let result;
  switch (operation) {
    case "add":
      result = num1 + num2;
      break;
    case "subtract":
      result = num1 - num2;
      break;
    case "multiply":
      result = num1 * num2;
      break;
    case "divide":
      if (num2 === 0) {
        resultDisplay.textContent = "Result: Cannot divide by zero.";
        return;
      }
      result = num1 / num2;
      break;
  }

  resultDisplay.textContent = `Result: ${result}`;
}