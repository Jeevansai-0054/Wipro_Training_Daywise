package com.wipro.basics;

public class Ex16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1,2,3,4,5};
		int s=0; int end=arr.length-1;
		for(int i=0;i<arr.length/2;i++)
		{
			int temp=0;
			temp=arr[end];
			arr[end]=arr[s];
			arr[s]=temp;
			s++;
			end--;
			
		}
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}

	}

}
