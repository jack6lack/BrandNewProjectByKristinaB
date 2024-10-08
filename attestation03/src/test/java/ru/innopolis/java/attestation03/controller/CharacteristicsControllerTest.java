package ru.innopolis.java.attestation03.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.innopolis.java.attestation03.dto.CharacteristicsDTO;
import ru.innopolis.java.attestation03.service.CharacteristicsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CharacteristicsControllerTest {

    @Mock
    private CharacteristicsService characteristicsService;

    @InjectMocks
    private CharacteristicsController characteristicsController;

    private List<CharacteristicsDTO> characteristicsDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        characteristicsDTOList = new ArrayList<>();
        characteristicsDTOList.add(new CharacteristicsDTO(1L, 10, 5));
        characteristicsDTOList.add(new CharacteristicsDTO(2L, 15, 10));
        characteristicsDTOList.add(new CharacteristicsDTO(3L, 20, 15));
    }

    @Test
    void getAll() {
        when(characteristicsService.getAll()).thenReturn(characteristicsDTOList);

        ResponseEntity<Iterable<CharacteristicsDTO>> response = characteristicsController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getById() {
        CharacteristicsDTO dto = new CharacteristicsDTO(1L, 10, 5);
        when(characteristicsService.getCharacteristicById(1L)).thenReturn(dto);

        ResponseEntity<CharacteristicsDTO> response = characteristicsController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void create() {
        CharacteristicsDTO dto = new CharacteristicsDTO(1L, 10, 5);
        when(characteristicsService.create(any(CharacteristicsDTO.class))).thenReturn(dto);

        ResponseEntity<CharacteristicsDTO> response = characteristicsController.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void update() {
        CharacteristicsDTO dto = new CharacteristicsDTO(1L, 10, 5);
        when(characteristicsService.updateCharacteristic(1L, dto)).thenReturn(dto);

        ResponseEntity<CharacteristicsDTO> response = characteristicsController.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void delete() {
        doNothing().when(characteristicsService).softDeleteById(1L);

        ResponseEntity<Void> response = characteristicsController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(characteristicsService, times(1)).softDeleteById(1L);
    }

    @Test
    void updateStrength() {
        CharacteristicsDTO dto = new CharacteristicsDTO(1L, 15, 5);
        when(characteristicsService.updateStrength(1L, 15)).thenReturn(dto);

        ResponseEntity<CharacteristicsDTO> response = characteristicsController.updateStrength(1L, 15);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void getById_NotFound() {
        when(characteristicsService.getCharacteristicById(99L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<CharacteristicsDTO> response = characteristicsController.getById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
