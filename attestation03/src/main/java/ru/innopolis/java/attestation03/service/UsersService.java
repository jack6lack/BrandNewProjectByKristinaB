package ru.innopolis.java.attestation03.service;

import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.UserDTO;

import java.util.Optional;

@Service

public interface UsersService {
    Iterable<UserDTO> getAll();

    UserDTO getUserById(Long userId);

    void deleteById(Long userId);

    void softDeleteById(Long userId);

    UserDTO create(UserDTO userDTO);

    UserDTO updateUser(Long userId, UserDTO newData);

    Optional<UserDTO> findUserByName(String name);
}
