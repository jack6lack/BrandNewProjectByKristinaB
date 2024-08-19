package ru.innopolis.java.homeworks.homework10;

import java.util.Arrays;

public class Sequence {
    public static int[] filter(int[] array, ByCondition condition) {
        int[] newArray = new int[array.length];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (condition.isOk(array[i])) {
                newArray[counter] = array[i];
                counter++;
            }
        }
        newArray = Arrays.copyOfRange(newArray, 0, counter);
        return newArray;
    }
}
