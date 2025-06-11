package Assignment.Java.Omilia;

import java.util.Scanner;

public class Main {
    public static class ConstValues{
        static final String WELCOME_STRING = "Enter phone number(and press enter):";
        static final String PHONE_NUMBER_VALID = "[phone number: VALID]";
        static final String PHONE_NUMBER_INVALID = "[phone number: INVALID]";
        static final String TEN_SYMBOLS_PATTERN = "(^2\\d{9}$)" + "|" + "(^69\\d{8}$)";
        static final String FOURTEEN_SYMBOLS_PATTERN = "(^00302\\d{9}$)" + "|" + "(^003069\\d{8}$)";
    }
    public static void main(String[] args) {
        String input, inputNumber;
        StringBuilder numberBuilder = new StringBuilder();

        System.out.println(ConstValues.WELCOME_STRING);
        Scanner scanner = new Scanner(System.in);

        input = scanner.nextLine().trim();
        for (String part : input.split("\\s+"))
            if (part.matches("\\d{1,3}")) {
                numberBuilder.append(part);
            } else {
                System.err.println("Invalid part of number: " + part);
                return;
            }

        inputNumber = numberBuilder.toString();
        System.out.print(inputNumber + "\t");

        if (isValidGreekNumber(inputNumber))
            System.out.println(ConstValues.PHONE_NUMBER_VALID);
        else
            System.out.println(ConstValues.PHONE_NUMBER_INVALID);
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
}