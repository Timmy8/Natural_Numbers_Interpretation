package Assignment.Java.Omilia;

import java.util.Scanner;

public class Main {
    public static class ConstValues{
        static final String WELCOME_STRING = "Enter phone number(and press enter):";
        static final String PHONE_NUMBER_VALID = "[phone number: VALID]";
        static final String PHONE_NUMBER_INVALID = "[phone number: INVALID]";
        static final String TEN_SYMBOLS_PATTERN = "(^2\\d{9}$)" + "|" + "(^69\\d{8}$)";
        static final String FOURTEEN_SYMBOLS_PATTER = "(^00302\\d{9}$)" + "|" + "(^003069\\d{8}$)";
    }
    public static void main(String[] args) {
        int enteredStringLength;
        String enteredStringNoSpace;

        System.out.println(ConstValues.WELCOME_STRING);
        Scanner scanner = new Scanner(System.in);

        enteredStringNoSpace = scanner.nextLine().replaceAll(" ","");
        enteredStringLength = enteredStringNoSpace.length();

        System.out.print(enteredStringNoSpace + "\t");

        switch (enteredStringLength){
            case 10 -> {
                if (enteredStringNoSpace.matches(ConstValues.TEN_SYMBOLS_PATTERN))
                    System.out.println(ConstValues.PHONE_NUMBER_VALID);
                else System.out.println(ConstValues.PHONE_NUMBER_INVALID);
            }
            case 14 -> {
                if (enteredStringNoSpace.matches(ConstValues.FOURTEEN_SYMBOLS_PATTER))
                    System.out.println(ConstValues.PHONE_NUMBER_VALID);
                else System.out.println(ConstValues.PHONE_NUMBER_INVALID);
            }
            default -> System.out.println(ConstValues.PHONE_NUMBER_INVALID);
        }
    }
}