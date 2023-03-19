package chucknorris;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static String zeroEncoder(char binaryCharacter) {
        if (binaryCharacter == '1') {
            return "0 ";
        } else {
            return "00 ";
        }
    }

    public static void decodeString() {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String inputEncodedString = scanner.nextLine();

        for (int i = 0; i < inputEncodedString.length(); i++) {
            if (inputEncodedString.charAt(i) != '0' && inputEncodedString.charAt(i) != ' ') {
                System.out.println("Encoded string is not valid.");
                return;
            }
        }

        String[] inputEncodedStringArray = inputEncodedString.split(" ");

        if (inputEncodedStringArray.length % 2 == 1) {
            System.out.println("Encoded string is not valid.");
            return;
        }

        String inputBinaryString = "";
        char previousAppendedChar = '2';

        for (int i = 0; i < inputEncodedStringArray.length; i++) {
            if (i % 2 == 0) {
                if (inputEncodedStringArray[i].equals("0")) {
                    inputBinaryString += "1";
                    previousAppendedChar = '1';
                } else if (inputEncodedStringArray[i].equals("00")) {
                    inputBinaryString += "0";
                    previousAppendedChar = '0';
                } else {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
            }

            else {
                inputBinaryString += "%c".formatted(previousAppendedChar).repeat(inputEncodedStringArray[i].length() - 1);
            }
        }

        if (inputBinaryString.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }

//        System.out.println(inputBinaryString);
        String[] decodedBinaryStringArray = new String[inputBinaryString.length() / 7];
        int beginIndex;
        int endIndex;
        for (int i = 0; i < decodedBinaryStringArray.length; i++) {
            beginIndex = i * 7;
            endIndex = (i * 7) + 7;
            decodedBinaryStringArray[i] = inputBinaryString.substring(beginIndex, endIndex);
        }

        String decodedString = "";
        int parsedIntValue;
        for (int i = 0; i < decodedBinaryStringArray.length; i++) {
            parsedIntValue = Integer.parseInt(decodedBinaryStringArray[i], 2);
            decodedString += "%c".formatted(parsedIntValue);
        }

        System.out.println("Decoded string:");
        if (decodedString != "") {
            System.out.println(decodedString);
        }
    }

    public static void encodeString() {
        System.out.println("Input string:");

        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine();

        String inputStringBinary = "";
        System.out.println("Encoded string:");
        for (int i = 0; i < inputString.length(); i++) {
            char characterValue = inputString.charAt(i);
            String characterBinary = "%07d".formatted(Integer.parseInt(Integer.toBinaryString(characterValue)));
            inputStringBinary += characterBinary;
//            System.out.printf("%c = %07d\n", characterValue, Integer.parseInt(Integer.toBinaryString(characterValue)));
        }
//        System.out.println(inputStringBinary);
        char currentCharacter;
        char previousCharacter = '2';
        int consectiveNumbers = 0;
        String encodedString = "";

        for (int i = 0; i < inputStringBinary.length(); i++) {
            currentCharacter = inputStringBinary.charAt(i);
            if (i == 0) {
                encodedString += zeroEncoder(currentCharacter);
                previousCharacter = currentCharacter;
                consectiveNumbers += 1;
            } else if (previousCharacter != currentCharacter) {
                encodedString += "0".repeat(consectiveNumbers);
                encodedString += " ";
                consectiveNumbers = 1;
                encodedString += zeroEncoder(currentCharacter);
                previousCharacter = currentCharacter;
            } else if (previousCharacter == currentCharacter) {
                consectiveNumbers +=1;
            }

            if (i == inputStringBinary.length() - 1) {
                encodedString += "0".repeat(consectiveNumbers);
            }
        }
        System.out.println(encodedString);
    }
    public static void main(String[] args) {

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            Scanner scanner = new Scanner(System.in);

            String inputInstruction = scanner.nextLine();

            if (inputInstruction.equals("decode")) {
                decodeString();
                System.out.println();
            } else if (inputInstruction.equals("encode")) {
                encodeString();
                System.out.println();
            } else if (inputInstruction.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("There is no " + "'" + inputInstruction + "'" + " operation");
                System.out.println();
            }
        }


    }
}