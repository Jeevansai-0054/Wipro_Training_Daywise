package com.wipro.oop;
import java.util.Scanner;

public class Ex8 {

    // Method that throws an exception if the number is zero
    public static double changeCurrency(double amount) throws NumberFormatException {
        if (amount == 0) {
            throw new NumberFormatException("Invalid Number");
        }
        return amount * 80;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        try {
            // Attempt to convert the currency
            double result = changeCurrency(amount);
            System.out.println("Converted amount: " + result);
        } catch (NumberFormatException e) {
            // Handle the exception if amount is zero
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


/*Ex8:Create a function called changeCurrency that would take a number . 
 * If the number is zero it will generate a NumberFormatException with a message Invalid Number.
 *  If the number is not zero
then multiply the input number with 80 and return the resultant number. Use throw,throws 
 in changeCurrency and using try/catch handle the exception*/