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

        try (Scanner scanner = new Scanner(System.in);){
            input = scanner.nextLine();
            inputAsNumber = createNumberFromInput(input);
        } catch (RuntimeException ex){
            System.err.println(ex.getMessage());
            return;
        }

        System.out.println(inputAsNumber + "\t" + (isValidGreekNumber(inputAsNumber)? ConstValues.PHONE_NUMBER_VALID : ConstValues.PHONE_NUMBER_INVALID));

        getAllPossibleNumbers(input);
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
        List<List<String>> options = new ArrayList<>();
        String[] tokens = input.split("\\s");
        List<Integer> parsedParts = Arrays.stream(tokens).mapToInt(Integer::parseInt).boxed().toList();

        for (int i = 0; i < parsedParts.size(); i++){
            List<String> variants = new ArrayList<>();
            int currentPart = parsedParts.get(i);
            int nextPart = (i + 1 < parsedParts.size())?parsedParts.get(i + 1) : Integer.MAX_VALUE;

            variants.add(String.valueOf(currentPart));

            if ((currentPart % 100 == 0 && nextPart < 100) || (currentPart % 10 == 0 && nextPart < 10)){
                variants.add(String.valueOf(currentPart + nextPart));

                System.out.println("i: " + variants);
            }

            if (currentPart > 20 && currentPart < 100  && currentPart % 10 != 0){
                String firstVariant = String.valueOf((currentPart / 10) * 10);
                String secondVariant = String.valueOf(currentPart % 10);
                variants.add(firstVariant + " " + secondVariant);

                System.out.println("i: " + variants);
            } else if (currentPart > 100 && currentPart < 1000  && currentPart % 100 != 0){
                String firstVariant = String.valueOf((currentPart / 100) * 100);
                String secondVariant = String.valueOf(currentPart % 100);
                variants.add(firstVariant + " " + secondVariant);

                System.out.println("i: " + variants);
            }

            options.add(variants);
//            if (parsedParts[i] > 10 && parsedParts[i] < 100 && parsedParts[i] % 10 != 0){
//                String possiblePart = ((parsedParts[i] / 10) * 10) + " " + (parsedParts[i] % 10);
//                System.out.println("pp3: " + parsedParts[i] + " : " + possiblePart);
//            } else if (parsedParts[i] > 100 && parsedParts[i] < 1000 && parsedParts[i] % 100 != 0){
//                String possiblePart = ((parsedParts[i] / 100) * 100) + " " + (parsedParts[i] % 100);
//                System.out.println("pp4: " + parsedParts[i] + " : " + possiblePart);
//            }
        }

        return possibleNumbers;
    }
}