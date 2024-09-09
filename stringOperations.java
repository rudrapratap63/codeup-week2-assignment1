import java.util.Scanner;

public class stringOperations {
    static Scanner input = new Scanner(System.in);
    static String originalString;

    public static void main(String[] args) {

        System.out.println("Enter a String: ");
        originalString = input.nextLine();
        if (originalString == null || originalString.isEmpty()) {
            System.out.println("Error: String can't be empty");
            return;
        }
        
        switchCase();
        // String encryptedMessage = shiftVariability(originalString);
        // System.out.println("Encrypted Message: " + encryptedMessage);
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
                shiftPattern[i] = 0; // Set a default value for invalid inputs
            }
        }
        
        return shiftPattern;
    }

    public static void switchCase(){
        System.out.println("1. Valid Parenthesis Combination Generator\r\n"
				+ "2. Digit Sum Loop\r\n"
				+ "3. Consecutive Number Summer\r\n"
				+ "4. Caesar Cipher with Shift Variability\r\n"
				+ "5. Encoding Challenge with ASCII Conversion\r\n"
				+ "6. Exit From the Program");
		String input_option = input.next();
		boolean isQuit = false; 
		do {
			switch(input_option) {
            case "1":
                System.out.println("Executing Case 1 :");
                break;
            case "2":
                System.out.println("Executing Case 2 :");
                break;
            case "3":
                System.out.println("Executing Case 3 :");
                break;
            case "4":
                System.out.println("Executing Case 4 :");
                String encryptedMessage = shiftVariability(originalString);
                System.out.println("Encrypted Message: " + encryptedMessage);
                break;
            case "5":
                System.out.println("Executing Case 5 :");
                break;
            case "6":
                System.out.println("Exiting from program :");
                isQuit = true;
                break;
            default:
                System.out.println("Invalid input");
                isQuit = false;
                break;
				}
			if(!isQuit) {
				System.out.println("1. Valid Parenthesis Combination Generator\r\n"
						+ "2. Digit Sum Loop\r\n"
						+ "3. Consecutive Number Summer\r\n"
						+ "4. Caesar Cipher with Shift Variability\r\n"
						+ "5. Encoding Challenge with ASCII Conversion\r\n"
						+ "6. Exit From the Program");
				input_option = input.next();
			}
		}while(!isQuit);
    }
}

