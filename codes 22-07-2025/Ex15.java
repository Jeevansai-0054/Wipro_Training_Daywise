package com.wipro.basics;

public class Ex15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= {2,7,11,15};
		int key= 9;
		for(int i=0;i<a.length;i++)
		{
			for(int j=i;j<a.length;j++)
			{
				if(a[i]+a[j]==key)
				{
					System.out.println(i+" "+j);
				}
			}
		}

	}

}
