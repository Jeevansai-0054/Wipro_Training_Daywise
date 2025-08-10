package com.wipro.oop;

public class Gpay implements PaymentMethod {
	
	@Override
	public void pay(double amount)
	{
		System.out.println("Amount paid succesfully using Gpay");
	}

}
