package com.wipro.oop;

public interface BankOps {
	void depoist(double amount, String accNumber);
	double withdraw(double amount,String accNumber);
	 
	

}

/*Create an interface called BankOpshaving methods:
  void deposit(double amount,String accNumber);
  doubledeposit(doubleamount,String accNumber);
 
Create two Clasess HDFC implements BankOps
                                   Citi implementsBankOps
 
Provide different implementation in these two classes. Create a test class 
called BankTest having main method and test these functionalities.*/