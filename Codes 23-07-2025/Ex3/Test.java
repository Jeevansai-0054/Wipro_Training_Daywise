package com.wipro.oop;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HDFC hdfc=new HDFC();
		Citi citi=new Citi();
		hdfc.depoist(8956,"38786256832");
		hdfc.withdraw(500, "989655656");
		
		citi.depoist(258858,"387852532");
		hdfc.withdraw(5500, "0009252556");
		

	}

}
