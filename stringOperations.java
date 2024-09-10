/*
 * Name : Rudra Pratap Singh
 * Date : 09/09/2024
 * 
 * Problem 1: Valid Parentheses Combination Generator
 * Description :
 * [ Write a Java program that generates all valid combinations of the string pairs of
   parentheses. Each combination should be unique and well-formed. ]
 * Example 1:
	Input: n = ab
	Output: [“a”,”b”,”ab”,”ba”]
   Example 2:
	Input: n = abc
	Output: [“a”,”b”,”c”,”ab”,”ac”,”ba”,”bc”,”ca”,”cb”....]
 * 
 * 
 * Problem 2: Digit Sum Loop(String)
 * Description :
 * [ Write a Java program that continuously takes a number as input and replaces it with the sum of
	its digits until the number is reduced to a single digit. ]
 * Example 1:
	Input: 9875
	Output: 2 (Explanation: 9+8+7+5=29, 2+9=11, 1+1=2)
 *
 * 
 * Problem 3: Consecutive Number Summer
 * Description :
 * [ Write a Java program that finds whether a given number can be expressed as the sum of two or
	more consecutive natural numbers. If possible, the program should print all possible
	combinations of consecutive natural numbers that sum up to the given number. ]
 * Example:
	Input: 15
	Output:
	1 + 2 + 3 + 4 + 5
	4 + 5 + 6
	7 + 8
 * 
 * 
 * Problem 4: Caesar Cipher with Shift Variability
 * Description :
 * [ Write a program that implements the Caesar Cipher encryption technique
	but with a twist. The program should take an input string and a shift pattern array. For
	each character in the string, apply the corresponding shift value from the pattern array. If
	the pattern array length is shorter than the input string, repeat the pattern. ]
 * Example:
	Input: "HELLO WORLD", Shift Pattern: [1, 2, 3]
	Output: "IFMMP XPSME" (Shifts: 'H' +1, 'E' +2, 'L' +3, 'L' +1, 'O' +2, etc.)
 * 
 * 
 * Problem 5: Encoding Challenge with ASCII Conversion
 * Description:
	[ Write a Java program that takes an unsorted array of digits and a series of integers. It encodes
	the highest digits, based on the provided series, into their corresponding ASCII characters. ]
	Example:
	 Input: Array: 2315, Series: 123
	 Output: "535150"
	 Explanation: The three highest numbers 5, 3, 2 are converted to ASCII characters.
 * **/



import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Scanner;

public class stringOperations {
    static Scanner input = new Scanner(System.in);
    static String originalString;

