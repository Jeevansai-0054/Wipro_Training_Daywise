package com.wipro.basics;

public class Ex14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[]= {1,1,2,2,3,4,4,5};
		int uc=0; 
		for(int i=0;i<num.length;i++)
		{	
			int oc=0;
			for(int j=0;j<num.length;j++)
			{
				if(num[i]==num[j])
				{
					oc++;
				}
			}
				if(oc==1)
				{
					uc++;
				}
			
		}
		System.out.println(uc);
		/*int dup[]=new int[uc];
		int c=0;
		for(int i=0;i<num.length;i++)
		{
			for(int j=0;j<num.length;j++)
			{
				if(num[i]!=num[j] && oc==1)
				{
					dup[c]=num[i];
					c++;
				}
			}
		}*/
	
		
	}
	

}


