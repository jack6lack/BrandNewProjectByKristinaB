package ru.innopolis.java.attestation.attestation01.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private String id;
    private LocalDateTime lt;
    private String login;
    private String password;
    private String confirmPassword;
    private String secondName;
    private String firstName;
    private String patronymic;
    private Integer age;
    private boolean isWorker;

    public User(String id, LocalDateTime lt, String login, String password, String confirmPassword, String secondName, String firstName, String patronymic, Integer age, boolean isWorker) {
        this.id = id;
        this.lt = lt;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        this.isWorker = isWorker;
    }

    public User(String login, String password, String confirmPassword, String secondName, String firstName, String patronymic, Integer age, boolean isWorker) {
        this.id = UUID.randomUUID().toString();
        this.lt = LocalDateTime.now();
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        this.isWorker = isWorker;
    }

    public User() {
        this.id = UUID.randomUUID().toString();
        this.lt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLt() {
        return lt;
    }

    public void setLt(LocalDateTime lt) {
        this.lt = lt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", lt=" + lt +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", isWorker=" + isWorker +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isWorker == user.isWorker && Objects.equals(id, user.id) && Objects.equals(lt, user.lt) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword) && Objects.equals(secondName, user.secondName) && Objects.equals(firstName, user.firstName) && Objects.equals(patronymic, user.patronymic) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lt, login, password, confirmPassword, secondName, firstName, patronymic, age, isWorker);
    }
}
