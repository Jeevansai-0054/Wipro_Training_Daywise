package com.wipro.day4;
import java.util.Collection;

public class EmployeeEx7 implements Comparable<EmployeeEx7>{

	String empId;
	String empName;
	int empAge;
	double empSalary;
	public EmployeeEx7(String empId, String empName, int empAge, double empSalary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empSalary = empSalary;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empSalary=" + empSalary
				+ "]";
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	public int compareTo(EmployeeEx7 e) {
		// TODO Auto-generated method stub
		if(this.empSalary>e.empSalary)
		{
			return 1;
		}
		else if(this.empSalary==e.empSalary)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	
}
/*Create a class called Employee having empId(String) , 
 * empName(String),empAge(int),empSalary(double). 
 */
 
/*Create a list of employees and sort that list by descending order of salary.]*/