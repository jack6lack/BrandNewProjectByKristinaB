package ru.innopolis.java.fourth;

import java.util.Arrays;
import java.util.Scanner;

public class AlsoMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //1
        String[] letters = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m"};
        System.out.println("вводим любую маленькую латинскую букву с клавиатуры и возвращаемся в прошлое: ");
        String theLetter = scanner.next();
        for (int i = 0; i < letters.length; i++) {
            if (theLetter.equals("q")) {
                System.out.println("m");
                break;
            } else if (theLetter.equals(letters[i])) {
                System.out.println(letters[i - 1]);
                break;
            }
        }

        //2
        //>>-->----<<<<--->>-->---->>----->--->>-->>>->>--><--<<----<--<<<--<<--<--<<
        System.out.println("введите до 160 символов >, < и - в произвольном порядке, чтобы узнать, сколько стрел угрожает вам");
        String st = scanner.next();
        int arrows = 0;
        char[] symbols = st.toCharArray();
        char[] arrow1 = {'<', '-', '-', '<', '<'};
        char[] arrow2 = {'>', '>', '-', '-', '>'};

        for (int j = 0; j < symbols.length - 4; j++) {
            if (arrow1[0] == symbols[j]
                    && arrow1[1] == symbols[j + 1]
                    && arrow1[2] == symbols[j + 2]
                    && arrow1[3] == symbols[j + 3]
                    && arrow1[4] == symbols[j + 4]) {
                System.out.println("стрела!");
                arrows++;
            }
            if (arrow2[0] == symbols[j]
                    && arrow2[1] == symbols[j + 1]
                    && arrow2[2] == symbols[j + 2]
                    && arrow2[3] == symbols[j + 3]
                    && arrow2[4] == symbols[j + 4]) {
                System.out.println("стрела!");
                arrows++;
            }
        }
        System.out.println("итоговое количество стрел: " + arrows);

        //3
        System.out.println("введите два слова на латинице: ");
        String theWord = scanner.next() + scanner.next();
        char[] dehortw = theWord.toLowerCase().replaceAll("\\s+", "").toCharArray();
        Arrays.sort(dehortw);
        System.out.println(String.valueOf(dehortw));
    }
}
