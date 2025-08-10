package com.wipro.basics;
	enum Month
	{
		JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER;
	}

public class Ex17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(Month mon:Month.values())
		{
			System.out.println(mon);
		}
		
		Month month=Month.FEBRUARY;
		if(month==Month.FEBRUARY)
		{
			System.out.println("The month is february");
		}
		else
		{
			System.out.println("the month is not february");
		}

	}

}
/*Create an enum called Month having values like JANUARY,FEBRURAY…DECEMBER
Using for each print all the possible Month values.
 
Create a variable coalled month of Type Month assignFEBRURAY to it and check using if condition if the assigned month isFEBRURAY or not*/
