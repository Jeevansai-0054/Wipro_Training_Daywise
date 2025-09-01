package com.wipro.day4;
import java.util.*;
public class Ex4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Integer> set1=new HashSet();
		HashSet<Integer> set2=new HashSet();
			set1.add(56);
			set1.add(89);
			set1.add(66);
			set1.add(55);
			
			set2.add(56);
			set2.add(68);
			set2.add(85);
			set2.add(81);
			
			set1.removeAll(set2);
			
			System.out.println(set1);
			
	}

}
/*Difference of Sets: Create two Sets of integers and find their difference.*/