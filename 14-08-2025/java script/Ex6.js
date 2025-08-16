const names = ["Jeevan", "Amit", "Priya", "Samantha", "Raj", "Kiran", "Jonathan"];

const filteredUppercase = names
  .filter(name => name.length > 5)
  .map(name => name.toUpperCase());

console.log("Names longer than 5 characters (uppercase):", filteredUppercase);