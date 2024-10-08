package ru.innopolis.java.attestation03.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.CharacteristicsDTO;
import ru.innopolis.java.attestation03.entity.Characteristics;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.CharacteristicsRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.CharacteristicsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CharacteristicsServiceImpl implements CharacteristicsService {

    private final CharacteristicsRepository characteristicsRepository;
    private final UsersRepository usersRepository;

    @Override
    public Iterable<CharacteristicsDTO> getAll() {
        return convertToDTOList(characteristicsRepository.findAll());
    }

    @Override
    public CharacteristicsDTO getCharacteristicById(Long characteristicsId) {
        Characteristics characteristics = characteristicsRepository.findById(characteristicsId)
                .orElseThrow(() -> new EntityNotFoundException("Characteristics not found"));
        return convertToDTO(characteristics);
    }

    @Override
    public void deleteById(Long characteristicId) {
        Characteristics characteristics = characteristicsRepository.findById(characteristicId)
                .orElseThrow(() -> new EntityNotFoundException("Characteristics not found"));
        characteristicsRepository.delete(characteristics);
    }

    @Override
    public void softDeleteById(Long characteristicsId) {
        Characteristics characteristics = characteristicsRepository.findById(characteristicsId)
                .orElseThrow(() -> new EntityNotFoundException("Characteristics not found"));
        characteristics.setDeleted(true);
        characteristicsRepository.save(characteristics);
    }

    @Override
    public CharacteristicsDTO create(CharacteristicsDTO characteristicsDTO) {
        Characteristics characteristics = convertToEntity(characteristicsDTO);
        Characteristics savedCharacteristics = characteristicsRepository.save(characteristics);
        return convertToDTO(savedCharacteristics);
    }

    @Override
    public CharacteristicsDTO updateCharacteristic(Long characteristicsId, CharacteristicsDTO newData) {
        Characteristics characteristics = characteristicsRepository.findById(characteristicsId)
                .orElseThrow(() -> new EntityNotFoundException("Characteristic not found"));
        characteristics.setStrength(newData.getStrength());
        characteristics.setDefense(newData.getDefense());
        if (!characteristics.getUser().getId().equals(newData.getUserId())) {
            User user = usersRepository.findById(newData.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            characteristics.setUser(user);
        }
        return convertToDTO(characteristicsRepository.save(characteristics));
    }

    @Override
    public CharacteristicsDTO updateStrength(Long userId, int newStrength) {
        Characteristics characteristics = characteristicsRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        characteristics.setStrength(newStrength);
        characteristicsRepository.save(characteristics);
        return convertToDTO(characteristics);
    }

    private CharacteristicsDTO convertToDTO(Characteristics characteristics) {
        return CharacteristicsDTO.builder()
                .userId(characteristics.getUser().getId())
                .strength(characteristics.getStrength())
                .defense(characteristics.getDefense())
                .build();
    }

    private Characteristics convertToEntity(CharacteristicsDTO characteristicsDTO) {
        Characteristics characteristics = new Characteristics();
        characteristics.setId(characteristicsDTO.getId());

        User user = usersRepository.findById(characteristicsDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        characteristics.setUser(user);
        characteristics.setStrength(characteristicsDTO.getStrength());
        characteristics.setDefense(characteristicsDTO.getDefense());
        return characteristics;
    }

    private Iterable<CharacteristicsDTO> convertToDTOList(Iterable<Characteristics> characteristicsList) {
        List<CharacteristicsDTO> dtoList = new ArrayList<>();
        characteristicsList.forEach(characteristics -> dtoList.add(convertToDTO(characteristics)));
        return dtoList;
    }
}
