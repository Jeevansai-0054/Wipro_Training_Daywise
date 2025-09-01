package com.wipro.basics;

public class Ex13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[]= {10,5,20,8,15};
		int max1=-10;
		int max2=-11;
		
		for(int i=0;i<num.length;i++)
		{
			if(num[i]>max1 )
			{
				
				max2=max1;
				max1=num[i];
			}
			else if(num[i]>max2 &&num[i]<max1)
			{
				max2=num[i];
			}
		}
		System.out.println(max2);
		

	}

}
