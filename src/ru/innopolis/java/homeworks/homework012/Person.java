package ru.innopolis.java.homeworks.homework012;

public class Person {

    private String secondName;
    private String firstName;
    private String patronymic;
    private String dateOfBirth;
    private Long phoneNumber;
    private Character gender;
    private Integer age;

    public Person() {
    }

    public Person(String secondName, String firstName, String patronymic, String dateOfBirth, Long phoneNumber, Character gender, Integer age) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + secondName +
                "><" + firstName +
                "><" + patronymic +
                "><" + dateOfBirth +
                "><" + phoneNumber +
                "><" + gender +
                ">";
    }
}
