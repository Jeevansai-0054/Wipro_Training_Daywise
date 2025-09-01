package com.wipro.oop.exception;

public class Ex7 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fname=null;
		try
		{
			fname.toUpperCase();
		}
		catch(NullPointerException obj) {
			
			System.out.println("String value not intialized");
		}
	
		

	}

}
/*Create a String variable called fName and set it to null; 
 * Try to use toUpperCase function on that object you will encounter a 
 * null pointer exception. With try/catch handle that exception.*/
 