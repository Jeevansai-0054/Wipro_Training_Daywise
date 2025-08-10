package com.wipro.oop;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Human h=new Human("Omnivores",2,false,true);
		Bird b=new Bird("Omnivores",2,true,false);
		Dog d=new Dog("Omnivores",4,true,true);
		
		System.out.println("Detalils of human");
		h.info();
		System.out.println();
		System.out.println("Detalils of Bird");
		b.info();
		System.out.println();
		System.out.println("Detalils of Dog");
		d.info();
		

	}

}
