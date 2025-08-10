package com.wipro.oop;

public interface PaymentMethod {

	 void pay(double amount);
	
	
}
/*
 * Ex6: Create an interface calledPaymentMethodhaving a method called void pay(double amount).
Create two concrete classes called Gpay and PhonePay, implementing the above interface.
Then in the test class create an Object of PaymentMethod to point to either Gpay or PhonePay 
and call the pay method.*/