    public static void main(String[] args) {
        System.out.println(
                          "1. Valid Parenthesis Combination Generator\r\n"
                        + "2. Digit Sum Loop\r\n"
                        + "3. Consecutive Number Summer\r\n"
                        + "4. Caesar Cipher with Shift Variability\r\n"
                        + "5. Encoding Challenge with ASCII Conversion\r\n"
                        + "6. Exit From the Program");
        String input_option = input.next();
        boolean isQuit = false;
        do {
            switch (input_option) {
                case "1":
                    System.out.println("Executing Case 1 :");
                    System.out.print("Enter a string: ");
                    String inpuString = input.nextLine();
                    if (inpuString == null || inpuString.isEmpty()) {
                        System.out.println("Error: String can't be empty");
                        return;
                        }
                    StringBuilder result = new StringBuilder();

                    result.append("[");
                    generateCombinations(inpuString.toCharArray(), "", 0, result);

                    if (result.length() > 1) {
                        result.setLength(result.length() - 2);
                    }

                    result.append("]");
                    System.out.println(result.toString());
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
                            System.out.println("Invalid input!!");
                            input.next();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Executing Case 3 :");
                    System.out.print("Enter a number: ");
                    try {
                        int inputNumber = input.nextInt();
                        if (inputNumber <= 0) {
                            System.out.println("Please enter a positive integer.");
                        } else {
                            findConsecutiveSums(inputNumber);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                    }
                    break;
                case "4":
                    System.out.println("Executing Case 4 :");
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
                        "1. Valid Parenthesis Combination Generator\r\n"+
                        "2. Digit Sum Loop\r\n"+
                        "3. Consecutive Number Summer\r\n"+
                        "4. Caesar Cipher with Shift Variability\r\n"+
                        "5. Encoding Challenge with ASCII Conversion\r\n"+
                        "6. Exit From the Program");
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

        StringBuilder encrypted = new StringBuilder();
        int patternLength = patternArray.length;
        int patternIndex = 0;

        for (int i = 0; i < mainString.length(); i++) {
            char currentCharacter = mainString.charAt(i);

            if (Character.isUpperCase(currentCharacter)) {
                int shift = patternArray[patternIndex % patternLength];
                char encryptedCharacter = (char) ((currentCharacter - 'A' + shift) % 26 + 'A');
                encrypted.append(encryptedCharacter);
                patternIndex++;
            } else if (Character.isLowerCase(currentCharacter)) {
                int shift = patternArray[patternIndex % patternLength];
                char encryptedCharacter = (char) ((currentCharacter - 'a' + shift) % 26 + 'a');
                encrypted.append(encryptedCharacter);
                patternIndex++;
            } else {
                encrypted.append(currentCharacter);
            }
        }

        return encrypted.toString();
    }

    public static int[] convertPatternToArray(String shiftPatternInput) {
        String[] stringValues = shiftPatternInput.split(",");
        int[] shiftPattern = new int[stringValues.length];

        for (int i = 0; i < stringValues.length; i++) {
            try {
                shiftPattern[i] = Integer.parseInt(stringValues[i].trim());
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid shift value ignored: " + stringValues[i]);
                shiftPattern[i] = 0; 
            }
        }

        return shiftPattern;
    }

    public static void findConsecutiveSums(int inputNumber) {
        boolean found = false;

        for (int k = 2; k <= inputNumber / 2; k++) {
            int numerator = inputNumber - (k * (k - 1)) / 2;

            if (numerator % k == 0) {
                int a = numerator / k;

                if (a > 0) {
                    found = true;
                    printConsecutiveNumbers(a, k);
                }
            }
        }

        if (!found) {
            System.out.println("No combinations found.");
        }
    }

    public static void printConsecutiveNumbers(int start, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(start + i);
            if (i < length - 1) {
                result.append(" + ");
            }
        }
        System.out.println(result.toString());
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

        System.out.println("Enter an unsorted array of digits (e.g., 2315): ");
        String inputArray = input.nextLine();
        System.out.println("Enter a series of integers (e.g., 123): ");
        String series = input.nextLine();

        if (inputArray.isEmpty() || series.isEmpty() || !isValidInput(inputArray) || !isValidSeries(series)) {
            System.out.println("Invalid input. Please enter only single-digit integers.");
            return;
        }

        int[] digits = new int[inputArray.length()];
        for (int i = 0; i < inputArray.length(); i++) {
            digits[i] = Character.getNumericValue(inputArray.charAt(i));
        }

        Arrays.sort(digits);
        StringBuilder encodedString = new StringBuilder();

        for (char c : series.toCharArray()) {
            int index = Character.getNumericValue(c) - 1;
            if (index >= 0 && index < digits.length) {
                encodedString.append((char) (digits[digits.length - 1 - index] + '0'));
            }
        }
        System.out.println("output is : " + encodedString.toString());
    }

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

    private static void generateCombinations(char[] chars, String current, int index, StringBuilder result) {
        if (index == chars.length) {
            return;
        }

        for (int i = index; i < chars.length; i++) {
            current += chars[i];
            result.append("\"").append(current).append("\", ");
            generateCombinations(chars, current, i + 1, result);
            current = current.substring(0, current.length() - 1); 
        }
    }
}
