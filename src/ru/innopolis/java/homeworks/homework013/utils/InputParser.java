package ru.innopolis.java.homeworks.homework013.utils;

public class InputParser {
    private static final Mapper<String, Integer> parseCount = stringCount -> {
        try {
            return Integer.parseInt(stringCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("невалидное значение");
        }
    };

    public static Mapper<String, Integer> validateCount = stringCount -> {
        try {
            return parseCount.map(stringCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    };
    private static final Mapper<String, Double> parseNumber = stringCount -> {
        try {
            return Double.parseDouble(stringCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("невалидное значение");
        }
    };

    public static Mapper<String, Double> validateNumber = stringCount -> {
        try {
            return parseNumber.map(stringCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0.0;
        }
    };

}
