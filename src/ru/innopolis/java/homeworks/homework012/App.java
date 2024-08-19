package ru.innopolis.java.homeworks.homework012;

import ru.innopolis.java.homeworks.homework08.support.TxtLogger;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person person;
        String checkLine;
        System.out.println("заполните следующие поля, разделив их пробелами:\nФамилия Имя Отчество датарождения номертелефона пол возраст\nпо окончании введите FIN");
        for (; ; ) {
            checkLine = scanner.nextLine();
            if (checkLine.equals("FIN")) {
                break;
            }
            String[] data = checkLine.split(" ");
            try {
                if (data.length != 7) {
                    throw new IllegalArgumentException("количество введенных полей не совпадает с требуемым (7)");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            try {
                person = readLineMakePerson(data);
                TxtLogger logger = new TxtLogger("src/ru/innopolis/java/homeworks/homework012/output/" + person.getSecondName());
                logger.log(person.toString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Person readLineMakePerson(String[] data) {
        Person person = new Person();
        for (String dataPiece : data) {
            if (dataPiece.contains(".")) {
                person.setDateOfBirth(dataPiece);
            } else if (dataPiece.length() == 1) {
                person.setGender(dataPiece.charAt(0));
            } else if (dataPiece.matches("[а-яА-Я]+")) {
                if (person.getSecondName() == null) {
                    person.setSecondName(dataPiece);
                } else if (person.getFirstName() == null) {
                    person.setFirstName(dataPiece);
                } else if (person.getPatronymic() == null) {
                    person.setPatronymic(dataPiece);
                }
            } else if (dataPiece.matches("^[0-9]*$")) {
                if (dataPiece.length() <= 3) {
                    person.setAge(Integer.parseInt(dataPiece));
                } else {
                    person.setPhoneNumber(Long.parseLong(dataPiece));
                }
            }
        }
        checkPersonsFieldForNull(person);
        return person;
    }


    private static void checkPersonsFieldForNull(Person person) {
        if (person.getSecondName() == null) {
            throw new IllegalArgumentException("введенный формат фамилии недопустим");
        } else if (person.getFirstName() == null) {
            throw new IllegalArgumentException("введенный формат имени недопустим");
        } else if (person.getPatronymic() == null) {
            throw new IllegalArgumentException("введенный формат отчества недопустим");
        } else if (person.getDateOfBirth() == null) {
            throw new IllegalArgumentException("введенный формат даты рождения недопустим");
        } else if (person.getPhoneNumber() == null) {
            throw new IllegalArgumentException("введенный формат номера телефона недопустим");
        } else if (person.getGender() == null || person.getGender() != 'f' && person.getGender() != 'm') {
            throw new IllegalArgumentException("введенный формат пола недопустим");
        } else if (person.getAge() == null) {
            throw new IllegalArgumentException("введенный формат возраста недопустим");
        }
    }
}
