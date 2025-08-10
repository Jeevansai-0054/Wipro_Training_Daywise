package com.wipro.oop;
import java.util.*;
import com.wipro.oop.exception.*;
public class ExceptionTest {

	
	public static void main(String[] args) throws CustomDefinedException{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int month=sc.nextInt();
		if(month<=12 && month>=1)
		{
			System.out.println("Given Month is Valid");
		}
		else
		{
			CustomDefinedException cde=new CustomDefinedException("");
			throw cde;
			
		}

	}

}
