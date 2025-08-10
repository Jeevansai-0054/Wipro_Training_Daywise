package com.wipro.basics;
import java.util.Scanner;

public class Ex08 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Input floating-point number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Input floating-point another number: ");
        double num2 = scanner.nextDouble();


        num1 = Math.round(num1 * 1000.0) / 1000.0;
        num2 = Math.round(num2 * 1000.0) / 1000.0;

    
        if (num1 == num2) {
            System.out.println("They are the same");
        } 
	else {
            System.out.println("They are different");
        }

       
    }
}
