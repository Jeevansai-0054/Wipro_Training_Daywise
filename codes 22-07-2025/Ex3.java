package com.wipro.basics;

public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float num=-5f;
		
		if(num==0)
		{
			System.out.println("Zero");
		}
		if(num<0)
		{
			if(Math.abs(num)<1 && num<0)
			System.out.println("small and negative");
			else
			{
				System.out.println("negative");
			}
		}
		else if(num>0)
		{
			if(Math.abs(num)>1000000 && num>0)
				System.out.println("large and Positive");
			else
				System.out.println("positive");
		}
		

	}

}
