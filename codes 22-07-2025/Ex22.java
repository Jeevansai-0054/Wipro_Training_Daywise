package com.wipro.basics;

public class Ex22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "Java is a powerful programming language"; 
        String[] words = input.split(" "); 
        String shortestWord = words[0]; 

        
        for (String word : words) {
            if (word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }
        System.out.println("Shortest word: " + shortestWord);

	}

}
/*Find Shortest Word in a String: Identify the shortest word in a string.*/