package com.wipro.oop;

public class Human extends Animal{

	public Human(String eating_habit, int legs, boolean tail, boolean canSwim) {
		super(eating_habit, legs, tail, canSwim);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void info() {
		System.out.println(eating_habit);
		System.out.println(legs);
		System.out.println(tail);
		System.out.println(canSwim);
		
	}

}
