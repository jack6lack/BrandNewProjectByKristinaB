package ru.innopolis.java.attestation.attestation01.support;

import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.repositories.Mapper;

import java.time.LocalDateTime;
import java.util.List;

public class DataWriterUtils {
    public static void printBeautifulLines(List<User> list, String fileName) {
        TxtDataWriter writer = new TxtDataWriter(fileName);
        for (User user : list) {
            writer.log(readUserMakeLine.map(user));
        }
    }

    public static Mapper<String, User> readLineMakeUser = line -> {
        User user = new User();
        String[] userData = line.split("\\|");
        user.setId(userData[0]);
        user.setLt(LocalDateTime.parse(userData[1]));
        user.setLogin(userData[2]);
        user.setPassword(userData[3]);
        user.setConfirmPassword(userData[4]);
        user.setSecondName(userData[5]);
        user.setFirstName(userData[6]);
        if (userData[7].equals("NULL")) {
            user.setPatronymic(null);
        } else {
            user.setPatronymic(userData[7]);
        }
        if (userData[8].equals("NULL")) {
            user.setAge(null);
        } else {
            user.setAge(Integer.parseInt(userData[8]));
        }
        user.setWorker(Boolean.parseBoolean(userData[9]));

        return user;
    };

    public static Mapper<User, String> readUserMakeLine = user -> {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId())
                .append("|")
                .append(user.getLt())
                .append("|")
                .append(user.getLogin())
                .append("|")
                .append(user.getPassword())
                .append("|")
                .append(user.getConfirmPassword())
                .append("|")
                .append(user.getSecondName())
                .append("|")
                .append(user.getFirstName())
                .append("|");
        if (user.getPatronymic() == null) {
            sb.append("NULL")
                    .append("|");
        } else {
            sb.append(user.getPatronymic())
                    .append("|");
        }
        if (user.getAge() == null) {
            sb.append("NULL")
                    .append("|");
        } else {
            sb.append(user.getAge())
                    .append("|");
        }
        sb.append(user.isWorker());

        return sb.toString();
    };
}
