package ru.innopolis.java.otherHW;

import java.util.HashSet;
import java.util.Scanner;

public class TheCoolestMain {
    /*
    �������� ����� ��������, �������� �� ������ ����������� (� ��� ������� �������� ��������� ������)
�� ���� �������� ������, ������� ������� �� ��������� ��������� ����. ���������, ��� � ������ ����������� ��� �������
����������� �������� ���� �� ���� ���! asdsadqdwe -> false qwertyuiopasdfghjklzxcvbnmljhjqenb -> true
*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1
        String st = scanner.next();
        st.replaceAll("\\s", "").toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(st);
        if (st.equals(stringBuilder.reverse().toString())) {
            System.out.println("��� ���������!");
        } else {
            System.out.println("��� �� ���������!");
        }

        //2
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphabetButChar = alphabet.toCharArray();
        String check = scanner.next();
        char[] checkButChar = check.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < checkButChar.length; i++) {
            if (checkButChar.length < 26) {
                System.out.println("�� ����� ����");
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
            System.out.println("��� ����� �������");
        } else {
            System.out.println("� ���������, ��� �� ���� �������, ����� �������");
        }
    }
}
