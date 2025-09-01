package com.wipro.basics;

public class Ex12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[]= {100,67,98,678,45,123};
		int max_value =Integer.MIN_VALUE;
		for(int i=0;i<num.length;i++)
		{
			if(num[i]>max_value)
			{
				max_value=num[i];
			}
		}
		System.out.println(max_value);
	}

}
