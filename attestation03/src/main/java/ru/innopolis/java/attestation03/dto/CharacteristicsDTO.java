package ru.innopolis.java.attestation03.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class CharacteristicsDTO {

    private Long id;
    private Long userId;
    private int strength;
    private int defense;

    public void setId(Long id) {
        throw new UnsupportedOperationException("Id cannot be manually set");
    }

    @Builder
    public CharacteristicsDTO(Long userId, Integer strength, Integer defense) {
        this.userId = userId;
        this.strength = strength;
        this.defense = defense;
    }
}
