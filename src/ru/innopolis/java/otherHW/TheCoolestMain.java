package ru.innopolis.java.otherHW;

import java.util.HashSet;
import java.util.Scanner;

public class TheCoolestMain {
    /*
    Напишите метод проверки, является ли строка палиндромом (в обе стороны читается одинаково строка)
На вход подается строка, которая состоит из маленьких латинских букв. Проверить, что в строке встречаются все символы
английского алфавита ХОТЯ БЫ ОДИН РАЗ! asdsadqdwe -> false qwertyuiopasdfghjklzxcvbnmljhjqenb -> true
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1
        String st = scanner.next();
        st.replaceAll("\\s", "").toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(st);
        if (st.equals(stringBuilder.reverse().toString())) {
            System.out.println("это палиндром!");
        } else {
            System.out.println("это не палиндром!");
        }

        //2
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetButChar = alphabet.toCharArray();
        String check = scanner.next();
        char[] checkButChar = check.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < checkButChar.length; i++) {
            if (checkButChar.length < 26) {
                System.out.println("ну точно фолс");
                break;
            }
            for (int j = 0; j < alphabetButChar.length; j++) {
                if (checkButChar[i] == alphabetButChar[j]) {
                    System.out.println(checkButChar[i] + " = " + alphabetButChar[j]);
                    set.add(checkButChar[i]);
                }
            }
        }
        if (set.size() == 26) {
            System.out.println("все буквы найдены");
        } else {
            System.out.println("к сожалению, это не весь алфавит, можно плакать");
        }
    }
}
