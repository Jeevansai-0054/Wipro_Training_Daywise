package com.wipro.oop;

public class Converter {

	public int convert(int a)
	{
		System.out.println("a");
		return 0;
	}
	public int convert(int a, int b)
	{
		System.out.println(a+" "+b);
		return 0;
	}
	public double convert(double a)
	{
		System.out.println(a);
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Converter obj=new Converter();
		
		obj.convert(7.99d);
		obj.convert(59);
		obj.convert(99,100);
		

	}

}
/*Create a class called Converter having methods called int convert (int a), int convert
 *  (int a,int b), double convert(doublea). In the test class instantiate this Converter 
 *  class and call all these methods separately.*/
 