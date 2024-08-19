package ru.innopolis.java.homeworks.homework10;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //main
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 87, 654};
        int y = Arrays.stream(ints).sum();
        ByCondition evenNumber = (x) -> x % 2 == 0;
        System.out.println("только четные числа из массива: " + Arrays.toString(Sequence.filter(ints, evenNumber)));//фильтровать массив, вернуть массив четных чисел
        System.out.println("является ли сумма чисел массива четным числом: " + evenNumber.isOk(y));//сложить элементы массива, вернуть "чет"/"нечет"

        //add
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"
        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println(i);
        System.out.println(s);
        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);
    }
}
