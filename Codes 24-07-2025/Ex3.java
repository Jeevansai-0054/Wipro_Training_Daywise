package com.wipro.day4;
import java.util.ArrayList;
import java.util.Iterator;
public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> List=new ArrayList<Integer>();
		List.add(10); List.add(20); List.add(30); List.add(40); List.add(50);
		
		Iterator itr=List.iterator();
		while(itr.hasNext())
		{
			System.out.print(itr.next()+" ");
		}
		List.remove(2);
		List.add(8959);
		System.out.println();
		System.out.println(List);
	}

}


 //Create an ArrayList of integers and add 5 elements to it. 
//Remove the element at index 2 and add a new element at the end