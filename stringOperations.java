/*
 * Name : Rudra Pratap Singh
 * Date : 09/09/2024
 * 
 * Problem 1: Valid Parentheses Combination Generator
 * [ Write a Java program that generates all valid combinations of the string pairs of
    parentheses. Each combination should be unique and well-formed. ]
 * 
 * Problem 2: Digit Sum Loop(String)
 * [ Write a Java program that continuously takes a number as input and replaces it with the sum of
	its digits until the number is reduced to a single digit. ]
 *
 * Problem 3: Consecutive Number Summer
 * [ Write a Java program that finds whether a given number can be expressed as the sum of two or
	more consecutive natural numbers. If possible, the program should print all possible
	combinations of consecutive natural numbers that sum up to the given number. ]
 * 
 * Problem 4: Caesar Cipher with Shift Variability
 * [ Write a program that implements the Caesar Cipher encryption technique
	but with a twist. The program should take an input string and a shift pattern array. For
	each character in the string, apply the corresponding shift value from the pattern array. If
	the pattern array length is shorter than the input string, repeat the pattern. ]
 * 
 * Problem 5: Encoding Challenge with ASCII Conversion
	[ Write a Java program that takes an unsorted array of digits and a series of integers. It encodes
	the highest digits, based on the provided series, into their corresponding ASCII characters. ]
 * **/

import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Scanner;

public class stringOperations {
    static Scanner input = new Scanner(System.in);
    static String originalString;
    static String finalResult;
    static int combinationCount;

