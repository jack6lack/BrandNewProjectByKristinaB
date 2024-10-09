package ru.innopolis.java.attestation03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.innopolis.java.attestation03.dto.CharacteristicsDTO;
import ru.innopolis.java.attestation03.entity.Characteristics;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.CharacteristicsRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.impl.CharacteristicsServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CharacteristicsServiceTest {

    @Mock
    private CharacteristicsRepository characteristicsRepository;

    @Mock
    private UsersRepository usersRepository;


    @InjectMocks
    private CharacteristicsServiceImpl characteristicsService;

    private Characteristics characteristics;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "name", "race", "class", false);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));

        characteristics = new Characteristics(1L, user, 10, 5, false);
    }

    @Test
    void testGetAll() {
        when(characteristicsRepository.findAll()).thenReturn(List.of(characteristics));

        Iterable<CharacteristicsDTO> result = characteristicsService.getAll();

        assertNotNull(result);
        assertEquals(1, ((Collection<?>) result).size());
    }

    @Test
    void testGetCharacteristicById() {
        when(characteristicsRepository.findById(1L)).thenReturn(Optional.of(characteristics));

        CharacteristicsDTO result = characteristicsService.getCharacteristicById(1L);

        assertNotNull(result);
        assertEquals(10, result.getStrength());
    }

    @Test
    void testCreate() {
        when(characteristicsRepository.save(any(Characteristics.class))).thenReturn(characteristics);

        CharacteristicsDTO dto = new CharacteristicsDTO(1L, 10, 5);
        CharacteristicsDTO result = characteristicsService.create(dto);

        assertNotNull(result);
        assertEquals(10, result.getStrength());
        verify(characteristicsRepository, times(1)).save(any(Characteristics.class));
    }

    @Test
    void testUpdateCharacteristic() {
        when(characteristicsRepository.findById(1L)).thenReturn(Optional.of(characteristics));
        when(characteristicsRepository.save(any(Characteristics.class))).thenReturn(characteristics);

        CharacteristicsDTO newDto = new CharacteristicsDTO(1L, 20, 10);
        CharacteristicsDTO result = characteristicsService.updateCharacteristic(1L, newDto);

        assertNotNull(result);
        assertEquals(20, result.getStrength());
        verify(characteristicsRepository, times(1)).save(any(Characteristics.class));
    }

    @Test
    void testDeleteById() {
        when(characteristicsRepository.findById(1L)).thenReturn(Optional.of(characteristics));
        doNothing().when(characteristicsRepository).delete(characteristics);

        characteristicsService.deleteById(1L);

        verify(characteristicsRepository, times(1)).findById(1L);
        verify(characteristicsRepository, times(1)).delete(characteristics);
    }

    @Test
    void testSoftDeleteById() {
        when(characteristicsRepository.findById(1L)).thenReturn(Optional.of(characteristics));
        characteristicsService.softDeleteById(1L);

        verify(characteristicsRepository, times(1)).save(characteristics);
        assertTrue(characteristics.isDeleted());
    }

    @Test
    void testUpdateStrength() {
        when(characteristicsRepository.findByUserId(1L)).thenReturn(Optional.of(characteristics));
        when(characteristicsRepository.save(any(Characteristics.class))).thenReturn(characteristics);

        CharacteristicsDTO result = characteristicsService.updateStrength(1L, 25);

        assertNotNull(result);
        assertEquals(25, result.getStrength());
    }
}
