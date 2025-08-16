var employeeList = [
    { empId: 1, empName: "Jeevan", salary: 50000 },
    { empId: 2, empName: "Meena", salary: 62000 },
    { empId: 3, empName: "Ravi", salary: 58000 }
];
function displayEmployees(employees) {
    console.log("📋 Employee Details:");
    for (var _i = 0, employees_1 = employees; _i < employees_1.length; _i++) {
        var emp = employees_1[_i];
        console.log("ID: ".concat(emp.empId, ", Name: ").concat(emp.empName, ", Salary: \u20B9").concat(emp.salary));
    }
    return employees.length;
}
var count = displayEmployees(employeeList);
console.log("👥 Total Employees:", count);
