package ru.innopolis.java.attestation03.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.innopolis.java.attestation03.dto.UserDTO;
import ru.innopolis.java.attestation03.service.UsersService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    private List<UserDTO> userDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO("coolName", "coolRace", "coolClass"));
        userDTOList.add(new UserDTO("coolestName", "coolestRace", "coolestClass"));
    }

    @Test
    void getAll() {
        when(usersService.getAll()).thenReturn(userDTOList);

        ResponseEntity<Iterable<UserDTO>> response = usersController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getById_Success() {
        UserDTO dto = new UserDTO("jack", "khajiit", "mage");
        when(usersService.getUserById(1L)).thenReturn(dto);

        ResponseEntity<UserDTO> response = usersController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals("jack", response.getBody().getCharacterName());
    }

    @Test
    void getById_NotFound() {
        when(usersService.getUserById(99L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<UserDTO> response = usersController.getById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        UserDTO dto = new UserDTO("jack", "khajiit", "mage");
        when(usersService.create(any(UserDTO.class))).thenReturn(dto);

        ResponseEntity<UserDTO> response = usersController.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals("jack", response.getBody().getCharacterName());
    }

    @Test
    void update() {
        UserDTO dto = new UserDTO("jack", "khajiit", "mage");
        when(usersService.updateUser(1L, dto)).thenReturn(dto);

        ResponseEntity<UserDTO> response = usersController.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals("jack", response.getBody().getCharacterName());
    }

    @Test
    void delete() {
        doNothing().when(usersService).softDeleteById(1L);

        ResponseEntity<Void> response = usersController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usersService, times(1)).softDeleteById(1L);
    }

    @Test
    void findByName_Success() {
        UserDTO dto = new UserDTO("jack", "khajiit", "mage");
        when(usersService.findUserByName("jack")).thenReturn(Optional.of(dto));

        ResponseEntity<UserDTO> response = usersController.findByName("jack");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals("jack", response.getBody().getCharacterName());
    }

    @Test
    void findByName_NotFound() {
        when(usersService.findUserByName("Unknown")).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = usersController.findByName("Unknown");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
