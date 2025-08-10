package com.wipro.oop;

public class HDFC implements BankOps{

	@Override
	public void depoist(double amount, String accNumber) {
		// TODO Auto-generated method stub
		System.out.println("your amount is "+ amount);
		System.out.println("Your accoun number is "+accNumber);
		
	}

	@Override
	public double withdraw(double amount, String accNumber) {
		// TODO Auto-generated method stub
		System.out.println("your amount is "+ amount);
		System.out.println("Your accoun number is "+accNumber);
		return 0;
	}

}
