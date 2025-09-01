package com.wipro.day4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ex10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 LocalDate date = LocalDate.of(2023, 11, 1);
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-uuuu");
	        String output = date.format(fmt);
	        System.out.println(output);
	}

}
/*Ex10: Create a Local date object for 1st November 2023 and print it like 01-11-2023*/