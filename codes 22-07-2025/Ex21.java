package com.wipro.basics;

public class Ex21 {

	public static void main(String[] args) {
	
			      String input = "Hello World!";
        int vowels = 0, consonants = 0;

        
        input = input.toLowerCase();


        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            }

            else if (ch >= 'a' && ch <= 'z') {
                consonants++;
            }
        }


        System.out.println("Number of vowels: " + vowels);
        System.out.println("Number of consonants: " + consonants);
	}

}
/*Exercise 21:
Count Vowels and Consonants: Count the number of vowels and consonants in a given string.
 */
