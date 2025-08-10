package com.wipro.oop;

public class Restaurant {

	String c_name;
	long ph_no;
	int Total_items;
	
	public Restaurant(String c_name, long ph_no, int total_items) {
		
		this.c_name = c_name;
		this.ph_no = ph_no;
		Total_items = total_items;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public long getPh_no() {
		return ph_no;
	}

	public void setPh_no(long ph_no) {
		this.ph_no = ph_no;
	}

	public int getTotal_items() {
		return Total_items;
	}

	public void setTotal_items(int total_items) {
		Total_items = total_items;
	}

	@Override
	public String toString() {
		return "Restaurant [c_name=" + c_name + ", ph_no=" + ph_no + ", Total_items=" + Total_items + "]";
	}


}
/*Ex1: Create a package called com.wipro.oop. Create a class called Restaurant having some properties 
 * , constructors , getters/setters,toStringmethod.
 
 
Create a package called com.wipro.oop.test and create a class 
RestaurantTesthaving a main method. Instantiate the Restaurant class and print it.*/