package com.wipro.day4;

import java.util.*;
import java.util.stream.Collectors;

public class Ex6
{

    public static void main(String[] args) {
        // 1. Create the map with currency codes and their names
        Map<String, String> currencyMap = new HashMap<>();
        currencyMap.put("INR", "Indian Rupee");
        currencyMap.put("USD", "United States Dollar");
        currencyMap.put("EUR", "Euro");
        currencyMap.put("JPY", "Japanese Yen");
        currencyMap.put("GBP", "British Pound");

        // 2. Sort the map by the currency names (values)
        LinkedHashMap<String, String> sortedByName = currencyMap.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,               // In case of duplicate values
                LinkedHashMap::new            // Preserve insertion order
            ));

        // 3. Print each entry in the format: "USD- United States Dollar"
        sortedByName.forEach((code, name) -> System.out.println(code + "- " + name));
    }
}

