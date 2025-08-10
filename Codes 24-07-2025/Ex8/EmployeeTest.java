package com.wipro.day4;

import java.util.ArrayList;
import java.util.Collections;


public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeEx7 E1=new EmployeeEx7("001","Jeevan",30,3595.65);
		EmployeeEx7 E2=new EmployeeEx7("002","Praveen",28,25000);
		EmployeeEx7 E3=new EmployeeEx7("003","Satish",24,60000);
		EmployeeEx7 E4=new EmployeeEx7("004","Bhanu",32,90000);
		
		ArrayList<EmployeeEx7> al=new ArrayList<EmployeeEx7>();
		al.add(E1);
		al.add(E2);
		al.add(E3);
		al.add(E4);
		
		System.out.println(al);
		Collections.sort(al);
		System.out.println(al);
	}

}
