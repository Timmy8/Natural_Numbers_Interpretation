package Assignment.Java.Omilia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final class ConstValues{
        static final String WELCOME_STRING = "Enter phone number(and press enter):";
        static final String PHONE_NUMBER_VALID = "[phone number: VALID]";
        static final String PHONE_NUMBER_INVALID = "[phone number: INVALID]";
        static final String TEN_SYMBOLS_PATTERN = "(^2\\d{9}$)" + "|" + "(^69\\d{8}$)";
        static final String FOURTEEN_SYMBOLS_PATTERN = "(^00302\\d{9}$)" + "|" + "(^003069\\d{8}$)";

        private ConstValues(){}
    }


    public static void main(String[] args) {
        String input, inputAsNumber;
        System.out.println(ConstValues.WELCOME_STRING);

        try (Scanner scanner = new Scanner(System.in)){
            input = scanner.nextLine();
            inputAsNumber = createNumberFromInput(input);
        } catch (RuntimeException ex){
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println("--- Basic version ---");
        System.out.println(inputAsNumber + "\t" + (isValidGreekNumber(inputAsNumber)? ConstValues.PHONE_NUMBER_VALID : ConstValues.PHONE_NUMBER_INVALID));

        System.out.println("--- Advanced version ---");
        List<String> allPossibleNumbers = getAllPossibleNumbers(input);
        for (int i = 0; i < allPossibleNumbers.size(); i++) {
            String number = allPossibleNumbers.get(i);
            System.out.println(
                    "Interpretation " + (i + 1) + ": " + number + " "
                    + (isValidGreekNumber(number) ? ConstValues.PHONE_NUMBER_VALID : ConstValues.PHONE_NUMBER_INVALID)
            );
        }
    }

    public static boolean isValidGreekNumber(String number){

        int inputStringLength = number.length();

        switch (inputStringLength){
            case 10 -> {
                return number.matches(ConstValues.TEN_SYMBOLS_PATTERN);
            }
            case 14 -> {
                return number.matches(ConstValues.FOURTEEN_SYMBOLS_PATTERN);
            }
            default -> {
                return false;
            }
        }
    }

    public static String createNumberFromInput(String input){
        StringBuilder numberBuilder = new StringBuilder();

        for (String part : input.trim().split("\\s+"))
            if (part.matches("\\d{1,3}")) {
                numberBuilder.append(part);
            } else {
                throw new RuntimeException("Invalid part of number: " + part);
            }

        return numberBuilder.toString();
    }

    public static List<String> getAllPossibleNumbers(String input){
        String[] tokens = input.trim().split("\\s");
        List<Integer> parsedParts = Arrays.stream(tokens)
                .map(part ->{
            try{
                return Integer.parseInt(part);
            } catch (NumberFormatException ex){
                throw new RuntimeException("Invalid part of number: " + part);
            }
        }).toList();

        List<String> result = new ArrayList<>();
        buildCombinations(parsedParts, 0, "", result);

        return result;
    }

    private static void buildCombinations(List<Integer> parsedParts, int index, String currentNumber, List<String> result){
        if (index >= parsedParts.size()) {
            result.add(currentNumber.trim());
            return;
        }

        int currentPart = parsedParts.get(index);

        buildCombinations(parsedParts, index + 1, currentNumber + currentPart, result);

        // Check if we can combine two numbers into one
        if (index + 1 < parsedParts.size()){
            int nextPart = parsedParts.get(index + 1);

            if ((currentPart % 100 == 0 && nextPart < 100) || (currentPart > 20 && currentPart % 10 == 0 && nextPart < 10)){
                int variant = currentPart + nextPart;
                buildCombinations(parsedParts, index + 2, currentNumber + variant, result);
            }
        }

        // Check if we can split one number into two different
        if (currentPart > 20 && currentPart < 100  && currentPart % 10 != 0){
            int firstVariant = (currentPart / 10) * 10;
            int secondVariant = currentPart % 10;
            buildCombinations(parsedParts, index + 1, currentNumber + firstVariant + secondVariant, result);

        } else if (currentPart > 100 && currentPart < 1000  && currentPart % 100 != 0){
            int firstVariant = (currentPart / 100) * 100;
            int secondVariant = currentPart % 100;
            buildCombinations(parsedParts, index + 1, currentNumber + firstVariant + secondVariant, result);
        }
    }
}