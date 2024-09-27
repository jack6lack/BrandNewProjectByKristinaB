package ru.innopolis.java.homeworks.homework012;

import ru.innopolis.java.homeworks.homework012.exceptions.WrongLoginException;
import ru.innopolis.java.homeworks.homework012.exceptions.WrongPasswordException;

public class User {

    public static boolean isMatchingRules(String login, String password, String confirmPassword) {
        String latinOrDigit = "[a-zA-Z\\d_]+";
        try {
            if (!login.matches(latinOrDigit) || login.length() >= 20) {
                throw new WrongLoginException("ошибка: " + login + " - логин может содержать только латинские буквы, цифры, знак подчеркивания и не должен быть 20+ символов");
            } else if (!password.matches(latinOrDigit) || password.length() >= 20) {
                throw new WrongPasswordException("ошибка: " + password + " - пароль может содержать только латинские буквы, цифры, знак подчеркивания и не должен быть 20+ символов");
            } else if (!confirmPassword.equals(password)) {
                throw new WrongPasswordException("ошибка: " + confirmPassword + " - пароли должны совпадать");
            } else {
                return true;
            }
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
