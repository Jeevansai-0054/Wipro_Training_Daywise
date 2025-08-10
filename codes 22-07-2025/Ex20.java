
/*String Anagram: Check if two strings are anagrams (contain the same characters in a different order). 
 * Examples "listen" and "silent“"earth" and "heart", or "race" and "care".  */

import java.util.Arrays;

public class AnagramChecker {

    public static boolean areAnagrams(String str1, String str2) {
        // Convert strings to lowercase to make the comparison case-insensitive
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // If lengths are not equal, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character arrays
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // Sort the character arrays
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare the sorted arrays
        return Arrays.equals(charArray1, charArray2);
    }

    public static void main(String[] args) {
        String str1 = "Race";
        String str2 = "Care";

        if (areAnagrams(str1, str2)) {
            System.out.println(str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println(str1 + " and " + str2 + " are not anagrams.");
        }
    }
}

 