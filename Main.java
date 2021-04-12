package encryptdecrypt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number = 0;
        String mySimple = "";
        String in = "";
        String out = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-key")) {
                number = Integer.parseInt(args[i + 1]);
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-data")) {
                mySimple = args[i + 1];
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-out")) {
                out = args[i + 1];
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-in")) {
                in = args[i + 1];
                File file = new File(in);
                try {
                    Scanner scanner = new Scanner(file);
                    String lin = scanner.nextLine();
                    scanner.close();
                    for (String arg : args) {
                        if (arg.equals("enc")) {
                            StringBuffer code = encryptLin(lin, number);
                            String result = String.valueOf(code);
                            File file2 = new File(out);
                            FileWriter writer = new FileWriter(file2);
                            writer.write(result);
                            writer.close();
                        } else if (arg.equals("dec")) {
                            StringBuffer code = decryptionLin(lin, number);
                            String result = String.valueOf(code);
                            File file2 = new File(out);
                            FileWriter writer = new FileWriter(file2);
                            writer.write(result);
                            writer.close();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("In file is empty");
                }
            }
        }
        for (int i = 0; i < args.length; i++) {
            for (String arg : args) {
                if (args[i].equals("-in") && arg.equals("shift")) {
                    in = args[i + 1];
                    File file = new File(in);
                    try {
                        Scanner scanner = new Scanner(file);
                        String lin1 = scanner.nextLine();
                        scanner.close();
                        for (String arg1 : args) {
                            if (arg1.equals("enc")) {
                                StringBuffer code1 = encryptShift(lin1, number);
                                String result1 = String.valueOf(code1);
                                File file2 = new File(out);
                                FileWriter writer = new FileWriter(file2);
                                writer.write(result1);
                                writer.close();
                            } else if (arg1.equals("dec")) {
                                StringBuffer code1 = decryptionShift(lin1, number);
                                String result1 = String.valueOf(code1);
                                File file2 = new File(out);
                                FileWriter writer = new FileWriter(file2);
                                writer.write(result1);
                                writer.close();
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("In file is empty");
                    }
                }
            }
        }
        for (String arg : args) {
            if (arg.equals("enc")) {
                System.out.println(encrypt(mySimple, number));
            } else if (arg.equals("dec")) {
                System.out.println(decryption(mySimple, number));
            }
        }

    }


    public static StringBuffer encrypt(String mySimple, int number) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < mySimple.length(); i++) {
            if (Character.isUpperCase(mySimple.charAt(i))) {
                char ch = (char) (((int) mySimple.charAt(i) +
                        number - 32) % 90 + 32);
                result.append(ch);
            } else {
                char ch = (char) (((int) mySimple.charAt(i) +
                        number - 122) % 90 + 122);
                result.append(ch);
            }
        }
        return result;
    }

    public static StringBuffer encryptLin(String lin, int number) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < lin.length(); i++) {
            if (Character.isUpperCase(lin.charAt(i))) {
                char ch = (char) (((int) lin.charAt(i) +
                        number - 32) % 90 + 32);
                result.append(ch);
            } else {
                char ch = (char) (((int) lin.charAt(i) +
                        number - 122) % 90 + 122);
                result.append(ch);
            }
        }
        return result;
    }

    public static StringBuffer decryption(String mySimple, int number) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < mySimple.length(); i++) {
            if (mySimple.charAt(i) == '%') {
                result.append(" ");
            } else if (Character.isUpperCase(mySimple.charAt(i))) {
                char ch = (char) (((int) mySimple.charAt(i) -
                        number - 32) % 90 + 32);
                result.append(ch);
            } else {
                char ch = (char) (((int) mySimple.charAt(i) -
                        number - 122) % 90 + 122);
                result.append(ch);
            }
        }
        return result;
    }

    public static StringBuffer decryptionLin(String lin, int number) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < lin.length(); i++) {
            if (lin.charAt(i) == '%') {
                result.append(" ");
            } else if (Character.isUpperCase(lin.charAt(i))) {
                char ch = (char) (((int) lin.charAt(i) -
                        number - 32) % 90 + 32);
                result.append(ch);
            } else {
                char ch = (char) (((int) lin.charAt(i) -
                        number - 122) % 90 + 122);
                result.append(ch);
            }
        }
        return result;
    }

    public static StringBuffer encryptShift(String lin1, int number) {
        StringBuffer result = new StringBuffer();

        for (char character : lin1.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + number) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result;
    }

    public static StringBuffer decryptionShift(String lin1, int number) {



        return encryptShift(lin1,(26-(number%26)));

    }
}

