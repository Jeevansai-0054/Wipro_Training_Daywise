package com.wipro.oop;
import java.util.Scanner;
public class PaymentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		PaymentMethod paymentgateway=null;
		int x=sc.nextInt();
		if(x==1)
		{
			paymentgateway =new Gpay();
			paymentgateway.pay(55.55d);
		}
		else if(x==0)
		{
			paymentgateway =new Phonepay();
			paymentgateway.pay(55.55d);
		}
		
	}

}
