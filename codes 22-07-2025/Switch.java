package com.wipro.basics;

public class Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char shape='R';
		switch (shape)
		{
			case 'R':
			System.out.println("Rectangle");
			break;
		
			case 'S':
			System.out.println("square");
			break;
			
			case 'C':
			System.out.println("Circle");
			break;
			
			default :
			System.out.println("Others");
			break;
		
		}

	}

}
