package com.wipro.day4;

import java.util.*;

public class Ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<>();
		list.add(59);
		list.add(5654);
		list.add(8945);
		list.add(59);
		
		System.out.println(list);
		
		LinkedHashSet<Integer> set=new LinkedHashSet<>(list);
		
		System.out.println(set);
		

	}

}
/*Ex5: Create a List having duplicate data (integer/string) 
 * then write a program to remove duplicates from it.*/
 