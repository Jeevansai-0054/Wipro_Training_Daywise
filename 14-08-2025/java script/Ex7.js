const persons = [
  { name: "Jeevan", age: 22, city: "Rajahmundry" },
  { name: "Amit", age: 17, city: "Delhi" },
  { name: "Priya", age: 19, city: "Mumbai" },
  { name: "Ravi", age: 16, city: "Hyderabad" },
  { name: "Sneha", age: 25, city: "Chennai" }
];


/*onst eligibleVoters = persons.filter(person => person.age >= 18);

console.log("Eligible Voters:");
eligibleVoters.forEach(person => {
  console.log(`${person.name} (${person.age}) from ${person.city}`);
});*/


/*persons.forEach(person => {
  person.status = person.age >= 18 ? "Adult" : "Minor";
});

console.log("Updated Person List with Status:");
console.log(persons);*/



// Create a new array with 'status' added
const updatedPersons = persons.map(person => {
  return {
    ...person,
    status: person.age >= 18 ? "Adult" : "Minor"
  };
});

console.log("Updated Person List with Status:");
console.log(updatedPersons);