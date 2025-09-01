package com.wipro.day4;




import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ex13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your birth date (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(input, formatter);
            LocalDate currentDate = LocalDate.now();

            int age = calculateAge(birthDate, currentDate);
            System.out.println("Your age: " + age + " years");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid format. Please use yyyy-MM-dd.");
        }

        scanner.close();
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate == null || currentDate == null) {
            return 0;
        }
        return Period.between(birthDate, currentDate).getYears();
    
}
}