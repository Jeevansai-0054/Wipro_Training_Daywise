package com.wipro.oop;

public abstract class Animal {
	
	String eating_habit;
	int legs;
	boolean tail;
	boolean canSwim;
	
	public abstract void info();
	
	public Animal(String eating_habit, int legs, boolean tail, boolean canSwim) {
		super();
		this.eating_habit = eating_habit;
		this.legs = legs;
		this.tail = tail;
		this.canSwim = canSwim;
	}

	public String getEating_habit() {
		return eating_habit;
	}
	public void setEating_habit(String eating_habit) {
		this.eating_habit = eating_habit;
	}
	public int getLegs() {
		return legs;
	}
	public void setLegs(int legs) {
		this.legs = legs;
	}
	public boolean isTail() {
		return tail;
	}
	public void setTail(boolean tail) {
		this.tail = tail;
	}
	public boolean isCanSwim() {
		return canSwim;
	}
	public void setCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}
	

}

/*Create an abstract class called  Animal and child classes called Human, Bird, Dog . 
 * Identify the properties and functionalities/behaviours that are common to the child
 *  classes and put them in the Animal class. 
 * Then extend Animal in all the child classes 
 * provide implementations for abstract method and test it
 */
 