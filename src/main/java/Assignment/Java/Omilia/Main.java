package Assignment.Java.Omilia;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Application for interpreting sequences of natural numbers entered by the user,
 * taking into account possible ambiguities in grouping.
 *
 * It generates all possible combined number interpretations,
 * validates each as a Greek phone number, and prints the results.
 */
public class Main {
    public static final class ConstValues{
        static final String WELCOME_STRING = "Enter phone number(and press enter):";
        static final String PHONE_NUMBER_VALID = "[phone number: VALID]";
        static final String PHONE_NUMBER_INVALID = "[phone number: INVALID]";
        static final String TEN_SYMBOLS_PATTERN = "(^2\\d{9}$)" + "|" + "(^69\\d{8}$)";
        static final String FOURTEEN_SYMBOLS_PATTERN = "(^00302\\d{9}$)" + "|" + "(^003069\\d{8}$)";

        private ConstValues(){}
    }

    /**
     * Program entry point.
     *
     * Reads a sequence of numbers from the console,
     * generates all possible interpretations,
     * and prints them along with phone number validation results.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        List<String> allPossibleNumbers;
        String input, inputAsNumber;
        System.out.println(ConstValues.WELCOME_STRING);

        try (Scanner scanner = new Scanner(System.in)){
            input = scanner.nextLine();
            String[] numberParts = validateInputAndSplitToParts(input);
            inputAsNumber = createNumberFromParts(numberParts);
            allPossibleNumbers = getAllPossibleNumbersFromParts(numberParts);
        } catch (RuntimeException ex){
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("--- Basic version ---");
        System.out.println(inputAsNumber + "\t" + (isValidGreekNumber(inputAsNumber)? ConstValues.PHONE_NUMBER_VALID : ConstValues.PHONE_NUMBER_INVALID));

        System.out.println("--- Advanced version ---");
        List<String> uniquePossibleNumber = new ArrayList<>(new HashSet<>(allPossibleNumbers));

        for (int i = 0; i < uniquePossibleNumber.size(); i++) {
            String number = uniquePossibleNumber.get(i);
            System.out.println(
                    "Interpretation " + (i + 1) + ": " + number + " "
                    + (isValidGreekNumber(number) ? ConstValues.PHONE_NUMBER_VALID : ConstValues.PHONE_NUMBER_INVALID)
            );
        }
    }

    /**
     * Splits the input string by spaces and validates that each part
     * is a number containing 1 to 3 digits.
     *
     * @param input the input string
     * @return array of validated number parts as strings
     * @throws RuntimeException if any part is invalid
     */
    public static String[] validateInputAndSplitToParts(String input){
            String[] parts = input.trim().split("\\s+");
            for (String part : parts)
                if (!part.matches("\\d{1,3}"))
                    throw new RuntimeException("Invalid part of number: " + part);

            return parts;
    }

    /**
     * Validates whether a given phone number string conforms to Greek phone number rules.
     *
     * Valid phone numbers have either 10 or 14 digits.
     * - 10-digit numbers must start with '2' or '69'.
     * - 14-digit numbers must start with '00302' or '003069'.
     *
     * @param phoneNumber The concatenated phone number string
     * @return true if the phone number is valid according to the rules, false otherwise
     */
    public static boolean isValidGreekNumber(String phoneNumber){

        int inputStringLength = phoneNumber.length();

        switch (inputStringLength){
            case 10 -> {
                return phoneNumber.matches(ConstValues.TEN_SYMBOLS_PATTERN);
            }
            case 14 -> {
                return phoneNumber.matches(ConstValues.FOURTEEN_SYMBOLS_PATTERN);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * Constructs a concatenated number string from the user input parts.
     *
     * Example:
     * Input: "30 2 5 58"
     * Output: "302558"
     *
     * @param numberParts the raw input string containing numbers separated by spaces
     * @return a single concatenated string representing the combined number
     */
    public static String createNumberFromParts(String[] numberParts){
        StringBuilder numberBuilder = new StringBuilder();

        for (String part : numberParts)
                numberBuilder.append(part);

        return numberBuilder.toString();
    }

    /**
     * Parses the input string into a list of integers and generates all possible
     * number interpretations by combining these integers with consideration for ambiguities.
     *
     * For example, the input "20 5" could produce "205" and "25".
     *
     * @param numberParts a string array containing numbers parts (each number up to three digits)
     * @return a list of all possible combined number interpretations as strings
     */
    public static List<String> getAllPossibleNumbersFromParts(String[] numberParts){
        List<Integer> parsedParts = Arrays.stream(numberParts).map(Integer::parseInt).toList();

        List<String> result = new ArrayList<>();
        buildCombinations(parsedParts, 0, "", result);

        return result;
    }

    /**
     * Recursively generates all possible number interpretations from the input sequence,
     * considering ambiguities in grouping (e.g., "20 5" → "205" or "25").
     *
     * @param parsedParts List of input numbers as integer
     * @param index Current index in the input list
     * @param currentNumber String accumulating the current interpretation
     * @param results List collecting all generated interpretations
     */
    private static void buildCombinations(List<Integer> parsedParts, int index, String currentNumber, List<String> results){
        if (index >= parsedParts.size()) {
            results.add(currentNumber);
            return;
        }

        int currentPart = parsedParts.get(index);

        // Add the current part
        buildCombinations(parsedParts, index + 1, currentNumber + currentPart, results);

        // Combine with the next part if it forms a valid alternative
        if (index + 1 < parsedParts.size()){
            int nextPart = parsedParts.get(index + 1);

            if ((currentPart % 100 == 0 && nextPart < 100) || (currentPart > 20 && currentPart % 10 == 0 && nextPart < 10)){
                int variant = currentPart + nextPart;
                buildCombinations(parsedParts, index + 2, currentNumber + variant, results);
            }
        }

        // Split current part into multiple parts if possible
        if (currentPart > 20 && currentPart < 100  && currentPart % 10 != 0){
            int firstVariant = (currentPart / 10) * 10;
            int secondVariant = currentPart % 10;
            buildCombinations(parsedParts, index + 1, currentNumber + firstVariant + secondVariant, results);

        } else if (currentPart > 100 && currentPart < 1000  && currentPart % 100 != 0){
            int firstVariant = (currentPart / 100) * 100;
            int secondVariant = currentPart % 100;
            buildCombinations(parsedParts, index + 1, currentNumber + firstVariant + secondVariant, results);
        }
    }
}