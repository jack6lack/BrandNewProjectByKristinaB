package ru.innopolis.java.attestation03.service;

import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.CharacteristicsDTO;

@Service

public interface CharacteristicsService {
    Iterable<CharacteristicsDTO> getAll();

    CharacteristicsDTO getCharacteristicById(Long characteristicId);

    void deleteById(Long characteristicId);

    void softDeleteById(Long characteristicId);

    CharacteristicsDTO create(CharacteristicsDTO characteristicsDTO);

    CharacteristicsDTO updateCharacteristic(Long characteristicId, CharacteristicsDTO newData);

    CharacteristicsDTO updateStrength(Long userId, int newStrength);
}
