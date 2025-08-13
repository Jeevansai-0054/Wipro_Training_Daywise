const employees = [
  { name: "Jeevan", role: "Developer", empId: "EMP001", salary: 55000 },
  { name: "Amit", role: "QA", empId: "EMP002", salary: 48000 },
  { name: "Priya", role: "Manager", empId: "EMP003", salary: 75000 },
  { name: "Ravi", role: "Developer", empId: "EMP004", salary: 60000 },
  { name: "Sneha", role: "Manager", empId: "EMP005", salary: 72000 }
];

const managers = employees.filter(emp => emp.role === "Manager");
const totalManagerSalary = managers.reduce((acc, emp) => acc + emp.salary, 0);

console.log("Total Salary drawn by Managers:", totalManagerSalary);