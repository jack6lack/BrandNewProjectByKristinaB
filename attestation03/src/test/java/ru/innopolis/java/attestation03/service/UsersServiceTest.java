package ru.innopolis.java.attestation03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.innopolis.java.attestation03.dto.UserDTO;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.impl.UsersServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "PlayerName", "Race1", "Class1", false);
        userDTO = new UserDTO("PlayerName", "Race1", "Class1");
    }

    @Test
    void testGetAll() {
        when(usersRepository.findAll()).thenReturn(List.of(user));

        Iterable<UserDTO> users = usersService.getAll();

        assertNotNull(users);
        assertEquals(1, ((Collection<?>) users).size());
    }

    @Test
    void testGetUserById() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO foundUser = usersService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals("PlayerName", foundUser.getCharacterName());
    }

    @Test
    void testCreateUser() {
        when(usersRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = usersService.create(userDTO);

        assertNotNull(createdUser);
        assertEquals("PlayerName", createdUser.getCharacterName());
        verify(usersRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = usersService.updateUser(1L, userDTO);

        assertNotNull(updatedUser);
        assertEquals("PlayerName", updatedUser.getCharacterName());
        verify(usersRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUserById() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(usersRepository).delete(user);

        usersService.deleteById(1L);

        verify(usersRepository, times(1)).delete(user);
    }

    @Test
    void testSoftDeleteUserById() {
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        assertFalse(user.isDeleted());
        usersService.softDeleteById(1L);

        assertTrue(user.isDeleted());
        verify(usersRepository, times(1)).save(user);
    }


    @Test
    void testFindUserByName() {
        when(usersRepository.findByCharacterName("PlayerName")).thenReturn(Optional.of(user));

        Optional<UserDTO> foundUser = usersService.findUserByName("PlayerName");

        assertTrue(foundUser.isPresent());
        assertEquals("PlayerName", foundUser.get().getCharacterName());
    }
}
