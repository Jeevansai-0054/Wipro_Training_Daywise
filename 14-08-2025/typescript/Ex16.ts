
interface Employee {
  empId: number;
  empName: string;
  salary: number;
}


const employeeList: Employee[] = [
  { empId: 1, empName: "Jeevan", salary: 50000 },
  { empId: 2, empName: "Meena", salary: 62000 },
  { empId: 3, empName: "Ravi", salary: 58000 }
];


function displayEmployees(employees: Employee[]): number {
  console.log("📋 Employee Details:");
  for (const emp of employees) {
    console.log(`ID: ${emp.empId}, Name: ${emp.empName}, Salary: ₹${emp.salary}`);
  }

  return employees.length;
}


const count = displayEmployees(employeeList);
console.log("👥 Total Employees:", count);