package com.wipro.basics;

public class Ex18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="AMMA";
		String rev="";
		int c=0;
		for(int i=0;i<s.length();i++)
		{
			rev=s.charAt(i)+rev;
		}
		if(s.equals(rev))
		{
			System.out.println("Palindrome");
		}
		else
		{
			System.out.println("Not a Palindrome");
		}

	}

}
