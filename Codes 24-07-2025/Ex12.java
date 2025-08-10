package com.wipro.day4;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            LocalDate start = LocalDate.parse(scanner.nextLine(), fmt);

            System.out.print("Enter end date (yyyy-MM-dd): ");
            LocalDate end = LocalDate.parse(scanner.nextLine(), fmt);

            if (end.isBefore(start)) {
                System.out.println("End date must be after or equal to start date.");
                return;
            }

            long days = ChronoUnit.DAYS.between(start, end);
            long months = ChronoUnit.MONTHS.between(start, end);
            long years = ChronoUnit.YEARS.between(start, end);

            Period period = Period.between(start, end);

            System.out.println("\nDifference in years: " + years);
            System.out.println("Difference in months: " + months);
            System.out.println("Difference in days: " + days);

            System.out.printf("\nDetailed: %d years, %d months, %d days%n",
                    period.getYears(), period.getMonths(), period.getDays());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use yyyy-MM-dd.");
        } finally {
            scanner.close();
        }
    }
}