    public static void main(String[] args) {
        System.out.println(
                          "1. Valid Parenthesis Combination Generator\r\n"
                        + "2. Digit Sum Loop\r\n"
                        + "3. Consecutive Number Summer\r\n"
                        + "4. Caesar Cipher with Shift Variability\r\n"
                        + "5. Encoding Challenge with ASCII Conversion\r\n"
                        + "0. Exit From the Program");
        String input_option = input.next();
        boolean isQuit = false;
        do {
            switch (input_option) {
                case "1":
                    System.out.println("Executing Case 1 :");
                    input.nextLine();
                    System.out.println("enter string :");
                    String inputString = input.nextLine();
                    if (inputString == null || inputString.isEmpty()) {
                        System.out.println("Error: String can't be empty");
                        return;
                    }
                    char[] characterInputs = new char[inputString.length()];
                    boolean[] used = new boolean[characterInputs.length];

                    for (int i = 0; i < inputString.length(); i++) {
                        characterInputs[i] = inputString.charAt(i);
                    }
                    char[] result = new char[characterInputs.length];
                    finalResult = "";
                    combinationCount=0;
                    finalResult += "[";
                    System.out.println("Total Combinations :- ");
                    
                    for (int length = 1; length <= characterInputs.length; length++) {
                        generateStrings(characterInputs, result, 0, used, length);
                    }
                    finalResult += "]";
                    System.out.println(finalResult);
                    System.out.println("Total combination is "+combinationCount);
                    System.out.println();
                    break;
                case "2":
                    System.out.println("Executing Case 2 :");
                    while (true) {
                        try {
                            System.out.println("Enter the number:");
                            int number = input.nextInt();
                            if (number < 0) {
                                System.out.println("Enter only positive numbers.");
                            } else {
                                int newResult = sumOfDigits(number);
                                System.out.println("Sum of digits: " + newResult);
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!! ");
                            input.next();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Executing Case 3 :");
                    while (true) {
                        try {
                            System.out.print("Enter a number: ");
                            int inputNumber = input.nextInt();
                            if (inputNumber <= 0) {
                                System.out.println("Please enter a positive integer.");
                            } else {
                                findConsecutiveSums(inputNumber);
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.next();
                        }
                    }
                    break;
                case "4":
                    System.out.println("Executing Case 4 :");
                    originalString ="";
                    System.out.println("Enter a string : ");
                    input.nextLine();
                    originalString = input.nextLine();
                    String encryptedMessage = shiftVariability(originalString);
                    System.out.println("Encrypted Message: " + encryptedMessage);
                    break;
                case "5":
                    System.out.println("Executing Case 5 :");
                    encodeToASCII();
                    break;
                case "0":
                    System.out.println("Exiting from program :");
                    isQuit = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    isQuit = false;
                    break;
            }
            if (!isQuit) {
                System.out.println(
                                "1. Valid Parenthesis Combination Generator\r\n" +
                                "2. Digit Sum Loop\r\n" +
                                "3. Consecutive Number Summer\r\n" +
                                "4. Caesar Cipher with Shift Variability\r\n" +
                                "5. Encoding Challenge with ASCII Conversion\r\n" +
                                "0. Exit From the Program");
                input.nextLine();
                input_option = input.next();
            }
        } while (!isQuit);
    }

    public static String shiftVariability(String mainString) {
        System.out.println("Enter the shift pattern (comma-separated): ");
        String stringPattern = input.nextLine();

        if (stringPattern == null || stringPattern.trim().isEmpty()) {
            System.out.println("Error: Shift pattern can't be empty");
            return "Give input again";
        }
        int[] patternArray = convertPatternToArray(stringPattern);

        String encrypted = "";
        int patternLength = patternArray.length;
        int patternIndex = 0;

        for (int i = 0; i < mainString.length(); i++) {
            char currentCharacter = mainString.charAt(i);
            if (Character.isUpperCase(currentCharacter)) {
                int shift = patternArray[patternIndex % patternLength];
                char encryptedCharacter = (char) ((currentCharacter - 'A' + shift) % 26 + 'A');
                encrypted += encryptedCharacter;
                patternIndex++;
            } else if (Character.isLowerCase(currentCharacter)) {
                int shift = patternArray[patternIndex % patternLength];
                char encryptedCharacter = (char) ((currentCharacter - 'a' + shift) % 26 + 'a');
                encrypted += encryptedCharacter;
                patternIndex++;
            } else {
                encrypted += currentCharacter;
            }
        }
        return encrypted;
    }

    public static int[] convertPatternToArray(String shiftPatternInput) {
        String[] stringValues = shiftPatternInput.split(",");
        int[] shiftPattern = new int[stringValues.length];

        for (int i = 0; i < stringValues.length; i++) {
            try {
                shiftPattern[i] = Integer.parseInt(stringValues[i].trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid shift value ignored: ");
                shiftPattern[i] = 0;
            }
        }
        return shiftPattern;
    }

    //findConsecutiveSums prints all the possible consecutive numbers which sum up to target
    public static void findConsecutiveSums(int inputNumber) {
        boolean found = false;
        for (int k = 2; k <= inputNumber / 2; k++) {
            int numerator = inputNumber - (k * (k - 1)) / 2;
            if (numerator % k == 0) {
                int a = numerator / k;
                if (a > 0) {
                    found = true;
                    printNumbers(a, k);
                }
            }
        }
        if (!found) {
            System.out.println("No combinations found.");
        }
    }

    //Print Numbers function is used to print consecutive numbers with addition sign
    public static void printNumbers(int start, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += start + i;
            if (i < length-1) {
                result += " + ";
            }
        }
        System.out.println(result);
    }

    private static boolean isValidInput(String inputArray) {
        for (char c : inputArray.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSeries(String series) {
        for (char c : series.toCharArray()) {
            if (!Character.isDigit(c) || Character.getNumericValue(c) < 1) {
                return false;
            }
        }
        return true;
    }

    private static void encodeToASCII() {
        input.nextLine();
        String inputArray;
        String series;
        while (true) {
            System.out.println("Enter array of digits : ");
            inputArray = input.nextLine();
            System.out.println("Enter series : ");
            series = input.nextLine();
            if (inputArray.isEmpty() || series.isEmpty() || !isValidInput(inputArray) || !isValidSeries(series)) {
                System.out.println("Invalid input. Please enter only single-digit positive integers.");
            }else{
                break;
            }
        }

        int[] digits = new int[inputArray.length()];
        for (int i = 0; i < inputArray.length(); i++) {
            digits[i] = Character.getNumericValue(inputArray.charAt(i));
        }
        Arrays.sort(digits);
        String encodedString ="" ;
        for (char c : series.toCharArray()) {
            int index = Character.getNumericValue(c) - 1;
            if (index >= 0 && index < digits.length) {
                encodedString += String.valueOf((int) (digits[digits.length - 1 - index] + 48));
            }
        }
        System.out.println("output is : " + encodedString);
    }

    //Sum of digits function is used to print the sum of all the digits until we left with one digit number
	//This is recursive function which uses number as parameter
    public static int sumOfDigits(int n) {
        int number = n;
        int sum = 0;
        while (number != 0) {
            sum += (number % 10);
            number /= 10;
        }
        if (sum > 9) {
            return sumOfDigits(sum);
        } else {
            return sum;
        }
    }

    //This Function is used to generate all sub-strings 
	//Parameters include character array which stores every element of given String
	//Resultant array which stores all possible substrings
	//Index is used for keeping track of resultant array
	//Used array is used to remove duplicates
	//Length is used to traverse through to the character input array
    public static void generateStrings(char[] characterInputs, char[] result, int index, boolean[] used, int length) {
        if (index == length) {
            finalResult += '"';
            for (int i = 0; i < length; i++) {
                finalResult = finalResult + result[i];
            }
            combinationCount++;
            finalResult+='"'+",";
            return;
        }
        for (int i = 0; i < characterInputs.length; i++) {
            if (!used[i]) {
                used[i] = true;
                result[index] = characterInputs[i];
                generateStrings(characterInputs, result, index + 1, used, length);
                used[i] = false;
            }
        }
    }   
}
