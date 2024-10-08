package ru.innopolis.java.attestation03.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.UserDTO;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Iterable<UserDTO> getAll() {
        return convertToDTOList(usersRepository.findAll());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return convertToDTO(user);
    }

    @Override
    public void deleteById(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        usersRepository.delete(user);
    }

    @Override
    public void softDeleteById(Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setDeleted(true);
        usersRepository.save(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = usersRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO newData) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setCharacterName(newData.getCharacterName());
        user.setCharacterRace(newData.getCharacterRace());
        user.setCharacterClass(newData.getCharacterClass());
        return convertToDTO(usersRepository.save(user));
    }

    @Override
    public Optional<UserDTO> findUserByName(String name) {
        return usersRepository.findByCharacterName(name)
                .map(this::convertToDTO);
    }

    private UserDTO convertToDTO(User user) {

        return UserDTO.builder()
                .characterName(user.getCharacterName())
                .characterRace(user.getCharacterRace())
                .characterClass(user.getCharacterClass())
                .build();
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setCharacterName(userDTO.getCharacterName());
        user.setCharacterRace(userDTO.getCharacterRace());
        user.setCharacterClass(userDTO.getCharacterClass());
        return user;
    }

    private Iterable<UserDTO> convertToDTOList(Iterable<User> userList) {
        List<UserDTO> dtoList = new ArrayList<>();
        userList.forEach(user -> dtoList.add(convertToDTO(user)));
        return dtoList;
    }

}
