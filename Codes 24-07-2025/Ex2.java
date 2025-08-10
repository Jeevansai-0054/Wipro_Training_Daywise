package com.wipro.day4;

public class Ex2 <object>{
	
	object obj;
	public void add(object obj)
	{
		this.obj=obj;
	}
	public object getData()
	{
		return obj;
	}
	public static void main(String[]args)
	{
		Ex2<Integer> E1=new Ex2();
		E1.add(100);
		System.out.println(E1.getData());
		
		Ex2<Double> E2=new Ex2();
		E2.add(7989212029d);
		System.out.println(E2.getData());

	}
	

}
/*Generic Box Class: Create a generic class Box that can store any type of object.
 *  Implement methods to add and retrieve the object.*/
 